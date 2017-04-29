package com.cxdai.console.borrow.manage.vo;

import com.cxdai.console.common.custody.Record;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Record")
public class RepayDetail extends Record{


	/**
	 * <p>
	 * Description:本息还款明细<br />
	 * </p>
	 * @author Luobinbin
	 * @version 0.1 2016-5-17
	 * @param args
	 * void
	 */
    public String P2PserialNo="";//平台流水号
    public String InvestmentSerialNo="";//原投资流水号
    public Integer Type=0;//回款类型
    public String Flag=""; //是否他行
    public String BankBranchNo;//开户行人行联行号
    public String AccountBankNumber="";//回款账户账号
    public String AccountName="";//回款账户户名
    public Integer RepaymentAmount=0;//还款金额
    public Integer Fee=0;//手续费
    public Integer ExtraInterest=0;//加息金额
	public String currency="156";//币种
    public String PayType="1";//还款类型
	/**
	 * @return p2PserialNo : return the property p2PserialNo.
	 */
	public String getP2PserialNo() {
		return P2PserialNo;
	}
	/**
	 * @param p2PserialNo : set the property p2PserialNo.
	 */
	public void setP2PserialNo(String p2PserialNo) {
		P2PserialNo = p2PserialNo;
	}
	/**
	 * @return investmentSerialNo : return the property investmentSerialNo.
	 */
	public String getInvestmentSerialNo() {
		return InvestmentSerialNo;
	}
	/**
	 * @param investmentSerialNo : set the property investmentSerialNo.
	 */
	public void setInvestmentSerialNo(String investmentSerialNo) {
		InvestmentSerialNo = investmentSerialNo;
	}
	/**
	 * @return type : return the property type.
	 */
	public Integer getType() {
		return Type;
	}
	/**
	 * @param type : set the property type.
	 */
	public void setType(Integer type) {
		Type = type;
	}
	/**
	 * @return flag : return the property flag.
	 */
	public String getFlag() {
		return Flag;
	}
	/**
	 * @param flag : set the property flag.
	 */
	public void setFlag(String flag) {
		Flag = flag;
	}
	/**
	 * @return bankBranchNo : return the property bankBranchNo.
	 */
	public String getBankBranchNo() {
		return BankBranchNo;
	}
	/**
	 * @param bankBranchNo : set the property bankBranchNo.
	 */
	public void setBankBranchNo(String bankBranchNo) {
		BankBranchNo = bankBranchNo;
	}
	/**
	 * @return accountBankNumber : return the property accountBankNumber.
	 */
	public String getAccountBankNumber() {
		return AccountBankNumber;
	}
	/**
	 * @param accountBankNumber : set the property accountBankNumber.
	 */
	public void setAccountBankNumber(String accountBankNumber) {
		AccountBankNumber = accountBankNumber;
	}
	/**
	 * @return accountName : return the property accountName.
	 */
	public String getAccountName() {
		return AccountName;
	}
	/**
	 * @param accountName : set the property accountName.
	 */
	public void setAccountName(String accountName) {
		AccountName = accountName;
	}
	/**
	 * @return repaymentAmount : return the property repaymentAmount.
	 */
	public Integer getRepaymentAmount() {
		return RepaymentAmount;
	}
	/**
	 * @param repaymentAmount : set the property repaymentAmount.
	 */
	public void setRepaymentAmount(Integer repaymentAmount) {
		RepaymentAmount = repaymentAmount;
	}
	/**
	 * @return fee : return the property fee.
	 */
	public Integer getFee() {
		return Fee;
	}
	/**
	 * @param fee : set the property fee.
	 */
	public void setFee(Integer fee) {
		Fee = fee;
	}
	/**
	 * @return extraInterest : return the property extraInterest.
	 */
	public Integer getExtraInterest() {
		return ExtraInterest;
	}
	/**
	 * @param extraInterest : set the property extraInterest.
	 */
	public void setExtraInterest(Integer extraInterest) {
		ExtraInterest = extraInterest;
	}
	/**
	 * @return payType : return the property payType.
	 */
	public String getPayType() {
		return PayType;
	}
	/**
	 * @param payType : set the property payType.
	 */
	public void setPayType(String payType) {
		PayType = payType;
	}


	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}


}
