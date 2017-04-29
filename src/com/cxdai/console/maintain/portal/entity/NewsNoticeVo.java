package com.cxdai.console.maintain.portal.entity;

import java.io.Serializable;

import com.cxdai.console.util.DateUtils;

/**
 * <p>
 * Description:会员类Vo<br />
 * </p>
 * 
 * @title MemberVo.java
 * @package com.cxdai.member.vo
 * @author justin.xu
 * @version 0.1 2014年4月11日
 */
public class NewsNoticeVo implements Serializable {
	private static final long serialVersionUID = -5890980057423538506L;
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

	/**
	 * 添加时间字符串
	 */
	private String addtimeStr;
	private String statusStr;
	private String typeStr;
	private String updatetimeStr;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getAddtimeStr() {
		if (addtime != null && !addtime.equals("")) {
			return DateUtils.timeStampToDate(addtime, DateUtils.YMD_DASH);
		}
		return addtimeStr;
	}

	public void setAddtimeStr(String addtimeStr) {
		this.addtimeStr = addtimeStr;
	}

	public String getStatusStr() {
		if (status == 0) {
			return "显示 ";
		}
		return "隐藏";
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public String getTypeStr() {
		if (type == 0) {
			return "网站公告";
		} else if (type == 1) {
			return "新闻动态";
		}
		return "行业动态";
	}

	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}

	public String getUpdatetimeStr() {
		if (updatetime != null && !updatetime.equals("")) {
			return DateUtils.timeStampToDate(updatetime, DateUtils.YMD_DASH);
		}
		return updatetimeStr;
	}

	public void setUpdatetimeStr(String updatetimeStr) {
		this.updatetimeStr = updatetimeStr;
	}
}