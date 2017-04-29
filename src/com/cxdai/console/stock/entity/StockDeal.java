package com.cxdai.console.stock.entity;

import java.math.BigDecimal;
import java.util.Date;

public class StockDeal {
    private Integer id;

    private Integer sellerEntrustId;

    private Integer sellerId;

    private String sellerName;

    private String sellerRealName;

    private String sellerStockCode;

    private String sellerStockName;

    private BigDecimal entrustPrice;

    private Integer surplusSellerAmount;

    private Integer buyerEntrustId;

    private BigDecimal buyerPrice;

    private Integer surplusBuyerAmount;

    private Integer buyerId;

    private String buyerName;

    private String buyerRealName;

    private String buyerStockCode;

    private String buyerStockName;

    private Integer dealType;

    private Integer turnoverAmount;

    private BigDecimal turnoverPrice;

    private BigDecimal turnoverTotalPrice;

    private BigDecimal serviceCharge;

    private BigDecimal sellerFee;

    private BigDecimal sellerRate;

    private BigDecimal buyerFee;

    private BigDecimal buyerRate;

    private Integer status;

    private String remark;

    private Integer adduser;

    private Date addtime;
    
    private Date updatetime;
    
    

    public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSellerEntrustId() {
        return sellerEntrustId;
    }

    public void setSellerEntrustId(Integer sellerEntrustId) {
        this.sellerEntrustId = sellerEntrustId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerRealName() {
        return sellerRealName;
    }

    public void setSellerRealName(String sellerRealName) {
        this.sellerRealName = sellerRealName;
    }

    public String getSellerStockCode() {
        return sellerStockCode;
    }

    public void setSellerStockCode(String sellerStockCode) {
        this.sellerStockCode = sellerStockCode;
    }

    public String getSellerStockName() {
        return sellerStockName;
    }

    public void setSellerStockName(String sellerStockName) {
        this.sellerStockName = sellerStockName;
    }

    public BigDecimal getEntrustPrice() {
        return entrustPrice;
    }

    public void setEntrustPrice(BigDecimal entrustPrice) {
        this.entrustPrice = entrustPrice;
    }

    public Integer getSurplusSellerAmount() {
        return surplusSellerAmount;
    }

    public void setSurplusSellerAmount(Integer surplusSellerAmount) {
        this.surplusSellerAmount = surplusSellerAmount;
    }

    public Integer getBuyerEntrustId() {
        return buyerEntrustId;
    }

    public void setBuyerEntrustId(Integer buyerEntrustId) {
        this.buyerEntrustId = buyerEntrustId;
    }

    public BigDecimal getBuyerPrice() {
        return buyerPrice;
    }

    public void setBuyerPrice(BigDecimal buyerPrice) {
        this.buyerPrice = buyerPrice;
    }

    public Integer getSurplusBuyerAmount() {
        return surplusBuyerAmount;
    }

    public void setSurplusBuyerAmount(Integer surplusBuyerAmount) {
        this.surplusBuyerAmount = surplusBuyerAmount;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerRealName() {
        return buyerRealName;
    }

    public void setBuyerRealName(String buyerRealName) {
        this.buyerRealName = buyerRealName;
    }

    public String getBuyerStockCode() {
        return buyerStockCode;
    }

    public void setBuyerStockCode(String buyerStockCode) {
        this.buyerStockCode = buyerStockCode;
    }

    public String getBuyerStockName() {
        return buyerStockName;
    }

    public void setBuyerStockName(String buyerStockName) {
        this.buyerStockName = buyerStockName;
    }

    public Integer getDealType() {
        return dealType;
    }

    public void setDealType(Integer dealType) {
        this.dealType = dealType;
    }

    public Integer getTurnoverAmount() {
        return turnoverAmount;
    }

    public void setTurnoverAmount(Integer turnoverAmount) {
        this.turnoverAmount = turnoverAmount;
    }

    public BigDecimal getTurnoverPrice() {
        return turnoverPrice;
    }

    public void setTurnoverPrice(BigDecimal turnoverPrice) {
        this.turnoverPrice = turnoverPrice;
    }

    public BigDecimal getTurnoverTotalPrice() {
        return turnoverTotalPrice;
    }

    public void setTurnoverTotalPrice(BigDecimal turnoverTotalPrice) {
        this.turnoverTotalPrice = turnoverTotalPrice;
    }

    public BigDecimal getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(BigDecimal serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public BigDecimal getSellerFee() {
        return sellerFee;
    }

    public void setSellerFee(BigDecimal sellerFee) {
        this.sellerFee = sellerFee;
    }

    public BigDecimal getSellerRate() {
        return sellerRate;
    }

    public void setSellerRate(BigDecimal sellerRate) {
        this.sellerRate = sellerRate;
    }

    public BigDecimal getBuyerFee() {
        return buyerFee;
    }

    public void setBuyerFee(BigDecimal buyerFee) {
        this.buyerFee = buyerFee;
    }

    public BigDecimal getBuyerRate() {
        return buyerRate;
    }

    public void setBuyerRate(BigDecimal buyerRate) {
        this.buyerRate = buyerRate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}