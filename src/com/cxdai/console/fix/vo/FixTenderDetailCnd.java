package com.cxdai.console.fix.vo;

import java.io.Serializable;
import java.util.Date;

import com.cxdai.console.common.page.BaseCnd;

public class FixTenderDetailCnd extends BaseCnd implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2034913447650749938L;
	
	/**
	 * 定期宝编号
	 */
	private String contractNo;
	
	
	/**
	 * 用户名
	 */
	private String userName;
	
	/**
	 * 开始时间
	 */
	private Date beginTime;
	
	/**
	 * 结束时间
	 */
	private Date endTime;
	private String beginTimeStr;
	private String endTimeStr;
	/**
	 * 状态
	 */
	private Integer status;

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getBeginTimeStr() {
		return beginTimeStr;
	}

	public void setBeginTimeStr(String beginTimeStr) {
		this.beginTimeStr = beginTimeStr;
	}

	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}
	
}
