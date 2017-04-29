package com.cxdai.console.fix.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * Description:直通车Vo<br />
 * </p>
 * @title FirstBorrowVo.java
 * @package com.cxdai.console.first.vo
 * @author justin.xu
 * @version 0.1 2014年7月2日
 */
public class FixStaticVo implements Serializable {
	private static final long serialVersionUID = -5552272784546139026L;
	/**
	 * 主键id
	 */
	private Integer id;
	/**
	 * 总账户待收总额
	 */

	private BigDecimal accountCollectMoney;
	/**
	 * 总账户待支出总额
	 */

	private BigDecimal accountHistoryInvest;
	/**
	 * 总账户现有的可用金额
	 */
	private BigDecimal accountRepayMentMoney;

	/**
	 * 总账户历史已支出利息
	 */
	private BigDecimal accountUserMoney;

	/**
	 * 将到期资金的本金
	 */
	private BigDecimal capital;

	/**
	 * 将到期资金的收益
	 */
	private BigDecimal profit;
	
	/**
	 * 发布金额
	 */
	private BigDecimal pubMoney;
	/**
	 * 发布数量
	 */
	private Integer pubNum;
	/**
	 * 实际满标金额
	 */
	private BigDecimal sucMoney;
	/**
	 * 参与人次
	 */
	private Integer joinNum;
	/**
	 * 参与人数
	 */
	private Integer joinUser;	
	
	/**
	 * 普通宝满宝金额
	 */
	private BigDecimal sucComMoney;
	/**
	 * 新手宝满宝金额
	 */
	private BigDecimal sucNewMoney;
	/**
	 * 新手宝转普通宝满宝金额
	 */
	private BigDecimal sucNewToComMoney;

	public BigDecimal getPubMoney() {
		return pubMoney;
	}

	public void setPubMoney(BigDecimal pubMoney) {
		this.pubMoney = pubMoney;
	}

	public Integer getPubNum() {
		return pubNum;
	}

	public void setPubNum(Integer pubNum) {
		this.pubNum = pubNum;
	}

	public BigDecimal getSucMoney() {
		return sucMoney;
	}

	public void setSucMoney(BigDecimal sucMoney) {
		this.sucMoney = sucMoney;
	}

	public Integer getJoinNum() {
		return joinNum;
	}

	public void setJoinNum(Integer joinNum) {
		this.joinNum = joinNum;
	}

	public Integer getJoinUser() {
		return joinUser;
	}

	public void setJoinUser(Integer joinUser) {
		this.joinUser = joinUser;
	}

	public BigDecimal getCapital() {
		return capital;
	}

	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getAccountCollectMoney() {
		return accountCollectMoney;
	}

	public void setAccountCollectMoney(BigDecimal accountCollectMoney) {
		this.accountCollectMoney = accountCollectMoney;
	}

	public BigDecimal getAccountHistoryInvest() {
		return accountHistoryInvest;
	}

	public void setAccountHistoryInvest(BigDecimal accountHistoryInvest) {
		this.accountHistoryInvest = accountHistoryInvest;
	}

	public BigDecimal getAccountRepayMentMoney() {
		return accountRepayMentMoney;
	}

	public void setAccountRepayMentMoney(BigDecimal accountRepayMentMoney) {
		this.accountRepayMentMoney = accountRepayMentMoney;
	}

	public BigDecimal getAccountUserMoney() {
		return accountUserMoney;
	}

	public void setAccountUserMoney(BigDecimal accountUserMoney) {
		this.accountUserMoney = accountUserMoney;
	}

	public BigDecimal getSucComMoney() {
		return sucComMoney;
	}

	public void setSucComMoney(BigDecimal sucComMoney) {
		this.sucComMoney = sucComMoney;
	}

	public BigDecimal getSucNewMoney() {
		return sucNewMoney;
	}

	public void setSucNewMoney(BigDecimal sucNewMoney) {
		this.sucNewMoney = sucNewMoney;
	}

	public BigDecimal getSucNewToComMoney() {
		return sucNewToComMoney;
	}

	public void setSucNewToComMoney(BigDecimal sucNewToComMoney) {
		this.sucNewToComMoney = sucNewToComMoney;
	}
	
	

}
