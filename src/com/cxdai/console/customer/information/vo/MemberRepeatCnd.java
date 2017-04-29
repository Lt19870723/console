package com.cxdai.console.customer.information.vo;

import java.io.Serializable;

/**
 * <p>
 * Description:会员类查询条件<br />
 * </p>
 * 
 * @title MemberRegisterCnd.java
 * @package com.cxdai.console.account.vo
 * @author justin.xu
 * @version 0.1 2015年1月6日
 */
public class MemberRepeatCnd implements Serializable {
	private static final long serialVersionUID = -5890980057423538506L;
	/**
	 * 主键ID
	 */
	private Integer id;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * email
	 */
	private String email;
	/**
	 * 推荐人用户名
	 */
	private String inviterName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getInviterName() {
		return inviterName;
	}

	public void setInviterName(String inviterName) {
		this.inviterName = inviterName;
	}

}