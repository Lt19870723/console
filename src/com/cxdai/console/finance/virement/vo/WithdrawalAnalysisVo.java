package com.cxdai.console.finance.virement.vo;

import java.math.BigDecimal;

/***
 * 
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title RechargeAnalysisVo.java
 * @package com.cxdai.console.finance.virement.vo
 * @author Administrator
 * @version 0.1 2016年4月17日
 */
public class WithdrawalAnalysisVo {
	private Integer userId;// 用户ID
	private String realname;// 用户姓名
	private String username;// 用户名
	private BigDecimal withdrawalAmount;// 提现金额,到账金额(不包含手续费)
	private String withdrawalType;// 提现分类
	private BigDecimal withdrawalRate;// 提现比率
	private BigDecimal assetAmount;// 资产总额
	private Integer rankno;// 排名

	private String withdrawalRateStr;// 提现比率添加%

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public BigDecimal getWithdrawalAmount() {
		return withdrawalAmount;
	}

	public void setWithdrawalAmount(BigDecimal withdrawalAmount) {
		this.withdrawalAmount = withdrawalAmount;
	}

	public String getWithdrawalType() {
		return withdrawalType;
	}

	public void setWithdrawalType(String withdrawalType) {
		this.withdrawalType = withdrawalType;
	}

	public BigDecimal getWithdrawalRate() {
		return withdrawalRate;
	}

	public void setWithdrawalRate(BigDecimal withdrawalRate) {
		this.withdrawalRate = withdrawalRate;
	}

	public BigDecimal getAssetAmount() {
		return assetAmount;
	}

	public void setAssetAmount(BigDecimal assetAmount) {
		this.assetAmount = assetAmount;
	}

	public Integer getRankno() {
		return rankno;
	}

	public void setRankno(Integer rankno) {
		this.rankno = rankno;
	}

	public String getWithdrawalRateStr() {
		withdrawalRateStr = withdrawalRate + "%";
		return withdrawalRateStr;
	}

	public void setWithdrawalRateStr(String withdrawalRateStr) {
		this.withdrawalRateStr = withdrawalRateStr;
	}

}
