package com.cxdai.console.statistics.customer.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.cxdai.console.util.MoneyUtil;

/**
 * 
 * <p>
 * Description:股东加权待收vo<br />
 * </p>
 * @title ShareholderRankVo.java
 * @package com.cxdai.console.account.vo 
 * @author yangshijin
 * @version 0.1 2014年7月4日
 */
public class ShareholderRankVo implements Serializable {
	private static final long serialVersionUID = -8828964992406883929L;
	/** 主键ID **/
	private Integer id;
	/** 用户ID　 **/
	private Integer userId;
	/** 加权待收　 **/
	private BigDecimal dayInterst;
	/** 加权待收排名　 **/
	private Integer dayInterstRank;
	/** 加权待收排名得分　 **/
	private BigDecimal dayInterstScore;
	/** 累计总积分　 **/
	private Integer accumulatepoints;
	/** 累计总积分排名　 **/
	private Integer accumulatepointsRank;
	/** 累计总积分排名得分　 **/
	private BigDecimal accumulatepointsScore;
	/** 优先投标总额　 **/
	private BigDecimal firstTenderTotal;
	/** 优先投标总额排名　 **/
	private Integer firstTenderTotalRank;
	/** 优先投标总额排名得分　 **/
	private BigDecimal firstTenderTotalScore;
	/** 推广有效人数　 **/
	private Integer extensionNumber;
	/** 推广有效人数排名　 **/
	private Integer extensionNumberRank;
	/** 推广有效人数排名得分　 **/
	private BigDecimal extensionNumberScore;
	/** 综合得分　 **/
	private BigDecimal totalScore;
	/** 添加时间 **/
	private String addtime;

	// 临时属性
	private Integer totalRank; // 综合排名
	private String username; // 用户名
	private String realname;  //姓名
	private String mobilenum; //手机号
	private String dayInterstStr;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public BigDecimal getDayInterst() {
		return dayInterst;
	}

	public void setDayInterst(BigDecimal dayInterst) {
		this.dayInterst = dayInterst;
	}

	public Integer getDayInterstRank() {
		return dayInterstRank;
	}

	public void setDayInterstRank(Integer dayInterstRank) {
		this.dayInterstRank = dayInterstRank;
	}

	public BigDecimal getDayInterstScore() {
		return dayInterstScore;
	}

	public void setDayInterstScore(BigDecimal dayInterstScore) {
		this.dayInterstScore = dayInterstScore;
	}

	public Integer getAccumulatepoints() {
		return accumulatepoints;
	}

	public void setAccumulatepoints(Integer accumulatepoints) {
		this.accumulatepoints = accumulatepoints;
	}

	public Integer getAccumulatepointsRank() {
		return accumulatepointsRank;
	}

	public void setAccumulatepointsRank(Integer accumulatepointsRank) {
		this.accumulatepointsRank = accumulatepointsRank;
	}

	public BigDecimal getAccumulatepointsScore() {
		return accumulatepointsScore;
	}

	public void setAccumulatepointsScore(BigDecimal accumulatepointsScore) {
		this.accumulatepointsScore = accumulatepointsScore;
	}

	public BigDecimal getFirstTenderTotal() {
		return firstTenderTotal;
	}

	public void setFirstTenderTotal(BigDecimal firstTenderTotal) {
		this.firstTenderTotal = firstTenderTotal;
	}

	public Integer getFirstTenderTotalRank() {
		return firstTenderTotalRank;
	}

	public void setFirstTenderTotalRank(Integer firstTenderTotalRank) {
		this.firstTenderTotalRank = firstTenderTotalRank;
	}

	public BigDecimal getFirstTenderTotalScore() {
		return firstTenderTotalScore;
	}

	public void setFirstTenderTotalScore(BigDecimal firstTenderTotalScore) {
		this.firstTenderTotalScore = firstTenderTotalScore;
	}

	public Integer getExtensionNumber() {
		return extensionNumber;
	}

	public void setExtensionNumber(Integer extensionNumber) {
		this.extensionNumber = extensionNumber;
	}

	public Integer getExtensionNumberRank() {
		return extensionNumberRank;
	}

	public void setExtensionNumberRank(Integer extensionNumberRank) {
		this.extensionNumberRank = extensionNumberRank;
	}

	public BigDecimal getExtensionNumberScore() {
		return extensionNumberScore;
	}

	public void setExtensionNumberScore(BigDecimal extensionNumberScore) {
		this.extensionNumberScore = extensionNumberScore;
	}

	public BigDecimal getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(BigDecimal totalScore) {
		this.totalScore = totalScore;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public Integer getTotalRank() {
		return totalRank;
	}

	public void setTotalRank(Integer totalRank) {
		this.totalRank = totalRank;
	}

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

	public String getMobilenum() {
		return mobilenum;
	}

	public void setMobilenum(String mobilenum) {
		this.mobilenum = mobilenum;
	}

	public String getDayInterstStr() {
		if(dayInterst != null){
			return MoneyUtil.fmtMoneyByDot(dayInterst);
		}
		return dayInterstStr;
	}

	public void setDayInterstStr(String dayInterstStr) {
		this.dayInterstStr = dayInterstStr;
	}
}
