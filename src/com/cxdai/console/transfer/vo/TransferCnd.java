package com.cxdai.console.transfer.vo;

import java.io.Serializable;
import java.util.Date;

import com.cxdai.console.common.page.BaseCnd;
import com.cxdai.console.util.DateUtils;

public class TransferCnd extends BaseCnd implements Serializable {
	/**
	 * @fields serialVersionUID
	 */

	private static final long serialVersionUID = -6658110060655667046L;
	/**
	 * 主键id
	 */
	private String id;
	/**
	 * 标题
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
	 * 页面上几种审核状态
	 */
	private String status;

	/**
	 * 标的的状态
	 * 
	 * @fields borrowStatus
	 */
	private String borrowStatus;

	/**
	 * 标的类型
	 * 
	 * @fields type
	 */
	private Integer type;

	/**
	 * 转让人
	 * 
	 * @fields transferUserName
	 */
	private String transferUserName;

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
	 * 转让状态
	 * 
	 * @fields transferStatus
	 */
	private int[] transferStatus;


	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public int[] getTransferStatus() {
		return transferStatus;
	}

	public void setTransferStatus(int[] transferStatus) {
		this.transferStatus = transferStatus;
	}

	public String getBorrowStatus() {
		return borrowStatus;
	}

	public void setBorrowStatus(String borrowStatus) {
		this.borrowStatus = borrowStatus;
	}

}
