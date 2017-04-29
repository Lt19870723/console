package com.cxdai.console.system.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.system.entity.Permission;
import com.cxdai.console.system.mapper.PermissionMapper;
import com.cxdai.console.system.vo.PermissionCnd;
import com.cxdai.console.system.vo.PermissionVo;

@Service
@Transactional(rollbackFor = Throwable.class)
public class PermissionService {

	@Autowired
	private PermissionMapper permissionMapper;

	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public List<Permission> selectAllPermissions() {
		return permissionMapper.selectPermissionList(new PermissionCnd(0), Page.DEFAULT);
	}
	
	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public Page selectPermissionPage(PermissionCnd permissionCnd, Page page) {
		page.setTotalCount(permissionMapper.countPermissionList(permissionCnd));
		if (page.getTotalPage() >= page.getPageNo()) {
			page.setResult(permissionMapper.selectPermissionList(permissionCnd, page));
		}
		return page;
	}

	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public PermissionVo selectById(Integer permissionId) {
		return permissionMapper.selectById(permissionId);
	}

	@CacheEvict(value = "userMenuCache", allEntries = true)
	public void saveOrUpdate(Permission permission, Integer[] resourcesIds) throws Exception {
		if (permission.getId() == null) {
			permission.setStatus(0);
			permissionMapper.insert(permission);
		} else {
			permissionMapper.update(permission);
			permissionMapper.deletePermissionResourcesByPermissionId(permission.getId());
		}
		permissionMapper.insertPermissionResources(permission.getId(), Arrays.asList(resourcesIds));
	}

	@CacheEvict(value = "userMenuCache", allEntries = true)
	public void updateStatus(Integer permissionId) throws Exception {
		permissionMapper.updateStatus(permissionId);
		// permissionMapper.deleteRolePermissionByPermissionId(permissionId);
		// permissionMapper.deleteUserPermissionByPermissionId(permissionId);
	}
}
