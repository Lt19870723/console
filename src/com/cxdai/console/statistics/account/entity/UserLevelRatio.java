package com.cxdai.console.statistics.account.entity;

/**
 * 
 * <p>
 * Description:获得用户会员等级和比率<br />
 * </p>
 * 
 * @title UserLevelRatio.java
 * @package com.cxdai.portal.account.util
 * @author yangshijin
 * @version 0.1 2014年12月18日
 */
public class UserLevelRatio {
	/** 用户id */
	private Integer userid;
	/** 等级 */
	private String o_userLevel;
	/** 比率 */
	private String o_ratio;

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getO_userLevel() {
		return o_userLevel;
	}

	public void setO_userLevel(String o_userLevel) {
		this.o_userLevel = o_userLevel;
	}

	public String getO_ratio() {
		return o_ratio;
	}

	public void setO_ratio(String o_ratio) {
		this.o_ratio = o_ratio;
	}
}
