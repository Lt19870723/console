package com.cxdai.console.lottery.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.cxdai.console.lottery.entity.BaseUseRecord;
import com.cxdai.console.util.DateUtils;

public class UseRecord extends BaseUseRecord {

	String userName;// 用户名
	String goodName; // 奖品名
	String activeName;// 活动类型
	String addtimeStr;
	String drawTimeStr;
	String dealTimeStr;

	String dealUserName;
	Date dealTime;
	String dealIp;
	/** 注册时间 */
	private Date registerTime;
	private String registerTimeStr;
	/** 注册来源类型 */
	private String registerSource;
	/** 待收总额 */
	private BigDecimal collection;
	/** 投资金额 */
	private BigDecimal tenderAccountTotal;
	/** 充值金额  */
	private BigDecimal rechargereTotal;
	
	private String userType;
	
	public String getAddtimeStr() {
		Date registerTime = getAddTime();
		if (registerTime != null) {
			return DateUtils.format(registerTime, DateUtils.YMD_HMS);
		}
		return addtimeStr;
	}

	public void setAddtimeStr(String registerTimeStr) {
		this.addtimeStr = registerTimeStr;
	}

	public String getDrawTimeStr() {
		Date registerTime = getDrawTime();
		if (registerTime != null) {
			return DateUtils.format(registerTime, DateUtils.YMD_HMS);
		}
		return drawTimeStr;
	}

	public String getDealTimeStr() {
		Date registerTime = getDealTime();
		if (registerTime != null) {
			return DateUtils.format(registerTime, DateUtils.YMD_HMS);
		}
		return dealTimeStr;
	}

	public void setDealTimeStr(String dealTimeStr) {
		this.dealTimeStr = dealTimeStr;
	}

	public void setDrawTimeStr(String registerTimeStr) {
		this.drawTimeStr = registerTimeStr;
	}

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

	public String getActiveName() {
		return activeName;
	}

	public void setActiveName(String activeName) {
		this.activeName = activeName;
	}


	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public String getRegisterSource() {
		return registerSource;
	}

	public void setRegisterSource(String registerSource) {
		this.registerSource = registerSource;
	}

	public BigDecimal getRechargereTotal() {
		return rechargereTotal;
	}

	public void setRechargereTotal(BigDecimal rechargereTotal) {
		this.rechargereTotal = rechargereTotal;
	}

	public BigDecimal getCollection() {
		return collection;
	}

	public void setCollection(BigDecimal collection) {
		this.collection = collection;
	}

	public String getRegisterTimeStr() {
		if (registerTime != null) {
			return DateUtils.format(registerTime, DateUtils.YMD_HMS);
		}
		return registerTimeStr;
	}

	public void setRegisterTimeStr(String registerTimeStr) {
		this.registerTimeStr = registerTimeStr;
	}

	public String getDealUserName() {
		return dealUserName;
	}

	public void setDealUserName(String dealUserName) {
		this.dealUserName = dealUserName;
	}

	public Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

	public String getDealIp() {
		return dealIp;
	}

	public void setDealIp(String dealIp) {
		this.dealIp = dealIp;
	}

	public BigDecimal getTenderAccountTotal() {
		return tenderAccountTotal;
	}

	public void setTenderAccountTotal(BigDecimal tenderAccountTotal) {
		this.tenderAccountTotal = tenderAccountTotal;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
}