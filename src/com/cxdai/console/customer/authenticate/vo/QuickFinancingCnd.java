package com.cxdai.console.customer.authenticate.vo;

import java.io.Serializable;

import com.cxdai.console.common.page.BaseCnd;

public class QuickFinancingCnd extends BaseCnd implements Serializable {

	private static final long serialVersionUID = 1L;
    
	private String tel;
	 
	private Integer mortgageType;
	 
	private Integer approveStatus;
    
	private String provinceCode;
	
	private String cityCode;
	
	public Integer getMortgageType() {
		return mortgageType;
	}

	public void setMortgageType(Integer mortgageType) {
		this.mortgageType = mortgageType;
	}

	public Integer getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(Integer approveStatus) {
		this.approveStatus = approveStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	 
}
