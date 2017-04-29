package com.cxdai.console.customer.information.vo;

import java.io.Serializable;

/**
 * <p>
 * Description:手机验证Vo<br />
 * </p>
 * 
 * @title MobileApproVo.java
 * @package com.cxdai.console.member.vo
 * @author justin.xu
 * @version 0.1 2014年4月25日
 */
public class MobileApproVo implements Serializable {

	private static final long serialVersionUID = -3441075247554353199L;
	/** ID */
	private Integer id;
	/** 用户ID */
	private Integer userId;
	/** 验证码 */
	private String randCode;
	/** 手机好吗 */
	private String mobileNum;
	/** 是否通过验证【0：未通过；1：通过】 */
	private Integer passed;
	/** 验证码发送时间 */
	private String addTime;
	/** 发送IP */
	private String addIp;
	/** 验证通过时间 */
	private String approTime;
	private String username;

	// 是否为投资者
	private int memberType;

	public int getMemberType() {
		return memberType;
	}

	public void setMemberType(int memberType) {
		this.memberType = memberType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

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

}
