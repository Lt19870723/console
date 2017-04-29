package com.cxdai.console.maintain.xw.entity;

/**
 * 发送到微信端用于传送数据的实体
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title AutoMessage.java
 * @package com.cxdai.wx.entry.message.pushmessage.vo
 * @author Wang Bo
 * @version 0.1 2014年11月1日
 */
public class AutoMessage {
	/** 主动推送内容 */
	private String message;
	/** 推送人 */
	private Integer wxId;
	private Integer userId;
	private Integer type;
	private String serialNumber;

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getWxId() {
		return wxId;
	}

	public void setWxId(Integer wxId) {
		this.wxId = wxId;
	}

}
