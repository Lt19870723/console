package com.cxdai.console.customer.information.vo;

import com.cxdai.console.customer.information.entity.OnlineCounter;
import com.cxdai.console.util.DateUtils;

public class OnlineCounterVo extends OnlineCounter {

	private static final long serialVersionUID = 3966348617679124263L;
	private String logintimeFmt;
	private String platformStr;

	public String getPlatformStr() {
		/* 平台来源 1：官网 2、微信，即用户登录的客户端 */
		return this.getPlatform() == null ? "" : (this.getPlatform() == 1 ? "官网" : "微信");
	}

	public String getLogintimeFmt() {

		return DateUtils.format(this.getLogintime(), DateUtils.YMD_HMS);
	}

}
