package com.cxdai.console.maintain.cms.entity;

import java.io.Serializable;

import com.cxdai.console.common.page.BaseCnd;

public class CmsPediaEntryCnd extends BaseCnd implements Serializable {

	/**
	 * @fields serialVersionUID
	 */

	private static final long serialVersionUID = 1L;
	private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

}