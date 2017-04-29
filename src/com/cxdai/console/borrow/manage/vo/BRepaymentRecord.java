package com.cxdai.console.borrow.manage.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * Description: 待还记录<br />
 * </p>
 * 
 * @title BRepaymentRecord.java
 * @package com.cxdai.base.entity
 * @author justin.xu
 * @version 0.1 2014年5月15日
 */
public class BRepaymentRecord implements Serializable {
	private static final long serialVersionUID = -1464099049609856305L;
	/** 主键Id */
	private Integer id;
	/** 状态 0---未还 1---已还 */
	private Integer status;
	/** 网站代还状态 0--未代还 1--已代还 */
	private Integer webstatus;
	/** 用户ID */
	private Integer userId;
	/** 排序 */
	private Integer order;
	/** 借款id */
	private Integer borrowId;
	/** 估计还款时间 */
	private String repaymentTime;
	/** 已经还款时间 */
	private String repaymentYestime;
	/** 预还金额 */
	private BigDecimal repaymentAccount;
	/** 实际金额 */
	private BigDecimal repaymentYesaccount;
	/** 逾期天数 */
	private Integer lateDays;
	/** 逾期利息 */
	private BigDecimal lateInterest;
	/** 利息 */
	private BigDecimal interest;
	/** 本金 */
	private BigDecimal capital;
	/** 添加时间 */
	private String addtime;
	/** 添加Ip */
	private String addip;
	/** 垫付时间 **/
	private Date advancetime;
	/** 实际垫付金额 */
	private BigDecimal advanceYesAmount;
	/** 垫付后逾期天数 */
	private Integer afterwebpayLateDay;
	/** 是否补罚息（0：未补，1：已补，2：不处理） **/
	private Integer isRepairInterest;
	/** 补罚息时间 */
	private Date repairInterestTime;

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

	public Integer getWebstatus() {
		return webstatus;
	}

	public void setWebstatus(Integer webstatus) {
		this.webstatus = webstatus;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}

	public String getRepaymentTime() {
		return repaymentTime;
	}

	public void setRepaymentTime(String repaymentTime) {
		this.repaymentTime = repaymentTime;
	}

	public String getRepaymentYestime() {
		return repaymentYestime;
	}

	public void setRepaymentYestime(String repaymentYestime) {
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

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getAddip() {
		return addip;
	}

	public void setAddip(String addip) {
		this.addip = addip;
	}

	public Date getAdvancetime() {
		return advancetime;
	}

	public void setAdvancetime(Date advancetime) {
		this.advancetime = advancetime;
	}

	public Integer getIsRepairInterest() {
		return isRepairInterest;
	}

	public void setIsRepairInterest(Integer isRepairInterest) {
		this.isRepairInterest = isRepairInterest;
	}

	public Date getRepairInterestTime() {
		return repairInterestTime;
	}

	public void setRepairInterestTime(Date repairInterestTime) {
		this.repairInterestTime = repairInterestTime;
	}

	public BigDecimal getAdvanceYesAmount() {
		return advanceYesAmount;
	}

	public void setAdvanceYesAmount(BigDecimal advanceYesAmount) {
		this.advanceYesAmount = advanceYesAmount;
	}

	public Integer getAfterwebpayLateDay() {
		return afterwebpayLateDay;
	}

	public void setAfterwebpayLateDay(Integer afterwebpayLateDay) {
		this.afterwebpayLateDay = afterwebpayLateDay;
	}
}
