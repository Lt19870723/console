package com.cxdai.console.system.entity;

import java.io.Serializable;

/**
 * <p>
 * Description:页面元素<br />
 * </p>
 * 
 * @title Element.java
 * @package com.cxdai.console.system.entity
 * @author qiongbiao.zhang
 * @version 0.1 2015年3月2日
 */
public class Element implements Serializable {
	private static final long serialVersionUID = 5400371886381432000L;

	private Integer id; // 主键
	private String name; // 元素名称
	private String desc; // 描述
	private Integer status; // 状态(0:启用,1:禁用)
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}