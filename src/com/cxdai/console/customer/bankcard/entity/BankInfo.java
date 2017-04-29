package com.cxdai.console.customer.bankcard.entity;

import java.io.Serializable;

/**
 * <p>
 * Description:银行信息<br />
 * </p>
 * 
 * @title BankInfo.java
 * @package com.cxdai.base.entity
 * @author justin.xu
 * @version 0.1 2014年5月13日
 */
public class BankInfo implements Serializable {
	private static final long serialVersionUID = 3279069815765729961L;
	private Integer id;
	/** 用户ID */
	private Integer userId;
	/** 銀行名稱 */
	private String bankName;
	/** 銀行帳戶號 */
	private String cardNum;
	/** 所屬分行 */
	private String branch;
	/** 真实姓名 */
	private String realName;
	/** 身份证号码 */
	private String idCardNo;
	/** 联行号 */
	private Long cnapsCode;
	/** 验证状态【0：未验证 1：验证通过】 */
	private Integer verifyStatus;
	/** 支付时的签约协议号 */
	private String noAgree;

	private String bankCode;

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

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
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

	public Long getCnapsCode() {
		return cnapsCode;
	}

	public void setCnapsCode(Long cnapsCode) {
		this.cnapsCode = cnapsCode;
	}

	public Integer getVerifyStatus() {
		return verifyStatus;
	}

	public void setVerifyStatus(Integer verifyStatus) {
		this.verifyStatus = verifyStatus;
	}

	public String getNoAgree() {
		return noAgree;
	}

	public void setNoAgree(String noAgree) {
		this.noAgree = noAgree;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

}
