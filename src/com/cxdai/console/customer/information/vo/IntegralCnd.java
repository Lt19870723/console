package com.cxdai.console.customer.information.vo;

import java.io.Serializable;

/**
 * <p>
 * Description:积分等级查询条件<br />
 * </p>
 * 
 * @title IntegralCnd.java
 * @package com.cxdai.console.member.vo
 * @author justin.xu
 * @version 0.1 2014年4月28日
 */
public class IntegralCnd implements Serializable {
	private static final long serialVersionUID = 763611187658246633L;
	private Integer id;
	private Integer userId;

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

}
