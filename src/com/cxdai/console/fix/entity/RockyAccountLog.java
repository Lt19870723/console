package com.cxdai.console.fix.entity;

import java.math.BigDecimal;

/**   
 * <p>
 * Description:普通账户日志<br />
 * </p>
 * @title RockyAccountLog.java
 * @package com.cxdai.console.fix.entity 
 * @author HuangJun
 * @version 0.1 2015年6月26日
 */
public class RockyAccountLog {
    private Integer id;

    private Integer userId;

    private String type;

    private BigDecimal total;

    private BigDecimal money;

    private BigDecimal useMoney;

    private BigDecimal noUseMoney;

    private BigDecimal collection;

    private Integer toUser;

    private Integer borrowId;

    private String borrowName;

    private Integer idType;

    private String remark;

    private String addtime;

    private String addip;

    private BigDecimal firstBorrowUseMoney;

    private BigDecimal drawMoney;

    private BigDecimal noDrawMoney;

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
}