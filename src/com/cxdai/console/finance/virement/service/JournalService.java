package com.cxdai.console.finance.virement.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.finance.virement.entity.Journal;
import com.cxdai.console.finance.virement.mapper.JournalMapper;
import com.cxdai.console.finance.virement.vo.JournalCnd;
import com.cxdai.console.finance.virement.vo.JournalVo;

@Service
public class JournalService {

	private final static Logger logger = Logger.getLogger(JournalService.class);
	@Autowired
	private JournalMapper journalMapper;

	/****
	 * 获取现金日记账列表
	 * 
	 * @param journalCnd
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	public Page queryListForPage(JournalCnd journalCnd, Integer curPage, Integer pageSize) {
		Page page = new Page(curPage, pageSize);
		Integer totalCount = journalMapper.queryListCountByCnd(journalCnd);
		page.setTotalCount(totalCount);
		List<JournalVo> list = journalMapper.queryListByCnd(journalCnd, page);
		page.setResult(list);
		return page;
	}

	/****
	 * 保存或更新现金日记账
	 * 
	 * @param journalVo
	 */
	@Transactional(rollbackFor = Exception.class)
	public void saveOrUpdateJournal(JournalVo journalVo) {
		JournalVo lastJournal = null;
		BigDecimal balance = BigDecimal.ZERO;
		Integer userId = journalVo.getUpdateuser();
		String ipAddr = journalVo.getUpdateip();

		BigDecimal debitAmount = journalVo.getDebitAmount() == null ? BigDecimal.ZERO : journalVo.getDebitAmount();
		BigDecimal creditAmount = journalVo.getCreditAmount() == null ? BigDecimal.ZERO : journalVo.getCreditAmount();

		journalVo.setStatus(Journal.STATUS_NOMAL);// 正常

		if (journalVo.getId() == null) {
			lastJournal = journalMapper.selectMaxJournal(journalVo);
			if (lastJournal != null) {
				balance = lastJournal.getBalance();
			}

			// 结余=上一条结余+收入-支出
			journalVo.setBalance(balance.add(debitAmount).subtract(creditAmount));
			journalMapper.insert(journalVo);
		} else {
			// 查询正常状态下当前ID的前一条记录
			BigDecimal diff = BigDecimal.ZERO;// 计算变化差值
			Journal oldJournal = journalMapper.selectByPrimaryKey(journalVo.getId());

			lastJournal = journalMapper.selectPreOneJournal(journalVo);
			if (lastJournal != null) {
				balance = lastJournal.getBalance();
			}
			// 结余=上一条结余+收入-支出
			balance = balance.add(debitAmount).subtract(creditAmount);
			diff = balance.subtract(oldJournal.getBalance());
			logger.info("原始结余金额: " + oldJournal.getBalance() + ";修改后结余金额: " + balance + ";二者差值: " + diff);

			// 1.更新当前现金日记账
			journalVo.setBalance(balance);
			journalMapper.updateByPrimaryKey(journalVo);

			// 2.更新操作日期当天此操作时间之后的所有现金日记账结余金额
			List<Journal> list = journalMapper.selectAfterCurrentOperationDateThisDay(journalVo);

			if (list != null && !list.isEmpty()) {
				for (Journal journal : list) {
					balance = journal.getBalance();
					balance = balance.add(diff);
					journal.setBalance(balance);
					journal.setUpdateip(ipAddr);
					journal.setUpdateuser(userId);
					journalMapper.updateByPrimaryKey(journal);
				}
			}

		}
	}

	/***
	 * 现金日记账判断
	 * 
	 * @param journalVo
	 * @return
	 */
	public String judgeJournal(JournalVo journalVo) {
		JournalVo lastJournal = null;
		Date operationDate = null;
		if (journalVo.getId() == null) {
			lastJournal = journalMapper.selectMaxJournal(journalVo);
		} else {
			// 查询正常状态下当前ID的前一条记录
			lastJournal = journalMapper.selectPreOneJournal(journalVo);
		}
		if (lastJournal != null) {
			operationDate = lastJournal.getAccountDate();
		}
		if (operationDate != null && journalVo.getOperationDate().before(operationDate)) {
			return "不能添加往日日记账";
		}
		return "ok";
	}

	/***
	 * 根据ID查询信息
	 * 
	 * @param id
	 * @return
	 */
	public Journal selectByPrimaryKey(Integer id) {
		return journalMapper.selectByPrimaryKey(id);
	}

	/****
	 * 删除日记账信息
	 * 
	 * @param id
	 * @param currentRequest
	 * @param currentUser
	 */
	@Transactional(rollbackFor = Exception.class)
	public void deleteJournal(Integer id, String ipAddr, Integer userId) {
		// 1.将现金日记账逻辑删除
		Journal dbJournal = journalMapper.selectByPrimaryKey(id);
		Journal journal = new Journal();
		journal.setId(id);
		journal.setUpdateip(ipAddr);
		journal.setUpdateuser(userId);
		journal.setStatus(Journal.STATUS_DELETE);
		journalMapper.deleteJournal(journal);

		BigDecimal debitAmount = dbJournal.getDebitAmount() == null ? BigDecimal.ZERO : dbJournal.getDebitAmount();
		BigDecimal creditAmount = dbJournal.getCreditAmount() == null ? BigDecimal.ZERO : dbJournal.getCreditAmount();
		// 变化的差值
		BigDecimal diff = debitAmount.subtract(creditAmount);
		logger.info("差值为: " + diff);
		JournalVo journalVo = new JournalVo();
		BeanUtils.copyProperties(dbJournal, journalVo);
		// 2.更新现金日记账,更新本条记录以后所有正常状态下的结余金额
		List<Journal> list = journalMapper.selectAfterCurrentOperationDateThisDay(journalVo);

		if (list != null && !list.isEmpty()) {
			for (Journal j : list) {

				j.setBalance(j.getBalance().subtract(diff));
				j.setUpdateip(ipAddr);
				j.setUpdateuser(userId);
				journalMapper.updateByPrimaryKey(j);
			}
		}
	}

	public List<Map<String, Object>> findJournalList(JournalCnd journalCnd) {
		journalCnd.setStatus(Journal.STATUS_NOMAL);
		return journalMapper.findJournalList(journalCnd);
	}

}
