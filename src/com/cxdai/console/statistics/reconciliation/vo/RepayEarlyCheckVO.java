package com.cxdai.console.statistics.reconciliation.vo;

import java.math.BigDecimal;

/**
 * <p>
 * Description:提前还款对账报表<br />
 * </p>
 * 
 * @title RepayEarlyCheckVO.java
 * @package com.cxdai.console.account.vo
 * @author justin.xu
 * @version 0.1 2015年1月24日
 */
public class RepayEarlyCheckVO {

	/**
	 * 1、投资人提前还款扣除总额【资金明细表】
	 */
	private BigDecimal subtractInterestTotal;
	/**
	 * 2、投资人应扣总额 = 投资人应收总额 - 投资人已收总额【待收表】
	 */
	private BigDecimal collectionSubtractTotal;
	/**
	 * 3、提前还款小账户进出资金 【小账号表】
	 */
	private BigDecimal websiteEarlyTotal;
	/**
	 * 4、借款人提前还款增加总额【资金明细表】
	 */
	private BigDecimal addInterestTotal;
	/**
	 * 5、借款人实际增加总额=借款人应还总额-借款人已还总额【待还表】
	 */
	private BigDecimal repaymentAddTotal;
	/**
	 * 1+3、2+3,4、5是否一致结果
	 */
	private String compareResult;

	public BigDecimal getSubtractInterestTotal() {
		return subtractInterestTotal;
	}

	public void setSubtractInterestTotal(BigDecimal subtractInterestTotal) {
		this.subtractInterestTotal = subtractInterestTotal;
	}

	public BigDecimal getCollectionSubtractTotal() {
		return collectionSubtractTotal;
	}

	public void setCollectionSubtractTotal(BigDecimal collectionSubtractTotal) {
		this.collectionSubtractTotal = collectionSubtractTotal;
	}

	public BigDecimal getWebsiteEarlyTotal() {
		return websiteEarlyTotal;
	}

	public void setWebsiteEarlyTotal(BigDecimal websiteEarlyTotal) {
		this.websiteEarlyTotal = websiteEarlyTotal;
	}

	public BigDecimal getAddInterestTotal() {
		return addInterestTotal;
	}

	public void setAddInterestTotal(BigDecimal addInterestTotal) {
		this.addInterestTotal = addInterestTotal;
	}

	public BigDecimal getRepaymentAddTotal() {
		return repaymentAddTotal;
	}

	public void setRepaymentAddTotal(BigDecimal repaymentAddTotal) {
		this.repaymentAddTotal = repaymentAddTotal;
	}

	public String getCompareResult() {
		// 比较 1+3、2+3,4、5是否一致
		if ((subtractInterestTotal.add(websiteEarlyTotal).add(collectionSubtractTotal).add(websiteEarlyTotal).add(addInterestTotal).add(repaymentAddTotal)).compareTo(repaymentAddTotal
				.multiply(new BigDecimal("4"))) == 0) {
			return "true";
		}
		return "false";
	}
}
