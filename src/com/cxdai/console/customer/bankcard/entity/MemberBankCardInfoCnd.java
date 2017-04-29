package com.cxdai.console.customer.bankcard.entity;

import java.io.Serializable;

import com.cxdai.console.common.page.BaseCnd;

public class MemberBankCardInfoCnd extends BaseCnd implements Serializable {

	private static final long serialVersionUID = -4540229019471070162L;
	/** 用户名 */
	private String userName;
	/** 用户真实姓名 */
	private String realName;
	/** 四要素状态查询 */
	private String verifyStatus;
	/**绑卡开始时间 */
	private String dateTimeStart;
	/** 绑卡结束时间*/
	private String dateTimeEnd;
	
	public String getDateTimeStart() {
		return dateTimeStart;
	}

	public void setDateTimeStart(String dateTimeStart) {
		this.dateTimeStart = dateTimeStart;
	}

	public String getDateTimeEnd() {
		return dateTimeEnd;
	}

	public void setDateTimeEnd(String dateTimeEnd) {
		this.dateTimeEnd = dateTimeEnd;
	}

	public String getVerifyStatus() {
		return verifyStatus;
	}

	public void setVerifyStatus(String verifyStatus) {
		this.verifyStatus = verifyStatus;
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

}
