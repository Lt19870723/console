package com.cxdai.console.finance.virement.vo;

import com.cxdai.console.common.page.BaseCnd;

public class RechargeAnalysisCnd extends BaseCnd {

	/**
	 * @fields serialVersionUID
	 */

	private static final long serialVersionUID = -3819948392719702422L;

	private String beginTime;// 开始时间
	private String endTime;// 结束时间

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
