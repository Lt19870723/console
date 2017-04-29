package com.cxdai.console.lottery.entity;

import java.io.Serializable;

/**
 * 元宝商城打折信息
 * 
 * @author Administrator
 */
public class ChanceInfoSignSet implements Serializable {

	private static final long serialVersionUID = -4054007780835554233L;

	Integer awardNum;// 签到奖励次数
	String beginDate;// 签到开始时间0点
	String endDate;// 签到结束时间0点

	
	public Integer getAwardNum() {
		return awardNum;
	}

	public void setAwardNum(Integer awardNum) {
		this.awardNum = awardNum;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
