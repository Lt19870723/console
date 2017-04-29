package com.cxdai.console.lottery.vo;

import java.math.BigDecimal;

public class UseRecordStatic {


	int chanceSendNum; //抽奖机会发放数量
	int chanceSendedNum;//已抽奖次数
    BigDecimal getSumAwardMoney;//已领奖品总价值
	BigDecimal noGetSumAwardMoney;// 未已领奖品总价值


	public int getChanceSendNum() {
		return chanceSendNum;
	}

	public void setChanceSendNum(int chanceSendNum) {
		this.chanceSendNum = chanceSendNum;
	}

	public int getChanceSendedNum() {
		return chanceSendedNum;
	}

	public void setChanceSendedNum(int chanceSendedNum) {
		this.chanceSendedNum = chanceSendedNum;
	}

	public BigDecimal getGetSumAwardMoney() {
		return getSumAwardMoney;
	}

	public void setGetSumAwardMoney(BigDecimal getSumAwardMoney) {
		this.getSumAwardMoney = getSumAwardMoney;
	}

	public BigDecimal getNoGetSumAwardMoney() {
		return noGetSumAwardMoney;
	}

	public void setNoGetSumAwardMoney(BigDecimal noGetSumAwardMoney) {
		this.noGetSumAwardMoney = noGetSumAwardMoney;
	}


}