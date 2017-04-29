package com.cxdai.console.base.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Description:直通车转让审核类<br />
 * </p>
 * 
 * @title FirstTransferApproved.java
 * @package com.cxdai.base.entity
 * @author justin.xu
 * @version 0.1 2015年3月16日
 */
public class FirstTransferApproved implements Serializable {

	private static final long serialVersionUID = -3531851612222097637L;
	private Integer id;
	/** 直通车转让ID */
	private Integer firstTransferId;
	/** 审核状态（1： 等待审核 2：初审不通过 ，3：初审通过 4： 复审不通过 5：复审通过） */
	private Integer status;
	/** 审核人（后台用户id,系统为-1) */
	private Integer verifyUser;
	/** 审核IP **/
	private String verifyIp;
	/** 审核MAC */
	private String verifyMac;
	/** 审核时间 */
	private Date verifyTime;
	/** 审核备注 */
	private String verifyRemark;
	/** 1: portal 2:console 3:系统自动 */
	private Integer system;
	/** 平台来源(1：网页 2、微信 3 安卓 4 IOS) */
	private Integer platform;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFirstTransferId() {
		return firstTransferId;
	}

	public void setFirstTransferId(Integer firstTransferId) {
		this.firstTransferId = firstTransferId;
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

	public String getVerifyIp() {
		return verifyIp;
	}

	public void setVerifyIp(String verifyIp) {
		this.verifyIp = verifyIp;
	}

	public String getVerifyMac() {
		return verifyMac;
	}

	public void setVerifyMac(String verifyMac) {
		this.verifyMac = verifyMac;
	}

	public Date getVerifyTime() {
		return verifyTime;
	}

	public void setVerifyTime(Date verifyTime) {
		this.verifyTime = verifyTime;
	}

	public String getVerifyRemark() {
		return verifyRemark;
	}

	public void setVerifyRemark(String verifyRemark) {
		this.verifyRemark = verifyRemark;
	}

	public Integer getSystem() {
		return system;
	}

	public void setSystem(Integer system) {
		this.system = system;
	}

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}

}
