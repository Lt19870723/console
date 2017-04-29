package com.cxdai.console.customer.bankcard.entity;

import java.io.Serializable;

import com.cxdai.console.common.page.BaseCnd;

/**
 * 
 * <p>
 * Description:银行卡分支行信息<br />
 * </p>
 * 
 * @title BankCnd.java
 * @package com.cxdai.console.account.vo
 * @author yangshijin
 * @version 0.1 2014年10月10日
 */
public class BankCnd extends BaseCnd implements Serializable {
	private static final long serialVersionUID = 1077516242641266576L;

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

}