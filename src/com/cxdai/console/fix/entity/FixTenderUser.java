package com.cxdai.console.fix.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * Description:定期投标记录类<br />
 * </p>
 * 
 * @title FixTenderUser.java
 * @package com.cxdai.console.fix.entity
 * @author caodefeng
 * @version 0.1 2015年5月23日
 */
public class FixTenderUser implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5239387929393550826L;

	/**
	 * 主键Id
	 */
	private Integer id; 
	
	/**
	 * 投资用户id
	 */
	private Integer userId; 
	
	/**
	 * 用户名
	 */
	private String username; 
	
	/**
	 * 定期宝Id
	 */
	private Integer fixBorrowId; 
	
	/**
	 * 最终认购记录id
	 */
	private Integer fixTenderRealId; 
	
	/**
	 * 借款标id
	 */
	private Integer borrowId; 
	
	/**
	 * 投标Id
	 */
	private Integer tenderId; 
	
	/**
	 * 借款标名称
	 */
	private String borrowName; 
	
	/**
	 * 借款标的合同编号
	 */
	private String contractNo; 
	
	/**
	 * 投标金额
	 */
	private BigDecimal account; 
	
	/**
	 * 添加时间
	 */
	private Date addtime; 
	
	/**
	 * 状态
	 */
	private Integer status; 
	
	/**
	 * 备注
	 */
	private String remark; 
	

	
	public Integer getId () {
		return id;
	}
	
	
	public void setId (Integer id) {
		this.id = id;
	}
	
	public Integer getUserId () {
		return userId;
	}
	
	
	public void setUserId (Integer userId) {
		this.userId = userId;
	}
	
	public String getUsername () {
		return username;
	}
	
	
	public void setUsername (String username) {
		this.username = username;
	}
	
	public Integer getFixBorrowId () {
		return fixBorrowId;
	}
	
	
	public void setFixBorrowId (Integer fixBorrowId) {
		this.fixBorrowId = fixBorrowId;
	}
	
	public Integer getFixTenderRealId () {
		return fixTenderRealId;
	}
	
	
	public void setFixTenderRealId (Integer fixTenderRealId) {
		this.fixTenderRealId = fixTenderRealId;
	}
	
	public Integer getBorrowId () {
		return borrowId;
	}
	
	
	public void setBorrowId (Integer borrowId) {
		this.borrowId = borrowId;
	}
	
	public Integer getTenderId () {
		return tenderId;
	}
	
	
	public void setTenderId (Integer tenderId) {
		this.tenderId = tenderId;
	}
	
	public String getBorrowName () {
		return borrowName;
	}
	
	
	public void setBorrowName (String borrowName) {
		this.borrowName = borrowName;
	}
	
	public String getContractNo () {
		return contractNo;
	}
	
	
	public void setContractNo (String contractNo) {
		this.contractNo = contractNo;
	}
	
	public BigDecimal getAccount () {
		return account;
	}
	
	
	public void setAccount (BigDecimal account) {
		this.account = account;
	}
	
	public Date getAddtime () {
		return addtime;
	}
	
	
	public void setAddtime (Date addtime) {
		this.addtime = addtime;
	}
	
	public Integer getStatus () {
		return status;
	}
	
	
	public void setStatus (Integer status) {
		this.status = status;
	}
	
	public String getRemark () {
		return remark;
	}
	
	
	public void setRemark (String remark) {
		this.remark = remark;
	}


}
