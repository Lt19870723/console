package com.cxdai.console.statistics.account.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.statistics.account.service.AccountService;

/**
 * 平台资金统计
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/statistics/platformaccount")
public class PlatformAccountController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(PlatformAccountController.class);
	@Autowired
	private AccountService accountService;
	
	@RequestMapping("/main")
	public ModelAndView forPlatformAccountMain(){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = accountService.platformAccount();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("平台资金统计错误信息："+e);
		}
		return new ModelAndView("/statistics/account/platformaccount/main").addObject("map", map);
	}
	
	/**
	 * 平台资金统计结果
	 * @return
	 */
	@RequestMapping("/platformaccount")
	public ModelAndView platformAccount(){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = accountService.platformAccount();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("平台资金统计错误信息："+e);
		}
		return new ModelAndView("/statistics/account/platformaccount/list").addObject("map", map);
	}

}
