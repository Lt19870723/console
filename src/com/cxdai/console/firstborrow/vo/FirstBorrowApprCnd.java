package com.cxdai.console.firstborrow.vo;

import java.io.Serializable;

/**
 * <p>
 * Description:优先投标计划审核查询条件<br />
 * </p>
 * 
 * @title FirstBorrowApprCnd.java
 * @package com.cxdai.console.first.vo
 * @author justin.xu
 * @version 0.1 2014年7月3日
 */
public class FirstBorrowApprCnd implements Serializable {
	private static final long serialVersionUID = -8485687809062135153L;
	/**
	 * 主键ID
	 */
	private String id;
	/**
	 * 优选计划ID
	 */
	private String firstBorrowId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstBorrowId() {
		return firstBorrowId;
	}

	public void setFirstBorrowId(String firstBorrowId) {
		this.firstBorrowId = firstBorrowId;
	}

}
