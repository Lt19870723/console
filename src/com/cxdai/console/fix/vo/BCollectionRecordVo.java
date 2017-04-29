package com.cxdai.console.fix.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.cxdai.console.base.entity.BCollectionRecord;

/**
 * 
 * <p>
 * Description:待收记录<br />
 * </p>
 * 
 * @title BCollectionRecordVo.java
 * @package com.cxdai.face.fix.vo
 * @author 朱泳霖
 * @version 0.1 2015年7月1日
 */
public class BCollectionRecordVo extends BCollectionRecord implements Serializable {

	private static final long serialVersionUID = -7100330584784625365L;

	/**
	 * 预还金额总和
	 */
	private BigDecimal repayAccountSum;

	/**
	 * 本金总和
	 */
	private BigDecimal capitalSum;

	public BigDecimal getRepayAccountSum() {
		return repayAccountSum;
	}

	public void setRepayAccountSum(BigDecimal repayAccountSum) {
		this.repayAccountSum = repayAccountSum;
	}

	public BigDecimal getCapitalSum() {
		return capitalSum;
	}

	public void setCapitalSum(BigDecimal capitalSum) {
		this.capitalSum = capitalSum;
	}


}
