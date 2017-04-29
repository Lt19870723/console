package com.cxdai.console.base.entity;

/**
 * <p>
 * Description:借款标审核<br />
 * </p>
 * 
 * @title BorrowApproved.java
 * @package com.cxdai.base.entity
 * @author justin.xu
 * @version 0.1 2014年8月30日
 */
public class BorrowApproved {
	/**
	 * 主键ID
	 */
	private Integer id;
	/**
	 * 借款标ID
	 */
	private Integer borrowId;

	/**
	 * 审核状态，目前采用4步审核【审核不通过对应的状态取负值】（-1:草稿标 0：待审核；1：初审通过，2：反欺诈通过，3：终审通过，4：复审通过；）
	 */
	private Integer status;

	/**
	 * 初审审核人
	 */
	private Integer verifyUser;

	/**
	 * 初审审核时间
	 */
	private String verifyTime;

	/**
	 * 初审审核备注
	 */
	private String verifyRemark;

	/**
	 * 反欺诈审核人
	 */
	private Integer verifyUser2;

	/**
	 * 反欺诈审核时间
	 */
	private String verifyTime2;

	/**
	 * 反欺诈审核备注
	 */
	private String verifyRemark2;

	/**
	 * 终审审核人
	 */
	private Integer verifyUser3;

	/**
	 * 终审审核时间
	 */
	private String verifyTime3;

	/**
	 * 终审审核备注
	 */
	private String verifyRemark3;

	/**
	 * 复审审核人
	 */
	private Integer verifyUser4;

	/**
	 * 复审审核时间
	 */
	private String verifyTime4;

	/**
	 * 复审审核备注
	 */
	private String verifyRemark4;

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

	public Integer getVerifyUser() {
		return verifyUser;
	}

	public void setVerifyUser(Integer verifyUser) {
		this.verifyUser = verifyUser;
	}

	public String getVerifyTime() {
		return verifyTime;
	}

	public void setVerifyTime(String verifyTime) {
		this.verifyTime = verifyTime;
	}

	public String getVerifyRemark() {
		return verifyRemark;
	}

	public void setVerifyRemark(String verifyRemark) {
		this.verifyRemark = verifyRemark;
	}

	public Integer getVerifyUser2() {
		return verifyUser2;
	}

	public void setVerifyUser2(Integer verifyUser2) {
		this.verifyUser2 = verifyUser2;
	}

	public String getVerifyTime2() {
		return verifyTime2;
	}

	public void setVerifyTime2(String verifyTime2) {
		this.verifyTime2 = verifyTime2;
	}

	public String getVerifyRemark2() {
		return verifyRemark2;
	}

	public void setVerifyRemark2(String verifyRemark2) {
		this.verifyRemark2 = verifyRemark2;
	}

	public Integer getVerifyUser3() {
		return verifyUser3;
	}

	public void setVerifyUser3(Integer verifyUser3) {
		this.verifyUser3 = verifyUser3;
	}

	public String getVerifyTime3() {
		return verifyTime3;
	}

	public void setVerifyTime3(String verifyTime3) {
		this.verifyTime3 = verifyTime3;
	}

	public String getVerifyRemark3() {
		return verifyRemark3;
	}

	public void setVerifyRemark3(String verifyRemark3) {
		this.verifyRemark3 = verifyRemark3;
	}

	public Integer getVerifyUser4() {
		return verifyUser4;
	}

	public void setVerifyUser4(Integer verifyUser4) {
		this.verifyUser4 = verifyUser4;
	}

	public String getVerifyTime4() {
		return verifyTime4;
	}

	public void setVerifyTime4(String verifyTime4) {
		this.verifyTime4 = verifyTime4;
	}

	public String getVerifyRemark4() {
		return verifyRemark4;
	}

	public void setVerifyRemark4(String verifyRemark4) {
		this.verifyRemark4 = verifyRemark4;
	}

}