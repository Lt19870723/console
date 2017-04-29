package com.cxdai.console.finance.virement.vo;

import java.math.BigDecimal;

/***
 * 
 * <p>
 * Description:充值返回实体类<br />
 * </p>
 * 
 * @title RechargeAnalysisVo.java
 * @package com.cxdai.console.finance.virement.vo
 * @author Kimmyzhao
 * @version 0.1 2016年4月18日
 */
public class RechargeAnalysisVo {
	private Integer userId;// 用户Id
	private String realname;// 用户真实姓名
	private BigDecimal rechargeAmount;// 充值金额
	private Integer rankno;// 排序
	private String username;// 充值金额
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public BigDecimal getRechargeAmount() {
		return rechargeAmount;
	}

	public void setRechargeAmount(BigDecimal rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}

	public Integer getRankno() {
		return rankno;
	}

	public void setRankno(Integer rankno) {
		this.rankno = rankno;
	}

}
