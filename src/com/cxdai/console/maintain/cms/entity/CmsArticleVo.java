package com.cxdai.console.maintain.cms.entity;

import com.cxdai.console.util.DateUtils;

public class CmsArticleVo extends CmsArticle {
	private String channelName;

	private String createTimeStr;



	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getCreateTimeStr() {
		if (getCreateTime() != null) {
			return DateUtils.format(getUpdateTime(), DateUtils.YMD_HMS);
		}
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}


}