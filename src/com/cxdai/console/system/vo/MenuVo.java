package com.cxdai.console.system.vo;

import com.cxdai.console.system.entity.Menu;

public class MenuVo extends Menu {
	private static final long serialVersionUID = -8029180866110555755L;
	
	private Integer fristMenuId; // 当前菜单所属的一级菜单ID
	private Integer secondMenuId; // 当前菜单所属的二级菜单ID

	public Integer getFristMenuId() {
		return fristMenuId;
	}

	public void setFristMenuId(Integer fristMenuId) {
		this.fristMenuId = fristMenuId;
	}

	public Integer getSecondMenuId() {
		return secondMenuId;
	}

	public void setSecondMenuId(Integer secondMenuId) {
		this.secondMenuId = secondMenuId;
	}
}