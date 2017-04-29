package com.cxdai.console.base.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * <p>
 * Description:黑名单<br />
 * </p>
 * 
 * @title BlackList.java
 * @package com.cxdai.base.entity
 * @author yangshijin
 * @version 0.1 2015年1月25日
 */
public class BlackList implements Serializable {

	private static final long serialVersionUID = -3535742918868087150L;
	/** 主键id */
	private Integer id;
	/** 用户id */
	private Integer userId;
	/**
	 * 类型【1：禁止手动投标，2：禁止认购直通车，3：禁止认购债权转让，4：禁止设置自动投标，5：禁止线上充值，6：禁止提现，7：禁止发净值标，
	 * 8：直通车下车， 9：禁止微信消息群推】
	 */
	private Integer type;
	/** 状态【1：生效，-1：失效，2：已处理】 */
	private Integer status;
	/** 添加时间 */
	private Date addTime;
	/** 添加人id */
	private Integer addId;
	/** 添加ip */
	private String addIp;
	/** 备注 */
	private String remark;
	/** 修改时间 */
	private Date uptime;
	/** 修改人id */
	private Integer updateId;
	/** 更新ip */
	private String updateIp;
	/** 更新备注 */
	private String updateRemark;

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Integer getAddId() {
		return addId;
	}

	public void setAddId(Integer addId) {
		this.addId = addId;
	}

	public String getAddIp() {
		return addIp;
	}

	public void setAddIp(String addIp) {
		this.addIp = addIp;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getUptime() {
		return uptime;
	}

	public void setUptime(Date uptime) {
		this.uptime = uptime;
	}

	public Integer getUpdateId() {
		return updateId;
	}

	public void setUpdateId(Integer updateId) {
		this.updateId = updateId;
	}

	public String getUpdateIp() {
		return updateIp;
	}

	public void setUpdateIp(String updateIp) {
		this.updateIp = updateIp;
	}

	public String getUpdateRemark() {
		return updateRemark;
	}

	public void setUpdateRemark(String updateRemark) {
		this.updateRemark = updateRemark;
	}
}
