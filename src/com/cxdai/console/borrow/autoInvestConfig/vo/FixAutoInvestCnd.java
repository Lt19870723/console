package com.cxdai.console.borrow.autoInvestConfig.vo;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.cxdai.console.common.page.BaseCnd;


/**
 * Description:自动投宝查询条件<br />
 * @author liutao
 * @date 2015年11月20日
 */
public class FixAutoInvestCnd extends BaseCnd implements Serializable {
	private static final long serialVersionUID = -5890980057423538506L;
	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 定期宝期限
	 */
	private String fixLimit;
	/**
	 *状态
	 */
	private String status;
	/**
	 * 记录类型
	 */
	private String recordType;
	/**
	 * 定期宝编号
	 */
	private String fixNo;
	/**
	 * 投宝方式
	 */
	private String autoTenderType;
	/**
	 * 记录开始时间
	 */
	private String addTimeStart;
	/**
	 * 记录截止时间
	 */
	private String addTimeEnd;
	/**
	 * 自动投宝记录ID
	 */
	private int id;
	private Integer autoTenderMoney;
	public String getUsername() {
		if(StringUtils.isNotEmpty(this.username)){
			return username.trim();	
		}
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFixLimit() {
		return fixLimit;
	}
	public void setFixLimit(String fixLimit) {
		this.fixLimit = fixLimit;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	public String getFixNo() {
		if(StringUtils.isNotEmpty(this.fixNo)){
			return fixNo.trim();	
		}
		return fixNo;
	}
	public void setFixNo(String fixNo) {
		this.fixNo = fixNo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAutoTenderType() {
		return autoTenderType;
	}
	public void setAutoTenderType(String autoTenderType) {
		this.autoTenderType = autoTenderType;
	}
	public String getAddTimeStart() {
		if(null!=this.addTimeStart&&StringUtils.isNotEmpty(addTimeStart)&&this.addTimeStart.length()==10){
			return addTimeStart=this.addTimeStart+" 00:00:00";
		}else{
			return addTimeStart;
		}
	}
	public void setAddTimeStart(String addTimeStart) {
		this.addTimeStart = addTimeStart;
	}
	public String getAddTimeEnd() {
		if(null!=this.addTimeEnd&&StringUtils.isNotEmpty(addTimeEnd)&&this.addTimeEnd.length()==10){
			return addTimeEnd=this.addTimeEnd+" 23:59:59";
		}else{
			return addTimeEnd;
		}
	}
	public void setAddTimeEnd(String addTimeEnd) {
		this.addTimeEnd = addTimeEnd;
	}
	public Integer getAutoTenderMoney() {
		return autoTenderMoney;
	}
	public void setAutoTenderMoney(Integer autoTenderMoney) {
		this.autoTenderMoney = autoTenderMoney;
	}
}