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
 * 直通车情况统计对象
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title NetValueBorrowVo.java
 * @package com.cxdai.console.opration.vo
 * @author ZHUCHEN
 * @version 0.1 2015年1月9日
 */

public class FirstInfoCountVo {
	private int publishNum;// 直通车发布数量
	private BigDecimal publishMoney = BigDecimal.ZERO;// 直通车发布金额
	private int joinNum;// 参与直通车的人次
	private int joinPeopleNum;// 参与直通车的人数

	public int getPublishNum() {
		return publishNum;
	}

	public void setPublishNum(int publishNum) {
		this.publishNum = publishNum;
	}

	public BigDecimal getPublishMoney() {
		return publishMoney;
	}

	public void setPublishMoney(BigDecimal publishMoney) {
		this.publishMoney = publishMoney;
	}

	public int getJoinNum() {
		return joinNum;
	}

	public void setJoinNum(int joinNum) {
		this.joinNum = joinNum;
	}

	public int getJoinPeopleNum() {
		return joinPeopleNum;
	}

	public void setJoinPeopleNum(int joinPeopleNum) {
		this.joinPeopleNum = joinPeopleNum;
	}
}
