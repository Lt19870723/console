package com.cxdai.console.transfer.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.cxdai.console.util.DateUtils;

public class SubscribeVo implements Serializable {

	/**
	 * @fields serialVersionUID
	 */

	private static final long serialVersionUID = 1L;
	private String subscribeUsername;
	private BigDecimal account; // 认购总本金
	private Date addTime; // 认购时间
	private String addTimeStr;

	public String getSubscribeUsername() {
		return subscribeUsername;
	}

	public void setSubscribeUsername(String subscribeUsername) {
		this.subscribeUsername = subscribeUsername;
	}

	public BigDecimal getAccount() {
		return account;
	}

	public void setAccount(BigDecimal account) {
		this.account = account;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getAddTimeStr() {
		if (addTime != null) {
			return DateUtils.format(addTime, DateUtils.YMD_HMS);
		}
		return addTimeStr;
	}

	public void setAddTimeStr(String addTimeStr) {
		this.addTimeStr = addTimeStr;
	}
 


}
