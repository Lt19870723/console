package com.cxdai.console.customer.information.vo;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.cxdai.console.common.page.BaseCnd;
import com.cxdai.console.util.DateUtils;

/**
 * 
 * <p>
 * Description:会员类查询条件<br />
 * </p>
 * 
 * @title MemberCnd.java
 * @package com.cxdai.console.account.vo
 * @author yangshijin
 * @version 0.1 2014年7月2日
 */
public class MemberCnd extends BaseCnd implements Serializable {
	public String getNewDealPassWord() {
		return newDealPassWord;
	}

	public void setNewDealPassWord(String newDealPassWord) {
		this.newDealPassWord = newDealPassWord;
	}

	public String getNewLoginPassWord() {
		return newLoginPassWord;
	}

	public void setNewLoginPassWord(String newLoginPassWord) {
		this.newLoginPassWord = newLoginPassWord;
	}

	private static final long serialVersionUID = -5890980057423538506L;
	/**
	 * 排序字段
	 */
	private String sortName;

	/**
	 * 排序方式：asc/desc
	 */
	private String sortMode;
	/**
	 * 主键ID
	 */
	private Integer id;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 用户ID
	 */
	private Integer userId;
	/**
	 * email
	 */
	private String email;
	/**
	 * 真实姓名
	 */
	private String realName;

	/**
	 * 手机号码
	 */
	private String mobileNum;

	/**
	 * 注册日期 开始时间
	 */
	private String addtimeBegin;

	/**
	 * 注册日期 结束时间
	 */
	private String addtimeEnd;

	/**
	 * 注册日期 开始时间
	 */
	private String addtimeBeginStr;
	/** 用户状态 （-1：账号注销 -2:待审核 -3：审核不通过 0：正常状态） */
	private Integer status;
	/**
	 * 用户类型
	 */
	private Integer usersType;

	/**
	 * 交易密码
	 */
	private String newDealPassWord;
	/**
	 * 登录密码
	 */
	private String accountTotalStatrt;

    private Integer isCustody;

	private String accountTotalEnd;
	private String newLoginPassWord;
	private String useTotalStatrt;
	private String useTotalEnd;
	private String curTotalEnd;
	private String curTotalStatrt;
	public String getAccountTotalStatrt() {
		return accountTotalStatrt;
	}

	public void setAccountTotalStatrt(String accountTotalStatrt) {
		this.accountTotalStatrt = accountTotalStatrt;
	}

	public String getAccountTotalEnd() {
		return accountTotalEnd;
	}

	public void setAccountTotalEnd(String accountTotalEnd) {
		this.accountTotalEnd = accountTotalEnd;
	}

	public String getUseTotalStatrt() {
		return useTotalStatrt;
	}

	public void setUseTotalStatrt(String useTotalStatrt) {
		this.useTotalStatrt = useTotalStatrt;
	}

	public String getUseTotalEnd() {
		return useTotalEnd;
	}

	public void setUseTotalEnd(String useTotalEnd) {
		this.useTotalEnd = useTotalEnd;
	}

	public String getCurTotalEnd() {
		return curTotalEnd;
	}

	public void setCurTotalEnd(String curTotalEnd) {
		this.curTotalEnd = curTotalEnd;
	}

	public String getCurTotalStatrt() {
		return curTotalStatrt;
	}

	public void setCurTotalStatrt(String curTotalStatrt) {
		this.curTotalStatrt = curTotalStatrt;
	}

	public Integer getUsersType() {
		return usersType;
	}

	public void setUsersType(Integer usersType) {
		this.usersType = usersType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

    public Integer getIsCustody() {
        return isCustody;
    }

    public void setIsCustody(Integer isCustody) {
        this.isCustody = isCustody;
    }

	/**
	 * 注册日期 结束时间
	 */
	private String addtimeEndStr;
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
	/**
	 *用户来源
	 */
	private String memberSource;
	
	public String getMemberSource() {
		return memberSource;
	}

	public void setMemberSource(String memberSource) {
		this.memberSource = memberSource;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public String getAddtimeBegin() {
		return addtimeBegin;
	}

	public void setAddtimeBegin(String addtimeBegin) {
		this.addtimeBegin = addtimeBegin;
	}

	public String getAddtimeEnd() {
		return addtimeEnd;
	}

	public void setAddtimeEnd(String addtimeEnd) {
		this.addtimeEnd = addtimeEnd;
	}

	public String getAddtimeBeginStr() {
		if (!StringUtils.isEmpty(getAddtimeBegin())) {
			return (String.valueOf(DateUtils.parse(getAddtimeBegin() + " 00:00:00", DateUtils.YMD_HMS).getTime() / 1000L));
		}
		return addtimeBeginStr;
	}

	public void setAddtimeBeginStr(String addtimeBeginStr) {
		this.addtimeBeginStr = addtimeBeginStr;
	}

	public String getAddtimeEndStr() {
		if (!StringUtils.isEmpty(getAddtimeEnd())) {
			return (String.valueOf(DateUtils.parse(getAddtimeEnd() + " 23:59:59", DateUtils.YMD_HMS).getTime() / 1000L));
		}
		return addtimeEndStr;
	}

	public void setAddtimeEndStr(String addtimeEndStr) {
		this.addtimeEndStr = addtimeEndStr;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getSortMode() {
		return sortMode;
	}

	public void setSortMode(String sortMode) {
		this.sortMode = sortMode;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getUsebeginTime() {
		return usebeginTime;
	}

	public void setUsebeginTime(String usebeginTime) {
		this.usebeginTime = usebeginTime;
	}

	public String getUseendTime() {
		return useendTime;
	}

	public void setUseendTime(String useendTime) {
		this.useendTime = useendTime;
	}

	public Integer getRedstatus() {
		return redstatus;
	}

	public void setRedstatus(Integer redstatus) {
		this.redstatus = redstatus;
	}
}