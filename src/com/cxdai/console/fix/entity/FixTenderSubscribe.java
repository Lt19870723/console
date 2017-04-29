package com.cxdai.console.fix.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * Description:定期宝投标转让认购<br />
 * </p>
 * 
 * @title FixTenderSubscribe.java
 * @package com.cxdai.console.fix.entity
 * @author HuangJun
 * @version 0.1 2015年6月25日
 */
public class FixTenderSubscribe implements Serializable {

	private static final long serialVersionUID = -6716752330349581059L;

	/**
	 * 主键ID
	 */
	private Integer id;

	/**
	 * 定期宝转让ID
	 */
	private Integer fixBorrowTransferId;

	/**
	 * 定期宝投标转让ID
	 */
	private Integer fixTenderTransferId;

	/**
	 * 转让定期宝ID
	 */
	private Integer oldFixBorrowId;

	/**
	 * 认购定期宝ID
	 */
	private Integer newFixBorrowId;

	/**
	 * 投标ID
	 */
	private Integer tenderId;

	/**
	 * 借款标ID
	 */
	private Integer borrowId;

	/**
	 * 认购金额
	 */
	private BigDecimal account;

	/**
	 * 状态
	 */
	private Integer status;

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
	 * 平台来源
	 */
	private Integer platform;

	/**
	 * 转让定期宝合同编号
	 */
	private String transferContractNo;

	/**
	 * 转让定期名称
	 */
	private String transferBorrowName;

	/**
	 * 认购定期宝合同编号
	 */
	private String subscribeContractNo;

	/**
	 * 认购定期名称
	 */
	private String subscribeBorrowName;

	/**
	 * 备注
	 */
	private String remark;

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

	public Integer getFixTenderTransferId() {
		return fixTenderTransferId;
	}

	public void setFixTenderTransferId(Integer fixTenderTransferId) {
		this.fixTenderTransferId = fixTenderTransferId;
	}

	public Integer getOldFixBorrowId() {
		return oldFixBorrowId;
	}

	public void setOldFixBorrowId(Integer oldFixBorrowId) {
		this.oldFixBorrowId = oldFixBorrowId;
	}

	public Integer getNewFixBorrowId() {
		return newFixBorrowId;
	}

	public void setNewFixBorrowId(Integer newFixBorrowId) {
		this.newFixBorrowId = newFixBorrowId;
	}

	public Integer getTenderId() {
		return tenderId;
	}

	public void setTenderId(Integer tenderId) {
		this.tenderId = tenderId;
	}

	public Integer getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}

	public BigDecimal getAccount() {
		return account;
	}

	public void setAccount(BigDecimal account) {
		this.account = account;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}

	public String getTransferContractNo() {
		return transferContractNo;
	}

	public void setTransferContractNo(String transferContractNo) {
		this.transferContractNo = transferContractNo;
	}

	public String getTransferBorrowName() {
		return transferBorrowName;
	}

	public void setTransferBorrowName(String transferBorrowName) {
		this.transferBorrowName = transferBorrowName;
	}

	public String getSubscribeContractNo() {
		return subscribeContractNo;
	}

	public void setSubscribeContractNo(String subscribeContractNo) {
		this.subscribeContractNo = subscribeContractNo;
	}

	public String getSubscribeBorrowName() {
		return subscribeBorrowName;
	}

	public void setSubscribeBorrowName(String subscribeBorrowName) {
		this.subscribeBorrowName = subscribeBorrowName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}