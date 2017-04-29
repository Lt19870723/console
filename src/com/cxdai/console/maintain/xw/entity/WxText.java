package com.cxdai.console.maintain.xw.entity;

import java.util.Date;

/**
 * 文本实体
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title WxText.java
 * @package com.cxdai.console.wxpush.vo
 * @author Wang Bo
 * @version 0.1 2015年1月20日
 */
public class WxText {
	private String text;
	private int removeTag;
	private int pushNum;
	private Date pushTime;
	private int id;
	private int createUser;
	private Date createTime;
	private int status;
	private Date updateTime;
	private int updateUser;

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(int updateUser) {
		this.updateUser = updateUser;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCreateUser() {
		return createUser;
	}

	public void setCreateUser(int createUser) {
		this.createUser = createUser;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getRemoveTag() {
		return removeTag;
	}

	public void setRemoveTag(int removeTag) {
		this.removeTag = removeTag;
	}

	public int getPushNum() {
		return pushNum;
	}

	public void setPushNum(int pushNum) {
		this.pushNum = pushNum;
	}

	public Date getPushTime() {
		return pushTime;
	}

	public void setPushTime(Date pushTime) {
		this.pushTime = pushTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
