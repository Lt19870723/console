package com.cxdai.console.sycee.entity;

import java.io.Serializable;

/**
 * 元宝商城打折信息
 * 
 * @author Administrator
 */
public class SyceeGoodsDiscount implements Serializable {

	private static final long serialVersionUID = -4054007780835554233L;

	Integer discountFlag;// 是否打折：1打折 0不打折
	String beginDate;// 打折开始时间0点
	String endDate;// 打折结束时间0点

	public Integer getDiscountFlag() {
		return discountFlag;
	}

	public void setDiscountFlag(Integer discountFlag) {
		this.discountFlag = discountFlag;
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
