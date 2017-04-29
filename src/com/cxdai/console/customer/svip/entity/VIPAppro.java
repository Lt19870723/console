package com.cxdai.console.customer.svip.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * Description:VIP认证<br />
 * </p>
 * 
 * @title VIPAppro.java
 * @package com.cxdai.base.entity
 * @author justin.xu
 * @version 0.1 2014年4月25日
 */
public class VIPAppro implements Serializable {
	private static final long serialVersionUID = -9190233496095471381L;
	/** ID */
	private Integer id;
	/** 用户ID */
	private Integer userId;
	/** 状态【1：正常；-1：失效】 */
	private Integer passed;
	/** 审核人 */
	private String approver;
	/** 审核备注 */
	private String approveRemark;
	/** 审核通过时间 */
	private String approveTime;
	/** 客服编号 */
	private String serviceNum;
	/** 有效截止 期 */
	private Date indate;
	/** 申请时间 */
	private String addTime;
	/** 申请IP */
	private String addIp;
	/** 是否缴费【0：否，1：是】 */
	private Integer isFee;
	/** 备注 */
	private String remark;
	/** 开通费用 */
	private BigDecimal fee;

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

	public Integer getPassed() {
		return passed;
	}

	public void setPassed(Integer passed) {
		this.passed = passed;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public String getApproveRemark() {
		return approveRemark;
	}

	public void setApproveRemark(String approveRemark) {
		this.approveRemark = approveRemark;
	}

	public String getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(String approveTime) {
		this.approveTime = approveTime;
	}

	public String getServiceNum() {
		return serviceNum;
	}

	public void setServiceNum(String serviceNum) {
		this.serviceNum = serviceNum;
	}

	public Date getIndate() {
		return indate;
	}

	public void setIndate(Date indate) {
		this.indate = indate;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getAddIp() {
		return addIp;
	}

	public void setAddIp(String addIp) {
		this.addIp = addIp;
	}

	public Integer getIsFee() {
		return isFee;
	}

	public void setIsFee(Integer isFee) {
		this.isFee = isFee;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

}
