package com.cxdai.console.customer.feedback.vo;

import org.apache.commons.lang3.StringUtils;

import com.cxdai.console.customer.feedback.entity.FeedbackInfo;
import com.cxdai.console.util.DateUtils;

public class FeedbackInfoVo extends FeedbackInfo {

	private String staffName;
	private String addTimeStr;
	private String contactTimeStr;

	public String getContactTimeStr() {
		if (null == this.getContactTime()) {
			return StringUtils.EMPTY;
		}
		return DateUtils.format(this.getContactTime(), DateUtils.YMD_HMS);
	}

	public String getAddTimeStr() {
		if (null == this.getAddTime()) {
			return StringUtils.EMPTY;
		}
		return DateUtils.format(this.getAddTime(), DateUtils.YMD_HMS);
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

}
