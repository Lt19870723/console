package com.cxdai.console.account.recharge.vo;

import java.io.Serializable;
import java.util.Date;

import com.cxdai.console.common.page.BaseCnd;

public class AutoInvestConfigRecordCnd extends BaseCnd implements Serializable {
	private static final long serialVersionUID = 7371537867372259682L;

	/**
	 * 用户Id
	 */
	private Integer user_id;

	/**
	 * 投标方式
	 */
	private String autoTenderType;

	/**
	 * 状态
	 */
	private String status;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 记录类型（0：新建，1：修改，2：投标成功，3：删除）
	 */
	private String record_type;

	/**
	 * 借款标名称
	 */
	private String borrowName;

	/**
	 * 添加时间 开始条件
	 */
	private Date addtimeBegin;

	/**
	 * 添加时间 截止条件
	 */
	private Date addtimeEnd;

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getAutoTenderType() {
		return autoTenderType;
	}

	public void setAutoTenderType(String autoTenderType) {
		this.autoTenderType = autoTenderType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRecord_type() {
		return record_type;
	}

	public void setRecord_type(String record_type) {
		this.record_type = record_type;
	}

	public String getBorrowName() {
		return borrowName;
	}

	public void setBorrowName(String borrowName) {
		this.borrowName = borrowName;
	}

	public Date getAddtimeBegin() {
		return addtimeBegin;
	}

	public void setAddtimeBegin(Date addtimeBegin) {
		this.addtimeBegin = addtimeBegin;
	}

	public Date getAddtimeEnd() {
		return addtimeEnd;
	}

	public void setAddtimeEnd(Date addtimeEnd) {
		this.addtimeEnd = addtimeEnd;
	}
}
