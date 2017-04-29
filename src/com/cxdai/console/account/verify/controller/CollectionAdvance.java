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
 * 待收垫付金额
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/account/collectionAdvance")
public class CollectionAdvance extends BaseController {
	
	private static final Logger logger = Logger.getLogger(CollectionAdvance.class);
	
	@Autowired
	private AccountValidateService accountValidateService;
	
	/**
	 * 进入待收垫付金额页面
	 * @return
	 */
	@RequestMapping("/main")
	public ModelAndView toCollectionAdvanceYAList(){
		return new ModelAndView("/account/verify/collectionadvance/main");
	}
	
	/**
	 * 待收垫付金额列表
	 * @param accountValidateCnd
	 * @param pageNo
	 * @return
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView searchCollectionAdvanceYAList(@ModelAttribute AccountValidateCnd accountValidateCnd,@PathVariable Integer pageNo){
		Page page = null;
		try {
			page = accountValidateService.queryCollectionAdvanceYAListByCnd(accountValidateCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询待收垫付金额错误信息"+e);
		}
		return new ModelAndView("/account/verify/collectionadvance/list").addObject("page", page);
	}

}
