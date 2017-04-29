package com.cxdai.console.curaccount.vo;

import java.io.Serializable;
import java.util.Date;

import com.cxdai.console.curaccount.entity.CurAccountlog;
import com.cxdai.console.util.DateUtils;

public class CurAccountLogCnd extends CurAccountlog implements Serializable {

	private static final long serialVersionUID = -6903226903535940857L;

	// 资金类型
	private Integer type_zj;

	// 转入，转出开始时间
	private Date saddtime;
	private String saddtimeStr;
	// 转入，转出结束时间
	private Date eaddtime;
	private String eaddtimeStr;

	private String userName;

	private Integer userId;

	private String beginDay;
	private String endDay;

	// 统计开始，结束时间
	private Date sTime;
	private Date eTime;
	
	private String typeStr;
	

	public String getTypeStr() {
		return typeStr;
	}

	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}

	/**
	 * @return userId : return the property userId.
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            : set the property userId.
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return beginDay : return the property beginDay.
	 */
	public String getBeginDay() {
		return beginDay;
	}

	/**
	 * @param beginDay
	 *            : set the property beginDay.
	 */
	public void setBeginDay(String beginDay) {
		this.beginDay = beginDay;
	}

	/**
	 * @return endDay : return the property endDay.
	 */
	public String getEndDay() {
		return endDay;
	}

	/**
	 * @param endDay
	 *            : set the property endDay.
	 */
	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}

	/**
	 * @return userName : return the property userName.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            : set the property userName.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return saddtime : return the property saddtime.
	 */
	public Date getSaddtime() {
		return saddtime;
	}

	/**
	 * @param saddtime
	 *            : set the property saddtime.
	 */
	public void setSaddtime(String saddtime) {
		this.saddtime = DateUtils.parse(saddtime, DateUtils.YMD_DASH);
	}

	/**
	 * @return eaddtime : return the property eaddtime.
	 */
	public Date getEaddtime() {
		return eaddtime;
	}

	/**
	 * @param eaddtime
	 *            : set the property eaddtime.
	 */
	public void setEaddtime(String eaddtime) {
		this.eaddtime = DateUtils.parse(eaddtime, DateUtils.YMD_DASH);
	}

	/**
	 * @return saddtimeStr : return the property saddtimeStr.
	 */
	public String getSaddtimeStr() {
		return saddtimeStr;
	}

	/**
	 * @param saddtimeStr
	 *            : set the property saddtimeStr.
	 */
	public void setSaddtimeStr(String saddtimeStr) {
		this.saddtimeStr = saddtimeStr;
	}

	/**
	 * @return eaddtimeStr : return the property eaddtimeStr.
	 */
	public String getEaddtimeStr() {
		return eaddtimeStr;
	}

	/**
	 * @param eaddtimeStr
	 *            : set the property eaddtimeStr.
	 */
	public void setEaddtimeStr(String eaddtimeStr) {
		this.eaddtimeStr = eaddtimeStr;
	}

	/**
	 * @return type_zj : return the property type_zj.
	 */
	public Integer getType_zj() {
		return type_zj;
	}

	/**
	 * @param type_zj
	 *            : set the property type_zj.
	 */
	public void setType_zj(Integer type_zj) {
		this.type_zj = type_zj;
	}

	/**
	 * @return sTime : return the property sTime.
	 */
	public Date getsTime() {
		return sTime;
	}

	/**
	 * @param sTime
	 *            : set the property sTime.
	 */
	public void setsTime(String sTime) {
		this.sTime = DateUtils.parse(sTime, DateUtils.YMD_DASH);
	}

	/**
	 * @return eTime : return the property eTime.
	 */
	public Date geteTime() {
		return eTime;
	}

	/**
	 * @param eTime
	 *            : set the property eTime.
	 */
	public void seteTime(String eTime) {
		this.eTime = DateUtils.parse(eTime, DateUtils.YMD_DASH);
	}

}