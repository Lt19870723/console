package com.cxdai.console.transfer.vo;

import java.io.Serializable;
import java.util.Date;

import com.cxdai.console.common.page.BaseCnd;
import com.cxdai.console.util.DateUtils;

public class TransferListCnd extends BaseCnd implements Serializable {
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
	 * 撤销开始日期
	 * 
	 * @fields transferSuccessbeginTime
	 */
	private Date transferCancelbeginTime;

	/**
	 * 撤销结束日期
	 * 
	 * @fields transferSuccessendTime
	 */
	private Date transferCancelendTime;

	/**
	 * 转让状态
	 * 
	 * @fields transferStatus
	 */
	private int transferType;


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

	public int getTransferType() {
		return transferType;
	}

	public void setTransferType(int transferType) {
		this.transferType = transferType;
	}

	public Date getTransferCancelbeginTime() {
		return transferCancelbeginTime;
	}

	public void setTransferCancelbeginTime(String transferCancelbeginTime) {
		this.transferCancelbeginTime = DateUtils.parse(transferCancelbeginTime, DateUtils.YMD_DASH);
	}

	public Date getTransferCancelendTime() {
		return transferCancelendTime;
	}

	public void setTransferCancelendTime(String transferCancelendTime) {
		this.transferCancelendTime = DateUtils.parse(transferCancelendTime, DateUtils.YMD_DASH);
	}


}
