package com.cxdai.console.customer.information.vo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.cxdai.console.common.page.BaseCnd;
import com.cxdai.console.util.DateUtils;

public class LoginRecordCnd extends BaseCnd implements Serializable {
	private static final long serialVersionUID = 4259299710466796790L;
	/** 用户名 */
	private String userName;
	/** 查询登录开始时间 */
	private String loginStartTime;
	/** 查询登录结束时间 */
	private String loginEndTime;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setLoginStartTime(String loginStartTime) {
		this.loginStartTime = loginStartTime;
	}

	public Date getLoginStartTime() {
		Date earliestTime = null;
		try {
			if (!StringUtils.isEmpty(loginStartTime)) {
				earliestTime = DateUtils.parse(loginStartTime + " 00:00:00", DateUtils.YMD_HMS);
			}
		} catch (Exception e) {
			return earliestTime;
		}
		return earliestTime;
	}

	public Date getLoginEndTime() {
		Date latestTime = null;
		try {
			if (!StringUtils.isEmpty(loginStartTime)) {
				latestTime = DateUtils.parse(loginEndTime + " 23:59:59", DateUtils.YMD_HMS);
			}
		} catch (Exception e) {
			return latestTime;
		}
		return latestTime;
	}

	public void setLoginEndTime(String loginEndTime) {
		this.loginEndTime = loginEndTime;
	}
}
