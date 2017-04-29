package com.cxdai.console.customer.bankcard.entity;

import java.util.Date;

/**
 * 
 * <p>
 * Description:银行卡分支行信息<br />
 * </p>
 * 
 * @title Bank.java
 * @package com.cxdai.console.account.entity
 * @author yangshijin
 * @version 0.1 2014年10月10日
 */
public class Bank {
	/** 主键ID */
	private Integer id;

	/** 状态【0:正常，-1:失效】 */
	private Integer status;

	private Long cnapsCode; // 联行号

	private String province; // 省

	private String city; // 市

	private String district; // 区

	private String bankName; // 银行名称

	private String branchName; // 支行名称

	private String bankCode; // 银行编码

	/** 添加人ID */
	private Integer addUserId;
	/** 添加时间 */
	private Date addTime;
	/** 修改人ID */
	private Integer updateUserId;
	/** 修改时间 */
	private Date updateTime;

	public Long getCnapsCode() {
		return cnapsCode;
	}

	public void setCnapsCode(Long cnapsCode) {
		this.cnapsCode = cnapsCode;
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

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAddUserId() {
		return addUserId;
	}

	public void setAddUserId(Integer addUserId) {
		this.addUserId = addUserId;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Integer getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Integer updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}