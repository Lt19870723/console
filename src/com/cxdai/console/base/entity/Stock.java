package com.cxdai.console.base.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.MoneyUtil;

/**
 * 投资人股权信息表
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title Stock.java
 * @package com.cxdai.base.entity
 * @author huangpin
 * @version 0.1 2014年9月11日
 */
public class Stock implements Serializable {

	private static final long serialVersionUID = -4585858481938746536L;

	private Integer id; // 自增ID
	private Integer userId; // 股权持有用户ID
	private Integer rank; // 股权排名
	private Integer totalRank; // 综合排名
	private Long stockNum; // 股权数量
	private BigDecimal stockMoney; // 现金行权后金额
	private Integer status; // 股权状态：1-期权持有中 2-已现金行权
	private Long exerciseTime; // 行权时间
	private String exerciseIp; // 行权ip
	private Long addTime; // 添加时间
	private String addIp; // 添加ip
	private Integer isAdminExercise; // 是否管理员代为行权：0-否 1-是
	private Integer adminId; // 行权管理员ID

	// 关联查询
	private String username; // 用户名
	private String realname; // 真实姓名
	private BigDecimal total; // 当前总额
	private BigDecimal maxTotal; // 历史最大总额
	private Long maxTime; // 最大总额时间
	private Integer isReduce; // 是否减仓超九成：0-否 1-是
	private BigDecimal curTotal;// 当前活期宝总额
	private BigDecimal fixTotal; // 定期宝
	// 显示属性
	private String statusStr;
	private String addTimeStr;
	private String exerciseTimeStr;
	private String isAdminExerciseStr;
	private String stockMoneyStr;
	private String totalStr;
	private String maxTotalStr;
	private String maxTimeStr;
	private String isReduceStr;
	private String curTotalStr;
	private String fixTotalStr;
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

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Long getStockNum() {
		return stockNum;
	}

	public void setStockNum(Long stockNum) {
		this.stockNum = stockNum;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsAdminExercise() {
		return isAdminExercise;
	}

	public void setIsAdminExercise(Integer isAdminExercise) {
		this.isAdminExercise = isAdminExercise;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public BigDecimal getStockMoney() {
		return stockMoney;
	}

	public void setStockMoney(BigDecimal stockMoney) {
		this.stockMoney = stockMoney;
	}

	public String getExerciseIp() {
		return exerciseIp;
	}

	public void setExerciseIp(String exerciseIp) {
		this.exerciseIp = exerciseIp;
	}

	public String getAddIp() {
		return addIp;
	}

	public void setAddIp(String addIp) {
		this.addIp = addIp;
	}

	public Long getExerciseTime() {
		return exerciseTime;
	}

	public void setExerciseTime(Long exerciseTime) {
		this.exerciseTime = exerciseTime;
	}

	public Long getAddTime() {
		return addTime;
	}

	public void setAddTime(Long addTime) {
		this.addTime = addTime;
	}

	public Integer getTotalRank() {
		return totalRank;
	}

	public void setTotalRank(Integer totalRank) {
		this.totalRank = totalRank;
	}

	public String getStatusStr() {
		if (status != null) {
			if (status == 1)
				statusStr = "期权持有中";
			if (status == 2)
				statusStr = "已现金行权";
		}
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public String getAddTimeStr() {
		if (addTime != null) {
			addTimeStr = DateUtils.timeStampToDate(addTime + "", DateUtils.YMD_HMS);
		}
		return addTimeStr;
	}

	public void setAddTimeStr(String addTimeStr) {
		this.addTimeStr = addTimeStr;
	}

	public String getExerciseTimeStr() {
		if (exerciseTime != null) {
			exerciseTimeStr = DateUtils.timeStampToDate(exerciseTime / 1000 + "", DateUtils.YMD_HMS);
		}
		return exerciseTimeStr;
	}

	public void setExerciseTimeStr(String exerciseTimeStr) {
		this.exerciseTimeStr = exerciseTimeStr;
	}

	public String getIsAdminExerciseStr() {
		if (isAdminExercise != null) {
			if (isAdminExercise == 1)
				isAdminExerciseStr = "是";
			else if (isAdminExercise == 0)
				isAdminExerciseStr = "否";
		}
		return isAdminExerciseStr;
	}

	public void setIsAdminExerciseStr(String isAdminExerciseStr) {
		this.isAdminExerciseStr = isAdminExerciseStr;
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

	public String getStockMoneyStr() {
		if (null != stockMoney) {
			stockMoneyStr = "¥ " + MoneyUtil.fmtMoneyByDot(stockMoney);
		}
		return stockMoneyStr;
	}

	public void setStockMoneyStr(String stockMoneyStr) {
		this.stockMoneyStr = stockMoneyStr;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getMaxTotal() {
		return maxTotal;
	}

	public void setMaxTotal(BigDecimal maxTotal) {
		this.maxTotal = maxTotal;
	}

	public Long getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(Long maxTime) {
		this.maxTime = maxTime;
	}

	public Integer getIsReduce() {
		return isReduce;
	}

	public void setIsReduce(Integer isReduce) {
		this.isReduce = isReduce;
	}

	public String getIsReduceStr() {
		if (isReduce != null) {
			if (isReduce == 1)
				isReduceStr = "是";
			else if (isReduce == 0)
				isReduceStr = "否";
		}
		return isReduceStr;
	}

	public void setIsReduceStr(String isReduceStr) {
		this.isReduceStr = isReduceStr;
	}

	public String getTotalStr() {
		if (null != total) {
			totalStr = "¥ " + MoneyUtil.fmtMoneyByDot(total);
		}
		return totalStr;
	}

	public void setTotalStr(String totalStr) {
		this.totalStr = totalStr;
	}

	public String getMaxTotalStr() {
		if (null != maxTotal) {
			maxTotalStr = "¥ " + MoneyUtil.fmtMoneyByDot(maxTotal);
		}
		return maxTotalStr;
	}

	public void setMaxTotalStr(String maxTotalStr) {
		this.maxTotalStr = maxTotalStr;
	}

	public String getMaxTimeStr() {
		if (maxTime != null) {
			maxTimeStr = DateUtils.timeStampToDate(maxTime + "", DateUtils.YMD_HMS);
		}
		return maxTimeStr;
	}

	public void setMaxTimeStr(String maxTimeStr) {
		this.maxTimeStr = maxTimeStr;
	}

	public BigDecimal getCurTotal() {
		return curTotal;
	}

	public void setCurTotal(BigDecimal curTotal) {
		this.curTotal = curTotal;
	}

	public String getCurTotalStr() {
		if (null != curTotal) {
			curTotalStr = "¥ " + MoneyUtil.fmtMoneyByDot(curTotal);
		}
		return curTotalStr;
	}

	public void setCurTotalStr(String curTotalStr) {
		this.curTotalStr = curTotalStr;
	}
	public BigDecimal getFixTotal() {
		return fixTotal;
	}

	public void setFixTotal(BigDecimal fixTotal) {
		this.fixTotal = fixTotal;
	}

	public String getFixTotalStr() {
		if (null != fixTotal) {
			fixTotalStr = "¥ " + MoneyUtil.fmtMoneyByDot(fixTotal);
		}
		return fixTotalStr;
	}

	public void setFixTotalStr(String fixTotalStr) {
		this.fixTotalStr = fixTotalStr;
	}



}
