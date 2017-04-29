package com.cxdai.console.index.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.index.vo.UserMenuInfo;
import com.cxdai.console.system.entity.Menu;
import com.cxdai.console.system.service.UserService;

@Controller
public class MainController extends BaseController {

	@Autowired
	private UserService userService;

	@RequestMapping("/main")
	public ModelAndView main() {
		UserMenuInfo userMenuInfo = userService.queryMenusByUserId(currentUser().getUserId());
		List<Menu> topMenus = userMenuInfo.getTopMenus();
		return forword("/main").addObject("topMenus", topMenus);
	}

	@RequestMapping("/main/menu-{pid}")
	public ModelAndView queryLeftMenus(@PathVariable("pid") Integer pid) {
		UserMenuInfo userMenuInfo = userService.queryMenusByUserId(currentUser().getUserId());
		Map<Menu, List<Menu>> leftMenus = userMenuInfo.getLeftMenus(pid);
		return forword("/leftmenu").addObject("leftMenus", leftMenus);
	}
}
