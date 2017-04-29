package com.cxdai.console.borrow.approve.vo;

import java.io.Serializable;

import com.cxdai.console.common.page.BaseCnd;



/**
 * <p>
 * Description:待收查询条件<br />
 * </p>
 * 
 * @title CollectionStatisticCnd.java
 * @package com.cxdai.portal.invest.vo
 * @author justin.xu
 * @version 0.1 2014年12月23日
 */
public class CollectionStatisticCnd extends BaseCnd implements Serializable {

	private static final long serialVersionUID = -7120622884570013103L;
	private Integer id;
	private Integer borrowId;
	/** 标的状态,-2:作废，-1:已债权转让，0:尚未还款，1：已还款，2：网站垫付 ，3:垫付后还款 */
	private Integer status;
	/** 待还记录id */
	private Integer repaymentId;
	/**
	 * 是否锁定记录, 锁定： yes
	 */
	private String lockedRecordYn;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getLockedRecordYn() {
		return lockedRecordYn;
	}

	public void setLockedRecordYn(String lockedRecordYn) {
		this.lockedRecordYn = lockedRecordYn;
	}

	public Integer getRepaymentId() {
		return repaymentId;
	}

	public void setRepaymentId(Integer repaymentId) {
		this.repaymentId = repaymentId;
	}

}