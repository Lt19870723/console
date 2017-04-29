package com.cxdai.console.account.cash.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.cxdai.console.common.page.BaseCnd;
import com.cxdai.console.util.DateUtils;

/**
 * <p>
 * Description:提现记录查询条件<br />
 * </p>
 * 
 * @title CashRecordCnd.java
 * @package com.cxdai.console.account.cash.vo
 * @author hujianpan
 * @version 0.1 2015年4月7日
 */
public class CashRecordCnd extends BaseCnd implements Serializable {
	private static final long serialVersionUID = -8537943174686481221L;
	private String id;
	/** 用户id */
	private String userId;
	/** 用户名 */
	private String username;
	/** 状态【-1：审核失败；0：申请提现；1：审核通过；2：打款结束】 3:取消提现 */
	private String status;
	/** 提现开始日期 */
	private Date beginTime;
	private String beginTimeStr;
	/** 提现结束日期 */
	private Date endTime;
	private String endTimeStr;
	/** 版本号 */
	private String version;

	/** 提现处理单双方式（1：单数 2：双数） **/
	private Integer type;

	/** 打款开始日期 */
	private Date beginTime2;
	private String beginTime2Str;
	/** 打款结束日期 */
	private Date endTime2;
	private String endTime2Str;

	/** 排序规则sql:例如 order by addtime desc */
	private String orderSql;
	/** 提现栏目（1：提现申请，2：提现处理，3：提现查询，4：提现打款成功） **/
	private Integer cashColumn;
	/**
	 * 是否锁定记录, 锁定： yes
	 */
	private String lockedRecordYn;
	/** 是否已导出 */
	private String isExport;

	private BigDecimal cashTotal;

	/** 审核时间 */
	private Date verifyTimeBeginDate;
	private String verifyTimeBeginStr;

	private Date verifyTimeEndDate;
	private String verifyTimeEndStr;
	/** 审核人 */
	private String verifyName;

    private Integer isCustody;
    private String statusIn;

	public BigDecimal getCashTotal() {
		return cashTotal;
	}

	public void setCashTotal(BigDecimal cashTotal) {
		this.cashTotal = cashTotal;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {

		this.beginTime = DateUtils.parse(beginTime, DateUtils.YMD_DASH);
	}

	public String getBeginTimeStr() {
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
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

	public String getOrderSql() {
		return orderSql;
	}

	public void setOrderSql(String orderSql) {
		this.orderSql = orderSql;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getBeginTime2() {
		return beginTime2;
	}

	public void setBeginTime2(String beginTime2) {
		this.beginTime2 = DateUtils.parse(beginTime2, DateUtils.YMD_DASH);
	}

	public String getBeginTime2Str() {
		return beginTime2Str;
	}

	public void setBeginTime2Str(String beginTime2Str) {
		this.beginTime2Str = beginTime2Str;
	}

	public Date getEndTime2() {
		return endTime2;
	}

	public void setEndTime2(String endTime2) {
		this.endTime2 = DateUtils.parse(endTime2, DateUtils.YMD_DASH);
	}

	public String getEndTime2Str() {
		return endTime2Str;
	}

	public void setEndTime2Str(String endTime2Str) {
		this.endTime2Str = endTime2Str;
	}

	public Integer getCashColumn() {
		return cashColumn;
	}

	public void setCashColumn(Integer cashColumn) {
		this.cashColumn = cashColumn;
	}

	public String getLockedRecordYn() {
		return lockedRecordYn;
	}

	public void setLockedRecordYn(String lockedRecordYn) {
		this.lockedRecordYn = lockedRecordYn;
	}

	public String getIsExport() {
		return isExport;
	}

	public void setIsExport(String isExport) {
		this.isExport = isExport;
	}

	public Date getVerifyTimeBeginDate() {
		return verifyTimeBeginDate;
	}

	public void setVerifyTimeBeginDate(String verifyTimeBeginDate) {
		this.verifyTimeBeginDate = DateUtils.parse(verifyTimeBeginDate, DateUtils.YMD_DASH);
	}

	public String getVerifyTimeBeginStr() {
		return verifyTimeBeginStr;
	}

	public void setVerifyTimeBeginStr(String verifyTimeBeginStr) {
		this.verifyTimeBeginStr = verifyTimeBeginStr;
	}

	public Date getVerifyTimeEndDate() {
		return verifyTimeEndDate;
	}

	public void setVerifyTimeEndDate(String verifyTimeEndDate) {
		this.verifyTimeEndDate = DateUtils.parse(verifyTimeEndDate, DateUtils.YMD_DASH);
	}

	public String getVerifyTimeEndStr() {
		return verifyTimeEndStr;
	}

	public void setVerifyTimeEndStr(String verifyTimeEndStr) {
		this.verifyTimeEndStr = verifyTimeEndStr;
	}

	public String getVerifyName() {
		return verifyName;
	}

	public void setVerifyName(String verifyName) {
		this.verifyName = verifyName;
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
