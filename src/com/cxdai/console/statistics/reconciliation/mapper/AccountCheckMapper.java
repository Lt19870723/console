package com.cxdai.console.statistics.reconciliation.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.cxdai.console.statistics.firstborrow.entity.NewThroughTrainInformationStatisticsVO;
import com.cxdai.console.statistics.reconciliation.entity.AccountCheckCnd;
import com.cxdai.console.statistics.reconciliation.vo.RealtimeUserAccountVO;
import com.cxdai.console.statistics.reconciliation.vo.RepayEarlyCheckVO;

/**
 * <p>
 * Description:资金对帐数据访问类<br />
 * </p>
 * 
 * @title AccountCheckMapper.java
 * @package com.cxdai.console.account.mapper
 * @author justin.xu
 * @version 0.1 2014年10月14日
 */
public interface AccountCheckMapper {
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
	public BigDecimal queryOnlineRechargeMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception;

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
	public BigDecimal queryOfflineRechargeMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception;

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
	public BigDecimal queryWebRechargeMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception;

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
	public BigDecimal queryInvestorTenderMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception;

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
	public BigDecimal queryBorrowerSuccuessMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception;

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
	public BigDecimal queryBorrowMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception;

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
	public BigDecimal queryRepaymentBackMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception;

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
	public BigDecimal queryWebSiteMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception;

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
	public BigDecimal queryBorrowerPayMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception;

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
	public BigDecimal queryWebMustPayMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception;

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
	public BigDecimal queryPaymentSuccessMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception;

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
	public BigDecimal queryPaymentFailedMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception;

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
	public BigDecimal queryPaymentActualFailedMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception;

	/**
	 * <p>
	 * Description:根据查询条件统计网站提现支出总额（包括作废金额）<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年10月14日
	 * @param accountCheckCnd
	 * @return
	 * @throws Exception BigDecimal
	 */
	public BigDecimal queryTakeCashMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception;

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
	public BigDecimal queryActualCashMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception;

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
	public BigDecimal queryWebPayRepaymentMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception;

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
	public BigDecimal queryWebPayCollectionMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception;

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
	public BigDecimal queryWebPayRepaymentBackMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception;

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
	public BigDecimal queryAfterWebPayMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception;

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
	public BigDecimal queryAfterWebPayRepaymentMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception;

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
	public BigDecimal queryAfterWebPayCollectionMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception;

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
	public BigDecimal queryAfterWebPayNoVipInterestMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception;

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
	public BigDecimal queryAfterWebPaySiteMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception;

	public NewThroughTrainInformationStatisticsVO queryNewThroughTrainInformationStatisticsData(AccountCheckCnd accountCheckCnd);

	/**
	 * <p>
	 * Description:根据查询条件统计提前还款对账数据<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年10月14日
	 * @param accountCheckCnd
	 * @return
	 * @throws Exception BigDecimal
	 */
	public RepayEarlyCheckVO queryRepayEarlyCheckByCnd(AccountCheckCnd accountCheckCnd) throws Exception;

	/**
	 * <p>
	 * Description:查询实时用户资金情况<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年10月14日
	 * @param accountCheckCnd
	 * @return RealtimeUserAccountVO
	 * @throws Exception
	 */
	public RealtimeUserAccountVO queryRealtimeUserAccount() throws Exception;

	/**
	 * <p>
	 * Description:查询实时用户资金情况集合<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年10月14日
	 * @param accountCheckCnd
	 * @return RealtimeUserAccountVO
	 * @throws Exception
	 */
	public List<RealtimeUserAccountVO> queryRealtimeUserAccountList() throws Exception;

}
