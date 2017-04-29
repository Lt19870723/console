package com.cxdai.console.maintain.portal.entity;

import java.io.Serializable;

import com.cxdai.console.common.page.BaseCnd;

public class NewsNoticeCnd extends BaseCnd implements Serializable{
	private static final long serialVersionUID = -67496093806755950L;

	/**
	 * 主键ID
	 */
	private Integer id;
	
	/**
	 * 类型【0：网站公告，1：新闻动态，2：行业动态】
	 */
	private Integer type;
	
	/**
	 * 状态【0：正常状态；1：隐藏不显示】
	 */
	private Integer status;
	
	/** 
	 * 标题
	 */
	private String title;
	
	/** 
	 * 开始时间 
	 */
	private String beginTime;
	
	/**
	 * 截至时间
	 */
	private String endTime;
	
	/** 
	 * 点击次数 
	 */
	private Integer hits;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getHits() {
		return hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}
}
