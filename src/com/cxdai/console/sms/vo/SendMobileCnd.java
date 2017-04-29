package com.cxdai.console.sms.vo;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>
 * Description:发送短信条件<br />
 * </p>
 * 
 * @title SendMobileCnd.java
 * @package com.cxdai.portal.member.vo
 * @author justin.xu
 * @version 0.1 2014年11月17日
 */
public class SendMobileCnd implements Serializable {

	private static final long serialVersionUID = 2536233498418360658L;
	/** 手机号 */
	private String mobile;
	/** 记录ip */
	private String ip;
	/** 发送短信模板 */
	private Integer smsTemplateType;
	/** 平台来源 */
	private Integer platform;

	/** 模块名，主要用于区分不同模块之间相同手机号进行缓存 */
	private String modelName;
	/** 是否需要验证码，同时必须设置模块名modelName ，验证码对应数据库的key为：randCode **/
	private boolean needRandcode;

	/** 原有模板参数 */
	private String pusername;
	private String ptime;
	private String pborrowName;
	private String pmoney;
	private String ppassword;
	private String ptype;
	private String plogpassword;

	/** 新增变量值map */
	private Map<String, Object> paramMap;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}

	public Integer getSmsTemplateType() {
		return smsTemplateType;
	}

	public void setSmsTemplateType(Integer smsTemplateType) {
		this.smsTemplateType = smsTemplateType;
	}

	public String getPusername() {
		return pusername;
	}

	public void setPusername(String pusername) {
		this.pusername = pusername;
	}

	public String getPtime() {
		return ptime;
	}

	public void setPtime(String ptime) {
		this.ptime = ptime;
	}

	public String getPborrowName() {
		return pborrowName;
	}

	public void setPborrowName(String pborrowName) {
		this.pborrowName = pborrowName;
	}

	public String getPmoney() {
		return pmoney;
	}

	public void setPmoney(String pmoney) {
		this.pmoney = pmoney;
	}

	public String getPpassword() {
		return ppassword;
	}

	public void setPpassword(String ppassword) {
		this.ppassword = ppassword;
	}

	public String getPtype() {
		return ptype;
	}

	public void setPtype(String ptype) {
		this.ptype = ptype;
	}

	public String getPlogpassword() {
		return plogpassword;
	}

	public void setPlogpassword(String plogpassword) {
		this.plogpassword = plogpassword;
	}

	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}

	public boolean getNeedRandcode() {
		return needRandcode;
	}

	public void setNeedRandcode(boolean needRandcode) {
		this.needRandcode = needRandcode;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

}
