package com.cxdai.console.customer.information.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Description:在线人数记录<br />
 * </p>
 * 
 * @title OnlineCounter.java
 * @package com.cxdai.base.entity
 * @author justin.xu
 * @version 0.1 2014年4月18日
 */
public class OnlineCounter implements Serializable {

	private static final long serialVersionUID = -8005802218725609268L;

	/** 主键 */
	private Integer id;

	/** 用户ID */
	private Integer userId;

	/** 用户登录名 */
	private String username;

	/** 记录创建时间 */
	private Date addtime;

	/** 登录时间 */
	private Date logintime;

	/** 最后一次存在时间 */
	private Date lastexisttime;

	/** SESSIONID */
	private String sessionid;

	/** 退出时间 */
	private Date logouttime;

	/** SESSION失效时间 */
	private Date sessionouttime;

	/** 最后一次更新时间 */
	private Date lastupdatetime;

	/** 备注信息 */
	private String remark;

	/** 客户端IP */
	private String addip;

	/** 前台还是后台 :0表示前台;1表示后台 */
	private String system;

	/** 国家或省份 */
	private String province;

	/** 城市 */
	private String city;

	/** 区域 */
	private String area;

	/** 平台来源 1：官网 2、微信，即用户登录的客户端 */
	private Integer platform;

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

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

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Date getLogintime() {
		return logintime;
	}

	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}

	public Date getLastexisttime() {
		return lastexisttime;
	}

	public void setLastexisttime(Date lastexisttime) {
		this.lastexisttime = lastexisttime;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public Date getLogouttime() {
		return logouttime;
	}

	public void setLogouttime(Date logouttime) {
		this.logouttime = logouttime;
	}

	public Date getSessionouttime() {
		return sessionouttime;
	}

	public void setSessionouttime(Date sessionouttime) {
		this.sessionouttime = sessionouttime;
	}

	public Date getLastupdatetime() {
		return lastupdatetime;
	}

	public void setLastupdatetime(Date lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAddip() {
		return addip;
	}

	public void setAddip(String addip) {
		this.addip = addip;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

}