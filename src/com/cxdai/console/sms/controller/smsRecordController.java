package com.cxdai.console.sms.controller;

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
import com.cxdai.console.customer.information.service.MemberService;
import com.cxdai.console.sms.service.SmsSendService;
import com.cxdai.console.sms.service.SmsTemplateService;
import com.cxdai.console.sms.vo.SmsTemplateCnd;

/**
 * 短信维护-短信发送查询
 * @author liutao
 * @Date 2016-03-23
 */
@Controller
@RequestMapping(value = "/sms/smsRecord")
public class smsRecordController extends BaseController {
	private static final Logger logger = Logger.getLogger(smsRecordController.class);
	@Autowired
	private MemberService memberService;
	@Autowired
	private SmsSendService smsSendService;
	@RequestMapping("/main")
	public ModelAndView forRedRecordMain() {
		ModelAndView modelAndView=new ModelAndView("/sms/smsRecord/main");
		return modelAndView;
	}

	/**
	 * 短信维护-短信发送
	 * @author liutao
	 * @Date 2015-11-11
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView searchSmsRecordVo(@ModelAttribute SmsTemplateCnd smsTemplateCnd, @PathVariable Integer pageNo) {
		Page page = null;
		try {
			page = smsSendService.searchSmsRecordVo(smsTemplateCnd, Constants.CONSOLE_PAGE_SIZE, pageNo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询短信模板信息错误：" + e.getMessage());
		}
		return new ModelAndView("/sms/smsRecord/list").addObject("page", page);
	}
}
