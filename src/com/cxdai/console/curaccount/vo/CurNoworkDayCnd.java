package com.cxdai.console.curaccount.vo;

import java.io.Serializable;

import com.cxdai.console.curaccount.entity.CurNoworkDay;

public class CurNoworkDayCnd extends CurNoworkDay implements Serializable {

	private static final long serialVersionUID = -6903226903535940857L;

	private String startDateStr;
	private String endDateStr;

	/**
	 * @return startDateStr : return the property startDateStr.
	 */
	public String getStartDateStr() {
		return startDateStr;
	}

	/**
	 * @param startDateStr
	 *            : set the property startDateStr.
	 */
	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}

	/**
	 * @return endDateStr : return the property endDateStr.
	 */
	public String getEndDateStr() {
		return endDateStr;
	}

	/**
	 * @param endDateStr
	 *            : set the property endDateStr.
	 */
	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}

}