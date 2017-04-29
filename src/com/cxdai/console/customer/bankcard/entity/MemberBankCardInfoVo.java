package com.cxdai.console.customer.bankcard.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class MemberBankCardInfoVo implements Serializable {

	/**
	 * @fields serialVersionUID
	 */

	private static final long serialVersionUID = 3580972510694089827L;
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/** 用户名 */
	private String username;
	/** 用户真实姓名 */
	private String realName;
	/** 用户状态 */
	private Integer status;
	/** 银行卡号 */
	private String cardNum;
	/** 用户邮箱 */
	private String email;
	/** 身份证号码 */
	private String IDCardNo;
	/** 银行名称 */
	private String bankName;
	/** 所属分行 */
	private String branch;
	/** 联行号 */
	private String cnapsCode;
	/** 银行卡状态 */
	private Integer bankCardStatus;
	/** 四要素验证状态*/
	private String bankVerify;
	private Date addTime;
	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getBankVerify() {
		if(StringUtils.isNotEmpty(bankVerify)&&bankVerify.equals("1")){
			return "已验证";
		}else{
			return "未验证";
		}
	}
	public void setBankVerify(String bankVerify) {
		this.bankVerify = bankVerify;
	}

	public Integer getBankCardStatus() {
		return bankCardStatus;
	}

	public void setBankCardStatus(Integer bankCardStatus) {
		this.bankCardStatus = bankCardStatus;
	}

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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIDCardNo() {
		return IDCardNo;
	}

	public void setIDCardNo(String iDCardNo) {
		IDCardNo = iDCardNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getCnapsCode() {
		return cnapsCode;
	}

	public void setCnapsCode(String cnapsCode) {
		this.cnapsCode = cnapsCode;
	}

}
