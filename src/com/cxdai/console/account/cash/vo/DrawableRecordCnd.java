package com.cxdai.console.account.cash.vo;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.cxdai.console.common.page.BaseCnd;
import com.cxdai.console.util.DateUtils;

/**
 * <p>
 * Description:转可提查询参数vo<br />
 * </p>
 * 
 * @title BorrowCnd.java
 * @package com.cxdai.console.account.vo
 * @author hujianpan
 * @version 0.1 2014年8月24日
 */
public class DrawableRecordCnd extends BaseCnd implements Serializable {
	public void setBeginPublicTimeStr(String beginPublicTimeStr) {
		this.beginPublicTimeStr = beginPublicTimeStr;
	}

	public void setEndPublicTimeStr(String endPublicTimeStr) {
		this.endPublicTimeStr = endPublicTimeStr;
	}

	/**
	 * @fields serialVersionUID
	 */

	private static final long serialVersionUID = 6134569878969727608L;
	public String beginPublicTimeStr;
	public String endPublicTimeStr;
	public String userName;
	public String userId;
	private String status;

	public String getBeginPublicTimeStr() throws ParseException {
		if (StringUtils.isEmpty(beginPublicTimeStr)) {
			return StringUtils.EMPTY;
		}
		return beginPublicTimeStr;
	}

	private String currentTime(String sourceDate) {
		if (null != sourceDate && !"".equals(sourceDate)) {
			Date date = new Date(sourceDate);
			String dateFmt = DateUtils.format(date, DateUtils.YMD_DASH);
			return DateUtils.date2TimeStamp(dateFmt);// 返回秒
		}
		return "";
	}

	public String getEndPublicTimeStr() throws ParseException {
		if (StringUtils.isEmpty(endPublicTimeStr)) {
			return StringUtils.EMPTY;
		}
		return endPublicTimeStr;
	}

	private void nextDate(String time) {
		if (null != time && !"".equals(time)) {
			Date date = new Date(time);
			String dateFmt = DateUtils.format(date, DateUtils.YMD_DASH);
			String endTimeStamp = DateUtils.dayOffset(DateUtils.parse(dateFmt, DateUtils.YMD_DASH), 1).getTime() / 1000 + "";
		}
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}