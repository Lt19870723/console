package com.cxdai.console.base.entity;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.druid.sql.visitor.functions.Substring;

public class BaseEBankInfo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_bankinfo.ID
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_bankinfo.USER_ID
     *
     * @mbggenerated
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_bankinfo.BANKNAME
     *
     * @mbggenerated
     */
    private String bankname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_bankinfo.ECARD_NO
     *
     * @mbggenerated
     */
    private String ecardNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_bankinfo.REALNAME
     *
     * @mbggenerated
     */
    private String realname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_bankinfo.CERTTYPE
     *
     * @mbggenerated
     */
    private Integer certtype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_bankinfo.CERT_NO
     *
     * @mbggenerated
     */
    private String certNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_bankinfo.MOBILE
     *
     * @mbggenerated
     */
    private String mobile;
    
    private String mobileStr;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_bankinfo.STATUS
     *
     * @mbggenerated
     */
    private Integer status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_bankinfo.BIND_NO
     *
     * @mbggenerated
     */
    private String bindNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_bankinfo.CARDTYPE
     *
     * @mbggenerated
     */
    private Integer cardtype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_bankinfo.CUSTODY_BIND_NO
     *
     * @mbggenerated
     */
    private String custodyBindNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_bankinfo.CUSTODY_BIND_NAME
     *
     * @mbggenerated
     */
    private String custodyBindName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_bankinfo.ADDTIME
     *
     * @mbggenerated
     */
    private Date addtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_bankinfo.ID
     *
     * @return the value of e_bankinfo.ID
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_bankinfo.ID
     *
     * @param id the value for e_bankinfo.ID
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_bankinfo.USER_ID
     *
     * @return the value of e_bankinfo.USER_ID
     *
     * @mbggenerated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_bankinfo.USER_ID
     *
     * @param userId the value for e_bankinfo.USER_ID
     *
     * @mbggenerated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_bankinfo.BANKNAME
     *
     * @return the value of e_bankinfo.BANKNAME
     *
     * @mbggenerated
     */
    public String getBankname() {
        return bankname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_bankinfo.BANKNAME
     *
     * @param bankname the value for e_bankinfo.BANKNAME
     *
     * @mbggenerated
     */
    public void setBankname(String bankname) {
        this.bankname = bankname == null ? null : bankname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_bankinfo.ECARD_NO
     *
     * @return the value of e_bankinfo.ECARD_NO
     *
     * @mbggenerated
     */
    public String getEcardNo() {
        return ecardNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_bankinfo.ECARD_NO
     *
     * @param ecardNo the value for e_bankinfo.ECARD_NO
     *
     * @mbggenerated
     */
    public void setEcardNo(String ecardNo) {
        this.ecardNo = ecardNo == null ? null : ecardNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_bankinfo.REALNAME
     *
     * @return the value of e_bankinfo.REALNAME
     *
     * @mbggenerated
     */
    public String getRealname() {
        return realname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_bankinfo.REALNAME
     *
     * @param realname the value for e_bankinfo.REALNAME
     *
     * @mbggenerated
     */
    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_bankinfo.CERTTYPE
     *
     * @return the value of e_bankinfo.CERTTYPE
     *
     * @mbggenerated
     */
    public Integer getCerttype() {
        return certtype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_bankinfo.CERTTYPE
     *
     * @param certtype the value for e_bankinfo.CERTTYPE
     *
     * @mbggenerated
     */
    public void setCerttype(Integer certtype) {
        this.certtype = certtype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_bankinfo.CERT_NO
     *
     * @return the value of e_bankinfo.CERT_NO
     *
     * @mbggenerated
     */
    public String getCertNo() {
        return certNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_bankinfo.CERT_NO
     *
     * @param certNo the value for e_bankinfo.CERT_NO
     *
     * @mbggenerated
     */
    public void setCertNo(String certNo) {
        this.certNo = certNo == null ? null : certNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_bankinfo.MOBILE
     *
     * @return the value of e_bankinfo.MOBILE
     *
     * @mbggenerated
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_bankinfo.MOBILE
     *
     * @param mobile the value for e_bankinfo.MOBILE
     *
     * @mbggenerated
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_bankinfo.STATUS
     *
     * @return the value of e_bankinfo.STATUS
     *
     * @mbggenerated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_bankinfo.STATUS
     *
     * @param status the value for e_bankinfo.STATUS
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_bankinfo.BIND_NO
     *
     * @return the value of e_bankinfo.BIND_NO
     *
     * @mbggenerated
     */
    public String getBindNo() {
        return bindNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_bankinfo.BIND_NO
     *
     * @param bindNo the value for e_bankinfo.BIND_NO
     *
     * @mbggenerated
     */
    public void setBindNo(String bindNo) {
        this.bindNo = bindNo == null ? null : bindNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_bankinfo.CARDTYPE
     *
     * @return the value of e_bankinfo.CARDTYPE
     *
     * @mbggenerated
     */
    public Integer getCardtype() {
        return cardtype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_bankinfo.CARDTYPE
     *
     * @param cardtype the value for e_bankinfo.CARDTYPE
     *
     * @mbggenerated
     */
    public void setCardtype(Integer cardtype) {
        this.cardtype = cardtype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_bankinfo.CUSTODY_BIND_NO
     *
     * @return the value of e_bankinfo.CUSTODY_BIND_NO
     *
     * @mbggenerated
     */
    public String getCustodyBindNo() {
        return custodyBindNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_bankinfo.CUSTODY_BIND_NO
     *
     * @param custodyBindNo the value for e_bankinfo.CUSTODY_BIND_NO
     *
     * @mbggenerated
     */
    public void setCustodyBindNo(String custodyBindNo) {
        this.custodyBindNo = custodyBindNo == null ? null : custodyBindNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_bankinfo.CUSTODY_BIND_NAME
     *
     * @return the value of e_bankinfo.CUSTODY_BIND_NAME
     *
     * @mbggenerated
     */
    public String getCustodyBindName() {
        return custodyBindName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_bankinfo.CUSTODY_BIND_NAME
     *
     * @param custodyBindName the value for e_bankinfo.CUSTODY_BIND_NAME
     *
     * @mbggenerated
     */
    public void setCustodyBindName(String custodyBindName) {
        this.custodyBindName = custodyBindName == null ? null : custodyBindName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_bankinfo.ADDTIME
     *
     * @return the value of e_bankinfo.ADDTIME
     *
     * @mbggenerated
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_bankinfo.ADDTIME
     *
     * @param addtime the value for e_bankinfo.ADDTIME
     *
     * @mbggenerated
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

	public String getMobileStr() {
		if(StringUtils.isNotBlank(mobile)){
			return mobile.substring(0, 3)+"******"+mobile.substring(mobile.length()-2, mobile.length());
		}
		return mobileStr;
	}

	public void setMobileStr(String mobileStr) {
		this.mobileStr = mobileStr;
	}
    

}