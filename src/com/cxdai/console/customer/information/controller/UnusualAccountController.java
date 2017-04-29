package com.cxdai.console.customer.information.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.information.service.UnusualAccountService;
import com.cxdai.console.customer.information.vo.UnusualAccountCnd;

/**
 * <p>
 * Description:客户管理-客户信息-异常账户记录<br />
 * </p>
 * 
 * @title MemberClearController.java
 * @package com.cxdai.console.customer.information.controller
 * @author hujianpan
 * @version 0.1 2015年3月16日
 */
@Controller
@RequestMapping(value = "/customer/unusualaccount")
public class UnusualAccountController extends BaseController {

	private final static Logger logger = Logger.getLogger(UnusualAccountController.class);
	@Autowired
	private UnusualAccountService unusualAccountService;

	/**
	 * <p>
	 * Description:异常账户主界面<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2015年3月11日
	 * @param memberCnd
	 * @return ModelAndView
	 */
	@RequestMapping("/main")
	public ModelAndView unusualAccountIndex() {
		return new ModelAndView("/customer/information/unusualaccount/index");
	}

	/**
	 * <p>
	 * Description:查询异常账户<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2015年3月11日
	 * @param unusualAccountCnd
	 * @param pageNo
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/query/{pageNo}")
	public ModelAndView queryUnusualAccout(@ModelAttribute UnusualAccountCnd unusualAccountCnd, @PathVariable Integer pageNo) {
		Page page = null;
		try {
			page = unusualAccountService.queryAccountUnusualForPage(unusualAccountCnd, Constants.CONSOLE_PAGE_SIZE, pageNo);
		} catch (Exception e) {
			logger.error(e);
			return new ModelAndView("/customer/information/unusualaccount/list").addObject("page", null);
		}
		return new ModelAndView("/customer/information/unusualaccount/list").addObject("page", page).addObject("unusualAccountCnd", unusualAccountCnd);
	}

}
