package com.cxdai.console.borrow.emerg.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * Description:借款标审核<br />
 * </p>
 * 
 * @title BorrowApprovedVo.java
 * @package com.cxdai.console.borrow.vo
 * @author justin.xu
 * @version 0.1 2014年8月30日
 */
public class UserTenderRecordVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer borrowId;
	private String name;
	private String apr;
	private String borrowDateTimeStr;
	private BigDecimal account;
	private BigDecimal tenderaccount; // 投标金额
	private Integer userId;
	private String borrowtype; // 标的类型
	private String borrowtypeStr; // 标的类型
	private String borrowUserName;
	private String tenderDateTimeStr;
	private String contractNo;
	private String tenderUserName;
	private Integer status; // 投标记录的状态
	private String statusStr; // 投标记录的状态
	private Integer isCustody;
	
	
	
	public Integer getIsCustody() {
		return isCustody;
	}

	public void setIsCustody(Integer isCustody) {
		this.isCustody = isCustody;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApr() {
		return apr;
	}

	public void setApr(String apr) {
		this.apr = apr;
	}

	public BigDecimal getAccount() {
		return account;
	}

	public void setAccount(BigDecimal account) {
		this.account = account;
	}

	public BigDecimal getTenderaccount() {
		return tenderaccount;
	}

	public void setTenderaccount(BigDecimal tenderaccount) {
		this.tenderaccount = tenderaccount;
	}

	public String getBorrowtype() {
		return borrowtype;
	}

	public void setBorrowtype(String borrowtype) {
		this.borrowtype = borrowtype;
	}

	public String getBorrowUserName() {
		return borrowUserName;
	}

	public void setBorrowUserName(String borrowUserName) {
		this.borrowUserName = borrowUserName;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getTenderUserName() {
		return tenderUserName;
	}

	public void setTenderUserName(String tenderUserName) {
		this.tenderUserName = tenderUserName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getBorrowtypeStr() {
		return borrowtypeStr;
	}

	public void setBorrowtypeStr(String borrowtypeStr) {
		this.borrowtypeStr = borrowtypeStr;
	}

	public Integer getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public String getTenderDateTimeStr() {
		return tenderDateTimeStr;
	}

	public void setTenderDateTimeStr(String tenderDateTimeStr) {
		this.tenderDateTimeStr = tenderDateTimeStr;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getBorrowDateTimeStr() {
		return borrowDateTimeStr;
	}

	public void setBorrowDateTimeStr(String borrowDateTimeStr) {
		this.borrowDateTimeStr = borrowDateTimeStr;
	}

}
