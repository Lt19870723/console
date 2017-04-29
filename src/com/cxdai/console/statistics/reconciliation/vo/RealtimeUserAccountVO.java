package com.cxdai.console.statistics.reconciliation.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * Description:最新直通车信息统计vo<br />
 * </p>
 * 
 * @title NewThroughTrainInformationStatisticsVO.java
 * @package com.cxdai.console.account.vo
 * @author 邹理享
 * @version 0.1 2015年1月16日
 */
public class RealtimeUserAccountVO implements Serializable {
	private static final long serialVersionUID = 8968171390940932208L;
	/** 用户类型 */
	private String userType;
	/** 用户名 */
	private String userName;
	/** 总资产 */
	private BigDecimal total;
	/** 待还净值 */
	private BigDecimal repayNetMoney;
	/** 实际资产 */
	private BigDecimal actualMoney;
	/** 可用余额 */
	private BigDecimal userMoney;
	/** 冻结金额 */
	private BigDecimal noUserMoney;
	/** 待收总额 */
	private BigDecimal collection;
	/** 可提现金额 */
	private BigDecimal drawMoney;
	/** 受限金额 */
	private BigDecimal noDrawMoney;

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getRepayNetMoney() {
		return repayNetMoney;
	}

	public void setRepayNetMoney(BigDecimal repayNetMoney) {
		this.repayNetMoney = repayNetMoney;
	}

	public BigDecimal getActualMoney() {
		return actualMoney;
	}

	public void setActualMoney(BigDecimal actualMoney) {
		this.actualMoney = actualMoney;
	}

	public BigDecimal getUserMoney() {
		return userMoney;
	}

	public void setUserMoney(BigDecimal userMoney) {
		this.userMoney = userMoney;
	}

	public BigDecimal getNoUserMoney() {
		return noUserMoney;
	}

	public void setNoUserMoney(BigDecimal noUserMoney) {
		this.noUserMoney = noUserMoney;
	}

	public BigDecimal getCollection() {
		return collection;
	}

	public void setCollection(BigDecimal collection) {
		this.collection = collection;
	}

	public BigDecimal getDrawMoney() {
		return drawMoney;
	}

	public void setDrawMoney(BigDecimal drawMoney) {
		this.drawMoney = drawMoney;
	}

	public BigDecimal getNoDrawMoney() {
		return noDrawMoney;
	}

	public void setNoDrawMoney(BigDecimal noDrawMoney) {
		this.noDrawMoney = noDrawMoney;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
