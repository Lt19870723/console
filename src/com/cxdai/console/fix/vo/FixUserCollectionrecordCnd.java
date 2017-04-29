package com.cxdai.console.fix.vo;

import java.io.Serializable;

import com.cxdai.console.common.page.BaseCnd;

public class FixUserCollectionrecordCnd extends BaseCnd implements Serializable {

	private static final long serialVersionUID = 2555010823306689591L;

	/**
	 * 定期宝ID
	 */
	private Integer fixBorrowId;

	/**
	 * 借款标ID
	 */
	private Integer borrowId;

	/**
	 * 投标ID
	 */
	private Integer tenderId;

	/**
	 * 用户ID
	 */
	private Integer userId;

	public Integer getFixBorrowId() {
		return fixBorrowId;
	}

	public void setFixBorrowId(Integer fixBorrowId) {
		this.fixBorrowId = fixBorrowId;
	}

	public Integer getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}

	public Integer getTenderId() {
		return tenderId;
	}

	public void setTenderId(Integer tenderId) {
		this.tenderId = tenderId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
