package com.cxdai.console.curaccount.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 活期宝转入调用参数
 * @author WangQianJin
 * @title 
 * @date 2015年8月27日
 */
public class CurInLaunchVo implements Serializable {
	
	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = -3974695126210247338L;
	
	/**
	 * 投标用户ID
	 */
	private Integer tenderUserId;
	
	/**
	 * 活期宝转出金额
	 */
	private BigDecimal curOutAccount;
	
	/**
	 * 投标资金退回类型
	 */
	private Integer tenderBackType;
	
	/**
	 * 转出日志类型
	 */
	private Integer curLogType;
	
	/**
	 * 账户日志类型
	 */
	private Integer accountLogType;
	
	/**
	 * 活期宝转出ID
	 */
	private Integer curOutId;
	
	/**
	 * ip地址
	 */
	private String addIp;
	
	/**
	 * 平台来源
	 */
	private Integer platform;

	public Integer getTenderUserId() {
		return tenderUserId;
	}

	public void setTenderUserId(Integer tenderUserId) {
		this.tenderUserId = tenderUserId;
	}

	public BigDecimal getCurOutAccount() {
		return curOutAccount;
	}

	public void setCurOutAccount(BigDecimal curOutAccount) {
		this.curOutAccount = curOutAccount;
	}

	public Integer getTenderBackType() {
		return tenderBackType;
	}

	public void setTenderBackType(Integer tenderBackType) {
		this.tenderBackType = tenderBackType;
	}

	public Integer getCurLogType() {
		return curLogType;
	}

	public void setCurLogType(Integer curLogType) {
		this.curLogType = curLogType;
	}

	public Integer getAccountLogType() {
		return accountLogType;
	}

	public void setAccountLogType(Integer accountLogType) {
		this.accountLogType = accountLogType;
	}

	public Integer getCurOutId() {
		return curOutId;
	}

	public void setCurOutId(Integer curOutId) {
		this.curOutId = curOutId;
	}

	public String getAddIp() {
		return addIp;
	}

	public void setAddIp(String addIp) {
		this.addIp = addIp;
	}

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}

	
}
