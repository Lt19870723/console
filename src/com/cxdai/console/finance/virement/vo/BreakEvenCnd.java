package com.cxdai.console.finance.virement.vo;

import java.util.Date;

import com.cxdai.console.common.page.BaseCnd;

public class BreakEvenCnd extends BaseCnd {
	/**
	 * @fields serialVersionUID
	 */

	private static final long serialVersionUID = -3479566965356164965L;
	/**
	 * @fields serialVersionUID
	 */

	private String beginTime;// 开始时间
	private String endTime;// 结束时间

	private Date begin;// 开始时间

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

	public Date getBegin() {
		return begin;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

}
