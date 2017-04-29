package com.cxdai.console.customer.bankcard.entity;

import java.io.Serializable;
import java.util.Date;

import com.cxdai.console.util.DateUtils;

/**
 * 银行卡更换申请点击日志
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title BankcardClick.java
 * @package com.cxdai.console.bank.vo
 * @author huangpin
 * @version 0.1 2015年3月30日
 */
public class BankcardClick implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id; // 自增ID
	private String errorMsg;// 错误信息
	private Integer userId;// 用户ID
	private Date addTime;// 添加时间
	private String addIp;// 添加IP

	// 显示属性
	private String addTimeStr;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getAddIp() {
		return addIp;
	}

	public void setAddIp(String addIp) {
		this.addIp = addIp;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getAddTimeStr() {
		if (addTime != null) {
			addTimeStr = DateUtils.format(addTime, DateUtils.YMD_HMS);
		}
		return addTimeStr;
	}

	public void setAddTimeStr(String addTimeStr) {
		this.addTimeStr = addTimeStr;
	}

}
