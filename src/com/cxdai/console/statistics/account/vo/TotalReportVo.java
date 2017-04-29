package com.cxdai.console.statistics.account.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.cxdai.console.util.MoneyUtil;

/**
 * @author hujianpan 运营报表vo
 */
public class TotalReportVo implements Serializable {
	/**
	 * @fields serialVersionUID
	 */
	private static final long serialVersionUID = -3240824751874092465L;

	// 注册人数：
	private Integer memberCount;
	private String memberCountStr;

	// 实名认证人数：
	private Integer realNameApproCount;
	private String realNameApproCountStr;

	// VIP认证人数：
	private Integer vipApproApproCount;
	private String vipApproApproCountStr;

	// 新增投资人数：
	private Integer addInvestPersonsTotal;
	private String addInvestPersonsTotalStr;

	// 累计投资人数：
	private Integer investPersonsTotal;
	private String investPersonsTotalStr;

	// 流失投资者人数：
	private Integer losePersonsTotal;
	private String losePersonsTotalStr;

	// 现有投资者人数：
	private Integer nowInvestPersonsTotal;
	private String nowInvestPersonsTotalStr;

	// 观望投资者人数：
	private Integer observePersonsTotal;
	private String observePersonsTotalStr;

	/********************** 交易数据统计 */
	// // 充值金额： （不包括官方账号）
	private BigDecimal rechangeTotalMoney;

	private String rechangeTotalMoneyStr;
	// 提现金额(已打款)：
	private BigDecimal cashTotalMoney;
	private String cashTotalMoneyStr;
	// 提现金额(已申请)：
	private BigDecimal cashTotalApplyMoney;
	private String cashTotalApplyMoneyStr;
	// 投资人充值总额：
	private BigDecimal rechangeTotalByTZ;
	private String rechangeTotalByTZStr;
	// 借款人充值总额：
	private BigDecimal rechangeTotalByJK;
	private String rechangeTotalByJKStr;
	// 净充值金额：
	private BigDecimal netRechargeTotal;
	private String netRechargeTotalStr;
	// 用户资金总额：
	private BigDecimal useMoneyTotal;
	private String useMoneyTotalStr;
	// 用户优先可用资金总额：
	private BigDecimal firstBorrowUseMoneyTotal;
	private String firstBorrowUseMoneyTotalStr;
	// 抵押标成交金额：
	private BigDecimal dyAccountMoney;
	private String dyAccountMoneyStr;
	// 净值标成交金额：
	private BigDecimal jzAccountMoney;
	private String jzAccountMoneyStr;
	// 抵押标时间加权成交金额：
	private BigDecimal dybTimeTotal;
	private String dybTimeTotalStr;
	// 净值标 时间加权交易金额：
	private BigDecimal jzbTimeTotal;
	private String jzbTimeTotalStr;
	// 秒标成交金额：
	private BigDecimal mbAccountMoney;
	private String mbAccountMoneyStr;
	// 推荐标成交金额：
	private BigDecimal tjbAccountMoney;
	private String tjbAccountMoneyStr;
	// 已支付抵押标利息总额：（即时）
	private BigDecimal dybHavePayInterstTotal;
	private String dybHavePayInterstTotalStr;
	// 待支付抵押标利息总额：
	private BigDecimal dybUnPayInterstTotal;
	private String dybUnPayInterstTotalStr;
	// 上线活动奖励：（即时）
	private BigDecimal activityAwardTotal;
	private String activityAwardTotalStr;

	// 债转 抵押标成交金额
	private BigDecimal dyTransferAccountMoney;
	private String dyTransferAccountMoneyStr;

	// 债转净值标成交金额：
	private BigDecimal jzTransferAccountMoney;
	private String jzTransferAccountMoneyStr;

	// 债转秒标成交金额：
	private BigDecimal mbTransferAccountMoney;
	private String mbTransferAccountMoneyStr;
	// 债转推荐标成交金额：
	private BigDecimal tjbTransferAccountMoney;
	private String tjbTransferAccountMoneyStr;

	// 担保标成交金额：
	private BigDecimal dbAccountMoney;
	private String dbAccountMoneyStr;

	// 债转担保标成交金额：
	private BigDecimal dbTransferAccountMoney;
	private String dbTransferAccountMoneyStr;

	/********************** 网站支出数据统计 */

	// 支出总和：
	private BigDecimal payTotal;
	private String payTotalStr;
	// 实名认证奖励：
	private BigDecimal realnamePassAwardTotal;
	private String realnamePassAwardTotalStr;
	// 推广注册奖励： （充值1000，推荐人和被推荐人各奖励10元）
	private BigDecimal generalizeRegisterTotal;
	private String generalizeRegisterTotalStr;
	// 提现补帖奖励：（待收超过5000，月初奖励10元）
	private BigDecimal monthFirstAwardTotal;
	private String monthFirstAwardTotalStr;
	// 论坛活动奖励：
	private BigDecimal forumActivityAwardTotal;
	private String forumActivityAwardTotalStr;
	// 线下充值奖励：
	private BigDecimal offLineRechargeAwardTotal;
	private String offLineRechargeAwardTotalStr;
	// 网银在线充值费用：
	private BigDecimal chinaBankFeeTotal;
	private String chinaBankFeeTotalStr;
	// 盛付通充值费用：
	private BigDecimal shengpayFeeTotal;
	private String shengpayFeeTotalStr;
	// 国付宝充值费用：
	private BigDecimal guopayFeeTotal;
	private String guopayFeeTotalStr;
	// 新浪支付充值费用
	private BigDecimal sinaFeeTotal;
	private String sinaFeeTotalStr;

