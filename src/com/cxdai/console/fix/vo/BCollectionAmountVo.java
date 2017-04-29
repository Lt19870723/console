package com.cxdai.console.fix.vo;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/6/15.
 */
public class BCollectionAmountVo {

    public BigDecimal total_repay_account;
    public BigDecimal total_capital;
    public BigDecimal total_interest;

    public BigDecimal getTotal_repay_account() {
        return total_repay_account;
    }

    public void setTotal_repay_account(BigDecimal total_repay_account) {
        this.total_repay_account = total_repay_account;
    }

    public BigDecimal getTotal_capital() {
        return total_capital;
    }

    public void setTotal_capital(BigDecimal total_capital) {
        this.total_capital = total_capital;
    }

    public BigDecimal getTotal_interest() {
        return total_interest;
    }

    public void setTotal_interest(BigDecimal total_interest) {
        this.total_interest = total_interest;
    }
}
