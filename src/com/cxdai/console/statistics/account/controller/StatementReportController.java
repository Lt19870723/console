package com.cxdai.console.statistics.account.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.statistics.account.entity.YunYingData;
import com.cxdai.console.statistics.account.service.OperateStatementService;
import com.cxdai.console.statistics.account.vo.OperateStatementVo;
import com.cxdai.console.statistics.account.vo.ReportStatementCnd;

/**
 * 统计报表
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/statistics/statementreport")
public class StatementReportController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(StatementReportController.class);
	@Autowired
	private OperateStatementService operateStatementService;
	
	@RequestMapping("/main")
	public ModelAndView forStatementReport(){
		return new ModelAndView("/statistics/account/statementreport/main");
	}
	
	@RequestMapping("/statisticsstatementcount")
	public ModelAndView statisticsStatement(@ModelAttribute ReportStatementCnd reportStatementCnd){
		OperateStatementVo operateStatementVo = new OperateStatementVo();
		try {
			operateStatementVo = operateStatementService.queryOperateStatement(reportStatementCnd);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("统计报表错误信息："+e);
		}
		return new ModelAndView("/statistics/account/statementreport/list").addObject("operateStatementVo", operateStatementVo);
	}
	@RequestMapping("/yunYingmain")
	public ModelAndView forunyingmain(){
		return new ModelAndView("/statistics/account/yunYingDate/main");
	}
	
	@RequestMapping("/yunYingList")
	public ModelAndView statisticsyunYingList(@ModelAttribute ReportStatementCnd reportStatementCnd){
		YunYingData yunYingData = new YunYingData();
		try {
			yunYingData = operateStatementService.querystatisticsyunYingList(reportStatementCnd);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("每周运营数据错误信息："+e);
		}
		return new ModelAndView("/statistics/account/yunYingDate/list").addObject("yunYingData", yunYingData);
	}
}
