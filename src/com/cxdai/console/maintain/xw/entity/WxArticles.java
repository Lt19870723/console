package com.cxdai.console.maintain.xw.entity;

import java.util.Date;

/**
 * 图文实体
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title WxArticles.java
 * @package com.cxdai.console.wxpush.vo
 * @author Wang Bo
 * @version 0.1 2015年1月20日
 */
public class WxArticles {
	private String title;
	private String description;
	private String picurl;
	private String url;
	private Integer parentId;
	private int sort;
	private Date createTime;
	private int createUser;
	private Date updateTime;
	private int updateUser;
	private int pushNum;
	private int status;
	private Date pushTime;
	private Integer id;
	private int removeTag;
	private int type;
	private String ip;
	private String serialNnumber;
	private int pushUser;
	private String mac;
	private int pushNumTotal;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getSerialNnumber() {
		return serialNnumber;
	}

	public void setSerialNnumber(String serialNnumber) {
		this.serialNnumber = serialNnumber;
	}

	public int getPushUser() {
		return pushUser;
	}

	public void setPushUser(int pushUser) {
		this.pushUser = pushUser;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public int getPushNumTotal() {
		return pushNumTotal;
	}

	public void setPushNumTotal(int pushNumTotal) {
		this.pushNumTotal = pushNumTotal;
	}

	public int getRemoveTag() {
		return removeTag;
	}

	public void setRemoveTag(int removeTag) {
		this.removeTag = removeTag;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public int getCreateUser() {
		return createUser;
	}

	public void setCreateUser(int createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setPushTime(Date pushTime) {
		this.pushTime = pushTime;
	}

	public int getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(int updateUser) {
		this.updateUser = updateUser;
	}

	public void setPushNum(int pushNum) {
		this.pushNum = pushNum;
	}

	public int getPushNum() {
		return pushNum;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

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

	public Date getPushTime() {
		return pushTime;
	}

	@Override
	public String toString() {
		return "WxArticles [title=" + title + ", description=" + description + ", picurl=" + picurl + ", url=" + url + ", parentId=" + parentId + ", sort=" + sort + ", createTime=" + createTime + ", createUser=" + createUser + ", updateTime=" + updateTime + ", updateUser=" + updateUser + ", pushNum=" + pushNum + ", status=" + status + ", pushTime=" + pushTime + ", id=" + id + ", removeTag=" + removeTag + ", type=" + type + "]";
	}

}
