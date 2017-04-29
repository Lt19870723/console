package com.cxdai.console.maintain.registersource.entity;

import java.io.Serializable;
import java.util.Date;

import com.cxdai.console.common.page.BaseCnd;

/**
 * <p>
 * Description:运营数据分析查询条件<br />
 * </p>
 * 
 * @title OperationNormalCnd.java
 * @package com.cxdai.console.opration.vo
 * @author justin.xu
 * @version 0.1 2014年12月24日
 */
public class SourceCnd extends BaseCnd implements Serializable {
	private static final long serialVersionUID = -8537943174686481221L;
	/** 开始日期 */
	private Date beginTime;
	/** 结束日期 */
	private Date endTime;
	/** 查询条件：source */
	private String source;

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
}
