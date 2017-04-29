package com.cxdai.console.statistics.account.controller;

import java.math.BigDecimal;
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
import com.cxdai.console.statistics.account.service.AccountLogService;
import com.cxdai.console.statistics.account.vo.AccountLogCnd;

/**
 * 用户充值统计
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/statistics/rechargeaccount")
public class RechargeAccountController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(PlatformAccountController.class);
	@Autowired
	private AccountLogService accountLogService;
	
	@RequestMapping("/main")
	public ModelAndView forAccountLogForRechargeMain(){
		return new ModelAndView("/statistics/account/rechargeaccount/main");
	}
	
	/**
	 * 用户充值资金统计结果
	 * @param accountLogCnd
	 * @return
	 */
	@RequestMapping("/rechargeaccount")
	public ModelAndView queryRechargeAccountLog(@ModelAttribute AccountLogCnd accountLogCnd,HttpServletRequest request){
		Map<String, BigDecimal> accountLogMap = new HashMap<String, BigDecimal>();
		try {
			if(!StringUtils.isEmpty(request.getParameter("begintotal"))){
				accountLogCnd.setBeginTime(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("begintotal")));
			}
			if(!StringUtils.isEmpty(request.getParameter("endtotal"))){
				accountLogCnd.setEndTime(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("endtotal")));
			}
			accountLogMap = accountLogService.queryAccoutLogByRecharge(accountLogCnd);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("用户充值资金统计错误信息："+e);
		}
		return new ModelAndView("/statistics/account/rechargeaccount/list").addObject("accountLogMap", accountLogMap);
	}
	
	

}
