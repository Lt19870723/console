package com.cxdai.console.base.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * Description:优先投标计划开通明细<br />
 * </p>
 * 
 * @title FirstTenderDetail.java
 * @package com.cxdai.base.entity
 * @author justin.xu
 * @version 0.1 2014年7月11日
 */
public class FirstTenderDetail implements Serializable {
	private static final long serialVersionUID = -191815169850848319L;
	/**
	 * 主键Id
	 */
	private Integer id;
	/**
	 * 用户Id
	 */
	private Integer userId;
	/**
	 * 优先投标计划ID
	 */
	private Integer firstBorrowId;
	/**
	 * 开通状态（ 0 ：投标中 1 ：投标成功 2：投标失败
	 */
	private Integer status;
	/**
	 * 开通金额
	 */
	private Integer account;
	/**
	 * 添加时间
	 */
	private Date addtime;
	/**
	 * 添加IP
	 */
	private String addIp;
	/** 可提现金额 */
	private BigDecimal drawMoney;
	/** 不可提现金额 */
	private BigDecimal noDrawMoney;
	/** 平台来源 */
	private String platform;
	/**
	 * 版本号
	 */
	private String version;
	/**
	 * 本身版本号，主要用于根据版本号更新
	 */
	private String selfVersion;
	/**
	 * 直通车最终记录id
	 */
	private Integer firstTenderRealId;

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

	public Integer getFirstBorrowId() {
		return firstBorrowId;
	}

	public void setFirstBorrowId(Integer firstBorrowId) {
		this.firstBorrowId = firstBorrowId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAccount() {
		return account;
	}

	public void setAccount(Integer account) {
		this.account = account;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getAddIp() {
		return addIp;
	}

	public void setAddIp(String addIp) {
		this.addIp = addIp;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSelfVersion() {
		return selfVersion;
	}

	public void setSelfVersion(String selfVersion) {
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

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public Integer getFirstTenderRealId() {
		return firstTenderRealId;
	}

	public void setFirstTenderRealId(Integer firstTenderRealId) {
		this.firstTenderRealId = firstTenderRealId;
	}

}
