package com.cxdai.console.borrow.manage.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.MoneyUtil;

public class CRSumCapitalToOtherVo {
	private int id;
	private int status;
	private int style;
	private int timeLimit;
	
	private Date successTime;
	private BigDecimal account;
	private BigDecimal collectionCapital;
	private BigDecimal repaymentCapital;
	private BigDecimal tenderCapital;
	private String name;
	
	private String timeStr;
	
	private String accountStr;
	private String collectionCapitalStr;
	private String repaymentCapitalStr;
	private String tenderCapitalStr;
	
	private Integer isCustody;
	
	
	
	public Integer getIsCustody() {
		return isCustody;
	}
	public void setIsCustody(Integer isCustody) {
		this.isCustody = isCustody;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getStyle() {
		return style;
	}
	public void setStyle(int style) {
		this.style = style;
	}
	public int getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}
	public Date getSuccessTime() {
		return successTime;
	}
	public void setSuccessTime(Date successTime) {
		this.successTime = successTime;
	}
	public BigDecimal getAccount() {
		return account;
	}
	public void setAccount(BigDecimal account) {
		this.account = account;
	}
	public BigDecimal getCollectionCapital() {
		return collectionCapital;
	}
	public void setCollectionCapital(BigDecimal collectionCapital) {
		this.collectionCapital = collectionCapital;
	}
	public BigDecimal getRepaymentCapital() {
		return repaymentCapital;
	}
	public void setRepaymentCapital(BigDecimal repaymentCapital) {
		this.repaymentCapital = repaymentCapital;
	}
	public BigDecimal getTenderCapital() {
		return tenderCapital;
	}
	public void setTenderCapital(BigDecimal tenderCapital) {
		this.tenderCapital = tenderCapital;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTimeStr() {
		if (getSuccessTime() != null) {
			return DateUtils.format(getSuccessTime(), DateUtils.YMD_HMS);
		}
		return timeStr;
	}
	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}
	
	
	public String getAccountStr() {
		if(getAccount() != null){
			return MoneyUtil.fmtMoneyByDot(getAccount());
		}
		return accountStr;
	}
	public void setAccountStr(String accountStr) {
		this.accountStr = accountStr;
	}
	
	public String getCollectionCapitalStr() {
		if(getCollectionCapital() != null){
			return MoneyUtil.fmtMoneyByDot(getCollectionCapital());
		}
		return collectionCapitalStr;
	}
	public void setCollectionCapitalStr(String collectionCapitalStr) {
		this.collectionCapitalStr = collectionCapitalStr;
	}
	
	public String getRepaymentCapitalStr() {
		if(getRepaymentCapital() != null){
			return MoneyUtil.fmtMoneyByDot(getRepaymentCapital());
		}
		return repaymentCapitalStr;
	}
	public void setRepaymentCapitalStr(String repaymentCapitalStr) {
		this.repaymentCapitalStr = repaymentCapitalStr;
	}
	
	public String getTenderCapitalStr() {
		if(getTenderCapital() != null){
			return MoneyUtil.fmtMoneyByDot(getTenderCapital());
		}
		return tenderCapitalStr;
	}
	public void setTenderCapitalStr(String tenderCapitalStr) {
		this.tenderCapitalStr = tenderCapitalStr;
	}
	
	
	
	
}
