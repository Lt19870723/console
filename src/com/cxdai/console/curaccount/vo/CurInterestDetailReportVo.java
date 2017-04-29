package com.cxdai.console.curaccount.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * <p>
 * Description:活期宝收益对账<br />
 * </p>
 * 
 * @title CurInterestDetailReportVo.java
 * @package com.cxdai.console.curaccount.vo
 * @author YangShiJin
 * @version 0.1 2015年5月27日
 */
public class CurInterestDetailReportVo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 计算收益个数
	 */
	private Integer detailCount;

	/**
	 * 收益入账个数
	 */
	private Integer accountLogCount;

	/**
	 * 收益总额
	 */
	private BigDecimal detailMoneyTotal;

	/**
	 * 入账总额
	 */
	private BigDecimal accountLogMoneyTotal;

	/**
	 * 是否存在重复计算收益（1： 存在，0： 存在）
	 */
	private Integer isRepeatDetail;

	/**
	 * 是否存在重复收益入账（1： 存在，0： 存在）
	 */
	private Integer isRepeatAccountLog;

	/**
	 * 是否存在收益入账出错（1： 存在，0： 存在）
	 */
	private Integer isEnterMoney;

	/**
	 * 是否存在未计算收益的用户（1： 存在，0： 存在）
	 */
	private Integer isUserNotHaveInterestDetial;

	/**
	 * 是否存在未收益入账的用户（1： 存在，0： 存在）
	 */
	private Integer isUserNotHaveAccountLog;

	public Integer getDetailCount() {
		return detailCount;
	}

	public void setDetailCount(Integer detailCount) {
		this.detailCount = detailCount;
	}

	public Integer getAccountLogCount() {
		return accountLogCount;
	}

	public void setAccountLogCount(Integer accountLogCount) {
		this.accountLogCount = accountLogCount;
	}

	public BigDecimal getDetailMoneyTotal() {
		return detailMoneyTotal;
	}

	public void setDetailMoneyTotal(BigDecimal detailMoneyTotal) {
		this.detailMoneyTotal = detailMoneyTotal;
	}

	public BigDecimal getAccountLogMoneyTotal() {
		return accountLogMoneyTotal;
	}

	public void setAccountLogMoneyTotal(BigDecimal accountLogMoneyTotal) {
		this.accountLogMoneyTotal = accountLogMoneyTotal;
	}

	public Integer getIsRepeatDetail() {
		return isRepeatDetail;
	}

	public void setIsRepeatDetail(Integer isRepeatDetail) {
		this.isRepeatDetail = isRepeatDetail;
	}

	public Integer getIsRepeatAccountLog() {
		return isRepeatAccountLog;
	}

	public void setIsRepeatAccountLog(Integer isRepeatAccountLog) {
		this.isRepeatAccountLog = isRepeatAccountLog;
	}

	public Integer getIsEnterMoney() {
		return isEnterMoney;
	}

	public void setIsEnterMoney(Integer isEnterMoney) {
		this.isEnterMoney = isEnterMoney;
	}

	public Integer getIsUserNotHaveInterestDetial() {
		return isUserNotHaveInterestDetial;
	}

	public void setIsUserNotHaveInterestDetial(Integer isUserNotHaveInterestDetial) {
		this.isUserNotHaveInterestDetial = isUserNotHaveInterestDetial;
	}

	public Integer getIsUserNotHaveAccountLog() {
		return isUserNotHaveAccountLog;
	}

	public void setIsUserNotHaveAccountLog(Integer isUserNotHaveAccountLog) {
		this.isUserNotHaveAccountLog = isUserNotHaveAccountLog;
	}
}
