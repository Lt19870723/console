package com.cxdai.console.base.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * Description:借款标<br />
 * </p>
 * 
 * @title Borrow.java
 * @package com.cxdai.base.entity
 * @author justin.xu
 * @version 0.1 2014年5月15日
 */
public class Borrow implements Serializable {

	private static final long serialVersionUID = -662710165519685271L;
	/** 主键ID */
	private Integer id;
	/** 用户ID */
	private Integer userId;
	/** 借款标题 */
	private String name;
	/** 状态 （0:草稿标 1：新标，审核中，2：投标中，3：满标复审中，4：还款中，5：还款结束，-1：流标，-2：被撤销 ，-3：审核失败） */
	private Integer status;
	/** 审核状态，目前采用4步审核【审核不通过对应的状态取负值】（-1:草稿标 0：待审核；1：初审通过，2：反欺诈通过，3：终审通过，4：复审通过；） */
	private Integer apprstatus;
	/** 排序 */
	private Integer order;
	/** 借款标种类（ 1：信用标，2：抵押标，3：净值标，4：秒标 5：担保标 ） */
	private Integer borrowtype;
	/** 应还总额 */
	private BigDecimal repaymentAccount;
	/** 每月还款额 */
	private BigDecimal monthlyRepayment;
	/** 已还金额 */
	private BigDecimal repaymentYesaccount;
	/** 已还利息 */
	private BigDecimal repaymentYesinterest;
	/** 满标时间 */
	private String successTime;
	/** 结束时间 */
	private String endTime;
	/** 用途【1:短期周转 2:生意周转 3:生活周转 4:购物消费 5:创业借款 6:其它借款】 */
	private String use;
	/** 借款期限 */
	private Integer timeLimit;
	/** 还款方式【 0：没有限制， 1：按月分期还款，2：按月付息到期还本 , 3：到期还本付息,4:按天到期还款 */
	private Integer style;
	/** 借贷总金额 */
	private BigDecimal account;
	/** 已经借到的金额 */
	private BigDecimal accountYes;
	/** 投标次数 */
	private Integer tenderTimes;
	/** 年利率 */
	private BigDecimal apr;
	/** 最低投标金额 */
	private BigDecimal lowestAccount;
	/** 最多投标金额 */
	private BigDecimal mostAccount;
	/** 有效时间 天数 */
	private Integer validTime;
	/** 投标奖励【 0：不设置奖励; 1：按投标金额比例奖励】 */
	private Integer award;
	/** 奖励的比例 */
	private BigDecimal funds;
	/** 借款详细说明 */
	private String content;
	/** 完成后发送站内信，0不发送，1发送 */
	private Integer sendmessage;
	/** 添加时间 */
	private Date addtime;
	/** 添加IP */
	private String addip;
	/** 撤销人（0：发标人自己撤销，其他数字表示员工ID，-1：系统流标） */
	private Integer cancelUser;
	/** 撤销时间 */
	private String cancelTime;
	/** 撤销备注 */
	private String cancelRemark;
	/** 借款标唯一标识 */
	private String uuid;
	/** 合同编号 */
	private String contractNo;
	/** 投标密码 */
	private String bidPassword;
	/** 是否正在自动投标 0：否，1：是 */
	private Integer isAutotender;
	/** 备注 */
	private String remark;
	/** 抵押标类型（1：新增，2：续贷，3：资产处理） */
	private Integer pledgeType;
	/** 定时发标时间 */
	private String timingBorrowTime;
	/** 发布时间 */
	private String publishTime;

	// 新增字段20140812
	/** 是否抵押 0：否，1：是 */
	private Integer isMortgage;
	/** 抵押类型 1-房抵 2-车抵 3-民间抵 */
	private Integer mortgageType;
	/** 是否机构担保 0：否，1：是 */
	private Integer isGuaranty;
	/** 担保机构ID */
	private Integer guarantyOrganization;
	/** 是否有连带担保物或担保人 1-有 0-无 */
	private Integer isJointGuaranty;
	/** 连带担保信息 */
	private String jointGuaranty;
	/** 借款用途，手输 */
	private String borrowUse;
	/** 借贷类型：1-诚薪贷 2-诚商贷 3-净值贷 */
	private Integer productType;
	/** 信用等级 分A、B、C、D四个等级 **/
	private String creditRating;
	/** 平台来源 */
	private String platform;

