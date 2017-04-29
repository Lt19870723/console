package com.cxdai.console.account.cash.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Description:提现操作日志<br />
 * </p>
 * 
 * @title CashRecordlog.java
 * @package com.cxdai.base.entity
 * @author justin.xu
 * @version 0.1 2014年11月10日
 */
public class CashRecordlog implements Serializable {
	private static final long serialVersionUID = 253259912412431081L;
	/** 主键 */
	private Integer id;
	/** 提现记录id */
	private Integer cashrecordId;
	/** 操作类型（3:取消提现) */
	private Integer type;
	/** 添加时间 */
	private Date addtime;
	/** 添加IP */
	private String addip;
	/** 添加人 */
	private Integer addUserId;
	/** 平台来源(1：网页 2、微信) */
	private Integer platform;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCashrecordId() {
		return cashrecordId;
	}

	public void setCashrecordId(Integer cashrecordId) {
		this.cashrecordId = cashrecordId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public Integer getAddUserId() {
		return addUserId;
	}

	public void setAddUserId(Integer addUserId) {
		this.addUserId = addUserId;
	}

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}

}
