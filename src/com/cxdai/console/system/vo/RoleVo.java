package com.cxdai.console.system.vo;

import com.cxdai.console.system.entity.Role;

public class RoleVo extends Role {
	private static final long serialVersionUID = -1921459968303002388L;

	private String permissionIds;

	public String getPermissionIds() {
		return permissionIds;
	}

	public void setPermissionIds(String permissionIds) {
		this.permissionIds = permissionIds;
	}
}