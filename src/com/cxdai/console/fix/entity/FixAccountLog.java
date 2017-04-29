package com.cxdai.console.fix.entity;

import java.math.BigDecimal;
import java.util.Date;


/**   
 * <p>
 * Description:定期宝账户日志<br />
 * </p>
 * @title FixAccountLog.java
 * @package com.cxdai.console.fix.entity 
 * @author HuangJun
 * @version 0.1 2015年6月28日
 */
public class FixAccountLog {
    private Integer id;

    private Integer fixBorrowId;

    private Integer type;

    private Integer borrowId;

    private String borrowName;

    private Integer idType;

    private BigDecimal money;

    private BigDecimal total;

    private BigDecimal useMoney;

    private BigDecimal noUseMoney;

    private BigDecimal collection;

    private BigDecimal capital;

    private BigDecimal profit;

    private Integer adduser;

    private Date addtime;

    private String addip;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFixBorrowId() {
        return fixBorrowId;
    }

    public void setFixBorrowId(Integer fixBorrowId) {
        this.fixBorrowId = fixBorrowId;
    }

  

    /**
	 * @return type : return the property type.
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type : set the property type.
	 */
	public void setType(Integer type) {
		this.type = type;
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

 

    /**
	 * @return idType : return the property idType.
	 */
	public Integer getIdType() {
		return idType;
	}

	/**
	 * @param idType : set the property idType.
	 */
	public void setIdType(Integer idType) {
		this.idType = idType;
	}

	public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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

    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public Integer getAdduser() {
        return adduser;
    }

    public void setAdduser(Integer adduser) {
        this.adduser = adduser;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getAddip() {
        return addip;
    }

    public void setAddip(String addip) {
        this.addip = addip;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}