package com.cxdai.console.system.vo;

import com.cxdai.console.common.page.BaseCnd;

public class UserCnd extends BaseCnd {
	private static final long serialVersionUID = -8322856145039361549L;
	
	public UserCnd() {
	}

	public UserCnd(String status) {
		this.status = status;
	}

	private Integer userId; // 用户ID
	private String userName; // 用户名
	private String name; // 姓名
	private String status; // 状态(0:启用,1:禁用)
	private String password; // 密码
	private String oldPassword; // 旧密码
	private String roleName; // 角色
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}