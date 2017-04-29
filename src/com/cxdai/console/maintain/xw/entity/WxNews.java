package com.cxdai.console.maintain.xw.entity;

/**
 * 图文资源实体用于推送封装
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title WxNews.java
 * @package com.cxdai.console.wxpush.vo
 * @author Wang Bo
 * @version 0.1 2015年1月20日
 */
public class WxNews {
	private String title;
	private String description;
	private String picurl;
	private String url;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
