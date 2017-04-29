package com.cxdai.console.base.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Description:直通车解锁审核<br />
 * </p>
 * 
 * @title FirstUnlockAppr.java
 * @package com.cxdai.base.entity
 * @author justin.xu
 * @version 0.1 2014年7月28日
 */
public class FirstUnlockAppr implements Serializable {
	private static final long serialVersionUID = -8485687809062135153L;
	/**
	 * 主键ID
	 */
	private Integer id;
	/**
	 * 审核人ID
	 */
	private Integer userId;
	/**
	 * 审核时间
	 */
	private Date apprTime;
	/**
	 * 审核状态 0 ：审核拒绝 1 审核通过
	 */
	private Integer status;
	/**
	 * 审核备注
	 */
	private String remark;
	/**
	 * 优先投标计划最终认购记录ID
	 */
	private Integer firstTenderRealId;

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

	public Date getApprTime() {
		return apprTime;
	}

	public void setApprTime(Date apprTime) {
		this.apprTime = apprTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getFirstTenderRealId() {
		return firstTenderRealId;
	}

	public void setFirstTenderRealId(Integer firstTenderRealId) {
		this.firstTenderRealId = firstTenderRealId;
	}

}
