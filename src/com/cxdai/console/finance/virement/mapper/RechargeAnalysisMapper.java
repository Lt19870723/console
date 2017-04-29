package com.cxdai.console.finance.virement.mapper;

import java.util.List;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.finance.virement.vo.RechargeAnalysisCnd;
import com.cxdai.console.finance.virement.vo.RechargeAnalysisVo;

public interface RechargeAnalysisMapper {

	Integer queryListCountByCnd(RechargeAnalysisCnd rechargeAnalysisCnd);

	List<RechargeAnalysisVo> queryListByCnd(RechargeAnalysisCnd rechargeAnalysisCnd, Page page);

	List<RechargeAnalysisVo> queryRechargeAnalysisList(RechargeAnalysisCnd rechargeAnalysisCnd);

}
