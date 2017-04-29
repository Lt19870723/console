package com.cxdai.console.common.ph.entity;

/**
 * 
 * <p>
 * Description:普惠接口请求构造<br />
 * </p>
 * @title RquestBuilder.java
 * @package com.gcjr.loan.puhui.base 
 * @author xiaofei.li
 * @version 0.1 2016-6-15
 */
public class BaseSend {
	
	/**请求 接收 加密实体**/
	private byte[] jsonContent;
	/**请求  接收 签名**/
	private String sign;

	
	public byte[] getJsonContent() {
		return jsonContent;
	}

	public void setJsonContent(byte[] jsonContent) {
		this.jsonContent = jsonContent;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
	
	

}
