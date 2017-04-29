package com.cxdai.console.borrow.approve.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

import com.cxdai.console.borrow.approve.entity.BTransfer;
import com.cxdai.console.common.Dictionary;
import com.cxdai.console.util.SimpleMoneyFormat;
import com.cxdai.console.util.StrinUtils;

public class BTransferVo extends BTransfer implements Serializable {

	private static final long serialVersionUID = -8768997898952437518L;
	private static DecimalFormat df = new DecimalFormat("#,##0.##");

	/** 借款类型 **/
	private String borrowtype;
	/** 借款人 **/
	private String borrowUserName;

	private Date repaymentTime; // 认购时间

	private BigDecimal repaymentAccount; // 认购金额对应交易金额

	private BigDecimal repaymentCapital; // 认购本金 转让价格

	/** 进度 **/
	private String scheduleStr;

	private Integer intervalDay;// 剩余期限-天数

	private Date nextRepaymentDate;// 下个还款日

	private String userName;

	// 金额转换显示accountReal
	private String accountRealStr;

	// 现利率=（债权价格-转让价格）*360/转让价格/实际剩余天数
	private String nowApr;

	private Integer parentId; // 投标记录表中parentid

	private String userNameSecret; // 隐藏用户名
	private String userNameEncrypt; // 加密用户名

	/** 还款方式 **/
	private String styleStr;

	/** 原始标借款本金 */
	private BigDecimal borrowAccount;
	/** 剩余还款期数 */
	private String remainPeriod;

	/** 原始标借款本金 */
	private String borrowAccountToChinese;

	/** 借款类型 **/
	private String borrowStyleStr;

