package com.cxdai.console.account.cash.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.statistics.account.service.AccountService;
import com.cxdai.console.statistics.account.vo.AccountCnd;

/**
 * 转可提受限异常
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/account/cashdrawexcecount")
public class CashDrawRecordExceController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(CashDrawRecordExceController.class);
	@Autowired
	private AccountService accountService;
	
	@RequestMapping("/main")
	public ModelAndView forAccountToDrawMain(){
		return new ModelAndView("/account/cash/query/drawexce/main");
	}
	
	/**
	 * 转可提受限异常列表
	 * @param accountCnd
	 * @return
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView searchAccoutList(@ModelAttribute AccountCnd accountCnd,@PathVariable Integer pageNo){
		Page page = null;
		try {
			page = accountService.queryAccountListForPage(accountCnd, Constants.CONSOLE_PAGE_SIZE4, pageNo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("转可提现受限错误信息："+e);
		}
		return new ModelAndView("/account/cash/query/drawexce/list").addObject("page", page);
	}
	
	/**
	 * 根据id提示可将受限转可提信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/getToDrawInfo/{id}")
	public ModelAndView getToDrawInfo(@PathVariable Integer id){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = accountService.getIsToDrawInfo(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/account/cash/query/drawexce/layer").addObject("map", map).addObject("userId", id);
	}
	
	/**
	 * 受限转可提
	 * @param id
	 * @return
	 */
	@RequestMapping("/accountToDraw/{id}")
	@ResponseBody
	public MessageBox accountToDraw(@PathVariable Integer id){
		String resultMsg=null;
		try {
			resultMsg = accountService.accountToDraw(id);
		} catch (Exception e) {
			e.printStackTrace();
			return MessageBox.build("1", "系统错误，请稍后再试!!");
		}
		return new MessageBox("0", resultMsg);
		
	}

}
