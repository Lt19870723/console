package com.cxdai.console.activity.mapper;

import java.util.List;

import com.cxdai.console.activity.vo.BillionActivity;
import com.cxdai.console.statistics.account.vo.ReportStatementCnd;


/**
 * <p>
 * Description:活动据访问类<br />
 * </p>
 * 
 * @title SmsTemplateMapper.java
 * @package com.cxdai.portal.sms.mapper
 * @author justin.xu
 * @version 0.1 2014年4月29日
 */
public interface BillionActivityMapper {
	/**
	 * 活动数据查询-50亿活动数据记录
	 * @author liutao
	 * @Date 2016-05-10
	 */
	public List<BillionActivity> queryFiveBillionList(
			ReportStatementCnd reportStatementCnd) throws Exception;
	/**
	 * 活动数据查询-50亿活动数据记录数
	 * @author liutao
	 * @Date 2016-05-10
	 */
	public Integer queryFiveBillionCount(
			ReportStatementCnd reportStatementCnd) throws Exception;
	/**
	 * 活动数据查询-50亿活动数据记录
	 * @author liutao
	 * @Date 2016-05-10
	 */
	public List<BillionActivity> queryFiveBillionList1(
			ReportStatementCnd reportStatementCnd) throws Exception;
	/**
	 * 活动数据查询-50亿活动数据记录数
	 * @author liutao
	 * @Date 2016-05-10
	 */
	public Integer queryFiveBillionCount1(
			ReportStatementCnd reportStatementCnd) throws Exception;
}
