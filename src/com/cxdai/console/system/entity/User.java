package com.cxdai.console.system.entity;

import java.io.Serializable;

/**
 * <p>
 * Description:用户<br />
 * </p>
 * 
 * @title User.java
 * @package com.cxdai.console.system.entity
 * @author qiongbiao.zhang
 * @version 0.1 2015年3月2日
 */
public class User implements Serializable {
	private static final long serialVersionUID = 1998534976523975259L;
	
	private Integer id; // 主键
	private String userName; // 用户名
	private String password; // 密码
	private String name; // 姓名
	private String email; // 邮箱
	private String mobile; // 手机
	private String tel; // 座机
	private String dept; // 部门
	private String position; // 职位
	private Integer status; // 状态(0:启用,1:禁用)
	private String roleName;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}