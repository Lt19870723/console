package com.cxdai.console.account.cash.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.cxdai.console.common.Dictionary;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.MoneyUtil;

/**
 * <p>
 * Description:提现记录Vo<br />
 * </p>
 * 
 * @title CashRecordVo.java
 * @package com.cxdai.console.account.vo
 * @author justin.xu
 * @version 0.1 2014年6月25日
 */
public class CashRecordVo implements Serializable {
	private static final long serialVersionUID = 253259912412431081L;

	private Integer id;
	private Integer userId;
	/** 状态【-1：审核失败；0：申请提现；1：审核通过；2：打款结束】 3:取消提现 */
	private Integer status;
	/** 账户 */
	private String account;
	/** 银行 */
	private String bank;
	/** 所属分行 */
	private String branch;
	/** 总金额 */
	private BigDecimal total;
	/** 到账总额 */
	private BigDecimal credited;
	/** 手续费 */
	private BigDecimal fee;
	/** 审核人ID */
	private Integer verifyUserid;
	/** 审核时间 */
	private String verifyTime;
	/** 审核备注 */
	private String verifyRemark;
	/** 打款ID */
	private Integer verifyUserid2;
	/** 打款时间 */
	private String verifyTime2;
	/** 打款备注 */
	private String verifyRemark2;
	/** 作废ID */
	private Integer verifyUserid3;
	/** 作废时间 */
	private String verifyTime3;
	/** 作废备注 */
	private String verifyRemark3;
	/** 添加时间 */
	private String addtime;
	/** 添加ip */
	private String addip;
	/** 版本号. **/
	private Integer version;
	/** 是否已导出(0：未导出，1：已导出) */
	private Integer isExport;
	/** 导出人 */
	private Integer exportUserId;
	/** 导出人 */
	private String exportUserName;
	/** 用户名 */
	private String username;
	/** 初审人 */
	private String verifyName;
	/** 终审人 */
	private String verifyName2;
	/** 真实姓名 */
	private String realname;
	private Integer isFinancialUser;
	/** 格式转换 */
	private String statusStr;
	private String addtimeymdhms;
	private String totalStr;
	private String creditedStr;
	private String feeStr;
	private String verifyTimeYmdhms;
	private String verifyTime2Ymdhms;
	private String addtimeYmd;
	private String verifyTimeYmd;
	private String verifyTime2Ymd;
	private String isFinancialUserStr;
    private String bizNo;
    private Integer isCustody;
    private String etradeNo;
	public String getEtradeNo() {
		return etradeNo;
	}

	public void setEtradeNo(String etradeNo) {
		this.etradeNo = etradeNo;
	}

	public Integer getIsFinancialUser() {
		return isFinancialUser;
	}

	public void setIsFinancialUser(Integer isFinancialUser) {
		this.isFinancialUser = isFinancialUser;
	}

