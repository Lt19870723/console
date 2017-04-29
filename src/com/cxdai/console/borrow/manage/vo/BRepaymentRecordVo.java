package com.cxdai.console.borrow.manage.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.MoneyUtil;

 
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
public class BRepaymentRecordVo extends BRepaymentRecord implements Serializable {
	private static final long serialVersionUID = -1464099049609856305L;
	private Integer borrowId;
	/** 借款标名字 */
	private String name;
	/** 还款方式【 0：没有限制， 1：按月分期还款，2：按月付息到期还本 , 3：到期还本付息,4:按天到期还款 */
	private Integer style;
	/** 借款标种类（ 1：信用标，2：抵押标，3：净值标，4：秒标 5：担保标 ） */
	private Integer borrowtype;
	/** 借款期限 */
	private Integer timeLimit;
	/** 年利率 */
	private BigDecimal apr;
	private String username;
	private String repaymentTimeStr;
	private String repaymentYestimeStr;
	private Integer borrowStatus;
	private BigDecimal firstAccount;

	/** 导出Excel属性 */
	private String borrowtypeStr; // 借款标种类
	private String periods; // 期数
	private String statusStr; // 状态

	// 临时属性
	private String repaymentAccountStr;
	private String interestStr;
	private String capitalStr;
	private String dealUserName;
	private Date readRepaymentTime;
	private String readRepaymentTimeStr;

	int earyDay = 0; // 还款提前天数

	/** 合同编号 */
	private String contractNo;
	/** 还款前可解锁的直通车金额 */
	private BigDecimal unlockFirstAccount;

	/** 需充值金额 */
	private BigDecimal needRechargeAccount;
	/**日期型应还款时间*/
	private Date repaymentTimeDate;
	private String successTime;
	
	/**不带小数的期数*/
	private String orderString;

	private Integer eSuccess;
	private BigDecimal eSucSun;
	private Integer eFailCount;
	private BigDecimal eFailSum;
	private Integer isCustody;


	private String eFailSumStr;

	
	public String getSuccessTime() {
		return successTime;
	}

	public void setSuccessTime(String successTime) {
		this.successTime = successTime;
	}

	public String getOrderString() {
		if (null != super.getOrder()) {			
			return super.getOrder().toString();
		}
		return orderString;
	}

	public void setOrderString(String orderString) {
		this.orderString = orderString;
	}

	public Date getRepaymentTimeDate() {
		if (null != super.getRepaymentTime()) {
			return DateUtils.timeStampToDate(super.getRepaymentTime());
		}
		return repaymentTimeDate;
	}

	public void setRepaymentTimeDate(Date repaymentTimeDate) {
		this.repaymentTimeDate = repaymentTimeDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStyle() {
		return style;
	}

	public void setStyle(Integer style) {
		this.style = style;
	}

	public Integer getBorrowtype() {
		return borrowtype;
	}

	public void setBorrowtype(Integer borrowtype) {
		this.borrowtype = borrowtype;
	}

	public Integer getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}

	public BigDecimal getApr() {
		return apr;
	}

	public void setApr(BigDecimal apr) {
		this.apr = apr;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public Integer getBorrowId() {
		return borrowId;
	}

	@Override
	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}

	public String getRepaymentTimeStr() {
		if (super.getRepaymentTime() != null && !super.getRepaymentTime().equals("")) {
			return DateUtils.timeStampToDate(super.getRepaymentTime(), DateUtils.YMD_DASH);
		}
		return repaymentTimeStr;
	}

	public void setRepaymentTimeStr(String repaymentTimeStr) {
		this.repaymentTimeStr = repaymentTimeStr;
	}

	public String getRepaymentYestimeStr() {
		if (super.getRepaymentYestime() != null && !super.getRepaymentYestime().equals("")) {
			return DateUtils.timeStampToDate(super.getRepaymentYestime(), DateUtils.YMD_DASH);
		}
		return repaymentYestimeStr;
	}

	public void setRepaymentYestimeStr(String repaymentYestimeStr) {
		this.repaymentYestimeStr = repaymentYestimeStr;
	}

	public int getEaryDay() {
		return earyDay;
	}

	public void setEaryDay(int earyDay) {
		this.earyDay = earyDay;
	}

	public Integer getBorrowStatus() {
		return borrowStatus;
	}

	public void setBorrowStatus(Integer borrowStatus) {
		this.borrowStatus = borrowStatus;
	}

	public BigDecimal getFirstAccount() {
		return firstAccount;
	}

	public void setFirstAccount(BigDecimal firstAccount) {
		this.firstAccount = firstAccount;
	}