	// 属性，非字段
	private Integer timeLimitDay;

	/** 专区类型【0：普通专区，1：新手专区】 */
	private Integer areaType;
	/** 新手专区转普通专区时间 */
	private Date areaChangeTime;
	/**存管方式   0：非存管，1：浙商存管 **/
	private Integer isCustody;
	/**是否登记  0：未登记，1：已登记 **/
	private Integer isCheck;
	/**银行登记项目ID **/
	private String eProjectId;
	/**还款账户名 **/
	private String repayName;
	/**还款账户账号 **/
	private String repayAcct;
	/**投资冻结总金额 **/
	private BigDecimal advance;
	/**投资成功笔数 **/
	private Integer succount;
	/**投资成功金额  **/
	private BigDecimal sucsum;
	/**投资失败笔数 **/
	private Integer failcount;
	/**投资失败金额 **/
	private BigDecimal failsum;
	
	private String isCustodyStr;
	
	private Integer isNotice;//是否已通知(普惠发标)
	
	
	
	public Integer getIsNotice() {
		return isNotice;
	}

	public void setIsNotice(Integer isNotice) {
		this.isNotice = isNotice;
	}

	public String getIsCustodyStr() {
		return isCustodyStr;
	}

	public void setIsCustodyStr(String isCustodyStr) {
		this.isCustodyStr = isCustodyStr;
	}

	public BigDecimal getAdvance() {
		return advance;
	}

	public void setAdvance(BigDecimal advance) {
		this.advance = advance;
	}

	public Integer getSuccount() {
		return succount;
	}

	public void setSuccount(Integer succount) {
		this.succount = succount;
	}

	public BigDecimal getSucsum() {
		return sucsum;
	}

	public void setSucsum(BigDecimal sucsum) {
		this.sucsum = sucsum;
	}

	public Integer getFailcount() {
		return failcount;
	}

	public void setFailcount(Integer failcount) {
		this.failcount = failcount;
	}

	public BigDecimal getFailsum() {
		return failsum;
	}

	public void setFailsum(BigDecimal failsum) {
		this.failsum = failsum;
	}

	public Integer getIsCustody() {
		return isCustody;
	}

	public void setIsCustody(Integer isCustody) {
		this.isCustody = isCustody;
	}

	public Integer getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(Integer isCheck) {
		this.isCheck = isCheck;
	}

	public String geteProjectId() {
		return eProjectId;
	}

	public void seteProjectId(String eProjectId) {
		this.eProjectId = eProjectId;
	}

	public String getRepayName() {
		return repayName;
	}

	public void setRepayName(String repayName) {
		this.repayName = repayName;
	}

	public String getRepayAcct() {
		return repayAcct;
	}

