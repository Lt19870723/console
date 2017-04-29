package com.cxdai.console.firstborrow.vo;

import java.io.Serializable;
import java.util.Date;

import com.cxdai.console.common.page.BaseCnd;
import com.cxdai.console.util.DateUtils;

public class FirstTransferCnd extends BaseCnd implements Serializable {
	/**
	 * @fields serialVersionUID
	 */

	private static final long serialVersionUID = -6658110060655667046L;

	/**
	 * 直通车转让ID
	 */
	private Integer id;

	/**
	 * 直通车标题
	 */
	private String name;

	/**
	 * 发布开始时间
	 */
	private Date beginTime;

	/**
	 * 发布结束时间
	 */
	private Date endTime;
	
	/**
	 * 撤销转让开始时间
	 */
	private Date transferCanclbeginTime;

	/**
	 * 撤销转让结束时间
	 */
	private Date transferCanclendTime;

	/**
	 * 转让人
	 * 
	 * @fields transferUserName
	 */
	private String transferUserName;

	/**
	 * 转让状态
	 */
	private Integer transferStatus;

	/**
	 * 锁定状态
	 */
	private String lockedRecordYn;

	/**
	 * 转让成功开始日期
	 * 
	 * @fields transferSuccessbeginTime
	 */
	private Date transferSuccessbeginTime;

	/**
	 * 转让成功结束日期
	 * 
	 * @fields transferSuccessendTime
	 */
	private Date transferSuccessendTime;
	
	/**
	 * 审核状态
	 */
	private Integer approvedStatus;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = DateUtils.parse(beginTime, DateUtils.YMD_DASH);
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = DateUtils.parse(endTime, DateUtils.YMD_DASH);
	}

	public String getTransferUserName() {
		return transferUserName;
	}

	public void setTransferUserName(String transferUserName) {
		this.transferUserName = transferUserName;
	}

	public Date getTransferSuccessbeginTime() {
		return transferSuccessbeginTime;
	}

	public void setTransferSuccessbeginTime(String transferSuccessbeginTime) {
		this.transferSuccessbeginTime = DateUtils.parse(transferSuccessbeginTime, DateUtils.YMD_DASH);
	}

	public Date getTransferSuccessendTime() {
		return transferSuccessendTime;
	}

	public void setTransferSuccessendTime(String transferSuccessendTime) {
		this.transferSuccessendTime = DateUtils.parse(transferSuccessendTime, DateUtils.YMD_DASH);
	}

	public Integer getTransferStatus() {
		return transferStatus;
	}

	public void setTransferStatus(Integer transferStatus) {
		this.transferStatus = transferStatus;
	}

	public String getLockedRecordYn() {
		return lockedRecordYn;
	}

	public void setLockedRecordYn(String lockedRecordYn) {
		this.lockedRecordYn = lockedRecordYn;
	}

	public Date getTransferCanclbeginTime() {
		return transferCanclbeginTime;
	}

	public void setTransferCanclbeginTime(String transferCanclbeginTime) {
		this.transferCanclbeginTime = DateUtils.parse(transferCanclbeginTime, DateUtils.YMD_DASH);
	}

	public Date getTransferCanclendTime() {
		return transferCanclendTime;
	}

	public void setTransferCanclendTime(String transferCanclendTime) {
		this.transferCanclendTime = DateUtils.parse(transferCanclendTime, DateUtils.YMD_DASH);
	}

	public Integer getApprovedStatus() {
		return approvedStatus;
	}

	public void setApprovedStatus(Integer approvedStatus) {
		this.approvedStatus = approvedStatus;
	}
}
