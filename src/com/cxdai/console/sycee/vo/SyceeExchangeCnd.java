package com.cxdai.console.sycee.vo;

import java.io.Serializable;

import com.cxdai.console.common.page.BaseCnd;

public class SyceeExchangeCnd extends BaseCnd implements  Serializable {

	private static final long serialVersionUID = -5890332628953747763L;
 
	private String username;//会员名称
	private String name ;//商品名称
	private String exchangeStart;// 兑换开始时间
	private String exchangeEnd;// 兑换结束时间
	private String dealStatus;//
	private String realname;//
	
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getDealStatus() {
		return dealStatus;
	}
	public void setDealStatus(String dealStatus) {
		this.dealStatus = dealStatus;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExchangeStart() {
		return exchangeStart;
	}
	public void setExchangeStart(String exchangeStart) {
		this.exchangeStart = exchangeStart;
	}
	public String getExchangeEnd() {
		return exchangeEnd;
	}
	public void setExchangeEnd(String exchangeEnd) {
		 this.exchangeEnd = exchangeEnd;
	}
	
	
}
