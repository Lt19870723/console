package com.cxdai.console.finance.virement.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.finance.virement.mapper.WithdrawalAnalysisMapper;
import com.cxdai.console.finance.virement.vo.WithdrawalAnalysisCnd;
import com.cxdai.console.finance.virement.vo.WithdrawalAnalysisVo;

@Service
public class WithdrawalAnalysisService {

	@Autowired
	private WithdrawalAnalysisMapper withdrawalAnalysisMapper;

	/***
	 * 
	 * <p>
	 * Description:根据条件查询用户充值数据<br />
	 * </p>
	 * 
	 * @author Kimmyzhao
	 * @version 0.1 2016年4月17日
	 * @param withdrawalAnalysisCnd
	 * @param pageNo
	 * @param consolePageSize
	 * @return Page
	 */
	public Page queryWithdrawalAnalysisListForPages(WithdrawalAnalysisCnd withdrawalAnalysisCnd, Integer curPage,
			Integer pageSize) {
		Page page = new Page(curPage, pageSize);
		Integer totalCount = withdrawalAnalysisMapper.queryListCountByCnd(withdrawalAnalysisCnd);
		page.setTotalCount(totalCount);
		List<WithdrawalAnalysisVo> list = withdrawalAnalysisMapper.queryListByCnd(withdrawalAnalysisCnd, page);
		page.setResult(list);
		return page;
	}

	public List<WithdrawalAnalysisVo> exportToExcel(WithdrawalAnalysisCnd withdrawalAnalysisCnd) {
		return withdrawalAnalysisMapper.queryWithdrawalAnalysisList(withdrawalAnalysisCnd);
	}

	/***
	 * 
	 * <p>
	 * Description:根据条件统计分类汇总<br />
	 * </p>
	 * 
	 * @author Kimmyzhao
	 * @version 0.1 2016年4月22日
	 * @param withdrawalAnalysisCnd
	 * @return Map<String,BigDecimal>
	 */
	public Map<String, BigDecimal> getTotalDataMap(WithdrawalAnalysisCnd withdrawalAnalysisCnd) {
		// 总金额=正常提现金额+撤资提现金额+撤资倾向提现金额
		BigDecimal totalWithdrawalAmount = withdrawalAnalysisMapper.getTotalWithdrawalAccount(withdrawalAnalysisCnd);
		BigDecimal totalNomalWithdrawal = withdrawalAnalysisMapper.getTotalNomalWithdrawal(withdrawalAnalysisCnd);
		BigDecimal totalDisinvestment = withdrawalAnalysisMapper.getTotalDisinvestment(withdrawalAnalysisCnd);
		BigDecimal totalDivestmentTendency = withdrawalAnalysisMapper.getTotalDivestmentTendency(withdrawalAnalysisCnd);

		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		map.put("totalWithdrawalAmount", totalWithdrawalAmount);
		map.put("totalNomalWithdrawal", totalNomalWithdrawal);
		map.put("totalDisinvestment", totalDisinvestment);
		map.put("totalDivestmentTendency", totalDivestmentTendency);
		return map;
	}

}
