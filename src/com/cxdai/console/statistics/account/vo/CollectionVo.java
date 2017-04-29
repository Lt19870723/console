package com.cxdai.console.statistics.account.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.cxdai.console.base.entity.BCollectionRecord;
import com.cxdai.console.util.MoneyUtil;

public class CollectionVo  extends BCollectionRecord implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private BigDecimal advanceYesaccount;
	private String username;
	
	//临时属性
	private String repayAccountStr;
	private String interestStr;
	private String capitalStr;
	private String advanceYAStr;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRepayAccountStr() {
		if(super.getRepayAccount() != null){
			return MoneyUtil.fmtMoneyByDot(super.getRepayAccount());
		}
		return repayAccountStr;
	}
	public void setRepayAccountStr(String repayAccountStr) {
		this.repayAccountStr = repayAccountStr;
	}
	
	public String getInterestStr() {
		if(super.getInterest() != null){
			return MoneyUtil.fmtMoneyByDot(super.getInterest());
		}
		return interestStr;
	}
	public void setInterestStr(String interestStr) {
		this.interestStr = interestStr;
	}
	
	public String getCapitalStr() {
		if(super.getCapital() != null){
			return MoneyUtil.fmtMoneyByDot(super.getCapital());
		}
		return capitalStr;
	}
	public void setCapitalStr(String capitalStr) {
		this.capitalStr = capitalStr;
	}
	
	public BigDecimal getAdvanceYesaccount() {
		return advanceYesaccount;
	}
	public void setAdvanceYesaccount(BigDecimal advanceYesaccount) {
		this.advanceYesaccount = advanceYesaccount;
	}
	
	public String getAdvanceYAStr() {
		if(getAdvanceYesaccount() != null){
			return MoneyUtil.fmtMoneyByDot(getAdvanceYesaccount());
		}
		return advanceYAStr;
	}
	public void setAdvanceYAStr(String advanceYAStr) {
		this.advanceYAStr = advanceYAStr;
	}


}
