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
 * 待收的应收总额
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/account/collectiontotal")
public class CollectionTotalController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(CollectionTotalController.class);
	
	@Autowired
	private AccountValidateService accountValidateService;
	/**
	 * 进入待收的应收总额页面
	 * @return
	 */
	@RequestMapping("/main")
	public ModelAndView toCollectionTotalList(){
		return new ModelAndView("/account/verify/collectiontotal/main");
	}
	
	/**
	 * 待收应收总额列表
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView searchCollectionTotalList(@ModelAttribute AccountValidateCnd accountValidateCnd,@PathVariable Integer pageNo){
		Page page = null;
		try {
			page = accountValidateService.queryCollectionValidateListByCnd(accountValidateCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询待收应收总额错误信息："+e);
		}
		return new ModelAndView("/account/verify/collectiontotal/list").addObject("page", page);
	}

}
