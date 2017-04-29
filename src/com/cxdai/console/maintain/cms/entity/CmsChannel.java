package com.cxdai.console.maintain.cms.entity;

import java.util.Date;

public class CmsChannel {
    private Integer id;

    private String urlCode;

    private String name;

    private String desc;

    private String icon;

	private Integer order = 0;

	private Integer parentId;

    private String seoTitle;

    private String seoDescription;

    private String seoKeywords;

	private Integer status = 0;

	private Integer updateBy = 0;

	private Date updateTime = new Date();

	private String parentChannelName;

	private Integer isHidden = 0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrlCode() {
        return urlCode;
    }

    public void setUrlCode(String urlCode) {
        this.urlCode = urlCode == null ? null : urlCode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

	public Integer getOrder() {
        return order;
    }

	public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getSeoTitle() {
        return seoTitle;
    }

    public void setSeoTitle(String seoTitle) {
        this.seoTitle = seoTitle == null ? null : seoTitle.trim();
    }

    public String getSeoDescription() {
        return seoDescription;
    }

    public void setSeoDescription(String seoDescription) {
        this.seoDescription = seoDescription == null ? null : seoDescription.trim();
    }

    public String getSeoKeywords() {
        return seoKeywords;
    }

    public void setSeoKeywords(String seoKeywords) {
        this.seoKeywords = seoKeywords == null ? null : seoKeywords.trim();
    }

	public Integer getStatus() {
        return status;
    }

	public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public String getParentChannelName() {
		return parentChannelName;
	}

	public void setParentChannelName(String parentChannelName) {
		this.parentChannelName = parentChannelName;
	}

	public Integer getIsHidden() {
		return isHidden;
	}

	public void setIsHidden(Integer isHidden) {
		this.isHidden = isHidden;
	}

}