package com.cxdai.console.borrow.manage.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RepaymentrecordlogVo implements Serializable {
	/**
	 * @fields serialVersionUID
	 */

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer repaymentrecordid;
	private Integer borrowId;
	private String borrowName;
	private String borrowUserName;
	private String apr;
	private Date addtime;
	private Date repaymentTime;
	private String addip;
	private Integer dealUserId;
	private String dealUserName;

	private BigDecimal repaymentAccount;
	private BigDecimal capital;
	private BigDecimal interest;
	int order;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApr() {
		return apr;
	}

	public void setApr(String apr) {
		this.apr = apr;
	}

	public Date getRepaymentTime() {
		return repaymentTime;
	}

	public void setRepaymentTime(Date repaymentTime) {
		this.repaymentTime = repaymentTime;
	}

	public Integer getRepaymentrecordid() {
		return repaymentrecordid;
	}

	public void setRepaymentrecordid(Integer repaymentrecordid) {
		this.repaymentrecordid = repaymentrecordid;
	}

	public String getDealUserName() {
		return dealUserName;
	}

	public void setDealUserName(String dealUserName) {
		this.dealUserName = dealUserName;
	}

	public Integer getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
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

	public Integer getDealUserId() {
		return dealUserId;
	}

	public void setDealUserId(Integer dealUserId) {
		this.dealUserId = dealUserId;
	}

	public String getBorrowName() {
		return borrowName;
	}

	public void setBorrowName(String borrowName) {
		this.borrowName = borrowName;
	}

	public String getBorrowUserName() {
		return borrowUserName;
	}

	public void setBorrowUserName(String borrowUserName) {
		this.borrowUserName = borrowUserName;
	}

	public BigDecimal getRepaymentAccount() {
		return repaymentAccount;
	}

	public void setRepaymentAccount(BigDecimal repaymentAccount) {
		this.repaymentAccount = repaymentAccount;
	}

	public BigDecimal getCapital() {
		return capital;
	}

	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}


}