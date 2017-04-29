package com.cxdai.console.customer.svip.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * <p>
 * Description:终身顶级会员<br />
 * </p>
 * 
 * @title TopSvip.java
 * @package com.cxdai.base.entity
 * @author yangshijin
 * @version 0.1 2015年1月13日
 */
public class VipLevel implements Serializable {
	private static final long serialVersionUID = 1842541364605130998L;

	/** 主键ID */
	private Integer id;

	/** 用户ID */
	private Integer userId;

	/** 状态【0：正常，-1：失效】 */
	private Integer status;

	/** 会员等级【1：终身顶级会员】 */
	private Integer type;

	/** 排名 */
	private Integer order;

	/** 添加人ID */
	private Integer addId;

	/** 添加时间 */
	private Date addTime;

	/** 添加IP */
	private String addIp;

	/** 　备注 */
	private String remark;

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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getAddId() {
		return addId;
	}

	public void setAddId(Integer addId) {
		this.addId = addId;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
