package com.cxdai.console.statistics.account.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.statistics.account.vo.ReportStatementCnd;
import com.cxdai.console.statistics.account.vo.TotalReportVo;
import com.cxdai.console.statistics.customer.entity.NewInterestMember;

public interface TReportMapper {

	// 查询
	public TotalReportVo queryTReport(ReportStatementCnd reportStatementCnd);
	public TotalReportVo queryTReport1(ReportStatementCnd reportStatementCnd);
	public int queryAddInvestPersonsTotal(ReportStatementCnd reportStatementCnd);
	public int queryInvestPersonsTotal(ReportStatementCnd reportStatementCnd);
	public int queryNowInvestPersonsTotal(ReportStatementCnd reportStatementCnd);
	// 查询流失投资者人数
	public int queryLosePersonsTotal1(ReportStatementCnd reportStatementCnd);

	// 查询流失投资者人数
	public int queryLosePersonsTotal2(ReportStatementCnd reportStatementCnd);

	// // 论坛活动奖励
	public BigDecimal queryForumAwardTotal(ReportStatementCnd reportStatementCnd);

	public BigDecimal queryActivityAwardTotal(ReportStatementCnd reportStatementCnd);

	// 网银在线充值费用
	public BigDecimal queryPayFeeTotal(ReportStatementCnd reportStatementCnd);

	// <!-- onlinetype等于2中的网银在线总额-->
	public BigDecimal queryPayFeeTotalOnline(ReportStatementCnd reportStatementCnd);

	/**
	 * <p>
	 * Description:统计公司需要给连连支付的充值费用<br />
	 * </p>
	 * @author justin.xu
	 * @version 0.1 2014年11月17日
	 * @param reportStatementCnd
	 * @return
	 * @throws Exception BigDecimal
	 */
	public BigDecimal queryllPayFeeTotal(ReportStatementCnd reportStatementCnd) throws Exception;

	/**
	 * 统计公司需要给富友支付的充值费用
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2015年8月24日
	 * @param reportStatementCnd
	 * @return
	 * @throws Exception BigDecimal
	 */
	public BigDecimal queryFuiouPayFeeTotal(ReportStatementCnd reportStatementCnd) throws Exception;


    /**
     * 统计公司需要给浙商存管的充值费用
     * @param reportStatementCnd
     * @return
     * @throws Exception
     */
    public BigDecimal queryCzbankCustodyTotal(ReportStatementCnd reportStatementCnd) throws Exception;

	/**
	 * <p>
	 * Description:活期宝已支付投资人利息<br />
	 * </p>
	 * @author 朱泳霖
	 * @version 0.1 2015年5月29日
	 * @param reportStatementCnd
	 * @return BigDecimal
	 */
	public BigDecimal queryCurInterestTotal(ReportStatementCnd reportStatementCnd);

	/**
	 * <p>
	 * Description:红包支出<br />
	 * </p>
	 * @author 刘涛
	 * @version 0.1 2015年10月11日
	 * @param reportStatementCnd
	 * @return BigDecimalqueryCurRedOutTotal
	 */
	public BigDecimal queryCurRedOutTotal(ReportStatementCnd reportStatementCnd);
	/**
	 * <p>
	 * Description:共享奖<br />
	 * </p>
	 * @author 刘涛
	 * @version 0.1 2016年01月05日
	 * @param reportStatementCnd
	 * @return BigDecimalqueryCurRedOutTotal
	 */
	public BigDecimal queryGongxiangJiangTotal(ReportStatementCnd reportStatementCnd);
	
	// 网银在线充值费用（最新）
	public BigDecimal queryPayFeeTotalForOnline(ReportStatementCnd reportStatementCnd);
	/**
	 * 红包管理-查询新投资人列表
	 * @author liutao
	 * @Date 2016-03-22
	 */
	public List<NewInterestMember> queryNewInterestMemberList(ReportStatementCnd reportStatementCnd, Page page);
	/**
	 * 新投资人列表-查询新投资人记录数
	 * @author liutao
	 * @Date 2016-03-22
	 */
	public Integer queryNewInterestMemberCount(ReportStatementCnd reportStatementCnd);
	/**
	 * 新投资人列表-查询新投资人列表导出
	 * @author liutao
	 * @Date 2016-03-22
	 */
	public List<NewInterestMember> queryNewInterestMemberList(ReportStatementCnd reportStatementCnd);
}
