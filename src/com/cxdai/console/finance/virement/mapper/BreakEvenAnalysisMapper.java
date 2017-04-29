package com.cxdai.console.finance.virement.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.finance.virement.entity.BreakEvenAnalysis;
import com.cxdai.console.finance.virement.vo.BreakEvenCnd;

public interface BreakEvenAnalysisMapper {
	BreakEvenAnalysis selectByPrimaryKey(Integer id);

	int updateByPrimaryKey(BreakEvenAnalysis record);

	Integer queryListCountByCnd(BreakEvenCnd breakEvenCnd);

	List<BreakEvenAnalysis> queryListByCnd(BreakEvenCnd breakEvenCnd, Page page);

	List<BreakEvenAnalysis> queryBreakEvenAnalysisList(BreakEvenCnd breakEvenCnd);

	List<BigDecimal> queryIncomeList(BreakEvenCnd breakEvenCnd);

	List<BigDecimal> queryPayList(BreakEvenCnd breakEvenCnd);

	List<Map<String, Object>> getIncomePayResultMap(BreakEvenCnd breakEvenCnd);

	/***
	 * 
	 * <p>
	 * Description:根据条件统计最大支出、最大收入<br />
	 * </p>
	 * 
	 * @author Kimmyzhao
	 * @version 0.1 2016年4月22日
	 * @param breakEvenCnd
	 * @return BigDecimal
	 */
	Map<String, BigDecimal> getMaxPayOrIncome(BreakEvenCnd breakEvenCnd);

	/****
	 * 净收益=收入-支出
	 * <p>
	 * Description:根据调价查询统计净收益信息<br />
	 * </p>
	 * 
	 * @author Kimmyzhao
	 * @version 0.1 2016年4月22日
	 * @param breakEvenCnd
	 * @return List<Map<String,Object>>
	 */
	List<Map<String, Object>> getNetBenefit(BreakEvenCnd breakEvenCnd);

	/***
	 * 
	 * <p>
	 * Description:根据条件统计最小最大净收益<br />
	 * </p>
	 * 
	 * @author Kimmyzhao
	 * @version 0.1 2016年4月23日
	 * @param breakEvenCnd
	 * @return BigDecimal
	 */
	Map<String, BigDecimal> getMinOrMaxNetBenefit(BreakEvenCnd breakEvenCnd);
	/**
	 * 
	 * <p>
	 * Description:获取上个月的数据<br />
	 * </p>
	 * @author fanhaijun
	 * @version 0.1 2016年2月1日
	 * @param breakEvenCnd
	 * @return
	 * List<BreakEvenAnalysis>
	 */
	List<BreakEvenAnalysis> findByMonth(BreakEvenCnd breakEvenCnd);

}