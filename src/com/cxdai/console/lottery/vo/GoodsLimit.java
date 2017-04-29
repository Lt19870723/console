package com.cxdai.console.lottery.vo;

import java.util.Date;

import com.cxdai.console.lottery.entity.BaseGoodLimit;
import com.cxdai.console.util.DateUtils;

public class GoodsLimit extends BaseGoodLimit {

	String goodName;

	String startTimeStr;

	String endTimeStr;

	public GoodsLimit() {
		super.setStatus(0);
		super.setUsedNum(0);
	}

	public String getStartTimeStr() {

		Date startTime = getStartTime();
		if (startTime != null) {
			return DateUtils.format(startTime, DateUtils.YMD_HMS);
		}

		return startTimeStr;
	}

	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}

	public String getEndTimeStr() {

		Date endTime = getEndTime();
		if (endTime != null) {
			return DateUtils.format(endTime, DateUtils.YMD_HMS);
		}
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

}