package com.cxdai.console.statistics.tender.entity;

/**
 * <p>
 * Description:推荐注册情况<br />
 * </p>
 * 
 * @title RecommendInfoVo.java
 * @package com.cxdai.console.opration.vo
 * @author justin.xu
 * @version 0.1 2014年12月25日
 */
public class RecommendInfoVo {

	/**
	 * 推荐人数量
	 */
	private Integer referrerPersons;
	/**
	 * 被推荐人数量
	 */
	private Integer recommendedPersons;

	public Integer getReferrerPersons() {
		return referrerPersons;
	}

	public void setReferrerPersons(Integer referrerPersons) {
		this.referrerPersons = referrerPersons;
	}

	public Integer getRecommendedPersons() {
		return recommendedPersons;
	}

	public void setRecommendedPersons(Integer recommendedPersons) {
		this.recommendedPersons = recommendedPersons;
	}
}