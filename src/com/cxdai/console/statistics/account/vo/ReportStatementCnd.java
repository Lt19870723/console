package com.cxdai.console.statistics.account.vo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.cxdai.console.common.page.BaseCnd;
import com.cxdai.console.util.DateUtils;

public class ReportStatementCnd extends BaseCnd implements Serializable {
	private static final long serialVersionUID = -5890980057423538506L;
	private String beginTime;
	private String endTime;
	private String beginTimeSec;
	private String endTimeSec;
	private int onlinetype;
	private String merchantNo;
	private String redBeginTime;//红包支出查询专用
	private String redEendTime;//红包支出查询专用
	private String source;//推广来源
	/*网银在线商户号*/
	private String onlineNo22791329;
	private String onlineNo22899911;
	private String onlineNo23234639;
	

	public String getOnlineNo22791329() {
		return onlineNo22791329;
	}

	public void setOnlineNo22791329(String onlineNo22791329) {
		this.onlineNo22791329 = onlineNo22791329;
	}

	public String getOnlineNo22899911() {
		return onlineNo22899911;
	}

	public void setOnlineNo22899911(String onlineNo22899911) {
		this.onlineNo22899911 = onlineNo22899911;
	}

	public String getOnlineNo23234639() {
		return onlineNo23234639;
	}

	public void setOnlineNo23234639(String onlineNo23234639) {
		this.onlineNo23234639 = onlineNo23234639;
	}

	/*
	 * 8 论坛查询条件
	 */
	private String beginTimeSecForforum;
	private String endTimeSecForforum;

	public String getBeginTimeSecForforum() {
		return beginTimeSecForforum;
	}

	public void setBeginTimeSecForforum(String beginTimeSecForforum) {
		this.beginTimeSecForforum = beginTimeSecForforum;
	}

	public String getEndTimeSecForforum() {
		return endTimeSecForforum;
	}

	public void setEndTimeSecForforum(String endTimeSecForforum) {
		this.endTimeSecForforum = endTimeSecForforum;
	}

	public void setBeginTimeSec(String beginTimeSec) {
		this.beginTimeSec = beginTimeSec;
	}

	public void setEndTimeSec(String endTimeSec) {
		this.endTimeSec = endTimeSec;
	}

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

	public int getOnlinetype() {
		return onlinetype;
	}

	public void setOnlinetype(int onlinetype) {
		this.onlinetype = onlinetype;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getBeginTimeSec() {
		if (null != beginTime && !"".equals(beginTime.trim())) {
			Date date = DateUtils.parse(beginTime, DateUtils.YMD_DASH);
			String dateFmt = DateUtils.format(date, DateUtils.YMD_DASH);
			beginTimeSec = DateUtils.date2TimeStamp(dateFmt);// 返回秒
		}
		return beginTimeSec;
	}

	public String getEndTimeSec() {
		if (null != endTime && !"".equals(endTime.trim())) {
			Date date = DateUtils.parse(endTime, DateUtils.YMD_DASH);
			endTimeSec = DateUtils.convert2EndDate(date).getTime() / 1000 + "";
		}
		return endTimeSec;
	}
	
	
	public String getRedBeginTime() {
		if(null!=this.beginTime&&StringUtils.isNotEmpty(beginTime)&&this.beginTime.length()==10){
			return this.redBeginTime=this.beginTime+" 00:00:00";
		}else{
			return redBeginTime;
		}
	}

	public void setRedBeginTime(String redBeginTime) {
		this.redBeginTime = redBeginTime;
	}

	public String getRedEendTime() {
		if(null!=this.endTime&&StringUtils.isNotEmpty(endTime)&&this.endTime.length()==10){
			return this.redEendTime=this.endTime+" 23:59:59";
		}else{
			return redEendTime;
		}
	}

	public void setRedEendTime(String redEendTime) {
		this.redEendTime = redEendTime;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
}
