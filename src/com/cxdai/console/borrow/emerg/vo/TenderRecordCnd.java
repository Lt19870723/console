package com.cxdai.console.borrow.emerg.vo;

import java.io.Serializable;

import com.cxdai.console.common.page.BaseCnd;

/**
 * <p>
 * Description:投标记录查询<br />
 * </p>
 * 
 * @title TenderRecordCnd.java
 * @package com.cxdai.console.borrow.vo
 * @author justin.xu
 * @version 0.1 2015年2月5日
 */
public class TenderRecordCnd extends BaseCnd implements Serializable {
	/**
	 * @fields serialVersionUID
	 */

	private static final long serialVersionUID = -2343651735385175138L;
	/**
	 * 借款标id
	 */
	private Integer borrowId;
	/**
	 * 记录的类型 0：原始投标记录 1：目前实际债权人记录，即原始投标记录和认购债转的记录，不包含已经债转出去的数据
	 */
	private Integer recordType;
	
	/**
	 * 用户ID
	 */
	private Integer userId;

	/**
	 * 投标ID
	 */
	private Integer tenderId;

	public Integer getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}

	public Integer getRecordType() {
		return recordType;
	}

	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getTenderId() {
		return tenderId;
	}

	public void setTenderId(Integer tenderId) {
		this.tenderId = tenderId;
	}

}