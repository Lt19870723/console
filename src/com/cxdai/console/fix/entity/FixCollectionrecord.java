package com.cxdai.console.fix.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * Description:定期宝投资人待收记录<br />
 * </p>
 * 
 * @title FixCollectionrecord.java
 * @package com.cxdai.console.fix.entity
 * @author HuangJun
 * @version 0.1 2015年6月28日
 */
public class FixCollectionrecord {
	private Integer id;

	private Integer fixBorrowId;

	private Integer userId;

	private Integer fixTenderRealId;

	private int status;

	private Date repayTime;

	private Date repayYestime;

	private BigDecimal repayAccount;

	private BigDecimal repayYesaccount;

	private BigDecimal interest;

	private BigDecimal capital;

	private Integer lateDays;

	private BigDecimal lateInterest;

	private BigDecimal advanceYesaccount;

	private Date advancetime;

	private Date addtime;

	private String addip;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFixBorrowId() {
		return fixBorrowId;
	}

	public void setFixBorrowId(Integer fixBorrowId) {
		this.fixBorrowId = fixBorrowId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getFixTenderRealId() {
		return fixTenderRealId;
	}

	public void setFixTenderRealId(Integer fixTenderRealId) {
		this.fixTenderRealId = fixTenderRealId;
	}



	/**
	 * @return status : return the property status.
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status : set the property status.
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	public Date getRepayTime() {
		return repayTime;
	}

	public void setRepayTime(Date repayTime) {
		this.repayTime = repayTime;
	}

	public Date getRepayYestime() {
		return repayYestime;
	}

	public void setRepayYestime(Date repayYestime) {
		this.repayYestime = repayYestime;
	}

	public BigDecimal getRepayAccount() {
		return repayAccount;
	}

	public void setRepayAccount(BigDecimal repayAccount) {
		this.repayAccount = repayAccount;
	}

	public BigDecimal getRepayYesaccount() {
		return repayYesaccount;
	}

	public void setRepayYesaccount(BigDecimal repayYesaccount) {
		this.repayYesaccount = repayYesaccount;
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

	public BigDecimal getAdvanceYesaccount() {
		return advanceYesaccount;
	}

	public void setAdvanceYesaccount(BigDecimal advanceYesaccount) {
		this.advanceYesaccount = advanceYesaccount;
	}

	public Date getAdvancetime() {
		return advancetime;
	}

	public void setAdvancetime(Date advancetime) {
		this.advancetime = advancetime;
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
}