package com.cxdai.console.statistics.account.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * Description:每周运营数据<br />
 * </p>
 * 
 * @title YunYingData.java
 * @package com.cxdai.model.account
 * @author liutao
 * @version 0.1 2016年4月22日
 */
public class YunYingData implements Serializable {

	private static final long serialVersionUID = -3531851612222097637L;
     private BigDecimal registeredNumber;//注册人数
     private BigDecimal investmentNumber;//投资人数
     private BigDecimal trainStockTotal;//直通车存量总额
     private BigDecimal trainOffTotal;//直通车下车总额
     private BigDecimal fixStockTotal;//定期宝存量
     
     private BigDecimal repaymentEarly;//提前还款标
     private BigDecimal repaymentMaturity;//到期结清标
     private BigDecimal repaymentAdd;//新增借款标
     private BigDecimal standardContinued;//续贷标
     private BigDecimal repaymentLate ;//逾期标
     
     private BigDecimal repaymentEarlyTotal;//提前还款标
     private BigDecimal repaymentMaturityTotal;//到期结清标
     private BigDecimal repaymentAddTotal;//新增借款标
     private BigDecimal standardContinuedTotal;//续贷标
     private BigDecimal repaymentLateTotal ;//逾期标
	public BigDecimal getRegisteredNumber() {
		return registeredNumber;
	}
	public void setRegisteredNumber(BigDecimal registeredNumber) {
		this.registeredNumber = registeredNumber;
	}
	public BigDecimal getInvestmentNumber() {
		return investmentNumber;
	}
	public void setInvestmentNumber(BigDecimal investmentNumber) {
		this.investmentNumber = investmentNumber;
	}
	public BigDecimal getTrainStockTotal() {
		return trainStockTotal;
	}
	public void setTrainStockTotal(BigDecimal trainStockTotal) {
		this.trainStockTotal = trainStockTotal;
	}
	public BigDecimal getTrainOffTotal() {
		return trainOffTotal;
	}
	public void setTrainOffTotal(BigDecimal trainOffTotal) {
		this.trainOffTotal = trainOffTotal;
	}
	public BigDecimal getFixStockTotal() {
		return fixStockTotal;
	}
	public void setFixStockTotal(BigDecimal fixStockTotal) {
		this.fixStockTotal = fixStockTotal;
	}
	public BigDecimal getRepaymentEarly() {
		return repaymentEarly;
	}
	public void setRepaymentEarly(BigDecimal repaymentEarly) {
		this.repaymentEarly = repaymentEarly;
	}
	public BigDecimal getRepaymentMaturity() {
		return repaymentMaturity;
	}
	public void setRepaymentMaturity(BigDecimal repaymentMaturity) {
		this.repaymentMaturity = repaymentMaturity;
	}
	public BigDecimal getRepaymentAdd() {
		return repaymentAdd;
	}
	public void setRepaymentAdd(BigDecimal repaymentAdd) {
		this.repaymentAdd = repaymentAdd;
	}
	public BigDecimal getStandardContinued() {
		return standardContinued;
	}
	public void setStandardContinued(BigDecimal standardContinued) {
		this.standardContinued = standardContinued;
	}
	public BigDecimal getRepaymentLate() {
		return repaymentLate;
	}
	public void setRepaymentLate(BigDecimal repaymentLate) {
		this.repaymentLate = repaymentLate;
	}
	public BigDecimal getRepaymentEarlyTotal() {
		return repaymentEarlyTotal;
	}
	public void setRepaymentEarlyTotal(BigDecimal repaymentEarlyTotal) {
		this.repaymentEarlyTotal = repaymentEarlyTotal;
	}
	public BigDecimal getRepaymentMaturityTotal() {
		return repaymentMaturityTotal;
	}
	public void setRepaymentMaturityTotal(BigDecimal repaymentMaturityTotal) {
		this.repaymentMaturityTotal = repaymentMaturityTotal;
	}
	public BigDecimal getRepaymentAddTotal() {
		return repaymentAddTotal;
	}
	public void setRepaymentAddTotal(BigDecimal repaymentAddTotal) {
		this.repaymentAddTotal = repaymentAddTotal;
	}
	public BigDecimal getStandardContinuedTotal() {
		return standardContinuedTotal;
	}
	public void setStandardContinuedTotal(BigDecimal standardContinuedTotal) {
		this.standardContinuedTotal = standardContinuedTotal;
	}
	public BigDecimal getRepaymentLateTotal() {
		return repaymentLateTotal;
	}
	public void setRepaymentLateTotal(BigDecimal repaymentLateTotal) {
		this.repaymentLateTotal = repaymentLateTotal;
	}
}
