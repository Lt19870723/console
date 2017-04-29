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
import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.bankcard.entity.MemberBankCardInfoCnd;
import com.cxdai.console.customer.bankcard.entity.MemberBankCardInfoVo;
import com.cxdai.console.customer.bankcard.service.MemberBankCardService;

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
@RequestMapping(value = "/customer/bankCardQuery")
public class BankCardQueryController extends BaseController {
	private static final Logger logger = Logger.getLogger(BankCardQueryController.class);

	@Autowired
	public MemberBankCardService memberBankCardService;

	@RequestMapping(value = "/main")
	public ModelAndView main() {
		return forword("/customer/bankcard/querymain");
	}

	@RequestMapping(value = "/list/{pageNo}")
	@ResponseBody
	public ModelAndView searchBankList(@ModelAttribute MemberBankCardInfoCnd memberBankCardInfoCnd, @PathVariable("pageNo") int pageNo) {

		try {
			Page page = memberBankCardService.queryMemberBankCardListWithPage(memberBankCardInfoCnd, 10, pageNo);
			return forword("/customer/bankcard/querylist").addObject("page", page);
		} catch (Exception e) {
			logger.error("searchBankList出错", e);
		}
		return null;
	}

	@RequestMapping(value = "/exportData")
	public void exportData() {
		// FSTASK 导出数据

	}


}