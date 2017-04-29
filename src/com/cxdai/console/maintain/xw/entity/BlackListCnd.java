package com.cxdai.console.maintain.xw.entity;

import java.io.Serializable;

import com.cxdai.console.common.page.BaseCnd;

public class BlackListCnd extends BaseCnd implements Serializable {
	private static final long serialVersionUID = -6504677530636449459L;
	/** id */
	private Integer id;
	/** 用户名 */
	private String username;

	/** 类型 */
	private Integer type;

	/** 状态 */
	private Integer status;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
