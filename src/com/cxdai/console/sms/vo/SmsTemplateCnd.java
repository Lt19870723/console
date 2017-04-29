package com.cxdai.console.sms.vo;

import java.io.Serializable;

import com.cxdai.console.common.page.BaseCnd;

/**
 * <p>
 * Description:短信模板查询条件<br />
 * </p>
 * 
 * @title SmsTemplateCnd.java
 * @package com.cxdai.portal.sms.vo
 * @author justin.xu
 * @version 0.1 2014年4月29日
 */
public class SmsTemplateCnd extends BaseCnd implements Serializable {
	private static final long serialVersionUID = 3687328787009445438L;

	private Integer id;

	/** 模板类型 */
	private Integer type;

	/** 逻辑删除标识【0：有效；-1：无效】 */
	private Integer flag;
	/** 短信内容 */
	private String content;
	/** 手机号码 */
	private String mobile;
	/** 发送状态 */
	private String sendStatus;
	/** 短信接口调用状态 */
	private String invokingStatus;
	private String  addtimeStart;
	private String  addtimeEnd;
	/** 供应商类型 */
	private String vendorType;
	/** 用户名*/
	private String username;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

	public String getInvokingStatus() {
		return invokingStatus;
	}

	public void setInvokingStatus(String invokingStatus) {
		this.invokingStatus = invokingStatus;
	}

	public String getVendorType() {
		return vendorType;
	}

	public void setVendorType(String vendorType) {
		this.vendorType = vendorType;
	}

	public String getAddtimeStart() {
		return addtimeStart;
	}

	public void setAddtimeStart(String addtimeStart) {
		this.addtimeStart = addtimeStart;
	}

	public String getAddtimeEnd() {
		return addtimeEnd;
	}

	public void setAddtimeEnd(String addtimeEnd) {
		this.addtimeEnd = addtimeEnd;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}