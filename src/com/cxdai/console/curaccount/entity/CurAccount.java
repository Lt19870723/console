package com.cxdai.console.curaccount.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**   
 * <p>
 * Description:活期宝账户表 <br />
 * </p>
 * @title CurAccount.java
 * @package com.cxdai.console.curAccount.entity 
 * @author HuangJun
 * @version 0.1 2015年5月13日
 */
public class CurAccount implements Serializable{
	private static final long serialVersionUID = -3531851612222097637L;
	
	/**
	 * 主键ID
	 */
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 活期宝账户金额
     */
    private BigDecimal total;

    /**
     * 可产生收益的金额
     */
    private BigDecimal useMoney;

    /**
     * 未产生收益的金额
     */
    private BigDecimal noUseMoney;

    /**
     * 累计收益金额
     */
    private BigDecimal interestTotal;

    /**
     * 昨日收益金额
     */
    private BigDecimal interestYesterday;

    /**
     * 开户人
     */
    private Integer adduser;

    /**
     * 开户时间
     */
    private Date addtime;

    /**
     * 失效时间
     */
    private Date endtime;

    /**
     * 最后更新人
     */
    private Integer lastUpUser;

    /**
     * 最后更新时间
     */
    private Date lastUpTime;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 备注
     */
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

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getLastUpUser() {
        return lastUpUser;
    }

    public void setLastUpUser(Integer lastUpUser) {
        this.lastUpUser = lastUpUser;
    }

    public Date getLastUpTime() {
        return lastUpTime;
    }

    public void setLastUpTime(Date lastUpTime) {
        this.lastUpTime = lastUpTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}