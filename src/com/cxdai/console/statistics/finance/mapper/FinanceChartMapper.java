package com.cxdai.console.statistics.finance.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.cxdai.console.borrow.manage.vo.BRepaymentRecordCnd;
import com.cxdai.console.borrow.manage.vo.BRepaymentRecordVo;
import com.cxdai.console.common.page.Page;

public interface FinanceChartMapper {

	/**
	 * 
	 * <p>
	 * Description:查询某段时间内的抵押标还款记录<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年12月4日
	 * @param repaymentRecordCnd
	 * @param page
	 * @return
	 * @throws Exception List<BRepaymentRecordVo>
	 */
	public List<BRepaymentRecordVo> findRepaymentChartForWeekPages(BRepaymentRecordCnd repaymentRecordCnd, Page page) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询某段时间内的抵押标还款记录（不分页）<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年12月8日
	 * @param repaymentRecordCnd
	 * @return
	 * @throws Exception List<BRepaymentRecordVo>
	 */
	public List<BRepaymentRecordVo> findRepaymentChartForWeekPages(BRepaymentRecordCnd repaymentRecordCnd) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询某段时间内的抵押标还款记录数量<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年12月4日
	 * @param repaymentRecordCnd
	 * @return
	 * @throws Exception Integer
	 */
	public Integer findRepaymentChartForWeekCount(BRepaymentRecordCnd repaymentRecordCnd) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:统计某段时间内的抵押标未还总金额、已还总金额、应还总金额<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年12月4日
	 * @param repaymentRecordCnd
	 * @return
	 * @throws Exception List<Map<String,Object>>
	 */
	public List<Map<String, Object>> queryRepaymentChartForWeek(BRepaymentRecordCnd repaymentRecordCnd) throws Exception;

	/**
	 * <p>
	 * Description:统计某段时间内的官方标未还直通车总金额、已还直通车总金额、应还直通车总金<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2015年3月3日
	 * @param bRepaymentRecordCnd
	 * @return
	 * @throws Exception Map<String,BigDecimal>
	 */
	public Map<String, BigDecimal> queryFirstRepaymentChartForWeek(BRepaymentRecordCnd repaymentRecordCnd) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询逾期的抵押标还款记录<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年12月4日
	 * @param repaymentRecordCnd
	 * @param page
	 * @return
	 * @throws Exception List<BRepaymentRecordVo>
	 */
	public List<BRepaymentRecordVo> findRepaymentChartForOverduePages(BRepaymentRecordCnd repaymentRecordCnd, Page page) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询逾期的抵押标还款记录(不分页)<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年12月4日
	 * @param repaymentRecordCnd
	 * @param page
	 * @return
	 * @throws Exception List<BRepaymentRecordVo>
	 */
	public List<BRepaymentRecordVo> findRepaymentChartForOverduePages(BRepaymentRecordCnd repaymentRecordCnd) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:统计逾期的相关数据<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2015年3月4日
	 * @param repaymentRecordCnd
	 * @return
	 * @throws Exception Map<String,Object>
	 */
	public Map<String, Object> queryRepaymentChartMapForOverdue(BRepaymentRecordCnd repaymentRecordCnd) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询逾期的抵押标还款记录数量<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年12月4日
	 * @param repaymentRecordCnd
	 * @return
	 * @throws Exception Integer
	 */
	public Integer findRepaymentChartForOverdueCount(BRepaymentRecordCnd repaymentRecordCnd) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询结清的抵押标还款记录<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年12月4日
	 * @param repaymentRecordCnd
	 * @param page
	 * @return
	 * @throws Exception List<BRepaymentRecordVo>
	 */
	public List<BRepaymentRecordVo> findRepaymentChartForFinishPages(BRepaymentRecordCnd repaymentRecordCnd, Page page) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询结清的抵押标还款记录(不分页)<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年12月4日
	 * @param repaymentRecordCnd
	 * @param page
	 * @return
	 * @throws Exception List<BRepaymentRecordVo>
	 */
	public List<BRepaymentRecordVo> findRepaymentChartForFinishPages(BRepaymentRecordCnd repaymentRecordCnd) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询结清的抵押标还款记录数量<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年12月4日
	 * @param repaymentRecordCnd
	 * @return
	 * @throws Exception Integer
	 */
	public Integer findRepaymentChartForFinishCount(BRepaymentRecordCnd repaymentRecordCnd) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:统计结清的相关数据<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2015年3月4日
	 * @param repaymentRecordCnd
	 * @return
	 * @throws Exception Map<String,Object>
	 */
	public Map<String, Object> queryRepaymentChartMapForFinish(BRepaymentRecordCnd repaymentRecordCnd) throws Exception;

	/**
	 * 统计某段时间内的官方标未还定期宝总金额、已还定期宝总金额、应还定期宝总金额
	 * @author WangQianJin
	 * @title @param repaymentRecordCnd
	 * @title @return
	 * @title @throws Exception
	 * @date 2016年3月7日
	 */
	public Map<String, BigDecimal> queryFixRepaymentChartForWeek(BRepaymentRecordCnd repaymentRecordCnd) throws Exception;
	
}
