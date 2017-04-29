package com.cxdai.console.customer.information.entity;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Description:认证修改记录日志表<br />
 * </p>
 * 
 * @title ApproModifyLog.java
 * @package com.cxdai.console.entity
 * @author justin.xu
 * @version 0.1 2014年10月18日
 */
public class ApproModifyLog implements Serializable {

	/**
	 * @fields serialVersionUID
	 */

	private static final long serialVersionUID = 6779012998142515211L;

	/** 主键Id **/
	private Integer id;
	/** 用户ID **/
	private Integer userId;

	/** 旧内容 **/
	private String oldContent;

	/** 新内容 **/
	private String newContent;

	/** 修改类型（1、邮箱 2、手机） **/
	private Integer type;

	/** 添加时间 */
	private Date addtime;

	/** 添加人 */
	private Integer staffId;

	/** 添加IP */
	private String addIp;

	/** 添加的mac地址 */
	private String addMac;

	/** 备注 **/
	private String remark;
	/** 是否审核通过 */
	private Integer isPassed;
	/** 平台来源 1：官网 2、微信，即用户登录的客户端 */
	private Integer platform;

	public Integer getIsPassed() {
		return isPassed;
	}

	public void setIsPassed(Integer isPassed) {
		this.isPassed = isPassed;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOldContent() {
		return oldContent;
	}

	public void setOldContent(String oldContent) {
		this.oldContent = oldContent;
	}

	public String getNewContent() {
		return newContent;
	}

	public void setNewContent(String newContent) {
		this.newContent = newContent;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAddIp() {
		return addIp;
	}

	public void setAddIp(String addIp) {
		this.addIp = addIp;
	}

	public String getAddMac() {
		return addMac;
	}

	public void setAddMac(String addMac) {
		this.addMac = addMac;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}

	public static ApproModifyLog createApproModifyVo(Integer platform, Integer userId, String ipAddr, Integer isPassed, String remark,
			String newContent, Integer type) {
		ApproModifyLog apprModifyLog = new ApproModifyLog();
		apprModifyLog.setIsPassed(Integer.valueOf(isPassed));
		apprModifyLog.setOldContent("");
		apprModifyLog.setNewContent(newContent);
		apprModifyLog.setPlatform(platform);
		apprModifyLog.setUserId(userId);
		apprModifyLog.setAddIp(ipAddr);
		apprModifyLog.setAddtime(new Date());
		apprModifyLog.setStaffId(-1);
		apprModifyLog.setRemark(remark);
		apprModifyLog.setAddMac("@@@@");// GetMacAddress.getMacAddress()
		apprModifyLog.setType(type);
		return apprModifyLog;
	}
}

