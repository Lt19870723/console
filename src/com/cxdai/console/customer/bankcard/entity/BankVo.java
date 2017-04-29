package com.cxdai.console.customer.bankcard.entity;

import java.io.Serializable;

/**
 * 
 * <p>
 * Description:银行卡分支行信息<br />
 * </p>
 * 
 * @title BankVo.java
 * @package com.cxdai.console.account.vo
 * @author yangshijin
 * @version 0.1 2014年10月10日
 */
public class BankVo extends Bank implements Serializable {
	private static final long serialVersionUID = 7826679240240420855L;

	private String addUserName; // 添加人姓名
	private String updateUserName; // 修改人姓名

	public String getAddUserName() {
		return addUserName;
	}

	public void setAddUserName(String addUserName) {
		this.addUserName = addUserName;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

}