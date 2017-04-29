package com.cxdai.console.account.reward.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * <p>
 * Description:用户净值额度明细<br />
 * </p>
 * 
 * @title NetvalueLog.java
 * @package com.cxdai.console.account.entity
 * @author yangshijin
 * @version 0.1 2014年10月21日
 */
public class NetvalueLog implements Serializable {
	private static final long serialVersionUID = 1043967256733700558L;

	/** 主键ID */
	private Integer id;
	/** 借款标Id */
	private Integer borrowId;
	/** 用户Id */
	private Integer userId;
	/** 净值额度 */
	private BigDecimal netMoneyLimit;
	/** 待还款的净值标数量 */
	private Integer netWaitTopayCount;
	/** 待收本金 */
	private BigDecimal waitReceiveCapital;
	/** 投标冻结 */
	private BigDecimal tenderLockAccountTotal;
	/** 待还总额 */
	private BigDecimal repaymentAccountTotal;
	/** 投标直通车冻结金额 */
	private BigDecimal firstFreezeAccount;
	/** 可用金额 */
	private BigDecimal useMoney;
	/** 直通车可用金额 */
	private BigDecimal firstBorrowUseMoney;
	/** 提现冻结金额 */
	private BigDecimal takeCashFreezeAccount;
	/** 日志备注 */
	private String remark;
	/** 操作时间 */
	private Date addtime;
	/** 插入IP */
	private String addip;
	/** 类型【0：借款入账，1：网站奖励】 */
	private Integer type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public BigDecimal getNetMoneyLimit() {
		return netMoneyLimit;
	}

	public void setNetMoneyLimit(BigDecimal netMoneyLimit) {
		this.netMoneyLimit = netMoneyLimit;
	}

	public Integer getNetWaitTopayCount() {
		return netWaitTopayCount;
	}

	public void setNetWaitTopayCount(Integer netWaitTopayCount) {
		this.netWaitTopayCount = netWaitTopayCount;
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

	public BigDecimal getUseMoney() {
		return useMoney;
	}

	public void setUseMoney(BigDecimal useMoney) {
		this.useMoney = useMoney;
	}

	public BigDecimal getFirstBorrowUseMoney() {
		return firstBorrowUseMoney;
	}

	public void setFirstBorrowUseMoney(BigDecimal firstBorrowUseMoney) {
		this.firstBorrowUseMoney = firstBorrowUseMoney;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getAddip() {
		return addip;
	}

	public void setAddip(String addip) {
		this.addip = addip;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public BigDecimal getTakeCashFreezeAccount() {
		return takeCashFreezeAccount;
	}

	public void setTakeCashFreezeAccount(BigDecimal takeCashFreezeAccount) {
		this.takeCashFreezeAccount = takeCashFreezeAccount;
	}

}
