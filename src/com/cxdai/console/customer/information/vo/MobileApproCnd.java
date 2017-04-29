package com.cxdai.console.customer.information.vo;

import java.io.Serializable;

/**
 * <p>
 * Description:手机验证查询条件<br />
 * </p>
 * 
 * @title MobileApproCnd.java
 * @package com.cxdai.console.member.vo
 * @author justin.xu
 * @version 0.1 2014年4月25日
 */
public class MobileApproCnd implements Serializable {

	private static final long serialVersionUID = -3441075247554353199L;
	/** ID */
	private Integer id;
	/** 用户ID */
	private Integer userId;
	/** 手机好吗 */
	private String mobileNum;

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

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

}
