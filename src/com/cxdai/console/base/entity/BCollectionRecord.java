package com.cxdai.console.base.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * Description:待收记录<br />
 * </p>
 * 
 * @title BCollectionRecord.java
 * @package com.cxdai.base.entity
 * @author justin.xu
 * @version 0.1 2014年5月15日
 */
public class BCollectionRecord implements Serializable {

	private static final long serialVersionUID = -7100330584784625365L;
	/** 主键Id */
	private Integer id;
	/** 标的状态,0:尚未还款，1：已还款，2：网站垫付 ，3:垫付后还款 */
	private Integer status;
	/** 顺序 */
	private Integer order;
	/** 借款id */
	private Integer tenderId;
	/** 估计还款时间 */
	private String repayTime;
	/** 已经还款时间 */
	private String repayYestime;
	/** 预还金额 */
	private BigDecimal repayAccount;
	/** 实还金额 */
	private BigDecimal repayYesaccount;
	/** 利息 */
	private BigDecimal interest;
	/** 本金 */
	private BigDecimal capital;
	/** 逾期天数 */
	private Integer lateDays;
	/** 逾期利息 */
	private BigDecimal lateInterest;
	/** 添加时间 */
	private String addtime;
	/** 添加ip */
	private String addip;
	/** 垫付时间 **/
	private Date advancetime;
	/** 优先投标计划ID */
	private Integer firstBorrowId;
	/** 优先投标计划是否失效（1：未失效，2：已失效） */
	private Integer isFirstBorrow;
	/** 用户ID */
	private Integer userId;
	/** 借款标ID */
	private Integer borrowId;
	/** 优先投标最终认购记录id */
	private Integer firstTenderRealId;

	private BigDecimal advanceYesaccount;
	
	/** 银行还款流水号*/
	private String eRepayMentNo;
	/** 银行还款状态(20：成功30：失败)*/
	private Integer eRepayMentStatus;	
	/** 银行清算时间*/
	private String eRepayMentDate;		
	/** 存管银行卡主键*/
	private Integer eBankInfoNo;	
	/** 平台还款业务流水号*/
	private String bizRepayMentNo;
	/** 存管方式(0：非存管，1：浙商存管)*/
	private Integer isCustody;	

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

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getTenderId() {
		return tenderId;
	}

	public void setTenderId(Integer tenderId) {
		this.tenderId = tenderId;
	}

	public String getRepayTime() {
		return repayTime;
	}

	public void setRepayTime(String repayTime) {
		this.repayTime = repayTime;
	}

	public String getRepayYestime() {
		return repayYestime;
	}

	public void setRepayYestime(String repayYestime) {
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

	public Integer getFirstBorrowId() {
		return firstBorrowId;
	}

	public void setFirstBorrowId(Integer firstBorrowId) {
		this.firstBorrowId = firstBorrowId;
	}

	public Integer getIsFirstBorrow() {
		return isFirstBorrow;
	}

	public void setIsFirstBorrow(Integer isFirstBorrow) {
		this.isFirstBorrow = isFirstBorrow;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}

	public Integer getFirstTenderRealId() {
		return firstTenderRealId;
	}

	public void setFirstTenderRealId(Integer firstTenderRealId) {
		this.firstTenderRealId = firstTenderRealId;
	}

	public BigDecimal getAdvanceYesaccount() {
		return advanceYesaccount;
	}

	public void setAdvanceYesaccount(BigDecimal advanceYesaccount) {
		this.advanceYesaccount = advanceYesaccount;
	}

	public String geteRepayMentNo() {
		return eRepayMentNo;
	}

	public void seteRepayMentNo(String eRepayMentNo) {
		this.eRepayMentNo = eRepayMentNo;
	}

	public Integer geteRepayMentStatus() {
		return eRepayMentStatus;
	}

	public void seteRepayMentStatus(Integer eRepayMentStatus) {
		this.eRepayMentStatus = eRepayMentStatus;
	}

	public String geteRepayMentDate() {
		return eRepayMentDate;
	}

	public void seteRepayMentDate(String eRepayMentDate) {
		this.eRepayMentDate = eRepayMentDate;
	}

	public Integer geteBankInfoNo() {
		return eBankInfoNo;
	}

	public void seteBankInfoNo(Integer eBankInfoNo) {
		this.eBankInfoNo = eBankInfoNo;
	}

	public String getBizRepayMentNo() {
		return bizRepayMentNo;
	}

	public void setBizRepayMentNo(String bizRepayMentNo) {
		this.bizRepayMentNo = bizRepayMentNo;
	}

	public Integer getIsCustody() {
		return isCustody;
	}

	public void setIsCustody(Integer isCustody) {
		this.isCustody = isCustody;
	}

}
