package com.cxdai.console.base.entity;

import java.util.Date;

public class BaseChanceInfo {
    private Integer id;

    private Integer lotteryChanceRuleInfoId;

    private Integer lotteryNum;

    private Integer useNum;

    private Integer userId;

    private String username;

    private Date addTime;

    private String remark;

    private String addIp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLotteryChanceRuleInfoId() {
        return lotteryChanceRuleInfoId;
    }

    public void setLotteryChanceRuleInfoId(Integer lotteryChanceRuleInfoId) {
        this.lotteryChanceRuleInfoId = lotteryChanceRuleInfoId;
    }

    public Integer getLotteryNum() {
        return lotteryNum;
    }

    public void setLotteryNum(Integer lotteryNum) {
        this.lotteryNum = lotteryNum;
    }

    public Integer getUseNum() {
        return useNum;
    }

    public void setUseNum(Integer useNum) {
        this.useNum = useNum;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getAddIp() {
        return addIp;
    }

    public void setAddIp(String addIp) {
        this.addIp = addIp == null ? null : addIp.trim();
    }
}