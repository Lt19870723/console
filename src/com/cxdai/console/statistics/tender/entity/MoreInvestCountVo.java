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

/**
 * 复投结果对象
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title MoreInvestCountVo.java
 * @package com.cxdai.console.opration.vo
 * @author ZHUCHEN
 * @version 0.1 2015年1月9日
 */
public class MoreInvestCountVo {
	private Integer times = 0;// 次数
	private Integer num = 0;// 人数

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
}