	public String getAccountRealStr() {
		try {
			accountRealStr = df.format(super.getAccountReal());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accountRealStr;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public BigDecimal getRepaymentAccount() {
		return repaymentAccount;
	}

	public void setRepaymentAccount(BigDecimal repaymentAccount) {
		this.repaymentAccount = repaymentAccount;
	}

	public BigDecimal getRepaymentCapital() {
		return repaymentCapital;
	}

	public void setRepaymentCapital(BigDecimal repaymentCapital) {
		this.repaymentCapital = repaymentCapital;
	}

	public Date getRepaymentTime() {
		return repaymentTime;
	}

	public void setRepaymentTime(Date repaymentTime) {
		this.repaymentTime = repaymentTime;
	}

	public Date getNextRepaymentDate() {
		return nextRepaymentDate;
	}

	public void setNextRepaymentDate(Date nextRepaymentDate) {
		this.nextRepaymentDate = nextRepaymentDate;
	}

	public String getBorrowtype() {
		return borrowtype;
	}

	public void setBorrowtype(String borrowtype) {
		this.borrowtype = borrowtype;
	}

	public String getBorrowUserName() {
		return borrowUserName;
	}

	public void setBorrowUserName(String borrowUserName) {
		this.borrowUserName = borrowUserName;
	}

	public String getScheduleStr() {
		if (null != super.getAccountYes()) {
			scheduleStr = df.format(super.getAccountYes().multiply(new BigDecimal(100)).divide(super.getAccountReal(), 2, BigDecimal.ROUND_DOWN));
		} else {
			scheduleStr = "0";
		}
		return scheduleStr;
	}

	public String getScheduleStrNoDecimal() {
		if (null != super.getAccountYes()) {
			scheduleStr = String.valueOf(super.getAccountYes().multiply(new BigDecimal(100)).divide(super.getAccountReal(), 0, BigDecimal.ROUND_DOWN));
		} else {
			scheduleStr = "0";
		}
		return scheduleStr;
	}

	public String getSubName() {
		if (getBorrowName() == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder(10);
		int l = 0;
		for (int i = 0; i < getBorrowName().length(); i++) {
			char ch = getBorrowName().charAt(i);
			sb.append(ch);
			// 判断是否数字，字母，特殊字符

			if (isChinese(ch)) {
				l += 2;
			} else {
				l += 1;
			}

			if (l >= 10) {
				break;
			}
		}
		sb.append(l >= 10 ? ".." : "");
		return sb.toString();
	}

	public static boolean isChinese(char a) {
		int v = a;
		return (v >= 19968 && v <= 171941);
	}

	public Integer getIntervalDay() {
		return intervalDay;
	}

	public void setIntervalDay(Integer intervalDay) {
		this.intervalDay = intervalDay;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getUserNameSecret() {
		userNameSecret = this.getUserName();
		userNameSecret = StrinUtils.stringSecretTo(userNameSecret);
		return userNameSecret;
	}

	public String getUserNameEncrypt() {
		userNameEncrypt = this.getUserName();
		userNameEncrypt = StrinUtils.stringEncryptEn(userNameEncrypt);
		return userNameEncrypt;
	}

	public String getNowApr() {
		// 按日还款：转让后收益率=原标利率+（1-转让系数）*360/剩余天数
		// 按月还款：转让后收益率=原标利率+24*（1-转让系数）/(剩余月数+1)
		// 月还息到期还本：综合利率=（原标利率*剩余月数+12*（1-转让系数））/剩余月数

		if (null != super.getAccountSurplus()) {
			// nowApr =
			// df.format(super.getAccountSurplus().subtract(super.getAccountReal()).multiply(new
			// BigDecimal(36000)).divide(super.getAccountReal(),8,
			// BigDecimal.ROUND_HALF_UP).divide(new
			// BigDecimal(super.getTimeLimit()),2, BigDecimal.ROUND_HALF_UP));

			Integer styleNum = getBorrowStyle();
			if (styleNum == 4) { // 按天 |
									// (1-getCoef())*360/super.getTimeLimit()+super.getBorrowApr()
				nowApr = df.format((new BigDecimal(1).subtract(super.getCoef())).multiply(new BigDecimal(36000)).divide(new BigDecimal(super.getTimeLimit()), 2, BigDecimal.ROUND_HALF_UP)
						.add(super.getBorrowApr()));
			}
			if (styleNum == 3) { // 按月
				Integer monthNum = (int) ((super.getTimeLimitReal() - 0.5) / 30 + 1);
				nowApr = df.format((new BigDecimal(2400)).multiply(new BigDecimal(1).subtract(super.getCoef())).divide((new BigDecimal(monthNum).add(new BigDecimal(1))), 2, BigDecimal.ROUND_HALF_UP)
						.add(super.getBorrowApr()));
			}
			if (styleNum == 2) { // 月还息到期还本
				nowApr = df.format((new BigDecimal(1200)).multiply(new BigDecimal(1).subtract(super.getCoef())).add(super.getBorrowApr().multiply(new BigDecimal(super.getTimeLimitReal())))
						.divide(new BigDecimal(super.getTimeLimitReal()), 2, BigDecimal.ROUND_HALF_UP));
			}
			if (styleNum == 1) { // 等额本息
				String str1 = super.getBorrowApr().divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP).toString();
				String str2 = super.getCoef().toString();
				double yearactive = Double.parseDouble(str1); // 年利率
				double monthactive = yearactive / 12; // 月利率
				int totalmonth = super.getTimeLimitReal(); // 剩余期数
				double price = 1 - Double.parseDouble(str2); // 1-转让系数
				nowApr = getRealRate(monthactive, totalmonth, price);

			}

		} else {
			nowApr = "0";
		}

		return nowApr;
	}

	public double getMonthmoney(double monthactive, int totalmonth, double price) {
		double totalmoney = 1;
		double monthmoney = (totalmoney - totalmoney * price) * monthactive * (Math.pow((1 + monthactive), totalmonth)) / (Math.pow((1 + monthactive), totalmonth) - 1);
		return monthmoney;
	}

	public String getRealRate(double monthactive, int totalmonth, double price) {
		double protoMonthmoney = getMonthmoney(monthactive, totalmonth, 0);
		double starttmp, endtmp;
		if (price >= 0) {
			starttmp = 0;
			endtmp = 1;
		} else {
			starttmp = -1;
			endtmp = 0;
		}
		while (true) {
			double tmpmonthactive = 0;
			tmpmonthactive = monthactive + endtmp;
			double monthmoney = getMonthmoney(tmpmonthactive, totalmonth, price);
			if (Math.abs(protoMonthmoney - monthmoney) >= 0.0000001) {
				if (protoMonthmoney < monthmoney) {
					endtmp = (starttmp + endtmp) / 2;
				} else {
					double tmp = starttmp;
					starttmp = endtmp;
					endtmp = endtmp + (endtmp - tmp) / 2;
				}
				continue;
			} else {
				return String.format("%.4f", tmpmonthactive * 1200);
			}
		}

	}

	public String getStyleStr() {
		if (null != super.getBorrowStyle()) {
			return Dictionary.getValue(400, String.valueOf(super.getBorrowStyle()));
		}
		return styleStr;
	}

	public void setStyleStr(String styleStr) {
		this.styleStr = styleStr;
	}

	public String getBorrowAccountToChinese() {
		if (null != borrowAccount) {
			return SimpleMoneyFormat.getInstance().format(borrowAccount);
		}
		return borrowAccountToChinese;
	}

	public void setBorrowAccountToChinese(String borrowAccountToChinese) {
		this.borrowAccountToChinese = borrowAccountToChinese;
	}

	public BigDecimal getBorrowAccount() {
		return borrowAccount;
	}

	public void setBorrowAccount(BigDecimal borrowAccount) {
		this.borrowAccount = borrowAccount;
	}

	public String getRemainPeriod() {
		return remainPeriod;
	}

	public void setRemainPeriod(String remainPeriod) {
		this.remainPeriod = remainPeriod;
	}

	public String getBorrowStyleStr() {
		if (super.getBorrowStyle() != null) {
			if (super.getBorrowStyle() == 1) {
				return "等额本息";
			}
			if (super.getBorrowStyle() == 2) {
				return "按月付息到期还本";
			}
			if (super.getBorrowStyle() == 3) {
				return "到期还本付息";
			}
			if (super.getBorrowStyle() == 4) {
				return "按天还款";
			}
		}
		return borrowStyleStr;
	}

	public void setBorrowStyleStr(String borrowStyleStr) {
		this.borrowStyleStr = borrowStyleStr;
	}
}
