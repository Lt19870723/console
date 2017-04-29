package com.cxdai.console.customer.information.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author WangQianJin
 * @title 借款标与业务员关联表
 * @date 2015年8月14日
 */
public class BorrowBusiness implements Serializable{

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 7470509345633046613L;
	
	/**
	 * 编号
	 */
	private Integer id;	
	/**
	 * 借款标ID
	 */
	private Integer borrowId;
	/**
	 * 用户ID（来源于rocky_member表中ID）
	 */
	private Integer userId;
	/**
	 * 用户名（来源于rocky_member表中USERNAME）
	 */
	private String username;
	/**
	 * 添加时间
	 */
	private Date addtime;
	/**
	 * 添加IP
	 */
	private String addip;
	/**
	 * 平台来源(1：网页 2：微信 3：安卓端 4： IOS端)
	 */
	private Integer platform;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBorrowId() {
		return borrowId;
	}
	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	public String getAddip() {
		return addip;
	}
	public void setAddip(String addip) {
		this.addip = addip;
	}
	public Integer getPlatform() {
		return platform;
	}
	public void setPlatform(Integer platform) {
		this.platform = platform;
	}
	
	
	
}
