package com.cxdai.console.maintain.xw.entity;

import java.util.Date;

/**
 * 显示的vo
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ShowList.java
 * @package com.cxdai.console.wxpush.vo
 * @author Wang Bo
 * @version 0.1 2015年1月20日
 */
public class ShowList {
	private int id;
	private String title;
	private String picurl;
	private Integer status;
	private String showStatus;
	private Date pushTime;
	private String showPushTime;
	private Integer pushNum;
	private String url;
	private String text;
	private Integer type;
	private Integer pushNumTotal;
	private String textLimit;
	private String showText;

	public String getShowText() {
		return showText;
	}

	public void setShowText(String showText) {
		this.showText = showText;
	}

	public Integer getPushNumTotal() {
		return pushNumTotal;
	}

	public void setPushNumTotal(Integer pushNumTotal) {
		this.pushNumTotal = pushNumTotal;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getShowPushTime() {
		return showPushTime;
	}

	public void setShowPushTime(String showPushTime) {
		this.showPushTime = showPushTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getShowStatus() {
		if (this.status == 1)
			return "已推送";
		else if (this.status == 3)
			return "推送中";
		return "未推送";
	}

	public void setShowStatus(String showStatus) {
		this.showStatus = showStatus;
	}

	public Date getPushTime() {
		return pushTime;
	}

	public void setPushTime(Date pushTime) {
		this.pushTime = pushTime;
	}

	public Integer getPushNum() {
		return pushNum;
	}

	public void setPushNum(Integer pushNum) {
		this.pushNum = pushNum;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public String getTextLimit() {
		if (this.text != null) {
			if (this.text.length() > 20) {
				textLimit = this.text.substring(0, 20) + "...";
			} else {
				textLimit = text;
			}
		}
		return textLimit;
	}

	public void setTextLimit(String textLimit) {
		this.textLimit = textLimit;
	}

}
