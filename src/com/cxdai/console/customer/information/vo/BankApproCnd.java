package com.cxdai.console.customer.information.vo;

import java.io.Serializable;

import com.cxdai.console.common.page.BaseCnd;


/**
 * @author WangQianJin
 * @title 银行卡审核查询条件
 * @date 2016年6月7日
 */
public class BankApproCnd extends BaseCnd implements Serializable {

	private static final long serialVersionUID = -8188288915492817098L;
	
	/** 用户名 */
	private String username;
	/** 状态 */
	private String status;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