	// 连连支付充值费用
	private BigDecimal llFeeTotal;
	private String llFeeTotalStr;

	// 富友支付充值费用
	private BigDecimal fuiouFeeTotal;

    // 浙商存管充值费用
    private BigDecimal czbankCustodyTotal;

	// 充值费用总和：（3家第三方充值费用总和加上线下充值奖励）
	private BigDecimal rechargeTotal;
	private String rechargeTotalStr;
	/********************** 网站收入数据统计 */
	// ：收入总和
	private BigDecimal incomeTotal;
	private String incomeTotalStr;
	// VIP费用收入：
	private BigDecimal vipIncomeTotal;
	private String vipIncomeTotalStr;
	// 提现手续费：
	private BigDecimal takeCashFeeTotal;
	private String takeCashFeeTotalStr;
	// 利息管理费：
	private BigDecimal interestFeeTotal;
	private String interestFeeTotalStr;
	// 转让管理费
	private BigDecimal transferManagerFree;
	private String transferManagerFreeStr;
	// 直通车转让管理费
	private BigDecimal firstTransferManagerFree;
	private String firstTransferManagerFreeStr;
	
	// 净值标借款管理费：
	private BigDecimal netBorrowFeeTotal;
	private String netBorrowFeeTotalStr;
	// 净收益：（收入总和—支出总和）
	private BigDecimal netEarningTotal;
	private String netEarningTotalStr;

	// 网银在线充值手续费收入
	private BigDecimal moneyLineReseive;
	private String moneyLineReseiveStr;

	// 新浪支付充值手续费收入
	private BigDecimal sinaLineReseive;
	private String sinaLineReseiveStr;

	// 连连支付充值手续费收入
	private BigDecimal llFeeReseive;
	private String llFeeReseiveStr;

	// 受限转可提手续费
	private BigDecimal changeDrawMoneyFee;

	// 净值标逾期罚息
	private BigDecimal netBorrowOverdueFee;

	// 活期宝利息总和：
	private BigDecimal curInterestTotal;
	private String curInterestTotalStr;
    //红包支出
	private BigDecimal redOutTotal;
    //共享奖支出
	private BigDecimal gxjOutTotal;
	// **************************************************************

	public BigDecimal getGxjOutTotal() {
		return gxjOutTotal;
	}

	public void setGxjOutTotal(BigDecimal gxjOutTotal) {
		this.gxjOutTotal = gxjOutTotal;
	}

	public Integer getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(Integer memberCount) {
		this.memberCount = memberCount;
	}

	public Integer getRealNameApproCount() {
		return realNameApproCount;
	}

	public void setRealNameApproCount(Integer realNameApproCount) {
		this.realNameApproCount = realNameApproCount;
	}

	public Integer getVipApproApproCount() {
		return vipApproApproCount;
	}

	public void setVipApproApproCount(Integer vipApproApproCount) {
		this.vipApproApproCount = vipApproApproCount;
	}

	public Integer getAddInvestPersonsTotal() {
		return addInvestPersonsTotal;
	}

	public void setAddInvestPersonsTotal(Integer addInvestPersonsTotal) {
		this.addInvestPersonsTotal = addInvestPersonsTotal;
	}

	public Integer getInvestPersonsTotal() {
		return investPersonsTotal;
	}

	public void setInvestPersonsTotal(Integer investPersonsTotal) {
		this.investPersonsTotal = investPersonsTotal;
	}

	public Integer getLosePersonsTotal() {
		return losePersonsTotal;
	}

	public void setLosePersonsTotal(Integer losePersonsTotal) {
		this.losePersonsTotal = losePersonsTotal;
	}

	public Integer getNowInvestPersonsTotal() {
		return nowInvestPersonsTotal;
	}

	public void setNowInvestPersonsTotal(Integer nowInvestPersonsTotal) {
		this.nowInvestPersonsTotal = nowInvestPersonsTotal;
	}

	public Integer getObservePersonsTotal() {
		return observePersonsTotal;
	}

	public void setObservePersonsTotal(Integer observePersonsTotal) {
		this.observePersonsTotal = observePersonsTotal;
	}

	public String getMemberCountStr() {
		/*
		 * if (null != memberCount) { memberCountStr = MoneyUtil.roundMoney(new BigDecimal(memberCount)); } return memberCountStr;
		 */
		return memberCount + "";
	}

	public String getRealNameApproCountStr() {
		/*
		 * if (null != realNameApproCount) { realNameApproCountStr = MoneyUtil.roundMoney(new BigDecimal(realNameApproCount)); } return realNameApproCountStr + "";
		 */
		return realNameApproCount + "";
	}

