package com.cxdai.console.account.risk.entity;

import java.math.BigDecimal;

/**
 * 
 * <p>
 * Description:风险保证金日志实体类<br />
 * </p>
 * @title RiskMarginLog.java
 * @package com.cxdai.base.entity 
 */
public class RiskMarginLog {
	
	/** 主键 **/
    private Integer id;
    
    /** 风险保证金 **/
    private BigDecimal account;
    
    /** 添加时间 **/
    private String addTime;
    
    /** 操作人**/
    private Integer staffId;
    
    /** 添加IP**/
    private String addIp;
    
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public BigDecimal getAccount() {
		return account;
	}
	
	public void setAccount(BigDecimal account) {
		this.account = account;
	}
	
	public String getAddTime() {
		return addTime;
	}
	
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	
	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public String getAddIp() {
		return addIp;
	}
	
	public void setAddIp(String addIp) {
		this.addIp = addIp;
	}
}
