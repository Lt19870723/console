package com.cxdai.console.firstborrow.vo;

import java.io.Serializable;

import com.cxdai.console.common.page.BaseCnd;

/**
 * <p>
 * Description:优先投标计划最终认购记录封装查询条件<br />
 * </p>
 * 
 * @title FirstTenderRealCnd.java
 * @package com.cxdai.console.first.vo
 * @author justin.xu
 * @version 0.1 2014年10月28日
 */
public class FirstTenderRealCnd extends BaseCnd implements Serializable {

	private static final long serialVersionUID = 4033975705063158765L;
	/**
	 * 主键id
	 */
	private String id;

	private String userId;

	/**
	 * 状态
	 */
	private String status;
	/**
	 * 排序sql
	 */
	private String orderSql;
	/**
	 * 是否锁定记录, 锁定： yes
	 */
	private String lockedRecordYn;
	/**
	 * 直通车标题
	 */
	private String firstBorrowName;
	/**
	 * 开通的用户名
	 */
	private String username;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getLockedRecordYn() {
		return lockedRecordYn;
	}

	public void setLockedRecordYn(String lockedRecordYn) {
		this.lockedRecordYn = lockedRecordYn;
	}

	public String getFirstBorrowName() {
		return firstBorrowName;
	}

	public void setFirstBorrowName(String firstBorrowName) {
		this.firstBorrowName = firstBorrowName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
