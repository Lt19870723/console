package com.cxdai.console.base.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Description:直通车转让事件日志类<br />
 * </p>
 * 
 * @title FirstTransferLog.java
 * @package com.cxdai.base.entity
 * @author justin.xu
 * @version 0.1 2015年3月16日
 */
public class FirstTransferLog implements Serializable {

	private static final long serialVersionUID = -3531851612222097637L;
	private Integer id;
	/** 直通车转让ID */
	private Integer firstTransferId;
	/** 操作人 */
	private Integer userId;
	/** 操作时间 */
	private Date addtime;
	/** 操作IP */
	private String addip;
	/** 操作MAC */
	private String addmac;
	/** 操作人(portal 对应账号用户名,console对应真实姓名,系统对应系统自动) */
	private String addname;
	/** 操作备注 */
	private String addRemark;
	/** 日志类型（1：新增、2：撤消、3、转让成功、4、流转 5：复审失败） */
	private Integer logType;
	/** 1: portal 2:console 3:系统自动 */
	private Integer system;
	/** 发起的平台来源(1：网页 2、微信 3 安卓 4 IOS) */
	private Integer platform;

	public FirstTransferLog() {
		super();
	}

	public FirstTransferLog(Integer firstTransferId, Integer userId, Date addtime, String addip, String addmac, String addname, String addRemark, Integer logType, Integer system, Integer platform) {
		super();
		this.firstTransferId = firstTransferId;
		this.userId = userId;
		this.addtime = addtime;
		this.addip = addip;
		this.addmac = addmac;
		this.addname = addname;
		this.addRemark = addRemark;
		this.logType = logType;
		this.system = system;
		this.platform = platform;
	}

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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getAddip() {
		return addip;
	}

	public void setAddip(String addip) {
		this.addip = addip;
	}

	public String getAddmac() {
		return addmac;
	}

	public void setAddmac(String addmac) {
		this.addmac = addmac;
	}

	public String getAddname() {
		return addname;
	}

	public void setAddname(String addname) {
		this.addname = addname;
	}

	public String getAddRemark() {
		return addRemark;
	}

	public void setAddRemark(String addRemark) {
		this.addRemark = addRemark;
	}

	public Integer getLogType() {
		return logType;
	}

	public void setLogType(Integer logType) {
		this.logType = logType;
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
