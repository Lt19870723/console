package com.cxdai.console.fix.vo;

import java.math.BigDecimal;

import com.cxdai.console.fix.entity.FixAccount;

/**
 * <p>
 * Description:定期宝账户类<br />
 * </p>
 * 
 * @title FixAccount.java
 * @package com.cxdai.base.entity
 * @author caodefeng
 * @version 0.1 2015年5月18日
 */
public class FixAccountVo extends FixAccount{
	
	/**
	 * 主键id
	 */
	private Integer id; 
	
	/**
	 * 定期宝Id
	 */
	private Integer fixBorrowId; 
	
	/**
	 * 账户总额
	 */
	private BigDecimal total; 
	
	/**
	 * 可用余额
	 */
	private BigDecimal useMoney; 
	
	/**
	 * 冻结金额
	 */
	private BigDecimal noUseMoney; 
	
	/**
	 * 待收总额
	 */
	private BigDecimal collection; 
	
	
	/**
	 * 本金
	 */
	private BigDecimal capital; 
	

	/**
	 * 实际收益
	 */
	private BigDecimal profit; 
	
	/**
	 * 待还金额
	 */
	private BigDecimal repaymentYesAccount; 
	
	
	/**
	 * 合同
	 */
	private String  contractNo; 
	public Integer getId () {
		return id;
	}
	
	
	public void setId (Integer id) {
		this.id = id;
	}
	
	public Integer getFixBorrowId () {
		return fixBorrowId;
	}
	
	
	public void setFixBorrowId (Integer fixBorrowId) {
		this.fixBorrowId = fixBorrowId;
	}
	
	public BigDecimal getTotal () {
		return total;
	}
	
	
	public void setTotal (BigDecimal total) {
		this.total = total;
	}
	
	public BigDecimal getUseMoney () {
		return useMoney;
	}
	
	
	public void setUseMoney (BigDecimal useMoney) {
		this.useMoney = useMoney;
	}
	
	public BigDecimal getNoUseMoney () {
		return noUseMoney;
	}
	
	
	public void setNoUseMoney (BigDecimal noUseMoney) {
		this.noUseMoney = noUseMoney;
	}
	
	public BigDecimal getCollection () {
		return collection;
	}
	
	
	public void setCollection (BigDecimal collection) {
		this.collection = collection;
	}


	public BigDecimal getCapital() {
		return capital;
	}


	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}


	public BigDecimal getProfit() {
		return profit;
	}


	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}


	public BigDecimal getRepaymentYesAccount() {
		return repaymentYesAccount;
	}


	public void setRepaymentYesAccount(BigDecimal repaymentYesAccount) {
		this.repaymentYesAccount = repaymentYesAccount;
	}


	public String getContractNo() {
		return contractNo;
	}


	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}


}
