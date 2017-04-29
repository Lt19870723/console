package com.cxdai.console.customer.bankcard.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.bankcard.constant.UserBindBankConstant;
import com.cxdai.console.customer.bankcard.entity.Bank;
import com.cxdai.console.customer.bankcard.entity.BankinfoLog;
import com.cxdai.console.customer.bankcard.mapper.BankinfoLogMapper;
import com.cxdai.console.customer.bankcard.mapper.UserBindBankMapper;
import com.cxdai.console.customer.bankcard.vo.BankCardLockCnd;
import com.cxdai.console.customer.bankcard.vo.BankCardLockVo;
import com.cxdai.console.customer.bankcard.vo.BankInfoVo;
import com.cxdai.console.customer.bankcard.vo.UserBindBankCnd;
import com.cxdai.console.customer.information.service.MemberService;
import com.cxdai.console.customer.information.service.RealNameApproService;
import com.cxdai.console.customer.information.vo.RealNameApproVo;

@Service
@Transactional(rollbackFor = Throwable.class)
public class UserBindBankService {

	@Autowired
	UserBindBankMapper userBindBankMapper;

	@Autowired
	MemberService memberService;

	@Autowired
	BankinfoLogMapper bankinfoLogMapper;

	@Autowired
	RealNameApproService realNameApproService;

	@Transactional(rollbackFor = Throwable.class)
	public void saveBank(BankInfoVo bankInfoVo) {

		Integer cardCount = vlidateAddBank(bankInfoVo);

		userBindBankMapper.insertEntity(bankInfoVo);

		insertBankinfoLog(bankInfoVo, UserBindBankConstant.BANKINFO_LOG_ADD, "新增银行卡");

		if (cardCount.intValue() == UserBindBankConstant.BANK_CARD_MAX_COUNT - 1) {
			// 新增锁定记录
			bankInfoVo.setCardNum("0");
			insertBankinfoLog(bankInfoVo, UserBindBankConstant.BANKINFO_LOG_LOCK, "系统锁定");
		}

	}

	@Transactional(rollbackFor = Throwable.class)
	private void insertBankinfoLog(BankInfoVo bankInfoVo, int bankinfoLogAdd, String remark) {
		BankinfoLog bankinfoLog = new BankinfoLog();
		bankinfoLog.setUserId(bankInfoVo.getUserId());
		bankinfoLog.setCardNum(bankInfoVo.getCardNum());
		bankinfoLog.setType(bankinfoLogAdd);
		bankinfoLog.setStatus(0);
		bankinfoLog.setAddBy(bankInfoVo.getAddBy());
		bankinfoLog.setAddTime(new Date());
		bankinfoLog.setRemark(remark);
		bankinfoLogMapper.insert(bankinfoLog);
	}

	@Transactional(rollbackFor = Throwable.class)
	public void deleteBank(BankInfoVo bankInfoVo) {
		userBindBankMapper.deleteBankinfoById(bankInfoVo.getId());
		insertBankinfoLog(bankInfoVo, UserBindBankConstant.BANKINFO_LOG_DELETE, "删除银行卡");

	}

	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public List<BankInfoVo> queryBanksByCnd(UserBindBankCnd bindBankCnd) {
		return userBindBankMapper.queryBanksByCnd(bindBankCnd);
	}

	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public List<Bank> queryProvinceList() {
		return userBindBankMapper.queryProvinceList();
	}

	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public List<Bank> queryCityList(String province) {
		return userBindBankMapper.queryCityList(province);
	}

	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public List<Bank> queryDistrictList(String city) {
		return userBindBankMapper.queryDistrictList(city);
	}

	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public List<Bank> queryBankList(String district) {
		return userBindBankMapper.queryBankList(district);
	}

	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public List<Bank> queryBranchList(String district, String bankCode, String branchKey) {
		if (branchKey != null) {
			branchKey = branchKey.trim();
		}
		return userBindBankMapper.queryBranchListLike(district, bankCode, branchKey);
	}

	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public List<Bank> queryBankInfosByCnapsCode(Long cnapsCode) {
		// 行号查询多个支行
		return userBindBankMapper.queryBankInfosByCnapsCode(cnapsCode);

	}

