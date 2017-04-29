/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title BorrowErrorVo.java
 * @package com.cxdai.console.borrow.check.vo 
 * @author tanghaitao
 * @version 0.1 2016年6月3日
 */
package com.cxdai.console.borrow.check.vo;

import java.math.BigDecimal;
import java.util.Date;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title BorrowErrorVo.java
 * @package com.cxdai.console.borrow.check.vo 
 * @author tanghaitao
 * @version 0.1 2016年6月3日
 */

public class BorrowErrorVo {

	private Integer id;
	
	private Integer status;//项目状态 0流标 1满标',
	
	private Integer borrowId;//标ID
	
	private Date addTime;
	
	private Date updateTime;
	
	private Integer updateUser;
	
	private Integer disposeStatus;//处理结果 0:未处理，1已处理',
	
	private String remark;//备注
	
	private String borrowName;//标名
	
	private BigDecimal account;//借款标计划金额
	
	private BigDecimal accountYes;//已借到金额
	
	private String repayName;//借款标还款账户名
	
	private String userName;//借款人用户名
	
	private String oriSerialNo;//原投资冻结流水号
	
	private String checkRemark;//审核备注
	
	
	

	public String getCheckRemark() {
		return checkRemark;
	}

	public void setCheckRemark(String checkRemark) {
		this.checkRemark = checkRemark;
	}

	public String getOriSerialNo() {
		return oriSerialNo;
	}

	public void setOriSerialNo(String oriSerialNo) {
		this.oriSerialNo = oriSerialNo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}

	public Integer getDisposeStatus() {
		return disposeStatus;
	}

	public void setDisposeStatus(Integer disposeStatus) {
		this.disposeStatus = disposeStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBorrowName() {
		return borrowName;
	}

	public void setBorrowName(String borrowName) {
		this.borrowName = borrowName;
	}

	public BigDecimal getAccount() {
		return account;
	}

	public void setAccount(BigDecimal account) {
		this.account = account;
	}

	public BigDecimal getAccountYes() {
		return accountYes;
	}

	public void setAccountYes(BigDecimal accountYes) {
		this.accountYes = accountYes;
	}

	public String getRepayName() {
		return repayName;
	}

	public void setRepayName(String repayName) {
		this.repayName = repayName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
	
	
	
}
