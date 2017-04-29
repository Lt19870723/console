package com.cxdai.console.customer.information.vo;

import java.io.Serializable;

/**
 * <p>
 * Description:用户类Vo，用于后台审核借款用户时查询使用<br />
 * </p>
 * 
 * @title MemberVo.java
 * @package com.cxdai.console.member.vo
 * @author hujianpan
 * @version 0.1 2014年11月14日
 */
public class MemberAuditVo implements Serializable {

	private static final long serialVersionUID = -5153921716975894025L;
	/**用户id*/
	private Integer id;
	/**用户名*/
	private String userName;
	/**用户邮箱*/
	private String email;
	/**用户手机*/
	private String mobileNum;
	/**注册时间*/
	private String addTime;
	/**用户ip*/
	private String addIp;
	/**账户状态*/
	private Integer status;
	/**用户身份*/
	private Integer type;
	/**用户类型：借款用户    理财用户*/
	private Integer isFinacialUser;
	/**审核人*/
	private String auditName;
	/**审核状态字段ISPASSED (0 待审核 1审核通过 -1审核不通过）*/
	private Integer isPassed;
	/**审核时间*/
	private String auditTime;
	/** 审核备注 */
	private String reason;
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNum() {
		return mobileNum;
	}
	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getAddIp() {
		return addIp;
	}
	public void setAddIp(String addIp) {
		this.addIp = addIp;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getIsFinacialUser() {
		return isFinacialUser;
	}
	public void setIsFinacialUser(Integer isFinacialUser) {
		this.isFinacialUser = isFinacialUser;
	}
	public Integer getIsPassed() {
		return isPassed;
	}
	public void setIsPassed(Integer isPassed) {
		this.isPassed = isPassed;
	}
	public String getAuditName() {
		return auditName;
	}
	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}
	public String getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}
	
	
}