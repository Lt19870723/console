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

import org.apache.commons.lang3.StringUtils;

/**
 * 客户投资记录
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title MemberTenderRecordVo.java
 * @package com.cxdai.console.opration.vo
 * @author 刘涛
 * @version 0.1 2016年4月20日
 */
public class MemberTenderRecordVo {
	private String userName;
	private String tenderType;
	private BigDecimal tenderMoney;
	private BigDecimal accountTotal;
	private String apr;
	private String tenderDate;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTenderType() {
		return tenderType;
	}
	public void setTenderType(String tenderType) {
		this.tenderType = tenderType;
	}
	public BigDecimal getTenderMoney() {
		return tenderMoney;
	}
	public void setTenderMoney(BigDecimal tenderMoney) {
		this.tenderMoney = tenderMoney;
	}
	public BigDecimal getAccountTotal() {
		return accountTotal;
	}
	public void setAccountTotal(BigDecimal accountTotal) {
		this.accountTotal = accountTotal;
	}
	public String getApr() {
		if(StringUtils.isNotEmpty(this.apr)){
			return apr+"%";
		}
		return "";
	}
	public void setApr(String apr) {
		this.apr = apr;
	}
	public String getTenderDate() {
		return tenderDate;
	}
	public void setTenderDate(String tenderDate) {
		this.tenderDate = tenderDate;
	}
}
