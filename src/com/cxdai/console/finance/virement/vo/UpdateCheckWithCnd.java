package com.cxdai.console.finance.virement.vo;

import java.math.BigDecimal;

import com.cxdai.console.common.page.BaseCnd;

@SuppressWarnings("serial")
public class UpdateCheckWithCnd extends BaseCnd {
	
	private Integer id;
	private BigDecimal totalExpenditure;
	private BigDecimal counterFee;
	private BigDecimal difference;
	private String remarks;
	private Integer isSuccess;//1对账成功 2保存草稿
	private Integer updateUser;
	private String updateIp;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public BigDecimal getTotalExpenditure() {
		return totalExpenditure;
	}
	public void setTotalExpenditure(BigDecimal totalExpenditure) {
		this.totalExpenditure = totalExpenditure;
	}
	public BigDecimal getCounterFee() {
		return counterFee;
	}
	public void setCounterFee(BigDecimal counterFee) {
		this.counterFee = counterFee;
	}
	public BigDecimal getDifference() {
		return difference;
	}
	public void setDifference(BigDecimal difference) {
		this.difference = difference;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(Integer isSuccess) {
		this.isSuccess = isSuccess;
	}
	public Integer getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}
	public String getUpdateIp() {
		return updateIp;
	}
	public void setUpdateIp(String updateIp) {
		this.updateIp = updateIp;
	}
	
	
}
