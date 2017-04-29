package com.cxdai.console.finance.virement.vo;

import java.math.BigDecimal;


public class CheckAccountRequestCnd{

	private Integer type;
	private String date;
	private BigDecimal checkFee;
	private BigDecimal receiveMoney;
	private BigDecimal checkSuccessMoney;
	private BigDecimal fictitiousRecharge;
	private BigDecimal differenceFee;
	private BigDecimal differenceTotal;
	private Integer isSuccess;
	private String remarks;
	private Integer updateUser;
	private String updateIp;
	
	
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
	public BigDecimal getCheckSuccessMoney() {
		return checkSuccessMoney;
	}
	public void setCheckSuccessMoney(BigDecimal checkSuccessMoney) {
		this.checkSuccessMoney = checkSuccessMoney;
	}
	public BigDecimal getFictitiousRecharge() {
		return fictitiousRecharge;
	}
	public void setFictitiousRecharge(BigDecimal fictitiousRecharge) {
		this.fictitiousRecharge = fictitiousRecharge;
	}
	public BigDecimal getDifferenceFee() {
		return differenceFee;
	}
	public void setDifferenceFee(BigDecimal differenceFee) {
		this.differenceFee = differenceFee;
	}
	public BigDecimal getDifferenceTotal() {
		return differenceTotal;
	}
	public void setDifferenceTotal(BigDecimal differenceTotal) {
		this.differenceTotal = differenceTotal;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public BigDecimal getCheckFee() {
		return checkFee;
	}
	public void setCheckFee(BigDecimal checkFee) {
		this.checkFee = checkFee;
	}
	public BigDecimal getReceiveMoney() {
		return receiveMoney;
	}
	public void setReceiveMoney(BigDecimal receiveMoney) {
		this.receiveMoney = receiveMoney;
	}
	
}
