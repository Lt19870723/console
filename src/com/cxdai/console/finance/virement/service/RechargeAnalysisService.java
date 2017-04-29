package com.cxdai.console.finance.virement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.finance.virement.mapper.RechargeAnalysisMapper;
import com.cxdai.console.finance.virement.vo.RechargeAnalysisCnd;
import com.cxdai.console.finance.virement.vo.RechargeAnalysisVo;

@Service
public class RechargeAnalysisService {

	@Autowired
	private RechargeAnalysisMapper rechargeAnalysisMapper;

	public Page queryRechargeAnalysisListForPages(RechargeAnalysisCnd rechargeAnalysisCnd, Integer curPage,
			Integer pageSize) {
		Page page = new Page(curPage, pageSize);
		Integer totalCount = rechargeAnalysisMapper.queryListCountByCnd(rechargeAnalysisCnd);
		page.setTotalCount(totalCount);
		List<RechargeAnalysisVo> list = rechargeAnalysisMapper.queryListByCnd(rechargeAnalysisCnd, page);
		page.setResult(list);
		return page;
	}

	public List<RechargeAnalysisVo> exportToExcel(RechargeAnalysisCnd rechargeAnalysisCnd) {
		return rechargeAnalysisMapper.queryRechargeAnalysisList(rechargeAnalysisCnd);
	}

}
