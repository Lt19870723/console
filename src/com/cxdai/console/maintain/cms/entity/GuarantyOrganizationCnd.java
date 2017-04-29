package com.cxdai.console.maintain.cms.entity;

import java.io.Serializable;

import com.cxdai.console.common.page.BaseCnd;

public class GuarantyOrganizationCnd extends BaseCnd implements Serializable {

	/**
	 * @fields serialVersionUID
	 */

	private static final long serialVersionUID = -4095883581130648921L;

	private Integer id; // 自增主键
	private String name; // 推荐机构的名称

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

}