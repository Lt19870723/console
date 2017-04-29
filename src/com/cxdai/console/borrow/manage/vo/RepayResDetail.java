package com.cxdai.console.borrow.manage.vo;

/**
 * 本息还款银行响应明细
 * @author Administrator
 *
 */
public class RepayResDetail {
	
	public String P2PserialNo;
	public String RepaymentStatus;
	public String RepaymentSerialNo;
	public String instSettleDate;
	public String getP2PserialNo() {
		return P2PserialNo;
	}
	public String getRepaymentStatus() {
		return RepaymentStatus;
	}
	public String getRepaymentSerialNo() {
		return RepaymentSerialNo;
	}
	public String getInstSettleDate() {
		return instSettleDate;
	}
	public void setP2PserialNo(String p2PserialNo) {
		P2PserialNo = p2PserialNo;
	}
	public void setRepaymentStatus(String repaymentStatus) {
		RepaymentStatus = repaymentStatus;
	}
	public void setRepaymentSerialNo(String repaymentSerialNo) {
		RepaymentSerialNo = repaymentSerialNo;
	}
	public void setInstSettleDate(String instSettleDate) {
		this.instSettleDate = instSettleDate;
	}

}
