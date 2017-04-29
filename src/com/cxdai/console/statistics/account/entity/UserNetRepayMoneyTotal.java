package com.cxdai.console.statistics.account.entity;

import java.math.BigDecimal;

/**
 * 
 * <p>
 * Description:获取用户投标中的净值标预还总额和借款管理费'<br />
 * </p>
 * 
 * @title UserNetRepayMoneyTotal.java
 * @package com.cxdai.console.account.entity
 * @author yangshijin
 * @version 0.1 2014年10月28日
 */
public class UserNetRepayMoneyTotal {

	/** 用户id */
	private Integer userid;
	/** 净值标本息总额 */
	private BigDecimal netRepayMoneyTotal;
	/** 借款管理费总额 */
	private BigDecimal managerFeeTotal;

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public BigDecimal getNetRepayMoneyTotal() {
		return netRepayMoneyTotal;
	}

	public void setNetRepayMoneyTotal(BigDecimal netRepayMoneyTotal) {
		this.netRepayMoneyTotal = netRepayMoneyTotal;
	}

	public BigDecimal getManagerFeeTotal() {
		return managerFeeTotal;
	}

	public void setManagerFeeTotal(BigDecimal managerFeeTotal) {
		this.managerFeeTotal = managerFeeTotal;
	}
}
