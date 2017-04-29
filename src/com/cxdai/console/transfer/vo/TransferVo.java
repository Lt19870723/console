package com.cxdai.console.transfer.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.cxdai.console.util.DateUtils;

public class TransferVo implements Serializable {


	/**
	 * @fields serialVersionUID
	 */
	private static final long serialVersionUID = 813573271032738944L;
	// 主键ID
	private Integer id;
	// 借款标ID
	private Integer borrowId;
	// 借款标题
	private String borrowName;
	// 借款标信用等级(A,B,C,D)
	private String borrowCreditRating;
	// 年利率
	private BigDecimal borrowApr;
	// 还款方式
	private Integer borrowStyle;
	// 期限
	private Integer borrowTimeLimit;
	// 期数
	private Integer borrowOrder;
	// 投标ID
	private Integer tenderId;
	// 投标金额
	private BigDecimal tenderCapital;
	// 债权转让标题
	private String transferName;
	// 债权转让内容
	private String transferContent;
	// 转让信用等级(A,B,C,D)
	private String transferCreditRating;
	// 原始投资本金
	private BigDecimal capital;
	// 应得利息
	private BigDecimal interest;
	// 剩余债权价值
	private BigDecimal account;
	// 转让系数
	private BigDecimal coef;
	// 转让价格
	private BigDecimal accountReal;
	// 转让管理费
	private BigDecimal manageFee;
	// 所得的利息管理费
	private BigDecimal interestManageFee;
	// 差额利息
	private BigDecimal interestDiff;
	// 转让盈亏(= INTEREST_MANAGE_FEE + INTEREST_DIFF)
	private BigDecimal gainLoss;
	// 最大认购金额
	private BigDecimal mostAccount;
	// 最小认购金额
	private BigDecimal lowestAccount;
	// 有效时间(天)
	private Integer validTime;
	// 认购密码(MD5)
	private String bidPassword;
	// 债权转让人
	private Integer userId;
	// 是否自动投标 0：否，1：是
	private Boolean isAutotransfer;
	// 定时发标时间
	private Date timingTime;
	// 添加时间
	private Date addTime;
	// 添加IP
	private String addIp;
	// 添加MAC
	private String addMac;
	// 操作来源(0:官网,1:wap,2:后台)
	private Integer addSystem;
	// 结束时间(流标时间)
	private Date endTime;
	// 满标时间
	private Date successTime;
	// 投标次数
	private Integer tenderTimes;
	// 已经借到的金额
	private BigDecimal accountYes;
	// 撤销人
	private Integer cancelUser;
	// 撤销时间
	private Date cancelTime;
	// 撤销备注
	private String cancelIp;
	// 撤销IP
	private String cancelMac;
	// 撤销MAC
	private String cancelRemark;
	// 合同编号
	private String contractNo;
	// 状态
	private Integer status;
	// 完成后发送站内信，0不发送，1发送
	private Integer sendmessage;
	// 备注
	private String remark;

	/** 借款类型 **/
	private String borrowtype;
	/** 借款人 **/
	private String borrowUserName;

	private Date nextRepaymentDate;// 下个还款日

	private int borrowType; // 借款标种类（ 1：信用标，2：抵押标，3：净值标，4：秒标 5：担保标 ）

	// 状态字符串
	private String statustr;

	// 用户名
	private String username;

	private String addTimeStr;
	
	private String successTimeStr;
	private String cancelTimeStr;

