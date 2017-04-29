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
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 推广统计对象
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title NetValueBorrowVo.java
 * @package com.cxdai.console.opration.vo
 * @author ZHUCHEN
 * @version 0.1 2015年1月9日
 */

public class RecommendCountVo {
	private String sourceName;// 用户来源
	private int registNum;// 注册人数
	private int realNameNum;// 实名数量
	private int vipNum;// vip数量
	private int rechargeNum;// 充值人数
	private int investNum;// 投资人数
	private int lostNum;// 流失人数
	private BigDecimal investMoney = BigDecimal.ZERO;// 投资金额
	private BigDecimal rechargeMoney = BigDecimal.ZERO;// 充值金额
	private Date sourceFrom;// 来源渠道开始时间
	private Date sourceEndTime;// 来源渠道结束时间
	private String remark;// 来源渠道停止备注

	public Date getSourceEndTime() {
		return sourceEndTime;
	}

	public void setSourceEndTime(Date sourceEndTime) {
		this.sourceEndTime = sourceEndTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public int getRegistNum() {
		return registNum;
	}

	public void setRegistNum(int registNum) {
		this.registNum = registNum;
	}

	public int getRealNameNum() {
		return realNameNum;
	}

	public void setRealNameNum(int realNameNum) {
		this.realNameNum = realNameNum;
	}

	public int getVipNum() {
		return vipNum;
	}

	public void setVipNum(int vipNum) {
		this.vipNum = vipNum;
	}

	public int getRechargeNum() {
		return rechargeNum;
	}

	public void setRechargeNum(int rechargeNum) {
		this.rechargeNum = rechargeNum;
	}

	public int getInvestNum() {
		return investNum;
	}

	public void setInvestNum(int investNum) {
		this.investNum = investNum;
	}

	public int getLostNum() {
		return lostNum;
	}

	public void setLostNum(int lostNum) {
		this.lostNum = lostNum;
	}

	public BigDecimal getInvestMoney() {
		return investMoney;
	}

	public void setInvestMoney(BigDecimal investMoney) {
		this.investMoney = investMoney;
	}

	public BigDecimal getRechargeMoney() {
		return rechargeMoney;
	}

	public void setRechargeMoney(BigDecimal rechargeMoney) {
		this.rechargeMoney = rechargeMoney;
	}

	public Date getSourceFrom() {
		return sourceFrom;
	}

	public void setSourceFrom(Date sourceFrom) {
		this.sourceFrom = sourceFrom;
	}

	public String getSourceFromStr() {
		if (sourceFrom == null) {
			return null;
		} else {
			return new SimpleDateFormat("yyyy-MM-dd").format(sourceFrom);
		}

	}

	public String getSourceEndTimeStr() {
		if (sourceEndTime == null) {
			return null;
		} else {
			return new SimpleDateFormat("yyyy-MM-dd").format(sourceEndTime);
		}

	}
}
