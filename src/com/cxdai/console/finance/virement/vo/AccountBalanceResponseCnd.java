package com.cxdai.console.finance.virement.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.cxdai.console.finance.virement.entity.AccountBalance;

public class AccountBalanceResponseCnd extends AccountBalance{
	private String 	payName;
	private String cardNo;
	private String bankName;
	private Date endTime;
	private BigDecimal balance;
	private int optType;
	private int bankType;
	
	
	
	
	
	
	public int getBankType() {
		return bankType;
	}
	public void setBankType(int bankType) {
		this.bankType = bankType;
	}
	public int getOptType() {
		return optType;
	}
	public void setOptType(int optType) {
		this.optType = optType;
	}

	public String getPayName() {
		return payName;
	}
	public void setPayName(String payName) {
		this.payName = payName;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
}
