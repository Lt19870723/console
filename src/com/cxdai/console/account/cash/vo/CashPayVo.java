package com.cxdai.console.account.cash.vo;

import java.math.BigDecimal;

/**
 * <p>
 * Description:提现待打款实体对象<br />
 * </p>
 * 
 * @title CashPayVo.java
 * @package com.cxdai.console.account.vo
 * @author yangshijin
 * @version 0.1 2014年9月22日
 */
public class CashPayVo {

	/** 收款方银行账号 */
	private String account;

	/** 银行类型 */
	private String bank;

	/** 真实姓名 */
	private String realName;

	/** 付款金额 */
	private BigDecimal credited;

	/** 用途组成 */
	private String cashUse;

	/** 汇路 */
	private String huilu;

	/** 开户行号 */
	private String cnapscode;

	/** 收款人手机号 */
	private String mobile;

	/** 账户属性 */
	private String accountAttr;

	/** 账户类型 */
	private String accountType;

	/** 支行名称 */
	private String branch;

	/** 开户地区（省） */
	private String province;

	/** 开户城市(市) */
	private String city;

	/** 付款说明 */
	private String remark;

	/** 所属机构 */
	private String institutions;
	private String ststem;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public BigDecimal getCredited() {
		return credited;
	}

	public void setCredited(BigDecimal credited) {
		this.credited = credited;
	}

	public String getCashUse() {
		return cashUse;
	}

	public void setCashUse(String cashUse) {
		this.cashUse = cashUse;
	}

	public String getHuilu() {
		return huilu;
	}

	public void setHuilu(String huilu) {
		this.huilu = huilu;
	}

	public String getCnapscode() {
		return cnapscode;
	}

	public void setCnapscode(String cnapscode) {
		this.cnapscode = cnapscode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAccountAttr() {
		return "对私";
	}

	public void setAccountAttr(String accountAttr) {
		this.accountAttr = accountAttr;
	}

	public String getAccountType() {
		return "借记卡";
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getRemark() {
		if (remark == null)
			remark = "";
		return remark + "国诚";
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getInstitutions() {
		return institutions;
	}

	public void setInstitutions(String institutions) {
		this.institutions = institutions;
	}

	public String getStstem() {
		return ststem = "国诚";
	}

	public void setStstem(String ststem) {
		this.ststem = ststem;
	}
}
