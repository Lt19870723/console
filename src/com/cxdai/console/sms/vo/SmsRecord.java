package com.cxdai.console.sms.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Description:短信发送记录实体类<br />
 * </p>
 * 
 * @title SmsRecord.java
 * @package com.cxdai.base.entity
 * @author justin.xu
 * @version 0.1 2014年4月30日
 */
public class SmsRecord implements Serializable {
	private static final long serialVersionUID = 1178922844202577381L;

	/** 主键 */
	private Integer id;

	/** 短信类型,和短信模板一致 */
	private Integer smsType;

	/** 手机号码 */
	private String mobile;

	/** 短信内容 */
	private String content;

	/** 发送状态 */
	private Integer sendStatus;

	/** 短信接口调用状态 */
	private String invokingStatus;

	/** 短信平台返回的ID */
	private String taskid;

	/** 插入时间 */
	private Date addtime;

	/** 插入IP */
	private String addip;
	/**短信模板类型*/
	private Integer smsTemplateType;
	/** 最后一次更新时间 */
	private Date lastmodifytime;
	/** 供应商类型，0:港奥资迅，1：漫道 */
	private Integer vendorType;
	/**平台来源 1：官网 2、微信，即用户登录的客户端*/
	private Integer  platform;
	/**用户名*/
	private String  username;

	public Integer getSmsTemplateType() {
		return smsTemplateType;
	}

	public void setSmsTemplateType(Integer smsTemplateType) {
		this.smsTemplateType = smsTemplateType;
	}

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSmsType() {
		return smsType;
	}

	public void setSmsType(Integer smsType) {
		this.smsType = smsType;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(Integer sendStatus) {
		this.sendStatus = sendStatus;
	}

	public String getInvokingStatus() {
		if(this.invokingStatus!=null&&this.invokingStatus.contains("成功")){
			return invokingStatus="发送成功";
		}else if(this.invokingStatus!=null&&this.invokingStatus.contains("失败")){
			return invokingStatus="发送失败";
		}else{
			return invokingStatus;
		}
		
	}

	public void setInvokingStatus(String invokingStatus) {
		this.invokingStatus = invokingStatus;
	}

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getAddip() {
		return addip;
	}

	public void setAddip(String addip) {
		this.addip = addip;
	}

	public Date getLastmodifytime() {
		return lastmodifytime;
	}

	public void setLastmodifytime(Date lastmodifytime) {
		this.lastmodifytime = lastmodifytime;
	}

	public Integer getVendorType() {
		return vendorType;
	}

	public void setVendorType(Integer vendorType) {
		this.vendorType = vendorType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}