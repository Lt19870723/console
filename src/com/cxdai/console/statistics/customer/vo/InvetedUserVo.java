package com.cxdai.console.statistics.customer.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.cxdai.console.util.DateUtils;

public class InvetedUserVo extends InviteDetail {

	// 推荐 人数 vo
	private BigDecimal awardmoney;// 奖励
	private int mobilePassed;// 手机是否通过
	private int emailPassed;// email是否通过
	private int realnamePassed; // 实名认证是否通过
	private int vipPassed; // vip 是否通过
	private int rechargePassed;
	private String registerTimeStr;


	// 推荐奖励 vo
	private Integer recommendSuccessUserCount;// 月度推荐成功人数
	private BigDecimal athleticsAward; // 预期竞技奖励
	private BigDecimal athleticsActualAward; // 实际竞技奖励
	private Date athleticsAwardGrantDate; // 竞技奖励发放日期
	private String athleticsAwardGrantDateStr; // 竞技奖励发放日期
	private String  athleticsRemark;// 晋级奖发放备注
	private BigDecimal directRecommendShareAward; // 直接推荐共享奖
	private BigDecimal indirectRecommendShareAward; // 间接推荐共享奖
	private Date shareAwardGrantDate;// 共享奖发放日期
	private String shareAwardGrantDateStr;// 共享奖发放日期
	private String inviteSuccessTimeStr;
	private BigDecimal getInterest;// 所得利息
	private BigDecimal  interestMoney; //共享奖预期金额
	private BigDecimal  interestActualMoney; //共享奖实际金额
	private String interestRemark;// 共享奖发放备注
	
	private String year;//年份


	// 上月利息净收益明细 vo
	private Integer borrowId; // 借款标id
	private String borrowName; // 借款标题
	private BigDecimal interest;// 尽收利息
	private BigDecimal interestManagerFree; // 利息管理费
	
	/**
	 * 累计投资金额
	 */
	private BigDecimal tenderMoney;
	
	/**
	 * 普通待收
	 */
	private BigDecimal normalColl;
	/**
	 * 直通车待收
	 */
	private BigDecimal firstColl;
	/**
	 * 定期宝待收
	 */
	private BigDecimal fixColl;	
	

	public BigDecimal getNormalColl() {
		return normalColl;
	}

	public void setNormalColl(BigDecimal normalColl) {
		this.normalColl = normalColl;
	}

	public BigDecimal getFirstColl() {
		return firstColl;
	}

	public void setFirstColl(BigDecimal firstColl) {
		this.firstColl = firstColl;
	}

	public BigDecimal getFixColl() {
		return fixColl;
	}

	public void setFixColl(BigDecimal fixColl) {
		this.fixColl = fixColl;
	}

	public BigDecimal getTenderMoney() {
		return tenderMoney;
	}

	public void setTenderMoney(BigDecimal tenderMoney) {
		this.tenderMoney = tenderMoney;
	}

	public Integer getRecommendSuccessUserCount() {
		return recommendSuccessUserCount;
	}

	public void setRecommendSuccessUserCount(Integer recommendSuccessUserCount) {
		this.recommendSuccessUserCount = recommendSuccessUserCount;
	}

	public BigDecimal getAthleticsAward() {
		return athleticsAward;
	}

	public void setAthleticsAward(BigDecimal athleticsAward) {
		this.athleticsAward = athleticsAward;
	}

	public Date getAthleticsAwardGrantDate() {
		return athleticsAwardGrantDate;
	}

	public void setAthleticsAwardGrantDate(Date athleticsAwardGrantDate) {
		this.athleticsAwardGrantDate = athleticsAwardGrantDate;
	}

	public BigDecimal getDirectRecommendShareAward() {
		return directRecommendShareAward;
	}

	public void setDirectRecommendShareAward(BigDecimal directRecommendShareAward) {
		this.directRecommendShareAward = directRecommendShareAward;
	}

	public BigDecimal getIndirectRecommendShareAward() {
		return indirectRecommendShareAward;
	}

	public void setIndirectRecommendShareAward(BigDecimal indirectRecommendShareAward) {
		this.indirectRecommendShareAward = indirectRecommendShareAward;
	}


	public Date getShareAwardGrantDate() {
		return shareAwardGrantDate;
	}

	public void setShareAwardGrantDate(Date shareAwardGrantDate) {
		this.shareAwardGrantDate = shareAwardGrantDate;
	}



	public BigDecimal getAwardmoney() {
		return awardmoney;
	}

