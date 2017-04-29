package com.cxdai.console.red.vo;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.cxdai.console.common.page.BaseCnd;

/**
 * Description:贵宾特权红包查询条件<br />
 * 
 * @author liutao
 * @version 0.1 2015年11月4日
 */
public class VipRedImportCnd extends BaseCnd implements Serializable {
	private static final long serialVersionUID = -5890980057423538506L;
	private String id;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 操作人
	 */
	private String optName;

	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 状态
	 * */
	private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		if (null != this.userName && StringUtils.isNotEmpty(this.userName)) {
			return this.userName.trim();
		}
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOptName() {
		if (null != this.optName && StringUtils.isNotEmpty(this.optName)) {
			return this.optName.trim();
		}
		return optName;
	}

	public void setOptName(String optName) {
		this.optName = optName;
	}

	public String getRemark() {
		if (null != this.remark && StringUtils.isNotEmpty(this.remark)) {
			return this.remark.trim();
		}
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}