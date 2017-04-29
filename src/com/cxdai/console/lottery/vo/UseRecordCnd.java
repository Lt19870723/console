package com.cxdai.console.lottery.vo;

import java.util.Date;

import com.cxdai.console.common.page.BaseCnd;
import com.cxdai.console.util.DateUtils;

public class UseRecordCnd extends BaseCnd {


	/**
	 * @fields serialVersionUID
	 */

	private static final long serialVersionUID = 1L;

	Date beginDate;

	Date endDate;
     
	String beginDateStr;
	
	String endDateStr;
	
	String userName;

	Integer awardType;

	Integer awardSource;

	String status;// 0：未领取，1：已领取，2：待处理，3：派发中，4：已过期
	
	String lotteryGoodsName;

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		if (beginDate != null) {
			this.beginDate = DateUtils.parse(DateUtils.format(beginDate, DateUtils.YMD_DASH) + " 00:00:00", DateUtils.YMD_HMS);
			return;
		}
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		if (endDate != null) {
			this.endDate = DateUtils.parse(DateUtils.format(endDate, DateUtils.YMD_DASH) + " 23:59:59", DateUtils.YMD_HMS);
			return;
		}

		this.endDate = endDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getAwardType() {
		return awardType;
	}

	public void setAwardType(Integer awardType) {
		this.awardType = awardType;
	}

	public Integer getAwardSource() {
		return awardSource;
	}

	public void setAwardSource(Integer awardSource) {
		this.awardSource = awardSource;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLotteryGoodsName() {
		return lotteryGoodsName;
	}

	public void setLotteryGoodsName(String lotteryGoodsName) {
		this.lotteryGoodsName = lotteryGoodsName;
	}

	public String getBeginDateStr() {
		return beginDateStr;
	}

	public void setBeginDateStr(String beginDateStr) {
		this.beginDateStr = beginDateStr;
	}

	public String getEndDateStr() {
		return endDateStr;
	}

	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}
	
}