package com.cxdai.console.fix.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class TenderRecordCheck implements Serializable {

	private static final long serialVersionUID = -8397164944912356504L;

	private String borrowName;

	private String username;

	private BigDecimal account;

	private String addtime;

	private String tenderType;

	public String getBorrowName() {
		return borrowName;
	}

	public void setBorrowName(String borrowName) {
		this.borrowName = borrowName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public BigDecimal getAccount() {
		return account;
	}

	public void setAccount(BigDecimal account) {
		this.account = account;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getTenderType() {
		return tenderType;
	}

	public void setTenderType(String tenderType) {
		this.tenderType = tenderType;
	}

}
