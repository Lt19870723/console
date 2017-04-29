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
public class EmailApproVo implements Serializable {

	private static final long serialVersionUID = -3441075247554353199L;
	/** ID */
	private Integer id;
	/** 用户ID */
	private Integer userId;
	/** 随机UUID */
	private String RANDUUID;
	/** 是否通过验证【0：未通过，1：通过】 */
	private String CHECKED;
	/** 插入时间 */
	private String APPRTIME;
	/** 发送IP */
	private String APPRIP;
	/** 用户名 */
	private String username;
	// 邮箱
	private String EMAIL;

	/** 时间 */
	private String addTime;

	// 是否为投资者
	private int memberType;

	private String remark;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public int getMemberType() {
		return memberType;
	}

	public void setMemberType(int memberType) {
		this.memberType = memberType;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
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

	public String getRANDUUID() {
		return RANDUUID;
	}

	public void setRANDUUID(String rANDUUID) {
		RANDUUID = rANDUUID;
	}

	public String getCHECKED() {
		return CHECKED;
	}

	public void setCHECKED(String cHECKED) {
		CHECKED = cHECKED;
	}

	public String getAPPRTIME() {
		return APPRTIME;
	}

	public void setAPPRTIME(String aPPRTIME) {
		APPRTIME = aPPRTIME;
	}

	public String getAPPRIP() {
		return APPRIP;
	}

	public void setAPPRIP(String aPPRIP) {
		APPRIP = aPPRIP;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
