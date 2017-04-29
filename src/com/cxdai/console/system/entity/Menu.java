package com.cxdai.console.system.entity;

import java.io.Serializable;

/**
 * <p>
 * Description:菜单<br />
 * </p>
 * 
 * @title Menu.java
 * @package com.cxdai.console.system.entity
 * @author qiongbiao.zhang
 * @version 0.1 2015年3月2日
 */
public class Menu implements Serializable {
	private static final long serialVersionUID = 1532510603937109402L;

	private Integer id; // 主键
	private String name; // 名称
	private String url; // url
	private String desc; // 描述
	private Integer order; // 排序
	private Integer level; // 菜单层级
	private Integer status; // 状态(0:启用,1:禁用)
	private Integer pid; // 父菜单ID
	private String remark; // 备注

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}