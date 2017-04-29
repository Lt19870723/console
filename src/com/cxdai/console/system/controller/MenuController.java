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
import com.cxdai.console.system.entity.Menu;
import com.cxdai.console.system.service.MenuService;
import com.cxdai.console.system.vo.MenuCnd;
import com.cxdai.console.system.vo.MenuVo;

/**
 * <p>
 * Description:系统管理-菜单管理<br />
 * </p>
 * 
 * @title MenuController.java
 * @package com.cxdai.console.system.controller
 * @author qiongbiao.zhang
 * @version 0.1 2015年3月2日
 */
@Controller
@RequestMapping(value = "/system/menu")
public class MenuController extends BaseController {
	private static final Logger logger = Logger.getLogger(MenuController.class);

	@Autowired
	private MenuService menuService;

	@RequestMapping(value = "/main")
	public ModelAndView main() {
		return forword("/system/menu/main");
	}

	@RequestMapping(value = "/list/{pageNo}")
	public ModelAndView list(@ModelAttribute MenuCnd menuCnd, @PathVariable("pageNo") Integer pageNo) {
		Page page = menuService.selectMenuPage(menuCnd, new Page(pageNo, 10));
		return forword("/system/menu/list").addObject("page", page);
	}

	@RequestMapping(value = "/edit")
	public ModelAndView edit(@RequestParam(value = "menuId", required = false) Integer menuId) {
		ModelAndView mv = forword("/system/menu/edit");
		
		List<Menu> firstMenus = menuService.selectByPid(0);
		mv.addObject("firstMenus", firstMenus);

		if (menuId != null) {
			MenuVo menu = menuService.selectById(menuId);
			mv.addObject("menu", menu);

			List<Menu> secondMenus = menuService.selectByPid(menu.getFristMenuId());
			mv.addObject("secondMenus", secondMenus);
		}

		return mv;
	}

	@RequestMapping(value = "/children/{pid}")
	@ResponseBody
	public List<Menu> children(@PathVariable("pid") Integer pid) {
		return menuService.selectByPid(pid);
	}

	@RequestMapping(value = "/save")
	@ResponseBody
	public MessageBox save(@ModelAttribute Menu menu) {
		if (StringUtils.isEmpty(menu.getName())) {
			return new MessageBox("1", "菜单名不能为空");
		}
		try {
			menuService.saveOrupdate(menu);
			return MessageBox.build("0", "保存成功");
		} catch (Exception e) {
			stackTraceError(logger, e);
			return MessageBox.build("1", "保存失败");
		}
	}

	@RequestMapping(value = "/updateStatus/{menuId}")
	@ResponseBody
	public MessageBox updateStatus(@PathVariable("menuId") Integer menuId) {
		try {
			menuService.updateStatus(menuId);
			return MessageBox.build("0", "操作成功");
		} catch (Exception e) {
			stackTraceError(logger, e);
			return MessageBox.build("1", "操作失败");
		}
	}
}