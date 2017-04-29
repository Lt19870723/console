package com.cxdai.console.fix.vo;

import java.math.BigDecimal;

import com.cxdai.console.fix.entity.FixTenderSubscribe;


/**
 * 
 * <p>
 * Description:定期宝投标转让认购表<br />
 * </p>
 * 
 * @title FixTenderSubscribeVo.java
 * @package com.cxdai.face.fix.vo
 * @author 朱泳霖
 * @version 0.1 2015年6月30日
 */
public class FixTenderSubscribeVo extends FixTenderSubscribe {

	private static final long serialVersionUID = 2788985301272633953L;

	/**
	 * 转让价格
	 */
	private BigDecimal accountReal;

	/**
	 * 购买总和
	 */
	private BigDecimal accountSum;

	/**
	 * 借款期限
	 */
	private Integer timeLimit;

	/**
	 * 还款方式
	 */
	private Integer style;

	/**
	 * 年利率
	 */
	private BigDecimal apr;

	public BigDecimal getAccountReal() {
		return accountReal;
	}

	public void setAccountReal(BigDecimal accountReal) {
		this.accountReal = accountReal;
	}

	public BigDecimal getAccountSum() {
		return accountSum;
	}

	public void setAccountSum(BigDecimal accountSum) {
		this.accountSum = accountSum;
	}

	public Integer getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}

	public Integer getStyle() {
		return style;
	}

	public void setStyle(Integer style) {
		this.style = style;
	}

	public BigDecimal getApr() {
		return apr;
	}

	public void setApr(BigDecimal apr) {
		this.apr = apr;
	}

}
