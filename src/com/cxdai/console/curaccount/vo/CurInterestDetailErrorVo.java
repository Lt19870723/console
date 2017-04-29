package com.cxdai.console.curaccount.vo;

import java.io.Serializable;

/**
 * 
 * <p>
 * Description:活期生息有误的用户Vo<br />
 * </p>
 * 
 * @title CurInterestDetailErrrorVo.java
 * @package com.cxdai.console.curaccount.vo
 * @author YangShiJin
 * @version 0.1 2015年5月27日
 */
public class CurInterestDetailErrorVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userName;

	private Integer userId;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
