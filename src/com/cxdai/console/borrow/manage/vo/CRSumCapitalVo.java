package com.cxdai.console.borrow.manage.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.MoneyUtil;

public class CRSumCapitalVo {
	private int id;
	private int status;
	private Date successTime;
	private int rid;
	
	private int rBorrowId;
	private int order;
	private BigDecimal capital;
	private BigDecimal sumCapital;
	private String name;
	
	private String timeStr;
	private String capitalStr;
	private String sumCapitalStr;
	
	private Integer isCustody;
	
	
	
	public Integer getIsCustody() {
		return isCustody;
	}
	public void setIsCustody(Integer isCustody) {
		this.isCustody = isCustody;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getSuccessTime() {
		return successTime;
	}
	public void setSuccessTime(Date successTime) {
		this.successTime = successTime;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getrBorrowId() {
		return rBorrowId;
	}
	public void setrBorrowId(int rBorrowId) {
		this.rBorrowId = rBorrowId;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public BigDecimal getCapital() {
		return capital;
	}
	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}
	public BigDecimal getSumCapital() {
		return sumCapital;
	}
	public void setSumCapital(BigDecimal sumCapital) {
		this.sumCapital = sumCapital;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTimeStr() {
		if (getSuccessTime() != null) {
			return DateUtils.format(getSuccessTime(), DateUtils.YMD_HMS);
		}
		return timeStr;
	}
	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}
	
	public String getCapitalStr() {
		if(getCapital() != null){
			return MoneyUtil.fmtMoneyByDot(getCapital());
		}
		return capitalStr;
	}
	public void setCapitalStr(String capitalStr) {
		this.capitalStr = capitalStr;
	}
	
	public String getSumCapitalStr() {
		if(getSumCapital() != null){
			return MoneyUtil.fmtMoneyByDot(getSumCapital());
		}
		return sumCapitalStr;
	}
	public void setSumCapitalStr(String sumCapitalStr) {
		this.sumCapitalStr = sumCapitalStr;
	}
	
	
	
}
