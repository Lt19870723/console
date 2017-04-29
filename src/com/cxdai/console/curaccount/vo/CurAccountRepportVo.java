package com.cxdai.console.curaccount.vo;

import java.math.BigDecimal;

/**
 * 
 * <p>
 * Description:账户资金对账<br />
 * </p>
 * 
 * @title CurAccountRepportVo.java
 * @package com.cxdai.console.curaccount.vo
 * @author YangShiJin
 * @version 0.1 2015年5月27日
 */
public class CurAccountRepportVo {

	/** 账户总额 */
	private BigDecimal sumTotal;
	/** 转入总额 */
	private BigDecimal sumInTotal;
	/** 转出总额 */
	private BigDecimal sumOutTotal;
	/** 累计收益总额 */
	private BigDecimal sumInterestTotal;
	/** 收益总额 */
	private BigDecimal sumInterestDetailTotal;
	/** 收益入账总额 */
	private BigDecimal sumAccountLogInterestTotal;
	/** 是否相等 1:相等 0：不相等 */
	private Integer isEqual;

	public BigDecimal getSumTotal() {
		return sumTotal;
	}

	public void setSumTotal(BigDecimal sumTotal) {
		this.sumTotal = sumTotal;
	}

	public BigDecimal getSumInTotal() {
		return sumInTotal;
	}

	public void setSumInTotal(BigDecimal sumInTotal) {
		this.sumInTotal = sumInTotal;
	}

	public BigDecimal getSumOutTotal() {
		return sumOutTotal;
	}

	public void setSumOutTotal(BigDecimal sumOutTotal) {
		this.sumOutTotal = sumOutTotal;
	}

	public BigDecimal getSumInterestTotal() {
		return sumInterestTotal;
	}

	public void setSumInterestTotal(BigDecimal sumInterestTotal) {
		this.sumInterestTotal = sumInterestTotal;
	}

	public Integer getIsEqual() {
		return isEqual;
	}

	public void setIsEqual(Integer isEqual) {
		this.isEqual = isEqual;
	}

	public BigDecimal getSumInterestDetailTotal() {
		return sumInterestDetailTotal;
	}

	public void setSumInterestDetailTotal(BigDecimal sumInterestDetailTotal) {
		this.sumInterestDetailTotal = sumInterestDetailTotal;
	}

	public BigDecimal getSumAccountLogInterestTotal() {
		return sumAccountLogInterestTotal;
	}

	public void setSumAccountLogInterestTotal(BigDecimal sumAccountLogInterestTotal) {
		this.sumAccountLogInterestTotal = sumAccountLogInterestTotal;
	}

}
