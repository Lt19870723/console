package com.cxdai.console.fix.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.cxdai.console.common.page.BaseCnd;

/**   
 * <p>
 * Description:定期宝转让<br />
 * </p>
 * @title FixBorrowTransfer.java
 * @package com.cxdai.console.fix.entity 
 * @author HuangJun
 * @version 0.1 2015年6月25日 
 */
public class FixBorrowTransferCnd  extends BaseCnd implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer fixBorrowId;

    private BigDecimal account;

    private BigDecimal useMoney;

    private BigDecimal awards;

    private BigDecimal accountReal;

    private BigDecimal accountYes;

    private Byte fixLockLimit;

    private Byte fixLockStyle;

    private Date fixLockEndtime;

    private BigDecimal fixApr;

    private String fixBorrowName;

    private String fixContractNo;

    private String bidPassword;

    private Date addtime;

    private Integer adduser;

    private Date lastUpdateTime;

    private Integer lastUpdateUser;

    private Date successTime;

    private Boolean status;

    private String remark;

    private Boolean platform;

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

    public BigDecimal getAwards() {
        return awards;
    }

    public void setAwards(BigDecimal awards) {
        this.awards = awards;
    }

    public BigDecimal getAccountReal() {
        return accountReal;
    }

    public void setAccountReal(BigDecimal accountReal) {
        this.accountReal = accountReal;
    }

    public BigDecimal getAccountYes() {
        return accountYes;
    }

    public void setAccountYes(BigDecimal accountYes) {
        this.accountYes = accountYes;
    }

    public Byte getFixLockLimit() {
        return fixLockLimit;
    }

    public void setFixLockLimit(Byte fixLockLimit) {
        this.fixLockLimit = fixLockLimit;
    }

    public Byte getFixLockStyle() {
        return fixLockStyle;
    }

    public void setFixLockStyle(Byte fixLockStyle) {
        this.fixLockStyle = fixLockStyle;
    }

    public Date getFixLockEndtime() {
        return fixLockEndtime;
    }

    public void setFixLockEndtime(Date fixLockEndtime) {
        this.fixLockEndtime = fixLockEndtime;
    }

    public BigDecimal getFixApr() {
        return fixApr;
    }

    public void setFixApr(BigDecimal fixApr) {
        this.fixApr = fixApr;
    }

    public String getFixBorrowName() {
        return fixBorrowName;
    }

    public void setFixBorrowName(String fixBorrowName) {
        this.fixBorrowName = fixBorrowName;
    }

    public String getFixContractNo() {
        return fixContractNo;
    }

    public void setFixContractNo(String fixContractNo) {
        this.fixContractNo = fixContractNo;
    }

    public String getBidPassword() {
        return bidPassword;
    }

    public void setBidPassword(String bidPassword) {
        this.bidPassword = bidPassword;
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

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(Integer lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    public Date getSuccessTime() {
        return successTime;
    }

    public void setSuccessTime(Date successTime) {
        this.successTime = successTime;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getPlatform() {
        return platform;
    }

    public void setPlatform(Boolean platform) {
        this.platform = platform;
    }
}