	public String getIsFinancialUserStr() {
		if (isFinancialUser == 1) {
			return "理财用户";
		} else if (isFinancialUser == 0) {
			return "借款用户";
		}
		return "";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getCredited() {
		return credited;
	}

	public void setCredited(BigDecimal credited) {
		this.credited = credited;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public Integer getVerifyUserid() {
		return verifyUserid;
	}

	public void setVerifyUserid(Integer verifyUserid) {
		this.verifyUserid = verifyUserid;
	}

	public String getVerifyTime() {
		return verifyTime;
	}

	public void setVerifyTime(String verifyTime) {
		this.verifyTime = verifyTime;
	}

	public String getVerifyRemark() {
		return verifyRemark;
	}

	public void setVerifyRemark(String verifyRemark) {
		this.verifyRemark = verifyRemark;
	}

	public Integer getVerifyUserid2() {
		return verifyUserid2;
	}

	public void setVerifyUserid2(Integer verifyUserid2) {
		this.verifyUserid2 = verifyUserid2;
	}

	public String getVerifyTime2() {
		return verifyTime2;
	}

	public void setVerifyTime2(String verifyTime2) {
		this.verifyTime2 = verifyTime2;
	}

	public String getVerifyRemark2() {
		return verifyRemark2;
	}

	public void setVerifyRemark2(String verifyRemark2) {
		this.verifyRemark2 = verifyRemark2;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getAddip() {
		return addip;
	}

	public void setAddip(String addip) {
		this.addip = addip;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getVerifyName() {
		return verifyName;
	}

	public void setVerifyName(String verifyName) {
		this.verifyName = verifyName;
	}

	public String getVerifyName2() {
		return verifyName2;
	}

	public void setVerifyName2(String verifyName2) {
		this.verifyName2 = verifyName2;
	}

	public Integer getVerifyUserid3() {
		return verifyUserid3;
	}

	public void setVerifyUserid3(Integer verifyUserid3) {
		this.verifyUserid3 = verifyUserid3;
	}

	public String getVerifyTime3() {
		return verifyTime3;
	}

	public void setVerifyTime3(String verifyTime3) {
		this.verifyTime3 = verifyTime3;
	}

	public String getVerifyRemark3() {
		return verifyRemark3;
	}

	public void setVerifyRemark3(String verifyRemark3) {
		this.verifyRemark3 = verifyRemark3;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getStatusStr() {
		return Dictionary.getValue(802, String.valueOf(status));
	}

	public String getAddtimeymdhms() {
		if (null != addtime) {
			return DateUtils.timeStampToDate(addtime, DateUtils.YMD_HMS);
		}
		return addtimeymdhms;
	}

	public String getTotalStr() {
		if (null != total) {
			return MoneyUtil.fmtMoneyByDot(total);
		}
		return totalStr;
	}

	public String getCreditedStr() {
		if (null != credited) {
			return MoneyUtil.fmtMoneyByDot(credited);
		}
		return creditedStr;
	}

	public String getFeeStr() {
		if (null != fee) {
			return MoneyUtil.fmtMoneyByDot(fee);
		}
		return feeStr;
	}

	public String getVerifyTimeYmdhms() {
		if (null != verifyTime) {
			return DateUtils.timeStampToDate(verifyTime, DateUtils.YMD_HMS);
		}
		return verifyTimeYmdhms;
	}

	public String getVerifyTime2Ymdhms() {
		if (null != verifyTime2) {
			return DateUtils.timeStampToDate(verifyTime2, DateUtils.YMD_HMS);
		}
		return verifyTime2Ymdhms;
	}

	public Integer getIsExport() {
		return isExport;
	}

	public void setIsExport(Integer isExport) {
		this.isExport = isExport;
	}

	public Integer getExportUserId() {
		return exportUserId;
	}

	public void setExportUserId(Integer exportUserId) {
		this.exportUserId = exportUserId;
	}

	public String getExportUserName() {
		return exportUserName;
	}

	public void setExportUserName(String exportUserName) {
		this.exportUserName = exportUserName;
	}

	public String getAddtimeYmd() {
		if (null != addtime) {
			return DateUtils.timeStampToDate(addtime, DateUtils.YMD_DASH);
		}
		return addtimeYmd;
	}

	public void setAddtimeYmd(String addtimeYmd) {
		this.addtimeYmd = addtimeYmd;
	}

	public String getVerifyTimeYmd() {
		if (null != verifyTime) {
			return DateUtils.timeStampToDate(verifyTime, DateUtils.YMD_DASH);
		}
		return verifyTimeYmd;
	}

	public void setVerifyTimeYmd(String verifyTimeYmd) {
		this.verifyTimeYmd = verifyTimeYmd;
	}

	public String getVerifyTime2Ymd() {
		if (null != verifyTime2) {
			return DateUtils.timeStampToDate(verifyTime2, DateUtils.YMD_DASH);
		}
		return verifyTime2Ymd;
	}

	public void setVerifyTime2Ymd(String verifyTime2Ymd) {
		this.verifyTime2Ymd = verifyTime2Ymd;
	}

    public String getBizNo() {
        return bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }

    public Integer getIsCustody() {
        return isCustody;
    }

    public void setIsCustody(Integer isCustody) {
        this.isCustody = isCustody;
    }
}
