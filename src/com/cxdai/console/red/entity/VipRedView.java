package com.cxdai.console.red.entity;

import java.io.Serializable;
import java.util.Date;

public class VipRedView implements Serializable {

	private static final long serialVersionUID = -8105755811911663717L;
	private Integer id;
	private String userName;// 用户名
	private Integer userId;// 用户主键
	private String remark;// 说明
	private Date addTime;// 发放时间
	private Integer addUserId;// 操作人ID
	private Integer optId;// 操作日志ID
	private Integer status;// 状态：0:未发放；1:已发放
	private String redType;
	private String redMoney;
	public String getRedMoney() {
		return redMoney;
	}

	public void setRedMoney(String redMoney) {
		this.redMoney = redMoney;
	}

	public String getRedType() {
		return redType;
	}

	public void setRedType(String redType) {
		this.redType = redType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Integer getAddUserId() {
		return addUserId;
	}

	public void setAddUserId(Integer addUserId) {
		this.addUserId = addUserId;
	}

	public Integer getOptId() {
		return optId;
	}

	public void setOptId(Integer optId) {
		this.optId = optId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
