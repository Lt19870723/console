package com.cxdai.console.fix.vo;

import com.cxdai.console.fix.entity.FixTenderTransfer;


public class FixTenderTransferVo extends FixTenderTransfer {
	private static final long serialVersionUID = 2788985301272633953L;

	/**
	 * 借款标题
	 */
	private String borrowName;

	public String getBorrowName() {
		return borrowName;
	}

	public void setBorrowName(String borrowName) {
		this.borrowName = borrowName;
	}

}
