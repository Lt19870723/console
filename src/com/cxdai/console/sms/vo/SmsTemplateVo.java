package com.cxdai.console.sms.vo;

import java.io.Serializable;

/**
 * <p>
 * Description:短信模板Vo<br />
 * </p>
 * 
 * @title SmsTemplateVo.java
 * @package com.cxdai.portal.sms.vo
 * @author justin.xu
 * @version 0.1 2014年4月29日
 */
public class SmsTemplateVo implements Serializable {
	private static final long serialVersionUID = 3687328787009445438L;

	private Integer id;

	/** 模板类型 */
	private Integer type;

	/** 模板短信内容 */
	private String content;

	/** 逻辑删除标识【0：有效；-1：无效】 */
	private Integer flag;

	/** 备注信息 */
	private String remark;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}