package com.cxdai.console.lottery.vo;

import com.cxdai.console.lottery.entity.BaseGoodSendInfo;

public class GoodSendInfo extends BaseGoodSendInfo {

	String userName;

	String goodName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

}