package com.cxdai.console.red.vo;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.cxdai.console.common.page.BaseCnd;

/**
 * Description:红包记录查询条件<br />
 * @author liutao
 * @version 0.1 2015年11月4日
 */
public class RedRecordCnd extends BaseCnd implements Serializable {
	private static final long serialVersionUID = -5890980057423538506L;
	/**
	 * 用户名
	 */
	private String username;
	private Integer userId;// 用户ID
	/**
	 * 红包金额
	 */
	private String redmoney;
	/**
	 *红包来源
	 */
	private Integer redsource;
	/**
	 * 发放日期 开始时间
	 */
	private String beginTime;

	/**
	 * 发放日期 结束时间
	 */
	private String endTime;
	/**
	 * 使用日期 开始时间
	 */
	private String usebeginTime;

	/**
	 * 使用日期 结束时间
	 */
	private String useendTime;
	/** 
	 * 红包状态
	 *  */
	private Integer redstatus;
	public String getUsername() {
		if(null!=this.username&&StringUtils.isNotEmpty(this.username)){
			return this.username.trim();
		}
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRedmoney() {
		return redmoney;
	}
	public void setRedmoney(String redmoney) {
		this.redmoney = redmoney;
	}
	public Integer getRedsource() {
		return redsource;
	}
	public void setRedsource(Integer redsource) {
		this.redsource = redsource;
	}
	public String getBeginTime() {
		if(null!=this.beginTime&&StringUtils.isNotEmpty(beginTime)&&this.beginTime.length()==10){
			return beginTime=this.beginTime+" 00:00:00";
		}else{
			return beginTime;
		}
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		if(null!=this.endTime&&StringUtils.isNotEmpty(endTime)&&this.endTime.length()==10){
			return endTime=this.endTime+" 23:59:59";
		}else{
			return endTime;
		}
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getRedstatus() {
		return redstatus;
	}
	public void setRedstatus(Integer redstatus) {
		this.redstatus = redstatus;
	}
	public String getUsebeginTime() {
		if(null!=this.usebeginTime&&StringUtils.isNotEmpty(usebeginTime)&&this.usebeginTime.length()==10){
			return usebeginTime=this.usebeginTime+" 00:00:00";
		}else{
			return usebeginTime;
		}
	}
	public void setUsebeginTime(String usebeginTime) {
		this.usebeginTime = usebeginTime;
	}
	public String getUseendTime() {
		if(null!=this.useendTime&&StringUtils.isNotEmpty(useendTime)&&this.useendTime.length()==10){
			return useendTime=this.useendTime+" 23:59:59";
		}else{
			return useendTime;
		}
	}
	public void setUseendTime(String useendTime) {
		this.useendTime = useendTime;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}