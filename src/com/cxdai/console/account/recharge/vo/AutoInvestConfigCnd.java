package com.cxdai.console.account.recharge.vo;

import java.io.Serializable;

import com.cxdai.console.common.page.BaseCnd;

/**
 * 
 * <p>
 * Description:自动投标规则匹配条件类<br />
 * </p>
 * 
 * @title AutoInvestConfigCnd.java
 * @package com.cxdai.console.account.vo
 * @author yangshijin
 * @version 0.1 2014年11月28日
 */
public class AutoInvestConfigCnd extends BaseCnd implements Serializable {
	private static final long serialVersionUID = 2974034919186861148L;
	/**
	 * 用户Id
	 */
	private Integer user_id;

	/**
	 * 投标方式
	 */
	private String autoTenderType;

	/**
	 * 状态
	 */
	private String status;

	/**
	 * 用户名
	 */
	private String username;

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getAutoTenderType() {
		return autoTenderType;
	}

	public void setAutoTenderType(String autoTenderType) {
		this.autoTenderType = autoTenderType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