	public void setRepayAcct(String repayAcct) {
		this.repayAcct = repayAcct;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getApprstatus() {
		return apprstatus;
	}

	public void setApprstatus(Integer apprstatus) {
		this.apprstatus = apprstatus;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getBorrowtype() {
		return borrowtype;
	}

	public void setBorrowtype(Integer borrowtype) {
		this.borrowtype = borrowtype;
	}

	public BigDecimal getRepaymentAccount() {
		return repaymentAccount;
	}

	public void setRepaymentAccount(BigDecimal repaymentAccount) {
		this.repaymentAccount = repaymentAccount;
	}

	public BigDecimal getMonthlyRepayment() {
		return monthlyRepayment;
	}

	public void setMonthlyRepayment(BigDecimal monthlyRepayment) {
		this.monthlyRepayment = monthlyRepayment;
	}

	public BigDecimal getRepaymentYesaccount() {
		return repaymentYesaccount;
	}

	public void setRepaymentYesaccount(BigDecimal repaymentYesaccount) {
		this.repaymentYesaccount = repaymentYesaccount;
	}

	public BigDecimal getRepaymentYesinterest() {
		return repaymentYesinterest;
	}

	public void setRepaymentYesinterest(BigDecimal repaymentYesinterest) {
		this.repaymentYesinterest = repaymentYesinterest;
	}

	public String getSuccessTime() {
		return successTime;
	}

	public void setSuccessTime(String successTime) {
		this.successTime = successTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getUse() {
		return use;
	}

	public void setUse(String use) {
		this.use = use;
	}

	public Integer getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}

	public Integer getStyle() {
		return style;
	}

	public void setStyle(Integer style) {
		this.style = style;
	}

	public BigDecimal getAccount() {
		return account;
	}

	public void setAccount(BigDecimal account) {
		this.account = account;
	}

	public BigDecimal getAccountYes() {
		return accountYes;
	}

	public void setAccountYes(BigDecimal accountYes) {
		this.accountYes = accountYes;
	}

	public Integer getTenderTimes() {
		return tenderTimes;
	}

	public void setTenderTimes(Integer tenderTimes) {
		this.tenderTimes = tenderTimes;
	}

	public BigDecimal getApr() {
		return apr;
	}

	public void setApr(BigDecimal apr) {
		this.apr = apr;
	}

	public BigDecimal getLowestAccount() {
		return lowestAccount;
	}

	public void setLowestAccount(BigDecimal lowestAccount) {
		this.lowestAccount = lowestAccount;
	}

	public BigDecimal getMostAccount() {
		return mostAccount;
	}

	public void setMostAccount(BigDecimal mostAccount) {
		this.mostAccount = mostAccount;
	}

	public Integer getValidTime() {
		return validTime;
	}

	public void setValidTime(Integer validTime) {
		this.validTime = validTime;
	}

	public Integer getAward() {
		return award;
	}

	public void setAward(Integer award) {
		this.award = award;
	}

	public BigDecimal getFunds() {
		return funds;
	}

	public void setFunds(BigDecimal funds) {
		this.funds = funds;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getSendmessage() {
		return sendmessage;
	}

	public void setSendmessage(Integer sendmessage) {
		this.sendmessage = sendmessage;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getAddip() {
		return addip;
	}

	public void setAddip(String addip) {
		this.addip = addip;
	}

	public Integer getCancelUser() {
		return cancelUser;
	}

	public void setCancelUser(Integer cancelUser) {
		this.cancelUser = cancelUser;
	}

	public String getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(String cancelTime) {
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

	public Integer getIsAutotender() {
		return isAutotender;
	}

	public void setIsAutotender(Integer isAutotender) {
		this.isAutotender = isAutotender;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getPledgeType() {
		return pledgeType;
	}

	public void setPledgeType(Integer pledgeType) {
		this.pledgeType = pledgeType;
	}

	public String getTimingBorrowTime() {
		return timingBorrowTime;
	}

	public void setTimingBorrowTime(String timingBorrowTime) {
		this.timingBorrowTime = timingBorrowTime;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public Integer getIsMortgage() {
		return isMortgage;
	}

	public void setIsMortgage(Integer isMortgage) {
		this.isMortgage = isMortgage;
	}

	public Integer getMortgageType() {
		return mortgageType;
	}

	public void setMortgageType(Integer mortgageType) {
		this.mortgageType = mortgageType;
	}

	public Integer getIsGuaranty() {
		return isGuaranty;
	}

	public void setIsGuaranty(Integer isGuaranty) {
		this.isGuaranty = isGuaranty;
	}

	public Integer getGuarantyOrganization() {
		return guarantyOrganization;
	}

	public void setGuarantyOrganization(Integer guarantyOrganization) {
		this.guarantyOrganization = guarantyOrganization;
	}

	public Integer getIsJointGuaranty() {
		return isJointGuaranty;
	}

	public void setIsJointGuaranty(Integer isJointGuaranty) {
		this.isJointGuaranty = isJointGuaranty;
	}

	public String getJointGuaranty() {
		return jointGuaranty;
	}

	public void setJointGuaranty(String jointGuaranty) {
		this.jointGuaranty = jointGuaranty;
	}

	public String getBorrowUse() {
		return borrowUse;
	}

	public void setBorrowUse(String borrowUse) {
		this.borrowUse = borrowUse;
	}

	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	public Integer getTimeLimitDay() {
		return timeLimitDay;
	}

	public void setTimeLimitDay(Integer timeLimitDay) {
		this.timeLimitDay = timeLimitDay;
	}

	public String getCreditRating() {
		return creditRating;
	}

	public void setCreditRating(String creditRating) {
		this.creditRating = creditRating;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

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

}