	public String getVipApproApproCountStr() {
		/*
		 * if (null != vipApproApproCount) { vipApproApproCountStr = MoneyUtil.roundMoney(new BigDecimal(vipApproApproCount)); } return vipApproApproCountStr + "";
		 */
		return vipApproApproCount + "";
	}

	public String getAddInvestPersonsTotalStr() {
		/*
		 * if (null != addInvestPersonsTotal) { addInvestPersonsTotalStr = MoneyUtil.roundMoney(new BigDecimal(addInvestPersonsTotal)); } return addInvestPersonsTotalStr + "";
		 */
		return addInvestPersonsTotal + "";
	}

	public String getInvestPersonsTotalStr() {
		/*
		 * if (null != investPersonsTotal) { investPersonsTotalStr = MoneyUtil.roundMoney(new BigDecimal(investPersonsTotal)); } return investPersonsTotalStr + "";
		 */

		return investPersonsTotal + "";
	}

	public String getLosePersonsTotalStr() {
		/*
		 * if (null != losePersonsTotal) { losePersonsTotalStr = MoneyUtil.roundMoney(new BigDecimal(losePersonsTotal)); } return losePersonsTotalStr + "";
		 */
		return losePersonsTotal + "";
	}

	public String getNowInvestPersonsTotalStr() {
		/*
		 * if (null != nowInvestPersonsTotal) { nowInvestPersonsTotalStr = MoneyUtil.roundMoney(new BigDecimal(nowInvestPersonsTotal)); } return nowInvestPersonsTotalStr + "";
		 */
		return nowInvestPersonsTotal + "";
	}

	public String getObservePersonsTotalStr() {
		/*
		 * if (null != observePersonsTotal) { observePersonsTotalStr = MoneyUtil.roundMoney(new BigDecimal(observePersonsTotal)); } return observePersonsTotalStr + "";
		 */

		return observePersonsTotal + "";
	}

	public BigDecimal getRechangeTotalMoney() {

		return rechangeTotalMoney;
	}

	public void setRechangeTotalMoney(BigDecimal rechangeTotalMoney) {
		this.rechangeTotalMoney = rechangeTotalMoney;
	}

	public BigDecimal getCashTotalMoney() {
		return cashTotalMoney;
	}

	public void setCashTotalMoney(BigDecimal cashTotalMoney) {
		this.cashTotalMoney = cashTotalMoney;
	}

	public BigDecimal getRechangeTotalByTZ() {
		return rechangeTotalByTZ;
	}

	public void setRechangeTotalByTZ(BigDecimal rechangeTotalByTZ) {
		this.rechangeTotalByTZ = rechangeTotalByTZ;
	}

	public BigDecimal getRechangeTotalByJK() {
		return rechangeTotalByJK;
	}

	public void setRechangeTotalByJK(BigDecimal rechangeTotalByJK) {
		this.rechangeTotalByJK = rechangeTotalByJK;
	}

	public BigDecimal getNetRechargeTotal() {
		return netRechargeTotal;
	}

	public void setNetRechargeTotal(BigDecimal netRechargeTotal) {
		this.netRechargeTotal = netRechargeTotal;
	}

	public BigDecimal getUseMoneyTotal() {
		return useMoneyTotal;
	}

	public void setUseMoneyTotal(BigDecimal useMoneyTotal) {
		this.useMoneyTotal = useMoneyTotal;
	}

	public BigDecimal getFirstBorrowUseMoneyTotal() {
		return firstBorrowUseMoneyTotal;
	}

	public void setFirstBorrowUseMoneyTotal(BigDecimal firstBorrowUseMoneyTotal) {
		this.firstBorrowUseMoneyTotal = firstBorrowUseMoneyTotal;
	}

	public BigDecimal getDyAccountMoney() {
		return dyAccountMoney;
	}

	public void setDyAccountMoney(BigDecimal dyAccountMoney) {
		this.dyAccountMoney = dyAccountMoney;
	}

	public BigDecimal getJzAccountMoney() {
		return jzAccountMoney;
	}

	public void setJzAccountMoney(BigDecimal jzAccountMoney) {
		this.jzAccountMoney = jzAccountMoney;
	}

	public BigDecimal getDybTimeTotal() {
		return dybTimeTotal;
	}

	public void setDybTimeTotal(BigDecimal dybTimeTotal) {
		this.dybTimeTotal = dybTimeTotal;
	}

	public BigDecimal getJzbTimeTotal() {
		return jzbTimeTotal;
	}

	public void setJzbTimeTotal(BigDecimal jzbTimeTotal) {
		this.jzbTimeTotal = jzbTimeTotal;
	}

	public BigDecimal getMbAccountMoney() {
		return mbAccountMoney;
	}

	public void setMbAccountMoney(BigDecimal mbAccountMoney) {
		this.mbAccountMoney = mbAccountMoney;
	}

	public BigDecimal getTjbAccountMoney() {
		return tjbAccountMoney;
	}

	public void setTjbAccountMoney(BigDecimal tjbAccountMoney) {
		this.tjbAccountMoney = tjbAccountMoney;
	}

