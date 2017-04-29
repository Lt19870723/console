/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title NetValueBorrowVo.java
 * @package com.cxdai.console.opration.vo 
 * @author ZHUCHEN
 * @version 0.1 2015年1月9日
 */
package com.cxdai.console.statistics.tender.entity;

import java.math.BigDecimal;

/**
 * 净值标统计对象
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title NetValueBorrowVo.java
 * @package com.cxdai.console.opration.vo
 * @author ZHUCHEN
 * @version 0.1 2015年1月9日
 */
public class NetValueBorrowCountVo {

	private int netValueCount;// 成交净值数量
	private BigDecimal netValueAccount = BigDecimal.ZERO;// 净值标成交金额
	private int publishCount;// 发布净值数量

	public int getNetValueCount() {
		return netValueCount;
	}

	public void setNetValueCount(int netValueCount) {
		this.netValueCount = netValueCount;
	}

	public BigDecimal getNetValueAccount() {
		return netValueAccount;
	}

	public void setNetValueAccount(BigDecimal netValueAccount) {
		this.netValueAccount = netValueAccount;
	}

	public int getPublishCount() {
		return publishCount;
	}

	public void setPublishCount(int publishCount) {
		this.publishCount = publishCount;
	}
}
