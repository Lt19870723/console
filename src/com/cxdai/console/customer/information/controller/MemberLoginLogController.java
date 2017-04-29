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
import com.cxdai.console.customer.information.service.LoginService;
import com.cxdai.console.customer.information.vo.LoginRecordCnd;

/**
 * <p>
 * Description:客户管理-客户信息-登录日志查询<br />
 * </p>
 * 
 * @title MemberClearController.java
 * @package com.cxdai.console.customer.information.controller
 * @author hujianpan
 * @version 0.1 2015年3月16日
 */
@Controller
@RequestMapping(value = "/customer/memberloginlog")
public class MemberLoginLogController extends BaseController {

	private final static Logger logger = Logger.getLogger(MemberLoginLogController.class);
	@Autowired
	private LoginService loginService;

	/**
	 * <p>
	 * Description:客戶信息-登录日志主界面<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2015年3月11日
	 * @param memberCnd
	 * @return ModelAndView
	 */
	@RequestMapping("/main")
	public ModelAndView loginLogIndex() {
		return new ModelAndView("/customer/information/loginlog/index");
	}

	/**
	 * <p>
	 * Description:客户信息-登录日志查询<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2015年3月11日
	 * @param loginRecordCnd
	 * @param pageNo
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/query/{pageNo}")
	public ModelAndView queryLoginLog(@ModelAttribute LoginRecordCnd loginRecordCnd, @PathVariable Integer pageNo) {
		Page page = null;
		try {
			page = loginService.queryMemberLoginRecordWithPage(loginRecordCnd, Constants.CONSOLE_PAGE_SIZE, pageNo);
		} catch (Exception e) {
			logger.error("网络异常：", e);
			return new ModelAndView("/customer/information/loginlog/list").addObject("page", null);
		}
		return new ModelAndView("/customer/information/loginlog/list").addObject("page", page).addObject("loginRecordCnd", loginRecordCnd);
	}
}
