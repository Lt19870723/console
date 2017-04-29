package com.cxdai.console.statistics.account.vo;

import java.io.Serializable;
import java.util.Date;

import com.cxdai.console.common.page.BaseCnd;


/**
 * <p>
 * Description:资金对帐查询条件<br />
 * </p>
 * 
 * @title AccountCheckCnd.java
 * @package com.cxdai.console.account.vo
 * @author justin.xu
 * @version 0.1 2014年10月14日
 */
public class AccountCheckCnd extends BaseCnd implements Serializable {
	private static final long serialVersionUID = 8983061145517554053L;
	private Date beginTime;

	private Date endTime;

	private String beginTimeStr;
	private String endTimeStr;

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getBeginTimeStr() {
		return beginTimeStr;
	}

	public void setBeginTimeStr(String beginTimeStr) {
		this.beginTimeStr = beginTimeStr;
	}

	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

}