	public void setAwardmoney(BigDecimal awardmoney) {
		this.awardmoney = awardmoney;
	}

	public int getMobilePassed() {
		return mobilePassed;
	}

	public void setMobilePassed(int mobilePassed) {
		this.mobilePassed = mobilePassed;
	}

	public int getEmailPassed() {
		return emailPassed;
	}

	public void setEmailPassed(int emailPassed) {
		this.emailPassed = emailPassed;
	}

	public int getRealnamePassed() {
		return realnamePassed;
	}

	public void setRealnamePassed(int realnamePassed) {
		this.realnamePassed = realnamePassed;
	}

	public int getVipPassed() {
		return vipPassed;
	}

	public void setVipPassed(int vipPassed) {
		this.vipPassed = vipPassed;
	}

	public int getRechargePassed() {
		return rechargePassed;
	}

	public void setRechargePassed(int rechargePassed) {
		this.rechargePassed = rechargePassed;
	}

	public String getRegisterTimeStr() {
		Date registerTime = getRegisterTime();
		if (registerTime != null) {
			return DateUtils.format(registerTime, DateUtils.YMD_DASH);
		}
		return registerTimeStr;
	}

	public void setRegisterTimeStr(String registerTimeStr) {
		this.registerTimeStr = registerTimeStr;
	}

	public String getInviteSuccessTimeStr() {

		Date inviteSuccessTime = getInviteSuccessTime();
		if (inviteSuccessTime != null) {
			return DateUtils.format(inviteSuccessTime, DateUtils.YMD_DASH);
		}

		return inviteSuccessTimeStr;
	}

	public String getAthleticsAwardGrantDateStr() {
		Date athleticsAwardGrant = getAthleticsAwardGrantDate();
		if (athleticsAwardGrant != null) {
			return DateUtils.format(athleticsAwardGrant, DateUtils.YMD_DASH);
		}
		return athleticsAwardGrantDateStr;
	}

	public void setAthleticsAwardGrantDateStr(String athleticsAwardGrantDateStr) {
		this.athleticsAwardGrantDateStr = athleticsAwardGrantDateStr;
	}

	public void setInviteSuccessTimeStr(String inviteSuccessTimeStr) {
		this.inviteSuccessTimeStr = inviteSuccessTimeStr;
	}

	public String getBorrowName() {
		return borrowName;
	}

	public void setBorrowName(String borrowName) {
		this.borrowName = borrowName;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public BigDecimal getInterestManagerFree() {
		return interestManagerFree;
	}

	public void setInterestManagerFree(BigDecimal interestManagerFree) {
		this.interestManagerFree = interestManagerFree;
	}

	public Integer getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}

	public BigDecimal getGetInterest() {
		return getInterest;
	}

	public void setGetInterest(BigDecimal getInterest) {
		this.getInterest = getInterest;
	}

	public BigDecimal getAthleticsActualAward() {
		return athleticsActualAward;
	}

	public void setAthleticsActualAward(BigDecimal athleticsActualAward) {
		this.athleticsActualAward = athleticsActualAward;
	}

	public String getAthleticsRemark() {
		return athleticsRemark;
	}

	public void setAthleticsRemark(String athleticsRemark) {
		this.athleticsRemark = athleticsRemark;
	}

	public BigDecimal getInterestMoney() {
		return interestMoney;
	}

	public void setInterestMoney(BigDecimal interestMoney) {
		this.interestMoney = interestMoney;
	}

	public BigDecimal getInterestActualMoney() {
		return interestActualMoney;
	}

	public void setInterestActualMoney(BigDecimal interestActualMoney) {
		this.interestActualMoney = interestActualMoney;
	}

	public String getInterestRemark() {
		return interestRemark;
	}

	public void setInterestRemark(String interestRemark) {
		this.interestRemark = interestRemark;
	}

	public String getShareAwardGrantDateStr() {
		Date shareAwardGrant = getShareAwardGrantDate();
		if (shareAwardGrant != null) {
			return DateUtils.format(shareAwardGrant, DateUtils.YMD_DASH);
		}
		return shareAwardGrantDateStr;
	}

	public void setShareAwardGrantDateStr(String shareAwardGrantDateStr) {
		this.shareAwardGrantDateStr = shareAwardGrantDateStr;
	}

		/**
		 * @return the year
		 */
		public String getYear() {
			return year;
		}

		/**
		 * @param year the year to set
		 */
		public void setYear(String year) {
			this.year = year;
		}

}
