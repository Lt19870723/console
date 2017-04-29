package com.cxdai.console.customer.bankcard.controller;

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
import com.cxdai.console.customer.bankcard.service.MemberBankCardService;

@Controller
@RequestMapping("/memberBank")
public class MemberBankCardController extends BaseController {

	private static final long serialVersionUID = -6926886776468657950L;

	@Autowired
	public MemberBankCardService memberBankCardService;
	
	@RequestMapping(value="/toMemberBankMain")
	@ResponseBody
	public ModelAndView toMemberBankMain(){
		return forword("/customer/bankcard/memberBankMain");
	}
	/**
	 * 
	 * <p>
	 * Description:查询银行卡分支行列表<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2014年10月10日 void
	 */
	@RequestMapping(value="/serachAll/{pageNo}")
	public ModelAndView searchBankList(@ModelAttribute MemberBankCardInfoCnd memberBankCardInfoCnd,@PathVariable("pageNo") int pageNo) {
		Page page = new Page();
		try {
			page = memberBankCardService.queryMemberBankCardListWithPage(memberBankCardInfoCnd, 10, pageNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forword("/customer/bankcard/memberBankList").addObject("page", page);
	}

}
