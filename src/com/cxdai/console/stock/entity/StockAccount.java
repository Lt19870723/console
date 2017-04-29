package com.cxdai.console.stock.entity;

import java.util.Date;

public class StockAccount {
    private Integer id;

    private Integer userId;

    private String stockCode;

    private String stockName;

    private Integer total;

    private Integer useStock;

    private Integer noUseStock;

    private String remark;

    private Integer status;

    private Date addtime;

    private Date updatetime;

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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getUseStock() {
        return useStock;
    }

    public void setUseStock(Integer useStock) {
        this.useStock = useStock;
    }

    public Integer getNoUseStock() {
        return noUseStock;
    }

    public void setNoUseStock(Integer noUseStock) {
        this.noUseStock = noUseStock;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}