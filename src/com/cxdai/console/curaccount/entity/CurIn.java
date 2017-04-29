package com.cxdai.console.curaccount.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CurIn {
	private Integer id;

	private Integer userId;

	private Integer outId;

	private BigDecimal account;

	private BigDecimal drawMoney;

	private BigDecimal noDrawMoney;

	private Integer curNoworkdayId;

	private Date calInterestDay;

	private BigDecimal actualMoney;

	private BigDecimal actualDrawMoney;

	private BigDecimal actualNoDrawMoney;

	private Integer tenderType;

	private Integer adduser;

	private Date addtime;

	private String addip;

	private Integer status;

	private Integer platform;

	private String remark;

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

	public Integer getOutId() {
		return outId;
	}

	public void setOutId(Integer outId) {
		this.outId = outId;
	}

	public BigDecimal getAccount() {
		return account;
	}

	public void setAccount(BigDecimal account) {
		this.account = account;
	}

	public BigDecimal getDrawMoney() {
		return drawMoney;
	}

	public void setDrawMoney(BigDecimal drawMoney) {
		this.drawMoney = drawMoney;
	}

	public BigDecimal getNoDrawMoney() {
		return noDrawMoney;
	}

	public void setNoDrawMoney(BigDecimal noDrawMoney) {
		this.noDrawMoney = noDrawMoney;
	}

	public Integer getCurNoworkdayId() {
		return curNoworkdayId;
	}

	public void setCurNoworkdayId(Integer curNoworkdayId) {
		this.curNoworkdayId = curNoworkdayId;
	}

	public Date getCalInterestDay() {
		return calInterestDay;
	}

	public void setCalInterestDay(Date calInterestDay) {
		this.calInterestDay = calInterestDay;
	}

	public BigDecimal getActualMoney() {
		return actualMoney;
	}

	public void setActualMoney(BigDecimal actualMoney) {
		this.actualMoney = actualMoney;
	}

	public Integer getTenderType() {
		return tenderType;
	}

	public void setTenderType(Integer tenderType) {
		this.tenderType = tenderType;
	}

	public Integer getAdduser() {
		return adduser;
	}

	public void setAdduser(Integer adduser) {
		this.adduser = adduser;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getAddip() {
		return addip;
	}

	public void setAddip(String addip) {
		this.addip = addip;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CurIn [id=" + id + ", userId=" + userId + ", outId=" + outId + ", account=" + account + ", drawMoney=" + drawMoney + ", noDrawMoney="
				+ noDrawMoney + ", curNoworkdayId=" + curNoworkdayId + ", calInterestDay=" + calInterestDay + ", actualMoney=" + actualMoney
				+ ", tenderType=" + tenderType + ", adduser=" + adduser + ", addtime=" + addtime + ", addip=" + addip + ", status=" + status
				+ ", platform=" + platform + ", remark=" + remark + "]";
	}

	public BigDecimal getActualDrawMoney() {
		return actualDrawMoney;
	}

	public void setActualDrawMoney(BigDecimal actualDrawMoney) {
		this.actualDrawMoney = actualDrawMoney;
	}

	public BigDecimal getActualNoDrawMoney() {
		return actualNoDrawMoney;
	}

	public void setActualNoDrawMoney(BigDecimal actualNoDrawMoney) {
		this.actualNoDrawMoney = actualNoDrawMoney;
	}
}