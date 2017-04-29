package com.cxdai.console.customer.bankcard.entity;

import java.io.Serializable;

/**
 * <p>
 * Description:手机支付信息银行卡解绑提交表单请求参数<br />
 * </p>
 * 
 * @title LlWapBankcardUnbindRequest.java
 * @package com.cxdai.console.lianlian.vo
 * @author justin.xu
 * @version 0.1 2015年4月8日
 */
public class LlWapBankcardUnbindRequest extends LlBaseRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 商户编号是在连支付平台上开 设的商户号码，为 18 位数字
	 */
	private String oid_partner;
	/**
	 * 该用户在商户系统中的唯一编号，要求是该编号在商户系统中唯一标识该用户
	 */
	private String user_id;
	/**
	 * 支付方式 D:认证支付
	 */
	private String pay_type;
	/**
	 * 签约协议号,银行卡签约的唯一编号
	 */
	private String no_agree;

	public String getOid_partner() {
		return oid_partner;
	}

	public void setOid_partner(String oid_partner) {
		this.oid_partner = oid_partner;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getPay_type() {
		return pay_type;
	}

	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}

	public String getNo_agree() {
		return no_agree;
	}

	public void setNo_agree(String no_agree) {
		this.no_agree = no_agree;
	}
}
