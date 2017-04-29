package com.cxdai.console.fix.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * Description:定期宝投标转让<br />
 * </p>
 * 
 * @title FixTenderTransfer.java
 * @package com.cxdai.console.fix.entity
 * @author HuangJun
 * @version 0.1 2015年6月25日
 */
public class FixTenderTransfer implements Serializable {

	private static final long serialVersionUID = -2827719189034709973L;

	/**
	 * 主键ID
	 */
	private Integer id;

	/**
	 * 定期宝转让ID
	 */
	private Integer fixBorrowTransferId;

	/**
	 * 定期宝ID
	 */
	private Integer fixBorrowId;

	/**
	 * 借款标ID
	 */
	private Integer borrowId;

	/**
	 * 投标ID
	 */
	private Integer tenderId;

	/**
	 * 债权本金
	 */
	private BigDecimal account;

	/**
	 * 转让价格
	 */
	private BigDecimal accountReal;

	/**
	 * 利息
	 */
	private BigDecimal interest;

	/**
	 * 待收金额
	 */
	private BigDecimal repaymentAccount;

	/**
	 * 添加时间
	 */
	private Date addtime;

	/**
	 * 添加人
	 */
	private Integer adduser;

	/**
	 * 添加IP
	 */
	private String addIp;

	/**
	 * 添加MAC
	 */
	private String addMac;

	/**
	 * 最后修改时间
	 */
	private Date lastUpdateTime;

	/**
	 * 最后修改人
	 */
	private Integer lastUpdateUser;

	/**
	 * 本记录转让成功时间
	 */
	private Date successTime;

	/**
	 * 已经借到的金额
	 */
	private BigDecimal accountYes;

	/**
	 * 标的合同编号
	 */
	private String contractNo;

	/**
	 * 状态
	 */
	private Integer status;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 平台来源
	 */
	private Integer platform;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFixBorrowTransferId() {
		return fixBorrowTransferId;
	}

	public void setFixBorrowTransferId(Integer fixBorrowTransferId) {
		this.fixBorrowTransferId = fixBorrowTransferId;
	}

	public Integer getFixBorrowId() {
		return fixBorrowId;
	}

	public void setFixBorrowId(Integer fixBorrowId) {
		this.fixBorrowId = fixBorrowId;
	}

	public Integer getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}

	public Integer getTenderId() {
		return tenderId;
	}

	public void setTenderId(Integer tenderId) {
		this.tenderId = tenderId;
	}

	public BigDecimal getAccount() {
		return account;
	}

	public void setAccount(BigDecimal account) {
		this.account = account;
	}

	public BigDecimal getAccountReal() {
		return accountReal;
	}

	public void setAccountReal(BigDecimal accountReal) {
		this.accountReal = accountReal;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public BigDecimal getRepaymentAccount() {
		return repaymentAccount;
	}

	public void setRepaymentAccount(BigDecimal repaymentAccount) {
		this.repaymentAccount = repaymentAccount;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Integer getAdduser() {
		return adduser;
	}

	public void setAdduser(Integer adduser) {
		this.adduser = adduser;
	}

	public String getAddIp() {
		return addIp;
	}

	public void setAddIp(String addIp) {
		this.addIp = addIp;
	}

	public String getAddMac() {
		return addMac;
	}

	public void setAddMac(String addMac) {
		this.addMac = addMac;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Integer getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(Integer lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	public Date getSuccessTime() {
		return successTime;
	}

	public void setSuccessTime(Date successTime) {
		this.successTime = successTime;
	}

	public BigDecimal getAccountYes() {
		return accountYes;
	}

	public void setAccountYes(BigDecimal accountYes) {
		this.accountYes = accountYes;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}
}