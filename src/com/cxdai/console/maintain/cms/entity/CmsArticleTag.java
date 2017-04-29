package com.cxdai.console.maintain.cms.entity;

public class CmsArticleTag extends CmsArticleTagKey {

	public CmsArticleTag() {
		super();

	}

	public CmsArticleTag(Integer articleId, Integer tagId, Integer channelId) {
		super();
		setTagId(tagId);
		setArticleId(articleId);
		this.channelId = channelId;
	}

	private Integer channelId;

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

}