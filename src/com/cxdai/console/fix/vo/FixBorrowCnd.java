package com.cxdai.console.fix.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.cxdai.console.common.page.BaseCnd;

/**
 * 定期宝查询类
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title FixBorrowCnd.java
 * @package com.cxdai.console.fix.vo
 * @author 陈建国
 * @version 0.1 2015年6月8日
 */
public class FixBorrowCnd extends BaseCnd implements Serializable {
	private static final long serialVersionUID = 8387186801624482411L;
	/**
	 * 主键id
	 */
	private Integer id;
	/**
	 * 标题
	 */
	private String name;
	/**
	 * 发布开始时间
	 */
	private String beginTime;
	/**
	 * 发布结束时间
	 */
	private String endTime;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 添加优先计划验证字段
	 */
	private String validateAdd;
	/**
	 * 排序sql
	 */
	private String orderSql;

	/**
	 * 是否锁定记录, 锁定： yes
	 */
	private String lockedRecordYn;
	/**
	 * 定期宝编号
	 */

	private String contractNo;
	/**
	 * 用户名字
	 */
	private String userName;

	/**
	 * 发布开始日期
	 */
	private Date beginDate;
	/**
	 * 发布结束日期
	 */
	private Date endDate;

	/**
	 * 转让价格
	 */
	private BigDecimal accountReal;

	/**
	 * 锁定期限
	 */
	private Integer lockLimit;

	/**
	 * 是否优先投标;0:优先,1:不优先
	 */
	private String isFirstFlag;

	/**
	 * 专区类型【0：普通专区，1：新手专区】
	 */
	private Integer areaType;

	private String useMoneyFlag;
	
	/**
	 * 借款标ID
	 */
	private Integer borrowId;	
	/**
	 * 定期宝类型【0：普通宝，1：新手宝】
	 */
	private String fixType;
	public Integer getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}

	public String getUseMoneyFlag() {
		return useMoneyFlag;
	}

	public void setUseMoneyFlag(String useMoneyFlag) {
		this.useMoneyFlag = useMoneyFlag;
	}

	public Integer getAreaType() {
		return areaType;
	}

	public void setAreaType(Integer areaType) {
		this.areaType = areaType;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getValidateAdd() {
		return validateAdd;
	}

	public void setValidateAdd(String validateAdd) {
		this.validateAdd = validateAdd;
	}

	public String getOrderSql() {
		return orderSql;
	}

	public void setOrderSql(String orderSql) {
		this.orderSql = orderSql;
	}

	public String getLockedRecordYn() {
		return lockedRecordYn;
	}

	public void setLockedRecordYn(String lockedRecordYn) {
		this.lockedRecordYn = lockedRecordYn;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getAccountReal() {
		return accountReal;
	}

	public void setAccountReal(BigDecimal accountReal) {
		this.accountReal = accountReal;
	}

	public Integer getLockLimit() {
		return lockLimit;
	}

	public void setLockLimit(Integer lockLimit) {
		this.lockLimit = lockLimit;
	}

	public String getIsFirstFlag() {
		return isFirstFlag;
	}

	public void setIsFirstFlag(String isFirstFlag) {
		this.isFirstFlag = isFirstFlag;
	}

	public String getFixType() {
		return fixType;
	}

	public void setFixType(String fixType) {
		this.fixType = fixType;
	}
}
