package com.cxdai.console.customer.bankcard.vo;

import java.io.Serializable;

/**
 * <p>
 * Description:手机支付信息银行卡解绑返回参数<br />
 * </p>
 * 
 * @title LlWapBankcardUnbindResponse.java
 * @package com.cxdai.console.lianlian.vo
 * @author justin.xu
 * @version 0.1 2015年4月8日
 */
public class LlWapBankcardUnbindResponse extends LlBaseResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 交易结果代码 */
	private String ret_code;
	/** 交易结果描述 */
	private String ret_msg;
	/**
	 * 签名方式 RSA 或者MD5
	 */
	private String sign_type;

	public String getRet_code() {
		return ret_code;
	}

	public void setRet_code(String ret_code) {
		this.ret_code = ret_code;
	}

	public String getRet_msg() {
		return ret_msg;
	}

	public void setRet_msg(String ret_msg) {
		this.ret_msg = ret_msg;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

}
