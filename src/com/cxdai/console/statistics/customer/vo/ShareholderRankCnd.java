package com.cxdai.console.statistics.customer.vo;

import java.io.Serializable;

import com.cxdai.console.common.page.BaseCnd;

/**
 * 
 * <p>
 * Description:股东加权待收<br />
 * </p>
 * 
 * @title ShareholderRankCnd.java
 * @package com.cxdai.console.account.vo
 * @author yangshijin
 * @version 0.1 2014年7月4日
 */
public class ShareholderRankCnd extends BaseCnd implements Serializable {

	private static final long serialVersionUID = -3599280498753668448L;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 姓名
	 */
	private String realname;

	/** 添加时间 **/
	private String addtime;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

}
