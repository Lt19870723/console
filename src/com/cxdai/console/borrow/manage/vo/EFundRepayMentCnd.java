package com.cxdai.console.borrow.manage.vo;


import java.util.Date;

public class EFundRepayMentCnd {
    private Integer id;
    private Integer type;
    private Integer borrowId;
    private Integer reapaymentId;
    private Integer collectionId;
    private String bizNo;
    private String eInvestNo;
    private String eRepaymentNo;
    private Integer isSend;
    private Integer result;
	private Date addtime;
	private Integer order;
	public Integer getId() {
		return id;
	}
	public Integer getType() {
		return type;
	}
	public Integer getBorrowId() {
		return borrowId;
	}
	public Integer getReapaymentId() {
		return reapaymentId;
	}
	public Integer getCollectionId() {
		return collectionId;
	}
	public String getBizNo() {
		return bizNo;
	}
	public String geteInvestNo() {
		return eInvestNo;
	}
	public String geteRepaymentNo() {
		return eRepaymentNo;
	}
	public Integer getIsSend() {
		return isSend;
	}
	public Integer getResult() {
		return result;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}
	public void setReapaymentId(Integer reapaymentId) {
		this.reapaymentId = reapaymentId;
	}
	public void setCollectionId(Integer collectionId) {
		this.collectionId = collectionId;
	}
	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}
	public void seteInvestNo(String eInvestNo) {
		this.eInvestNo = eInvestNo;
	}
	public void seteRepaymentNo(String eRepaymentNo) {
		this.eRepaymentNo = eRepaymentNo;
	}
	public void setIsSend(Integer isSend) {
		this.isSend = isSend;
	}
	public void setResult(Integer result) {
		this.result = result;
	}
	public Date getAddtime() {return addtime;}
	public void setAddtime(Date addtime) {this.addtime = addtime;}
	public Integer getOrder() {return order;}
	public void setOrder(Integer order) {this.order = order;}

    
    

}