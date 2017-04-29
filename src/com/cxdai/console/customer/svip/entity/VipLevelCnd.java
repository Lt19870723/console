package com.cxdai.console.customer.svip.entity;

import java.io.Serializable;

import com.cxdai.console.common.page.BaseCnd;

/**
 * 
 * <p>
 * Description:终身顶级会员<br />
 * </p>
 * 
 * @title TopSvip.java
 * @package com.cxdai.base.entity
 * @author yangshijin
 * @version 0.1 2015年1月13日
 */
public class VipLevelCnd extends BaseCnd implements Serializable {
	private static final long serialVersionUID = -2033213769093637666L;

	/** 用户ID */
	private Integer userId;
	/** 用户名 */
	private String username;
	/** 真实姓名 */
	private String realName;
	/** 是否是VIP */
	private Integer isVip;
	/** 是否是顶级VIP */
	private Integer isSvip;
	/** 排序语句 */
	private String orderSql;
	
	private String remark; //设置备注

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getIsVip() {
		return isVip;
	}

	public void setIsVip(Integer isVip) {
		this.isVip = isVip;
	}

	public Integer getIsSvip() {
		return isSvip;
	}

	public void setIsSvip(Integer isSvip) {
		this.isSvip = isSvip;
	}

	public String getOrderSql() {
		return orderSql;
	}

	public void setOrderSql(String orderSql) {
		this.orderSql = orderSql;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
