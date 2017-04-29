package com.cxdai.console.borrow.approve.vo;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

import com.cxdai.console.common.page.BaseCnd;
import com.cxdai.console.util.DateUtils;
 
/**
 * <p>
 * Description:借款标审核历史查询条件<br />
 * </p>
 * 
 * @title BorrowCnd.java
 * @package com.cxdai.console.borrow.vo
 * @author hujinapan
 * @version 0.1 2014年12月09日
 */
public class BorrowAuditHistoryCnd extends BaseCnd implements Serializable {
	/**
	 * @fields serialVersionUID
	 */

	private static final long serialVersionUID = -2343651735385175138L;

	/** 借款标题 */
	private String name;

	/** 借款标发布人用户名 **/
	private String userName;

	/** 合同编号 **/
	private String contractNo;

	/** 借款标类型 **/
	private Integer borrowtype;

	/** 满标开始时间 **/
	private Date successTimeBegin;
	private String successTimeBeginStr;

	/** 满标结束时间 **/
	private String endTimeForSuccessTime;
	private Date successTimeEnd;
	private String successTimeEndStr;
	private String  beginTimeForSuccessTime;
	public String beginPublicTimeStr;
	public String endPublicTimeStr;
	public String status;
	
	private String isCustody;
	
	
	
	
	public String getIsCustody() {
		return isCustody;
	}

	public void setIsCustody(String isCustody) {
		this.isCustody = isCustody;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@SuppressWarnings("deprecation")
	public String getBeginPublicTimeStr() throws ParseException {
		return convertTime(beginPublicTimeStr);
	}

	private String convertTime(String sourceDate) {
		if(null != sourceDate && !"".equals(sourceDate)){
			return  DateUtils.date2TimeStamp(sourceDate)+"000";//返回秒
		}
		return "";
	}

	public String getEndPublicTimeStr() throws ParseException {
		if(null != endPublicTimeStr && !"".equals(endPublicTimeStr)){
		 
			String endTimeStamp = DateUtils.dayOffset(DateUtils.parse(endPublicTimeStr, DateUtils.YMD_DASH),1).getTime()+"";
			return  endTimeStamp/*DateUtils.date2TimeStamp(dateFmt)*/;//返回秒
		}
		 return "";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public Integer getBorrowtype() {
		return borrowtype;
	}

	public void setBorrowtype(Integer borrowtype) {
		this.borrowtype = borrowtype;
	}

	public Date getSuccessTimeBegin() {
		return successTimeBegin;
	}

	public void setSuccessTimeBegin(Date successTimeBegin) {
		this.successTimeBegin = successTimeBegin;
	}

	public String getSuccessTimeBeginStr() {
		return successTimeBeginStr;
	}

	public void setSuccessTimeBeginStr(String successTimeBeginStr) {
		this.successTimeBeginStr = successTimeBeginStr;
	}

	public Date getSuccessTimeEnd() {
		return successTimeEnd;
	}

	public void setSuccessTimeEnd(Date successTimeEnd) {
		this.successTimeEnd = successTimeEnd;
	}

	public String getSuccessTimeEndStr() {
		return successTimeEndStr;
	}

	public void setSuccessTimeEndStr(String successTimeEndStr) {
		this.successTimeEndStr = successTimeEndStr;
	}

	public String getEndTimeForSuccessTime() {
		return endTimeForSuccessTime;
	}

	public void setEndTimeForSuccessTime(String endTimeForSuccessTime) {
		this.endTimeForSuccessTime = endTimeForSuccessTime;
	}

	public String getBeginTimeForSuccessTime() {
		return beginTimeForSuccessTime;
	}

	public void setBeginTimeForSuccessTime(String beginTimeForSuccessTime) {
		this.beginTimeForSuccessTime = beginTimeForSuccessTime;
	}

	public void setBeginPublicTimeStr(String beginPublicTimeStr) {
		this.beginPublicTimeStr = beginPublicTimeStr;
	}

	public void setEndPublicTimeStr(String endPublicTimeStr) {
		this.endPublicTimeStr = endPublicTimeStr;
	}

}