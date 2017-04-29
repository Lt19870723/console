package com.cxdai.console.maintain.portal.entity;

import java.io.Serializable;

/**
 * 
 * <p>
 * Description:新闻公告类<br />
 * </p>
 * 
 * @title NewsNotice.java
 * @package com.cxdai.base.entity
 * @author yangshijin
 * @version 0.1 2014年4月23日
 */
public class NewsNotice implements Serializable {
	private static final long serialVersionUID = -4675853866079693131L;

	/**
	 * 主键ID
	 */
	private Integer id;
	/**
	 * 类型【0：网站公告，1：新闻动态，2：行业动态】
	 */
	private int type;
	/**
	 * 状态【0：正常状态；1：隐藏不显示】
	 */
	private int status;
	/**
	 * 点击次数
	 */
	private int hits;
	/**
	 * 来源出处
	 */
	private String source;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 文章作者
	 */
	private String author;
	/**
	 * 正文
	 */
	private String context;
	/**
	 * 发布时间
	 */
	private String addtime;
	/**
	 * 发布IP
	 */
	private String addip;
	/**
	 * 维护更新时间
	 */
	private String updatetime;
	/**
	 * 维护更新IP
	 */
	private String upip;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getAddip() {
		return addip;
	}

	public void setAddip(String addip) {
		this.addip = addip;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getUpip() {
		return upip;
	}

	public void setUpip(String upip) {
		this.upip = upip;
	}
}