package com.cxdai.console.borrow.emerg.vo;

import com.cxdai.console.base.entity.Borrow;

public class SalariatBorrowVo extends Borrow {
	private static final long serialVersionUID = 1L;
	private String isGuarantyCB;
	private String oldBidPassword;
	private Integer businessUserId;

	public String getIsGuarantyCB() {
		return isGuarantyCB;
	}

	public void setIsGuarantyCB(String isGuarantyCB) {
		this.isGuarantyCB = isGuarantyCB;
	}

	public String getOldBidPassword() {
		return oldBidPassword;
	}

	public void setOldBidPassword(String oldBidPassword) {
		this.oldBidPassword = oldBidPassword;
	}

	public Integer getBusinessUserId() {
		return businessUserId;
	}

	public void setBusinessUserId(Integer businessUserId) {
		this.businessUserId = businessUserId;
	}
	

}
