package com.cxdai.console.borrow.manage.vo;

import java.math.BigDecimal;

/**   
 * <p>
 * </p>
 * @title BorrowUserRechangeCnd.java
 * @package com.cxdai.console.borrow.vo 
 * @author 邹理享
 * @version 0.1 2015年5月12日
 */
public class BorrowUserRechangeVo {

	private String userName;// 充值用户
	private BigDecimal reChangeMoney;// 充值金额
	private String accountType;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public BigDecimal getReChangeMoney() {
		return reChangeMoney;
	}

	public void setReChangeMoney(BigDecimal reChangeMoney) {
		this.reChangeMoney = reChangeMoney;
	}

	public String getAccountType() { return accountType;}

	public void setAccountType(String accountType) {this.accountType = accountType;}


}
