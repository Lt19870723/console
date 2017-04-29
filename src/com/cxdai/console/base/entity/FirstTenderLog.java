package com.cxdai.console.base.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * <p>
 * Description:直通车投标日志记录<br />
 * </p>
 * 
 * @title FirstTenderLog.java
 * @package com.cxdai.base.entity
 * @author yangshijin
 * @version 0.1 2015年3月11日
 */
public class FirstTenderLog implements Serializable {
	private static final long serialVersionUID = 4829448266420311599L;

	/**
	 * 主键Id
	 */
	private Integer id;
	/**
	 * 直通车开通人ID
	 */
	private Integer userId;
	/**
	 * 直通车ID
	 */
	private Integer firstBorrowId;
	/**
	 * 直通车最终记录ID
	 */
	private Integer firstTenderRealId;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 可用金额（投之前直通车可用余额）
	 */
	private BigDecimal useMoney;
	/**
	 * 开通时间
	 */
	private Date openTime;
	/**
	 * 上车时间（用于直通车投标）
	 */
	private Date onBusTime;
	/**
	 * 排队序列号
	 */
	private Integer orderNum;
	/**
	 * 借款标ID
	 */
	private Integer borrowId;
	/**
	 * 借款标名称
	 */
	private String borrowName;

	/**
	 * 借款标编号
	 */
	private String borrowContractNo;

	/**
	 * 借款标发标时间
	 */
	private Date borrowPublishTime;

	/**
	 * 标剩余金额（投之前此标剩余金额）
	 */
	private BigDecimal remaindMoney;

	/**
	 * 实际投标金额（成功投标金额）
	 */
	private BigDecimal realAccount;

	/**
	 * 状态【1：投标成功，2：未投标】
	 */
	private Integer status;

	/**
	 * 插入时间
	 */
	private Date addTime;

	/**
	 * 结果说明备注
	 */
	private String remark;

	/**
	 * 版本号.
	 */
	private String version;
	/**
	 * 本身版本号，主要用于根据版本号更新
	 */
	private String selfVersion;

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

	public Integer getFirstBorrowId() {
		return firstBorrowId;
	}

	public void setFirstBorrowId(Integer firstBorrowId) {
		this.firstBorrowId = firstBorrowId;
	}

	public Integer getFirstTenderRealId() {
		return firstTenderRealId;
	}

	public void setFirstTenderRealId(Integer firstTenderRealId) {
		this.firstTenderRealId = firstTenderRealId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public BigDecimal getUseMoney() {
		return useMoney;
	}

	public void setUseMoney(BigDecimal useMoney) {
		this.useMoney = useMoney;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public Date getOnBusTime() {
		return onBusTime;
	}

	public void setOnBusTime(Date onBusTime) {
		this.onBusTime = onBusTime;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}

	public String getBorrowName() {
		return borrowName;
	}

	public void setBorrowName(String borrowName) {
		this.borrowName = borrowName;
	}

	public String getBorrowContractNo() {
		return borrowContractNo;
	}

	public void setBorrowContractNo(String borrowContractNo) {
		this.borrowContractNo = borrowContractNo;
	}

	public Date getBorrowPublishTime() {
		return borrowPublishTime;
	}

	public void setBorrowPublishTime(Date borrowPublishTime) {
		this.borrowPublishTime = borrowPublishTime;
	}

	public BigDecimal getRemaindMoney() {
		return remaindMoney;
	}

	public void setRemaindMoney(BigDecimal remaindMoney) {
		this.remaindMoney = remaindMoney;
	}

	public BigDecimal getRealAccount() {
		return realAccount;
	}

	public void setRealAccount(BigDecimal realAccount) {
		this.realAccount = realAccount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSelfVersion() {
		return selfVersion;
	}

	public void setSelfVersion(String selfVersion) {
		this.selfVersion = selfVersion;
	}
}
