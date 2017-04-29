package com.cxdai.console.customer.information.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author WangQianJin
 * @title 业务员
 * @date 2015年8月14日
 */
public class Business implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3944745326268303383L;
	
	/**
	 * 编号
	 */
	private Integer id;
	/**
	 * 用户ID（来源于rocky_member表中ID）
	 */
	private Integer userId;
	/**
	 * 用户名（来源于rocky_member表中USERNAME）
	 */
	private String username;
	/**
	 * 状态（数据字典）
	 */
	private Integer status;
	/**
	 * 添加时间
	 */
	private Date addtime;
	/**
	 * 添加IP
	 */
	private String addip;
	/**
	 * 系统用户ID
	 */
	private Integer adduser;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	public Integer getAdduser() {
		return adduser;
	}
	public void setAdduser(Integer adduser) {
		this.adduser = adduser;
	}
	
}
