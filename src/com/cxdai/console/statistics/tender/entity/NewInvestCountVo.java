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
 * 新增投资人数相关统计结果对象
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title NewInvestCountVo.java
 * @package com.cxdai.console.opration.vo
 * @author ZHUCHEN
 * @version 0.1 2015年1月9日
 */
public class NewInvestCountVo {

	private int num;// 新增投资人数
	private BigDecimal totalMoney = BigDecimal.ZERO;// 新增投资总额

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}
}
