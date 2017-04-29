package com.cxdai.console.fix.vo;

import java.io.Serializable;

import com.cxdai.console.common.page.BaseCnd;

public class FixTenderUserCnd extends BaseCnd implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2248971288736351007L;

	/**
	 * 定期宝编号
	 */
	private String fixContractNo;

	/**
	 * 借款标名称
	 */
	private String borrowName;

	/**
	 * 借款标的合同编号
	 */
	private String contractNo;

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

	public String getFixContractNo() {
		return fixContractNo;
	}

	public void setFixContractNo(String fixContractNo) {
		this.fixContractNo = fixContractNo;
	}

	public String getBorrowName() {
		return borrowName;
	}

	public void setBorrowName(String borrowName) {
		this.borrowName = borrowName;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

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

}
