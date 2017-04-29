package com.cxdai.console.base.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * Description:直通车转让类<br />
 * </p>
 * 
 * @title FirstTransfer.java
 * @package com.cxdai.base.entity
 * @author justin.xu
 * @version 0.1 2015年3月15日
 */
public class FirstTransfer implements Serializable {

	private static final long serialVersionUID = -3531851612222097637L;
	private Integer id;
	/** 直通车最终认购记录ID */
	private Integer firstTenderRealId;
	/** 直通车期数 */
	private String firstPeriods;
	/** 直通车预期收益 */
	private String firstPerceivedRate;
	/** 投资本金[A]：开通直通车的金额（直通车可用余额+直通车待收本金) */
	private BigDecimal tenderCapital;
	/** 应得利息[E](占用利息总和，直通车所投的每一个标资金占用时间内利息总和) */
	private BigDecimal interest;
	/** 利息的利息[G](直通车所投的每一个标已到期利息在未到期产生的利息总和) */
	private BigDecimal interestDiff;
	/** 债权价格[A+E-G]=TENDER_CAPITAL+INTEREST-INTEREST_DIFF */
	private BigDecimal account;
	/** 认购奖励,不能超过债权价格1% */
	private BigDecimal awards;
	/** 转让价格=债转价格-认购奖励 */
	private BigDecimal accountReal;
	/** 转让系数 **/
	private BigDecimal coef;
	/** 转让管理费=投资本金*比率 **/
	private BigDecimal manageFee;
	/** 所得的利息管理费 **/
	private BigDecimal interestManageFee;
	/** 有效时间(天)，最大3天 */
	private Integer validTime;
	/** 债权转让标题=直通车标题 */
	private String transferName;
	/** 直通车转让人 */
	private Integer userId;
	/** 添加时间 */
	private Date addtime;
	/** 最后修改时间 */
	private Date lastUpdateTime;
	/** 最后修改人(portal 对应账号用户名,console对应真实姓名,系统对应系统自动操作) */
	private String lastUpdateName;
	/** 结束时间 */
	private Date endTime;
	/** 直通车转让成功时间 */
	private Date successTime;
	/** 认购密码(MD5) */
	private String bidPassword;
	/** 状态(1：直通车转让，审核中，2：转让中，3：转让复审中，4：转让完成，5：转让流车，6：被撤销 ，7：复审失败) */
	private Integer status;
	/** 备注 */
	private String remark;
	/** 发起的平台来源(1：网页 2、微信 3 安卓 4 IOS) */
	private Integer platform;
	/** 实际认购人待收总利息=为未还款的总利息-转让人占用的利息+利息的利息 */
	private BigDecimal actualInterest;
	/** 平均周期（月）= 总月数【去除已还款的月数】/标的个数 */
	private BigDecimal avgCycle;
	/** 平均利率（月）= 利率总计/总月数 */
	private BigDecimal avgRate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFirstTenderRealId() {
		return firstTenderRealId;
	}

	public void setFirstTenderRealId(Integer firstTenderRealId) {
		this.firstTenderRealId = firstTenderRealId;
	}

	public String getFirstPeriods() {
		return firstPeriods;
	}

	public void setFirstPeriods(String firstPeriods) {
		this.firstPeriods = firstPeriods;
	}

	public String getFirstPerceivedRate() {
		return firstPerceivedRate;
	}

	public void setFirstPerceivedRate(String firstPerceivedRate) {
		this.firstPerceivedRate = firstPerceivedRate;
	}

	public BigDecimal getTenderCapital() {
		return tenderCapital;
	}

	public void setTenderCapital(BigDecimal tenderCapital) {
		this.tenderCapital = tenderCapital;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public BigDecimal getInterestDiff() {
		return interestDiff;
	}

	public void setInterestDiff(BigDecimal interestDiff) {
		this.interestDiff = interestDiff;
	}

	public BigDecimal getAccount() {
		return account;
	}

	public void setAccount(BigDecimal account) {
		this.account = account;
	}

	public BigDecimal getAwards() {
		return awards;
	}

	public void setAwards(BigDecimal awards) {
		this.awards = awards;
	}

	public BigDecimal getAccountReal() {
		return accountReal;
	}

	public void setAccountReal(BigDecimal accountReal) {
		this.accountReal = accountReal;
	}

	public BigDecimal getCoef() {
		return coef;
	}

	public void setCoef(BigDecimal coef) {
		this.coef = coef;
	}

	public BigDecimal getManageFee() {
		return manageFee;
	}

	public void setManageFee(BigDecimal manageFee) {
		this.manageFee = manageFee;
	}

	public BigDecimal getInterestManageFee() {
		return interestManageFee;
	}

	public void setInterestManageFee(BigDecimal interestManageFee) {
		this.interestManageFee = interestManageFee;
	}

	public Integer getValidTime() {
		return validTime;
	}

	public void setValidTime(Integer validTime) {
		this.validTime = validTime;
	}

	public String getTransferName() {
		return transferName;
	}

	public void setTransferName(String transferName) {
		this.transferName = transferName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getLastUpdateName() {
		return lastUpdateName;
	}

	public void setLastUpdateName(String lastUpdateName) {
		this.lastUpdateName = lastUpdateName;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getSuccessTime() {
		return successTime;
	}

	public void setSuccessTime(Date successTime) {
		this.successTime = successTime;
	}

	public String getBidPassword() {
		return bidPassword;
	}

	public void setBidPassword(String bidPassword) {
		this.bidPassword = bidPassword;
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

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}

	public BigDecimal getActualInterest() {
		return actualInterest;
	}

	public void setActualInterest(BigDecimal actualInterest) {
		this.actualInterest = actualInterest;
	}

	public BigDecimal getAvgCycle() {
		return avgCycle;
	}

	public void setAvgCycle(BigDecimal avgCycle) {
		this.avgCycle = avgCycle;
	}

	public BigDecimal getAvgRate() {
		return avgRate;
	}

	public void setAvgRate(BigDecimal avgRate) {
		this.avgRate = avgRate;
	}

}
