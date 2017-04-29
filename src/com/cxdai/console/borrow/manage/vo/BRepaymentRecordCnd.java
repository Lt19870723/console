package com.cxdai.console.borrow.manage.vo;

import java.io.Serializable;
import java.util.Date;

import com.cxdai.console.common.page.BaseCnd;
import com.cxdai.console.util.DateUtils;


/**
 * <p>
 * Description:待还记录<br />
 * </p>
 * 
 * @title BRepaymentRecordVo.java
 * @package com.cxdai.console.borrow.vo
 * @author justin.xu
 * @version 0.1 2014年8月13日
 */
public class BRepaymentRecordCnd extends BaseCnd implements Serializable {

	private static final long serialVersionUID = 1946789729579635457L;

	/** 借款标名字 */
	private String name;

	private String username;

	/** 借款标种类（ 1：信用标，2：抵押标，3：净值标，4：秒标 5：担保标 ） */
	private Integer borrowtype;

	/** 状态 0---未还 1---已还 */
	private Integer status;

	private Integer webstatus;

	/** 应还款时间 开始时间 */
	private Date repaymentTimeBegin;

	/** 应还款时间 结束时间 */
	private Date repaymentTimeEnd;

	/** 应还款时间 开始时间 */
	private String repaymentTimeBeginStr;

	/** 应还款时间 结束时间 */
	private String repaymentTimeEndStr;

	/** 开始时间 */
	private String beignTime;

	/** 截止时间 */
	private String endTime;
	
	private String statusStr;
	
	private String isCustody;
	
	

	public String getIsCustody() {
		return isCustody;
	}

	public void setIsCustody(String isCustody) {
		this.isCustody = isCustody;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getBorrowtype() {
		return borrowtype;
	}

	public void setBorrowtype(Integer borrowtype) {
		this.borrowtype = borrowtype;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getWebstatus() {
		return webstatus;
	}

	public void setWebstatus(Integer webstatus) {
		this.webstatus = webstatus;
	}

	public Date getRepaymentTimeBegin() {
		return repaymentTimeBegin;
	}

	public void setRepaymentTimeBegin(Date repaymentTimeBegin) {
		this.repaymentTimeBegin = repaymentTimeBegin;
	}

	public Date getRepaymentTimeEnd() {
		return repaymentTimeEnd;
	}

	public void setRepaymentTimeEnd(Date repaymentTimeEnd) {
		this.repaymentTimeEnd = repaymentTimeEnd;
	}

	public String getRepaymentTimeBeginStr() {
		return convertTime(repaymentTimeBeginStr);
	}

	public void setRepaymentTimeBeginStr(String repaymentTimeBeginStr) {
		this.repaymentTimeBeginStr = repaymentTimeBeginStr;
	}

	public String getRepaymentTimeEndStr() {
		return convertTime(repaymentTimeEndStr);
	}

	public void setRepaymentTimeEndStr(String repaymentTimeEndStr) {
		this.repaymentTimeEndStr = repaymentTimeEndStr;
	}

	public String getBeignTime() {
		return beignTime;
	}

	public void setBeignTime(String beignTime) {
		this.beignTime = beignTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}
	
	private String convertTime(String sourceDate) {
		if(null != sourceDate && !"".equals(sourceDate)){
			return  DateUtils.date2TimeStamp(sourceDate)+"000";//返回秒
		}
		return "";
	}

}
