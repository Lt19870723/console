package com.cxdai.console.finance.virement.vo;

import java.math.BigDecimal;


public class CheckAccountResponseCnd{

	private Integer type;
	private BigDecimal differenceFee;
	private BigDecimal differenceTotal;
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public BigDecimal getDifferenceFee() {
		return differenceFee;
	}
	public void setDifferenceFee(BigDecimal differenceFee) {
		this.differenceFee = differenceFee;
	}
	public BigDecimal getDifferenceTotal() {
		return differenceTotal;
	}
	public void setDifferenceTotal(BigDecimal differenceTotal) {
		this.differenceTotal = differenceTotal;
	}
	
}
