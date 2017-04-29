package com.cxdai.console.system.controller;

import java.util.List;

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
import com.cxdai.console.system.entity.Permission;
import com.cxdai.console.system.entity.Role;
import com.cxdai.console.system.service.PermissionService;
import com.cxdai.console.system.service.RoleService;
import com.cxdai.console.system.vo.RoleCnd;
import com.cxdai.console.system.vo.RoleVo;

/**
 * <p>
 * Description:系统管理-角色管理<br />
 * </p>
 * 
 * @title RoleController.java
 * @package com.cxdai.console.system.controller
 * @author hujianpan
 * @version 0.1 2015年3月3日
 */
@Controller
@RequestMapping(value = "/system/role")
public class RoleController extends BaseController {
	private static final Logger logger = Logger.getLogger(RoleController.class);

	@Autowired
	private RoleService roleService;
	@Autowired
	private PermissionService permissionService;
	
	@RequestMapping(value = "/main")
	public ModelAndView main() {
		return forword("/system/role/main");
	}

	@RequestMapping(value = "/list/{pageNo}")
	public ModelAndView list(@ModelAttribute RoleCnd roleCnd, @PathVariable("pageNo") Integer pageNo) {
		Page page = roleService.selectRolePage(roleCnd, new Page(pageNo, 10));
		return forword("/system/role/list").addObject("page", page);
	}

	@RequestMapping(value = "/edit")
	public ModelAndView edit(@RequestParam(value = "roleId", required = false) Integer roleId) {
		ModelAndView mv = forword("/system/role/edit");
		
		List<Permission> permissions = permissionService.selectAllPermissions();
		mv.addObject("permissions", permissions);
		
		if (roleId != null) {
			RoleVo role = roleService.selectById(roleId);
			mv.addObject("role", role);
		}

		return mv;
	}
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public MessageBox save(
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "permissionIds[]") Integer[] permissionIds,
			@RequestParam(value = "desc", required = false) String desc) {
		
		if (StringUtils.isEmpty(name)) {
			return new MessageBox("1", "角色名不能为空");
		}
		if (ArrayUtils.isEmpty(permissionIds)) {
			return new MessageBox("1", "权限不能为空");
		}
		
		Role role = new Role();
		role.setId(id);
		role.setName(name);
		role.setDesc(desc);
		try {
			roleService.saveOrUpdate(role, permissionIds);
			return MessageBox.build("0", "保存成功");
		} catch (Exception e) {
			stackTraceError(logger, e);
			return MessageBox.build("1", "保存失败");
		}
	}
	
	@RequestMapping(value = "/updateStatus/{roleId}")
	@ResponseBody
	public MessageBox updateStatus(@PathVariable("roleId") Integer roleId) {
		try {
			roleService.updateStatus(roleId);
			return MessageBox.build("0", "操作成功");
		} catch (Exception e) {
			stackTraceError(logger, e);
			return MessageBox.build("1", "操作失败");
		}
	}
}