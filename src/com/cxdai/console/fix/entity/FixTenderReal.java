package com.cxdai.console.fix.entity;

import java.math.BigDecimal;
import java.util.Date;


/**   
 * <p>
 * Description:定期宝认购明细表<br />
 * </p>
 * @title FixTenderReal.java
 * @package com.cxdai.console.fix.entity 
 * @author HuangJun
 * @version 0.1 2015年6月29日
 */
public class FixTenderReal {
    private Integer id;

    private Integer fixBorrowId;

    private Integer userId;

    private BigDecimal account;

    private BigDecimal useMoney;

    private Integer status;

    private Date addtime;

    private Integer ordernum;

    private Byte fixTenderType;

    private Integer parentId;

    private Integer unlockUserid;

    private Date unlockTime;

    private String unlockIp;

    private String remark;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getAccount() {
        return account;
    }

    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    public BigDecimal getUseMoney() {
        return useMoney;
    }

    public void setUseMoney(BigDecimal useMoney) {
        this.useMoney = useMoney;
    }

  

    /**
	 * @return status : return the property status.
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status : set the property status.
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Integer getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    public Byte getFixTenderType() {
        return fixTenderType;
    }

    public void setFixTenderType(Byte fixTenderType) {
        this.fixTenderType = fixTenderType;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getUnlockUserid() {
        return unlockUserid;
    }

    public void setUnlockUserid(Integer unlockUserid) {
        this.unlockUserid = unlockUserid;
    }

    public Date getUnlockTime() {
        return unlockTime;
    }

    public void setUnlockTime(Date unlockTime) {
        this.unlockTime = unlockTime;
    }

    public String getUnlockIp() {
        return unlockIp;
    }

    public void setUnlockIp(String unlockIp) {
        this.unlockIp = unlockIp;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}