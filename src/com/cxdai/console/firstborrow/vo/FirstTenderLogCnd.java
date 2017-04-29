package com.cxdai.console.firstborrow.vo;

import java.io.Serializable;

import com.cxdai.console.common.page.BaseCnd;

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
public class FirstTenderLogCnd extends BaseCnd implements Serializable {
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
	 * 用户名
	 */
	private String username;

	/**
	 * 借款标名称
	 */
	private String borrowName;

	/**
	 * 借款标编号
	 */
	private String borrowContractNo;

	/**
	 * 状态【1：投标成功，2：未投标】
	 */
	private String status;

	/**
	 * 排序字段
	 */
	private String orderSql;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderSql() {
		return orderSql;
	}

	public void setOrderSql(String orderSql) {
		this.orderSql = orderSql;
	}
}
