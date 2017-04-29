package com.cxdai.console.maintain.xw.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 封装查询实体
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title SearchNews.java
 * @package com.cxdai.console.wxpush.vo
 * @author Wang Bo
 * @version 0.1 2015年1月20日
 */
public class SearchNews implements Serializable {
	/**
	 * @fields serialVersionUID
	 */

	private static final long serialVersionUID = 1L;
	private String title;
	private Long createTime;
	private Long createTime2;
	private Date pushTime;
	private Date pushTime2;
	private int begin;
	private Integer pageSize;
	private String isPush;
	private Integer pushType;

	public Integer getPushType() {
		return pushType;
	}

	public void setPushType(Integer pushType) {
		this.pushType = pushType;
	}

	public Date getPushTime() {
		return pushTime;
	}

	public void setPushTime(Date pushTime) {
		this.pushTime = pushTime;
	}

	public Date getPushTime2() {
		return pushTime2;
	}

	public void setPushTime2(Date pushTime2) {
		this.pushTime2 = pushTime2;
	}

	public String getIsPush() {
		return isPush;
	}

	public void setIsPush(String isPush) {
		this.isPush = isPush;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCreateTime2() {
		return createTime2;
	}

	public void setCreateTime2(Long createTime2) {
		this.createTime2 = createTime2;
	}

}