	public BigDecimal getDybHavePayInterstTotal() {
		return dybHavePayInterstTotal;
	}

	public void setDybHavePayInterstTotal(BigDecimal dybHavePayInterstTotal) {
		this.dybHavePayInterstTotal = dybHavePayInterstTotal;
	}

	public BigDecimal getDybUnPayInterstTotal() {
		return dybUnPayInterstTotal;
	}

	public void setDybUnPayInterstTotal(BigDecimal dybUnPayInterstTotal) {
		this.dybUnPayInterstTotal = dybUnPayInterstTotal;
	}

	public BigDecimal getActivityAwardTotal() {
		return activityAwardTotal;
	}

	public void setActivityAwardTotal(BigDecimal activityAwardTotal) {
		this.activityAwardTotal = activityAwardTotal;
	}

	public void setMemberCountStr(String memberCountStr) {
		this.memberCountStr = memberCountStr;
	}

	public void setRealNameApproCountStr(String realNameApproCountStr) {
		this.realNameApproCountStr = realNameApproCountStr;
	}

	public void setVipApproApproCountStr(String vipApproApproCountStr) {
		this.vipApproApproCountStr = vipApproApproCountStr;
	}

	public void setAddInvestPersonsTotalStr(String addInvestPersonsTotalStr) {
		this.addInvestPersonsTotalStr = addInvestPersonsTotalStr;
	}

	public void setInvestPersonsTotalStr(String investPersonsTotalStr) {
		this.investPersonsTotalStr = investPersonsTotalStr;
	}

	public void setLosePersonsTotalStr(String losePersonsTotalStr) {
		this.losePersonsTotalStr = losePersonsTotalStr;
	}

	public void setNowInvestPersonsTotalStr(String nowInvestPersonsTotalStr) {
		this.nowInvestPersonsTotalStr = nowInvestPersonsTotalStr;
	}

	public void setObservePersonsTotalStr(String observePersonsTotalStr) {
		this.observePersonsTotalStr = observePersonsTotalStr;
	}

	public BigDecimal getPayTotal() {
		return payTotal;
	}

	public void setPayTotal(BigDecimal payTotal) {
		this.payTotal = payTotal;
	}

	public BigDecimal getRealnamePassAwardTotal() {
		return realnamePassAwardTotal;
	}

	public void setRealnamePassAwardTotal(BigDecimal realnamePassAwardTotal) {
		this.realnamePassAwardTotal = realnamePassAwardTotal;
	}

	public BigDecimal getGeneralizeRegisterTotal() {
		return generalizeRegisterTotal;
	}

	public void setGeneralizeRegisterTotal(BigDecimal generalizeRegisterTotal) {
		this.generalizeRegisterTotal = generalizeRegisterTotal;
	}

	public BigDecimal getMonthFirstAwardTotal() {
		return monthFirstAwardTotal;
	}

	public void setMonthFirstAwardTotal(BigDecimal monthFirstAwardTotal) {
		this.monthFirstAwardTotal = monthFirstAwardTotal;
	}

	public BigDecimal getForumActivityAwardTotal() {
		return forumActivityAwardTotal;
	}

	public void setForumActivityAwardTotal(BigDecimal forumActivityAwardTotal) {
		this.forumActivityAwardTotal = forumActivityAwardTotal;
	}

	public BigDecimal getOffLineRechargeAwardTotal() {
		return offLineRechargeAwardTotal;
	}

	public void setOffLineRechargeAwardTotal(BigDecimal offLineRechargeAwardTotal) {
		this.offLineRechargeAwardTotal = offLineRechargeAwardTotal;
	}

	public BigDecimal getChinaBankFeeTotal() {
		return chinaBankFeeTotal;
	}

	public void setChinaBankFeeTotal(BigDecimal chinaBankFeeTotal) {
		this.chinaBankFeeTotal = chinaBankFeeTotal;
	}

	public BigDecimal getShengpayFeeTotal() {
		return shengpayFeeTotal;
	}

	public void setShengpayFeeTotal(BigDecimal shengpayFeeTotal) {
		this.shengpayFeeTotal = shengpayFeeTotal;
	}

	public BigDecimal getGuopayFeeTotal() {
		return guopayFeeTotal;
	}

	public void setGuopayFeeTotal(BigDecimal guopayFeeTotal) {
		this.guopayFeeTotal = guopayFeeTotal;
	}

	public BigDecimal getSinaFeeTotal() {
		return sinaFeeTotal;
	}

	public void setSinaFeeTotal(BigDecimal sinaFeeTotal) {
		this.sinaFeeTotal = sinaFeeTotal;
	}

	public BigDecimal getRechargeTotal() {
		return rechargeTotal;
	}

	public void setRechargeTotal(BigDecimal rechargeTotal) {
		this.rechargeTotal = rechargeTotal;
	}

	public BigDecimal getIncomeTotal() {
		return incomeTotal;
	}

	public void setIncomeTotal(BigDecimal incomeTotal) {
		this.incomeTotal = incomeTotal;
	}

	public BigDecimal getVipIncomeTotal() {
		return vipIncomeTotal;
	}

