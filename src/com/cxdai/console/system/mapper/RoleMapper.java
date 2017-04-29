package com.cxdai.console.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.system.entity.Role;
import com.cxdai.console.system.vo.RoleCnd;
import com.cxdai.console.system.vo.RoleVo;

public interface RoleMapper {
	
	public int countRoleList(RoleCnd roleCnd);
	public List<Role> selectRoleList(RoleCnd roleCnd, Page page);
	public List<Role> selectRoleList(RoleCnd roleCnd);
	public RoleVo selectById(Integer roleId);

	public int insert(Role Role);
	public int insertRolePermission(@Param("roleId") Integer roleId, @Param("permissionIds") List<Integer> permissionIds);
	public int update(Role Role);
	
	public int updateStatus(Integer roleId);
	public int deleteUserPermissionByRoleId(Integer roleId);
	public int deleteRolePermissionByRoleId(Integer roleId);
	public int deleteUserRoleByRoleId(Integer roleId);
}