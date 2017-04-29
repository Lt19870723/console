package com.cxdai.console.base.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * Description:投标记录<br />
 * </p>
 * 
 * @title BTenderRecord.java
 * @package com.cxdai.base.entity
 * @author justin.xu
 * @version 0.1 2014年5月15日
 */
public class BTenderRecord implements Serializable {
	private static final long serialVersionUID = -6008307072262760924L;
	/** 主键Id */
	private Integer id;
	/** 用户Id */
	private Integer userId;
	/** 借款Id */
	private Integer borrowId;
	/** 投标状态（ -1：所投标失败 ，0：正在投标 ，1：所投标还款中 ，2：所投标完成结束） */
	private Integer status;
	/** 投标金额 */
	private BigDecimal account;
	/** 利息 */
	private BigDecimal interest;
	/** 待收金额 */
	private BigDecimal repaymentAccount;
	/** 已还总额 */
	private BigDecimal repaymentYesaccount;
	/** 已还利息 */
	private BigDecimal repaymentYesinterest;
	/** 添加时间 */
	private String addtime;
	/** 添加IP */
	private String addip;
	/** 投标方式（0：手动投标，1：自动投标，2：直通车投标，3：定期宝投标，4：权证人员投标） */
	private Integer tenderType;
	/** 自动投标排名位数 */
	private Integer autotenderOrder;
	/** 自动排名位置不变备注 */
	private String autotenderOrderRemark;
	/** 优先投标计划ID */
	private Integer firstBorrowId;
	/** 优先投标计划投标比例 */
	private String firstBorrowScale;
	/** 用户等级 */
	private String userLevel;
	/** 利息管理费比率 */
	private String ratio;
	/** 是否是VIP 1:是：0：否 */
	private Integer isVip;

	/** 可提现金额 */
	private BigDecimal drawMoney;
	/** 不可提现金额 */
	private BigDecimal noDrawMoney;
	/** 自动类型（1：按抵押标、担保标投标，2：按净值标、信用标投标 */
	private Integer autoType;

	/**
	 * 父ID,用于债权转让
	 */
	private Integer parentId;

	/**
	 * 平台来源(1：网页 2、微信)
	 */
	private Integer platform;

	/**
	 * 优先投标最终认购记录ID
	 */
	private Integer firstTenderRealId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BigDecimal getAccount() {
		return account;
	}

	public void setAccount(BigDecimal account) {
		this.account = account;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
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

	public BigDecimal getRepaymentYesinterest() {
		return repaymentYesinterest;
	}

	public void setRepaymentYesinterest(BigDecimal repaymentYesinterest) {
		this.repaymentYesinterest = repaymentYesinterest;
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

	public Integer getTenderType() {
		return tenderType;
	}

	public void setTenderType(Integer tenderType) {
		this.tenderType = tenderType;
	}

	public Integer getAutotenderOrder() {
		return autotenderOrder;
	}

	public void setAutotenderOrder(Integer autotenderOrder) {
		this.autotenderOrder = autotenderOrder;
	}

	public String getAutotenderOrderRemark() {
		return autotenderOrderRemark;
	}

	public void setAutotenderOrderRemark(String autotenderOrderRemark) {
		this.autotenderOrderRemark = autotenderOrderRemark;
	}

	public Integer getFirstBorrowId() {
		return firstBorrowId;
	}

	public void setFirstBorrowId(Integer firstBorrowId) {
		this.firstBorrowId = firstBorrowId;
	}

	public String getFirstBorrowScale() {
		return firstBorrowScale;
	}

	public void setFirstBorrowScale(String firstBorrowScale) {
		this.firstBorrowScale = firstBorrowScale;
	}

	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	public String getRatio() {
		return ratio;
	}

	public void setRatio(String ratio) {
		this.ratio = ratio;
	}

	public BigDecimal getDrawMoney() {
		return drawMoney;
	}

	public void setDrawMoney(BigDecimal drawMoney) {
		this.drawMoney = drawMoney;
	}

	public BigDecimal getNoDrawMoney() {
		return noDrawMoney;
	}

	public void setNoDrawMoney(BigDecimal noDrawMoney) {
		this.noDrawMoney = noDrawMoney;
	}

	public Integer getAutoType() {
		return autoType;
	}

	public void setAutoType(Integer autoType) {
		this.autoType = autoType;
	}

	public Integer getIsVip() {
		return isVip;
	}

	public void setIsVip(Integer isVip) {
		this.isVip = isVip;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}

	public Integer getFirstTenderRealId() {
		return firstTenderRealId;
	}

	public void setFirstTenderRealId(Integer firstTenderRealId) {
		this.firstTenderRealId = firstTenderRealId;
	}
}
