package com.cxdai.console.statistics.reconciliation.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.statistics.firstborrow.entity.NewThroughTrainInformationStatisticsVO;
import com.cxdai.console.statistics.reconciliation.entity.AccountCheckCnd;
import com.cxdai.console.statistics.reconciliation.mapper.AccountCheckMapper;
import com.cxdai.console.statistics.reconciliation.vo.RealtimeUserAccountVO;
import com.cxdai.console.statistics.reconciliation.vo.RepayEarlyCheckVO;
import com.cxdai.console.util.DateUtils;

/**
 * <p>
 * Description:资金对账数据业务实现类<br />
 * </p>
 * 
 * @title AccountCheckServiceImpl.java
 * @package com.cxdai.console.account.service.impl
 * @author justin.xu
 * @version 0.1 2014年10月14日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class AccountCheckService{

	@Autowired
	private AccountCheckMapper accountCheckMapper;

	
	public Map<String, Object> queryTopupCheckByCnd(AccountCheckCnd accountCheckCnd) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (null != accountCheckCnd.getBeginTime()) {
			accountCheckCnd.setBeginTime(DateUtils.convert2StartDate(accountCheckCnd.getBeginTime()));
			accountCheckCnd.setBeginTimeStr(accountCheckCnd.getBeginTime().getTime() / 1000 + "");
		}
		if (null != accountCheckCnd.getEndTime()) {
			accountCheckCnd.setEndTime(DateUtils.convert2EndDate(accountCheckCnd.getEndTime()));
			accountCheckCnd.setEndTimeStr(accountCheckCnd.getEndTime().getTime() / 1000 + "");
		}
		// 根据查询条件统计线上充值总额
		BigDecimal onlineRechargeMoney = this.queryOnlineRechargeMoneyByCnd(accountCheckCnd);
		resultMap.put("onlineRechargeMoney", onlineRechargeMoney);
		// 根据查询条件统计线下充值总额
		BigDecimal offlineRechargeMoney = this.queryOfflineRechargeMoneyByCnd(accountCheckCnd);
		resultMap.put("offlineRechargeMoney", offlineRechargeMoney);
		resultMap.put("rechargeMoney", onlineRechargeMoney.add(offlineRechargeMoney));
		// 根据查询条件统计网站充值入账总额
		BigDecimal webRechargeMoney = this.queryWebRechargeMoneyByCnd(accountCheckCnd);
		resultMap.put("webRechargeMoney", webRechargeMoney);

		resultMap.put("compareResult", "false");
		// 比较网站的充值记录总额（线上线下充值总额）、网站充值入账总额是否一致
		if ((onlineRechargeMoney.add(offlineRechargeMoney)).compareTo(webRechargeMoney) == 0) {
			resultMap.put("compareResult", "true");
		}
		return resultMap;
	}

	
	public Map<String, Object> queryTenderCheckByCnd(AccountCheckCnd accountCheckCnd) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (null != accountCheckCnd.getBeginTime()) {
			accountCheckCnd.setBeginTime(DateUtils.convert2StartDate(accountCheckCnd.getBeginTime()));
			accountCheckCnd.setBeginTimeStr(accountCheckCnd.getBeginTime().getTime() / 1000 + "");
		}
		if (null != accountCheckCnd.getEndTime()) {
			accountCheckCnd.setEndTime(DateUtils.convert2EndDate(accountCheckCnd.getEndTime()));
			accountCheckCnd.setEndTimeStr(accountCheckCnd.getEndTime().getTime() / 1000 + "");
		}
		// 根据查询条件统计网站所有投资者的投标支出
		BigDecimal investorTenderMoney = this.queryInvestorTenderMoneyByCnd(accountCheckCnd);
		resultMap.put("investorTenderMoney", investorTenderMoney);
		// 根据查询条件统计网站所有借款者的借款入账金额
		BigDecimal borrowerSuccuessMoney = this.queryBorrowerSuccuessMoneyByCnd(accountCheckCnd);
		resultMap.put("borrowerSuccuessMoney", borrowerSuccuessMoney);
		// 根据查询条件统计网站所有借款标的借款总额
		BigDecimal borrowMoney = this.queryBorrowMoneyByCnd(accountCheckCnd);
		resultMap.put("borrowMoney", borrowMoney);

		resultMap.put("compareResult", "false");
		// 比较网站所有投资者的投标支出、网站所有借款者的借款入账金额、网站所有借款标的借款总额是否一致
		if (investorTenderMoney.compareTo(borrowerSuccuessMoney) == 0 && investorTenderMoney.compareTo(borrowMoney) == 0) {
			resultMap.put("compareResult", "true");
		}

		return resultMap;
	}

	
	public Map<String, Object> queryRepaymentCheckByCnd(AccountCheckCnd accountCheckCnd) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (null != accountCheckCnd.getBeginTime()) {
			accountCheckCnd.setBeginTime(DateUtils.convert2StartDate(accountCheckCnd.getBeginTime()));
			accountCheckCnd.setBeginTimeStr(accountCheckCnd.getBeginTime().getTime() / 1000 + "");
		}
		if (null != accountCheckCnd.getEndTime()) {
			accountCheckCnd.setEndTime(DateUtils.convert2EndDate(accountCheckCnd.getEndTime()));
			accountCheckCnd.setEndTimeStr(accountCheckCnd.getEndTime().getTime() / 1000 + "");
		}
		// 提前还款对账
		RepayEarlyCheckVO repayEarlyCheckVO = accountCheckMapper.queryRepayEarlyCheckByCnd(accountCheckCnd);
		resultMap.put("repayEarlyCheckVO", repayEarlyCheckVO);

		// 网站所有投资者的回款入账
		BigDecimal repaymentBackMoney = this.queryRepaymentBackMoneyByCnd(accountCheckCnd);
		// 扣除提前还款
		repaymentBackMoney = repaymentBackMoney.subtract(repayEarlyCheckVO.getSubtractInterestTotal());
		resultMap.put("repaymentBackMoney", repaymentBackMoney);
		// 根据查询条件统计回款的小账户进出资金
		BigDecimal webSiteMoney = this.queryWebSiteMoneyByCnd(accountCheckCnd);
		webSiteMoney = webSiteMoney.subtract(repayEarlyCheckVO.getWebsiteEarlyTotal());
		resultMap.put("webSiteMoney", webSiteMoney);
		BigDecimal allRepaymentMoney = repaymentBackMoney.add(webSiteMoney);
		// 根据查询条件统计网站所有借款者的还款支出
		BigDecimal borrowerPayMoney = this.queryBorrowerPayMoneyByCnd(accountCheckCnd);
		// 根据查询条件统计网站应还总额
		BigDecimal webMustPayMoney = this.queryWebMustPayMoneyByCnd(accountCheckCnd);

		// 合并验证公式 1-4-5 = 2-7 = 3-8 参见 BUG #3766
		borrowerPayMoney = borrowerPayMoney.subtract(repayEarlyCheckVO.getAddInterestTotal());
		webMustPayMoney = webMustPayMoney.subtract(repayEarlyCheckVO.getRepaymentAddTotal());
		resultMap.put("allRepaymentMoney", allRepaymentMoney);
		resultMap.put("borrowerPayMoney", borrowerPayMoney);
		resultMap.put("webMustPayMoney", webMustPayMoney);

		resultMap.put("compareResult", "false");
		// 比较回款入账+小账户、借款者的还款支出、网站应还总额是否一致
		if (allRepaymentMoney.compareTo(borrowerPayMoney) == 0 && allRepaymentMoney.compareTo(webMustPayMoney) == 0) {
			resultMap.put("compareResult", "true");
		}

		// 垫付对帐 网站当天垫付总额、网站当天垫付入帐总额、网站当天所有垫付回款入帐总额，判断这三个值是否相等（可容许偏差几分钱）
		// 网站当天垫付总额=待还表中(垫付时间为查询时间、垫付状态为已垫付）的所有垫付总额；
		// BigDecimal webPayRepaymentMoney =
		// this.queryWebPayRepaymentMoneyByCnd(accountCheckCnd);
		// resultMap.put("webPayRepaymentMoney", webPayRepaymentMoney);
		// 网站当天垫付入帐总额= 待收表中（垫付时间为查询时间）的所有垫付总额
		// BigDecimal webPayCollectionMoney =
		// this.queryWebPayCollectionMoneyByCnd(accountCheckCnd);
		// resultMap.put("webPayCollectionMoney", webPayCollectionMoney);
		// 网站当天所有垫付回款入帐总额=资金明细表中时间为查询时间且与待还表的垫付时间相差不差过2分钟，类型为还款入帐（借款标为待还表中垫付时间为查询时间、垫付状态为已垫付的借款标）的操作金额之和。
		// BigDecimal webPayRepaymentBackMoney =
		// this.queryWebPayRepaymentBackMoneyByCnd(accountCheckCnd);
		// resultMap.put("webPayRepaymentBackMoney", webPayRepaymentBackMoney);

		// 垫付后还款对帐
		// 借款者垫付后还款总额=资金明细表中类型为垫付后还款的操作金额之和
		// BigDecimal afterWebPayMoney =
		// this.queryAfterWebPayMoneyByCnd(accountCheckCnd);
		// resultMap.put("afterWebPayMoney", afterWebPayMoney);
		// 借款者实际垫付还款总额 = 待还表中实际还款金额之和
		// BigDecimal afterWebPayRepaymentMoney =
		// this.queryAfterWebPayRepaymentMoneyByCnd(accountCheckCnd);
		// resultMap.put("afterWebPayRepaymentMoney",
		// afterWebPayRepaymentMoney);
		// 投资者应收垫付后入帐总额 = 待收表中状态为垫付后还款的实际收款金额之和 + 非VIP非当天垫付后还款的应收利息之和
		// 待收表中状态为垫付后还款的实际收款金额之和
		// BigDecimal afterWebPayCollectionMoney =
		// this.queryAfterWebPayCollectionMoneyByCnd(accountCheckCnd);
		// resultMap.put("afterWebPayCollectionMoney",
		// afterWebPayCollectionMoney);
		// 非VIP非当天垫付后还款的应收利息之和
		// BigDecimal afterWebPayNoVipInterestMoney =
		// this.queryAfterWebPayNoVipInterestMoneyByCnd(accountCheckCnd);
		// resultMap.put("afterWebPayNoVipInterestMoney",
		// afterWebPayNoVipInterestMoney);
		// 根据查询条件统计垫付后还款小帐号
		// BigDecimal afterWebPaySiteMoney =
		// this.queryAfterWebPaySiteMoneyByCnd(accountCheckCnd);
		// resultMap.put("afterWebPaySiteMoney", afterWebPaySiteMoney);
		// resultMap.put("afterWebPayInvestorCollectionMoney",
		// afterWebPayCollectionMoney.add(afterWebPayNoVipInterestMoney).add(afterWebPaySiteMoney));

		return resultMap;
	}

	
	public Map<String, Object> queryEarlyRepaymentCheckByCnd(AccountCheckCnd accountCheckCnd) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (null != accountCheckCnd.getBeginTime()) {
			accountCheckCnd.setBeginTime(DateUtils.convert2StartDate(accountCheckCnd.getBeginTime()));
			accountCheckCnd.setBeginTimeStr(accountCheckCnd.getBeginTime().getTime() / 1000 + "");
		}
		if (null != accountCheckCnd.getEndTime()) {
			accountCheckCnd.setEndTime(DateUtils.convert2EndDate(accountCheckCnd.getEndTime()));
			accountCheckCnd.setEndTimeStr(accountCheckCnd.getEndTime().getTime() / 1000 + "");
		}
		// 提前还款对账
		RepayEarlyCheckVO repayEarlyCheckVO = accountCheckMapper.queryRepayEarlyCheckByCnd(accountCheckCnd);
		resultMap.put("repayEarlyCheckVO", repayEarlyCheckVO);
		return resultMap;
	}

	
	public Map<String, Object> queryTakeCashCheckByCnd(AccountCheckCnd accountCheckCnd) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (null != accountCheckCnd.getBeginTime()) {
			accountCheckCnd.setBeginTime(DateUtils.convert2StartDate(accountCheckCnd.getBeginTime()));
			accountCheckCnd.setBeginTimeStr(accountCheckCnd.getBeginTime().getTime() / 1000 + "");
		}
		if (null != accountCheckCnd.getEndTime()) {
			accountCheckCnd.setEndTime(DateUtils.convert2EndDate(accountCheckCnd.getEndTime()));
			accountCheckCnd.setEndTimeStr(accountCheckCnd.getEndTime().getTime() / 1000 + "");
		}
		// 根据查询条件统计网站的提现记录总额
		BigDecimal paymentSuccessMoney = this.queryPaymentSuccessMoneyByCnd(accountCheckCnd);
		resultMap.put("paymentSuccessMoney", paymentSuccessMoney);
		// 根据查询条件统计网站的提现作废总额
		BigDecimal paymentFailedMoney = this.queryPaymentFailedMoneyByCnd(accountCheckCnd);
		resultMap.put("paymentFailedMoney", paymentFailedMoney);
		// 根据条件统计网站的实现提现作废总额
		BigDecimal actualFailedMoney = this.queryPaymentActualFailedMoneyByCnd(accountCheckCnd);
		resultMap.put("actualFailedMoney", actualFailedMoney);
		// 净提现总额 = 提现记录总额-提现作废总额+实现提现作废总额
		BigDecimal paymentRealSuccessMoney = paymentSuccessMoney.subtract(paymentFailedMoney).add(actualFailedMoney);
		resultMap.put("paymentRealSuccessMoney", paymentRealSuccessMoney);
		// 根据查询条件统计网站提现支出总额（包括作废金额）
		BigDecimal takeCashMoney = this.queryTakeCashMoneyByCnd(accountCheckCnd);
		resultMap.put("takeCashMoney", takeCashMoney);
		// 根据查询条件统计公司实际打款总额
		BigDecimal actualCashMoney = this.queryActualCashMoneyByCnd(accountCheckCnd);
		resultMap.put("actualCashMoney", actualCashMoney);

		resultMap.put("compareResult", "false");
		// 比较：净提现总额、提现支出总额、实际打款总额三者是否一致
		if (paymentRealSuccessMoney.compareTo(takeCashMoney) == 0 && paymentRealSuccessMoney.compareTo(actualCashMoney) == 0) {
			resultMap.put("compareResult", "true");
		}
		return resultMap;
	}

	/**
	 * <p>
	 * Description:根据查询条件统计线上充值总额<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年10月14日
	 * @param accountCheckCnd
	 * @return
	 * @throws Exception BigDecimal
	 */
	private BigDecimal queryOnlineRechargeMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception {
		BigDecimal result = accountCheckMapper.queryOnlineRechargeMoneyByCnd(accountCheckCnd);
		if (null != result) {
			return result;
		}
		return BigDecimal.ZERO;
	}

	/**
	 * <p>
	 * Description:根据查询条件统计线下充值总额<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年10月14日
	 * @param accountCheckCnd
	 * @return
	 * @throws Exception BigDecimal
	 */
	private BigDecimal queryOfflineRechargeMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception {
		BigDecimal result = accountCheckMapper.queryOfflineRechargeMoneyByCnd(accountCheckCnd);
		if (null != result) {
			return result;
		}
		return BigDecimal.ZERO;
	}

	/**
	 * <p>
	 * Description:根据查询条件统计网站充值入账总额<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年10月14日
	 * @param accountCheckCnd
	 * @return
	 * @throws Exception BigDecimal
	 */
	private BigDecimal queryWebRechargeMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception {
		BigDecimal result = accountCheckMapper.queryWebRechargeMoneyByCnd(accountCheckCnd);
		if (null != result) {
			return result;
		}
		return BigDecimal.ZERO;
	}

	/**
	 * <p>
	 * Description:根据查询条件统计网站所有投资者的投标支出<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年10月14日
	 * @param accountCheckCnd
	 * @return
	 * @throws Exception BigDecimal
	 */
	private BigDecimal queryInvestorTenderMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception {
		BigDecimal result = accountCheckMapper.queryInvestorTenderMoneyByCnd(accountCheckCnd);
		if (null != result) {
			return result;
		}
		return BigDecimal.ZERO;
	}

	/**
	 * <p>
	 * Description:根据查询条件统计网站所有借款者的借款入账金额<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年10月14日
	 * @param accountCheckCnd
	 * @return
	 * @throws Exception BigDecimal
	 */
	private BigDecimal queryBorrowerSuccuessMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception {
		BigDecimal result = accountCheckMapper.queryBorrowerSuccuessMoneyByCnd(accountCheckCnd);
		if (null != result) {
			return result;
		}
		return BigDecimal.ZERO;
	}

	/**
	 * <p>
	 * Description:根据查询条件统计网站所有借款标的借款总额<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年10月14日
	 * @param accountCheckCnd
	 * @return
	 * @throws Exception BigDecimal
	 */
	private BigDecimal queryBorrowMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception {
		BigDecimal result = accountCheckMapper.queryBorrowMoneyByCnd(accountCheckCnd);
		if (null != result) {
			return result;
		}
		return BigDecimal.ZERO;
	}

	/**
	 * <p>
	 * Description:根据查询条件统计网站所有投资者的回款入账<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年10月14日
	 * @param accountCheckCnd
	 * @return
	 * @throws Exception BigDecimal
	 */
	private BigDecimal queryRepaymentBackMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception {
		BigDecimal result = accountCheckMapper.queryRepaymentBackMoneyByCnd(accountCheckCnd);
		if (null != result) {
			return result;
		}
		return BigDecimal.ZERO;
	}

	/**
	 * <p>
	 * Description:根据查询条件统计回款的小账户进出资金<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年10月14日
	 * @param accountCheckCnd
	 * @return
	 * @throws Exception BigDecimal
	 */
	private BigDecimal queryWebSiteMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception {
		BigDecimal result = accountCheckMapper.queryWebSiteMoneyByCnd(accountCheckCnd);
		if (null != result) {
			return result;
		}
		return BigDecimal.ZERO;
	}

	/**
	 * <p>
	 * Description:根据查询条件统计网站所有借款者的还款支出<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年10月14日
	 * @param accountCheckCnd
	 * @return
	 * @throws Exception BigDecimal
	 */
	private BigDecimal queryBorrowerPayMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception {
		BigDecimal result = accountCheckMapper.queryBorrowerPayMoneyByCnd(accountCheckCnd);
		if (null != result) {
			return result;
		}
		return BigDecimal.ZERO;
	}

	/**
	 * <p>
	 * Description:根据查询条件统计网站应还总额<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年10月14日
	 * @param accountCheckCnd
	 * @return
	 * @throws Exception BigDecimal
	 */
	private BigDecimal queryWebMustPayMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception {
		BigDecimal result = accountCheckMapper.queryWebMustPayMoneyByCnd(accountCheckCnd);
		if (null != result) {
			return result;
		}
		return BigDecimal.ZERO;
	}

	/**
	 * <p>
	 * Description:根据查询条件统计网站的提现记录总额<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年10月14日
	 * @param accountCheckCnd
	 * @return
	 * @throws Exception BigDecimal
	 */
	private BigDecimal queryPaymentSuccessMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception {
		BigDecimal result = accountCheckMapper.queryPaymentSuccessMoneyByCnd(accountCheckCnd);
		if (null != result) {
			return result;
		}
		return BigDecimal.ZERO;
	}

	/**
	 * <p>
	 * Description:根据查询条件统计网站的提现作废总额<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年10月14日
	 * @param accountCheckCnd
	 * @return
	 * @throws Exception BigDecimal
	 */
	private BigDecimal queryPaymentFailedMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception {
		BigDecimal result = accountCheckMapper.queryPaymentFailedMoneyByCnd(accountCheckCnd);
		if (null != result) {
			return result;
		}
		return BigDecimal.ZERO;
	}

	/**
	 * <p>
	 * Description:根据查询条件统计网站的实际提现作废总额<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年10月14日
	 * @param accountCheckCnd
	 * @return
	 * @throws Exception BigDecimal
	 */
	private BigDecimal queryPaymentActualFailedMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception {
		BigDecimal result = accountCheckMapper.queryPaymentActualFailedMoneyByCnd(accountCheckCnd);
		if (null != result) {
			return result;
		}
		return BigDecimal.ZERO;
	}

	/**
	 * <p>
	 * Description:根据查询条件统计网站提现支出总额<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年10月14日
	 * @param accountCheckCnd
	 * @return
	 * @throws Exception BigDecimal
	 */
	private BigDecimal queryTakeCashMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception {
		BigDecimal result = accountCheckMapper.queryTakeCashMoneyByCnd(accountCheckCnd);
		if (null != result) {
			return result;
		}
		return BigDecimal.ZERO;
	}

	/**
	 * <p>
	 * Description:根据查询条件统计公司实际打款总额（包括作废金额）<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年10月14日
	 * @param accountCheckCnd
	 * @return
	 * @throws Exception BigDecimal
	 */
	private BigDecimal queryActualCashMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception {
		BigDecimal result = accountCheckMapper.queryActualCashMoneyByCnd(accountCheckCnd);
		if (null != result) {
			return result;
		}
		return BigDecimal.ZERO;
	}

	/**
	 * <p>
	 * Description:根据查询条件统计网站当天垫付总额<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年11月30日
	 * @param accountCheckCnd
	 * @return
	 * @throws Exception BigDecimal
	 */
	private BigDecimal queryWebPayRepaymentMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception {
		BigDecimal result = accountCheckMapper.queryWebPayRepaymentMoneyByCnd(accountCheckCnd);
		if (null != result) {
			return result;
		}
		return BigDecimal.ZERO;
	}

	/**
	 * <p>
	 * Description:根据查询条件网站垫付入帐总额<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年11月30日
	 * @param accountCheckCnd
	 * @return
	 * @throws Exception BigDecimal
	 */
	private BigDecimal queryWebPayCollectionMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception {
		BigDecimal result = accountCheckMapper.queryWebPayCollectionMoneyByCnd(accountCheckCnd);
		if (null != result) {
			return result;
		}
		return BigDecimal.ZERO;
	}

	/**
	 * <p>
	 * Description:根据查询网站当天所有垫付回款入帐总额<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年11月30日
	 * @param accountCheckCnd
	 * @return
	 * @throws Exception BigDecimal
	 */
	private BigDecimal queryWebPayRepaymentBackMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception {
		BigDecimal result = accountCheckMapper.queryWebPayRepaymentBackMoneyByCnd(accountCheckCnd);
		if (null != result) {
			return result;
		}
		return BigDecimal.ZERO;
	}

	/**
	 * <p>
	 * Description:根据查询借款者垫付后还款总额<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年11月30日
	 * @param accountCheckCnd
	 * @return
	 * @throws Exception BigDecimal
	 */
	private BigDecimal queryAfterWebPayMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception {
		BigDecimal result = accountCheckMapper.queryAfterWebPayMoneyByCnd(accountCheckCnd);
		if (null != result) {
			return result;
		}
		return BigDecimal.ZERO;
	}

	/**
	 * <p>
	 * Description:根据查询借款者实际垫付还款总额<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年11月30日
	 * @param accountCheckCnd
	 * @return
	 * @throws Exception BigDecimal
	 */
	private BigDecimal queryAfterWebPayRepaymentMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception {
		BigDecimal result = accountCheckMapper.queryAfterWebPayRepaymentMoneyByCnd(accountCheckCnd);
		if (null != result) {
			return result;
		}
		return BigDecimal.ZERO;
	}

	/**
	 * <p>
	 * Description:根据查询待收表中状态为垫付后还款的实际收款金额之和<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年11月30日
	 * @param accountCheckCnd
	 * @return
	 * @throws Exception BigDecimal
	 */
	private BigDecimal queryAfterWebPayCollectionMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception {
		BigDecimal result = accountCheckMapper.queryAfterWebPayCollectionMoneyByCnd(accountCheckCnd);
		if (null != result) {
			return result;
		}
		return BigDecimal.ZERO;
	}

	/**
	 * <p>
	 * Description:根据查询非VIP非当天垫付后还款的应收利息之和<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年11月30日
	 * @param accountCheckCnd
	 * @return
	 * @throws Exception BigDecimal
	 */
	private BigDecimal queryAfterWebPayNoVipInterestMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception {
		BigDecimal result = accountCheckMapper.queryAfterWebPayNoVipInterestMoneyByCnd(accountCheckCnd);
		if (null != result) {
			return result;
		}
		return BigDecimal.ZERO;
	}

	/**
	 * <p>
	 * Description:根据查询条件统计垫付后还款小帐号<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年10月14日
	 * @param accountCheckCnd
	 * @return
	 * @throws Exception BigDecimal
	 */
	private BigDecimal queryAfterWebPaySiteMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception {
		BigDecimal result = accountCheckMapper.queryAfterWebPaySiteMoneyByCnd(accountCheckCnd);
		if (null != result) {
			return result;
		}
		return BigDecimal.ZERO;
	}

	
	public NewThroughTrainInformationStatisticsVO queryNewThroughTrainInformationStatisticsData(AccountCheckCnd accountCheckCnd) {
		if (null != accountCheckCnd.getBeginTime()) {
			accountCheckCnd.setBeginTime(DateUtils.convert2StartDate(accountCheckCnd.getBeginTime()));
			accountCheckCnd.setBeginTimeStr(accountCheckCnd.getBeginTime().getTime() / 1000 + "");
		} else {
			accountCheckCnd.setBeginTimeStr("");
		}
		if (null != accountCheckCnd.getEndTime()) {
			accountCheckCnd.setEndTime(DateUtils.convert2EndDate(accountCheckCnd.getEndTime()));
			accountCheckCnd.setEndTimeStr(accountCheckCnd.getEndTime().getTime() / 1000 + "");
		} else {
			accountCheckCnd.setEndTimeStr("");
		}
		NewThroughTrainInformationStatisticsVO result = accountCheckMapper.queryNewThroughTrainInformationStatisticsData(accountCheckCnd);
		return result;
	}

	
	public RealtimeUserAccountVO queryRealtimeUserAccount() throws Exception {
		return accountCheckMapper.queryRealtimeUserAccount();
	}

	
	public List<RealtimeUserAccountVO> queryRealtimeUserAccountList() throws Exception {
		return accountCheckMapper.queryRealtimeUserAccountList();
	}

}
