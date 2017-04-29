package com.cxdai.console.borrow.manage.vo;

import java.math.BigDecimal;

import com.cxdai.console.util.MoneyUtil;

public class TenderCollectionVo {

	private int borrowId;
	private BigDecimal sumRepaymentAccount;
	private BigDecimal sumRepayAccount;
	private BigDecimal diff;
	private String name;
	
	//临时属性 
	private String sumRepaymentAccountStr;
	private String sumRepayAccountStr;
	private String diffStr;
	
	public int getBorrowId() {
		return borrowId;
	}
	public void setBorrowId(int borrowId) {
		this.borrowId = borrowId;
	}
	
	public BigDecimal getSumRepaymentAccount() {
		return sumRepaymentAccount;
	}
	public void setSumRepaymentAccount(BigDecimal sumRepaymentAccount) {
		this.sumRepaymentAccount = sumRepaymentAccount;
	}
	public BigDecimal getSumRepayAccount() {
		return sumRepayAccount;
	}
	public void setSumRepayAccount(BigDecimal sumRepayAccount) {
		this.sumRepayAccount = sumRepayAccount;
	}
	public BigDecimal getDiff() {
		return diff;
	}
	public void setDiff(BigDecimal diff) {
		this.diff = diff;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSumRepaymentAccountStr() {
		if(getSumRepaymentAccount() != null){
			return MoneyUtil.fmtMoneyByDot(getSumRepaymentAccount());
		}
		return sumRepaymentAccountStr;
	}
	public void setSumRepaymentAccountStr(String sumRepaymentAccountStr) {
		this.sumRepaymentAccountStr = sumRepaymentAccountStr;
	}
	
	public String getSumRepayAccountStr() {
		if(getSumRepayAccount() != null){
			return MoneyUtil.fmtMoneyByDot(getSumRepayAccount());
		}
		return sumRepayAccountStr;
	}
	public void setSumRepayAccountStr(String sumRepayAccountStr) {
		this.sumRepayAccountStr = sumRepayAccountStr;
	}
	
	public String getDiffStr() {
		if(getDiff() != null){
			return MoneyUtil.fmtMoneyByDot(getDiff());
		}
		return diffStr;
	}
	public void setDiffStr(String diffStr) {
		this.diffStr = diffStr;
	}

	
}
