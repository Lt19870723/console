package com.cxdai.console.system.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
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
import com.cxdai.console.system.entity.Permission;
import com.cxdai.console.system.service.MenuService;
import com.cxdai.console.system.service.PermissionService;
import com.cxdai.console.system.vo.PermissionCnd;
import com.cxdai.console.system.vo.PermissionVo;

/**
 * <p>
 * Description:系統管理-权限管理<br />
 * </p>
 * 
 * @title PermissionController.java
 * @package com.cxdai.console.system.controller
 * @author hujianpan
 * @version 0.1 2015年3月3日
 */
@Controller
@RequestMapping(value = "/system/permission")
public class PermissionController extends BaseController {
	private static final Logger logger = Logger.getLogger(PermissionController.class);

	@Autowired
	private PermissionService permissionService;
	@Autowired
	private MenuService menuService;
	
	@RequestMapping(value = "/main")
	public ModelAndView main() {
		return forword("/system/permission/main");
	}

	@RequestMapping(value = "/list/{pageNo}")
	public ModelAndView list(@ModelAttribute PermissionCnd permissionCnd, @PathVariable("pageNo") Integer pageNo) {
		Page page = permissionService.selectPermissionPage(permissionCnd, new Page(pageNo, 10));
		return forword("/system/permission/list").addObject("page", page);
	}

	@RequestMapping(value = "/edit")
	public ModelAndView edit(@RequestParam(value = "permissionId", required = false) Integer permissionId) {
		ModelAndView mv = forword("/system/permission/edit");
		
		Map<String, List<Menu>> menuTree = menuService.selectMenuTree();
		mv.addObject("menuTree", menuTree);
		
		if (permissionId != null) {
			PermissionVo permission = permissionService.selectById(permissionId);
			mv.addObject("permission", permission);
		}
		return mv;
	}
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public MessageBox save(
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "resourcesIds[]") Integer[] resourcesIds,
			@RequestParam(value = "desc", required = false) String desc) {
		
		if (StringUtils.isEmpty(name)) {
			return new MessageBox("1", "权限名不能为空");
		}
		if (ArrayUtils.isEmpty(resourcesIds)) {
			return new MessageBox("1", "资源不能为空");
		}
		
		Permission permission = new Permission();
		permission.setId(id);
		permission.setName(name);
		permission.setDesc(desc);
		try {
			permissionService.saveOrUpdate(permission, resourcesIds);
			return MessageBox.build("0", "保存成功");
		} catch (Exception e) {
			stackTraceError(logger, e);
			return MessageBox.build("1", "保存失败");
		}
	}
	
	@RequestMapping(value = "/updateStatus/{permissionId}")
	@ResponseBody
	public MessageBox updateStatus(@PathVariable("permissionId") Integer permissionId) {
		try {
			permissionService.updateStatus(permissionId);
			return MessageBox.build("0", "操作成功");
		} catch (Exception e) {
			stackTraceError(logger, e);
			return MessageBox.build("1", "操作失败");
		}
	}
}
