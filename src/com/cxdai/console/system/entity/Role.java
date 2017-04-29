package com.cxdai.console.system.entity;

import java.io.Serializable;

/**
 * <p>
 * Description:角色<br />
 * </p>
 * 
 * @title Role.java
 * @package com.cxdai.console.system.entity
 * @author qiongbiao.zhang
 * @version 0.1 2015年3月2日
 */
public class Role implements Serializable {
	private static final long serialVersionUID = -8260133167978457196L;

	private Integer id; // 主键
	private String name; // 名称
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