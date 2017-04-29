package com.cxdai.console.base.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * Description:直通车转让认购表对应entity类<br />
 * </p>
 * 
 * @title FirstSubscribe.java
 * @package com.cxdai.base.entity
 * @author 朱泳霖
 * @version 0.1 2015年3月19日
 */
public class FirstSubscribe implements Serializable {

	private static final long serialVersionUID = -5552272784546139026L;

	/**
	 * 主键ID
	 */
	private Integer id;

	/**
	 * 直通车认购人
	 */
	private Integer userId;
	
	/**
	 * 直通车转让ID
	 */
	private Integer firstTransferId;
	
	/**
	 * 直通车ID
	 */
	private Integer firstBorrowId;
	
	/**
	 * 转让人直通车最终认购记录ID
	 */
	private Integer oldFirstTenderRealId;
	
	/**
	 * 接收人直通车最终认购记录ID
	 */
	private Integer newFirstTenderRealId;
	
	/**
	 * 认购金额=转让价格
	 */
	private BigDecimal account;
	
	/**
	 * 认购的可提现金额
	 */
	private BigDecimal drawMoney;
	
	/**
	 * 认购的受限金额
	 */
	private BigDecimal noDrawMoney;

	/**
	 * 用户等级
	 */
	private String userLevel;

	/**
	 * 利息管理费比率
	 */
	private BigDecimal ratio;

	/**
	 * 是否是VIP 1:是：0：否
	 */
	private Integer isVip;

	/**
	 * 状态(1认购中；2认购失败；3认购成功)
	 */
	private Integer status;

	/**
	 * 添加时间
	 */
	private Date addTime;

	/**
	 * 添加IP
	 */
	private String addIp;

	/**
	 * 添加MAC
	 */
	private String addMac;

	/**
	 * 认购方式（0：手动投标，1：自动投标，2：直通车投标）
	 */
	private Integer subscribeType;

	/**
	 * 自动投标排名位数
	 */
	private String autotenderOrder;

	/**
	 * 自动排名位置不变备注
	 */
	private String autotenderOrderRemark;

	/**
	 * 平台来源(1：网页 2、微信 3 安卓 4 IOS)
	 */
	private Integer platform;

	/**
	 * 备注
	 */
	private String remark;

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

	public Integer getFirstTransferId() {
		return firstTransferId;
	}

	public void setFirstTransferId(Integer firstTransferId) {
		this.firstTransferId = firstTransferId;
	}

	public Integer getFirstBorrowId() {
		return firstBorrowId;
	}

	public void setFirstBorrowId(Integer firstBorrowId) {
		this.firstBorrowId = firstBorrowId;
	}

	public Integer getOldFirstTenderRealId() {
		return oldFirstTenderRealId;
	}

	public void setOldFirstTenderRealId(Integer oldFirstTenderRealId) {
		this.oldFirstTenderRealId = oldFirstTenderRealId;
	}

	public Integer getNewFirstTenderRealId() {
		return newFirstTenderRealId;
	}

	public void setNewFirstTenderRealId(Integer newFirstTenderRealId) {
		this.newFirstTenderRealId = newFirstTenderRealId;
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

	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	public BigDecimal getRatio() {
		return ratio;
	}

	public void setRatio(BigDecimal ratio) {
		this.ratio = ratio;
	}

	public Integer getIsVip() {
		return isVip;
	}

	public void setIsVip(Integer isVip) {
		this.isVip = isVip;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getAddIp() {
		return addIp;
	}

	public void setAddIp(String addIp) {
		this.addIp = addIp;
	}

	public String getAddMac() {
		return addMac;
	}

	public void setAddMac(String addMac) {
		this.addMac = addMac;
	}

	public Integer getSubscribeType() {
		return subscribeType;
	}

	public void setSubscribeType(Integer subscribeType) {
		this.subscribeType = subscribeType;
	}

	public String getAutotenderOrder() {
		return autotenderOrder;
	}

	public void setAutotenderOrder(String autotenderOrder) {
		this.autotenderOrder = autotenderOrder;
	}

	public String getAutotenderOrderRemark() {
		return autotenderOrderRemark;
	}

	public void setAutotenderOrderRemark(String autotenderOrderRemark) {
		this.autotenderOrderRemark = autotenderOrderRemark;
	}

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
