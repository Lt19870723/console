package com.cxdai.console.index.controller;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.system.service.UserService;
import com.cxdai.console.system.vo.UserCnd;
import com.cxdai.console.system.vo.UserVo;
import com.cxdai.console.util.MD5;

@Controller
public class LoginController extends BaseController {
	private static final Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login")
	public ModelAndView login() {
		return forword("/login");
	}

	@RequestMapping(value = "/onLogin")
	@ResponseBody
	public MessageBox onLogin(
			@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password) {
		try {
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(username, MD5.toMD5(password));
			subject.login(token);
			return MessageBox.build("0", "");
		} catch (Exception e) {
			stackTraceError(logger, e);
			return MessageBox.build("1", "用户名或密码错误.");
		}
	}

	@RequestMapping(value = "/logout")
	@ResponseBody
	public MessageBox logout() {
		SecurityUtils.getSubject().logout();
		return MessageBox.build("0", "成功退出");
	}
	
	@RequestMapping(value = "/changePwd")
	public ModelAndView changePwd() {
		return forword("/changePwd");
	}
	
	@RequestMapping(value = "/changePwd/save")
	@ResponseBody
	public MessageBox saveChangePwd(@ModelAttribute UserCnd userCnd) {
		try {

			UserVo userVo = userService.selectById(currentUser().getUserId());
			if (!userVo.getPassword().equals(MD5.toMD5(userCnd.getOldPassword()))) {
				return MessageBox.build("1", "旧密码输入错误");
			}

			userCnd.setUserId(currentUser().getUserId());
			userCnd.setPassword(MD5.toMD5(userCnd.getPassword()));
			userService.changePwd(userCnd);
			SecurityUtils.getSubject().logout();
			return MessageBox.build("0", "密码修改成功，请重新登录！");
		} catch (Exception e) {
			stackTraceError(logger, e);
			return MessageBox.build("1", "密码修改失败");
		}
	}
}
