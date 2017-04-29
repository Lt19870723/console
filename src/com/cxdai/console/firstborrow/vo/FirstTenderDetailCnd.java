package com.cxdai.console.firstborrow.vo;

import java.io.Serializable;

import com.cxdai.console.common.page.BaseCnd;

/**
 * <p>
 * Description:优先投标封装查询条件<br />
 * </p>
 * 
 * @title FirstTenderDetailCnd.java
 * @package com.cxdai.console.first.vo
 * @author justin.xu
 * @version 0.1 2014年7月22日
 */
public class FirstTenderDetailCnd extends BaseCnd implements Serializable {

	private static final long serialVersionUID = 4033975705063158765L;
	/**
	 * 主键id
	 */
	private Integer id;
	/**
	 * 优先投标计划ID
	 */
	private Integer firstBorrowId;
	/**
	 * 用户Id
	 */
	private Integer userId;
	/**
	 * 状态
	 */
	private Integer status;
	/**
	 * 版本号
	 */
	private String version;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFirstBorrowId() {
		return firstBorrowId;
	}

	public void setFirstBorrowId(Integer firstBorrowId) {
		this.firstBorrowId = firstBorrowId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
