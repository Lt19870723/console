package com.cxdai.console.customer.feedback.controller;

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
import com.cxdai.console.customer.feedback.service.FeedbackInfoService;
import com.cxdai.console.customer.feedback.vo.FeedbackInfoCnd;
import com.cxdai.console.customer.feedback.vo.FeedbackInfoVo;
import com.cxdai.console.system.vo.UserVo;

/**
 * <p>
 * Description:客户信息controller<br />
 * </p>
 * 
 * @title MemberController.java
 * @package com.cxdai.console.customer.information.controller
 * @author hujianpan
 * @version 0.1 2015年3月11日
 */
@Controller
@RequestMapping(value = "/customer/feedback")
public class FeedbackController extends BaseController {

	private final static Logger logger = Logger.getLogger(FeedbackController.class);

	@Autowired
	private FeedbackInfoService feedbackInfoService;

	/**
	 * <p>
	 * Description:反馈、回访处理主界面<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2015年3月12日
	 * @return ModelAndView
	 */
	@RequestMapping("/main")
	public ModelAndView feedbackIndex() {
		return new ModelAndView("/customer/feedback/index");
	}

	@RequestMapping(value = "/query/{pageNo}")
	public ModelAndView queryFeedbackList(@ModelAttribute FeedbackInfoCnd feedbackInfoCnd, @PathVariable Integer pageNo) {
		Page page = null;
		try {
			feedbackInfoCnd.setStaffId(currentUser().getUserId());
			page = feedbackInfoService.queryListForPage(feedbackInfoCnd, Constants.CONSOLE_PAGE_SIZE, pageNo);
		} catch (Exception e) {
			logger.error(e);
		}
		return new ModelAndView("/customer/feedback/list").addObject("page", page).addObject("feedbackInfoCnd", feedbackInfoCnd);
	}

	@RequestMapping(value = "/showFeedbackLayer/{id}")
	public ModelAndView showFeedbackLayer(@PathVariable Integer id) {
		return new ModelAndView("/customer/feedback/layer").addObject("id", id);
	}

	@RequestMapping(value = "/showFeedbackDetail/{id}")
	public ModelAndView showFeedbackDetail(@PathVariable Integer id) {
		FeedbackInfoVo feedbackInfoVo = new FeedbackInfoVo();
		try {
			feedbackInfoVo.setId(id);
			if (feedbackInfoVo.getId() > 0) {
				feedbackInfoVo = feedbackInfoService.queryById(feedbackInfoVo.getId());
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return new ModelAndView("/customer/feedback/detail").addObject("feedbackInfoVo", feedbackInfoVo);
	}

	@RequestMapping(value = "/approvePass")
	@ResponseBody
	public MessageBox approvePass(@ModelAttribute FeedbackInfoVo feedbackInfoVo) {
		String resultMsg = "success";
		try {
			UserVo userVo = new UserVo();
			userVo.setId(currentUser().getUserId());
			resultMsg = feedbackInfoService.saveApprovePass(feedbackInfoVo, userVo);
		} catch (Exception e) {
			resultMsg = "网络异常，请刷新页面或稍后重试！";
			logger.error(e);
			return new MessageBox("1", resultMsg);
		}
		return new MessageBox("0", "处理成功！");
	}

	@RequestMapping(value = "/approveReject")
	@ResponseBody
	public MessageBox approveReject(@ModelAttribute FeedbackInfoVo feedbackInfoVo) {
		String resultMsg = "success";
		try {
			UserVo userVo = new UserVo();
			userVo.setId(currentUser().getUserId());
			resultMsg = feedbackInfoService.saveApproveReject(feedbackInfoVo, userVo);
		} catch (Exception e) {
			resultMsg = "网络异常，请刷新页面或稍后重试！";
			return new MessageBox("1", resultMsg);
		}
		return new MessageBox("0", "处理成功！");
	}

}
