package com.cxdai.console.fix.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * Description:定期宝待还记录<br />
 * </p>
 * 
 * @title FixRepaymentrecord.java
 * @package com.cxdai.face.fix.entity
 * @author HuangJun
 * @version 0.1 2015年6月25日
 */
public class FixRepaymentrecord {
	private Integer id;

	private Integer status;

	private Integer fixBorrowId;

	private Date repaymentTime;

	private Date repaymentYestime;

	private BigDecimal repaymentAccount;

	private BigDecimal repaymentYesaccount;

	private BigDecimal interest;

	private BigDecimal capital;

	private Integer lateDays;

	private BigDecimal lateInterest;

	private Date advancetime;

	private BigDecimal advanceYesaccount;

	private Integer afterwebpayLateDay;

	private Byte isRepairInterest;

	private Date repairInterestTime;

	private Date addtime;

	private String addip;

	private Byte platform;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	

	/**
	 * @return status : return the property status.
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status : set the property status.
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getFixBorrowId() {
		return fixBorrowId;
	}

	public void setFixBorrowId(Integer fixBorrowId) {
		this.fixBorrowId = fixBorrowId;
	}

	public Date getRepaymentTime() {
		return repaymentTime;
	}

	public void setRepaymentTime(Date repaymentTime) {
		this.repaymentTime = repaymentTime;
	}

	public Date getRepaymentYestime() {
		return repaymentYestime;
	}

	public void setRepaymentYestime(Date repaymentYestime) {
		this.repaymentYestime = repaymentYestime;
	}

	public BigDecimal getRepaymentAccount() {
		return repaymentAccount;
	}

	public void setRepaymentAccount(BigDecimal repaymentAccount) {
		this.repaymentAccount = repaymentAccount;
	}

	public BigDecimal getRepaymentYesaccount() {
		return repaymentYesaccount;
	}

	public void setRepaymentYesaccount(BigDecimal repaymentYesaccount) {
		this.repaymentYesaccount = repaymentYesaccount;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public BigDecimal getCapital() {
		return capital;
	}

	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}

	public Integer getLateDays() {
		return lateDays;
	}

	public void setLateDays(Integer lateDays) {
		this.lateDays = lateDays;
	}

	public BigDecimal getLateInterest() {
		return lateInterest;
	}

	public void setLateInterest(BigDecimal lateInterest) {
		this.lateInterest = lateInterest;
	}

	public Date getAdvancetime() {
		return advancetime;
	}

	public void setAdvancetime(Date advancetime) {
		this.advancetime = advancetime;
	}

	public BigDecimal getAdvanceYesaccount() {
		return advanceYesaccount;
	}

	public void setAdvanceYesaccount(BigDecimal advanceYesaccount) {
		this.advanceYesaccount = advanceYesaccount;
	}

	public Integer getAfterwebpayLateDay() {
		return afterwebpayLateDay;
	}

	public void setAfterwebpayLateDay(Integer afterwebpayLateDay) {
		this.afterwebpayLateDay = afterwebpayLateDay;
	}

	public Byte getIsRepairInterest() {
		return isRepairInterest;
	}

	public void setIsRepairInterest(Byte isRepairInterest) {
		this.isRepairInterest = isRepairInterest;
	}

	public Date getRepairInterestTime() {
		return repairInterestTime;
	}

	public void setRepairInterestTime(Date repairInterestTime) {
		this.repairInterestTime = repairInterestTime;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getAddip() {
		return addip;
	}

	public void setAddip(String addip) {
		this.addip = addip;
	}

	public Byte getPlatform() {
		return platform;
	}

	public void setPlatform(Byte platform) {
		this.platform = platform;
	}
}