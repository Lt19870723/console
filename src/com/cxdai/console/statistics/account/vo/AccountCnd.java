package com.cxdai.console.statistics.account.vo;

import java.io.Serializable;

import com.cxdai.console.common.page.BaseCnd;


/**
 * <p>
 * Description:账户类查询条件<br />
 * </p>
 * 
 * @title AccountCnd.java
 * @package com.cxdai.console.account.vo
 * @author justin.xu
 * @version 0.1 2014年4月25日
 */
public class AccountCnd extends BaseCnd implements Serializable {

	private static final long serialVersionUID = -3531851612222097637L;
	private Integer id;
	private Integer userId;
	private String username;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