	public String getBorrowtypeStr() {
		if (borrowtype != null) {
			if (borrowtype == 1) {
				return "信用标";
			}
			if (borrowtype == 2) {
				return "抵押标";
			}
			if (borrowtype == 3) {
				return "净值标";
			}
			if (borrowtype == 4) {
				return "秒标";
			}
			if (borrowtype == 5) {
				return "担保标";
			}
		}
		return borrowtypeStr;
	}

	public void setBorrowtypeStr(String borrowtypeStr) {
		this.borrowtypeStr = borrowtypeStr;
	}

	public String getPeriods() {
		if (style != null) {
			if (style == 1 || style == 2) {
				return super.getOrder() + "/" + timeLimit;
			} else {
				return "1/1";
			}
		}
		return periods;
	}

	public void setPeriods(String periods) {
		this.periods = periods;
	}

	public String getStatusStr() {
		if (super.getStatus() != null && super.getWebstatus() != null) {
			if (super.getStatus() == 0 && super.getWebstatus() == 0) {
				return "未还款";
			}
			if (super.getStatus() == 0 && super.getWebstatus() == 1) {
				return "已垫付";
			}
			if (super.getStatus() == 1) {
				return "已还款";
			}
		}
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public String getRepaymentAccountStr() {
		if (super.getRepaymentAccount() != null) {
			return MoneyUtil.fmtMoneyByDot(super.getRepaymentAccount());
		}
		return repaymentAccountStr;
	}

	public void setRepaymentAccountStr(String repaymentAccountStr) {
		this.repaymentAccountStr = repaymentAccountStr;
	}

	public String getInterestStr() {
		if (super.getInterest() != null) {
			return MoneyUtil.fmtMoneyByDot(super.getInterest());
		}
		return interestStr;
	}

	public void setInterestStr(String interestStr) {
		this.interestStr = interestStr;
	}

	public String getCapitalStr() {
		if (super.getCapital() != null) {
			return MoneyUtil.fmtMoneyByDot(super.getCapital());
		}
		return capitalStr;
	}

	public void setCapitalStr(String capitalStr) {
		this.capitalStr = capitalStr;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public BigDecimal getUnlockFirstAccount() {
		return unlockFirstAccount;
	}

	public void setUnlockFirstAccount(BigDecimal unlockFirstAccount) {
		this.unlockFirstAccount = unlockFirstAccount;
	}

	public String getDealUserName() {
		return dealUserName;
	}

	public void setDealUserName(String dealUserName) {
		this.dealUserName = dealUserName;
	}

	public Date getReadRepaymentTime() {
		return readRepaymentTime;
	}

	public void setReadRepaymentTime(Date readRepaymentTime) {
		this.readRepaymentTime = readRepaymentTime;
	}

	public String getReadRepaymentTimeStr() {
		Date registerTime = getReadRepaymentTime();
		if (registerTime != null) {
			return DateUtils.format(registerTime, DateUtils.YMD_HMS);
		}
		return readRepaymentTimeStr;
	}

	public void setReadRepaymentTimeStr(String readRepaymentTimeStr) {
		this.readRepaymentTimeStr = readRepaymentTimeStr;
	}

	public BigDecimal getNeedRechargeAccount() {
		return needRechargeAccount;
	}

	public void setNeedRechargeAccount(BigDecimal needRechargeAccount) {
		this.needRechargeAccount = needRechargeAccount;
	}

	public Integer getIsCustody() {
		return isCustody;
	}

	public void setIsCustody(Integer isCustody) {
		this.isCustody = isCustody;
	}
	public Integer geteSuccess() {
		return eSuccess;
	}

	public void seteSuccess(Integer eSuccess) {
		this.eSuccess = eSuccess;
	}

	public BigDecimal geteSucSun() {
		return eSucSun;
	}

	public void seteSucSun(BigDecimal eSucSun) {
		this.eSucSun = eSucSun;
	}

	public Integer geteFailCount() {
		return eFailCount;
	}

	public void seteFailCount(Integer eFailCount) {
		this.eFailCount = eFailCount;
	}

	public BigDecimal geteFailSum() {
		return eFailSum;
	}

	public void seteFailSum(BigDecimal eFailSum) {
		this.eFailSum = eFailSum;
	}


	public String geteFailSumStr() {

		if (geteFailSum() != null) {
			return MoneyUtil.fmtMoneyByDot(geteFailSum().divide(new BigDecimal(100),8, BigDecimal.ROUND_HALF_UP));
		}
		return eFailSumStr;
	}

	public void seteFailSumStr(String eFailSumStr) {
		this.eFailSumStr = eFailSumStr;
	}
}
