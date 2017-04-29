package com.cxdai.console.borrow.emerg.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.cxdai.console.base.entity.BTenderRecord;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.MoneyUtil;

/**
 * <p>
 * Description:投标记录Vo<br />
 * </p>
 * 
 * @title TenderRecordVo.java
 * @package com.cxdai.portal.borrow.vo
 * @author justin.xu
 * @version 0.1 2014年9月13日
 */
public class TenderRecordVo extends BTenderRecord implements Serializable {

	private static final long serialVersionUID = 7163900380887252193L;
	private String username;
	private String email;
	private Date addtimeDate;
	/** 直通车标题 */
	private String firstBorrowName;
	/** 直通车期 数 */
	private String firstPeriods;
	private String addtimeStr;
	
	//临时属性
	private String repaymentAccountStr;
	private String interestStr;
	private String accountStr;
	
	//定期宝转让
	private BigDecimal tenderCapital;
	private BigDecimal tenderInterest;
	private BigDecimal tenderRepayAccount;
	private String borrowContractNo;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstBorrowName() {
		return firstBorrowName;
	}

	public void setFirstBorrowName(String firstBorrowName) {
		this.firstBorrowName = firstBorrowName;
	}

	public Date getAddtimeDate() {
		if (null != super.getAddtime()) {
			return DateUtils.timeStampToDate(super.getAddtime());
		}
		return addtimeDate;
	}

	public void setAddtimeDate(Date addtimeDate) {
		this.addtimeDate = addtimeDate;
	}

	public String getFirstPeriods() {
		return firstPeriods;
	}

	public void setFirstPeriods(String firstPeriods) {
		this.firstPeriods = firstPeriods;
	}

	public String getAddtimeStr() {
		if (null != super.getAddtime()) {
			return DateUtils.timeStampToDate(super.getAddtime(), DateUtils.YMD_HMS);
		}
		return addtimeStr;
	}

	public void setAddtimeStr(String addtimeStr) {
		this.addtimeStr = addtimeStr;
	}

	public String getRepaymentAccountStr() {
		if(super.getRepaymentAccount() != null){
			return MoneyUtil.fmtMoneyByDot(super.getRepaymentAccount());
		}
		return repaymentAccountStr;
	}
	public void setRepaymentAccountStr(String repaymentAccountStr) {
		this.repaymentAccountStr = repaymentAccountStr;
	}

	public String getInterestStr() {
		if(super.getInterest() != null){
			return MoneyUtil.fmtMoneyByDot(super.getInterest());
		}
		return interestStr;
	}
	public void setInterestStr(String interestStr) {
		this.interestStr = interestStr;
	}

	public String getAccountStr() {
		if(super.getAccount() != null){
			return MoneyUtil.fmtMoneyByDot(super.getAccount());
		}
		return accountStr;
	}
	public void setAccountStr(String accountStr) {
		this.accountStr = accountStr;
	}

	public BigDecimal getTenderCapital() {
		return tenderCapital;
	}

	public void setTenderCapital(BigDecimal tenderCapital) {
		this.tenderCapital = tenderCapital;
	}

	public BigDecimal getTenderInterest() {
		return tenderInterest;
	}

	public void setTenderInterest(BigDecimal tenderInterest) {
		this.tenderInterest = tenderInterest;
	}

	public BigDecimal getTenderRepayAccount() {
		return tenderRepayAccount;
	}

	public void setTenderRepayAccount(BigDecimal tenderRepayAccount) {
		this.tenderRepayAccount = tenderRepayAccount;
	}

	public String getBorrowContractNo() {
		return borrowContractNo;
	}

	public void setBorrowContractNo(String borrowContractNo) {
		this.borrowContractNo = borrowContractNo;
	}

}