package com.cxdai.console.customer.bankcard.entity;

import java.io.Serializable;

import com.cxdai.console.common.page.BaseCnd;

/**
 * 银行卡更换申请记录查询参数
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title BankcardChangeCnd.java
 * @package com.cxdai.console.bank.vo
 * @author huangpin
 * @version 0.1 2015年3月27日
 */
public class BankcardChangeCnd extends BaseCnd implements Serializable {

	private static final long serialVersionUID = 1L;

	private String realName; // 真实姓名
	private String userName; // 申请人用户名
	private String approveStatus; // 审核状态：0，待审核，1，审核通过，-1，审核不通过，-2，草稿
	private String phone; // 手机号
	private String oldBankcard; // 原银行卡号
	private String newBankcard; // 新卡卡号

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}

	public String getNewBankcard() {
		return newBankcard;
	}

	public void setNewBankcard(String newBankcard) {
		this.newBankcard = newBankcard;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOldBankcard() {
		return oldBankcard;
	}

	public void setOldBankcard(String oldBankcard) {
		this.oldBankcard = oldBankcard;
	}

}
