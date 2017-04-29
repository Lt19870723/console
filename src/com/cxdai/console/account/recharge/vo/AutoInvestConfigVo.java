package com.cxdai.console.account.recharge.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.cxdai.console.base.entity.AutoInvestConfig;



/**
 * 
 * <p>
 * Description:自动投标规则实体类<br />
 * </p>
 * 
 * @title AutoInvestConfigVo.java
 * @package com.cxdai.console.account.vo
 * @author yangshijin
 * @version 0.1 2014年11月28日
 */
public class AutoInvestConfigVo extends AutoInvestConfig implements Serializable {
	private static final long serialVersionUID = -7608517712274876960L;

	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 排名
	 */
	private Integer rownum;

	/**
	 * 可用余额
	 */
	private BigDecimal useMoney;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getRownum() {
		return rownum;
	}

	public void setRownum(Integer rownum) {
		this.rownum = rownum;
	}

	public BigDecimal getUseMoney() {
		return useMoney;
	}

	public void setUseMoney(BigDecimal useMoney) {
		this.useMoney = useMoney;
	}
}
