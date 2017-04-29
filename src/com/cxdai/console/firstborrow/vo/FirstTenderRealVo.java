package com.cxdai.console.firstborrow.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import com.cxdai.console.common.Constants;
import com.cxdai.console.common.Dictionary;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.MoneyUtil;
 


/**
 * <p>
 * Description:优先投标计划最终认购记录<br />
 * </p>
 * 
 * @title FirstTenderRealVo.java
 * @package com.cxdai.console.first.vo
 * @author justin.xu
 * @version 0.1 2014年10月28日
 */
public class FirstTenderRealVo implements Serializable {
	private static final long serialVersionUID = -191815169850848319L;
	/**
	 * 主键Id
	 */
	private Integer id;
	/**
	 * 优先投标计划ID
	 */
	private Integer firstBorrowId;
	/**
	 * 用户开通金额
	 */
	private Integer account;
	/**
	 * 所占比例,直接精确到小数点位，比如：0.001，即千分之一
	 */
	private BigDecimal rate;
	/**
	 * 用户Id
	 */
	private Integer userId;
	/**
	 * 可用余额
	 */
	private BigDecimal useMoney;
	/**
	 * 计划总金额
	 */
	private Integer planAccount;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 状态 0 ：未失效 1 ：已失效
	 */
	private Integer status;
	/**
	 * 版本号.
	 */
	private String version;
	/**
	 * 添加时间
	 */
	private Date addtime;
	/**
	 * 开通类型（1：首次开通，2：上车）
	 */
	private Integer firstTenderType;

	/**
	 * 上车时间
	 */
	private Date onBusTime;

	/**
	 * 排队号
	 */
	private Integer orderNum;

	/** 直通车标题 */
	private String firstBorrowName;
	private Date firstSuccessTime;
	private Integer firstStatus;
	/** 用户名 */
	private String username;

	private String planAccountStr;// 以万返回千位分隔
	private String accountStr;
	private String useMoneyStr;
	private String rateStr;

	/** 是否可以解锁 是：Y */
	private String unLockYn;
	private String statusStr;

	private String realName;
	/**
	 * 用户解锁金额
	 */
	private Integer unlockAccount;

	/** 认购时间 */
	private Date buyTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFirstBorrowId() {
		return firstBorrowId;
	}

	public void setFirstBorrowId(Integer firstBorrowId) {
		this.firstBorrowId = firstBorrowId;
	}

	public Integer getAccount() {
		return account;
	}

	public void setAccount(Integer account) {
		this.account = account;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public BigDecimal getUseMoney() {
		return useMoney;
	}

	public void setUseMoney(BigDecimal useMoney) {
		this.useMoney = useMoney;
	}

	public Integer getPlanAccount() {
		return planAccount;
	}

	public void setPlanAccount(Integer planAccount) {
		this.planAccount = planAccount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getFirstBorrowName() {
		return firstBorrowName;
	}

	public void setFirstBorrowName(String firstBorrowName) {
		this.firstBorrowName = firstBorrowName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPlanAccountStr() {
		if (null != planAccount) {
			return MoneyUtil.fmtMoneyByDot(new BigDecimal(planAccount));
		}
		return planAccountStr;
	}

	public String getAccountStr() {
		if (null != account) {
			return MoneyUtil.fmtMoneyByDot(new BigDecimal(account));
		}
		return accountStr;
	}

	public String getUseMoneyStr() {
		if (null != useMoney) {
			return MoneyUtil.fmtMoneyByDot(useMoney);
		}
		return useMoneyStr;
	}

	public String getRateStr() {
		if (null != rate) {
			return BusinessConstants.decimalPercentDf.format(rate.multiply(new BigDecimal(100)).setScale(2, RoundingMode.DOWN));
		}
		return rateStr;
	}

	public String getUnLockYn() {
		if (null != addtime) {
			// 获取锁定日期 371 693 //提前解锁国阳资产和国诚金融的直通车 justin.xu 2014-10-18
			Date canlockEndTime = DateUtils.monthOffset(DateUtils.convert2StartDate(addtime), BusinessConstants.FIRST_UNLOCK_AFTER_MONTH);
			if ((new Date().compareTo(canlockEndTime) > 0 || (userId == 371 || userId == 693 || userId == 74288 || userId == 150 || userId == 2))
					&& status == Constants.TENDER_REAL_STATUS_AVAILABLE) {
				return "Y";
			}
		}
		return "";
	}

	public String getStatusStr() {
		return Dictionary.getValue(808, String.valueOf(status));
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Integer getFirstTenderType() {
		return firstTenderType;
	}

	public void setFirstTenderType(Integer firstTenderType) {
		this.firstTenderType = firstTenderType;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getUnlockAccount() {
		if (status == 0 || status == 2) {
			return 0;
		}
		if (status == 1) {
			return account;
		}
		return unlockAccount;
	}

	public void setUnlockAccount(Integer unlockAccount) {
		this.unlockAccount = unlockAccount;
	}

	public Date getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(Date buyTime) {
		this.buyTime = buyTime;
	}

	public Date getOnBusTime() {
		return onBusTime;
	}

	public void setOnBusTime(Date onBusTime) {
		this.onBusTime = onBusTime;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

}
