package com.cxdai.console.system.vo;

import com.cxdai.console.system.entity.Permission;

public class PermissionVo extends Permission {
	private static final long serialVersionUID = 376028111042989846L;

	private String resourcesIds;

	public String getResourcesIds() {
		return resourcesIds;
	}

	public void setResourcesIds(String resourcesIds) {
		this.resourcesIds = resourcesIds;
	}
}