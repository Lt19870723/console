package com.cxdai.console.stock.entity;

import java.util.Date;

public class StockAccountlog {
    private Integer id;

    private Integer userId;

    private Integer accountId;

    private Integer optStock;

    private Integer total;

    private Integer useStock;

    private Integer noUseStock;

    private Integer optType;

    private Integer targetId;

    private String targetName;

    private Integer targetType;

    private Integer toUser;

    private String remark;

    private Date addtime;

    private String addip;

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

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getOptStock() {
        return optStock;
    }

    public void setOptStock(Integer optStock) {
        this.optStock = optStock;
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

    public Integer getOptType() {
        return optType;
    }

    public void setOptType(Integer optType) {
        this.optType = optType;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public Integer getTargetType() {
        return targetType;
    }

    public void setTargetType(Integer targetType) {
        this.targetType = targetType;
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
}