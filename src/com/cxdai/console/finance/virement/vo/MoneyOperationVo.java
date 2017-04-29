package com.cxdai.console.finance.virement.vo;

import com.cxdai.console.finance.virement.entity.MoneyOperation;

public class MoneyOperationVo extends MoneyOperation {

	private String operator;// 操作员

	private String approvalUser;// 审核人

	private String applyForUser;// 申请人

	private String payeeBankName;// 转入银行
	private String payerBankName;// 转出银行

	public String getApprovalUser() {
		return approvalUser;
	}

	public void setApprovalUser(String approvalUser) {
		this.approvalUser = approvalUser;
	}

	public String getApplyForUser() {
		return applyForUser;
	}

	public void setApplyForUser(String applyForUser) {
		this.applyForUser = applyForUser;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getPayeeBankName() {
		return payeeBankName;
	}

	public void setPayeeBankName(String payeeBankName) {
		this.payeeBankName = payeeBankName;
	}

	public String getPayerBankName() {
		return payerBankName;
	}

	public void setPayerBankName(String payerBankName) {
		this.payerBankName = payerBankName;
	}

}
