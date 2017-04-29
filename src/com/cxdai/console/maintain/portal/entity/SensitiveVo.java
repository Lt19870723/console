package com.cxdai.console.maintain.portal.entity;

import java.io.Serializable;

import com.cxdai.console.common.page.BaseCnd;

/**
 * <p>
 * Description:手机验证Vo<br />
 * </p>
 * 
 * @title MobileApproVo.java
 * @package com.cxdai.console.member.vo
 * @author justin.xu
 * @version 0.1 2014年4月25日
 */
public class SensitiveVo extends BaseCnd implements Serializable {

	private static final long serialVersionUID = -3441075247554353199L;
	/** ID */
	private Integer id;
	/* 关键字 */
	private String word;
	/* 类型 */
	private Integer type;
	/* 1:注册 2:bbs */
	private Integer source;

	/* 类型名称 */
	private String typeStr;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTypeStr() {
		return typeStr;
	}

	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

}
