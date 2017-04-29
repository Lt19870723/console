package com.cxdai.console.borrow.manage.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.MoneyUtil;

/**
 * 
 * <p>
 * Description:待收表的每投标记录-实体类<br />
 * </p>
 * 
 * @author 陈鹏
 * @version 0.1 2015年1月22日
 */
public class CSumCapitalToTenderVo {
	
	private int bStatus;
	private Date tAddtime;
	private int id;
	private BigDecimal account;
	private BigDecimal sumCapital;
	private BigDecimal interest;
	private BigDecimal sumInterest;
	private BigDecimal repaymentAccount;
	private BigDecimal sumRepayAccount;
	private String name;
	
	private String tAddtimeStr;
	
	private String accountStr;
	private String sumCapitalStr;
	private String interestStr;
	private String sumInterestStr;
	private String repaymentAccountStr;
	private String sumRepayAccountStr;
	
	private Integer isCustody;
	
	
	
	public Integer getIsCustody() {
		return isCustody;
	}
	public void setIsCustody(Integer isCustody) {
		this.isCustody = isCustody;
	}
	public int getbStatus() {
		return bStatus;
	}
	public void setbStatus(int bStatus) {
		this.bStatus = bStatus;
	}
	public Date gettAddtime() {
		return tAddtime;
	}
	public void settAddtime(Date tAddtime) {
		this.tAddtime = tAddtime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BigDecimal getAccount() {
		return account;
	}
	public void setAccount(BigDecimal account) {
		this.account = account;
	}
	public BigDecimal getSumCapital() {
		return sumCapital;
	}
	public void setSumCapital(BigDecimal sumCapital) {
		this.sumCapital = sumCapital;
	}
	public BigDecimal getInterest() {
		return interest;
	}
	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}
	public BigDecimal getSumInterest() {
		return sumInterest;
	}
	public void setSumInterest(BigDecimal sumInterest) {
		this.sumInterest = sumInterest;
	}
	public BigDecimal getRepaymentAccount() {
		return repaymentAccount;
	}
	public void setRepaymentAccount(BigDecimal repaymentAccount) {
		this.repaymentAccount = repaymentAccount;
	}
	public BigDecimal getSumRepayAccount() {
		return sumRepayAccount;
	}
	public void setSumRepayAccount(BigDecimal sumRepayAccount) {
		this.sumRepayAccount = sumRepayAccount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String gettAddtimeStr() {
		if (gettAddtime() != null) {
			return DateUtils.format(gettAddtime(), DateUtils.YMD_HMS);
		}
		return tAddtimeStr;
	}
	public void settAddtimeStr(String tAddtimeStr) {
		this.tAddtimeStr = tAddtimeStr;
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
	
	public String getSumCapitalStr() {
		if(getSumCapital() != null){
			return MoneyUtil.fmtMoneyByDot(getSumCapital());
		}
		return sumCapitalStr;
	}
	public void setSumCapitalStr(String sumCapitalStr) {
		this.sumCapitalStr = sumCapitalStr;
	}
	
	public String getInterestStr() {
		if(getInterest() != null){
			return MoneyUtil.fmtMoneyByDot(getInterest());
		}
		return interestStr;
	}
	public void setInterestStr(String interestStr) {
		this.interestStr = interestStr;
	}
	
	public String getSumInterestStr() {
		if(getSumInterest() != null){
			return MoneyUtil.fmtMoneyByDot(getSumInterest());
		}
		return sumInterestStr;
	}
	public void setSumInterestStr(String sumInterestStr) {
		this.sumInterestStr = sumInterestStr;
	}
	
	public String getRepaymentAccountStr() {
		if(getRepaymentAccount() != null){
			return MoneyUtil.fmtMoneyByDot(getRepaymentAccount());
		}
		return repaymentAccountStr;
	}
	public void setRepaymentAccountStr(String repaymentAccountStr) {
		this.repaymentAccountStr = repaymentAccountStr;
	}
	
	public String getSumRepayAccountStr() {
		if(getSumRepayAccount() != null){
			return MoneyUtil.fmtMoneyByDot(getSumRepayAccount());
		}
		return sumRepayAccountStr;
	}
	public void setSumRepayAccountStr(String sumRepayAccountStr) {
		this.sumRepayAccountStr = sumRepayAccountStr;
	}
	
}
