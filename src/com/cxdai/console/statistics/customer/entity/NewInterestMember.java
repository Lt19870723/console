package com.cxdai.console.statistics.customer.entity;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.cxdai.console.common.Dictionary;
import com.cxdai.console.system.entity.Configuration;
import com.cxdai.console.util.DateUtils;


public class NewInterestMember {

	private String userName;//用户名
	private String mobilenum;//手机号
	private String realName;//真实姓名
	private Date registerTime;//注册时间
	private Date investTime;//首次投资时间
	private BigDecimal investMoney;//首次投资金额
	private BigDecimal investMoneyTotal;//投资总额
	private String type;//标的种类
	private BigDecimal repaymentAccountTotal;//当前待收
	private String source;//推广来源
	private String registerTimeStr;//注册时间
	private String investTimeStr;//首次投资时间
	private String sourceString;//推广来源
	private String inviterName;//推荐人
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobilenum() {
		return mobilenum;
	}
	public void setMobilenum(String mobilenum) {
		this.mobilenum = mobilenum;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public Date getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	public Date getInvestTime() {
		return investTime;
	}
	public void setInvestTime(Date investTime) {
		this.investTime = investTime;
	}
	public BigDecimal getInvestMoney() {
		return investMoney;
	}
	public void setInvestMoney(BigDecimal investMoney) {
		this.investMoney = investMoney;
	}
	public BigDecimal getInvestMoneyTotal() {
		return investMoneyTotal;
	}
	public void setInvestMoneyTotal(BigDecimal investMoneyTotal) {
		this.investMoneyTotal = investMoneyTotal;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public BigDecimal getRepaymentAccountTotal() {
		return repaymentAccountTotal;
	}
	public void setRepaymentAccountTotal(BigDecimal repaymentAccountTotal) {
		this.repaymentAccountTotal = repaymentAccountTotal;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getRegisterTimeStr() {
		if(this.registerTime!=null){
			registerTimeStr=DateUtils.format(this.registerTime,"yyyy-MM-dd HH:mm:ss");
		}
		return registerTimeStr;
	}
	public void setRegisterTimeStr(String registerTimeStr) {
		this.registerTimeStr = registerTimeStr;
	}
	public String getInvestTimeStr() {
		if(this.investTime!=null){
			investTimeStr=DateUtils.format(this.investTime,"yyyy-MM-dd HH:mm:ss");
		}
		return investTimeStr;
	}
	public void setInvestTimeStr(String investTimeStr) {
		this.investTimeStr = investTimeStr;
	}
	public String getSourceString() {
		if(this.source!=null){
			sourceString = Dictionary.getValue(1101,this.source);// 来源
		}
		return sourceString;
	}
	public void setSourceString(String sourceString) {
		this.sourceString = sourceString;
	}
	public String getInviterName() {
		if(StringUtils.isNotEmpty(inviterName)){
			return inviterName;
		}else{
			return "";
		}
		
	}
	public void setInviterName(String inviterName) {
		this.inviterName = inviterName;
	}
}
