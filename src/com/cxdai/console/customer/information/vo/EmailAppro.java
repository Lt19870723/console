package com.cxdai.console.customer.information.vo;

import java.io.Serializable;

/**
 * <p>
 * Description:邮箱认证<br />
 * </p>
 * 
 * @title EmailAppro.java
 * @package com.base.entity
 * @author justin.xu
 * @version 0.1 2014年4月16日
 */
public class EmailAppro implements Serializable {
	private static final long serialVersionUID = -1748725774856611015L;
	/** 主键ID */
	private Integer id;
	/** 会员ID */
	private Integer userId;
	/** 随机UUID */
	private String randUUID;
	/** 是否通过验证【0：未通过，1：通过】 */
	private Integer checked;
	/** 认证时间 */
	private String apprTime;
	/** 认证IP */
	private String apprIp;

	/** 平台来源 1：官网 2、微信，即用户登录的客户端 */
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

	public String getRandUUID() {
		return randUUID;
	}

	public void setRandUUID(String randUUID) {
		this.randUUID = randUUID;
	}

	public Integer getChecked() {
		return checked;
	}

	public void setChecked(Integer checked) {
		this.checked = checked;
	}

	public String getApprTime() {
		return apprTime;
	}

	public void setApprTime(String apprTime) {
		this.apprTime = apprTime;
	}

	public String getApprIp() {
		return apprIp;
	}

	public void setApprIp(String apprIp) {
		this.apprIp = apprIp;
	}

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}
}
