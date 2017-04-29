package com.cxdai.console.activity.vo;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

/**
 * 活动数据查询-50亿活动数据
 * @author liutao
 * @Date 2016-05-10
 */
public class BillionActivity implements Serializable {
	private static final long serialVersionUID = 1178922844202577381L;

	/** 用户名 */
	private String userName;

	/** 红包金额 */
	private String redMoney;
	/** 投标日期 */
	private String interestTime;
	/** 红包数量 */
	private String redCount;
	/** 红包类型 */
	private String redType;
	/** 备注 */
	private String remark;

	public String getRedType() {
		return "50亿活动红包";
	}

	public void setRedType(String redType) {
		this.redType = redType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRedMoney() {
		return redMoney;
	}

	public void setRedMoney(String redMoney) {
		this.redMoney = redMoney;
	}

	public String getRedCount() {
		return redCount;
	}

	public void setRedCount(String redCount) {
		this.redCount = redCount;
	}

	public String getInterestTime() {
		return interestTime;
	}

	public void setInterestTime(String interestTime) {
		this.interestTime = interestTime;
	}
}