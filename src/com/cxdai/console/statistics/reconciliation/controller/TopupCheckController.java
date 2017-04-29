package com.cxdai.console.statistics.reconciliation.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.statistics.reconciliation.entity.AccountCheckCnd;
import com.cxdai.console.statistics.reconciliation.service.AccountCheckService;

/**
 * 充值对账
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/statistics/topupcheckinfo")
public class TopupCheckController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(TopupCheckController.class);
	
	@Autowired
	private AccountCheckService accountCheckService;
	
	@RequestMapping("/main")
	public ModelAndView forTopupCheckMain(){
		return new ModelAndView("/statistics/reconciliation/topupcheck/main");
	}
	
	/**
	 * 充值对账统计结果
	 * @return
	 */
	@RequestMapping("/topupcount")
	public ModelAndView queryTopupCheckMap(@ModelAttribute AccountCheckCnd accountCheckCnd,HttpServletRequest request){
		Map<String, Object> checkMap = new HashMap<String, Object>();
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			if(!StringUtils.isEmpty(request.getParameter("beginTotalTime"))){
				accountCheckCnd.setBeginTime(format.parse(request.getParameter("beginTotalTime")));
				accountCheckCnd.setEndTime(format.parse(request.getParameter("beginTotalTime")));
			}
			checkMap = accountCheckService.queryTopupCheckByCnd(accountCheckCnd);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("充值对账统计错误信息："+e);
		}
		return new ModelAndView("/statistics/reconciliation/topupcheck/list").addObject("checkMap", checkMap);
	}

}
