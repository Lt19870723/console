package com.cxdai.console.statistics.firstborrow.controller;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.statistics.firstborrow.entity.NewThroughTrainInformationStatisticsVO;
import com.cxdai.console.statistics.reconciliation.entity.AccountCheckCnd;
import com.cxdai.console.statistics.reconciliation.service.AccountCheckService;

/**
 * 直通车分析--上下车分析
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/statistics/throughtraingetoffinfo")
public class ThroughTrainInformationController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(ThroughTrainInformationController.class);
	@Autowired
	private AccountCheckService accountCheckService;
	
	@RequestMapping("/main")
	public ModelAndView forThroughTrainInformationGetonOrOffStatistics(){
		return new ModelAndView("/statistics/firstborrow/getofftrain/main");
	}
	
	/**
	 * 上下车统计结果
	 * @param accountCheckCnd
	 * @return
	 */
	@RequestMapping("/querynewthroughgetoff")
	public ModelAndView queryNewThroughTrainInformationStatistics(@ModelAttribute AccountCheckCnd accountCheckCnd,HttpServletRequest request){
		NewThroughTrainInformationStatisticsVO informationStatisticsVO = new NewThroughTrainInformationStatisticsVO();
		try {
			if(!StringUtils.isEmpty(request.getParameter("begintotal"))){
				accountCheckCnd.setBeginTime(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("begintotal")));
			}
			if(!StringUtils.isEmpty(request.getParameter("endtotal"))){
				accountCheckCnd.setEndTime(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("endtotal")));
			}
			informationStatisticsVO=accountCheckService.queryNewThroughTrainInformationStatisticsData(accountCheckCnd);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("上下车统计错误信息："+e);
		}
		return new ModelAndView("/statistics/firstborrow/getofftrain/list").addObject("informationStatisticsVO", informationStatisticsVO);
	}

}
