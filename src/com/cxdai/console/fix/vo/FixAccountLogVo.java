package com.cxdai.console.fix.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * Description:定期宝账户日志类<br />
 * </p>
 * 
 * @title FixAccountLog.java
 * @package com.cxdai.base.entity
 * @author caodefeng
 * @version 0.1 2015年5月18日
 */
public class FixAccountLogVo {
	
	/**
	 * 主键id
	 */
	private Integer id; 
	
	/**
	 * 定期宝id
	 */
	private Integer fixBorrowId; 
	
	/**
	 * 账户变动类型
	 */
	private Integer type; 
	
	/**
	 * 借款标id
	 */
	private Integer borrowId; 
	
	/**
	 * 借款标名称
	 */
	private String borrowName; 
	
	/**
	 * 业务类型：1：借款标
	 */
	private Integer idType; 
	
	/**
	 * 操作金额
	 */
	private BigDecimal money; 
	
	/**
	 * 总额
	 */
	private BigDecimal total; 
	
	/**
	 * 可用余额
	 */
	private BigDecimal useMoney; 
	
	/**
	 * 冻结金额
	 */
	private BigDecimal noUseMoney; 
	
	/**
	 * 待收总额
	 */
	private BigDecimal collection; 
	
	/**
	 * 操作人
	 */
	private Integer addUser; 
	
	/**
	 * 操作时间
	 */
	private Date addTime; 
	
	/**
	 * 操作ip
	 */
	private String addIp; 
	
	/**
	 * 备注
	 */
	private String remark; 
	/**
	 * 实际收益
	 */
	private BigDecimal profit; 
	
	/**
	 * 本金
	 */
	private BigDecimal capital; 

	
	public Integer getId () {
		return id;
	}
	
	
	public void setId (Integer id) {
		this.id = id;
	}
	
	public Integer getFixBorrowId () {
		return fixBorrowId;
	}
	
	
	public void setFixBorrowId (Integer fixBorrowId) {
		this.fixBorrowId = fixBorrowId;
	}
	
	public Integer getType () {
		return type;
	}
	
	
	public void setType (Integer type) {
		this.type = type;
	}
	
	public Integer getBorrowId () {
		return borrowId;
	}
	
	
	public void setBorrowId (Integer borrowId) {
		this.borrowId = borrowId;
	}
	
	public String getBorrowName () {
		return borrowName;
	}
	
	
	public void setBorrowName (String borrowName) {
		this.borrowName = borrowName;
	}
	
	public Integer getIdType () {
		return idType;
	}
	
	
	public void setIdType (Integer idType) {
		this.idType = idType;
	}
	
	public BigDecimal getMoney () {
		return money;
	}
	
	
	public void setMoney (BigDecimal money) {
		this.money = money;
	}
	
	public BigDecimal getTotal () {
		return total;
	}
	
	
	public void setTotal (BigDecimal total) {
		this.total = total;
	}
	
	public BigDecimal getUseMoney () {
		return useMoney;
	}
	
	
	public void setUseMoney (BigDecimal useMoney) {
		this.useMoney = useMoney;
	}
	
	public BigDecimal getNoUseMoney () {
		return noUseMoney;
	}
	
	
	public void setNoUseMoney (BigDecimal noUseMoney) {
		this.noUseMoney = noUseMoney;
	}
	
	public BigDecimal getCollection () {
		return collection;
	}
	
	
	public void setCollection (BigDecimal collection) {
		this.collection = collection;
	}


	public Integer getAddUser() {
		return addUser;
	}


	public void setAddUser(Integer addUser) {
		this.addUser = addUser;
	}


	public Date getAddTime() {
		return addTime;
	}


	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}


	public String getAddIp() {
		return addIp;
	}


	public void setAddIp(String addIp) {
		this.addIp = addIp;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public BigDecimal getProfit() {
		return profit;
	}


	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}


	public BigDecimal getCapital() {
		return capital;
	}


	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}
	
	 
}
