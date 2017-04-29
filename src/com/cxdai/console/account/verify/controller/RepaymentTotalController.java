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
 * 待还应还总额
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/account/repaymenttotal")
public class RepaymentTotalController extends BaseController {

	private static final Logger logger = Logger.getLogger(RepaymentTotalController.class);
	
	@Autowired
	private AccountValidateService accountValidateService;
	
	/**
	 * 进入待还应还总额界面
	 * @return
	 */
	@RequestMapping("/main")
	public ModelAndView toRepaymentTotalList(){
		return new ModelAndView("/account/verify/repaymenttotal/main");
	}
	
	/**
	 * 待还应还总额列表
	 * @param accountValidateCnd
	 * @param pageNo
	 * @return
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView searchRepaymentTotalList(@ModelAttribute AccountValidateCnd accountValidateCnd,@PathVariable Integer pageNo){
		Page page = null;
		try {
			page = accountValidateService.queryRepaymentValidateListByCnd(accountValidateCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询待还应还总额错误信息："+e);
		}
		return new ModelAndView("/account/verify/repaymenttotal/list").addObject("page", page);
	}
	
}
