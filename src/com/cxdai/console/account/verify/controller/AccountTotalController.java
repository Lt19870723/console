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
 * 账户总额
 * @author Administrator
 */
@Controller
@RequestMapping(value="/account/accounttotal")
public class AccountTotalController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(AccountTotalController.class);
	
	@Autowired
	private AccountValidateService accountValidateService;
	
	/**
	 * 进入账户总额页面
	 * @return
	 */
	@RequestMapping("/main")
	public ModelAndView toAccountTotalList(){
		return new ModelAndView("/account/verify/accounttotal/main");
	}
	
	@RequestMapping("/list/{pageNo}")
	public ModelAndView searchAccountValidateList(@ModelAttribute AccountValidateCnd accountValidateCnd,@PathVariable Integer pageNo){
		Page page = null;
		try {
			page=accountValidateService.queryAccountValidateListByCnd(accountValidateCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询账户总额错误信息："+e);
		}
		return new ModelAndView("/account/verify/accounttotal/list").addObject("page", page);
	}

}
