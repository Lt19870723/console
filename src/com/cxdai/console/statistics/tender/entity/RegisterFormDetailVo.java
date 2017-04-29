/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title NetValueBorrowVo.java
 * @package com.cxdai.console.opration.vo 
 * @author ZHUCHEN
 * @version 0.1 2015年1月9日
 */
package com.cxdai.console.statistics.tender.entity;

import java.math.BigDecimal;

/**
 * @author WangQianJin
 * @title 注册表单明细对象
 * @date 2016年1月14日
 */
public class RegisterFormDetailVo {
	
	// 用户名
	private String username;
	// 用户状态
	private String status;
	// 真实姓名
	private String realname;
	// 邮箱
	private String email;
	// 手机
	private String mobile;
	// 注册时间
	private String registerTime;
	// 来源
	private String source;
	// 资产总额
	private BigDecimal total = BigDecimal.ZERO;
	// 是否实名认证
	private String realPassed;
	// 是否手机认证
	private String mobilePassed;
	// 是否邮箱认证
	private String emailPassed;
	// 是否VIP
	private String vipPassed;
	// 是否充值
	private String rechargePassed;
	// 充值金额
	private BigDecimal rechargeMoney = BigDecimal.ZERO;
	// 是否投标
	private String tenderPassed;
	// 投标金额
	private BigDecimal tenderMoney = BigDecimal.ZERO;
	// 是否提现
	private String cashPassed;
	// 提现金额
	private BigDecimal cashMoney = BigDecimal.ZERO;
	// 充值和提现差值
	private BigDecimal diffMoney = BigDecimal.ZERO;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public String getRealPassed() {
		return realPassed;
	}
	public void setRealPassed(String realPassed) {
		this.realPassed = realPassed;
	}
	public String getMobilePassed() {
		return mobilePassed;
	}
	public void setMobilePassed(String mobilePassed) {
		this.mobilePassed = mobilePassed;
	}
	public String getEmailPassed() {
		return emailPassed;
	}
	public void setEmailPassed(String emailPassed) {
		this.emailPassed = emailPassed;
	}
	public String getVipPassed() {
		return vipPassed;
	}
	public void setVipPassed(String vipPassed) {
		this.vipPassed = vipPassed;
	}
	public String getRechargePassed() {
		return rechargePassed;
	}
	public void setRechargePassed(String rechargePassed) {
		this.rechargePassed = rechargePassed;
	}
	public BigDecimal getRechargeMoney() {
		return rechargeMoney;
	}
	public void setRechargeMoney(BigDecimal rechargeMoney) {
		this.rechargeMoney = rechargeMoney;
	}
	public String getTenderPassed() {
		return tenderPassed;
	}
	public void setTenderPassed(String tenderPassed) {
		this.tenderPassed = tenderPassed;
	}
	public BigDecimal getTenderMoney() {
		return tenderMoney;
	}
	public void setTenderMoney(BigDecimal tenderMoney) {
		this.tenderMoney = tenderMoney;
	}
	public String getCashPassed() {
		return cashPassed;
	}
	public void setCashPassed(String cashPassed) {
		this.cashPassed = cashPassed;
	}
	public BigDecimal getCashMoney() {
		return cashMoney;
	}
	public void setCashMoney(BigDecimal cashMoney) {
		this.cashMoney = cashMoney;
	}
	public BigDecimal getDiffMoney() {
		return diffMoney;
	}
	public void setDiffMoney(BigDecimal diffMoney) {
		this.diffMoney = diffMoney;
	}
	
	

}
