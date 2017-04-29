package com.cxdai.console.security;

import java.io.Serializable;

/**
 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
 */
public class ShiroUser implements Serializable {
	private static final long serialVersionUID = 4999382582432113545L;

	public ShiroUser() {
	}

	public ShiroUser(Integer userId, String userName) {
		this.userId = userId;
		this.userName = userName;
	}

	private Integer userId;
	private String userName;
	private String name;
	private Integer platform;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

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

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}
}