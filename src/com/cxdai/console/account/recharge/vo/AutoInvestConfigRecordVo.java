package com.cxdai.console.account.recharge.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.cxdai.console.base.entity.AutoInvestConfigRecord;
import com.cxdai.console.util.DateUtils;

/**
 * 
 * <p>
 * Description:自动投标规则日志记录<br />
 * </p>
 * 
 * @title AutoInvestConfigRecordVo.java
 * @package com.cxdai.console.account.vo
 * @author yangshijinO
 * @version 0.1 2014年11月28日
 */
public class AutoInvestConfigRecordVo extends AutoInvestConfigRecord implements Serializable {
	private static final long serialVersionUID = -5816997501577003675L;

	private Integer borrowType;
	private String borrowTypeStr;

	private String username;

	/**
	 * 投标金额
	 */
	private BigDecimal tender_record_accout;

	/**
	 * 可用余额
	 */
	private BigDecimal useMoney;

	private String addtimeStamp;

	public Integer getBorrowType() {
		return borrowType;
	}

	public void setBorrowType(Integer borrowType) {
		this.borrowType = borrowType;
	}

	public String getBorrowTypeStr() {
		if (null != borrowType) {
			if (borrowType == 1) {
				return "推荐标";
			} else if (borrowType == 2) {
				return "抵押标";
			} else if (borrowType == 3) {
				return "净值标";
			} else if (borrowType == 4) {
				return "秒标";
			} else if (borrowType == 5) {
				return "担保标";
			}
		}
		return "";
	}

	public void setBorrowTypeStr(String borrowTypeStr) {
		this.borrowTypeStr = borrowTypeStr;
	}

	public BigDecimal getTender_record_accout() {
		return tender_record_accout;
	}

	public void setTender_record_accout(BigDecimal tender_record_accout) {
		this.tender_record_accout = tender_record_accout;
	}

	public BigDecimal getUseMoney() {
		return useMoney;
	}

	public void setUseMoney(BigDecimal useMoney) {
		this.useMoney = useMoney;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddtimeStamp() {
		if (super.getAddtime() != null) {
			return String.valueOf(DateUtils.dateTime2TimeStamp(DateUtils.format(super.getAddtime(), DateUtils.YMD_HMS)));
		}
		return addtimeStamp;
	}

	public void setAddtimeStamp(String addtimeStamp) {
		this.addtimeStamp = addtimeStamp;
	}
}
