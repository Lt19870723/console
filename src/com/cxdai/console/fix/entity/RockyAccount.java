package com.cxdai.console.fix.entity;

import java.math.BigDecimal;

/**   
 * <p>
 * Description:账户<br />
 * </p>
 * @title RockyAccount.java
 * @package com.cxdai.console.fix.entity 
 * @author HuangJun
 * @version 0.1 2015年6月25日
 */
public class RockyAccount {
    private Integer id;

    private Integer userId;

    private BigDecimal total;

    private BigDecimal useMoney;

    private BigDecimal noUseMoney;

    private BigDecimal collection;

    private BigDecimal netvalue;

    private BigDecimal firstBorrowUseMoney;

    private Integer version;

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

    public BigDecimal getNetvalue() {
        return netvalue;
    }

    public void setNetvalue(BigDecimal netvalue) {
        this.netvalue = netvalue;
    }

    public BigDecimal getFirstBorrowUseMoney() {
        return firstBorrowUseMoney;
    }

    public void setFirstBorrowUseMoney(BigDecimal firstBorrowUseMoney) {
        this.firstBorrowUseMoney = firstBorrowUseMoney;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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