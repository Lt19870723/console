package com.cxdai.console.system.vo;

import com.cxdai.console.common.page.BaseCnd;

public class MenuCnd extends BaseCnd {
	private static final long serialVersionUID = 974451604343875422L;

	public MenuCnd() {
	}

	public MenuCnd(Integer status) {
		this.status = status;
	}
	
	private String name; // 名称
	private Integer level; // 菜单层级
	private Integer status; // 状态(0:启用,1:禁用)
	private Integer pid; // 父菜单ID

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
}
