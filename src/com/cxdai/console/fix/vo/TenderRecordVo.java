package com.cxdai.console.fix.vo;

import java.text.SimpleDateFormat;

import com.cxdai.console.base.entity.BTenderRecord;
import com.cxdai.console.util.DateUtils;

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
public class TenderRecordVo extends BTenderRecord {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5549073056981228141L;
	
	/**
	 * 定期宝id
	 */
	private Integer fixBorrowId;
	
	/**
	 * 定期宝编号
	 */
	private String fixContractNo;
	
	/**
	 * 定期宝还款日
	 */
	private String lockEndtime;
	
	/**
	 * 借款标名称
	 */
	private String borrowName;
	
	/**
	 * 借款标合同编号
	 */
	private String contractNo;
	
	/**
	 * 借款标所属原定期宝
	 */
	private String parentContractNo;
	
	/**
	 * 投标时间
	 */
	private String tenderTime;

	public Integer getFixBorrowId() {
		return fixBorrowId;
	}

	public void setFixBorrowId(Integer fixBorrowId) {
		this.fixBorrowId = fixBorrowId;
	}

	public String getFixContractNo() {
		return fixContractNo;
	}

	public void setFixContractNo(String fixContractNo) {
		this.fixContractNo = fixContractNo;
	}

	public String getLockEndtime() {
		return lockEndtime;
	}

	public void setLockEndtime(String lockEndtime) {
		this.lockEndtime = lockEndtime;
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

	public String getParentContractNo() {
		return parentContractNo;
	}

	public void setParentContractNo(String parentContractNo) {
		this.parentContractNo = parentContractNo;
	}

	public String getTenderTime() {
		if(null!=getAddtime()&& !getAddtime().trim().equals("")){
			tenderTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(DateUtils.timeStampToDate(getAddtime()));
		}
		return tenderTime;
	}

	public void setTenderTime(String tenderTime) {
		this.tenderTime = tenderTime;
	}
	
	
}
