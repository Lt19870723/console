package com.cxdai.console.curaccount.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.cxdai.console.common.page.BaseCnd;

public class CurAccountlog extends BaseCnd implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer userId;

	private BigDecimal money;

	private Integer type;

	private BigDecimal total;

	private BigDecimal useMoney;

	private BigDecimal noUseMoney;

	private BigDecimal interestTotal;

	private BigDecimal interestYesterday;

	private Integer adduser;

	private Date addtime;

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

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public BigDecimal getInterestTotal() {
		return interestTotal;
	}

	public void setInterestTotal(BigDecimal interestTotal) {
		this.interestTotal = interestTotal;
	}

	public BigDecimal getInterestYesterday() {
		return interestYesterday;
	}

	public void setInterestYesterday(BigDecimal interestYesterday) {
		this.interestYesterday = interestYesterday;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CurAccountlog [id=" + id + ", userId=" + userId + ", money=" + money + ", type=" + type + ", total=" + total + ", useMoney="
				+ useMoney + ", noUseMoney=" + noUseMoney + ", interestTotal=" + interestTotal + ", interestYesterday=" + interestYesterday
				+ ", adduser=" + adduser + ", addtime=" + addtime + ", addip=" + addip + ", remark=" + remark + "]";
	}

}