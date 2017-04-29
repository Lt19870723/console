package com.cxdai.console.fix.entity;

import java.math.BigDecimal;
import java.util.Date;


/**   
 * <p>
 * Description:定期宝信息类<br />
 * </p>
 * @title FixBorrow.java
 * @package com.cxdai.console.fix.entity 
 * @author HuangJun
 * @version 0.1 2015年6月28日
 */
public class FixBorrow {

	
	/** 
	 * 最后修改人
	 */ 
	private Integer lastModifyUser;
	/** 
	 * 最后修改时间
	 */ 
	private Date lastModifyTime;
	
	/**
	 * 主键Id
	 */
	private Integer id; 
	
	/**
	 * 定期宝名称
	 */
	private String name; 
	
	/**
	 * 状态
	 */
	private Integer status; 
	
	/**
	 * 开放金额
	 */
	private BigDecimal planAccount; 
	
	/**
	 * 实际认购金额
	 */
	private BigDecimal accountYes; 
	
	/**
	 * 最低投标金额
	 */
	private BigDecimal lowestAccount; 
	
	/**
	 * 最多投标金额
	 */
	private BigDecimal mostAccount; 
	
	/**
	 * 锁定期限
	 */
	private Integer lockLimit; 
	
	/**
	 * 锁定方式
	 */
	private Integer lockStyle; 
	
	/**
	 * 锁定结束日期
	 */
	private Date lockEndtime; 
	
	/**
	 * 年化利率
	 */
	private BigDecimal apr; 
	
	/**
	 * 满标时间
	 */
	private Date successTime; 
	
	/**
	 * 投标有效期限，以分钟为单位
	 */
	private Integer validTime; 
	
	/**
	 * 认购截止时间
	 */
	private Date endTime; 
	
	/**
	 * 计划详细说明
	 */
	private String content; 
	
	/**
	 * 完成后发送站内信
	 */
	private Integer sendmessage; 
	
	/**
	 * 添加时间
	 */
	private Date addtime; 
	
	/**
	 * 添加ip
	 */
	private String addip; 
	
	/**
	 * 添加人id
	 */
	private Integer adduser; 
	
	/**
	 * 撤销人
	 */
	private Integer cancelUser; 
	
	/**
	 * 撤销时间
	 */
	private Date cancelTime; 
	
	/**
	 * 撤销ip
	 */
	private String cancelIp; 
	
	/**
	 * 撤销备注
	 */
	private String cancelRemark; 
	
	/**
	 * 定期宝编号
	 */
	private String contractNo; 
	
	/**
	 * 投标密码
	 */
	private String bidPassword; 
	
	/**
	 * 备注
	 */
	private String remark; 
	
	/**
	 * 开放时间
	 */
	private Date publishTime; 
	
	/**
	 * 认购次数
	 */
	private Integer tenderTimes; 
	
	/**
	 * 专区类型【0：普通专区，1：新手专区】
	 */
	private Integer areaType;
	/**
	 * 新手专区转普通专区时间
	 */
	private Date areaChangeTime;	
	
	public Integer getAreaType() {
		return areaType;
	}


	public void setAreaType(Integer areaType) {
		this.areaType = areaType;
	}


	public Date getAreaChangeTime() {
		return areaChangeTime;
	}


	public void setAreaChangeTime(Date areaChangeTime) {
		this.areaChangeTime = areaChangeTime;
	}
	
	public Integer getId () {
		return id;
	}
	
	
	public void setId (Integer id) {
		this.id = id;
	}
	
	public String getName () {
		return name;
	}
	
	
	public void setName (String name) {
		this.name = name;
	}
	
	public Integer getStatus () {
		return status;
	}
	
	
	public void setStatus (Integer status) {
		this.status = status;
	}
	
	public BigDecimal getPlanAccount () {
		return planAccount;
	}
	
	
	public void setPlanAccount (BigDecimal planAccount) {
		this.planAccount = planAccount;
	}
	
	public BigDecimal getAccountYes () {
		return accountYes;
	}
	
	
	public void setAccountYes (BigDecimal accountYes) {
		this.accountYes = accountYes;
	}
	
	public BigDecimal getLowestAccount () {
		return lowestAccount;
	}
	
	
	public void setLowestAccount (BigDecimal lowestAccount) {
		this.lowestAccount = lowestAccount;
	}
	
