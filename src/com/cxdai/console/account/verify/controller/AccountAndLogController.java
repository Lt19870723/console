package com.cxdai.console.account.verify.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.account.verify.service.AccountValidateService;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.statistics.account.vo.AccountValidateCnd;

/**
 * 资金验证
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/account/accountandlog/verify")
public class AccountAndLogController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(AccountAndLogController.class);
	
	@Autowired
	private AccountValidateService accountValidateService;
	
	/**
	 * Description账户和账户日志比对<br />
	 */
	@RequestMapping("/main")
	public ModelAndView toAccountAndLogList() {
		return new ModelAndView("/account/verify/accountandlog/main");
	}
	
	/**
	 * 账户和账户日志列表
	 * @param accountValidateCnd
	 * @param pageNo
	 * @return
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView searchAccountAndLogList(@ModelAttribute AccountValidateCnd accountValidateCnd,@PathVariable Integer pageNo){
		Page page = null;
		try {
			page = accountValidateService.queryAccountAndLogListByCnd(accountValidateCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取账户和账户列表错误信息："+e);
		}
		return new ModelAndView("/account/verify/accountandlog/list").addObject("page", page);
	}
	

}
