package com.cxdai.console.common.ph.entity;

import java.util.List;

/**
 * 
 * <p>
 * Description:普惠交易返回<br />
 * </p>
 * @title JsonResult.java
 * @package com.gcjr.loan.puhui.base 
 * @author xiaofei.li
 * @version 0.1 2016-6-15
 */
public class JsonResult {
	
	/**请求返回码**/
	private String resCode;
	/**请求返回内容**/
	private String resMessage;
	
	/**请求返回内容**/
	private String resultStr;
	
	
	public String getResultStr() {
		return resultStr;
	}
	public void setResultStr(String resultStr) {
		this.resultStr = resultStr;
	}
	public String getResCode() {
		return resCode;
	}
	public void setResCode(String resCode) {
		this.resCode = resCode;
	}
	public String getResMessage() {
		return resMessage;
	}
	public void setResMessage(String resMessage) {
		this.resMessage = resMessage;
	}
	
}