	public Integer vlidateAddBank(BankInfoVo bankInfoVo) {
		String userName = bankInfoVo.getUserName();
		Integer userId = memberService.getMemberIdByUserName(userName);

		if (userId == null) {
			throw new RuntimeException("用户名不存在！");
		}
		bankInfoVo.setUserId(userId);

		// 检查实名认证
		RealNameApproVo rna = realNameApproService.getRealNameApproByUserName(userName);
		if (null == rna || rna.getIsPassed() != UserBindBankConstant.REALNAME_APPR_ISPASSED_PASSED) {
			throw new RuntimeException("您还未通过实名认证，无法设置银行卡信息。");
		}

		String cardNum = bankInfoVo.getCardNum();
		if (!StringUtils.isEmpty(cardNum)) {
			if (userBindBankMapper.countCardByCardNum(cardNum) > 0) {
				throw new RuntimeException("卡号已存在！");
			}
		}

		Integer cardCount = userBindBankMapper.queryCardCountByUserId(userId);

		if (cardCount.intValue() >= UserBindBankConstant.BANK_CARD_MAX_COUNT) {
			throw new RuntimeException("银行卡数目已上限，不能添加");
		}

		return cardCount;

	}
	
	/**
	 * 
	 * <p>
	 * Description:注销用户所有银行卡<br />
	 * </p>
	 * @author 胡建盼
	 * @version 0.1 2014年12月5日
	 * @param memberClearVo
	 * @throws Exception
	 * void
	 */
	@Transactional(rollbackFor = Throwable.class)
	public void cancelUserBankCard(Integer userId)
			throws Exception {
		bankinfoLogMapper.batchInsert(BankinfoLog.builder(userId,UserBindBankConstant.BANKINFO_LOG_DELETE, "用户注销，删除银行卡"));
		userBindBankMapper.deleteBankinfoByUserId(userId);
	}

	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public Page queryBankCardLockListByCnd(BankCardLockCnd bankCardLockCnd, int pageNo, int pageSize) {
		Page page = new Page(pageNo, pageSize);
		page.setResult(bankinfoLogMapper.queryPageAllBankCardLockListByCnd(bankCardLockCnd, page));
		page.setTotalCount(bankinfoLogMapper.queryCountPageAllBankCardLockListByCnd(bankCardLockCnd));
		return page;
	}

	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public Page queryWxBindListByCnd(BankCardLockCnd bankCardLockCnd, int pageNo, int pageSize) {
		Page page = new Page(pageNo, pageSize);
		page.setResult(bankinfoLogMapper.queryPageAllWxBindListByCnd(bankCardLockCnd, page));
		page.setTotalCount(bankinfoLogMapper.queryCountPageAllWxBindListByCnd(bankCardLockCnd));
		return page;
	}

	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public int queryBankCardLockAcount() {
		return bankinfoLogMapper.queryBankCardLockAcount();
	}

	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public int queryWxBindAcount() {
		return bankinfoLogMapper.queryWxBindAcount();
	}
	public int queryWxBindAcountByTime(BankCardLockCnd bankCardLockCnd) {
		return bankinfoLogMapper.queryWxBindAcountByTime(bankCardLockCnd);
	}
	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public List<BankCardLockVo> exportToExcel(BankCardLockCnd bankCardLockCnd, HttpServletRequest request, HttpServletResponse response) {
		return bankinfoLogMapper.queryBankCardLockListByCnd(bankCardLockCnd);
	}

	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public List<BankCardLockVo> wxExportToExcel(BankCardLockCnd bankCardLockCnd, HttpServletRequest request, HttpServletResponse response) {
		return bankinfoLogMapper.queryPageAllWxBindListByCnd(bankCardLockCnd);
	}


    /**
     * 
     * <p>
     * Description:分页查询用户银行卡操作日志流水<br />
     * </p>
     * @author 胡建盼
     * @version 0.1 2014年12月10日
     * @param userBindBankCnd
     * @return
     * List<BankinfoLog>
     */
	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public Page queryUserBanksCardOperateLog(
			UserBindBankCnd bindBankCnd,Page page) {
		
		page.setTotalCount(bankinfoLogMapper.countUserBanksCardOperateLog(bindBankCnd));
		page.setResult(bankinfoLogMapper.queryUserBanksCardOperateLog(bindBankCnd,page));
		return page;
	}

}
