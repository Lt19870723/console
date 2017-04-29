package com.cxdai.console.base.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * <p>
 * Description:借款标提前满标日志记录<br />
 * </p>
 * 
 * @title BorrowAdvanceLog.java
 * @package com.cxdai.base.entity
 * @author yangshijin
 * @version 0.1 2014年8月27日
 */
public class BorrowAdvanceLog implements Serializable {

	private static final long serialVersionUID = 6076537122849642666L;
	/** 主键ID */
	private Integer id;
	/** 借款人ID */
	private Integer userId;
	/** 借款标Id */
	private Integer borrowId;
	/** 借款金额 */
	private BigDecimal account;
	/** 实际满标金额 */
	private BigDecimal realAccount;
	/** 添加时间 */
	private Date addtime;
	/** 添加IP */
	private String addip;
	/** 操作人 */
	private Integer operatorId;
	/** 平台来源 */
	private Integer platform;
	/** 操作类型 1:提前满标 2：撤标 */
	private Integer type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}

	public BigDecimal getAccount() {
		return account;
	}

	public void setAccount(BigDecimal account) {
		this.account = account;
	}

	public BigDecimal getRealAccount() {
		return realAccount;
	}

	public void setRealAccount(BigDecimal realAccount) {
		this.realAccount = realAccount;
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

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
