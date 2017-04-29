package com.cxdai.console.system.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.system.entity.Role;
import com.cxdai.console.system.service.RoleService;
import com.cxdai.console.system.service.UserService;
import com.cxdai.console.system.vo.RoleCnd;
import com.cxdai.console.system.vo.UserCnd;
import com.cxdai.console.system.vo.UserVo;

/**
 * <p>
 * Description:系统管理-用户管理<br />
 * </p>
 * 
 * @title UserController.java
 * @package com.cxdai.console.system.controller
 * @author qiongbiao.zhang
 * @version 0.1 2015年3月2日
 */
@Controller
@RequestMapping(value = "/system/user")
public class UserController extends BaseController {
	private static final Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value = "/main")
	public ModelAndView main() {
		List<Role> roles=roleService.selectRoleList(new RoleCnd(0));
		if(null!=roles&&roles.size()>0){
			return forword("/system/user/main").addObject("roles",roles);
		}else{
			return forword("/system/user/main");
		}
	}

	@RequestMapping(value = "/list/{pageNo}")
	public ModelAndView list(@ModelAttribute UserCnd userCnd, @PathVariable("pageNo") Integer pageNo) {
		Page page = userService.selectUserPage(userCnd, new Page(pageNo, 10));
		return forword("/system/user/list").addObject("page", page);
	}

	@RequestMapping(value = "/edit")
	public ModelAndView edit(@RequestParam(value = "userId", required = false) Integer userId) {
		ModelAndView mv = forword("/system/user/edit");
		
		List<Role> roles = roleService.selectAllRoles();
		mv.addObject("roles", roles);
		
		if (userId != null) {
			UserVo user = userService.selectById(userId);
			mv.addObject("user", user);
		}

		return mv;
	}
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public MessageBox save(@ModelAttribute UserVo user) {

		if (StringUtils.isEmpty(user.getUserName())) {
			return new MessageBox("1", "用户名不能为空");
		}
		if (StringUtils.isEmpty(user.getName())) {
			return new MessageBox("1", "员工姓名不能为空");
		}
		if (user.getRoleId() == null) {
			return new MessageBox("1", "角色不能为空");
		}
		
		try {
			userService.saveOrUpdate(user);
			return MessageBox.build("0", "保存成功");
		} catch (Exception e) {
			stackTraceError(logger, e);
			return MessageBox.build("1", "保存失败");
		}
	}
	
	@RequestMapping(value = "/updateStatus/{userId}")
	@ResponseBody
	public MessageBox updateStatus(@PathVariable("userId") Integer userId) {
		try {
			userService.updateStatus(userId);
			return MessageBox.build("0", "操作成功");
		} catch (Exception e) {
			stackTraceError(logger, e);
			return MessageBox.build("1", "操作失败");
		}
	}
}