	public void setVipIncomeTotal(BigDecimal vipIncomeTotal) {
		this.vipIncomeTotal = vipIncomeTotal;
	}

	public BigDecimal getTakeCashFeeTotal() {
		return takeCashFeeTotal;
	}

	public void setTakeCashFeeTotal(BigDecimal takeCashFeeTotal) {
		this.takeCashFeeTotal = takeCashFeeTotal;
	}

	public BigDecimal getInterestFeeTotal() {
		return interestFeeTotal;
	}

	public void setInterestFeeTotal(BigDecimal interestFeeTotal) {
		this.interestFeeTotal = interestFeeTotal;
	}

	public BigDecimal getNetBorrowFeeTotal() {
		return netBorrowFeeTotal;
	}

	public void setNetBorrowFeeTotal(BigDecimal netBorrowFeeTotal) {
		this.netBorrowFeeTotal = netBorrowFeeTotal;
	}

	public BigDecimal getNetEarningTotal() {
		return netEarningTotal;
	}

	public void setNetEarningTotal(BigDecimal netEarningTotal) {
		this.netEarningTotal = netEarningTotal;
	}

	/***********************************************************/
	public String getRechangeTotalMoneyStr() {
		if (null != rechangeTotalMoney) {
			rechangeTotalMoneyStr = "¥ " + MoneyUtil.roundMoney(rechangeTotalMoney);
		}

		return rechangeTotalMoneyStr;
	}

	public String getCashTotalMoneyStr() {
		if (null != cashTotalMoney) {
			cashTotalMoneyStr = "¥ " + MoneyUtil.roundMoney(cashTotalMoney);
		}

		return cashTotalMoneyStr;
	}

	public String getRechangeTotalByTZStr() {
		if (null != rechangeTotalByTZ) {
			rechangeTotalByTZStr = "¥ " + MoneyUtil.roundMoney(rechangeTotalByTZ);
		}
		return rechangeTotalByTZStr;
	}

	public String getRechangeTotalByJKStr() {
		if (null != rechangeTotalByJK) {
			rechangeTotalByJKStr = "¥ " + MoneyUtil.roundMoney(rechangeTotalByJK);
		}
		return rechangeTotalByJKStr;
	}

	public String getNetRechargeTotalStr() {
		if (null != netRechargeTotal) {
			netRechargeTotalStr = "¥ " + MoneyUtil.roundMoney(netRechargeTotal);
		}
		return netRechargeTotalStr;
	}

	public String getUseMoneyTotalStr() {
		if (null != useMoneyTotal) {
			useMoneyTotalStr = "¥ " + MoneyUtil.roundMoney(useMoneyTotal);
		}
		return useMoneyTotalStr;
	}

	public String getFirstBorrowUseMoneyTotalStr() {
		if (null != firstBorrowUseMoneyTotal) {
			firstBorrowUseMoneyTotalStr = "¥ " + MoneyUtil.roundMoney(firstBorrowUseMoneyTotal);
		}
		return firstBorrowUseMoneyTotalStr;
	}

	public String getDyAccountMoneyStr() {
		if (null != dyAccountMoney) {
			dyAccountMoneyStr = "¥ " + MoneyUtil.roundMoney(dyAccountMoney);
		}
		return dyAccountMoneyStr;
	}

	public String getJzAccountMoneyStr() {
		if (null != jzAccountMoney) {
			jzAccountMoneyStr = "¥ " + MoneyUtil.roundMoney(jzAccountMoney);
		}
		return jzAccountMoneyStr;
	}

	public String getDybTimeTotalStr() {
		if (null != dybTimeTotal) {
			dybTimeTotalStr = "¥ " + MoneyUtil.roundMoney(dybTimeTotal);
		}
		return dybTimeTotalStr;
	}

	public String getJzbTimeTotalStr() {
		if (null != jzbTimeTotal) {
			jzbTimeTotalStr = "¥ " + MoneyUtil.roundMoney(jzbTimeTotal);
		}
		return jzbTimeTotalStr;
	}

	public String getMbAccountMoneyStr() {
		if (null != mbAccountMoney) {
			mbAccountMoneyStr = "¥ " + MoneyUtil.roundMoney(mbAccountMoney);
		}
		return mbAccountMoneyStr;
	}

	public String getTjbAccountMoneyStr() {
		if (null != tjbAccountMoney) {
			tjbAccountMoneyStr = "¥ " + MoneyUtil.roundMoney(tjbAccountMoney);
		}
		return tjbAccountMoneyStr;
	}

	public String getDybHavePayInterstTotalStr() {
		if (null != dybHavePayInterstTotal) {
			dybHavePayInterstTotalStr = "¥ " + MoneyUtil.roundMoney(dybHavePayInterstTotal);
		}

		return dybHavePayInterstTotalStr;
	}

	public String getDybUnPayInterstTotalStr() {
		if (null != dybUnPayInterstTotal) {
			dybUnPayInterstTotalStr = "¥ " + MoneyUtil.roundMoney(dybUnPayInterstTotal);
		}
		return dybUnPayInterstTotalStr;
	}

