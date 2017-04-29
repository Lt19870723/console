package com.cxdai.console.lottery.vo;

import com.cxdai.console.common.page.BaseCnd;

public class GoodCnd extends BaseCnd {

	/**
	 * @fields serialVersionUID
	 */

	private static final long serialVersionUID = 1L;

	Integer Id;

	String name;

	Integer type;

	Integer parentId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

}