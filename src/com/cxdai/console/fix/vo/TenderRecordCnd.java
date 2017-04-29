package com.cxdai.console.fix.vo;

import com.cxdai.console.common.page.BaseCnd;

/**
 * <p>
 * Description:投标记录Vo<br />
 * </p>
 * 
 * @title TenderRecordVo.java
 * @package com.cxdai.console.fix.entity
 * @author caodefeng
 * @version 0.1 2014年6月8日
 */
public class TenderRecordCnd extends BaseCnd {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 定期宝编号
	 */
	private String fixContractNo;
	
	/**
	 * 借款标名称
	 */
	private String borrowName; 
	
	/**
	 * 借款标的合同编号
	 */
	private String contractNo;
	
	/**
	 * 定期宝id
	 */
	private Integer fixBorrowId;

	public String getFixContractNo() {
		return fixContractNo;
	}

	public void setFixContractNo(String fixContractNo) {
		this.fixContractNo = fixContractNo;
	}

	public String getBorrowName() {
		return borrowName;
	}

	public void setBorrowName(String borrowName) {
		this.borrowName = borrowName;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public Integer getFixBorrowId() {
		return fixBorrowId;
	}

	public void setFixBorrowId(Integer fixBorrowId) {
		this.fixBorrowId = fixBorrowId;
	} 
	
	
}
