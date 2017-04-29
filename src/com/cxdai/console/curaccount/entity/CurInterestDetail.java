package com.cxdai.console.curaccount.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CurInterestDetail {
    private Integer id;

    private Integer userId;

    //活期宝资产
    private BigDecimal total;

    private BigDecimal interestTotal;

    private BigDecimal useMoney;

    //未产生收益的金额
    private BigDecimal noUseMoney;

    //每日收益金额
    private BigDecimal money;

    //收益日期
    private Date interestDate;

    private Date addtime;

    private Integer adduser;

    private String addip;

    private String remark;

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

    public BigDecimal getInterestTotal() {
        return interestTotal;
    }

    public void setInterestTotal(BigDecimal interestTotal) {
        this.interestTotal = interestTotal;
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

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Date getInterestDate() {
        return interestDate;
    }

    public void setInterestDate(Date interestDate) {
        this.interestDate = interestDate;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Integer getAdduser() {
        return adduser;
    }

    public void setAdduser(Integer adduser) {
        this.adduser = adduser;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CurInterestDetail [id=" + id + ", userId=" + userId + ", total=" + total + ", interestTotal=" + interestTotal + ", useMoney="
				+ useMoney + ", noUseMoney=" + noUseMoney + ", money=" + money + ", interestDate=" + interestDate + ", addtime=" + addtime
				+ ", adduser=" + adduser + ", addip=" + addip + ", remark=" + remark + "]";
	}
    
    
}