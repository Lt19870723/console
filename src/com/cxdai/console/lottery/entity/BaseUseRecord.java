package com.cxdai.console.lottery.entity;

import java.math.BigDecimal;
import java.util.Date;

public class BaseUseRecord {
    private Integer id;

    private Integer userId;

    private Integer lotteryId;

    private Integer lotteryGoodsId;

    private Integer status;

    private Date addTime;

    private Integer awardType;

    private String lotteryGoodsName;

    private Integer awardNum;

    private BigDecimal awardMoney;

    private Date overdueTime;

    private Date drawTime;

    private String remark;

    private String platform;


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

    public Integer getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(Integer lotteryId) {
        this.lotteryId = lotteryId;
    }

    public Integer getLotteryGoodsId() {
        return lotteryGoodsId;
    }

    public void setLotteryGoodsId(Integer lotteryGoodsId) {
        this.lotteryGoodsId = lotteryGoodsId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getAwardType() {
        return awardType;
    }

    public void setAwardType(Integer awardType) {
        this.awardType = awardType;
    }

    public String getLotteryGoodsName() {
        return lotteryGoodsName;
    }

    public void setLotteryGoodsName(String lotteryGoodsName) {
        this.lotteryGoodsName = lotteryGoodsName == null ? null : lotteryGoodsName.trim();
    }

    public Integer getAwardNum() {
        return awardNum;
    }

    public void setAwardNum(Integer awardNum) {
        this.awardNum = awardNum;
    }

    public BigDecimal getAwardMoney() {
        return awardMoney;
    }

    public void setAwardMoney(BigDecimal awardMoney) {
        this.awardMoney = awardMoney;
    }

    public Date getOverdueTime() {
        return overdueTime;
    }

    public void setOverdueTime(Date overdueTime) {
        this.overdueTime = overdueTime;
    }

    public Date getDrawTime() {
        return drawTime;
    }

    public void setDrawTime(Date drawTime) {
        this.drawTime = drawTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}