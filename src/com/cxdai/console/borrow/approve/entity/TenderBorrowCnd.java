package com.cxdai.console.borrow.approve.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.cxdai.console.common.page.BaseCnd;


/**
 * <p>
 * Description:投标参数<br />
 * </p>
 * @title TenderBorrowCnd.java
 * @package com.cxdai.portal.borrow.vo
 * @author justin.xu
 * @version 0.1 2014年8月12日
 */
public class TenderBorrowCnd extends BaseCnd implements Serializable {

	private static final long serialVersionUID = -1272534286573229542L;
	/**
	 * 投标金额
	 */
	private BigDecimal tenderMoney;
	/**
	 * 借款标id
	 */
	private Integer borrowid;
	/**
	 * 投标密码
	 */
	private String bidPassword;
	/**
	 * 交易密码
	 */
	private String payPassword;
	/**
	 * 验证码
	 */
	private String checkCode;

	/** 是否使用活期宝金额 0：不使用，1：使用 */
	private String isUseCurMoney;

	/** 红包ID */
	private Integer redId;
	
	/**短信验证码****/
	private String mobileCode;
	
	

	public String getMobileCode() {
		return mobileCode;
	}

	public void setMobileCode(String mobileCode) {
		this.mobileCode = mobileCode;
	}

	public Integer getRedId() {
		return redId;
	}

	public void setRedId(Integer redId) {
		this.redId = redId;
	}

	public BigDecimal getTenderMoney() {
		return tenderMoney;
	}

	public void setTenderMoney(BigDecimal tenderMoney) {
		this.tenderMoney = tenderMoney;
	}

	public Integer getBorrowid() {
		return borrowid;
	}

	public void setBorrowid(Integer borrowid) {
		this.borrowid = borrowid;
	}

	public String getBidPassword() {
		return bidPassword;
	}

	public void setBidPassword(String bidPassword) {
		this.bidPassword = bidPassword;
	}

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public String getIsUseCurMoney() {
		return isUseCurMoney;
	}

	public void setIsUseCurMoney(String isUseCurMoney) {
		this.isUseCurMoney = isUseCurMoney;
	}
}