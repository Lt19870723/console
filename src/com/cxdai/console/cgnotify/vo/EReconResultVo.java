package com.cxdai.console.cgnotify.vo;

import java.math.BigDecimal;

/**
 * <p>
 * Description: <br />
 * </p>
 *
 * @author zhaowei
 * @version 0.1 2016/5/27
 * @title cxdai_interface
 * @package com.cxdai.console.cgnotify
 */
public class EReconResultVo {

    private String sysNo;
    private String bankNo;
    private Integer sysStatus;
    private Integer bankStatus;
    private BigDecimal sysAccount;
    private BigDecimal bankAccount;
    private BigDecimal sysFee;
    private BigDecimal bankFee;
    private Integer detailId;

    public String getSysNo() {
        return sysNo;
    }

    public void setSysNo(String sysNo) {
        this.sysNo = sysNo;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public Integer getSysStatus() {
        return sysStatus;
    }

    public void setSysStatus(Integer sysStatus) {
        this.sysStatus = sysStatus;
    }

    public Integer getBankStatus() {
        return bankStatus;
    }

    public void setBankStatus(Integer bankStatus) {
        this.bankStatus = bankStatus;
    }

    public BigDecimal getSysAccount() {
        return sysAccount;
    }

    public void setSysAccount(BigDecimal sysAccount) {
        this.sysAccount = sysAccount;
    }

    public BigDecimal getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BigDecimal bankAccount) {
        this.bankAccount = bankAccount;
    }

    public BigDecimal getSysFee() {
        return sysFee;
    }

    public void setSysFee(BigDecimal sysFee) {
        this.sysFee = sysFee;
    }

    public BigDecimal getBankFee() {
        return bankFee;
    }

    public void setBankFee(BigDecimal bankFee) {
        this.bankFee = bankFee;
    }

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }
}
