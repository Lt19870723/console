package com.cxdai.console.customer.bankcard.entity;

import java.io.Serializable;
import java.util.Date;

public class BankcardClickCnd implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer userId;// 用户ID
	private Date beginTime;// 添加时间
	private Date endTime;// 添加时间

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
