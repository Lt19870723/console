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
 * 待收、待还表中每期本金之和
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/account/crsumcapital")
public class CRSumCapital extends BaseController {
	
	private static final Logger logger = Logger.getLogger(CRSumCapital.class);
	
	@Autowired
	private AccountValidateService accountValidateService;
	
	
	@RequestMapping("/main")
	public ModelAndView toCRSumCapitalList(){
		return new ModelAndView("/account/verify/crsumcapital/main");
	}
	
	/**
	 * 待收、待还表中每期本金之和列表
	 * @param accountValidateCnd
	 * @param pageNo
	 * @return
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView searchCRSumCapitalList(@ModelAttribute AccountValidateCnd accountValidateCnd,@PathVariable Integer pageNo){
		Page page = null;
		try {
			page = accountValidateService.queryCRSumCapitalListByCnd(accountValidateCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询待收、待还表中每期本金之和错误信息："+e);
		}
		return new ModelAndView("/account/verify/crsumcapital/list").addObject("page", page);
	}
	
	

}
