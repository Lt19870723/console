package com.cxdai.console.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.system.entity.Permission;
import com.cxdai.console.system.vo.PermissionCnd;
import com.cxdai.console.system.vo.PermissionVo;

public interface PermissionMapper {
	
	public int countPermissionList(PermissionCnd permissionCnd);
	public List<Permission> selectPermissionList(PermissionCnd permissionCnd, Page page);

	public PermissionVo selectById(Integer permissionId);

	public int insert(Permission permission);
	public int insertPermissionResources(@Param("permissionId") Integer permissionId, @Param("resourcesIds") List<Integer> resourcesIds);
	public int update(Permission permission);
	
	public int updateStatus(Integer permissionId);
	public int deletePermissionResourcesByPermissionId(Integer permissionId);
	public int deleteRolePermissionByPermissionId(Integer permissionId);
	public int deleteUserPermissionByPermissionId(Integer permissionId);
}