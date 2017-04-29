package com.cxdai.console.system.entity;

import java.io.Serializable;

/**
 * <p>
 * Description:字典项配置<br />
 * </p>
 * 
 * @title Configuration.java
 * @package com.cxdai.base.entity
 * @author justin.xu
 * @version 0.1 2014年4月28日
 */
public class Configuration implements Serializable {
	private static final long serialVersionUID = 2133468441069597006L;
	/** 主键ID */
	private Integer id;
	// TODO 配置类型设定
	/** 配置类型，从1开始排序【1：资金流动说明；2：邮箱关联；3：投资等级；4：信用等级；】 */
	private Integer type;
	/** 排序 */
	private Integer order;
	/** 名称 */
	private String name;
	/** 值 */
	private String value;
	/** 配置是否生效【0：有效；-1：无效】 */
	private String status;
	/** 描述信息（表名_字段） */
	private String desc;

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

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
