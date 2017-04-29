package com.cxdai.console.statistics.account.entity;

import java.math.BigDecimal;

//净值额度=（待收本金+可用资金+投标直通车可用余额+投标直通车冻结金额+投标冻结）*0.8-待还本息
public class UserNetValue {

	private Integer userid;
	private Integer netWaitToPayCount;// 待还款的净值标数量
	private BigDecimal netMoneyLimit;// 净值额度
	private BigDecimal waitReceiveCapital; // 待收本金
	private BigDecimal netvalueUsemoney;// 可用资金
	private BigDecimal netvalueFirstBorrowUseMoney;// 投标直通车可用余额
	private BigDecimal tenderLockAccountTotal;// 投标冻结
	private BigDecimal firstFreezeAccount;// 投标直通车冻结金额
	private BigDecimal repaymentAccountTotal;// 待还本息
	private BigDecimal takeCashFreezeAccount;// 提现冻结
	private BigDecimal transferLockAccountTotal;// 债转冻结

	public BigDecimal getNetMoneyLimit() {
		return netMoneyLimit;
	}

	public void setNetMoneyLimit(BigDecimal netMoneyLimit) {
		this.netMoneyLimit = netMoneyLimit;
	}

	public Integer getNetWaitToPayCount() {
		return netWaitToPayCount;
	}

	public void setNetWaitToPayCount(Integer netWaitToPayCount) {
		this.netWaitToPayCount = netWaitToPayCount;
	}

	public BigDecimal getWaitReceiveCapital() {
		return waitReceiveCapital;
	}

	public void setWaitReceiveCapital(BigDecimal waitReceiveCapital) {
		this.waitReceiveCapital = waitReceiveCapital;
	}

	public BigDecimal getTenderLockAccountTotal() {
		return tenderLockAccountTotal;
	}

	public void setTenderLockAccountTotal(BigDecimal tenderLockAccountTotal) {
		this.tenderLockAccountTotal = tenderLockAccountTotal;
	}

	public BigDecimal getRepaymentAccountTotal() {
		return repaymentAccountTotal;
	}

	public void setRepaymentAccountTotal(BigDecimal repaymentAccountTotal) {
		this.repaymentAccountTotal = repaymentAccountTotal;
	}

	public BigDecimal getFirstFreezeAccount() {
		return firstFreezeAccount;
	}

	public void setFirstFreezeAccount(BigDecimal firstFreezeAccount) {
		this.firstFreezeAccount = firstFreezeAccount;
	}

	public BigDecimal getNetvalueUsemoney() {
		return netvalueUsemoney;
	}

	public void setNetvalueUsemoney(BigDecimal netvalueUsemoney) {
		this.netvalueUsemoney = netvalueUsemoney;
	}

	public BigDecimal getNetvalueFirstBorrowUseMoney() {
		return netvalueFirstBorrowUseMoney;
	}

	public void setNetvalueFirstBorrowUseMoney(BigDecimal netvalueFirstBorrowUseMoney) {
		this.netvalueFirstBorrowUseMoney = netvalueFirstBorrowUseMoney;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public BigDecimal getTakeCashFreezeAccount() {
		return takeCashFreezeAccount;
	}

	public void setTakeCashFreezeAccount(BigDecimal takeCashFreezeAccount) {
		this.takeCashFreezeAccount = takeCashFreezeAccount;
	}

	public BigDecimal getTransferLockAccountTotal() {
		return transferLockAccountTotal;
	}

	public void setTransferLockAccountTotal(BigDecimal transferLockAccountTotal) {
		this.transferLockAccountTotal = transferLockAccountTotal;
	}

}