	public String getActivityAwardTotalStr() {
		if (null != activityAwardTotal) {
			activityAwardTotalStr = "¥ " + MoneyUtil.roundMoney(activityAwardTotal);
		}
		return activityAwardTotalStr;
	}

	public String getPayTotalStr() {
		if (null != payTotal) {
			payTotalStr = "¥ " + MoneyUtil.roundMoney(payTotal);
		}
		return payTotalStr;
	}

	public String getRealnamePassAwardTotalStr() {
		if (null != realnamePassAwardTotal) {
			realnamePassAwardTotalStr = "¥ " + MoneyUtil.roundMoney(realnamePassAwardTotal);
		}
		return realnamePassAwardTotalStr;
	}

	public String getGeneralizeRegisterTotalStr() {
		if (null != generalizeRegisterTotal) {
			generalizeRegisterTotalStr = "¥ " + MoneyUtil.roundMoney(generalizeRegisterTotal);
		}
		return generalizeRegisterTotalStr;
	}

	public String getMonthFirstAwardTotalStr() {

		if (null != monthFirstAwardTotal) {
			monthFirstAwardTotalStr = "¥ " + MoneyUtil.roundMoney(monthFirstAwardTotal);
		}
		return monthFirstAwardTotalStr;
	}

	public String getForumActivityAwardTotalStr() {
		if (null != forumActivityAwardTotal) {
			forumActivityAwardTotalStr = "¥ " + MoneyUtil.roundMoney(forumActivityAwardTotal);
		}
		return forumActivityAwardTotalStr;
	}

	public String getOffLineRechargeAwardTotalStr() {
		if (null != offLineRechargeAwardTotal) {
			offLineRechargeAwardTotalStr = "¥ " + MoneyUtil.roundMoney(offLineRechargeAwardTotal);
		}
		return offLineRechargeAwardTotalStr;
	}

	public String getChinaBankFeeTotalStr() {
		if (null != chinaBankFeeTotal) {
			chinaBankFeeTotalStr = "¥ " + MoneyUtil.roundMoney(chinaBankFeeTotal);
		}
		return chinaBankFeeTotalStr;
	}

	public String getShengpayFeeTotalStr() {
		if (null != shengpayFeeTotal) {
			shengpayFeeTotalStr = "¥ " + MoneyUtil.roundMoney(shengpayFeeTotal);
		}
		return shengpayFeeTotalStr;
	}

	public String getGuopayFeeTotalStr() {
		if (null != guopayFeeTotal) {
			guopayFeeTotalStr = "¥ " + MoneyUtil.roundMoney(guopayFeeTotal);
		}
		return guopayFeeTotalStr;
	}

	public String getSinaFeeTotalStr() {
		if (null != sinaFeeTotal) {
			sinaFeeTotalStr = "¥ " + MoneyUtil.roundMoney(sinaFeeTotal);
		}
		return sinaFeeTotalStr;
	}

	public BigDecimal getLlFeeTotal() {
		return llFeeTotal;
	}

	public void setLlFeeTotal(BigDecimal llFeeTotal) {
		this.llFeeTotal = llFeeTotal;
	}

	public String getLlFeeTotalStr() {
		if (null != llFeeTotal) {
			llFeeTotalStr = "¥ " + MoneyUtil.roundMoney(llFeeTotal);
		}
		return llFeeTotalStr;
	}

	public String getRechargeTotalStr() {
		if (null != rechargeTotal) {
			rechargeTotalStr = "¥ " + MoneyUtil.roundMoney(rechargeTotal);
		}
		return rechargeTotalStr;
	}

	public String getIncomeTotalStr() {
		if (null != incomeTotal) {
			incomeTotalStr = "¥ " + MoneyUtil.roundMoney(incomeTotal);
		}
		return incomeTotalStr;
	}

	public String getVipIncomeTotalStr() {
		if (null != vipIncomeTotal) {
			vipIncomeTotalStr = "¥ " + MoneyUtil.roundMoney(vipIncomeTotal);
		}
		return vipIncomeTotalStr;
	}

	public String getTakeCashFeeTotalStr() {
		if (null != takeCashFeeTotal) {
			takeCashFeeTotalStr = "¥ " + MoneyUtil.roundMoney(takeCashFeeTotal);
		}
		return takeCashFeeTotalStr;
	}

	public String getInterestFeeTotalStr() {
		if (null != interestFeeTotal) {
			interestFeeTotalStr = "¥ " + MoneyUtil.roundMoney(interestFeeTotal);
		}
		return interestFeeTotalStr;
	}

	public String getNetBorrowFeeTotalStr() {
		if (null != netBorrowFeeTotal) {
			netBorrowFeeTotalStr = "¥ " + MoneyUtil.roundMoney(netBorrowFeeTotal);
		}
		return netBorrowFeeTotalStr;
	}

	public String getNetEarningTotalStr() {
		if (null != netEarningTotal) {
			netEarningTotalStr = "¥ " + MoneyUtil.roundMoney(netEarningTotal);
		}
		return netEarningTotalStr;
	}

	public BigDecimal getMoneyLineReseive() {
		return moneyLineReseive;
	}

	public void setMoneyLineReseive(BigDecimal moneyLineReseive) {
		this.moneyLineReseive = moneyLineReseive;
	}

