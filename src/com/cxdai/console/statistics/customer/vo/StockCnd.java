package com.cxdai.console.statistics.customer.vo;

import java.io.Serializable;

import com.cxdai.console.common.page.BaseCnd;

public class StockCnd extends BaseCnd implements Serializable {

	/**
	 * @fields serialVersionUID
	 */

	private static final long serialVersionUID = 6506873656475226297L;

	private String status; // 期权状态
	private String username;// 用户名
	private String realname;// 真实姓名
	private String isReduce;// 是否减仓超九成 0-否 1-是

	public String getStatus() {
		if ("0".equals(status)) {
			status = "";
		}
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

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

	public String getIsReduce() {
		return isReduce;
	}

	public void setIsReduce(String isReduce) {
		this.isReduce = isReduce;
	}

}