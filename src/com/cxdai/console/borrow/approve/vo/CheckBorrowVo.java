package com.cxdai.console.borrow.approve.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.cxdai.console.common.Dictionary;
import com.cxdai.console.util.DateUtils;
 

/**
 * <p>
 * Description:借款标审核返回Vo<br />
 * </p>
 * 
 * @title CheckBorrowVo.java
 * @package com.cxdai.console.borrow.vo
 * @author justin.xu
 * @version 0.1 2014年8月13日
 */
public class CheckBorrowVo {

	/** 主键ID */
	private Integer id;
	/** 借款标题 */
	private String name;
	/** 借款标种类（ 1：信用标，2：抵押标，3：净值标，4：秒标 ） */
	private Integer borrowType;
	/** 用户ID */
	private Integer userId;
	/** 借贷总金额 */
	private BigDecimal account;
	/** 年利率 */
	private BigDecimal apr;
	/** 还款方式【 0：没有限制， 1：按月分期还款，2：按月付息到期还本 , 3：到期还本付息,4:按天到期还款 */
	private Integer style;
	/** 有效时间 天数 */
	private Integer validTime;
	/** 定时发标时间 */
	private String timingBorrowTime;
	/** 添加时间 */
	private Date addTime;
	/** 借款期限 */
	private Integer timeLimit;
	/** 抵押标类型（1：新增，2：续贷，3：资产处理） */
	private Integer pledgeType;

	private String username;
	private String borrowTypeName;
	private String styleName;
	private String timingBorrowTimeStr;
	private String creditRating;
	
	private Integer isCustody;
	
	

	public Integer getIsCustody() {
		return isCustody;
	}

	public void setIsCustody(Integer isCustody) {
		this.isCustody = isCustody;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBorrowType() {
		return borrowType;
	}

	public void setBorrowType(Integer borrowType) {
		this.borrowType = borrowType;
	}

	public String getBorrowTypeName() {
		if (null != borrowType) {
			return Dictionary.getValue(300, String.valueOf(borrowType));
		}
		return borrowTypeName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public BigDecimal getAccount() {
		return account;
	}

	public void setAccount(BigDecimal account) {
		this.account = account;
	}

	public BigDecimal getApr() {
		return apr;
	}

	public void setApr(BigDecimal apr) {
		this.apr = apr;
	}

	public int getStyle() {
		return style;
	}

	public void setStyle(int style) {
		this.style = style;
	}

	public String getStyleName() {
		if (null != style) {
			return Dictionary.getValue(400, String.valueOf(style));
		}
		return styleName;
	}

	public int getValidTime() {
		return validTime;
	}

	public void setValidTime(int validTime) {
		this.validTime = validTime;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getTimingBorrowTime() {
		return timingBorrowTime;
	}

	public void setTimingBorrowTime(String timingBorrowTime) {
		this.timingBorrowTime = timingBorrowTime;
	}

	public String getTimingBorrowTimeStr() {
		if (timingBorrowTime != null && !timingBorrowTime.equals("")) {
			return DateUtils.timeStampToDate(timingBorrowTime, DateUtils.YMD_HMS);
		}
		return timingBorrowTimeStr;
	}

	public void setTimingBorrowTimeStr(String timingBorrowTimeStr) {
		this.timingBorrowTimeStr = timingBorrowTimeStr;
	}

	public String getCreditRating() {
		return creditRating;
	}

	public void setCreditRating(String creditRating) {
		this.creditRating = creditRating;
	}

	public Integer getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}

	public Integer getPledgeType() {
		return pledgeType;
	}

	public void setPledgeType(Integer pledgeType) {
		this.pledgeType = pledgeType;
	}
}
