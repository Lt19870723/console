package com.cxdai.console.borrow.approve.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * Description:还款对应的待收统计信息<br />
 * </p>
 * 
 * @title CollectionRepayInfoVo.java
 * @package com.cxdai.portal.invest.vo
 * @author justin.xu
 * @version 0.1 2015年2月25日
 */
public class CollectionRepayInfoVo implements Serializable {

	private static final long serialVersionUID = 4681705261694221077L;
	/**
	 * 应收总额
	 */
	private BigDecimal repayAccountTotal;
	/**
	 * 应收人数
	 */
	private Integer collectionPersons;

	public BigDecimal getRepayAccountTotal() {
		return repayAccountTotal;
	}

	public void setRepayAccountTotal(BigDecimal repayAccountTotal) {
		this.repayAccountTotal = repayAccountTotal;
	}

	public Integer getCollectionPersons() {
		return collectionPersons;
	}

	public void setCollectionPersons(Integer collectionPersons) {
		this.collectionPersons = collectionPersons;
	}

}
