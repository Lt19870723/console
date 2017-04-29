package com.cxdai.console.fix.vo;

import com.cxdai.console.common.page.BaseCnd;

/**
 * 
 * <p>
 * Description:待收记录查询条件<br />
 * </p>
 * 
 * @title BCollectionRecordCnd.java
 * @package com.cxdai.face.fix.vo
 * @author 朱泳霖
 * @version 0.1 2015年7月1日
 */
public class BCollectionRecordCnd extends BaseCnd {

	private static final long serialVersionUID = -747561170614279281L;

	/**
	 * 投标ID
	 */
	private Integer tenderId;

	/**
	 * 定期宝ID
	 */
	private Integer fixBorrowId;

	/**
	 * 期数
	 */
	private Integer order;
	
	/**
	 * 借款标ID
	 */
	private Integer borrowId;
	
	/**
	 * 平台交易流水号
	 */
	private String bizNo;
	
	/**
	 * 主键
	 */
	private Integer id;
	

	public Integer getTenderId() {
		return tenderId;
	}

	public void setTenderId(Integer tenderId) {
		this.tenderId = tenderId;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getFixBorrowId() {
		return fixBorrowId;
	}

	public void setFixBorrowId(Integer fixBorrowId) {
		this.fixBorrowId = fixBorrowId;
	}

	public Integer getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}

	public String getBizNo() {
		return bizNo;
	}

	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
