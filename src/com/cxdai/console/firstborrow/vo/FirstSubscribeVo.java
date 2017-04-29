package com.cxdai.console.firstborrow.vo;

import java.io.Serializable;

import com.cxdai.console.base.entity.FirstSubscribe;
import com.cxdai.console.util.DateUtils;

/**
 * <p>
 * Description:直通车转让认购Vo<br />
 * </p>
 * 
 * @title FirstSubscribeVo.java
 * @package com.cxdai.portal.first.vo
 * @author 朱泳霖
 * @version 0.1 2015年3月19日
 */
public class FirstSubscribeVo extends FirstSubscribe implements Serializable {
	private static final long serialVersionUID = -191815169850848319L;
	
	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 认购时间
	 */
	private String addTimeStr;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAddTimeStr() {
		if (super.getAddTime() != null) {
			return DateUtils.format(super.getAddTime(), DateUtils.YMD_HMS);
		}
		return addTimeStr;
	}

	public void setAddTimeStr(String addTimeStr) {
		this.addTimeStr = addTimeStr;
	}

}