	public BigDecimal getMostAccount () {
		return mostAccount;
	}
	
	
	public void setMostAccount (BigDecimal mostAccount) {
		this.mostAccount = mostAccount;
	}
	
	public Integer getLockLimit () {
		return lockLimit;
	}
	
	
	public void setLockLimit (Integer lockLimit) {
		this.lockLimit = lockLimit;
	}
	
	public Integer getLockStyle () {
		return lockStyle;
	}
	
	
	public void setLockStyle (Integer lockStyle) {
		this.lockStyle = lockStyle;
	}
	

	
	/**
	 * @return lockEndtime : return the property lockEndtime.
	 */
	public Date getLockEndtime() {
		return lockEndtime;
	}


	/**
	 * @param lockEndtime : set the property lockEndtime.
	 */
	public void setLockEndtime(Date lockEndtime) {
		this.lockEndtime = lockEndtime;
	}


	public BigDecimal getApr () {
		return apr;
	}
	
	
	public void setApr (BigDecimal apr) {
		this.apr = apr;
	}
	
	public Date getSuccessTime () {
		return successTime;
	}
	
	
	public void setSuccessTime (Date successTime) {
		this.successTime = successTime;
	}
	
	public Integer getValidTime () {
		return validTime;
	}
	
	
	public void setValidTime (Integer validTime) {
		this.validTime = validTime;
	}
	
	public Date getEndTime () {
		return endTime;
	}
	
	
	public void setEndTime (Date endTime) {
		this.endTime = endTime;
	}
	
	public String getContent () {
		return content;
	}
	
	
	public void setContent (String content) {
		this.content = content;
	}
	
	public Integer getSendmessage () {
		return sendmessage;
	}
	
	
	public void setSendmessage (Integer sendmessage) {
		this.sendmessage = sendmessage;
	}
	
	public Date getAddtime () {
		return addtime;
	}
	
	
	public void setAddtime (Date addtime) {
		this.addtime = addtime;
	}
	
	public String getAddip () {
		return addip;
	}
	
	
	public void setAddip (String addip) {
		this.addip = addip;
	}
	
	public Integer getAdduser () {
		return adduser;
	}
	
	
	public void setAdduser (Integer adduser) {
		this.adduser = adduser;
	}
	
	public Integer getCancelUser () {
		return cancelUser;
	}
	
	
	public void setCancelUser (Integer cancelUser) {
		this.cancelUser = cancelUser;
	}
	
	public Date getCancelTime () {
		return cancelTime;
	}
	
	
	public void setCancelTime (Date cancelTime) {
		this.cancelTime = cancelTime;
	}
	
	public String getCancelIp () {
		return cancelIp;
	}
	
	
	public void setCancelIp (String cancelIp) {
		this.cancelIp = cancelIp;
	}
	
	public String getCancelRemark () {
		return cancelRemark;
	}
	
	
	public void setCancelRemark (String cancelRemark) {
		this.cancelRemark = cancelRemark;
	}
	
	public String getContractNo () {
		return contractNo;
	}
	
	
	public void setContractNo (String contractNo) {
		this.contractNo = contractNo;
	}
	
	public String getBidPassword () {
		return bidPassword;
	}
	
	
	public void setBidPassword (String bidPassword) {
		this.bidPassword = bidPassword;
	}
	
	public String getRemark () {
		return remark;
	}
	
	
	public void setRemark (String remark) {
		this.remark = remark;
	}
	
	public Date getPublishTime () {
		return publishTime;
	}
	
	
	public void setPublishTime (Date publishTime) {
		this.publishTime = publishTime;
	}
	
	public Integer getTenderTimes () {
		return tenderTimes;
	}
	
	
	public void setTenderTimes (Integer tenderTimes) {
		this.tenderTimes = tenderTimes;
	}


	/**
	 * @return lastModifyUser : return the property lastModifyUser.
	 */
	public Integer getLastModifyUser() {
		return lastModifyUser;
	}


	/**
	 * @param lastModifyUser : set the property lastModifyUser.
	 */
	public void setLastModifyUser(Integer lastModifyUser) {
		this.lastModifyUser = lastModifyUser;
	}


	/**
	 * @return lastModifyTime : return the property lastModifyTime.
	 */
	public Date getLastModifyTime() {
		return lastModifyTime;
	}


	/**
	 * @param lastModifyTime : set the property lastModifyTime.
	 */
	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	
	

}
