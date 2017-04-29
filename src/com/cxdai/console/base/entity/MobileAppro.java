package com.cxdai.console.base.entity;

import java.io.Serializable;

/**
 * <p>
 * Description:手机验证<br />
 * </p>
 * 
 * @title MobileAppro.java
 * @package com.cxdai.base.entity
 * @author justin.xu
 * @version 0.1 2014年4月25日
 */
public class MobileAppro implements Serializable {

	private static final long serialVersionUID = -3441075247554353199L;
	/** ID */
	private Integer id;
	/** 用户ID */
	private Integer userId;
	/** 验证码 */
	private String randCode;
	/** 手机号码 */
	private String mobileNum;
	/** 是否通过验证【0：未通过；1：通过】 */
	private Integer passed;
	/** 验证码发送时间 */
	private String addTime;
	/** 发送IP */
	private String addIp;
	/** 验证通过时间 */
	private String approTime;
	/** 平台来源(1：网页 2、微信) */
	private Integer platform;

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

	public String getRandCode() {
		return randCode;
	}

	public void setRandCode(String randCode) {
		this.randCode = randCode;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public Integer getPassed() {
		return passed;
	}

	public void setPassed(Integer passed) {
		this.passed = passed;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getAddIp() {
		return addIp;
	}

	public void setAddIp(String addIp) {
		this.addIp = addIp;
	}

	public String getApproTime() {
		return approTime;
	}

	public void setApproTime(String approTime) {
		this.approTime = approTime;
	}

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}
}
