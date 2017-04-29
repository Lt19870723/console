package com.cxdai.console.borrow.emerg.vo;

import java.io.Serializable;

import com.cxdai.console.common.page.BaseCnd;

public class UserTenderRecordCnd extends BaseCnd implements Serializable {

	/**
	 * @fields serialVersionUID
	 */

	private static final long serialVersionUID = 1L;
	private String contractNo; // 借款表编号
	private String borrowUserName; // 借款用户名
	private String tenderUserName; // 投标用户名
	private Integer borrowtype; // 标的类型
	private String isCustody;
	
	

	public String getIsCustody() {
		return isCustody;
	}

	public void setIsCustody(String isCustody) {
		this.isCustody = isCustody;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getBorrowUserName() {
		return borrowUserName;
	}

	public void setBorrowUserName(String borrowUserName) {
		this.borrowUserName = borrowUserName;
	}

	public String getTenderUserName() {
		return tenderUserName;
	}

	public void setTenderUserName(String tenderUserName) {
		this.tenderUserName = tenderUserName;
	}

	public Integer getBorrowtype() {
		return borrowtype;
	}

	public void setBorrowtype(Integer borrowtype) {
		this.borrowtype = borrowtype;
	}


}