package com.cxdai.console.customer.information.vo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.cxdai.console.common.page.BaseCnd;
import com.cxdai.console.util.DateUtils;


/**
 * <p>
 * Description:实名认证查询条件<br />
 * </p>
 * 
 * @title RealNameApproCnd.java
 * @package com.cxdai.portal.member.vo
 * @author justin.xu
 * @version 0.1 2014年4月24日
 */
public class RealNameApproCnd extends BaseCnd implements Serializable {
	private static final long serialVersionUID = -1748725774856611015L;
	private String id;
	/** 用户名 */
	private String username;
	/** 用户名 */
	private String realname;
	/** 申请开始日期 */
	private Date appBeginTime;
	private String appBeginTimeStr;
	/** 申请结束日期 */
	private Date appEndTime;
	private String appEndTimeStr;
	/** 审核状态 */
	private String isPassed;
	/** 版本号 */
	private String version;
	private String number;
	private int auditType;
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	/** 验证重复数据 */
	/** 会员ID */
	private Integer userId;
	/** 身份证号码 */
	private String idCardNo;

	public int getAuditType() {
		return auditType;
	}

	public void setAuditType(int auditType) {
		this.auditType = auditType;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getAppBeginTime() {

		if (!StringUtils.isEmpty(appBeginTimeStr)) {
			return DateUtils.parse(appBeginTimeStr, DateUtils.YMD_DASH);
		}

		return appBeginTime;
	}

	public void setAppBeginTime(Date appBeginTime) {
		this.appBeginTime = appBeginTime;
	}

	public String getAppBeginTimeStr() {
		return appBeginTimeStr;
	}

	public void setAppBeginTimeStr(String appBeginTimeStr) {
		this.appBeginTimeStr = appBeginTimeStr;
	}

	public Date getAppEndTime() {
		if (!StringUtils.isEmpty(appEndTimeStr)) {
			return DateUtils.parse(appEndTimeStr, DateUtils.YMD_DASH);
		}
		return appEndTime;
	}

	public void setAppEndTime(Date appEndTime) {
		this.appEndTime = appEndTime;
	}

	public String getAppEndTimeStr() {
		return appEndTimeStr;
	}

	public void setAppEndTimeStr(String appEndTimeStr) {
		this.appEndTimeStr = appEndTimeStr;
	}

	public String getIsPassed() {
		return isPassed;
	}

	public void setIsPassed(String isPassed) {
		this.isPassed = isPassed;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

}
