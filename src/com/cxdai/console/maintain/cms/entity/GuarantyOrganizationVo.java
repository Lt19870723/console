package com.cxdai.console.maintain.cms.entity;

import java.io.Serializable;

import com.cxdai.console.base.entity.GuarantyOrganization;
import com.cxdai.console.util.DateUtils;

public class GuarantyOrganizationVo extends GuarantyOrganization implements Serializable {

	/**
	 * @fields serialVersionUID
	 */

	private static final long serialVersionUID = 1L;

	private String addtimeStr; // 添加时间

	public String getAddtimeStr() {
		if (getAddtime() != null) {
			addtimeStr = DateUtils.timeStampToDate(String.valueOf(getAddtime().getTime() / 1000L), DateUtils.YMD_HMS);
		}
		return addtimeStr;
	}

	public void setAddtimeStr(String addtimeStr) {
		this.addtimeStr = addtimeStr;
	}

}
