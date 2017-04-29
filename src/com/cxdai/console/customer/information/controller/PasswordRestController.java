package com.cxdai.console.customer.information.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
import com.cxdai.console.common.ELTag;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.information.service.MemberService;
import com.cxdai.console.customer.information.vo.MemberCnd;

/**
 * <p>
 * Description:用户管理-密码重置<br />
 * </p>
 * 
 * @title PasswordRestController.java
 * @package com.cxdai.console.customer.information.controller
 * @author hujianpan
 * @version 0.1 2015年3月16日
 */
@Controller
@RequestMapping(value = "/customer/managerPassword")
public class PasswordRestController extends BaseController {

	private final static Logger logger = Logger.getLogger(PasswordRestController.class);
	@Autowired
	private MemberService memberService;

	/**
	 * <p>
	 * Description:密码管理<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2015年3月13日
	 * @param pageName
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/{pageName}")
	public ModelAndView managerPassword(@PathVariable String pageName) {
		return new ModelAndView("/customer/information/managerpassword/" + pageName);
	}

	/**
	 * <p>
	 * Description:更新借款用户密码<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2015年3月13日 void
	 */
	@RequestMapping("/updateBorrowsPWD")
	@ResponseBody
	public MessageBox updateBorrowsPWD(HttpServletRequest request) {
		String msg;
		try {
			String newPassword = request.getParameter("newPassword");
			String againPassword = request.getParameter("againPassword");
			if (StringUtils.isEmpty(newPassword) || StringUtils.isEmpty(againPassword)) {
				msg = "密码不能为空";
				return new MessageBox("1", msg);
			}
			if (newPassword.trim().compareTo(againPassword.trim()) != 0) {
				msg = "两次密码输入不一致";
				return new MessageBox("1", msg);
			}
			memberService.updateBorrowMemberPassword(newPassword);
			msg = "密码修改成功";
		} catch (Exception e) {
			msg = "密码修改失败";
			return new MessageBox("1", msg);
		}
		return new MessageBox("0", msg);
	}

	/**
	 * <p>
	 * Description:客户信息 - 查询<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2015年3月11日
	 * @param memberCnd
	 * @param pageNo
	 * @return ModelAndView
	 */
	@RequestMapping("/query/{pageNo}")
	public ModelAndView queryTenderer(@ModelAttribute MemberCnd memberCnd, @PathVariable("pageNo") Integer pageNo) {
		ModelAndView mv = new ModelAndView("/customer/information/managerpassword/tendererlist");
		Page page = null;
		try {
			Integer pageSize = Constants.CONSOLE_PAGE_SIZE2;
			page = memberService.queryMemberVoListForPages(memberCnd, pageNo, pageSize);
		} catch (Exception e) {
			logger.error(e);
		}
		return mv.addObject("page", page).addObject("memberCnd", memberCnd);
	}

	/**
	 * <p>
	 * Description:初始化重置密码界面<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2015年3月13日
	 * @param passwordType
	 * @param username
	 * @return ModelAndView
	 */
	@RequestMapping("/initEditPage/{passwordType}/{username}")
	public ModelAndView initEditPage(@PathVariable String passwordType, @PathVariable String username) {
		ModelAndView mv = new ModelAndView("/customer/information/managerpassword/editlayer");
		final String decode = ELTag.decode(username);
		return mv.addObject("passwordType", passwordType).addObject("username", decode);
	}

	/**
	 * <p>
	 * Description:重置登录密码<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2015年3月13日
	 * @return MessageBox
	 */
	@RequestMapping(value = "resetLoginPwd")
	@ResponseBody
	public MessageBox resetLoginPwd(@ModelAttribute MemberCnd memberCnd) {
		String msg;
		if (StringUtils.isEmpty(memberCnd.getUsername())) {
			return new MessageBox("1", "用户名不能为空！");
		}
		if (StringUtils.isEmpty(memberCnd.getNewLoginPassWord())) {
			return new MessageBox("1", "新密码不能为空！");
		}
		try {
			memberService.updateLoginPwd(memberCnd.getUsername(), memberCnd.getNewLoginPassWord());
			msg = "登录密码重置成功!!";
		} catch (Exception e) {
			logger.error("resetLoginPwd", e);
			msg = "登录密码重置失败!!";
			return new MessageBox("1", msg);
		}
		return new MessageBox("0", msg);
	}

	/**
	 * <p>
	 * Description:重置交易密码<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2015年3月13日
	 * @return MessageBox
	 */
	@RequestMapping(value = "resetDealPwd")
	@ResponseBody
	public MessageBox resetDealPwd(@ModelAttribute MemberCnd memberCnd) {
		String msg;
		if (StringUtils.isEmpty(memberCnd.getUsername())) {
			return new MessageBox("1", "用户名不能为空！");
		}
		if (StringUtils.isEmpty(memberCnd.getNewDealPassWord())) {
			return new MessageBox("1", "新密码不能为空！");
		}
		try {
			memberService.updateDealPwd(memberCnd.getUsername(), memberCnd.getNewDealPassWord());
			msg = "交易密码重置成功!!";
		} catch (Exception e) {
			logger.error("resetDealPwd", e);
			msg = "交易密码重置失败!!";
			return new MessageBox("1", msg);
		}
		return new MessageBox("0", msg);
	}
}
