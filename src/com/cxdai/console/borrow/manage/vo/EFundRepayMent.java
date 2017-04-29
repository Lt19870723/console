package com.cxdai.console.borrow.manage.vo;

import java.util.Date;

public class EFundRepayMent {
    private Integer id;
    private Integer type;
    private Integer borrowId;
    private Integer reapaymentId;
    private Integer collectionId;
    private Integer order;
    private String repayName;
    private Integer innerFlag;
    private String bizNo;
    private String eInvestNo;
    private String eRepaymentNo;
    private Integer returnType;
    private Integer flag;
    private String bankBranchNo;
    private String accountBankNumber;
    private String accountName;
    private Integer repaymentAmount;
    private Integer fee;
    private Integer extraInterest;
    private Integer currency;
    private Integer payType;
    private Integer isSend;
    private Integer result;
    private Integer attachAmount;
    private Date addtime;
    private Date updatetime;
	/**银行登记项目ID **/
	private String eProjectId;
    
    

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_fund_repayment.ID
     *
     * @return the value of e_fund_repayment.ID
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_fund_repayment.ID
     *
     * @param id the value for e_fund_repayment.ID
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_fund_repayment.TYPE
     *
     * @return the value of e_fund_repayment.TYPE
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_fund_repayment.TYPE
     *
     * @param type the value for e_fund_repayment.TYPE
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_fund_repayment.BORROW_ID
     *
     * @return the value of e_fund_repayment.BORROW_ID
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public Integer getBorrowId() {
        return borrowId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_fund_repayment.BORROW_ID
     *
     * @param borrowId the value for e_fund_repayment.BORROW_ID
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public void setBorrowId(Integer borrowId) {
        this.borrowId = borrowId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_fund_repayment.REAPAYMENT_ID
     *
     * @return the value of e_fund_repayment.REAPAYMENT_ID
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public Integer getReapaymentId() {
        return reapaymentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_fund_repayment.REAPAYMENT_ID
     *
     * @param reapaymentId the value for e_fund_repayment.REAPAYMENT_ID
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public void setReapaymentId(Integer reapaymentId) {
        this.reapaymentId = reapaymentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_fund_repayment.COLLECTION_ID
     *
     * @return the value of e_fund_repayment.COLLECTION_ID
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public Integer getCollectionId() {
        return collectionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_fund_repayment.COLLECTION_ID
     *
     * @param collectionId the value for e_fund_repayment.COLLECTION_ID
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_fund_repayment.ORDER
     *
     * @return the value of e_fund_repayment.ORDER
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public Integer getOrder() {
        return order;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_fund_repayment.ORDER
     *
     * @param order the value for e_fund_repayment.ORDER
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public void setOrder(Integer order) {
        this.order = order;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_fund_repayment.REPAY_NAME
     *
     * @return the value of e_fund_repayment.REPAY_NAME
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public String getRepayName() {
        return repayName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_fund_repayment.REPAY_NAME
     *
     * @param repayName the value for e_fund_repayment.REPAY_NAME
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public void setRepayName(String repayName) {
        this.repayName = repayName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_fund_repayment.INNER_FLAG
     *
     * @return the value of e_fund_repayment.INNER_FLAG
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public Integer getInnerFlag() {
        return innerFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_fund_repayment.INNER_FLAG
     *
     * @param innerFlag the value for e_fund_repayment.INNER_FLAG
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public void setInnerFlag(Integer innerFlag) {
        this.innerFlag = innerFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_fund_repayment.BIZ_NO
     *
     * @return the value of e_fund_repayment.BIZ_NO
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public String getBizNo() {
        return bizNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_fund_repayment.BIZ_NO
     *
     * @param bizNo the value for e_fund_repayment.BIZ_NO
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_fund_repayment.E_INVEST_NO
     *
     * @return the value of e_fund_repayment.E_INVEST_NO
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public String geteInvestNo() {
        return eInvestNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_fund_repayment.E_INVEST_NO
     *
     * @param eInvestNo the value for e_fund_repayment.E_INVEST_NO
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public void seteInvestNo(String eInvestNo) {
        this.eInvestNo = eInvestNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_fund_repayment.E_REPAYMENT_NO
     *
     * @return the value of e_fund_repayment.E_REPAYMENT_NO
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public String geteRepaymentNo() {
        return eRepaymentNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_fund_repayment.E_REPAYMENT_NO
     *
     * @param eRepaymentNo the value for e_fund_repayment.E_REPAYMENT_NO
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public void seteRepaymentNo(String eRepaymentNo) {
        this.eRepaymentNo = eRepaymentNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_fund_repayment.RETURN_TYPE
     *
     * @return the value of e_fund_repayment.RETURN_TYPE
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public Integer getReturnType() {
        return returnType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_fund_repayment.RETURN_TYPE
     *
     * @param returnType the value for e_fund_repayment.RETURN_TYPE
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public void setReturnType(Integer returnType) {
        this.returnType = returnType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_fund_repayment.FLAG
     *
     * @return the value of e_fund_repayment.FLAG
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public Integer getFlag() {
        return flag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_fund_repayment.FLAG
     *
     * @param flag the value for e_fund_repayment.FLAG
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_fund_repayment.BANK_BRANCH_NO
     *
     * @return the value of e_fund_repayment.BANK_BRANCH_NO
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public String getBankBranchNo() {
        return bankBranchNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_fund_repayment.BANK_BRANCH_NO
     *
     * @param bankBranchNo the value for e_fund_repayment.BANK_BRANCH_NO
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public void setBankBranchNo(String bankBranchNo) {
        this.bankBranchNo = bankBranchNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_fund_repayment.ACCOUNT_BANK_NUMBER
     *
     * @return the value of e_fund_repayment.ACCOUNT_BANK_NUMBER
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public String getAccountBankNumber() {
        return accountBankNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_fund_repayment.ACCOUNT_BANK_NUMBER
     *
     * @param accountBankNumber the value for e_fund_repayment.ACCOUNT_BANK_NUMBER
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public void setAccountBankNumber(String accountBankNumber) {
        this.accountBankNumber = accountBankNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_fund_repayment.ACCOUNT_NAME
     *
     * @return the value of e_fund_repayment.ACCOUNT_NAME
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_fund_repayment.ACCOUNT_NAME
     *
     * @param accountName the value for e_fund_repayment.ACCOUNT_NAME
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_fund_repayment.REPAYMENT_AMOUNT
     *
     * @return the value of e_fund_repayment.REPAYMENT_AMOUNT
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public Integer getRepaymentAmount() {
        return repaymentAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_fund_repayment.REPAYMENT_AMOUNT
     *
     * @param repaymentAmount the value for e_fund_repayment.REPAYMENT_AMOUNT
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public void setRepaymentAmount(Integer repaymentAmount) {
        this.repaymentAmount = repaymentAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_fund_repayment.FEE
     *
     * @return the value of e_fund_repayment.FEE
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public Integer getFee() {
        return fee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_fund_repayment.FEE
     *
     * @param fee the value for e_fund_repayment.FEE
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public void setFee(Integer fee) {
        this.fee = fee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_fund_repayment.EXTRA_INTEREST
     *
     * @return the value of e_fund_repayment.EXTRA_INTEREST
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public Integer getExtraInterest() {
        return extraInterest;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_fund_repayment.EXTRA_INTEREST
     *
     * @param extraInterest the value for e_fund_repayment.EXTRA_INTEREST
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public void setExtraInterest(Integer extraInterest) {
        this.extraInterest = extraInterest;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_fund_repayment.CURRENCY
     *
     * @return the value of e_fund_repayment.CURRENCY
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public Integer getCurrency() {
        return currency;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_fund_repayment.CURRENCY
     *
     * @param currency the value for e_fund_repayment.CURRENCY
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public void setCurrency(Integer currency) {
        this.currency = currency;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_fund_repayment.PAY_TYPE
     *
     * @return the value of e_fund_repayment.PAY_TYPE
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public Integer getPayType() {
        return payType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_fund_repayment.PAY_TYPE
     *
     * @param payType the value for e_fund_repayment.PAY_TYPE
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_fund_repayment.IS_SEND
     *
     * @return the value of e_fund_repayment.IS_SEND
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public Integer getIsSend() {
        return isSend;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_fund_repayment.IS_SEND
     *
     * @param isSend the value for e_fund_repayment.IS_SEND
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public void setIsSend(Integer isSend) {
        this.isSend = isSend;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_fund_repayment.RESULT
     *
     * @return the value of e_fund_repayment.RESULT
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public Integer getResult() {
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_fund_repayment.RESULT
     *
     * @param result the value for e_fund_repayment.RESULT
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public void setResult(Integer result) {
        this.result = result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_fund_repayment.ADDTIME
     *
     * @return the value of e_fund_repayment.ADDTIME
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_fund_repayment.ADDTIME
     *
     * @param addtime the value for e_fund_repayment.ADDTIME
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_fund_repayment.UPDATETIME
     *
     * @return the value of e_fund_repayment.UPDATETIME
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_fund_repayment.UPDATETIME
     *
     * @param updatetime the value for e_fund_repayment.UPDATETIME
     *
     * @mbggenerated Tue May 17 14:51:20 CST 2016
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

	public String geteProjectId() {
		return eProjectId;
	}

	public void seteProjectId(String eProjectId) {
		this.eProjectId = eProjectId;
	}

    public Integer getAttachAmount() {
        return attachAmount;
    }

    public void setAttachAmount(Integer attachAmount) {
        this.attachAmount = attachAmount;
    }
}