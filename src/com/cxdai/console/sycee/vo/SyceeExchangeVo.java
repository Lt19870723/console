package com.cxdai.console.sycee.vo;

import java.io.Serializable;

import com.cxdai.console.sycee.entity.SyceeExchange;

public class SyceeExchangeVo extends SyceeExchange implements Serializable {

	private static final long serialVersionUID = 1L;

	private Float money;// 实际价值

	private String secondClass;// 商品二级分类：红包，抽奖机会，电影票，话费，充值卡...
	private String showFlag;// 商品上下架：1上架，2下架

	public Float getMoney() {
		return money;
	}

	public void setMoney(Float money) {
		this.money = money;
	}

	public String getSecondClass() {
		return secondClass;
	}

	public void setSecondClass(String secondClass) {
		this.secondClass = secondClass;
	}

	public String getShowFlag() {
		return showFlag;
	}

	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}

}
