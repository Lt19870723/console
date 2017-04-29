package com.cxdai.console.statistics.account.vo;

/**
 * 
 * <p>
 * Description:资金验证查询参数类<br />
 * </p>
 * 
 * @author 陈鹏
 * @version 0.1 2015年1月20日
 */
public class AccountValidateCnd {
	//用户名；
	private String username;
	//借款标id
	private int borrowId;
	
	//借款标题；
	private String bname;
	
	private String isCustody;
	
	
	
	public String getIsCustody() {
		return isCustody;
	}
	public void setIsCustody(String isCustody) {
		this.isCustody = isCustody;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public int getBorrowId() {
		return borrowId;
	}
	public void setBorrowId(int borrowId) {
		this.borrowId = borrowId;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	
}
