package com.cxdai.console.statistics.account.vo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.cxdai.console.common.page.BaseCnd;


/**
 * <p>
 * Description:资金操作日志查询条件<br />
 * </p>
 * 
 * @title AccountLogRequest.java
 * @package com.cxdai.console.account.vo
 * @author justin.xu
 * @version 0.1 2014年4月30日
 */
public class AccountLogCnd extends BaseCnd implements Serializable {
	private static final long serialVersionUID = 8983061145517554053L;
	private Integer id;
	/** 會員ID */
	private Integer userId;
	/** 日誌類型 */
	private String type;
	/** 日誌備註 */
	private String remark;

	private String username;

	private Date beginTime;

	private Date endTime;

	private String beginTimeStr;
	private String endTimeStr;
	private String registTimeStart;
	private String registTimeEnd;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

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

	public String getBeginTimeStr() {
		return beginTimeStr;
	}

	public void setBeginTimeStr(String beginTimeStr) {
		this.beginTimeStr = beginTimeStr;
	}

	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

	public String getRegistTimeStart() {
		if(null!=registTimeStart&&StringUtils.isNotEmpty(registTimeStart)&&registTimeStart.length()==10){
			return registTimeStart=this.registTimeStart+" 00:00:00";
		}else{
			return registTimeStart;
		}
	}

	public void setRegistTimeStart(String registTimeStart) {
		this.registTimeStart = registTimeStart;
	}

	public String getRegistTimeEnd() {
		if(null!=registTimeEnd&&StringUtils.isNotEmpty(registTimeEnd)&&registTimeEnd.length()==10){
			return registTimeEnd=this.registTimeEnd+" 23:59:59";
		}else{
			return registTimeEnd;
		}
	}

	public void setRegistTimeEnd(String registTimeEnd) {
		this.registTimeEnd = registTimeEnd;
	}
}
