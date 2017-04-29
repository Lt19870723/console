package com.cxdai.console.system.vo;

import java.io.Serializable;

import com.cxdai.console.common.page.BaseCnd;

/**
 * 
 * <p>
 * Description:IP白名单<br />
 * </p>
 * @title IPWhileList.java
 * @package com.cxdai.base.entity 
 * @author yangshijin
 * @version 0.1 2014年7月2日
 */
public class IPWhileListCnd extends BaseCnd implements Serializable{
	private static final long serialVersionUID = 6238016681818780346L;

	/** 主键id */
	private Integer id;
	
	/** ip **/
	private String ip;
	
	/** 访问接口类型(0:全部接口，1：网贷天眼接口，2：网贷之家接口)**/
	private Integer accessType;
	
	/** 公司名称 **/
	private String company;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getAccessType() {
		return accessType;
	}

	public void setAccessType(Integer accessType) {
		this.accessType = accessType;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

}
