package com.cxdai.console.finance.virement.vo;

import com.cxdai.console.common.page.BaseCnd;

@SuppressWarnings("serial")
public class QueryPageCheckWithCnd extends BaseCnd{

	private String startDate;
	private String endDate;
	private Integer isSuccess;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Integer isSuccess) {
		this.isSuccess = isSuccess;
	}
	
	
}
