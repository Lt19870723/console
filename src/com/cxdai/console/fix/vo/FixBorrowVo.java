package com.cxdai.console.fix.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.cxdai.console.common.Dictionary;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.MoneyUtil;

/**
 * 定期宝类
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title FixBorrowVo.java
 * @package com.cxdai.console.fix.vo
 * @author 陈建国
 * @version 0.1 2015年6月3日
 */
public class FixBorrowVo implements Serializable {
	private static final long serialVersionUID = -5552272784546139026L;
	// 高亮标记
	private int tLight;
	// 本金
	private BigDecimal capital;

	// 锁定结束日期
	private Date lockEndtime;
	private String lockEndtimeStr;

	// 一键发宝
	private String borrowName;
	private String parentContractNo;
	private Date tenderTime;
	// 默认开通时间
	private Date defaultPubTime;
	// 转让价格
	private BigDecimal zrMoney;

	/**
	 * 主键ID
	 */
	private Integer id;
	/**
	 * 标题
	 */
	private String name;

	/**
	 * 状态 （0:草稿标
	 * 1：新标,审核中，2：审核不通过，3：审核通过投标中，4：满标复审中，5：满标审核通过，6：满标审核拒绝，-1：流标，-2：被撤销,-3:已过期
	 * ）
	 */
	private Integer status;
	/**
	 * 计划金额
	 */
	private BigDecimal planAccount;

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
	 * 锁定方式 （0：月）
	 */
	private Integer lockStyle;
	/**
	 * 锁定结束日期，精确到天
	 */
	private Date lockEndTime;
	/**
	 * 预期收益
	 */
	private BigDecimal apr;

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
	private Date addTime;
	/**
	 * 添加IP
	 */
	private String addIp;
	/**
	 * 撤销人（0：发标人自己撤销，其他数字表示员工ID，-1：系统流标）
	 */
	private Integer lastModifyUser;
	/**
	 * 撤销时间
	 */
	private Date lastModifyTime;

	/**
	 * 撤销备注
	 */
	private String lastModifyRemark;
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
	private BigDecimal accountYes;
	/**
	 * 密码原文
	 */
	private String passwordSource;

	/** 认购截止时间 */
	private Date endTime;

	private String sourceFrom;

	private BigDecimal borrowCapital; // 投标回款本息
	private BigDecimal borrowInterest; // 投标利息
	private String borrowContractNo;// 投标借款编号
	private Date borrowRepayTime;// 投标回款时间

	private BigDecimal account;// 投标回款金额

	private BigDecimal totalInterest;// 定期宝总利息

	/** 转换后格式 */
	private String statusStr;
	private String speicStatusStr1; // 针对特殊的加入中和预发中的状态设置
	private String planAccountStr;// 以万返回千位分隔
	private String mostAccountStr;// 以万返回千位分隔
	private String borrowCapitalStr; // 投标回款本息
	private String borrowInterestStr; // 投标利息
	private String lowestAccountStr;// 以万返回千位分隔
	private String lockStyleStr;
	private String lockLimitStr;
	private String publishTimeStr;
	private String lockEndTimeStr;
	private String endTimeStr;
	private String cancelTimeStr;
	private String successTimeStr;
	private String borrowRepayTimeStr;// 投标回款时间
	private String planAccountSource;// 以万返回
	private String mostAccountSource;// 以万返回
	private String lowestAccountSource;// 以万返回
	private String sourceFromStr;// 返回来源
	private String aprStr;
	// 是否清空密码
	private String cleanPassword;
	private Integer validTimeStyle; // 有效期限单位 1：按天，2：按小时，3：按分钟
	private String validTimeStr;
	private String totalInvest;
	private String repaymentMoney;
	private String useMoney; // 可用金额
	private String planAccountSpecilStr; // 已元为单位，格式化

	private String userName;
	private String bidPasswordTemp;
	public String getBidPasswordTemp() {
		return bidPasswordTemp;
	}

	public void setBidPasswordTemp(String bidPasswordTemp) {
		this.bidPasswordTemp = bidPasswordTemp;
	}

	/**
	 * 定期宝账户可用余额
	 */
	private BigDecimal fixAccountUserMoney;

	/**
	 * 是否可以修改
	 */
	private Integer flagUpdate;

	/**
	 * 专区类型【0：普通专区，1：新手专区】
	 */
	private Integer areaType;
	/**
	 * 新手专区转普通专区时间
	 */
	private Date areaChangeTime;

	private Integer tenderBidFlag;// 限制自动投标标记：0，不限制；1，限制
	private Date tenderBidDate;// 限制自动投标日期，此日期（含）之前不自动投标
	private Integer diffTenderDays;//当前日期-限制自动投标日期，>0代表可自动投标
	
	