	public BigDecimal getSinaLineReseive() {
		return sinaLineReseive;
	}

	public void setSinaLineReseive(BigDecimal sinaLineReseive) {
		this.sinaLineReseive = sinaLineReseive;
	}

	public String getMoneyLineReseiveStr() {
		if (null != moneyLineReseive) {
			moneyLineReseiveStr = "¥ " + MoneyUtil.roundMoney(moneyLineReseive);
		}

		return moneyLineReseiveStr;
	}

	public String getSinaLineReseiveStr() {
		if (null != sinaLineReseive) {
			sinaLineReseiveStr = "¥ " + MoneyUtil.roundMoney(sinaLineReseive);
		}
		return sinaLineReseiveStr;
	}

	public BigDecimal getDyTransferAccountMoney() {
		return dyTransferAccountMoney;
	}

	public void setDyTransferAccountMoney(BigDecimal dyTransferAccountMoney) {
		this.dyTransferAccountMoney = dyTransferAccountMoney;
	}

	public String getDyTransferAccountMoneyStr() {
		if (null != dyTransferAccountMoney) {
			dyTransferAccountMoneyStr = "¥ " + MoneyUtil.roundMoney(dyTransferAccountMoney);
		}
		return dyTransferAccountMoneyStr;
	}

	public void setDyTransferAccountMoneyStr(String dyTransferAccountMoneyStr) {
		this.dyTransferAccountMoneyStr = dyTransferAccountMoneyStr;
	}

	public BigDecimal getJzTransferAccountMoney() {
		return jzTransferAccountMoney;
	}

	public void setJzTransferAccountMoney(BigDecimal jzTransferAccountMoney) {
		this.jzTransferAccountMoney = jzTransferAccountMoney;
	}

	public String getJzTransferAccountMoneyStr() {
		if (null != jzTransferAccountMoney) {
			jzTransferAccountMoneyStr = "¥ " + MoneyUtil.roundMoney(jzTransferAccountMoney);
		}
		return jzTransferAccountMoneyStr;
	}

	public void setJzTransferAccountMoneyStr(String jzTransferAccountMoneyStr) {
		this.jzTransferAccountMoneyStr = jzTransferAccountMoneyStr;
	}

	public BigDecimal getMbTransferAccountMoney() {
		return mbTransferAccountMoney;
	}

	public void setMbTransferAccountMoney(BigDecimal mbTransferAccountMoney) {
		this.mbTransferAccountMoney = mbTransferAccountMoney;
	}

	public String getMbTransferAccountMoneyStr() {
		if (null != mbTransferAccountMoney) {
			mbTransferAccountMoneyStr = "¥ " + MoneyUtil.roundMoney(mbTransferAccountMoney);
		}
		return mbTransferAccountMoneyStr;
	}

	public void setMbTransferAccountMoneyStr(String mbTransferAccountMoneyStr) {
		this.mbTransferAccountMoneyStr = mbTransferAccountMoneyStr;
	}

	public BigDecimal getTjbTransferAccountMoney() {
		return tjbTransferAccountMoney;
	}

	public void setTjbTransferAccountMoney(BigDecimal tjbTransferAccountMoney) {
		this.tjbTransferAccountMoney = tjbTransferAccountMoney;
	}

	public String getTjbTransferAccountMoneyStr() {
		if (null != tjbTransferAccountMoney) {
			tjbTransferAccountMoneyStr = "¥ " + MoneyUtil.roundMoney(tjbTransferAccountMoney);
		}
		return tjbTransferAccountMoneyStr;
	}

	public void setTjbTransferAccountMoneyStr(String tjbTransferAccountMoneyStr) {
		this.tjbTransferAccountMoneyStr = tjbTransferAccountMoneyStr;
	}

	public BigDecimal getTransferManagerFree() {
		return transferManagerFree;
	}

	public void setTransferManagerFree(BigDecimal transferManagerFree) {
		this.transferManagerFree = transferManagerFree;
	}

	public String getTransferManagerFreeStr() {
		if (null != transferManagerFree) {
			transferManagerFreeStr = "¥ " + MoneyUtil.roundMoney(transferManagerFree);
		}
		return transferManagerFreeStr;
	}

	public void setTransferManagerFreeStr(String transferManagerFreeStr) {
		this.transferManagerFreeStr = transferManagerFreeStr;
	}

	public BigDecimal getFirstTransferManagerFree() {		
		return firstTransferManagerFree;
	}

	public void setFirstTransferManagerFree(BigDecimal firstTransferManagerFree) {
		this.firstTransferManagerFree = firstTransferManagerFree;
	}

	public String getFirstTransferManagerFreeStr() {
		if (null != firstTransferManagerFree) {
			firstTransferManagerFreeStr = "¥ " + MoneyUtil.roundMoney(firstTransferManagerFree);
		}
		return firstTransferManagerFreeStr;
	}

	public void setFirstTransferManagerFreeStr(String firstTransferManagerFreeStr) {
		this.firstTransferManagerFreeStr = firstTransferManagerFreeStr;
	}

	public BigDecimal getLlFeeReseive() {
		return llFeeReseive;
	}

