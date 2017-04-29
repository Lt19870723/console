package com.cxdai.console.sycee.entity;

import java.io.Serializable;

import com.cxdai.console.common.page.BaseCnd;

public class MemberAccumulatePointsCnd extends BaseCnd implements Serializable {

	private static final long serialVersionUID = 2781175311077936255L;

	private String username;// 用户名
	private String realname;// 真实姓名
	private Integer type;// 类型
	private String addtimeBegin;// 元宝交易时间-开始
	private String addtimeEnd;// 元宝交易时间-结束

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getAddtimeBegin() {
		return addtimeBegin;
	}

	public void setAddtimeBegin(String addtimeBegin) {
		this.addtimeBegin = addtimeBegin;
	}

	public String getAddtimeEnd() {
		return addtimeEnd;
	}

	public void setAddtimeEnd(String addtimeEnd) {
		this.addtimeEnd = addtimeEnd;
	}

}
