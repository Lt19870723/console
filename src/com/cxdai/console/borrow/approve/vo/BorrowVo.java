package com.cxdai.console.borrow.approve.vo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.cxdai.console.base.entity.Borrow;
import com.cxdai.console.common.Dictionary;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.MoneyUtil;
import com.cxdai.console.util.SimpleMoneyFormat;

/**
 * <p>
 * Description:借款标<br />
 * </p>
 * 
 * @title BorrowVo.java
 * @package com.cxdai.portal.borrow.vo
 * @author justin.xu
 * @version 0.1 2014年5月15日
 */
public class BorrowVo extends Borrow implements Serializable {

	private static final long serialVersionUID = -662710165519685271L;
	// 临时属性.............
	/** 进度 **/
	private String scheduleStr;
	/** 借款人用户名 **/
	private String userName;
	/** 还款时间 **/
	private String repaymentTime;
	/** 借款人头像 **/
	private String headimg;
	/** 还款方式 **/
	private String styleStr;
	/** 定时发标时间 */
	private String timingBorrowTimeStr;
	/** 发布时间 */
	private String publishTimeStr;
	/** 还款时间 **/
	private String repaymentTimeStr;
	private String addtimeStr;
	private String successTimeStr;
	private Date successTimeDate;
	private String endTimeStr;
	private Date endTimeDate;

	private String accountStr;
	private String statusStr;

	// begin export to excle need fied add by hujianpan
	private String borrowTypeStrExp;
	private String accountStrExp;
	private String aprStr;
	private String styleStrExp;
	private String publishTimeYmdhms;
	private String validTimeYmdhms;
	private String appraStatusStr;
	private String repaymentAccountSrt;

	/** 金额转换为大写 */
	private String accountToChinese;
	/** 完整时间期限 */
	private String timelimitformat;

	/** 应付利息 */
	private BigDecimal interest;
	/** 应付还款时间 */
	private Date shouldRepaymentTime;
	/** 实付利息 */
	private BigDecimal actualInterest;
	/** 实付支付时间 */
	private Date repaymentYestime;
	/** 罚息 */
	private BigDecimal lateInterest;

	/** 应付还款时间 */
	private String shouldRepaymentTimeStr;
	/** 实付支付时间 */
	private String repaymentYestimeStr;

	private String interestStrExp;
	private String actualInterestStrExp;
	private String lateInterestStrExp;
	private String borrowTypeName;
	private String styleName;
	private BigDecimal accountTotal;
	private BigDecimal repaymentAccountTotal;
	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public BigDecimal getActualInterest() {
		return actualInterest;
	}

	public void setActualInterest(BigDecimal actualInterest) {
		this.actualInterest = actualInterest;
	}

	public Date getShouldRepaymentTime() {
		return shouldRepaymentTime;
	}

	public void setShouldRepaymentTime(Date shouldRepaymentTime) {
		this.shouldRepaymentTime = shouldRepaymentTime;
	}

	public Date getRepaymentYestime() {
		return repaymentYestime;
	}

	public void setRepaymentYestime(Date repaymentYestime) {
		this.repaymentYestime = repaymentYestime;
	}

	public String getShouldRepaymentTimeStr() {

		if (null != shouldRepaymentTime) {
			return DateUtils.format(shouldRepaymentTime, DateUtils.YMD_HMS);
		}

		return shouldRepaymentTimeStr;
	}

	public void setShouldRepaymentTimeStr(String shouldRepaymentTimeStr) {
		this.shouldRepaymentTimeStr = shouldRepaymentTimeStr;
	}

	public String getRepaymentYestimeStr() {

		if (null != repaymentYestime) {
			return DateUtils.format(repaymentYestime, DateUtils.YMD_HMS);
		}

		return repaymentYestimeStr;
	}

	public void setRepaymentYestimeStr(String repaymentYestimeStr) {
		this.repaymentYestimeStr = repaymentYestimeStr;
	}

