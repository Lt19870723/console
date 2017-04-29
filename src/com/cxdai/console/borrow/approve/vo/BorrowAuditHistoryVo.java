package com.cxdai.console.borrow.approve.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.cxdai.console.base.entity.Borrow;
import com.cxdai.console.common.Dictionary;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.MoneyUtil;
import com.cxdai.console.util.SimpleMoneyFormat;

 
/**
 * <p>
 * Description:借款标审核日志<br />
 * </p>
 * 
 * @title BorrowAuditHistoryVo
 * @package com.cxdai.portal.borrow.vo
 * @author hujianpan
 * @version 0.1 2014年12月09日
 */
public class BorrowAuditHistoryVo extends Borrow implements Serializable {

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
	/** 审核用户编号 */
	private String jobNum;
	/** 金额转换为大写 */
	private String accountToChinese;
	/** 完整时间期限 */
	private String timelimitformat;
	/** 审核类型 */
	private String auditType;
	/** 审核时间 */
	private String verifyTime;

	public String getVerifyTime() {
		return verifyTime;
	}

	public void setVerifyTime(String verifyTime) {
		this.verifyTime = verifyTime;
	}

	public String getVerifyUser() {
		return verifyUser == null ? "0" : verifyUser;
	}

	public void setVerifyUser(String verifyUser) {
		this.verifyUser = verifyUser;
	}

	public String getVerifyRemark() {

		return verifyRemark == null ? "" : verifyRemark;
	}

	public void setVerifyRemark(String verifyRemark) {
		this.verifyRemark = verifyRemark;
	}

	public String getVerifyDetail() {
		return formartAuditMessage();
	}

	public String getAuditType() {
		return auditType;
	}

	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}

	protected String formartAuditMessage() {
		return "";
	}

	public void setVerifyDetail(String verifyDetail) {
		this.verifyDetail = verifyDetail;
	}

	private String verifyUser;
	private String verifyRemark;

	private String verifyDetail;

	public String getRepaymentAccountSrt() {
		return MoneyUtil.fmtMoneyByDot(this.getRepaymentAccount());
	}

	public String getAppraStatusStr() {
		return BorrowAuditHistoryVo.apprStatus.get(this.getApprstatus());
	}

	public String getValidTimeYmdhms() {
		if (null != this.getValidTime()) {
			return this.getValidTime().toString();
		}
		return "";

	}

	public String getPublishTimeYmdhms() {
		if (null != this.getPublishTime()) {
			return DateUtils.timeStampToDate(this.getPublishTime(),
					DateUtils.YMD_HMS);
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
			return "按天到期还款";
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
		return this.getBorrowtype() == 1 ? "信用标"
				: (this.getBorrowtype() == 2 ? "抵押标"
						: (this.getBorrowtype() == 3 ? "净值标" : (this
								.getBorrowtype() == 5 ? "担保标" : "秒标")));
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
			scheduleStr = String.valueOf(super.getAccountYes()
					.multiply(new BigDecimal(100))
					.divide(super.getAccount(), 2, BigDecimal.ROUND_DOWN));
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
			return DateUtils.timeStampToDate(super.getTimingBorrowTime(),
					DateUtils.YMD_HMS);
		}
		return timingBorrowTimeStr;
	}

	public void setTimingBorrowTimeStr(String timingBorrowTimeStr) {
		this.timingBorrowTimeStr = timingBorrowTimeStr;
	}

	public String getPublishTimeStr() {
		if (super.getPublishTime() != null) {
			return DateUtils.timeStampToDate(super.getPublishTime(),
					DateUtils.YMD_HMS);
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
			return DateUtils.timeStampToDate(super.getSuccessTime(),
					DateUtils.YMD_HMS);
		}
		return successTimeStr;
	}

	public void setSuccessTimeStr(String successTimeStr) {
		this.successTimeStr = successTimeStr;
	}

	public String getEndTimeStr() {
		if (super.getEndTime() != null) {
			return DateUtils.timeStampToDate(super.getEndTime(),
					DateUtils.YMD_HMS);
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

	public String getJobNum() {
		return jobNum;
	}

	public void setJobNum(String jobNum) {
		this.jobNum = jobNum;
	}

	final static Map<Integer, String> apprStatus = new HashMap<Integer, String>();
	{
		apprStatus.put(-1, "草稿标");
		apprStatus.put(0, "待审核");
		apprStatus.put(1, "初审通过");
		apprStatus.put(-1, "初审不通过");
		apprStatus.put(2, "反欺诈审核通过");
		apprStatus.put(-2, "反欺诈审核不通过");
		apprStatus.put(3, "终审通过");
		apprStatus.put(-3, "终审通过");
		apprStatus.put(4, "复审通过");
		apprStatus.put(-4, "复审不通过");
	}
}