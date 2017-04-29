package com.cxdai.console.base.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Description:直通车<br />
 * </p>
 * 
 * @title FirstBorrow.java
 * @package com.cxdai.base.entity
 * @author justin.xu
 * @version 0.1 2014年7月2日
 */
public class FirstBorrow implements Serializable {
	private static final long serialVersionUID = -5552272784546139026L;
	/**
	 * 主键ID
	 */
	private Integer id;
	/**
	 * 标题
	 */
	private String name;
	/**
	 * 期数
	 */
	private String periods;
	/**
	 * 期数说明
	 */
	private String periodsDesc;
	/**
	 * 状态 （0:草稿标
	 * 1：新标,审核中，2：审核不通过，3：审核通过投标中，4：满标复审中，5：满标审核通过，6：满标审核拒绝，-1：流标，-2：被撤销,-3:已过期
	 * ）
	 */
	private Integer status;
	/**
	 * 计划金额
	 */
	private Integer planAccount;
	/**
	 * 实际金额
	 */
	private Integer realAccount;
	/**
	 * 最低投标金额
	 */
	private Integer lowestAccount;
	/**
	 * 最多投标金额
	 */
	private Integer mostAccount;
	/**
	 * 锁定期限
	 */
	private Integer lockLimit;
	/**
	 * 锁定方式 （0：月）
	 */
	private Integer lockStyle;
	/**
	 * 锁定结束日期，精确到天
	 */
	private Date lockEndtime;
	/**
	 * 预期收益
	 */
	private String perceivedRate;
	/**
	 * 加入费率
	 */
	private Double addRate;
	/**
	 * 服务费率
	 */
	private Double serviceRate;
	/**
	 * 退出费率
	 */
	private Double exitRate;
	/**
	 * 满标时间
	 */
	private Date successTime;
	/**
	 * 投标有效期限 分钟数
	 */
	private Integer validTime;
	/**
	 * 计划详细说明
	 */
	private String content;
	/**
	 * 完成后发送站内信，0不发送，1发送
	 */
	private Integer sendMessage;
	/**
	 * 添加时间
	 */
	private Date addtime;
	/**
	 * 添加IP
	 */
	private String addIp;
	/**
	 * 撤销人（0：发标人自己撤销，其他数字表示员工ID，-1：系统流标）
	 */
	private Integer cancelUser;
	/**
	 * 撤销时间
	 */
	private Date cancelTime;
	/**
	 * 撤销备注
	 */
	private String cancelRemark;
	/**
	 * 借款标唯一标识
	 */
	private String uuid;
	/**
	 * 合同编号
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
	 * 发布时间，审核通过的时间（即用户可以投标的时间）
	 */
	private Date publishTime;
	/**
	 * 用户IO
	 */
	private Integer userId;
	/**
	 * 投标次数 满标时更新此字段
	 */
	private Integer tenderTimes;
	/**
	 * 已经借到的金额
	 */
	private Integer accountYes;
	/**
	 * 密码原文
	 */
	private String passwordSource;
	/** 版本号. **/
	private String version;
	/**
	 * 本身版本号，主要用于根据版本号更新
	 */
	private String selfVersion;

	// 是否清空密码
	private String cleanPassword;

	/** 认购截止时间 */
	private Date endTime;

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

	public String getPeriods() {
		return periods;
	}

	public void setPeriods(String periods) {
		this.periods = periods;
	}

	public String getPeriodsDesc() {
		return periodsDesc;
	}

	public void setPeriodsDesc(String periodsDesc) {
		this.periodsDesc = periodsDesc;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getPlanAccount() {
		return planAccount;
	}

	public void setPlanAccount(Integer planAccount) {
		this.planAccount = planAccount;
	}

	public Integer getLowestAccount() {
		return lowestAccount;
	}

	public void setLowestAccount(Integer lowestAccount) {
		this.lowestAccount = lowestAccount;
	}

	public Integer getMostAccount() {
		return mostAccount;
	}

	public void setMostAccount(Integer mostAccount) {
		this.mostAccount = mostAccount;
	}

	public Integer getLockLimit() {
		return lockLimit;
	}

	public void setLockLimit(Integer lockLimit) {
		this.lockLimit = lockLimit;
	}

	public Integer getLockStyle() {
		return lockStyle;
	}

	public void setLockStyle(Integer lockStyle) {
		this.lockStyle = lockStyle;
	}

	public Date getLockEndtime() {
		return lockEndtime;
	}

	public void setLockEndtime(Date lockEndtime) {
		this.lockEndtime = lockEndtime;
	}

	public String getPerceivedRate() {
		return perceivedRate;
	}

	public void setPerceivedRate(String perceivedRate) {
		this.perceivedRate = perceivedRate;
	}

	public Double getAddRate() {
		return addRate;
	}

	public void setAddRate(Double addRate) {
		this.addRate = addRate;
	}

	public Double getServiceRate() {
		return serviceRate;
	}

	public void setServiceRate(Double serviceRate) {
		this.serviceRate = serviceRate;
	}

	public Double getExitRate() {
		return exitRate;
	}

	public void setExitRate(Double exitRate) {
		this.exitRate = exitRate;
	}

	public Date getSuccessTime() {
		return successTime;
	}

	public void setSuccessTime(Date successTime) {
		this.successTime = successTime;
	}

	public Integer getValidTime() {
		return validTime;
	}

	public void setValidTime(Integer validTime) {
		this.validTime = validTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getSendMessage() {
		return sendMessage;
	}

	public void setSendMessage(Integer sendMessage) {
		this.sendMessage = sendMessage;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getAddIp() {
		return addIp;
	}

	public void setAddIp(String addIp) {
		this.addIp = addIp;
	}

	public Integer getCancelUser() {
		return cancelUser;
	}

	public void setCancelUser(Integer cancelUser) {
		this.cancelUser = cancelUser;
	}

	public Date getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}

	public String getCancelRemark() {
		return cancelRemark;
	}

	public void setCancelRemark(String cancelRemark) {
		this.cancelRemark = cancelRemark;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getBidPassword() {
		return bidPassword;
	}

	public void setBidPassword(String bidPassword) {
		this.bidPassword = bidPassword;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getTenderTimes() {
		return tenderTimes;
	}

	public void setTenderTimes(Integer tenderTimes) {
		this.tenderTimes = tenderTimes;
	}

	public Integer getAccountYes() {
		return accountYes;
	}

	public void setAccountYes(Integer accountYes) {
		this.accountYes = accountYes;
	}

	public String getPasswordSource() {
		return passwordSource;
	}

	public void setPasswordSource(String passwordSource) {
		this.passwordSource = passwordSource;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSelfVersion() {
		return selfVersion;
	}

	public void setSelfVersion(String selfVersion) {
		this.selfVersion = selfVersion;
	}

	public String getCleanPassword() {
		return cleanPassword;
	}

	public void setCleanPassword(String cleanPassword) {
		this.cleanPassword = cleanPassword;
	}

	public Integer getRealAccount() {
		return realAccount;
	}

	public void setRealAccount(Integer realAccount) {
		this.realAccount = realAccount;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
