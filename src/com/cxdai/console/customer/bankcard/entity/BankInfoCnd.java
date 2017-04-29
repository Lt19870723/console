package com.cxdai.console.customer.bankcard.entity;

import java.io.Serializable;

/**
 * <p>
 * Description:银行信息查询条件<br />
 * </p>
 * 
 * @title BankInfoCnd.java
 * @package com.cxdai.console.lianlian.vo
 * @author justin.xu
 * @version 0.1 2015年4月8日
 */
public class BankInfoCnd implements Serializable {
	private static final long serialVersionUID = 3279069815765729961L;
	private Integer id;
	/** 用户ID */
	private Integer userId;
	/** 銀行帳戶號 */
	private String cardNum;
	/** 验证状态【0：未验证 1：验证通过】 */
	private Integer verifyStatus;

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

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public Integer getVerifyStatus() {
		return verifyStatus;
	}

	public void setVerifyStatus(Integer verifyStatus) {
		this.verifyStatus = verifyStatus;
	}

}
