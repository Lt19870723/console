package com.cxdai.console.customer.contact.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.contact.service.ContactInfoService;
import com.cxdai.console.customer.feedback.vo.FeedbackInfoCnd;
import com.cxdai.console.customer.feedback.vo.FeedbackInfoVo;
import com.cxdai.console.system.vo.UserVo;

/**
 * <p>
 * Description:客户管理-我要联系-确认联系<br />
 * </p>
 * 
 * @title ContactController.java
 * @package com.cxdai.console.customer.contact.controller
 * @author hujianpan
 * @version 0.1 2015年3月16日
 */
@Controller
@RequestMapping(value = "/customer/contact")
public class ContactController extends BaseController {

	private final static Logger logger = Logger.getLogger(ContactController.class);

	@Autowired
	private ContactInfoService contactInfoService;

	/**
	 * <p>
	 * Description:客户管理 - 我要联系主界面<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2015年3月12日
	 * @return ModelAndView
	 */
	@RequestMapping("/main")
	public ModelAndView contactIndex() {
		return new ModelAndView("/customer/contact/index");
	}

	/**
	 * <p>
	 * Description:查询待处理的匿名提交联系方式的用户<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2015年3月12日
	 * @param feedbackInfoCnd
	 * @param pageNo
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/query/{pageNo}")
	public ModelAndView queryWaitContactList(@ModelAttribute FeedbackInfoCnd feedbackInfoCnd, @PathVariable Integer pageNo) {
		Page page = null;
		try {
			page = contactInfoService.queryListForPage(feedbackInfoCnd, Constants.CONSOLE_PAGE_SIZE, pageNo);
		} catch (Exception e) {
			logger.error(e);
		}
		return new ModelAndView("/customer/contact/list").addObject("page", page).addObject("feedbackInfoCnd", feedbackInfoCnd);

	}

	/**
	 * <p>
	 * Description:已联系后处理<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2015年3月12日
	 * @param id
	 * @return MessageBox
	 */
	@RequestMapping(value = "/contacted/{id}")
	@ResponseBody
	public MessageBox contacted(@PathVariable Integer id) {
		String resultMsg = null;
		FeedbackInfoVo feedbackInfoVo = new FeedbackInfoVo();
		try {
			UserVo user = new UserVo();
			user.setId(currentUser().getUserId());
			feedbackInfoVo.setId(id);
			resultMsg = contactInfoService.updateAcceptFeedback(feedbackInfoVo, user);
		} catch (Exception e) {
			resultMsg = "网络异常，请刷新页面或稍后重试！";
			logger.error(e);
			return new MessageBox("1", resultMsg);
		}
		if (!"success".equals(resultMsg)) {
			return new MessageBox("1", resultMsg);
		}
		return new MessageBox("0", "处理成功！");
	}

}
