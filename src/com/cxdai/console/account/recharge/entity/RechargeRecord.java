package com.cxdai.console.account.recharge.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * Description:充值记录<br />
 * </p>
 * 
 * @title RechargeRecord.java
 * @package com.cxdai.base.entity
 * @author justin.xu
 * @version 0.1 2014年5月22日
 */
public class RechargeRecord implements Serializable {

	private static final long serialVersionUID = 1262575504723921762L;
	private Integer id;
	/** 用户唯一ID */
	private Integer userId;
	/** 类型(1：在线充值，2：线下转账) */
	private Integer type;
	/** 订单号 */
	private String tradeNo;
	/** 状态（0充值审核中，1充值成功，9表示失败，2在线充值待付款， 3初审成功） */
	private Integer status;
	/** 充值金额 */
	private BigDecimal money;
	/** 充值银行 */
	private String payment;
	/** 备注 */
	private String remark;
	/** 充值手续费 */
	private BigDecimal fee;
	/** 初审审核人id(0默认值，表示在线充值) */
	private Integer verifyUserid;
	/** 初审审核时间 */
	private String verifyTime;
	/** 初审审核备注 */
	private String verifyRemark;
	/** 添加时间 */
	private String addtime;
	/** 添加ip */
	private String addip;
	/** 第三方支付平台 1：网银在线 2：国付宝 3：盛付通 */
	private Integer onlinetype;
	/** 终审审核人id */
	private Integer verifyUserid2;
	/** 终审审核时间 */
	private String verifyTime2;
	/** 终审审核备注 */
	private String verifyRemark2;
	/** 银行帐号 */
	private String cardNum;
	/** 银行开户人姓名 */
	private String bankUsername;
	/** 版本号. **/
	private Integer version;

	private Integer isCustody;
	/**
	 * 本身版本号，主要用于根据版本号更新
	 */
	private Integer selfVersion;

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public Integer getVerifyUserid() {
		return verifyUserid;
	}

	public void setVerifyUserid(Integer verifyUserid) {
		this.verifyUserid = verifyUserid;
	}

	public String getVerifyTime() {
		return verifyTime;
	}

	public void setVerifyTime(String verifyTime) {
		this.verifyTime = verifyTime;
	}

	public String getVerifyRemark() {
		return verifyRemark;
	}

	public void setVerifyRemark(String verifyRemark) {
		this.verifyRemark = verifyRemark;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getAddip() {
		return addip;
	}

	public void setAddip(String addip) {
		this.addip = addip;
	}

	public Integer getOnlinetype() {
		return onlinetype;
	}

	public void setOnlinetype(Integer onlinetype) {
		this.onlinetype = onlinetype;
	}

	public Integer getVerifyUserid2() {
		return verifyUserid2;
	}

	public void setVerifyUserid2(Integer verifyUserid2) {
		this.verifyUserid2 = verifyUserid2;
	}

	public String getVerifyTime2() {
		return verifyTime2;
	}

	public void setVerifyTime2(String verifyTime2) {
		this.verifyTime2 = verifyTime2;
	}

	public String getVerifyRemark2() {
		return verifyRemark2;
	}

	public void setVerifyRemark2(String verifyRemark2) {
		this.verifyRemark2 = verifyRemark2;
	}

	public String getBankUsername() {
		return bankUsername;
	}

	public void setBankUsername(String bankUsername) {
		this.bankUsername = bankUsername;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getSelfVersion() {
		return selfVersion;
	}

	public void setSelfVersion(Integer selfVersion) {
		this.selfVersion = selfVersion;
	}


	public Integer getIsCustody() {
		return isCustody;
	}

	public void setIsCustody(Integer isCustody) {
		this.isCustody = isCustody;
	}


}
