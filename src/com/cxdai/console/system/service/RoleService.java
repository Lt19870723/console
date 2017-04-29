package com.cxdai.console.system.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.system.entity.Role;
import com.cxdai.console.system.mapper.RoleMapper;
import com.cxdai.console.system.vo.RoleCnd;
import com.cxdai.console.system.vo.RoleVo;

@Service
@Transactional(rollbackFor = Throwable.class)
public class RoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public List<Role> selectAllRoles() {
		return roleMapper.selectRoleList(new RoleCnd(0), Page.DEFAULT);
	}
	
	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public Page selectRolePage(RoleCnd roleCnd, Page page) {
		page.setTotalCount(roleMapper.countRoleList(roleCnd));
		if (page.getTotalPage() >= page.getPageNo()) {
			page.setResult(roleMapper.selectRoleList(roleCnd, page));
		}
		return page;
	}

	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public RoleVo selectById(Integer roleId) {
		return roleMapper.selectById(roleId);
	}

	@CacheEvict(value = "userMenuCache", allEntries = true)
	public void saveOrUpdate(Role role, Integer[] permissionIds) throws Exception {
		if (role.getId() == null) {
			role.setStatus(0);
			roleMapper.insert(role);
		} else {
			roleMapper.update(role);
			roleMapper.deleteRolePermissionByRoleId(role.getId());
		}
		roleMapper.insertRolePermission(role.getId(), Arrays.asList(permissionIds));
	}

	@CacheEvict(value = "userMenuCache", allEntries = true)
	public void updateStatus(Integer roleId) throws Exception {
		// roleMapper.deleteUserPermissionByRoleId(roleId);
		// roleMapper.deleteRolePermissionByRoleId(roleId);
		// roleMapper.deleteUserRoleByRoleId(roleId);
		roleMapper.updateStatus(roleId);
	}
	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public List<Role> selectRoleList(RoleCnd roleCnd) {
		return roleMapper.selectRoleList(roleCnd);
	}
}
