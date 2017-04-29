package com.cxdai.console.customer.information.entity;

import java.io.Serializable;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.cxdai.console.system.vo.UserVo;
import com.cxdai.console.util.HttpTookit;

/**
 * <p>
 * Description:用户审核记录日志表<br />
 * </p>
 * 
 * @title MemberAuditLog.java
 * @package com.cxdai.console.entity
 * @author hujianpan
 * @version 0.1 2014年11月14日
 */
public class MemberAuditLog implements Serializable {

	/**
	 * @fields serialVersionUID
	 */

	private static final long serialVersionUID = 6779012998142515211L;

	/** 主键Id **/
	private Integer id;
	/** 用户ID **/
	private Integer userId;

	/** 用户名 **/
	private String memberName;

	/** 修改类型（） **/
	private Integer type;

	/** 添加时间 */
	private Date addtime;

	/** 添加人 */
	private Integer staffId;

	/** 添加IP */
	private String addIp;

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	/** 备注 **/
	private String remark;
	/** 是否审核通过 */
	private Integer isPassed;

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

	public static MemberAuditLog createMemberAuditLog(Member member, UserVo user, Integer isPass, String remark, HttpServletRequest request) {
		MemberAuditLog memberAuditLog = new MemberAuditLog();
		memberAuditLog.setAddIp(HttpTookit.getRealIpAddr(request));
		memberAuditLog.setAddtime(new Date());
		memberAuditLog.setIsPassed(isPass);
		memberAuditLog.setMemberName(member.getUsername());
		memberAuditLog.setRemark(remark);
		memberAuditLog.setStaffId(user.getId());
		memberAuditLog.setUserId(member.getId());
		return memberAuditLog;
	}
}
