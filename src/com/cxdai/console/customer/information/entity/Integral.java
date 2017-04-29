/**
 * 
 */
package com.cxdai.console.customer.information.entity;

import java.io.Serializable;

/**
 * <p>
 * Description:积分等级<br />
 * </p>
 * 
 * @title Integral.java
 * @package com.base.entity
 * @author justin.xu
 * @version 0.1 2014年4月16日
 */
public class Integral implements Serializable {
	private static final long serialVersionUID = 763611187658246633L;
	private Integer id;
	private Integer userId;
	/**
	 * 信用等级
	 */
	private String creditLevel;
	/**
	 * 信用积分
	 */
	private Integer creditIntegral;
	/**
	 * 投资等级
	 */
	private String investLevel;
	/**
	 * 投资积分
	 */
	private Integer investIntegral;
	/**
	 * 可兑换积分数量
	 */
	private Integer integral;

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

	public String getCreditLevel() {
		return creditLevel;
	}

	public void setCreditLevel(String creditLevel) {
		this.creditLevel = creditLevel;
	}

	public Integer getCreditIntegral() {
		return creditIntegral;
	}

	public void setCreditIntegral(Integer creditIntegral) {
		this.creditIntegral = creditIntegral;
	}

	public String getInvestLevel() {
		return investLevel;
	}

	public void setInvestLevel(String investLevel) {
		this.investLevel = investLevel;
	}

	public Integer getInvestIntegral() {
		return investIntegral;
	}

	public void setInvestIntegral(Integer investIntegral) {
		this.investIntegral = investIntegral;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}
}
