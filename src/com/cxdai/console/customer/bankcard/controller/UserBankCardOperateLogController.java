package com.cxdai.console.customer.bankcard.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.WxContants;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.bankcard.service.UserBindBankService;
import com.cxdai.console.customer.bankcard.vo.UserBindBankCnd;

/**
 * 
 * @author 胡建盼
 * @desc 用户银行卡操作日志
 * 
 */
@Controller
@RequestMapping(value="/userBankCardOperateLog")
public class UserBankCardOperateLogController extends BaseController {

	/**
	 * @fields serialVersionUID
	 */


	private final static Logger logger = Logger.getLogger(UserBankCardOperateLogController.class);

	@Autowired
	UserBindBankService userBindBankService;
	

	@RequestMapping(value = "/toUserBankCardOperateLogMain")
	@ResponseBody
	public ModelAndView toUserBankCardOperateLogMain(){
		return forword("/customer/bankcard/userBankCardOperateLogMain");
	}
	
	

	@RequestMapping(value="/serachAll/{pageNo}")
	public ModelAndView serachAll(@ModelAttribute UserBindBankCnd userBindBankCnd,@PathVariable("pageNo") int pageNo) {
		Page page = new Page();
		try {
			page =  userBindBankService.queryUserBanksCardOperateLog(userBindBankCnd, new Page(pageNo, WxContants.WX_PAGE_SIZE));
		} catch (Exception e) {
			logger.error(e);
		}
		return forword("/customer/bankcard/userBankCardOperateLogList").addObject("page",page);
	}

}
