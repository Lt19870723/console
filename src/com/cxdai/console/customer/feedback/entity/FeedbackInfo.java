package com.cxdai.console.customer.feedback.entity;

import java.util.Date;

public class FeedbackInfo {

	/** 主键ID */
	private int id;

	/** 状态(0：待解决，1：已解决，2：不予解决) */
	private Integer status;

	/** 手机号 */
	private String mobileNum;

	/** 用户名 */
	private String userName;

	/** 真实姓名 */
	private String realName;

	/** 接单人Id */
	private Integer staffId;

	/** 联系时间 */
	private Date contactTime;

	/** 联系内容 */
	private String contactContent;

	/** 添加时间 */
	private Date addTime;

	/** 版本 */
	private Integer version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public Date getContactTime() {
		return contactTime;
	}

	public void setContactTime(Date contactTime) {
		this.contactTime = contactTime;
	}

	public String getContactContent() {
		return contactContent;
	}

	public void setContactContent(String contactContent) {
		this.contactContent = contactContent;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
