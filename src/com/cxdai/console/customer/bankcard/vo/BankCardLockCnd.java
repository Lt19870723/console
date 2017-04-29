package com.cxdai.console.customer.bankcard.vo;

import java.math.BigDecimal;

/**
 * 
 * <p>
 * Description:银行卡锁定查询参数类<br />
 * </p>
 * 
 * @author 陈鹏
 * @version 0.1 2014年12月18日
 */
public class BankCardLockCnd {

	//用户名；
	private String username;
	
	//是否锁定；
	private Integer lockStatus;
	
	//用户资产总额1
	private BigDecimal totalStart;
	
	//用户资产总额2
	private BigDecimal totalEnd;
	//绑定时间
	private String createTimeStart;
	private String createTimeEnd;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getLockStatus() {
		return lockStatus;
	}

	public void setLockStatus(Integer lockStatus) {
		this.lockStatus = lockStatus;
	}

	public BigDecimal getTotalStart() {
		return totalStart;
	}

	public void setTotalStart(BigDecimal totalStart) {
		this.totalStart = totalStart;
	}

	public BigDecimal getTotalEnd() {
		return totalEnd;
	}

	public void setTotalEnd(BigDecimal totalEnd) {
		this.totalEnd = totalEnd;
	}

	public String getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(String createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public String getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}
}
