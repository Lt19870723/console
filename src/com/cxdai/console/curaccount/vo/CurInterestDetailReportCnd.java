package com.cxdai.console.curaccount.vo;

import java.io.Serializable;
import java.util.Date;

import com.cxdai.console.common.page.BaseCnd;
import com.cxdai.console.util.DateUtils;

public class CurInterestDetailReportCnd extends BaseCnd implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = DateUtils.parse(date, DateUtils.YMD_DASH);
	}
}
