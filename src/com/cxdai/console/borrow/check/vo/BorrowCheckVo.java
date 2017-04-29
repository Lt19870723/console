
package com.cxdai.console.borrow.check.vo;

import java.math.BigDecimal;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title BorrowCheckVo.java
 * @package com.cxdai.console.borrow.check.vo 
 * @author tanghaitao
 * @version 0.1 2016年5月30日
 */

public class BorrowCheckVo {

	private BigDecimal account;
	
	private Integer count;
	
	private BigDecimal sumRepayAccount;
	
	private BigDecimal sumInterest;
	
	private BigDecimal sumCapital;
	

	public BigDecimal getSumRepayAccount() {
		return sumRepayAccount;
	}

	public void setSumRepayAccount(BigDecimal sumRepayAccount) {
		this.sumRepayAccount = sumRepayAccount;
	}

	public BigDecimal getSumInterest() {
		return sumInterest;
	}

	public void setSumInterest(BigDecimal sumInterest) {
		this.sumInterest = sumInterest;
	}

	public BigDecimal getSumCapital() {
		return sumCapital;
	}

	public void setSumCapital(BigDecimal sumCapital) {
		this.sumCapital = sumCapital;
	}

	public BigDecimal getAccount() {
		return account;
	}

	public void setAccount(BigDecimal account) {
		this.account = account;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	
	
	
}
