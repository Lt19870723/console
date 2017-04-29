package com.cxdai.console.borrow.approve.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.cxdai.console.util.MoneyUtil;



/**
 * 
 * @author hujianpan 查询区间待收金额汇总
 * 
 */
public class CollectionStatisticVo implements Serializable {

	public Integer getIsPay() {
		return isPay;
	}

	public void setIsPay(Integer isPay) {
		this.isPay = isPay;
	}

	/**
	 * @fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/** 是否已收 */
	private Integer isPay;
	/** 预还金额汇总--待收汇总 */
	private BigDecimal repayAccountSum;
	/** 实还金额汇总--已收汇总 */
	private BigDecimal repayYesAccountSum;
	/** 本金汇总 */
	private BigDecimal capitalSum;
	/** 利息汇总 */
	private BigDecimal interestSum;
	/** 逾期罚息汇总 */
	private BigDecimal lateInterestSum;
	private BigDecimal totalSum;
	/************* begin formater Attribute ****************/
	private String repayAccountSumFmt;
	private String repayYesAccountSumFmt;
	private String capitalSumFmt;
	private String interestSumFmt;
	private String totalSumFmt;
	private String lateInterestSumFmt;

	/************* end formater Attribute ****************/

	/*************************************** begin get() set() method *****************************************************************/

	public String getRepayAccountSumFmt() {
		return MoneyUtil.roundMoney(repayAccountSum);
	}

	public CollectionStatisticVo() {
		super();
		this.isPay = Integer.valueOf(0);
		this.repayAccountSum = BigDecimal.ZERO;
		this.repayYesAccountSum = BigDecimal.ZERO;
		this.capitalSum = BigDecimal.ZERO;
		this.interestSum = BigDecimal.ZERO;
		this.lateInterestSum = BigDecimal.ZERO;
		this.totalSum = BigDecimal.ZERO;
	}

	public String getLateInterestSumFmt() {
		return MoneyUtil.roundMoney(lateInterestSum);
	}

	public String getRepayYesAccountSumFmt() {
		return MoneyUtil.roundMoney(repayYesAccountSum);
	}

	public String getCapitalSumFmt() {
		return MoneyUtil.roundMoney(capitalSum);
	}

	public String getInterestSumFmt() {
		return MoneyUtil.roundMoney(interestSum);
	}

	public String getTotalSumFmt() {
		if (isPay == 1) {// 实收总额
			return MoneyUtil.roundMoney(repayYesAccountSum.add(lateInterestSum));
		} else if (isPay == 0) {// 待收总额
			return MoneyUtil.roundMoney(repayAccountSum.add(lateInterestSum));
		} else {
			return MoneyUtil.roundMoney(BigDecimal.ZERO);
		}

	}

	public BigDecimal getRepayAccountSum() {
		return repayAccountSum;
	}

	public void setRepayAccountSum(BigDecimal repayAccountSum) {
		this.repayAccountSum = repayAccountSum;
	}

	public BigDecimal getRepayYesAccountSum() {
		return repayYesAccountSum;
	}

	public void setRepayYesAccountSum(BigDecimal repayYesAccountSum) {
		this.repayYesAccountSum = repayYesAccountSum;
	}

	public BigDecimal getCapitalSum() {
		return capitalSum;
	}

	public void setCapitalSum(BigDecimal capitalSum) {
		this.capitalSum = capitalSum;
	}

	public BigDecimal getInterestSum() {
		return interestSum;
	}

	public void setInterestSum(BigDecimal interestSum) {
		this.interestSum = interestSum;
	}

	public BigDecimal getLateInterestSum() {
		return lateInterestSum;
	}

	public void setLateInterestSum(BigDecimal lateInterestSum) {
		this.lateInterestSum = lateInterestSum;
	}

	public BigDecimal getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(BigDecimal totalSum) {
		this.totalSum = totalSum;
	}

}
