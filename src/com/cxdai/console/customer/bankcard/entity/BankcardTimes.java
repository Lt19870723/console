package com.cxdai.console.customer.bankcard.entity;

import java.io.Serializable;

public class BankcardTimes implements Serializable {

	private static final long serialVersionUID = 1041042801118767257L;

	private Integer id; // 自增ID
	private Integer userId; // 用户ID
	private Integer changeTimes; // 换卡次数
	private Integer applyTimes; // 申请次数
	private Integer clickTimes; // 点击申请次数

	// 关联查询显示
	private String userName;
	private String realName;
	private String bank;
	private String bankcard;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getChangeTimes() {
		return changeTimes;
	}

	public void setChangeTimes(Integer changeTimes) {
		this.changeTimes = changeTimes;
	}

	public Integer getApplyTimes() {
		return applyTimes;
	}

	public void setApplyTimes(Integer applyTimes) {
		this.applyTimes = applyTimes;
	}

	public Integer getClickTimes() {
		return clickTimes;
	}

	public void setClickTimes(Integer clickTimes) {
		this.clickTimes = clickTimes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

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

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBankcard() {
		return bankcard;
	}

	public void setBankcard(String bankcard) {
		this.bankcard = bankcard;
	}

}
