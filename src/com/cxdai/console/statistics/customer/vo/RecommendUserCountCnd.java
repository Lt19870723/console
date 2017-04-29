package com.cxdai.console.statistics.customer.vo;

import java.util.Date;

public class RecommendUserCountCnd {

	private String userName;// 推荐人

	private String invitedUsername; // 被推荐人

	private String isRecommendSuccess; // 是否推荐成功

	private Date invitedRegistBeginTime; // 被推荐人注册时间
	private Date invitedRegistEndTime; // 被推荐人注册时间

	private Date invitedSuccessBeginTime; // 推荐成功开始时间
	private Date invitedSuccessEndTime; // 推荐成功结束时间

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getInvitedUsername() {
		return invitedUsername;
	}

	public void setInvitedUsername(String invitedUsername) {
		this.invitedUsername = invitedUsername;
	}

	public String getIsRecommendSuccess() {
		return isRecommendSuccess;
	}

	public void setIsRecommendSuccess(String isRecommendSuccess) {
		this.isRecommendSuccess = isRecommendSuccess;
	}

	public Date getInvitedRegistBeginTime() {
		return invitedRegistBeginTime;
	}

	public void setInvitedRegistBeginTime(Date invitedRegistBeginTime) {
		this.invitedRegistBeginTime = invitedRegistBeginTime;
	}

	public Date getInvitedRegistEndTime() {
		return invitedRegistEndTime;
	}

	public void setInvitedRegistEndTime(Date invitedRegistEndTime) {
		this.invitedRegistEndTime = invitedRegistEndTime;
	}

	public Date getInvitedSuccessBeginTime() {
		return invitedSuccessBeginTime;
	}

	public void setInvitedSuccessBeginTime(Date invitedSuccessBeginTime) {
		this.invitedSuccessBeginTime = invitedSuccessBeginTime;
	}

	public Date getInvitedSuccessEndTime() {
		return invitedSuccessEndTime;
	}

	public void setInvitedSuccessEndTime(Date invitedSuccessEndTime) {
		this.invitedSuccessEndTime = invitedSuccessEndTime;
	}


}
