package com.cxdai.console.borrow.approve.vo;

import java.io.Serializable;

import com.cxdai.console.common.page.BaseCnd;

 

/**
 * <p>
 * Description:债权转让-查询条件<br />
 * </p>
 * 
 * @title SearchTransferBean.java
 * @package com.cxdai.portal.transfer.vo
 * @author chenpeng
 * @version 0.1 2014年10月21日
 */
public class SearchTransferVo extends BaseCnd implements Serializable {
	private static final long serialVersionUID = 4004120941203254494L;
	// 标的类型
	private String borrowType;
	// 标的状态
	private String transferStatus;
	// 剩余期限
	private String remainingTerm;
	// 还款方式
	private String repayType;
	// 排序内容
	private String orderBy;
	// 排序方式
	private String orderType;

	public String getBorrowType() {
		return borrowType;
	}

	public void setBorrowType(String borrowType) {
		this.borrowType = borrowType;
	}

	public String getTransferStatus() {
		return transferStatus;
	}

	public void setTransferStatus(String transferStatus) {
		this.transferStatus = transferStatus;
	}

	public String getRepayType() {
		return repayType;
	}

	public void setRepayType(String repayType) {
		this.repayType = repayType;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getRemainingTerm() {
		return remainingTerm;
	}

	public void setRemainingTerm(String remainingTerm) {
		this.remainingTerm = remainingTerm;
	}

}
