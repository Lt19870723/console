package com.cxdai.console.account.recharge.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.cxdai.console.common.Dictionary;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.MoneyUtil;

/**
 * <p>
 * Description:充值记录<br />
 * </p>
 * 
 * @title RechargeRecordVo.java
 * @package com.cxdai.console.account.vo
 * @author justin.xu
 * @version 0.1 2014年6月12日
 */
public class RechargeRecordVo implements Serializable {
	private static final long serialVersionUID = 253259912412431081L;
	private Integer id;
	/** 用户唯一ID */
	private Integer userId;
	/** 类型(1：在线充值，2：线下转账) */
	private Integer type;
	/** 订单号 */
	private String tradeNo;
	/** 状态（0充值审核中，1充值成功，9表示失败，2在线充值待付款， 3初审成功） */
	private Integer status;
	/** 充值金额 */
	private BigDecimal money;
	/** 充值银行 */
	private String payment;
	/** 备注 */
	private String remark;
	/** 充值手续费 */
	private BigDecimal fee;
	/** 初审审核人id(0默认值，表示在线充值) */
	private Integer verifyUserid;
	/** 初审审核时间 */
	private String verifyTime;
	/** 初审审核备注 */
	private String verifyRemark;
	/** 添加时间 */
	private String addtime;
	/** 添加ip */
	private String addip;
	/** 第三方支付平台 1：网银在线 2：国付宝 3：盛付通 */
	private Integer onlinetype;
	/** 终审审核人id */
	private Integer verifyUserid2;
	/** 终审审核时间 */
	private String verifyTime2;
	/** 终审审核备注 */
	private String verifyRemark2;
	/** 银行帐号 */
	private String cardNum;
	/** 银行开户人姓名 */
	private String bankUsername;
	/** 版本号. **/
	private Integer version;

	/** 用户名 */
	private String username;
	/** 初审人 */
	private String verifyName;
	/** 终审人 */
	private String verifyName2;
	/** 真实姓名 */
	private String realname;
	/** 理财/借款用户（1 理财用户 0 借款用户） */
	private Integer isFinancialUser;
	/** 格式转换 */
	private String typeStr;
	private String statusStr;
	private String addtimeymdhms;
	private String moneyStr;
	private String feeStr;
	private String verifyTimeYmdhms;
	private String verifyTime2Ymdhms;
	private String onlinetypeStr;
	private String addtimeYmd;
	private String verifyTimeYmd;
	private String verifyTime2Ymd;
    private Integer isCustody;
    private String bizNo;
	private String eTradeNo;//银行流水号

	private String userType; // 用户类型

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Integer getOnlinetype() {
		return onlinetype;
	}

	public void setOnlinetype(Integer onlinetype) {
		this.onlinetype = onlinetype;
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

	public String getBankUsername() {
		return bankUsername;
	}

	public void setBankUsername(String bankUsername) {
		this.bankUsername = bankUsername;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTypeStr() {
		if (null != type) {
			if (type == 1) {
				return "在线充值";
			} else if (type == 2 && null != cardNum && "44461248@qq.com".equals(cardNum)) {
				return "支付宝充值";
			} else if (type == 2 && null != cardNum && !"44461248@qq.com".equals(cardNum)) {
				return "线下充值";
			}
		}
		return typeStr;
	}

	public String getStatusStr() {
		return Dictionary.getValue(801, String.valueOf(status));
	}

	public String getOnlinetypeStr() {
		if (null == onlinetype) {
			return "";
		}
		return Dictionary.getValue(807, String.valueOf(onlinetype));
	}

	public String getAddtimeymdhms() {
		if (null != addtime) {
			return DateUtils.timeStampToDate(addtime, DateUtils.YMD_HMS);
		}
		return addtimeymdhms;
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

	public String getMoneyStr() {
		if (null != money) {
			return MoneyUtil.fmtMoneyByDot(money);
		}
		return moneyStr;
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

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
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

	public Integer getIsFinancialUser() {
		return isFinancialUser;
	}

	public void setIsFinancialUser(Integer isFinancialUser) {
		this.isFinancialUser = isFinancialUser;
	}

	public String getUserType() {
		if (isFinancialUser != null) {
			if (isFinancialUser.intValue() == 0) {
				return "借款用户";
			} else {
				return "理财用户";
			}
		}
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

    public Integer getIsCustody() {
        return isCustody;
    }

    public void setIsCustody(Integer isCustody) {
        this.isCustody = isCustody;
    }

    public String getBizNo() {
        return bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }


	public String geteTradeNo() {
		return eTradeNo;
	}

	public void seteTradeNo(String eTradeNo) {
		this.eTradeNo = eTradeNo;
	}
}
