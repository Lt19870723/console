package com.cxdai.console.stock.vo;

import java.io.Serializable;

/**
 * 
 * <p>
 * Description:账户类查询条件<br />
 * </p>
 * @title CapitalAccountCnd.java
 * @package com.cxdai.portal.stockright.vo 
 * @author xiaofei.li
 * @version 0.1 2016-5-23
 */
public class CapitalAccountCnd implements Serializable {

	private static final long serialVersionUID = -3531851612222097637L;
	private Integer id;
	private Integer userId;
	private String dateTime;
	private String year;
	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * 是否锁定记录, 锁定： yes
	 */
	private String lockedRecordYn;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getLockedRecordYn() {
		return lockedRecordYn;
	}

	public void setLockedRecordYn(String lockedRecordYn) {
		this.lockedRecordYn = lockedRecordYn;
	}

}
