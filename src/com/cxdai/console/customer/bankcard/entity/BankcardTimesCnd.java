package com.cxdai.console.customer.bankcard.entity;

import java.io.Serializable;

import com.cxdai.console.common.page.BaseCnd;

public class BankcardTimesCnd extends BaseCnd implements Serializable {
	private static final long serialVersionUID = 1L;

	private String userName;
	private String realName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
