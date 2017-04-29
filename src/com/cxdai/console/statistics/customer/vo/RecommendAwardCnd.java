package com.cxdai.console.statistics.customer.vo;


public class RecommendAwardCnd {

	private String userName;// 推荐人
	private int month; // 月份
	private Integer userId;

	private Integer invitedUserId;

	private Integer type; // 直接 or 间接
	private String year; //年份
	
	private String tjDate;// 统计日期 yyyy-MM


	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public Integer getInvitedUserId() {
		return invitedUserId;
	}

	public void setInvitedUserId(Integer invitedUserId) {
		this.invitedUserId = invitedUserId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	
	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}
	



	/**
	 * @return the tjDate
	 */
	public String getTjDate() {
		return tjDate;
	}

	/**
	 * @param tjDate the tjDate to set
	 */
	public void setTjDate(String tjDate) {
		this.tjDate = tjDate;
	}


}
