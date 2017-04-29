package com.cxdai.console.finance.virement.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CheckRecharge {
    private Integer id;

    private Date date;

    private Integer type;

    private BigDecimal totalMoney;

    private BigDecimal successMoney;

    private BigDecimal checkSuccessMoney;

    private BigDecimal calculationFee;

    private BigDecimal checkFee;

    private BigDecimal differenceFee;

    private BigDecimal receiveMoney;

    private BigDecimal fictitiousRecharge;

    private BigDecimal differenceTotal;

    private String remarks;

    private Integer isSuccess;

    private Integer status;

    private Date verifyTime;

    private Integer addUser;

    private Date addTime;

    private String addIp;

    private Integer updateUser;

    private Date updateTime;

    private String updateIp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getSuccessMoney() {
        return successMoney;
    }

    public void setSuccessMoney(BigDecimal successMoney) {
        this.successMoney = successMoney;
    }

    public BigDecimal getCheckSuccessMoney() {
        return checkSuccessMoney;
    }

    public void setCheckSuccessMoney(BigDecimal checkSuccessMoney) {
        this.checkSuccessMoney = checkSuccessMoney;
    }

    public BigDecimal getCalculationFee() {
        return calculationFee;
    }

    public void setCalculationFee(BigDecimal calculationFee) {
        this.calculationFee = calculationFee;
    }

    public BigDecimal getCheckFee() {
        return checkFee;
    }

    public void setCheckFee(BigDecimal checkFee) {
        this.checkFee = checkFee;
    }

    public BigDecimal getDifferenceFee() {
        return differenceFee;
    }

    public void setDifferenceFee(BigDecimal differenceFee) {
        this.differenceFee = differenceFee;
    }

    public BigDecimal getReceiveMoney() {
        return receiveMoney;
    }

    public void setReceiveMoney(BigDecimal receiveMoney) {
        this.receiveMoney = receiveMoney;
    }

    public BigDecimal getFictitiousRecharge() {
        return fictitiousRecharge;
    }

    public void setFictitiousRecharge(BigDecimal fictitiousRecharge) {
        this.fictitiousRecharge = fictitiousRecharge;
    }

    public BigDecimal getDifferenceTotal() {
        return differenceTotal;
    }

    public void setDifferenceTotal(BigDecimal differenceTotal) {
        this.differenceTotal = differenceTotal;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Integer isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(Date verifyTime) {
        this.verifyTime = verifyTime;
    }

    public Integer getAddUser() {
        return addUser;
    }

    public void setAddUser(Integer addUser) {
        this.addUser = addUser;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getAddIp() {
        return addIp;
    }

    public void setAddIp(String addIp) {
        this.addIp = addIp;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateIp() {
        return updateIp;
    }

    public void setUpdateIp(String updateIp) {
        this.updateIp = updateIp;
    }
}