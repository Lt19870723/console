package com.cxdai.console.customer.bankcard.vo;

import java.io.Serializable;

import com.cxdai.console.common.page.BaseCnd;

public class UserBindBankCnd extends BaseCnd implements Serializable {
	private static final long serialVersionUID = -3341541860348334233L;
	String userName;
	/**银行卡号*/
	String cardNum;
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

}
