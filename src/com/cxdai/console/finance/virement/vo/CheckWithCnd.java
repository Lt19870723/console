package com.cxdai.console.finance.virement.vo;

import java.math.BigDecimal;


public class CheckWithCnd{

	private Integer id;
	private BigDecimal totalExpenditure;
	private BigDecimal counterFee;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public BigDecimal getTotalExpenditure() {
		return totalExpenditure;
	}
	public void setTotalExpenditure(BigDecimal totalExpenditure) {
		this.totalExpenditure = totalExpenditure;
	}
	public BigDecimal getCounterFee() {
		return counterFee;
	}
	public void setCounterFee(BigDecimal counterFee) {
		this.counterFee = counterFee;
	}
	
}