	public void setLlFeeReseive(BigDecimal llFeeReseive) {
		this.llFeeReseive = llFeeReseive;
	}

	public String getLlFeeReseiveStr() {
		if (null != llFeeReseive) {
			llFeeReseiveStr = "¥ " + MoneyUtil.roundMoney(llFeeReseive);
		}
		return llFeeReseiveStr;
	}

	public void setLlFeeReseiveStr(String llFeeReseiveStr) {
		this.llFeeReseiveStr = llFeeReseiveStr;
	}

	public void setJzAccountMoneyStr(String jzAccountMoneyStr) {
		this.jzAccountMoneyStr = jzAccountMoneyStr;
	}

	public void setJzbTimeTotalStr(String jzbTimeTotalStr) {
		this.jzbTimeTotalStr = jzbTimeTotalStr;
	}

	public void setMbAccountMoneyStr(String mbAccountMoneyStr) {
		this.mbAccountMoneyStr = mbAccountMoneyStr;
	}

	public void setTjbAccountMoneyStr(String tjbAccountMoneyStr) {
		this.tjbAccountMoneyStr = tjbAccountMoneyStr;
	}

	public void setLlFeeTotalStr(String llFeeTotalStr) {
		this.llFeeTotalStr = llFeeTotalStr;
	}

	public BigDecimal getDbAccountMoney() {
		return dbAccountMoney;
	}

	public void setDbAccountMoney(BigDecimal dbAccountMoney) {
		this.dbAccountMoney = dbAccountMoney;
	}

	public String getDbAccountMoneyStr() {
		if (null != dbAccountMoney) {
			dbAccountMoneyStr = "¥ " + MoneyUtil.roundMoney(dbAccountMoney);
		}
		return dbAccountMoneyStr;
	}

	public void setDbAccountMoneyStr(String dbAccountMoneyStr) {
		this.dbAccountMoneyStr = dbAccountMoneyStr;
	}

	public BigDecimal getDbTransferAccountMoney() {
		return dbTransferAccountMoney;
	}

	public void setDbTransferAccountMoney(BigDecimal dbTransferAccountMoney) {
		this.dbTransferAccountMoney = dbTransferAccountMoney;
	}

	public String getDbTransferAccountMoneyStr() {
		if (null != dbTransferAccountMoney) {
			dbTransferAccountMoneyStr = "¥ " + MoneyUtil.roundMoney(dbTransferAccountMoney);
		}
		return dbTransferAccountMoneyStr;
	}

	public void setDbTransferAccountMoneyStr(String dbTransferAccountMoneyStr) {
		this.dbTransferAccountMoneyStr = dbTransferAccountMoneyStr;

	}

	public BigDecimal getCashTotalApplyMoney() {
		return cashTotalApplyMoney;
	}

	public void setCashTotalApplyMoney(BigDecimal cashTotalApplyMoney) {
		this.cashTotalApplyMoney = cashTotalApplyMoney;
	}

	public String getCashTotalApplyMoneyStr() {
		if (null != cashTotalApplyMoney) {
			cashTotalApplyMoneyStr = "¥ " + MoneyUtil.roundMoney(cashTotalApplyMoney);
		}
		return cashTotalApplyMoneyStr;
	}

	public void setCashTotalApplyMoneyStr(String cashTotalApplyMoneyStr) {
		this.cashTotalApplyMoneyStr = cashTotalApplyMoneyStr;
	}

	public BigDecimal getChangeDrawMoneyFee() {
		return changeDrawMoneyFee;
	}

	public void setChangeDrawMoneyFee(BigDecimal changeDrawMoneyFee) {
		this.changeDrawMoneyFee = changeDrawMoneyFee;
	}

	public BigDecimal getNetBorrowOverdueFee() {
		return netBorrowOverdueFee;
	}

	public void setNetBorrowOverdueFee(BigDecimal netBorrowOverdueFee) {
		this.netBorrowOverdueFee = netBorrowOverdueFee;
	}

	public BigDecimal getCurInterestTotal() {
		return curInterestTotal;
	}

	public void setCurInterestTotal(BigDecimal curInterestTotal) {
		this.curInterestTotal = curInterestTotal;
	}

	public String getCurInterestTotalStr() {
		if (null != curInterestTotal) {
			curInterestTotalStr = "¥ " + MoneyUtil.roundMoney(curInterestTotal);
		}
		return curInterestTotalStr;
	}

	public BigDecimal getFuiouFeeTotal() {
		return fuiouFeeTotal;
	}

	public void setFuiouFeeTotal(BigDecimal fuiouFeeTotal) {
		this.fuiouFeeTotal = fuiouFeeTotal;
	}

	public BigDecimal getRedOutTotal() {
		return redOutTotal;
	}

	public void setRedOutTotal(BigDecimal redOutTotal) {
		this.redOutTotal = redOutTotal;
	}

    public BigDecimal getCzbankCustodyTotal() {
        return czbankCustodyTotal;
    }

    public void setCzbankCustodyTotal(BigDecimal czbankCustodyTotal) {
        this.czbankCustodyTotal = czbankCustodyTotal;
    }
}
