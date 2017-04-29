package com.cxdai.console.fix.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.cxdai.console.fix.entity.FixAccount;
import com.cxdai.console.fix.entity.FixBorrow;
import com.cxdai.console.fix.entity.FixCollectionrecord;
import com.cxdai.console.fix.entity.FixOperationLog;
import com.cxdai.console.fix.entity.FixRepaymentrecord;
import com.cxdai.console.fix.entity.FixTenderDetail;
import com.cxdai.console.fix.entity.FixTenderReal;
import com.cxdai.console.fix.entity.RockyAccount;
import com.cxdai.console.fix.entity.RockyAccountLog;
import com.cxdai.console.fix.mapper.FixAccountLogMapper;
import com.cxdai.console.fix.mapper.FixAccountMapper;
import com.cxdai.console.fix.mapper.FixBorrowMapper;
import com.cxdai.console.fix.mapper.FixCollectionrecordMapper;
import com.cxdai.console.fix.mapper.FixOperationLogMapper;
import com.cxdai.console.fix.mapper.FixRepaymentrecordMapper;
import com.cxdai.console.fix.mapper.FixTenderDetailMapper;
import com.cxdai.console.fix.mapper.FixTenderRealMapper;
import com.cxdai.console.fix.mapper.RockyAccountLogMapper;
import com.cxdai.console.fix.mapper.RockyAccountMapper;
import com.cxdai.console.fix.vo.FixAccountLogVo;
import com.cxdai.console.fix.vo.FixAccountVo;
import com.cxdai.console.fix.vo.FixBorrowVo;
import com.cxdai.console.fix.vo.FixCollectionrecordVo;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.util.ShiroUtils;
/**
 * 
 * <p>
 * Description:定期宝待还业务<br />
 * </p>
 * @title FixRepaymentRecordService.java
 * @package com.cxdai.console.fix.service 
 * @author yubin
 * @version 0.1 2015年8月13日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class FixRepaymentRecordService {
	public Logger logger = Logger.getLogger(FixRepaymentRecordService.class);
	private static String FIXREPAY = "定期宝还款";

	@Autowired
	private FixRepaymentrecordMapper fixRepaymentrecordMapper;
	@Autowired
	private FixBorrowMapper fixBorrowMapper;
	@Autowired
	private FixCollectionrecordMapper fixCollectionrecordMapper;
	@Autowired
	private FixAccountMapper fixAccountMapper;
	@Autowired
	private FixAccountLogMapper fixAccountLogMapper;
	@Autowired
	private RockyAccountMapper rockyAccountMapper;
	@Autowired
	private RockyAccountLogMapper rockyAccountLogMapper;
	@Autowired
	private FixOperationLogMapper fixOperationLogMapper;
	@Autowired
	private FixTenderDetailMapper fixTenderDetailMapper;
	@Autowired
	private FixTenderRealMapper fixTenderRealMapper;
	
	/**
	 * <p>
	 * Description:获取还款总额，还款可用余额，还款实际收益<br />
	 * </p>
	 * @author HuangJun
	 * @version 0.1 2015年6月28日
	 * @param fixRepaymentrecord
	 * @return
	 * @throws Exception
	 * FixRepaymentrecord
	 */
	public FixRepaymentrecord queryFixRepayByConn(FixRepaymentrecord fixRepaymentrecord) throws Exception{
		return fixRepaymentrecordMapper.queryFixRepayByConn(fixRepaymentrecord);
	}
	
	

	/**
	 * <p>
	 * Description:根据定期宝id更新定期宝待还=1(已还)<br />
	 * </p>
	 * @author HuangJun
	 * @version 0.1 2015年6月29日
	 * @param fixRepaymentrecord
	 * @return
	 * @throws Exception
	 * int
	 */
	int updateRepaymentById(FixRepaymentrecord fixRepaymentrecord) throws Exception{
		return fixRepaymentrecordMapper.updateRepaymentById(fixRepaymentrecord);
	}
	/**
	 * <p>
	 * Description:定期宝手动还款<br />
	 * 具体操作 <br />
	 * </p>
	 * @author HuangJun
	 * @version 0.1 2015年7月1日
	 * @param fixBorrowId
	 * @throws Exception
	 * void
	 */
	@Transactional(rollbackFor = Throwable.class)
	public String saveFixBorrowRepayOperation(Integer fixBorrowId, String ipAddress) throws Exception {
		String retMsg = "success";

		// 当前user
		ShiroUser staff=ShiroUtils.currentUser();
		Integer userIdOper = staff.getUserId();

		// 1.当前定期宝 id,name,锁表
		FixBorrow fixBorrowCnd = new FixBorrow();
		fixBorrowCnd.setId(fixBorrowId);
		FixBorrowVo retFixBorrowVo = fixBorrowMapper.queryFixBorrowByIdForUpdate(fixBorrowCnd);
		String fixName = retFixBorrowVo.getName();

		// 2. 获取还款总额，还款可用余额，还款实际收益
		FixRepaymentrecord fixRepaymentrecord = new FixRepaymentrecord();
		fixRepaymentrecord.setFixBorrowId(fixBorrowId);
		FixRepaymentrecord retFixRepaymentrecord = fixRepaymentrecordMapper.queryFixRepayByConn(fixRepaymentrecord);
		if (StringUtils.isEmpty(retFixRepaymentrecord)) {
			retMsg = "当前定期宝没有待还金额，定期宝id = " + fixBorrowId;
			logger.error(retMsg);
			return retMsg;
		}
		BigDecimal repayAccount = retFixRepaymentrecord.getRepaymentAccount();// 还款总额
		BigDecimal repayCapital = retFixRepaymentrecord.getCapital(); // 还款本金
		BigDecimal repayInterest = retFixRepaymentrecord.getInterest(); // 还款利息

		// 3.1 判断：用户待收金额,本金，利息的和 = 待还总额，本金,利息
		FixCollectionrecord fixCollectionrecordCnd = new FixCollectionrecord();
		fixCollectionrecordCnd.setFixBorrowId(fixBorrowId);
		FixCollectionrecordVo retFixCollectionrecordVo = fixCollectionrecordMapper.querySumCollectionAccountByFixBorrowId(fixCollectionrecordCnd);
		if (StringUtils.isEmpty(retFixCollectionrecordVo)) {
			retMsg = "当前定期宝没有待收金额，定期宝id = " + fixBorrowId;
			logger.error(retMsg);
			return retMsg;
		}
		if (retFixCollectionrecordVo.getSumRepayAccount().compareTo(repayAccount) != 0
				|| retFixCollectionrecordVo.getSumCapital().compareTo(repayCapital) != 0
				|| retFixCollectionrecordVo.getSumInterest().compareTo(repayInterest) != 0) {
			retMsg = "当前定期宝用户待收金额,本金，利息的和 != 待还总额,本金,利息，定期宝id = " + fixBorrowId;
			logger.error(retMsg);
			return retMsg;
		}

		// 3.2 判断：无债权
		FixAccount fixAccountCnd1 = new FixAccount();
		fixAccountCnd1.setFixBorrowId(fixBorrowId);
		int isDebt = fixAccountMapper.queryCountByFixBorrowId(fixAccountCnd1);
		if (isDebt == 0) {
			retMsg = "当前定期宝不符合无债权条件，定期宝id = " + fixBorrowId;
			logger.error(retMsg);
			return retMsg;
		}

		// 3.3 判断:还款日期<=now()
		FixRepaymentrecord fixRepaymentrecordCnd1 = new FixRepaymentrecord();
		fixRepaymentrecordCnd1.setFixBorrowId(fixBorrowId);
		int isRepayDate = fixRepaymentrecordMapper.queryRepaymentByFixBorrowId(fixRepaymentrecordCnd1);
		if (isRepayDate == 0) {
			retMsg = "当前定期宝不符合预计还款日期条件，定期宝id = " + fixBorrowId;
			logger.error(retMsg);
			return retMsg;
		}

		// 3.4 判断: 还款日期(含逾期还款的情况) t_fix_borrow.LOCK_ENDTIME<= now()
		FixBorrow fixBorrowCnd1 = new FixBorrow();
		fixBorrowCnd1.setId(fixBorrowId);
		int isRepayDate1 = fixBorrowMapper.queryFixBorrowCountById(fixBorrowCnd1);
		if (isRepayDate1 == 0) {
			retMsg = "当前定期宝不符合还款日期条件，定期宝id = " + fixBorrowId;
			logger.error(retMsg);
			return retMsg;
		}

		// 4. 根据定期宝ID查询定期宝账户,并锁定
		FixAccount fixAccountCnd = new FixAccount();
		fixAccountCnd.setFixBorrowId(fixBorrowId);
		FixAccountVo retFixAccount = fixAccountMapper.queryFixAccountByIdForUpdate(fixAccountCnd);
		if (StringUtils.isEmpty(retFixAccount)) {
			retMsg = "当前定期宝无账户，定期宝id = " + fixBorrowId;
			logger.error(retMsg);
			return retMsg;
		}

		// 5.判断:可用余额应该 不等于 还款本金
		if (retFixAccount.getUseMoney().compareTo(repayCapital) != 0) {
			retMsg = "当前定期宝可用余额 不等于 还款本金，定期宝id = " + fixBorrowId;
			logger.error(retMsg);
			return retMsg;
		}

		// 6. 根据定期宝ID更新定期宝账户(钱减少)
		fixAccountCnd.setTotal(retFixAccount.getTotal().subtract(repayAccount));
		fixAccountCnd.setUseMoney(retFixAccount.getUseMoney().subtract(repayCapital));
		fixAccountCnd.setProfit(retFixAccount.getProfit().subtract(repayInterest));
		fixAccountMapper.updateFixAccountById(fixAccountCnd);

		// 7. 新增:定期宝账户日志
		FixAccountLogVo fixAccountLogCnd = new FixAccountLogVo();
		fixAccountLogCnd.setFixBorrowId(fixBorrowId);
		fixAccountLogCnd.setType(8);// 8:定期宝还款
		fixAccountLogCnd.setBorrowId(fixBorrowId);
		fixAccountLogCnd.setBorrowName(fixName);
		fixAccountLogCnd.setIdType(2);// 定期宝
		fixAccountLogCnd.setMoney(repayAccount);
		// 金额
		fixAccountLogCnd.setTotal(fixAccountCnd.getTotal());
		fixAccountLogCnd.setUseMoney(fixAccountCnd.getUseMoney());
		fixAccountLogCnd.setNoUseMoney(retFixAccount.getNoUseMoney());
		fixAccountLogCnd.setCollection(retFixAccount.getCollection());
		fixAccountLogCnd.setCapital(retFixAccount.getCapital());
		fixAccountLogCnd.setProfit(fixAccountCnd.getProfit());
		// 操作人时间，ip,备注
		fixAccountLogCnd.setAddUser(userIdOper);
		fixAccountLogCnd.setAddIp(ipAddress);
		fixAccountLogCnd.setRemark(FIXREPAY);
		fixAccountLogMapper.insertFixAccountLog(fixAccountLogCnd);

		// 8. 根据定期宝id更新定期宝待还=1(已还)
		FixRepaymentrecord fixRepaymentrecordCnd = new FixRepaymentrecord();
		fixRepaymentrecordCnd.setRepaymentYesaccount(repayAccount);
		fixRepaymentrecordCnd.setFixBorrowId(fixBorrowId);
		fixRepaymentrecordCnd.setStatus(1);
		fixRepaymentrecordMapper.updateRepaymentById(fixRepaymentrecordCnd);

		// 9. 根据定期宝ID获得该用户和该用户投宝的待收金额,并锁定该表
		FixCollectionrecord fixCollectionrecordCnd2 = new FixCollectionrecord();
		fixCollectionrecordCnd2.setFixBorrowId(fixBorrowId);
		List<FixCollectionrecordVo> retFixCollectionrecordLst = fixCollectionrecordMapper.queryCollectionByConn(fixCollectionrecordCnd2);

		// 10. 定期宝循环还款给投资人
		for (FixCollectionrecord fixCollectionrecord : retFixCollectionrecordLst) {// 所有投资人待收金额for_start
			Integer userId = fixCollectionrecord.getUserId();
			BigDecimal repayUserAccount = fixCollectionrecord.getRepayAccount();

			// 11. 根据userId查询账户表rocky_account并锁定
			RockyAccount rockyAccountCnd = new RockyAccount();
			rockyAccountCnd.setUserId(userId);
			RockyAccount rockyAccountVo = rockyAccountMapper.queryRockyAccountByUserIdForUpdate(rockyAccountCnd);

			// 12.根据userId更新账户表 rocky_account 的金额(钱增加,总额，可用，可提)
			rockyAccountCnd.setTotal(rockyAccountVo.getTotal().add(repayUserAccount));
			rockyAccountCnd.setUseMoney(rockyAccountVo.getUseMoney().add(repayUserAccount));
			rockyAccountCnd.setDrawMoney(rockyAccountVo.getDrawMoney().add(repayUserAccount));
			rockyAccountMapper.updateRockyAccountByUserId(rockyAccountCnd);

			// 13. 新增:普通账户日志rocky_accountlog
			RockyAccountLog rockyAccountLog = new RockyAccountLog();
			rockyAccountLog.setUserId(userId);
			rockyAccountLog.setType("fix_repayment");
			rockyAccountLog.setTotal(rockyAccountCnd.getTotal());
			rockyAccountLog.setMoney(repayUserAccount);
			rockyAccountLog.setUseMoney(rockyAccountCnd.getUseMoney());
			rockyAccountLog.setNoUseMoney(rockyAccountVo.getNoUseMoney());
			rockyAccountLog.setCollection(rockyAccountVo.getCollection());
			rockyAccountLog.setToUser(fixBorrowId);
			rockyAccountLog.setBorrowId(fixBorrowId);
			rockyAccountLog.setBorrowName(fixName);
			rockyAccountLog.setIdType(6);// 6:定期宝
			rockyAccountLog.setRemark(FIXREPAY);
			rockyAccountLog.setAddip(ipAddress);
			rockyAccountLog.setFirstBorrowUseMoney(rockyAccountVo.getFirstBorrowUseMoney());
			rockyAccountLog.setDrawMoney(rockyAccountCnd.getDrawMoney());
			rockyAccountLog.setNoDrawMoney(rockyAccountVo.getNoDrawMoney());
			rockyAccountLogMapper.insertRockyAccountLog(rockyAccountLog);

			// 14. 根据【定期宝Id】【userId】更新定期宝投资人待收记录
			FixCollectionrecord fixCollectionrecordCnd1 = new FixCollectionrecord();
			fixCollectionrecordCnd1.setRepayYesaccount(repayUserAccount);
			fixCollectionrecordCnd1.setStatus(1);// 1:已还款
			fixCollectionrecordCnd1.setFixBorrowId(fixBorrowId);
			fixCollectionrecordCnd1.setUserId(userId);
			fixCollectionrecordMapper.updateFixCollectionRecordByFixId(fixCollectionrecordCnd1);

		}// 所有投资人待收金额for_end

		// 15. 更新：定期宝信息表t_fix_borrow.STATUS = 7(还款结束),先锁表
		FixBorrow fixBorrowRepayCnd = new FixBorrow();
		fixBorrowRepayCnd.setId(fixBorrowId);
		fixBorrowRepayCnd.setStatus(7);// 7(还款结束)
		fixBorrowRepayCnd.setLastModifyUser(userIdOper);
		fixBorrowMapper.updateFixBorrowById(fixBorrowRepayCnd);

		// 16. 新增：定期宝操作日志 t_fix_operation_log
		FixOperationLog fixOperationLogCnd = new FixOperationLog();
		fixOperationLogCnd.setUserId(userIdOper);
		fixOperationLogCnd.setUserType(2);// 2:后台
		fixOperationLogCnd.setFixBorrowId(fixBorrowId);
		fixOperationLogCnd.setOperType(11); // 11已还款
		fixOperationLogCnd.setAddip(ipAddress);
		fixOperationLogCnd.setRemark(FIXREPAY);
		fixOperationLogCnd.setPlatform(1);// 1网页
		fixOperationLogMapper.insertFixOperationLog(fixOperationLogCnd);

		// 17. 根据定期宝ID，更新定期宝认购明细t_fix_tender_detail.STATUS = 3(已还款)
		FixTenderDetail fixTenderDetailCnd = new FixTenderDetail();
		fixTenderDetailCnd.setFixBorrowId(fixBorrowId);
		fixTenderDetailCnd.setStatus(3);// 3(已还款)
		fixTenderDetailMapper.updateFixFenderDetailByConn(fixTenderDetailCnd);

		// 18. 根据定期宝ID，更新 定期宝最终认购记录表t_fix_tender_real.status = 1(还款结束)
		FixTenderReal fixTenderRealCnd = new FixTenderReal();
		fixTenderRealCnd.setFixBorrowId(fixBorrowId);
		fixTenderRealCnd.setStatus(1);
		fixTenderRealMapper.updateFixTenderRealByFixId(fixTenderRealCnd);
		return retMsg;
	}
}
