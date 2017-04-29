package com.cxdai.console.base.entity;

import java.io.Serializable;

/**
 * <p>
 * Description:员工<br />
 * </p>
 * 
 * @title Staff.java
 * @package com.cxdai.base.entity
 * @author justin.xu
 * @version 0.1 2014年4月25日
 */
public class Staff implements Serializable {
	private static final long serialVersionUID = 1206107633267339937L;

	private Integer id;

	/** 工作编号 */
	private String jobNum;
	/** 登陆密码 */
	private String passwd;
	/** 员工姓名 */
	private String name;
	/** QQ号码 */
	private String qq;
	/** E-Mail */
	private String email;
	/** 手机号码 */
	private String mobile;
	/** 座机号码 */
	private String tel;
	/** 角色岗位【1：财务部，2：风险控制，3：运营团队，4：贷后管理，5：人事HR，9：管理员】 */
	private Integer jobPost;
	/** 角色权限【0：员工权限，1：团队经理权限，2：主管（部门经理）权限，3：副总经理权限，4：总经理权限】 */
	private Integer powerRight;
	/** 审核权限【0：没有权限，1：初审权限；2：反欺诈权限；3：终审权限；4：复审权限，5:撤标权限，9：所有审核权限】 */
	private Integer approPower;
	/** 入职日期 */
	private String comeinDate;
	/** 部门值 */
	private String deptValue;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getJobNum() {
		return jobNum;
	}

	public void setJobNum(String jobNum) {
		this.jobNum = jobNum;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Integer getJobPost() {
		return jobPost;
	}

	public void setJobPost(Integer jobPost) {
		this.jobPost = jobPost;
	}

	public Integer getPowerRight() {
		return powerRight;
	}

	public void setPowerRight(Integer powerRight) {
		this.powerRight = powerRight;
	}

	public Integer getApproPower() {
		return approPower;
	}

	public void setApproPower(Integer approPower) {
		this.approPower = approPower;
	}

	public String getComeinDate() {
		return comeinDate;
	}

	public void setComeinDate(String comeinDate) {
		this.comeinDate = comeinDate;
	}

	public String getDeptValue() {
		return deptValue;
	}

	public void setDeptValue(String deptValue) {
		this.deptValue = deptValue;
	}

}
