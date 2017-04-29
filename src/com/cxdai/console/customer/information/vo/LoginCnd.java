package com.cxdai.console.customer.information.vo;

import java.io.Serializable;

public class LoginCnd implements Serializable {

	private static final long serialVersionUID = 2536233498418360658L;
	private Integer userId;
	private String userName;
	private String ip;
	private String sessionId;
	/** 平台来源 1：官网 2、微信，即用户登录的客户端 */
	private Integer platform;

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}

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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}
