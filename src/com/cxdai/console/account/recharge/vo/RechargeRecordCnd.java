package com.cxdai.console.account.recharge.vo;

import java.io.Serializable;
import java.util.Date;

import com.cxdai.console.common.page.BaseCnd;
import com.cxdai.console.util.DateUtils;

/**
 * <p>
 * Description:充值记录和审核查询条件<br />
 * </p>
 * 
 * @title RealNameApproCnd.java
 * @package com.cxdai.portal.member.vo
 * @author justin.xu
 * @version 0.1 2014年4月24日
 */
public class RechargeRecordCnd extends BaseCnd implements Serializable {
	private static final long serialVersionUID = -1748725774856611015L;
	private String id;
	/** 订单号 */
	private String tradeNo;
	/** 用户名 */
	private String username;
	/** 充值开始日期 */
	private Date beginTime;
	private String beginTimeStr;
	/** 充值结束日期 */
	private Date endTime;
	private String endTimeStr;
	/** 类型(1：在线充值，2：线下转账) */
	private String type;
	/** 状态（0充值审核中，1充值成功，9表示失败，2在线充值待付款，3充值初审成功） */
	private String status;
	/** 第三方支付平台 1：网银在线 2：国付宝 3：盛付通 4：新浪支付 */
	private String onlinetype;
	/** 版本号 */
	private String version;
	/** 用户类型 */
	private String userType;
    /** 是否存管充值 */
    private Integer isCustody;
    /** 平台来源 */
    private Integer platFrom;
    
    public Integer getPlatFrom() {
		return platFrom;
	}

	public void setPlatFrom(Integer platFrom) {
		this.platFrom = platFrom;
	}

	private String statusIn;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = DateUtils.parse(beginTime, DateUtils.YMD_DASH);
	}

	public String getBeginTimeStr() {
		// 转换成秒数
		if (null != beginTime) {
			beginTime = DateUtils.convert2StartDate(beginTime);
			beginTimeStr = beginTime.getTime() / 1000 + "";
		} else {
			beginTimeStr = null;
		}
		return beginTimeStr;
	}

	public void setBeginTimeStr(String beginTimeStr) {
		this.beginTimeStr = beginTimeStr;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = DateUtils.parse(endTime, DateUtils.YMD_DASH);
	}

	public String getEndTimeStr() {
		if (null != endTime) {
			endTime = DateUtils.convert2EndDate(endTime);
			endTimeStr = endTime.getTime() / 1000 + "";
		} else {
			endTimeStr = null;
		}
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getOnlinetype() {
		return onlinetype;
	}

	public void setOnlinetype(String onlinetype) {
		this.onlinetype = onlinetype;
	}

	public String getUserType() {
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

    public String getStatusIn() {
        return statusIn;
    }

    public void setStatusIn(String statusIn) {
        this.statusIn = statusIn;
    }
}