	public BigDecimal getLateInterest() {
		return lateInterest;
	}

	public void setLateInterest(BigDecimal lateInterest) {
		this.lateInterest = lateInterest;
	}

	public String getRepaymentAccountSrt() {
		return MoneyUtil.fmtMoneyByDot(this.getRepaymentAccount());
	}

	public String getAppraStatusStr() {
		/** -1:草稿标 0：待审核；1：初审通过，2：反欺诈通过，3：终审通过，4：复审通过； */
		return this.getApprstatus() == -1 ? "草稿标" : (this.getApprstatus() == 0 ? "待审核" : (this.getApprstatus() == 1 ? "初审通过"
				: (this.getApprstatus() == 2 ? "反欺诈通过" : (this.getApprstatus() == 3 ? "终审通过" : (this.getApprstatus() == 4 ? "复审通过" : "")))));
	}

	public String getValidTimeYmdhms() {
		if (null != this.getValidTime()) {
			return this.getValidTime().toString();
		}
		return "";

	}

	public String getPublishTimeYmdhms() {
		if (null != this.getPublishTime()) {
			return DateUtils.timeStampToDate(this.getPublishTime(), DateUtils.YMD_HMS);
		}
		return publishTimeYmdhms;
	}

	public String getStyleStrExp() {
		if (this.getStyle() == 0) {
			return "没有限制";
		} else if (this.getStyle() == 1) {
			return "等额本息";
		} else if (this.getStyle() == 2) {
			return "按月付息到期还本";
		} else if (this.getStyle() == 3) {
			return "到期还本付息";
		} else if (this.getStyle() == 4) {
			return "按天还款";
		}
		return styleStrExp;
	}

	public String getAprStr() {
		if (null != this.getApr()) {
			return MoneyUtil.fmtMoneyByDot(this.getApr());
		}

		return aprStr;
	}

	public String getBorrowTypeStrExp() {
		return this.getBorrowtype() == 1 ? "信用标" : (this.getBorrowtype() == 2 ? "抵押标" : (this.getBorrowtype() == 3 ? "净值标"
				: (this.getBorrowtype() == 5 ? "担保标" : "秒标")));
	}

	public String getAccountStrExp() {
		if (null != this.getAccount()) {
			return MoneyUtil.fmtMoneyByDot(this.getAccount());
		}
		return accountStrExp;
	}

