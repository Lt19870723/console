package com.cxdai.console.statistics.account.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.cxdai.console.util.MoneyUtil;

/**
 * 
 * @author hujianpan
 *  运营报表vo
 *
 */
public class OperateStatementVo implements Serializable{
	/** 
	 * @fields serialVersionUID 
	 */ 
	private static final long serialVersionUID = -3240824751874092465L;
	/**注册人数*/
	private Integer  totalRegMembers;
	private String  totalRegMembersStr;
	/**实名认证人数*/
	private Integer totalRealNameMembers;
	private String totalRealNameMembersStr;
	/**VIP认证人数*/
	private Integer totalVIPPassMembers;
	private String totalVIPPassMembersStr;
	/**投资人数*/
	private Integer totalTenders;
	private String totalTendersStr;
	/**充值金额*/
	private BigDecimal totalRechargeMoney;
	private String totalRechargeMoneyStr;
	/**提现金额*/
	private BigDecimal totalCashMoney;
	private String totalCashMoneyStr;
	/**推荐标金额*/
	private BigDecimal totalTJMoney;
	private String totalTJMoneyStr;
	/**抵押标金额*/
	private BigDecimal totalDYMoney;
	private String totalDYMoneyStr;
	/**净值标金额*/
	private BigDecimal totalJZMoney;
	private String totalJZMoneyStr;
	/**秒标金额*/
	private BigDecimal totalMBMoney;
	private String totalMBMoneyStr;
	/**担保标金额*/
	private BigDecimal totalDBMoney;
	private String totalDBMoneyStr;
	
	/**存管提现金额*/
	private BigDecimal totalCGMoney;
	private String totalCGMoneyStr;
	
	public BigDecimal getTotalCGMoney() {
		return totalCGMoney;
	}
	public void setTotalCGMoney(BigDecimal totalCGMoney) {
		this.totalCGMoney = totalCGMoney;
	}
	public String getTotalRegMembersStr() {
		return totalRegMembers+"";
	}
	public String getTotalRealNameMembersStr() {
		return totalRealNameMembers+"";
	}
	public String getTotalVIPPassMembersStr() {
		return totalVIPPassMembers+"";
	}
	public String getTotalTendersStr() {
		return totalTenders+"";
	}
	public String getTotalRechargeMoneyStr() {
		if(null == totalRechargeMoney ){
			return "0";
		}
		return MoneyUtil.fmtMoneyByDot(totalRechargeMoney);
	}
	public String getTotalCashMoneyStr() {
		if(null == totalCashMoney){
			return "0";
		}
		return MoneyUtil.fmtMoneyByDot(totalCashMoney) ;
	}
	public String getTotalTJMoneyStr() {
		if(null == totalTJMoney){
			return "0";
		}
		return MoneyUtil.fmtMoneyByDot(totalTJMoney);
	}
	public String getTotalDYMoneyStr() {
		if(null == totalDYMoney){
			return "0";
		}
		return MoneyUtil.fmtMoneyByDot(totalDYMoney);
	}
	public String getTotalJZMoneyStr() {
		if(null == totalJZMoney){
			return "totalJZMoney";
		}
		return MoneyUtil.fmtMoneyByDot(totalJZMoney);
	}
	public String getTotalMBMoneyStr() {
		if(null == totalMBMoney){
			return "0";
		}
		return MoneyUtil.fmtMoneyByDot(totalMBMoney);
	}
	public String getTotalDBMoneyStr() {
		if(null == totalDBMoney){
			return "";
		}
		return MoneyUtil.fmtMoneyByDot(totalDBMoney);
	}
	public Integer getTotalRegMembers() {
		return totalRegMembers;
	}
	public void setTotalRegMembers(Integer totalRegMembers) {
		this.totalRegMembers = totalRegMembers;
	}
	public Integer getTotalRealNameMembers() {
		return totalRealNameMembers;
	}
	public void setTotalRealNameMembers(Integer totalRealNameMembers) {
		this.totalRealNameMembers = totalRealNameMembers;
	}
	public Integer getTotalVIPPassMembers() {
		return totalVIPPassMembers;
	}
	public void setTotalVIPPassMembers(Integer totalVIPPassMembers) {
		this.totalVIPPassMembers = totalVIPPassMembers;
	}
	public Integer getTotalTenders() {
		return totalTenders;
	}
	public void setTotalTenders(Integer totalTenders) {
		this.totalTenders = totalTenders;
	}
	public BigDecimal getTotalRechargeMoney() {
		return totalRechargeMoney;
	}
	public void setTotalRechargeMoney(BigDecimal totalRechargeMoney) {
		this.totalRechargeMoney = totalRechargeMoney;
	}
	public BigDecimal getTotalCashMoney() {
		return totalCashMoney;
	}
	public void setTotalCashMoney(BigDecimal totalCashMoney) {
		this.totalCashMoney = totalCashMoney;
	}
	public BigDecimal getTotalTJMoney() {
		return totalTJMoney;
	}
	public void setTotalTJMoney(BigDecimal totalTJMoney) {
		this.totalTJMoney = totalTJMoney;
	}
	public BigDecimal getTotalDYMoney() {
		return totalDYMoney;
	}
	public void setTotalDYMoney(BigDecimal totalDYMoney) {
		this.totalDYMoney = totalDYMoney;
	}
	public BigDecimal getTotalJZMoney() {
		return totalJZMoney;
	}
	public void setTotalJZMoney(BigDecimal totalJZMoney) {
		this.totalJZMoney = totalJZMoney;
	}
	public BigDecimal getTotalMBMoney() {
		return totalMBMoney;
	}
	public void setTotalMBMoney(BigDecimal totalMBMoney) {
		this.totalMBMoney = totalMBMoney;
	}
	public BigDecimal getTotalDBMoney() {
		return totalDBMoney;
	}
	public void setTotalDBMoney(BigDecimal totalDBMoney) {
		this.totalDBMoney = totalDBMoney;
	}
	
	
}