	public Integer getDiffTenderDays() {
		return diffTenderDays;
	}

	public void setDiffTenderDays(Integer diffTenderDays) {
		this.diffTenderDays = diffTenderDays;
	}

	public Integer getTenderBidFlag() {
		return tenderBidFlag;
	}

	public void setTenderBidFlag(Integer tenderBidFlag) {
		this.tenderBidFlag = tenderBidFlag;
	}

	public Date getTenderBidDate() {
		return tenderBidDate;
	}

	public void setTenderBidDate(Date tenderBidDate) {
		this.tenderBidDate = tenderBidDate;
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

	public Integer getFlagUpdate() {
		flagUpdate = 0;
		// 当前时间往后推半小时
		Date beforeNow = DateUtils.minuteOffset(new Date(), 30);
		if (null != publishTime) {
			if (publishTime.getTime() >= beforeNow.getTime()) {
				flagUpdate = 1;
			}
		}
		return flagUpdate;
	}

	public String getAprStr() {
		if (apr == null) {
			return new BigDecimal(0).intValue() + "%";
		}
		return apr.intValue() + "%";
	}

	/**
	 * 定期宝待还利息
	 */
	private BigDecimal interest;

	public String getTotalInvest() {
		return totalInvest;
	}

	public void setTotalInvest(String totalInvest) {
		this.totalInvest = totalInvest;
	}

	public String getRepaymentMoney() {
		return repaymentMoney;
	}

	public void setRepaymentMoney(String repaymentMoney) {
		this.repaymentMoney = repaymentMoney;
	}

	public String getUseMoney() {
		return useMoney;
	}

	public void setUseMoney(String useMoney) {
		this.useMoney = useMoney;
	}

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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Date getLockEndTime() {
		return lockEndTime;
	}

	public void setLockEndTime(Date lockEndTime) {
		this.lockEndTime = lockEndTime;
	}

	public BigDecimal getApr() {
		return apr;
	}

	public void setApr(BigDecimal apr) {
		this.apr = apr;
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

	public Integer getLastModifyUser() {
		return lastModifyUser;
	}

	public void setLastModifyUser(Integer lastModifyUser) {
		this.lastModifyUser = lastModifyUser;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public String getLastModifyRemark() {
		return lastModifyRemark;
	}

	public void setLastModifyRemark(String lastModifyRemark) {
		this.lastModifyRemark = lastModifyRemark;
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

	public String getPasswordSource() {
		return passwordSource;
	}

	public void setPasswordSource(String passwordSource) {
		this.passwordSource = passwordSource;
	}

	public String getStatusStr() {
		if (null != status && "3".equals(status.toString())
				&& DateUtils.secondDiff(new Date(), publishTime) < 0) {
			return "预发中";
		} else if (null != status && "3".equals(status.toString())
				&& DateUtils.secondDiff(new Date(), publishTime) > 0) {
			return "开放中";
		}else{
			return Dictionary.getValue(1501, String.valueOf(status));
		}
	}

	public String getSpeicStatusStr1() {
		// 增加对预发中和加入中特殊状态的判断

		if (null != status && "3".equals(status.toString())
				&& DateUtils.secondDiff(new Date(), publishTime) < 0) {
			return "预发中";
		} else if (null != status && "3".equals(status.toString())
				&& DateUtils.secondDiff(new Date(), publishTime) > 0) {
			return "开放中";
		} else if (null != status && "5".equals(status.toString())) {
			return "收益中";
		} else if (null != status && "7".equals(status.toString())) {
			return "已退出";
		} else if (null != status && "-2".equals(status.toString())) {
			return "已撤销";
		} else if (null != status && "-1".equals(status.toString())) {
			return "已流宝";
		} else {
			return "";
		}

	}

	public String getPlanAccountStr() {
		if (null != planAccount) {
			return BusinessConstants.numberDf.format(planAccount
					.divide(new BigDecimal(10000)));
		}
		return planAccountStr;
	}

	public String getEndTimeStr() {
		if (null != endTime) {
			return DateUtils.format(endTime, DateUtils.YMD_HMS);
		}
		return endTimeStr;
	}

	public String getLockStyleStr() {
		return Dictionary.getValue(804, String.valueOf(lockStyle));
	}

	public String getPublishTimeStr() {
		if (null != publishTime) {
			return DateUtils.format(publishTime, DateUtils.YMD_HMS);
		}
		return publishTimeStr;
	}

	public String getCleanPassword() {
		return cleanPassword;
	}

	public void setCleanPassword(String cleanPassword) {
		this.cleanPassword = cleanPassword;
	}

	public String getLockEndtimeStr() {
		if (null != lockEndTime) {
			return DateUtils.format(lockEndTime, DateUtils.YMD_DASH);
		}
		return lockEndTimeStr;
	}

	public String getLastModifyTimeStr() {
		if (null != lastModifyTime) {
			return DateUtils.format(lastModifyTime, DateUtils.YMD_DASH);
		}
		return lockEndTimeStr;
	}

	public String getLockLimitStr() {
		if (null != lockLimit) {
			return lockLimit + "个月";
		}
		return "";
	}

	public String getSuccessTimeStr() {
		if (null != successTime) {
			return DateUtils.format(successTime, DateUtils.YMD_HMS);
		}
		return successTimeStr;
	}

	public String getBorrowRepayTimeStr() {
		if (null != borrowRepayTime) {
			return DateUtils.format(borrowRepayTime, DateUtils.YMD_HMS);
		}
		return "";
	}

	public String getPlanAccountSource() {
		if (null != planAccount) {
			return String.valueOf(planAccount.divide(new BigDecimal(10000)));
		}
		return planAccountSource;
	}

	public String getMostAccountSource() {
		if (null != mostAccount) {
			return String.valueOf(mostAccount.divide(new BigDecimal(10000)));
		}
		return mostAccountSource;
	}

	public String getLowestAccountSource() {
		if (null != lowestAccount) {
			return String.valueOf(lowestAccount.divide(new BigDecimal(10000)));
		}
		return lowestAccountSource;
	}

	public String getMostAccountStr() {
		if (null != mostAccount) {
			return BusinessConstants.numberDf.format(mostAccount
					.divide(new BigDecimal(10000)));
		}
		return mostAccountStr;
	}
	public String getPlanAccountStr2() {
		if (null != planAccount) {
			return MoneyUtil.fmtMoneyByDot(planAccount);
		}
		return planAccountStr;
	}
	public String getBorrowCapitalStr() {
		if (null != borrowCapital) {
			return MoneyUtil.fmtMoneyByDot(borrowCapital);
		}
		return "0.00";
	}

	public String getBorrowInterestStr() {
		if (null != borrowInterest) {
			return MoneyUtil.fmtMoneyByDot(borrowInterest);
		}
		return "0.00";
	}

	public String getSourceFromStr() {
		if (null != sourceFrom) {
			return "1".equals(sourceFrom) == true ? "新发宝" : "接手宝";
		}
		return mostAccountStr;
	}

	public String getLowestAccountStr() {
		if (null != lowestAccount) {
			return BusinessConstants.decimalPercentDf.format(lowestAccount
					.divide(new BigDecimal(10000)));
		}
		return lowestAccountStr;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getValidTimeStyle() {
		return validTimeStyle;
	}

	public void setValidTimeStyle(Integer validTimeStyle) {
		this.validTimeStyle = validTimeStyle;
	}

	public String getSourceFrom() {
		return sourceFrom;
	}

	public void setSourceFrom(String sourceFrom) {
		this.sourceFrom = sourceFrom;
	}

	public String getValidTimeStr() {
		int a = 24 * 60;
		int b = 60;
		if (validTime == null) {
			return "0分钟";
		}
		if ((validTime % a) == 0) {
			return (validTime / a) + "天";
		} else {
			if ((validTime % b) == 0) {
				return (validTime / b) + "小时";
			} else {
				return validTime + "分钟";
			}
		}
	}

	public void setValidTimeStr(String validTimeStr) {
		this.validTimeStr = validTimeStr;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public String getPlanAccountSpecilStr() {
		return planAccountSpecilStr;
	}

	public void setPlanAccountSpecilStr(String planAccountSpecilStr) {
		this.planAccountSpecilStr = planAccountSpecilStr;
	}

	public BigDecimal getBorrowCapital() {
		return borrowCapital;
	}

	public void setBorrowCapital(BigDecimal borrowCapital) {
		this.borrowCapital = borrowCapital;
	}

	public BigDecimal getBorrowInterest() {
		return borrowInterest;
	}

	public void setBorrowInterest(BigDecimal borrowInterest) {
		this.borrowInterest = borrowInterest;
	}

	public String getBorrowContractNo() {
		return borrowContractNo;
	}

	public void setBorrowContractNo(String borrowContractNo) {
		this.borrowContractNo = borrowContractNo;
	}

	public Date getBorrowRepayTime() {
		return borrowRepayTime;
	}

	public void setBorrowRepayTime(Date borrowRepayTime) {
		this.borrowRepayTime = borrowRepayTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public BigDecimal getAccount() {
		return account;
	}

	public void setAccount(BigDecimal account) {
		this.account = account;
	}

	public BigDecimal getPlanAccount() {
		return planAccount;
	}

	public void setPlanAccount(BigDecimal planAccount) {
		this.planAccount = planAccount;
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

	public BigDecimal getAccountYes() {
		return accountYes;
	}

	public void setAccountYes(BigDecimal accountYes) {
		this.accountYes = accountYes;
	}

	public BigDecimal getTotalInterest() {
		return totalInterest;
	}

	public void setTotalInterest(BigDecimal totalInterest) {
		this.totalInterest = totalInterest;
	}

	/**
	 * @return borrowName : return the property borrowName.
	 */
	public String getBorrowName() {
		return borrowName;
	}

	/**
	 * @param borrowName
	 *            : set the property borrowName.
	 */
	public void setBorrowName(String borrowName) {
		this.borrowName = borrowName;
	}

	/**
	 * @return parentContractNo : return the property parentContractNo.
	 */
	public String getParentContractNo() {
		return parentContractNo;
	}

	/**
	 * @param parentContractNo
	 *            : set the property parentContractNo.
	 */
	public void setParentContractNo(String parentContractNo) {
		this.parentContractNo = parentContractNo;
	}

	/**
	 * @return tenderTime : return the property tenderTime.
	 */
	public Date getTenderTime() {
		return tenderTime;
	}

	/**
	 * @param tenderTime
	 *            : set the property tenderTime.
	 */
	public void setTenderTime(Date tenderTime) {
		this.tenderTime = tenderTime;
	}

	/**
	 * @return defaultPubTime : return the property defaultPubTime.
	 */
	public Date getDefaultPubTime() {
		return defaultPubTime;
	}

	/**
	 * @param defaultPubTime
	 *            : set the property defaultPubTime.
	 */
	public void setDefaultPubTime(Date defaultPubTime) {
		this.defaultPubTime = defaultPubTime;
	}

	/**
	 * @return zrMoney : return the property zrMoney.
	 */
	public BigDecimal getZrMoney() {
		return zrMoney;
	}

	/**
	 * @param zrMoney
	 *            : set the property zrMoney.
	 */
	public void setZrMoney(BigDecimal zrMoney) {
		this.zrMoney = zrMoney;
	}

	/**
	 * @return lockEndtime : return the property lockEndtime.
	 */
	public Date getLockEndtime() {
		return lockEndtime;
	}

	/**
	 * @param lockEndtime
	 *            : set the property lockEndtime.
	 */
	public void setLockEndtime(Date lockEndtime) {
		this.lockEndtime = lockEndtime;
	}

	/**
	 * @return lockEndTimeStr : return the property lockEndTimeStr.
	 */
	public String getLockEndTimeStr() {
		if (null != lockEndTime) {
			return DateUtils.format(lockEndTime, DateUtils.YMD_DASH);
		}
		return "";
	}

	/**
	 * @param lockEndTimeStr
	 *            : set the property lockEndTimeStr.
	 */
	public void setLockEndTimeStr(String lockEndTimeStr) {
		this.lockEndTimeStr = lockEndTimeStr;
	}

	/**
	 * @param mostAccountStr
	 *            : set the property mostAccountStr.
	 */
	public void setMostAccountStr(String mostAccountStr) {
		this.mostAccountStr = mostAccountStr;
	}

	/**
	 * @param planAccountStr
	 *            : set the property planAccountStr.
	 */
	public void setPlanAccountStr(String planAccountStr) {
		this.planAccountStr = planAccountStr;
	}

	/**
	 * @return tLight : return the property tLight.
	 */
	public int gettLight() {
		return tLight;
	}

	/**
	 * @param tLight
	 *            : set the property tLight.
	 */
	public void settLight(int tLight) {
		this.tLight = tLight;
	}

	/**
	 * @return capital : return the property capital.
	 */
	public BigDecimal getCapital() {
		return capital;
	}

	/**
	 * @param capital
	 *            : set the property capital.
	 */
	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}

	public BigDecimal getFixAccountUserMoney() {
		return fixAccountUserMoney;
	}

	public void setFixAccountUserMoney(BigDecimal fixAccountUserMoney) {
		this.fixAccountUserMoney = fixAccountUserMoney;
	}

	public void setBorrowCapitalStr(String borrowCapitalStr) {
		this.borrowCapitalStr = borrowCapitalStr;
	}

	public void setBorrowInterestStr(String borrowInterestStr) {
		this.borrowInterestStr = borrowInterestStr;
	}

	public void setBorrowRepayTimeStr(String borrowRepayTimeStr) {
		this.borrowRepayTimeStr = borrowRepayTimeStr;
	}
}
