package com.cxdai.console.firstborrow.vo;

import java.io.Serializable;
import java.util.Date;

import com.cxdai.console.common.page.BaseCnd;
import com.cxdai.console.util.DateUtils;

/**
 * <p>
 * Description:直通车查询条件<br />
 * </p>
 * 
 * @title FirstBorrowCnd.java
 * @package com.cxdai.console.first.vo
 * @author justin.xu
 * @version 0.1 2014年7月2日
 */
public class FirstBorrowCnd extends BaseCnd implements Serializable {
	private static final long serialVersionUID = 8387186801624482411L;
	/**
	 * 主键id
	 */
	private String id;
	/**
	 * 标题
	 */
	private String name;
	/**
	 * 发布开始时间
	 */
	private Date beginTime;
	/**
	 * 发布结束时间
	 */
	private Date endTime;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 添加优先计划验证字段
	 */
	private String validateAdd;
	/**
	 * 排序sql
	 */
	private String orderSql;
	/**
	 * 版本号
	 */
	private String version;
	/**
	 * 是否锁定记录, 锁定： yes
	 */
	private String lockedRecordYn;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = DateUtils.parse(beginTime, DateUtils.YMD_DASH);
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = DateUtils.parse(endTime, DateUtils.YMD_DASH);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getValidateAdd() {
		return validateAdd;
	}

	public void setValidateAdd(String validateAdd) {
		this.validateAdd = validateAdd;
	}

	public String getOrderSql() {
		return orderSql;
	}

	public void setOrderSql(String orderSql) {
		this.orderSql = orderSql;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getLockedRecordYn() {
		return lockedRecordYn;
	}

	public void setLockedRecordYn(String lockedRecordYn) {
		this.lockedRecordYn = lockedRecordYn;
	}

}
