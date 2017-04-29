package com.cxdai.console.customer.svip.entity;

import java.io.Serializable;

/**
 * 
 * <p>
 * Description:终身顶级会员<br />
 * </p>
 * 
 * @title TopSvip.java
 * @package com.cxdai.base.entity
 * @author yangshijin
 * @version 0.1 2015年1月13日
 */
public class VipLevelVo extends VipLevel implements Serializable {
	private static final long serialVersionUID = -2033213769093637666L;

	/** 用户名 */
	private String username;
	/** 真实姓名 */
	private String realName;
	/** 邮箱 */
	private String email;
	/** 手机 */
	private String mobileNum;
	/** 是否是VIP */
	private String isVip;
	/** 是否是顶级VIP */
	private String isSvip;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public String getIsVip() {
		return isVip;
	}

	public void setIsVip(String isVip) {
		this.isVip = isVip;
	}

	public String getIsSvip() {
		return isSvip;
	}

	public void setIsSvip(String isSvip) {
		this.isSvip = isSvip;
	}
}
