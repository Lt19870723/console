package com.cxdai.console.stock.entity;

import java.math.BigDecimal;
import java.util.Date;

public class StockEntrustlog {
    private Integer id;

    private Integer entrustId;

    private Integer userId;

    private String userName;

    private String userRealName;
    
    private String entrustCode;

    private String stockCode;

    private String stockName;

    private Integer entrustType;

    private BigDecimal price;

    private Integer amount;

    private BigDecimal totalPrice;

    private BigDecimal expectFee;

    private BigDecimal entrustTotalPrice;

    private Integer status;

    private Integer dealAmount;

    private BigDecimal dealTotalPrice;

    private BigDecimal dealRate;

    private BigDecimal dealFee;
    
    private BigDecimal drawMoney;
    
    private BigDecimal noDrawMoney;
    
    private Integer residueAmount;

    private Date expiryDate;

    private Date dealTime;

    private Integer platform;

    private String remark;

    private Integer adduser;

    private String optUserName;

    private String optUserRealName;

    private Date addtime;

    private String addip;
    
    private BigDecimal curUseMoney;
    
    private BigDecimal curNoUseMoney;
    

    public BigDecimal getCurUseMoney() {
		return curUseMoney;
	}

	public void setCurUseMoney(BigDecimal curUseMoney) {
		this.curUseMoney = curUseMoney;
	}

	public BigDecimal getCurNoUseMoney() {
		return curNoUseMoney;
	}

	public void setCurNoUseMoney(BigDecimal curNoUseMoney) {
		this.curNoUseMoney = curNoUseMoney;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(Integer entrustId) {
        this.entrustId = entrustId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Integer getEntrustType() {
        return entrustType;
    }

    public void setEntrustType(Integer entrustType) {
        this.entrustType = entrustType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getExpectFee() {
        return expectFee;
    }

    public void setExpectFee(BigDecimal expectFee) {
        this.expectFee = expectFee;
    }

    public BigDecimal getEntrustTotalPrice() {
        return entrustTotalPrice;
    }

    public void setEntrustTotalPrice(BigDecimal entrustTotalPrice) {
        this.entrustTotalPrice = entrustTotalPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(Integer dealAmount) {
        this.dealAmount = dealAmount;
    }

    public BigDecimal getDealTotalPrice() {
        return dealTotalPrice;
    }

    public void setDealTotalPrice(BigDecimal dealTotalPrice) {
        this.dealTotalPrice = dealTotalPrice;
    }

    public BigDecimal getDealRate() {
        return dealRate;
    }

    public void setDealRate(BigDecimal dealRate) {
        this.dealRate = dealRate;
    }
    

    public BigDecimal getDealFee() {
		return dealFee;
	}

	public void setDealFee(BigDecimal dealFee) {
		this.dealFee = dealFee;
	}

	public Integer getResidueAmount() {
        return residueAmount;
    }

    public void setResidueAmount(Integer residueAmount) {
        this.residueAmount = residueAmount;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getAdduser() {
        return adduser;
    }

    public void setAdduser(Integer adduser) {
        this.adduser = adduser;
    }

    public String getOptUserName() {
        return optUserName;
    }

    public void setOptUserName(String optUserName) {
        this.optUserName = optUserName;
    }

    public String getOptUserRealName() {
        return optUserRealName;
    }

    public void setOptUserRealName(String optUserRealName) {
        this.optUserRealName = optUserRealName;
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

	public String getEntrustCode() {
		return entrustCode;
	}

	public void setEntrustCode(String entrustCode) {
		this.entrustCode = entrustCode;
	}
    
    
}