	// end export to excle need fied
	public String getScheduleStr() {
		if (null != super.getAccountYes()) {
			scheduleStr = String.valueOf(super.getAccountYes().multiply(new BigDecimal(100)).divide(super.getAccount(), 2, BigDecimal.ROUND_DOWN));
		} else {
			scheduleStr = "0";
		}
		return scheduleStr;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRepaymentTime() {
		return repaymentTime;
	}

	public void setRepaymentTime(String repaymentTime) {
		this.repaymentTime = repaymentTime;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public String getStyleStr() {
		if (null != super.getStyle()) {
			return Dictionary.getValue(400, String.valueOf(super.getStyle()));
		}
		return styleStr;
	}

	public void setStyleStr(String styleStr) {
		this.styleStr = styleStr;
	}

	public String getTimingBorrowTimeStr() {
		if (super.getTimingBorrowTime() != null) {
			return DateUtils.timeStampToDate(super.getTimingBorrowTime(), DateUtils.YMD_HMS);
		}
		return timingBorrowTimeStr;
	}

	public void setTimingBorrowTimeStr(String timingBorrowTimeStr) {
		this.timingBorrowTimeStr = timingBorrowTimeStr;
	}

	public String getPublishTimeStr() {
		if (super.getPublishTime() != null) {
			return DateUtils.timeStampToDate(super.getPublishTime(), DateUtils.YMD_HMS);
		}
		return publishTimeStr;
	}

	public void setPublishTimeStr(String publishTimeStr) {
		this.publishTimeStr = publishTimeStr;
	}

	public String getRepaymentTimeStr() {
		if (repaymentTime != null) {
			return DateUtils.timeStampToDate(repaymentTime, DateUtils.YMD_HMS);
		}
		return repaymentTimeStr;
	}

	public void setRepaymentTimeStr(String repaymentTimeStr) {
		this.repaymentTimeStr = repaymentTimeStr;
	}

	public String getAddtimeStr() {
		if (super.getAddtime() != null) {
			return DateUtils.format(super.getAddtime(), DateUtils.YMD_HMS);
		}
		return addtimeStr;
	}

	public void setAddtimeStr(String addtimeStr) {
		this.addtimeStr = addtimeStr;
	}

	public String getSuccessTimeStr() {
		if (super.getSuccessTime() != null) {
			return DateUtils.timeStampToDate(super.getSuccessTime(), DateUtils.YMD_HMS);
		}
		return successTimeStr;
	}

	public void setSuccessTimeStr(String successTimeStr) {
		this.successTimeStr = successTimeStr;
	}

	public String getEndTimeStr() {
		if (super.getEndTime() != null) {
			return DateUtils.timeStampToDate(super.getEndTime(), DateUtils.YMD_HMS);
		}
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

	public String getAccountStr() {
		return accountStr;
	}

	public void setAccountStr(String accountStr) {
		this.accountStr = accountStr;
	}

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public Date getSuccessTimeDate() {
		if (null != super.getSuccessTime()) {
			return DateUtils.timeStampToDate(super.getSuccessTime());
		}
		return successTimeDate;
	}

	public String getAccountToChinese() {
		if (null != super.getAccount()) {
			return SimpleMoneyFormat.getInstance().format(super.getAccount());
		}
		return "";
	}

	public String getTimelimitformat() {
		if (super.getBorrowtype() == 4) {
			return "秒还";
		}
		if (super.getStyle() == 4) {
			return super.getTimeLimit() + "天";
		}
		return super.getTimeLimit() + "个月";
	}

	public Date getEndTimeDate() {
		if (null != super.getEndTime()) {
			return DateUtils.timeStampToDate(super.getEndTime());
		}
		return endTimeDate;
	}

	public String getInterestStrExp() {
		if (null != interest) {
			return MoneyUtil.fmtMoneyByDot(interest);
		}
		return interestStrExp;
	}

	public void setInterestStrExp(String interestStrExp) {
		this.interestStrExp = interestStrExp;
	}

	public String getActualInterestStrExp() {
		if (null != actualInterest) {
			return MoneyUtil.fmtMoneyByDot(actualInterest);
		}
		return actualInterestStrExp;
	}

	public void setActualInterestStrExp(String actualInterestStrExp) {
		this.actualInterestStrExp = actualInterestStrExp;
	}

	public String getLateInterestStrExp() {
		if (null != lateInterest) {
			return MoneyUtil.fmtMoneyByDot(lateInterest);
		}
		return lateInterestStrExp;
	}

	public void setLateInterestStrExp(String lateInterestStrExp) {
		this.lateInterestStrExp = lateInterestStrExp;
	}

	public String getBorrowTypeName() {
		if (null != super.getBorrowtype()) {
			return Dictionary.getValue(300, String.valueOf(super.getBorrowtype()));
		}
		return borrowTypeName;
	}

	public void setBorrowTypeName(String borrowTypeName) {
		this.borrowTypeName = borrowTypeName;
	}

	public String getStyleName() {
		if (null != super.getStyle()) {
			return Dictionary.getValue(400, String.valueOf(super.getStyle()));
		}
		return styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}

	public BigDecimal getAccountTotal() {
		return accountTotal;
	}

	public void setAccountTotal(BigDecimal accountTotal) {
		this.accountTotal = accountTotal;
	}

	public BigDecimal getRepaymentAccountTotal() {
		return repaymentAccountTotal;
	}

	public void setRepaymentAccountTotal(BigDecimal repaymentAccountTotal) {
		this.repaymentAccountTotal = repaymentAccountTotal;
	}
	
}