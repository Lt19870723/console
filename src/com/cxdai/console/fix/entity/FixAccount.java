package com.cxdai.console.fix.entity;

import java.math.BigDecimal;


/**   
 * <p>
 * Description:定期宝账户<br />
 * </p>
 * @title FixAccount.java
 * @package com.cxdai.console.fix.entity 
 * @author HuangJun
 * @version 0.1 2015年6月28日
 */
public class FixAccount {
    private Integer id;

    private Integer fixBorrowId;

    private BigDecimal total;

    private BigDecimal useMoney;

    private BigDecimal noUseMoney;

    private BigDecimal collection;

    private BigDecimal capital;

    private BigDecimal profit;

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
}