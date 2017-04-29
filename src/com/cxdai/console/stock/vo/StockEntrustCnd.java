package com.cxdai.console.stock.vo;

import java.io.Serializable;
import java.math.BigDecimal;


public class StockEntrustCnd implements Serializable{
	private static final long serialVersionUID = -8005802218725609268L;
	private Integer userId;
	private Integer entrustType;//委托类型  1: 认购 2：转让
	private Integer sort;//排序规则  1：金额从大到小  2：金额从小到大  3：时间倒叙  4： 时间升序
	private Integer status;//委托状态
	private String startDate;//申请时间起
	private String endDate;//申请时间止
	private Integer isLocked; //不为空时候，查询锁定
	private Integer id; //委托单ID
	private String updateIp;
	private BigDecimal price;//认购转让单价
	private String isResidue;
	private String addIp;//认购转让单价
	

	public String getAddIp() {
		return addIp;
	}

	public void setAddIp(String addIp) {
		this.addIp = addIp;
	}

	public String getIsResidue() {
		return isResidue;
	}

	public void setIsResidue(String isResidue) {
		this.isResidue = isResidue;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getUpdateIp() {
		return updateIp;
	}

	public void setUpdateIp(String updateIp) {
		this.updateIp = updateIp;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Integer isLocked) {
		this.isLocked = isLocked;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getEntrustType() {
		return entrustType;
	}

	public void setEntrustType(Integer entrustType) {
		this.entrustType = entrustType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}