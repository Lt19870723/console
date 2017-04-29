package com.cxdai.console.red.entity;

import java.io.Serializable;
import java.util.Date;

public class VipRedImport implements Serializable {

	private static final long serialVersionUID = -8105755811911663717L;
	private Integer id;
	private Integer userId;
	private Integer number;// 总计导入的人数
	private String optName;// 操作人
	private Date optTime;//操作时间
	private String remark;// 备注说明
	private String remarkInfo;//批量导入时备注说明
	private Date addTime;// 创建时间
	private String addName;// 创建人
	private Integer status;// 状态：0:未发放；1:已发放
	private Date grantTime;// 发放时间
	private String useName;// 用户名
	private String strAlertMsg;// 返回信息
	private String redMoney;
	private String redType;
	private String redCount;
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getRedCount() {
		return redCount;
	}

	public void setRedCount(String redCount) {
		this.redCount = redCount;
	}

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

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getOptName() {
		return optName;
	}

	public void setOptName(String optName) {
		this.optName = optName;
	}

	public Date getOptTime() {
		return optTime;
	}

	public void setOptTime(Date optTime) {
		this.optTime = optTime;
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

	public String getAddName() {
		return addName;
	}

	public void setAddName(String addName) {
		this.addName = addName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getGrantTime() {
		return grantTime;
	}

	public void setGrantTime(Date grantTime) {
		this.grantTime = grantTime;
	}

	public String getUseName() {
		return useName;
	}

	public void setUseName(String useName) {
		this.useName = useName;
	}

	public String getStrAlertMsg() {
		return strAlertMsg;
	}

	public void setStrAlertMsg(String strAlertMsg) {
		this.strAlertMsg = strAlertMsg;
	}

	public String getRemarkInfo() {
		return remarkInfo;
	}

	public void setRemarkInfo(String remarkInfo) {
		this.remarkInfo = remarkInfo;
	}
	
	
}
