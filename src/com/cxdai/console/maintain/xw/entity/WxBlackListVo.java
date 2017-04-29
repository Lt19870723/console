package com.cxdai.console.maintain.xw.entity;

import java.io.Serializable;

/**
 * 
 * <p>
 * Description:黑名单<br />
 * </p>
 * 
 * @title BlackListVo.java
 * @package com.cxdai.console.member.vo
 * @author yangshijin
 * @version 0.1 2015年1月25日
 */
public class WxBlackListVo extends BlackList implements Serializable {

	private static final long serialVersionUID = 821922926532730599L;
	/** 用户名 */
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
