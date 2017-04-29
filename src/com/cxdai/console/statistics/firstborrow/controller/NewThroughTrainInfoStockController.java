package com.cxdai.console.statistics.firstborrow.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.statistics.firstborrow.entity.NewThroughTrainInformationStatisticsVO;
import com.cxdai.console.statistics.reconciliation.entity.AccountCheckCnd;
import com.cxdai.console.statistics.reconciliation.service.AccountCheckService;

/**
 * 直通车分析--库存分析
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/statistics/newthroughtrainstock")
public class NewThroughTrainInfoStockController extends BaseController {

	private static final Logger logger = Logger.getLogger(NewThroughTrainInfoStockController.class);
	
	@Autowired
	private AccountCheckService accountCheckService;
	
	@RequestMapping("/main")
	public ModelAndView forNewThroughTrainInformationStatistics(@ModelAttribute AccountCheckCnd accountCheckCnd){
		NewThroughTrainInformationStatisticsVO informationStatisticsVO = new NewThroughTrainInformationStatisticsVO();
		try {
			informationStatisticsVO = accountCheckService.queryNewThroughTrainInformationStatisticsData(accountCheckCnd);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("直通车库存错误信息："+e);
		}
		return new ModelAndView("/statistics/firstborrow/newtrainstock/main").addObject("informationStatisticsVO", informationStatisticsVO);
	}
	
}
