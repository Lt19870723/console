package com.cxdai.console.curaccount.vo;

import java.io.Serializable;

import com.cxdai.console.curaccount.entity.CurIn;

public class CurInVo extends CurIn implements Serializable {

	private static final long serialVersionUID = -6903226903535940857L;

	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}