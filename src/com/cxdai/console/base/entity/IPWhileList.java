package com.cxdai.console.base.entity;

import java.io.Serializable;

/**
 * 
 * <p>
 * Description:IP白名单<br />
 * </p>
 * @title IPWhileList.java
 * @package com.cxdai.base.entity 
 * @author yangshijin
 * @version 0.1 2014年7月2日
 */
public class IPWhileList implements Serializable{
	private static final long serialVersionUID = -3905856647916530439L;

	/** 主键id */
	private Integer id;
	
	/** ip **/
	private String ip;
	
	/** 状态(0:正常， 1：已删除)  **/
	private int status;
	
	/** 访问接口类型(0:全部接口，1：网贷天眼接口，2：网贷之家接口)**/
	private int accessType;
	
	/** 公司名称 **/
	private String company;
	
	/** 添加时间 **/
	private String addtime;
	
	/** 创建人ID **/
	private Integer addStaffId;
	
	/** 修改时间 **/
	private String updateTime;
	
	/** 修改人ID **/
	private Integer  updateStaffId;
	
	/** 备注 **/
	private String remark;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public int getAccessType() {
		return accessType;
	}

	public void setAccessType(int accessType) {
		this.accessType = accessType;
	}

	public Integer getAddStaffId() {
		return addStaffId;
	}

	public void setAddStaffId(Integer addStaffId) {
		this.addStaffId = addStaffId;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getUpdateStaffId() {
		return updateStaffId;
	}

	public void setUpdateStaffId(Integer updateStaffId) {
		this.updateStaffId = updateStaffId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
