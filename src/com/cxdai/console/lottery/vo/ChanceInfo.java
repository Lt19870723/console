package com.cxdai.console.lottery.vo;

import java.util.Date;

import com.cxdai.console.base.entity.BaseChanceInfo;
import com.cxdai.console.util.DateUtils;

public class ChanceInfo extends BaseChanceInfo {

	String addTimeStr;

	String chanceRuleName;

	public String getChanceRuleName() {
		return chanceRuleName;
	}

	public void setChanceRuleName(String chanceRuleName) {
		this.chanceRuleName = chanceRuleName;
	}

	public String getAddTimeStr() {
		Date addTime = getAddTime();
		if (addTime != null) {
			return DateUtils.format(addTime, DateUtils.YMD_HMS);
		}
		return addTimeStr;
	}

	public void setAddTimeStr(String addTimeStr) {
		this.addTimeStr = addTimeStr;
	}

}