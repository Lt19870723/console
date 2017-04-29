package com.cxdai.console.base.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * Description:优先投标计划最终开通<br />
 * </p>
 * 
 * @title FirstTenderReal.java
 * @package com.cxdai.base.entity
 * @author justin.xu
 * @version 0.1 2014年7月17日
 */
public class FirstTenderReal implements Serializable {
	private static final long serialVersionUID = -191815169850848319L;
	/**
	 * 主键Id
	 */
	private Integer id;
	/**
	 * 优先投标计划ID
	 */
	private Integer firstBorrowId;
	/**
	 * 用户开通金额
	 */
	private Integer account;
	/**
	 * 所占比例,直接精确到小数点位，比如：0.001，即千分之一
	 */
	private BigDecimal rate;
	/**
	 * 用户Id
	 */
	private Integer userId;
	/**
	 * 可用余额
	 */
	private BigDecimal useMoney;
	/**
	 * 计划总金额
	 */
	private Integer planAccount;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 状态 0 ：未失效 1 ：已失效
	 */
	private Integer status;
	/**
	 * 版本号.
	 */
	private String version;
	/**
	 * 本身版本号，主要用于根据版本号更新
	 */
	private String selfVersion;

	/**
	 * 添加时间
	 */
	private Date addtime;

	/**
	 * 开通类型（1：首次开通，2：上车）
	 */
	private Integer firstTenderType;

	/**
	 * 上车时间
	 */
	private Date onBusTime;

	/**
	 * 排队号
	 */
	private Integer orderNum;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFirstBorrowId() {
		return firstBorrowId;
	}

	public void setFirstBorrowId(Integer firstBorrowId) {
		this.firstBorrowId = firstBorrowId;
	}

	public Integer getAccount() {
		return account;
	}

	public void setAccount(Integer account) {
		this.account = account;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public BigDecimal getUseMoney() {
		return useMoney;
	}

	public void setUseMoney(BigDecimal useMoney) {
		this.useMoney = useMoney;
	}

	public Integer getPlanAccount() {
		return planAccount;
	}

	public void setPlanAccount(Integer planAccount) {
		this.planAccount = planAccount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Integer getFirstTenderType() {
		return firstTenderType;
	}

	public void setFirstTenderType(Integer firstTenderType) {
		this.firstTenderType = firstTenderType;
	}

	public Date getOnBusTime() {
		return onBusTime;
	}

	public void setOnBusTime(Date onBusTime) {
		this.onBusTime = onBusTime;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
}
