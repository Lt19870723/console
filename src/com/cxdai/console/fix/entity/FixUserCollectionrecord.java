package com.cxdai.console.fix.entity;

import java.math.BigDecimal;
import java.util.Date;

public class FixUserCollectionrecord {
    private Integer id;

    private Integer fixBorrowId;

    private Integer borrowId;

    private Integer userId;

    private Integer tenderId;

    private Integer order;

    private BigDecimal capital;

    private Date addtime;

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

    public Integer getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Integer borrowId) {
        this.borrowId = borrowId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTenderId() {
        return tenderId;
    }

    public void setTenderId(Integer tenderId) {
        this.tenderId = tenderId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}