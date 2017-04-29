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
 * 每月投资额统计对象
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title MonthlyInvestVo.java
 * @package com.cxdai.console.opration.vo
 * @author ZHUCHEN
 * @version 0.1 2015年1月17日
 */
public class MonthlyInvestVo {
	private long userId;
	private String userName;
	private String monthStr;
	private BigDecimal money = BigDecimal.ZERO;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getMonthStr() {
		return monthStr;
	}

	public void setMonthStr(String monthStr) {
		this.monthStr = monthStr;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
