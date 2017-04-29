package com.cxdai.console.customer.bankcard.vo;

import java.io.Serializable;

import com.cxdai.console.customer.bankcard.entity.BankInfo;

/**
 * <p>
 * Description:银行卡信息<br />
 * </p>
 * 
 * @title BankInfoVo.java
 * @package com.cxdai.console.userbindbank.vo
 * @author 邹理享
 * @version 0.1 2014年11月18日
 */
public class BankInfoVo extends  BankInfo implements Serializable {
	private static final long serialVersionUID = 3279069815765729961L;
	private Integer id;
	/** 用户ID */
	private Integer userId;
	/** 银行名称 */
	private String bankName;
	/** 银行账户号 */
	private String cardNum;
	/** 所属分行 */
	private String branch;
	/** 真实姓名 */
	private String realName;
	/** 身份证号码 */
	private String idCardNo;
	/** 联行号 */
	private Long cnapsCode;
	/** 用户名 */
	private String userName;
	/** 确认卡号 */
	private String verifycardNum;
	/** 操作人 */
	private Integer addBy;

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getVerifycardNum() {
		return verifycardNum;
	}

	public void setVerifycardNum(String verifycardNum) {
		this.verifycardNum = verifycardNum;
	}

	public Integer getAddBy() {
		return addBy;
	}

	public void setAddBy(Integer addBy) {
		this.addBy = addBy;
	}

}
