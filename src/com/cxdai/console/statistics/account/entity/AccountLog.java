package com.cxdai.console.statistics.account.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * Description:资金操作日志<br />
 * </p>
 * 
 * @title AccountLog.java
 * @package com.cxdai.base.entity
 * @author justin.xu
 * @version 0.1 2014年4月25日
 */
public class AccountLog implements Serializable {
	private static final long serialVersionUID = 8983061145517554053L;
	private Integer id;
	/** 會員ID */
	private Integer userId;
	/** 日誌類型 */
	private String type;
	/** 帳戶總額 */
	private BigDecimal total;
	/** 操作金額 */
	private BigDecimal money;
	/** 可用餘額 */
	private BigDecimal useMoney;
	/** 凍結金額 */
	private BigDecimal noUseMoney;
	/** 待收總額 */
	private BigDecimal collection;
	/** 交易對方 */
	private Integer toUser;
	/** 借款标ID */
	private Integer borrowId;
	/** 借款标NAME */
	private String borrowName;
	/** 0:借款标ID, 1:直通车id, 2:待还记录id */
	private Integer idType;
	/** 日誌備註 */
	private String remark;
	/** 操作時間 */
	private String addtime;
	/** 插入IP */
	private String addip;
	/** 优先计划总可用金额 */
	private BigDecimal firstBorrowUseMoney;
	/** 可提现金额 */
	private BigDecimal drawMoney;
	/** 不可提现金额 */
	private BigDecimal noDrawMoney;

	/** 存管可用金额*/
	private BigDecimal eUseMoney;
	/**存管冻结金额*/
	private BigDecimal eFreezeMoney;
	/** 存管待收金额*/
	private BigDecimal eCollection;

	private Integer isCustody;//是否开立存管账户（0：未开立；1：已开立）

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getUseMoney() {
		return useMoney;
	}

	public void setUseMoney(BigDecimal useMoney) {
		this.useMoney = useMoney;
	}

	public BigDecimal getNoUseMoney() {
		return noUseMoney;
	}

	public void setNoUseMoney(BigDecimal noUseMoney) {
		this.noUseMoney = noUseMoney;
	}

	public BigDecimal getCollection() {
		return collection;
	}

	public void setCollection(BigDecimal collection) {
		this.collection = collection;
	}

	public Integer getToUser() {
		return toUser;
	}

	public void setToUser(Integer toUser) {
		this.toUser = toUser;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public BigDecimal getFirstBorrowUseMoney() {
		return firstBorrowUseMoney;
	}

	public void setFirstBorrowUseMoney(BigDecimal firstBorrowUseMoney) {
		this.firstBorrowUseMoney = firstBorrowUseMoney;
	}

	public BigDecimal getDrawMoney() {
		return drawMoney;
	}

	public void setDrawMoney(BigDecimal drawMoney) {
		this.drawMoney = drawMoney;
	}

	public BigDecimal getNoDrawMoney() {
		return noDrawMoney;
	}

	public void setNoDrawMoney(BigDecimal noDrawMoney) {
		this.noDrawMoney = noDrawMoney;
	}

	public Integer getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}

	public String getBorrowName() {
		return borrowName;
	}

	public void setBorrowName(String borrowName) {
		this.borrowName = borrowName;
	}

	public Integer getIdType() {
		return idType;
	}

	public void setIdType(Integer idType) {
		this.idType = idType;
	}

	public BigDecimal geteUseMoney() {
		return eUseMoney;
	}

	public void seteUseMoney(BigDecimal eUseMoney) {
		this.eUseMoney = eUseMoney;
	}

	public BigDecimal geteFreezeMoney() {
		return eFreezeMoney;
	}

	public void seteFreezeMoney(BigDecimal eFreezeMoney) {
		this.eFreezeMoney = eFreezeMoney;
	}

	public BigDecimal geteCollection() {
		return eCollection;
	}

	public void seteCollection(BigDecimal eCollection) {
		this.eCollection = eCollection;
	}

	public Integer getIsCustody() {
		return isCustody;
	}

	public void setIsCustody(Integer isCustody) {
		this.isCustody = isCustody;
	}

}
