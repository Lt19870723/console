package com.cxdai.console.system.vo;

import com.cxdai.console.common.page.BaseCnd;

public class RoleCnd extends BaseCnd {
	private static final long serialVersionUID = -8322856145039361549L;
	
	public RoleCnd() {
	}

	public RoleCnd(Integer status) {
		this.status = status;
	}
	
	private String name; // 名称
	private Integer status; // 状态(0:启用,1:禁用)

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}