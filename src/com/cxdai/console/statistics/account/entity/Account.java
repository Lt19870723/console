package com.cxdai.console.statistics.account.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * Description:账户类<br />
 * </p>
 * 
 * @title Account.java
 * @package com.cxdai.model.account
 * @author justin.xu
 * @version 0.1 2014年4月16日
 */
public class Account implements Serializable {

	private static final long serialVersionUID = -3531851612222097637L;
	private Integer id;
	private Integer userId;
	/** 账户总额 */
	private BigDecimal total;
	/** 可用餘額 */
	private BigDecimal useMoney;
	/** 凍結金額 */
	private BigDecimal noUseMoney;
	/** 待收總額 */
	private BigDecimal collection;
	/** 贷款額度 */
	private BigDecimal netvalue;
	/** 优先计划总可用金额 */
	private BigDecimal firstBorrowUseMoney;
	/** 可提现金额 */
	private BigDecimal drawMoney;
	/** 不可提现金额 */
	private BigDecimal noDrawMoney;
	/** 版本号. **/
	private Integer version;

	/** 存管可用金额*/
	private BigDecimal eUseMoney;
	/**存管冻结金额*/
	private BigDecimal eFreezeMoney;
	/** 存管待收金额*/
	private BigDecimal eCollection;
	/**存管可提 **/
	private BigDecimal zsWithdrawamount;

	
	
	public BigDecimal getZsWithdrawamount() {
		return zsWithdrawamount;
	}

	public void setZsWithdrawamount(BigDecimal zsWithdrawamount) {
		this.zsWithdrawamount = zsWithdrawamount;
	}

	/**
	 * 本身版本号，主要用于根据版本号更新
	 */
	private Integer selfVersion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getUseMoney() {
		return useMoney;
	}

	public void setUseMoney(BigDecimal useMoney) {
		this.useMoney = useMoney;
	}

	public BigDecimal getNoUseMoney() {
		return noUseMoney;
	}

	public void setNoUseMoney(BigDecimal noUseMoney) {
		this.noUseMoney = noUseMoney;
	}

	public BigDecimal getCollection() {
		return collection;
	}

	public void setCollection(BigDecimal collection) {
		this.collection = collection;
	}

	public BigDecimal getNetvalue() {
		return netvalue;
	}

	public void setNetvalue(BigDecimal netvalue) {
		this.netvalue = netvalue;
	}

	public BigDecimal getFirstBorrowUseMoney() {
		return firstBorrowUseMoney;
	}

	public void setFirstBorrowUseMoney(BigDecimal firstBorrowUseMoney) {
		this.firstBorrowUseMoney = firstBorrowUseMoney;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getSelfVersion() {
		return selfVersion;
	}

	public void setSelfVersion(Integer selfVersion) {
		this.selfVersion = selfVersion;
	}

	public BigDecimal getDrawMoney() {
		return drawMoney;
	}

	public void setDrawMoney(BigDecimal drawMoney) {
		this.drawMoney = drawMoney;
	}

	public BigDecimal getNoDrawMoney() {
		return noDrawMoney;
	}

	public void setNoDrawMoney(BigDecimal noDrawMoney) {
		this.noDrawMoney = noDrawMoney;
	}
	public BigDecimal geteUseMoney() {return eUseMoney;}

	public void seteUseMoney(BigDecimal eUseMoney) {this.eUseMoney = eUseMoney;}

	public BigDecimal geteFreezeMoney() {return eFreezeMoney;}

	public void seteFreezeMoney(BigDecimal eFreezeMoney) {this.eFreezeMoney = eFreezeMoney;}

	public BigDecimal geteCollection() {return eCollection;}

	public void seteCollection(BigDecimal eCollection) {this.eCollection = eCollection;}


}
