package com.cxdai.console.borrow.manage.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * <p>
 * Description:导出Excel对象<br />
 * </p>
 * 
 * @title BRepaymentRecordCheck.java
 * @package com.cxdai.console.borrow.vo
 * @author yangshijin
 * @version 0.1 2014年10月16日
 */
public class BRepaymentRecordCheck implements Serializable {
	private static final long serialVersionUID = 8526089441333142226L;

	/** 借款标名字 */
	private String borrowName;
	/** 期数 */
	private String periods;
	private String username;
	private String repaymentTimeStr;
	private BigDecimal capital;
	private BigDecimal firstTenderAccount;
	private BigDecimal interest;
	private BigDecimal lateInterest;
	private String statusStr;

	public String getBorrowName() {
		return borrowName;
	}

	public void setBorrowName(String borrowName) {
		this.borrowName = borrowName;
	}

	public String getPeriods() {
		return periods;
	}

	public void setPeriods(String periods) {
		this.periods = periods;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRepaymentTimeStr() {
		return repaymentTimeStr;
	}

	public void setRepaymentTimeStr(String repaymentTimeStr) {
		this.repaymentTimeStr = repaymentTimeStr;
	}

	public BigDecimal getCapital() {
		return capital;
	}

	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}

	public BigDecimal getFirstTenderAccount() {
		return firstTenderAccount;
	}

	public void setFirstTenderAccount(BigDecimal firstTenderAccount) {
		this.firstTenderAccount = firstTenderAccount;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public BigDecimal getLateInterest() {
		return lateInterest;
	}

	public void setLateInterest(BigDecimal lateInterest) {
		this.lateInterest = lateInterest;
	}

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}
}