	private String cancelUserStr;



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}

	public String getBorrowName() {
		return borrowName;
	}

	public void setBorrowName(String borrowName) {
		this.borrowName = borrowName;
	}

	public String getBorrowCreditRating() {
		return borrowCreditRating;
	}

	public void setBorrowCreditRating(String borrowCreditRating) {
		this.borrowCreditRating = borrowCreditRating;
	}

	public BigDecimal getBorrowApr() {
		return borrowApr;
	}

	public void setBorrowApr(BigDecimal borrowApr) {
		this.borrowApr = borrowApr;
	}

	public Integer getTenderId() {
		return tenderId;
	}

	public void setTenderId(Integer tenderId) {
		this.tenderId = tenderId;
	}

	public BigDecimal getTenderCapital() {
		return tenderCapital;
	}

	public void setTenderCapital(BigDecimal tenderCapital) {
		this.tenderCapital = tenderCapital;
	}

	public String getTransferName() {
		return transferName;
	}

	public void setTransferName(String transferName) {
		this.transferName = transferName;
	}

	public String getTransferContent() {
		return transferContent;
	}

	public void setTransferContent(String transferContent) {
		this.transferContent = transferContent;
	}

	public String getTransferCreditRating() {
		return transferCreditRating;
	}

	public void setTransferCreditRating(String transferCreditRating) {
		this.transferCreditRating = transferCreditRating;
	}

	public BigDecimal getCapital() {
		return capital;
	}

	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public BigDecimal getAccount() {
		return account;
	}

	public void setAccount(BigDecimal account) {
		this.account = account;
	}

	public BigDecimal getCoef() {
		return coef;
	}

	public void setCoef(BigDecimal coef) {
		this.coef = coef;
	}

	public BigDecimal getAccountReal() {
		return accountReal;
	}

	public void setAccountReal(BigDecimal accountReal) {
		this.accountReal = accountReal;
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

	public BigDecimal getInterestDiff() {
		return interestDiff;
	}

	public void setInterestDiff(BigDecimal interestDiff) {
		this.interestDiff = interestDiff;
	}

	public BigDecimal getGainLoss() {
		return gainLoss;
	}

	public void setGainLoss(BigDecimal gainLoss) {
		this.gainLoss = gainLoss;
	}

	public BigDecimal getMostAccount() {
		return mostAccount;
	}

	public void setMostAccount(BigDecimal mostAccount) {
		this.mostAccount = mostAccount;
	}

	public BigDecimal getLowestAccount() {
		return lowestAccount;
	}

	public void setLowestAccount(BigDecimal lowestAccount) {
		this.lowestAccount = lowestAccount;
	}

	public Integer getValidTime() {
		return validTime;
	}

	public void setValidTime(Integer validTime) {
		this.validTime = validTime;
	}

	public String getBidPassword() {
		return bidPassword;
	}

	public void setBidPassword(String bidPassword) {
		this.bidPassword = bidPassword;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Boolean getIsAutotransfer() {
		return isAutotransfer;
	}

	public void setIsAutotransfer(Boolean isAutotransfer) {
		this.isAutotransfer = isAutotransfer;
	}

	public Date getTimingTime() {
		return timingTime;
	}

	public void setTimingTime(Date timingTime) {
		this.timingTime = timingTime;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getAddIp() {
		return addIp;
	}

	public void setAddIp(String addIp) {
		this.addIp = addIp;
	}

	public String getAddMac() {
		return addMac;
	}

	public void setAddMac(String addMac) {
		this.addMac = addMac;
	}

	public Integer getAddSystem() {
		return addSystem;
	}

	public void setAddSystem(Integer addSystem) {
		this.addSystem = addSystem;
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

	public BigDecimal getAccountYes() {
		return accountYes;
	}

	public void setAccountYes(BigDecimal accountYes) {
		this.accountYes = accountYes;
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

	public String getCancelIp() {
		return cancelIp;
	}

	public void setCancelIp(String cancelIp) {
		this.cancelIp = cancelIp;
	}

	public String getCancelMac() {
		return cancelMac;
	}

	public void setCancelMac(String cancelMac) {
		this.cancelMac = cancelMac;
	}

	public String getCancelRemark() {
		return cancelRemark;
	}

	public void setCancelRemark(String cancelRemark) {
		this.cancelRemark = cancelRemark;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getBorrowStyle() {
		return borrowStyle;
	}

	public void setBorrowStyle(Integer borrowStyle) {
		this.borrowStyle = borrowStyle;
	}

	public Integer getBorrowTimeLimit() {
		return borrowTimeLimit;
	}

	public void setBorrowTimeLimit(Integer borrowTimeLimit) {
		this.borrowTimeLimit = borrowTimeLimit;
	}

	public Integer getBorrowOrder() {
		return borrowOrder;
	}

	public void setBorrowOrder(Integer borrowOrder) {
		this.borrowOrder = borrowOrder;
	}

	public Integer getTenderTimes() {
		return tenderTimes;
	}

	public void setTenderTimes(Integer tenderTimes) {
		this.tenderTimes = tenderTimes;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSendmessage() {
		return sendmessage;
	}

	public void setSendmessage(Integer sendmessage) {
		this.sendmessage = sendmessage;
	}

	public int getBorrowType() {
		return borrowType;
	}

	public void setBorrowType(int borrowType) {
		this.borrowType = borrowType;
	}

	public Date getNextRepaymentDate() {
		return nextRepaymentDate;
	}

	public void setNextRepaymentDate(Date nextRepaymentDate) {
		this.nextRepaymentDate = nextRepaymentDate;
	}

	public String getBorrowtype() {
		return borrowtype;
	}

	public void setBorrowtype(String borrowtype) {
		this.borrowtype = borrowtype;
	}

	public String getBorrowUserName() {
		return borrowUserName;
	}

	public void setBorrowUserName(String borrowUserName) {
		this.borrowUserName = borrowUserName;
	}

	public String getStatustr() {
		return statustr;
	}

	public void setStatustr(String statustr) {
		this.statustr = statustr;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddTimeStr() {
		if (addTime != null) {
			return DateUtils.format(addTime, DateUtils.YMD_HMS);
		}
		return addTimeStr;
	}

	public void setAddTimeStr(String addTimeStr) {
		this.addTimeStr = addTimeStr;
	}

	public String getSuccessTimeStr() {
		if (successTime != null) {
			return DateUtils.format(successTime, DateUtils.YMD_HMS);
		}
		return successTimeStr;
	}

	public void setSuccessTimeStr(String successTimeStr) {
		this.successTimeStr = successTimeStr;
	}

	public String getCancelUserStr() {
		return cancelUserStr;
	}

	public void setCancelUserStr(String cancelUserStr) {
		this.cancelUserStr = cancelUserStr;
	}

	public String getCancelTimeStr() {
		
		if (cancelTime != null) {
			return DateUtils.format(cancelTime, DateUtils.YMD_HMS);
		}
		
		return cancelTimeStr;
	}

	public void setCancelTimeStr(String cancelTimeStr) {
		this.cancelTimeStr = cancelTimeStr;
	}




}
