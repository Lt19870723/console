package com.cxdai.console.customer.bankcard.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.bankcard.service.UserBindBankService;
import com.cxdai.console.customer.bankcard.vo.BankCardLockCnd;

/**
 * <p>
 * Description:客户管理-银行卡查询<br />
 * </p>
 * 
 * @title MenuController.java
 * @package com.cxdai.console.system.controller
 * @author qiongbiao.zhang
 * @version 0.1 2015年3月2日
 */
@Controller
@RequestMapping(value = "/customer/bankCardLock")
public class BankCardLockController extends BaseController {
	private static final Logger logger = Logger.getLogger(BankCardLockController.class);

	@Autowired
	public UserBindBankService userBindBankService;

	@RequestMapping(value = "/main")
	public ModelAndView main() {
		int bcLockNum = userBindBankService.queryBankCardLockAcount();
		int wxBindNum = userBindBankService.queryWxBindAcount();
		return forword("/customer/bankcard/lockmain").addObject("bcLockNum", bcLockNum).addObject("wxBindNum", wxBindNum);
	}

	@RequestMapping(value = "/list/{pageNo}")
	public ModelAndView searchBankList(@ModelAttribute BankCardLockCnd bankCardLockCnd, @PathVariable("pageNo") Integer pageNo) {

		try {
			Page page = userBindBankService.queryBankCardLockListByCnd(bankCardLockCnd, pageNo, 10);
			return forword("/customer/bankcard/locklist").addObject("page", page);
		} catch (Exception e) {
			logger.error("searchBankList出错", e);
		}
		return null;
	}



}