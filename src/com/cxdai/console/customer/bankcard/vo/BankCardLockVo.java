package com.cxdai.console.customer.bankcard.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * <p>
 * Description:银行卡锁定查询结果类<br />
 * </p>
 * 
 * @author 陈鹏
 * @version 0.1 2014年12月18日
 */
public class BankCardLockVo implements Serializable {

	private static final long serialVersionUID = 1L;

	//用户名
	private String vusername;
	
	//邮箱
	private String vemial;
	
	//手机 
	private String vmobile;
	
	//真实姓名  
	private String vrealname;
	//锁定状态 
	private String vlockStatus;
	
	//已有银行卡数量 
	private Integer vbCardNum;
	
	//资产总额
	private BigDecimal vtotal;
	
	//待收总额  
	private BigDecimal vcollection;
	
	//可用金额  
	private BigDecimal vuseMoney;
	
	//冻结金额
	private BigDecimal vnoUseMoney;
	
	//活期宝总额
	private BigDecimal curTotal;
	//定期宝总额
	private BigDecimal fixTotal;	
	//绑定时间
	private Date createTime;
	public BigDecimal getCurTotal() {
		return curTotal;
	}

	public void setCurTotal(BigDecimal curTotal) {
		this.curTotal = curTotal;
	}

	public BigDecimal getFixTotal() {
		return fixTotal;
	}

	public void setFixTotal(BigDecimal fixTotal) {
		this.fixTotal = fixTotal;
	}

	public String getVusername() {
		return vusername;
	}

	public void setVusername(String vusername) {
		this.vusername = vusername;
	}

	public String getVemial() {
		return vemial;
	}

	public void setVemial(String vemial) {
		this.vemial = vemial;
	}

	public String getVmobile() {
		return vmobile;
	}

	public void setVmobile(String vmobile) {
		this.vmobile = vmobile;
	}

	public String getVrealname() {
		return vrealname;
	}

	public void setVrealname(String vrealname) {
		this.vrealname = vrealname;
	}

	public String getVlockStatus() {
		return vlockStatus;
	}

	public void setVlockStatus(String vlockStatus) {
		this.vlockStatus = vlockStatus;
	}

	public Integer getVbCardNum() {
		return vbCardNum;
	}

	public void setVbCardNum(Integer vbCardNum) {
		this.vbCardNum = vbCardNum;
	}

	public BigDecimal getVtotal() {
		return vtotal;
	}

	public void setVtotal(BigDecimal vtotal) {
		this.vtotal = vtotal;
	}

	public BigDecimal getVcollection() {
		return vcollection;
	}

	public void setVcollection(BigDecimal vcollection) {
		this.vcollection = vcollection;
	}

	public BigDecimal getVuseMoney() {
		return vuseMoney;
	}

	public void setVuseMoney(BigDecimal vuseMoney) {
		this.vuseMoney = vuseMoney;
	}

	public BigDecimal getVnoUseMoney() {
		return vnoUseMoney;
	}

	public void setVnoUseMoney(BigDecimal vnoUseMoney) {
		this.vnoUseMoney = vnoUseMoney;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
