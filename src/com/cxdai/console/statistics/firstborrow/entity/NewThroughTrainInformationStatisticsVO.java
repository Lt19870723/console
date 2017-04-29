package com.cxdai.console.statistics.firstborrow.entity;

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
public class NewThroughTrainInformationStatisticsVO {

	BigDecimal stockTotalAmount; // 存量
	BigDecimal getoffTotalAmount;// 下车
	BigDecimal publishTotalAmount;// 发布
	BigDecimal newgetonTotalAmount;// 上车
	/** 直通车可用余额 */
	private BigDecimal firstUseMoney;

	public BigDecimal getStockTotalAmount() {
		return stockTotalAmount;
	}

	public void setStockTotalAmount(BigDecimal stockTotalAmount) {
		this.stockTotalAmount = stockTotalAmount;
	}

	public BigDecimal getGetoffTotalAmount() {
		return getoffTotalAmount;
	}

	public void setGetoffTotalAmount(BigDecimal getoffTotalAmount) {
		this.getoffTotalAmount = getoffTotalAmount;
	}

	public BigDecimal getPublishTotalAmount() {
		return publishTotalAmount;
	}

	public void setPublishTotalAmount(BigDecimal publishTotalAmount) {
		this.publishTotalAmount = publishTotalAmount;
	}

	public BigDecimal getNewgetonTotalAmount() {
		return newgetonTotalAmount;
	}

	public void setNewgetonTotalAmount(BigDecimal newgetonTotalAmount) {
		this.newgetonTotalAmount = newgetonTotalAmount;
	}

	public BigDecimal getFirstUseMoney() {
		return firstUseMoney;
	}

	public void setFirstUseMoney(BigDecimal firstUseMoney) {
		this.firstUseMoney = firstUseMoney;
	}

}
