package com.cxdai.console.fix.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * Description:定期宝认购明细类<br />
 * </p>
 * 
 * @title Account.java
 * @package com.cxdai.base.account
 * @author caodefeng
 * @version 0.1 2015年5月14日
 */
public class FixTenderDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5239387929393550828L;

	/**
	 * 主键
	 */
	private Integer id;
	
	/**
	 * 用户id
	 */
	private Integer userId;
	
	/**
	 * 定期宝id
	 */
	private Integer fixBorrowId;
	
	/**
	 * 明细状态(0 ：投标中  1 ：投标成功   2：投标失败 3：已还款)
	 */
	private Integer status;
	
	/**
	 * 认购金额
	 */
	private BigDecimal account; 
	
	/**
	 * 认购金额中包含的可提现金额
	 */
	private BigDecimal drawMoney;
	
	/**
	 * 认购金额中包含的受限金额
	 */
	private BigDecimal noDrawMoney; 
	
	/**
	 * 添加时间
	 */
	private Date addtime;
	
	/**
	 * 添加ip
	 */
	private String addip;
	
	/**
	 * 平台来源
	 */
	private Integer platform;
	
	/**
	 * 最终认购记录id
	 */
	private Integer fixTenderRealId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getFixBorrowId() {
		return fixBorrowId;
	}

	public void setFixBorrowId(Integer fixBorrowId) {
		this.fixBorrowId = fixBorrowId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BigDecimal getAccount() {
		return account;
	}

	public void setAccount(BigDecimal account) {
		this.account = account;
	}

	public BigDecimal getDrawMoney() {
		return drawMoney;
	}

	public void setDrawMoney(BigDecimal drawMoney) {
		this.drawMoney = drawMoney;
	}

	public BigDecimal getNoDrawMoney() {
		return noDrawMoney;
	}

	public void setNoDrawMoney(BigDecimal noDrawMoney) {
		this.noDrawMoney = noDrawMoney;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getAddip() {
		return addip;
	}

	public void setAddip(String addip) {
		this.addip = addip;
	}

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}

	public Integer getFixTenderRealId() {
		return fixTenderRealId;
	}

	public void setFixTenderRealId(Integer fixTenderRealId) {
		this.fixTenderRealId = fixTenderRealId;
	}
	
	
	
}
