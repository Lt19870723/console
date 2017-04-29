package com.cxdai.console.curaccount.vo;

import java.io.Serializable;

import com.cxdai.console.common.page.BaseCnd;

/**
 * <p>
 * Description: 活期宝账户 Cnd <br />
 * </p>
 * 
 * @title CurAccountCnd.java
 * @package com.cxdai.portal.curAccount.vo
 * @author HuangJun   
 * @version 0.1 2015年5月6日  
 */
public class CurAccountCnd extends BaseCnd implements Serializable {

	private static final long serialVersionUID = -6903226903535940857L;

	private Integer userId;

	private String beginDay;
	private String endDay;
	// 查询不同时间的tag, 如： 7天，30天
	private String tag;

	// 收益明细首次加载tag
	private String initTag;

	// 用户名
	private String userName;

	/**
	 * @return userId : return the property userId.
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            : set the property userId.
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return beginDay : return the property beginDay.
	 */
	public String getBeginDay() {
		return beginDay;
	}

	/**
	 * @param beginDay
	 *            : set the property beginDay.
	 */
	public void setBeginDay(String beginDay) {
		this.beginDay = beginDay;
	}

	/**
	 * @return endDay : return the property endDay.
	 */
	public String getEndDay() {
		return endDay;
	}

	/**
	 * @param endDay
	 *            : set the property endDay.
	 */
	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}

	/**
	 * @return tag : return the property tag.
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * @param tag
	 *            : set the property tag.
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	/**
	 * @return initTag : return the property initTag.
	 */
	public String getInitTag() {
		return initTag;
	}

	/**
	 * @param initTag
	 *            : set the property initTag.
	 */
	public void setInitTag(String initTag) {
		this.initTag = initTag;
	}

	/**
	 * @return userName : return the property userName.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            : set the property userName.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

}