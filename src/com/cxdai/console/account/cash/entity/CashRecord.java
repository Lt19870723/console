package com.cxdai.console.account.cash.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * Description:提现记录<br />
 * </p>
 * 
 * @title CashRecord.java
 * @package com.cxdai.base.entity
 * @author justin.xu
 * @version 0.1 2014年6月18日
 */
public class CashRecord implements Serializable {
	private static final long serialVersionUID = 253259912412431081L;

	private Integer id;
	private Integer userId;
	/** 状态【-1：审核失败；0：申请提现；1：审核通过；2：打款结束】 3:取消提现 */
	private Integer status;
	/** 账户 */
	private String account;
	/** 银行 */
	private String bank;
	/** 所属分行 */
	private String branch;
	/** 联行号 */
	private Long cnapsCode;
	/** 总金额 */
	private BigDecimal total;
	/** 到账总额 */
	private BigDecimal credited;
	/** 手续费 */
	private BigDecimal fee;
	/** 审核人ID */
	private Integer verifyUserid;
	/** 审核时间 */
	private String verifyTime;
	/** 审核备注 */
	private String verifyRemark;
	/** 打款ID */
	private Integer verifyUserid2;
	/** 打款时间 */
	private String verifyTime2;
	/** 打款备注 */
	private String verifyRemark2;
	/** 作废ID */
	private Integer verifyUserid3;
	/** 作废时间 */
	private String verifyTime3;
	/** 作废备注 */
	private String verifyRemark3;
	/** 添加时间 */
	private String addtime;
	/** 添加ip */
	private String addip;
	/** 版本号. **/
	private Integer version;
	/** 是否已导出(0：未导出，1：已导出) */
	private Integer isExport;
	/** 导出人 */
	private Integer exportUserId;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getCredited() {
		return credited;
	}

	public void setCredited(BigDecimal credited) {
		this.credited = credited;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public Integer getVerifyUserid() {
		return verifyUserid;
	}

	public void setVerifyUserid(Integer verifyUserid) {
		this.verifyUserid = verifyUserid;
	}

	public String getVerifyTime() {
		return verifyTime;
	}

	public void setVerifyTime(String verifyTime) {
		this.verifyTime = verifyTime;
	}

	public String getVerifyRemark() {
		return verifyRemark;
	}

	public void setVerifyRemark(String verifyRemark) {
		this.verifyRemark = verifyRemark;
	}

	public Integer getVerifyUserid2() {
		return verifyUserid2;
	}

	public void setVerifyUserid2(Integer verifyUserid2) {
		this.verifyUserid2 = verifyUserid2;
	}

	public String getVerifyTime2() {
		return verifyTime2;
	}

	public void setVerifyTime2(String verifyTime2) {
		this.verifyTime2 = verifyTime2;
	}

	public String getVerifyRemark2() {
		return verifyRemark2;
	}

	public void setVerifyRemark2(String verifyRemark2) {
		this.verifyRemark2 = verifyRemark2;
	}

	public Integer getVerifyUserid3() {
		return verifyUserid3;
	}

	public void setVerifyUserid3(Integer verifyUserid3) {
		this.verifyUserid3 = verifyUserid3;
	}

	public String getVerifyTime3() {
		return verifyTime3;
	}

	public void setVerifyTime3(String verifyTime3) {
		this.verifyTime3 = verifyTime3;
	}

	public String getVerifyRemark3() {
		return verifyRemark3;
	}

	public void setVerifyRemark3(String verifyRemark3) {
		this.verifyRemark3 = verifyRemark3;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getAddip() {
		return addip;
	}

	public void setAddip(String addip) {
		this.addip = addip;
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

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public Long getCnapsCode() {
		return cnapsCode;
	}

	public void setCnapsCode(Long cnapsCode) {
		this.cnapsCode = cnapsCode;
	}

	public Integer getIsExport() {
		return isExport;
	}

	public void setIsExport(Integer isExport) {
		this.isExport = isExport;
	}

	public Integer getExportUserId() {
		return exportUserId;
	}

	public void setExportUserId(Integer exportUserId) {
		this.exportUserId = exportUserId;
	}
}
