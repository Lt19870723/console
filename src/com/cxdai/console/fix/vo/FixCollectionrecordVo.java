package com.cxdai.console.fix.vo;

import java.math.BigDecimal;

import com.cxdai.console.fix.entity.FixCollectionrecord;

/**
 * <p>
 * Description:定期宝投资人待收记录vo <br />
 * </p>
 * 
 * @title FixCollectionrecordVo.java
 * @package com.cxdai.console.fix.vo
 * @author HuangJun
 * @version 0.1 2015年6月28日
 */
public class FixCollectionrecordVo extends FixCollectionrecord {

	// 待收总额，本金，利息的和
	private BigDecimal sumRepayAccount;
	private BigDecimal sumCapital;
	private BigDecimal sumInterest;

	/**
	 * @return sumRepayAccount : return the property sumRepayAccount.
	 */
	public BigDecimal getSumRepayAccount() {
		return sumRepayAccount;
	}

	/**
	 * @param sumRepayAccount
	 *            : set the property sumRepayAccount.
	 */
	public void setSumRepayAccount(BigDecimal sumRepayAccount) {
		this.sumRepayAccount = sumRepayAccount;
	}

	/**
	 * @return sumCapital : return the property sumCapital.
	 */
	public BigDecimal getSumCapital() {
		return sumCapital;
	}

	/**
	 * @param sumCapital
	 *            : set the property sumCapital.
	 */
	public void setSumCapital(BigDecimal sumCapital) {
		this.sumCapital = sumCapital;
	}

	/**
	 * @return sumInterest : return the property sumInterest.
	 */
	public BigDecimal getSumInterest() {
		return sumInterest;
	}

	/**
	 * @param sumInterest
	 *            : set the property sumInterest.
	 */
	public void setSumInterest(BigDecimal sumInterest) {
		this.sumInterest = sumInterest;
	}

}