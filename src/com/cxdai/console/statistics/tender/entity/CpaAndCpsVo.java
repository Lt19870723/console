/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title NetValueBorrowVo.java
 * @package com.cxdai.console.opration.vo 
 * @author ZHUCHEN
 * @version 0.1 2015年1月9日
 */
package com.cxdai.console.statistics.tender.entity;

import java.math.BigDecimal;

/**
 * @author WangQianJin
 * @title CPA与CPS导出对象
 * @date 2016年3月21日
 */
public class CpaAndCpsVo {
	
	// 用户名
	private String username;
	// 绑定注册时间
	private String bingTime;
	// 投资时间
	private String tenderTime;
	// 投资金额
	private BigDecimal bidAmount=BigDecimal.ZERO;
	// 期限
	private Integer timeLimit;
	
	// 用户ID
	private Integer userId;
	// 投标ID
	private Integer tenderId;
	// 标名称
	private String borrowName;
	// 标ID
	private Integer borrowId;
	// 来源
	private String source;
	// 是否债转
	private String isTransfer;	
	
	public String getIsTransfer() {
		return isTransfer;
	}
	public void setIsTransfer(String isTransfer) {
		this.isTransfer = isTransfer;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getBingTime() {
		return bingTime;
	}
	public void setBingTime(String bingTime) {
		this.bingTime = bingTime;
	}
	public String getTenderTime() {
		return tenderTime;
	}
	public void setTenderTime(String tenderTime) {
		this.tenderTime = tenderTime;
	}
	public BigDecimal getBidAmount() {
		return bidAmount;
	}
	public void setBidAmount(BigDecimal bidAmount) {
		this.bidAmount = bidAmount;
	}
	public Integer getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getTenderId() {
		return tenderId;
	}
	public void setTenderId(Integer tenderId) {
		this.tenderId = tenderId;
	}
	public String getBorrowName() {
		return borrowName;
	}
	public void setBorrowName(String borrowName) {
		this.borrowName = borrowName;
	}
	public Integer getBorrowId() {
		return borrowId;
	}
	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	

}
