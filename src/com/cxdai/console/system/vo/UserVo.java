package com.cxdai.console.system.vo;

import com.cxdai.console.system.entity.User;

public class UserVo extends User {
	private static final long serialVersionUID = -1921459968303002388L;

	private Integer roleId;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
}