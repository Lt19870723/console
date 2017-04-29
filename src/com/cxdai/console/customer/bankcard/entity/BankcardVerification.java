package com.cxdai.console.customer.bankcard.entity;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

/**
 * 银行卡四要素验证记录表
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title BankcardChange.java
 * @package com.cxdai.portal.member.vo
 * @author liutao
 * @version 0.1 2016年5月4日
 */
public class BankcardVerification implements Serializable {
	private static final long serialVersionUID = -5292768592815317113L;

	private Integer id;
	private String userId;
	private String userName;
	private String verifyTime;
	private String add_time;
	private String auResultCode;
	private String auResultInfo;
	private String errorCode;
	private String errorMsg;
	private String bankCardNum;
	private String realName;
	private String idCardNo;
	private String bankPhone;
	private String type;
	private Integer deleteFlag;
	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getVerifyTime() {
		if (StringUtils.isNotEmpty(this.verifyTime)) {
			return verifyTime = verifyTime.substring(0, 19);
		}
		return verifyTime;
	}

	public void setVerifyTime(String verifyTime) {
		this.verifyTime = verifyTime;
	}

	public String getAdd_time() {
		return add_time;
	}

	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}

	public String getAuResultCode() {
		return auResultCode;
	}

	public void setAuResultCode(String auResultCode) {
		this.auResultCode = auResultCode;
	}

	public String getAuResultInfo() {
		return auResultInfo;
	}

	public void setAuResultInfo(String auResultInfo) {
		this.auResultInfo = auResultInfo;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getBankCardNum() {
		return bankCardNum;
	}

	public void setBankCardNum(String bankCardNum) {
		this.bankCardNum = bankCardNum;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getBankPhone() {
		return bankPhone;
	}

	public void setBankPhone(String bankPhone) {
		this.bankPhone = bankPhone;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
