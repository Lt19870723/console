package com.cxdai.console.fix.entity;

/**
 * <p>
 * Description:定期宝操作日志类<br />
 * </p>
 * 
 * @title FixOperationLog.java
 * @package com.cxdai.console.fix.entity
 * @author HuangJun
 * @version 0.1 2015年6月29日
 */
public class FixOperationLog {

	/**
	 * 主键id
	 */
	private Integer id;

	/**
	 * 操作人id
	 */
	private Integer userId;

	/**
	 * 用户类型
	 */
	private Integer userType;

	/**
	 * 定期宝id
	 */
	private Integer fixBorrowId;

	/**
	 * 最终认购表id
	 */
	private Integer fixTenderRealId;

	/**
	 * 操作类型
	 */
	private Integer operType;

	/**
	 * 添加时间
	 */
	private java.util.Date addtime;

	/**
	 * 添加ip
	 */
	private String addip;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 平台来源
	 */
	private Integer platform;
	/**
	 * 自动投标截止日期
	 */
	private String setautobidTime;
	
	

	public String getSetautobidTime() {
		return setautobidTime;
	}

	public void setSetautobidTime(String setautobidTime) {
		this.setautobidTime = setautobidTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getFixBorrowId() {
		return fixBorrowId;
	}

	public void setFixBorrowId(Integer fixBorrowId) {
		this.fixBorrowId = fixBorrowId;
	}

	public Integer getFixTenderRealId() {
		return fixTenderRealId;
	}

	public void setFixTenderRealId(Integer fixTenderRealId) {
		this.fixTenderRealId = fixTenderRealId;
	}

	public Integer getOperType() {
		return operType;
	}

	public void setOperType(Integer operType) {
		this.operType = operType;
	}

	public java.util.Date getAddtime() {
		return addtime;
	}

	public void setAddtime(java.util.Date addtime) {
		this.addtime = addtime;
	}

	public String getAddip() {
		return addip;
	}

	public void setAddip(String addip) {
		this.addip = addip;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}

}
