package com.cxdai.console.statistics.tender.entity;

import java.math.BigDecimal;

/**
 * <p>
 * Description:标统计数据<br />
 * </p>
 * 
 * @title BorrowDataInfoVo.java
 * @package com.cxdai.console.opration.vo
 * @author justin.xu
 * @version 0.1 2014年12月24日
 */
public class BorrowDataInfoVo {
	/**
	 * 投标类型
	 */
	private Integer tenderType;
	/**
	 * 标金额
	 */
	private BigDecimal account;

	public Integer getTenderType() {
		return tenderType;
	}

	public void setTenderType(Integer tenderType) {
		this.tenderType = tenderType;
	}

	public BigDecimal getAccount() {
		return account;
	}

	public void setAccount(BigDecimal account) {
		this.account = account;
	}
}