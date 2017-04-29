package com.cxdai.console.finance.virement.vo;

import java.util.Date;

import com.cxdai.console.finance.virement.entity.Journal;

public class JournalVo extends Journal {

	private Integer flag;

	private Date accountDate;

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Date getAccountDate() {
		return accountDate;
	}

	public void setAccountDate(Date accountDate) {
		this.accountDate = accountDate;
	}

}
