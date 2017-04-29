package com.cxdai.console.customer.bankcard.entity;

import java.io.Serializable;

/**
 * <p>
 * Description:支付基本请求数据<br />
 * </p>
 * 
 * @title LlBaseRequest.java
 * @package com.cxdai.console.lianlian.vo
 * @author justin.xu
 * @version 0.1 2015年4月8日
 */
public class LlBaseRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 签名 RSA 加密签名，见安全签名机制
	 */
	private String sign;
	/**
	 * 签名方式 RSA 或者MD5
	 */
	private String sign_type;

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String md5KeyProperty() {
		return "key";
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
}
