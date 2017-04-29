package com.cxdai.console.curaccount.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.cxdai.console.curaccount.entity.CurAccount;

public class CurAccountVo extends CurAccount implements Serializable {

	private static final long serialVersionUID = -6903226903535940857L;

	// 查看id
	private Integer id;

	// 用户名
	private String userName;

	// 活期宝资产和
	private BigDecimal sumTotal = new BigDecimal("0");
	// 累计收益金额和
	private BigDecimal sumInTotal = new BigDecimal("0");

	// 昨日收益金额和
	private BigDecimal sumYesterday = new BigDecimal("0");

	/**
	 * @return userName : return the property userName.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            : set the property userName.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return sumTotal : return the property sumTotal.
	 */
	public BigDecimal getSumTotal() {
		return sumTotal;
	}

	/**
	 * @param sumTotal
	 *            : set the property sumTotal.
	 */
	public void setSumTotal(BigDecimal sumTotal) {
		this.sumTotal = sumTotal;
	}

	/**
	 * @return sumInTotal : return the property sumInTotal.
	 */
	public BigDecimal getSumInTotal() {
		return sumInTotal;
	}

	/**
	 * @param sumInTotal
	 *            : set the property sumInTotal.
	 */
	public void setSumInTotal(BigDecimal sumInTotal) {
		this.sumInTotal = sumInTotal;
	}

	/**
	 * @return sumYesterday : return the property sumYesterday.
	 */
	public BigDecimal getSumYesterday() {
		return sumYesterday;
	}

	/**
	 * @param sumYesterday
	 *            : set the property sumYesterday.
	 */
	public void setSumYesterday(BigDecimal sumYesterday) {
		this.sumYesterday = sumYesterday;
	}

	/**
	 * @return id : return the property id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            : set the property id.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

}