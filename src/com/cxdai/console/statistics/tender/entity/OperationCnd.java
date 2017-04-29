package com.cxdai.console.statistics.tender.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
public class OperationCnd extends BaseCnd implements Serializable {
	private static final long serialVersionUID = -8537943174686481221L;
	/** 开始日期 */
	private Date beginTime;
	private String beginTimeStr;
	/** 结束日期 */
	private Date endTime;
	private String endTimeStr;
	/** 查询条件：source */
	private String source;
	/** 用于复投人次统计的次数区间 */
	private String beginTimes;
	private String endTimes;
	/** 用于新增投资人次统计的次数区间 */
	private String investMoneyBegin;
	private String investMoneyEnd;
	/** 月统计量统计查询条件 */
	private String platForm;
	private String userName;
	private List<String> headers;
	
	/** 类型：CPA；CPS */
	private String sourceType;

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public String getBeginTimeStr() {
		return beginTimeStr;
	}

	public void setBeginTimeStr(String beginTimeStr) {
		this.beginTimeStr = beginTimeStr;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getBeginTimes() {
		return beginTimes;
	}

	public void setBeginTimes(String beginTimes) {
		this.beginTimes = beginTimes;
	}

	public String getEndTimes() {
		return endTimes;
	}

	public void setEndTimes(String endTimes) {
		this.endTimes = endTimes;
	}

	public String getInvestMoneyBegin() {
		return investMoneyBegin;
	}

	public void setInvestMoneyBegin(String investMoneyBegin) {
		this.investMoneyBegin = investMoneyBegin;
	}

	public String getInvestMoneyEnd() {
		return investMoneyEnd;
	}

	public void setInvestMoneyEnd(String investMoneyEnd) {
		this.investMoneyEnd = investMoneyEnd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<String> getHeaders() {
		return headers;
	}

	public void setHeaders(List<String> headers) {
		this.headers = headers;
	}

	public String getPlatForm() {
		return platForm;
	}

	public void setPlatForm(String platForm) {
		this.platForm = platForm;
	}
}
