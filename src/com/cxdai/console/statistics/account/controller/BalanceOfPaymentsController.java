package com.cxdai.console.statistics.account.controller;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.fix.entity.RockyAccountLog;
import com.cxdai.console.statistics.account.service.TReportService;
import com.cxdai.console.statistics.account.vo.ReportStatementCnd;
import com.cxdai.console.statistics.account.vo.TotalReportVo;

/**
 * 平台收支统计
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/statistics/balancepaymentcount")
public class BalanceOfPaymentsController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(BalanceOfPaymentsController.class);
	@Autowired
	private TReportService tRepotrService;
	
	@RequestMapping("/main")
	public ModelAndView forTotalReport(){
		return new ModelAndView("/statistics/account/balanpaycount/main");
	}
	
	/**
	 * 平台收支统计结果
	 * @param reportStatementCnd
	 * @return
	 */
	@RequestMapping("/reportcount")
	public ModelAndView TotalReport(@ModelAttribute ReportStatementCnd reportStatementCnd) {
		TotalReportVo totalReportVo = new TotalReportVo();
		BigDecimal totalMoney = new BigDecimal("0");
		BigDecimal awardMoney = new BigDecimal("0");
		try { 
			totalReportVo = tRepotrService.queryTReport(reportStatementCnd);
			
			totalMoney = tRepotrService.querySumRockyAccountLog(reportStatementCnd);  //推荐活动现金支出
			
			awardMoney = tRepotrService.queryAwardAccountLog(reportStatementCnd);  //论坛活动现金支出
			
			System.out.print("吴少铃"+totalReportVo.getPayTotal());
			BigDecimal paytotal = totalReportVo.getPayTotal().add(totalMoney);   //总支出
			totalReportVo.setPayTotal(paytotal);   //传到返回类中
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("平台收支统计错误信息："+e);
		}
		return new ModelAndView("/statistics/account/balanpaycount/list").addObject("reportVo", totalReportVo).addObject("totalMoney",totalMoney).addObject("awardMoney", awardMoney);
	}

}
