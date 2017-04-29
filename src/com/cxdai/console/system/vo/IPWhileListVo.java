package com.cxdai.console.system.vo;

import java.io.Serializable;
import java.util.Collection;

import com.cxdai.console.common.Dictionary;
import com.cxdai.console.system.entity.Configuration;
import com.cxdai.console.util.DateUtils;

/**
 * 
 * <p>
 * Description:IP白名单<br />
 * </p>
 * 
 * @title IPWhileList.java
 * @package com.cxdai.base.entity
 * @author yangshijin
 * @version 0.1 2014年7月2日
 */
public class IPWhileListVo implements Serializable {

	private static final long serialVersionUID = -1126501063669044440L;

	/** 主键id */
	private Integer id;

	/** ip **/
	private String ip;

	/** 状态(0:正常， 1：已删除) **/
	private int status;

	/** 访问接口类型(0:全部接口，1：网贷天眼接口，2：网贷之家接口) **/
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
	private Integer updateStaffId;

	/** 备注 **/
	private String remark;

	/**
	 * 添加人
	 */
	private String addStaffName;
	/**
	 * 修改人
	 */
	private String updateStaffName;

	/** 添加时间 **/
	private String addtimeStr;

	/** 修改时间 **/
	private String updateTimeStr;

	private String accessTypeStr;

	private String statusStr;

	public IPWhileListVo() {
	}

	public IPWhileListVo(Integer accessType) {
		this.accessType = accessType;
	}

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

	public String getAddStaffName() {
		return addStaffName;
	}

	public void setAddStaffName(String addStaffName) {
		this.addStaffName = addStaffName;
	}

	public String getUpdateStaffName() {
		return updateStaffName;
	}

	public void setUpdateStaffName(String updateStaffName) {
		this.updateStaffName = updateStaffName;
	}

	public String getAddtimeStr() {
		if (addtime != null && !addtime.equals("")) {
			return DateUtils.timeStampToDate(addtime, DateUtils.YMD_HMS);
		}
		return addtimeStr;
	}

	public void setAddtimeStr(String addtimeStr) {
		this.addtimeStr = addtimeStr;
	}

	public String getUpdateTimeStr() {
		if (updateTime != null && !updateTime.equals("")) {
			return DateUtils.timeStampToDate(updateTime, DateUtils.YMD_HMS);
		}
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
	}

	public String getAccessTypeStr() {
		Collection<Configuration> configs = Dictionary.getValues(1400);
		for (Configuration vo : configs) {
			if (vo.getValue().equals(String.valueOf(this.getAccessType()))) {
				return vo.getDesc();
			}
		}
		return "全部接口";
	}

	public void setAccessTypeStr(String accessTypeStr) {
		this.accessTypeStr = accessTypeStr;
	}

	public String getStatusStr() {
		if (status == 0) {
			return "正常";
		} else if (status == 1) {
			return "已删除";
		}
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

}
