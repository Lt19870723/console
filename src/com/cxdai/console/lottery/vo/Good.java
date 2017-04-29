package com.cxdai.console.lottery.vo;

import com.cxdai.console.lottery.entity.BaseGood;


public class Good extends BaseGood {

	// 指定活动中奖概率
	private String activtyAwardChanceStr;
	// 子概率
	private String chirldChanceStr;
	// 子名称
	private String chirldNameStr;

	private String realGoodName;


	public String getActivtyAwardChanceStr() {
		return activtyAwardChanceStr;
	}

	public void setActivtyAwardChanceStr(String activtyAwardChanceStr) {
		this.activtyAwardChanceStr = activtyAwardChanceStr;
	}

	public String getChirldChanceStr() {
		return chirldChanceStr;
	}

	public void setChirldChanceStr(String chirldChanceStr) {
		this.chirldChanceStr = chirldChanceStr;
	}

	public String getChirldNameStr() {
		return chirldNameStr;
	}

	public void setChirldNameStr(String chirldNameStr) {
		this.chirldNameStr = chirldNameStr;
	}

	public String getRealGoodName() {
		return realGoodName;
	}

	public void setRealGoodName(String realGoodName) {
		this.realGoodName = realGoodName;
	}


}