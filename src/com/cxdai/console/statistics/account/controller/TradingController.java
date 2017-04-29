package com.cxdai.console.statistics.account.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.statistics.account.service.TReportService;
import com.cxdai.console.statistics.account.vo.ReportStatementCnd;
import com.cxdai.console.statistics.account.vo.TotalReportVo;

/**
 * 平台交易
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/statistics/tradingcount")
public class TradingController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(TradingController.class);
	@Autowired
	private TReportService tRepotrService;
	
	@RequestMapping("/main")
	public ModelAndView forTotalReport(){
		return new ModelAndView("/statistics/account/trdingcount/main");
	}
	
	/**
	 * 平台收支统计结果
	 * @param reportStatementCnd
	 * @return
	 */
	@RequestMapping("/reportcount")
	public ModelAndView TotalReport(@ModelAttribute ReportStatementCnd reportStatementCnd) {
		TotalReportVo totalReportVo = new TotalReportVo();
		try {
			totalReportVo = tRepotrService.queryTReport(reportStatementCnd);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("平台收支统计错误信息："+e);
		}
		return new ModelAndView("/statistics/account/trdingcount/list").addObject("reportVo", totalReportVo);
	}

}
