package com.cxdai.console.fix.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * Description:定期宝转让<br />
 * </p>
 * 
 * @title FixBorrowTransfer.java
 * @package com.cxdai.console.fix.entity
 * @author HuangJun
 * @version 0.1 2015年6月25日
 */
public class FixBorrowTransfer implements Serializable {

	private static final long serialVersionUID = -6621016547027706877L;

	/**
	 * 主键ID
	 */
	private Integer id;

	/**
	 * 定期宝ID
	 */
	private Integer fixBorrowId;

	/**
	 * 债权本金
	 */
	private BigDecimal account;

	private String accountRealWan;//万元为单位
	
	/**
	 * 可用余额
	 */
	private BigDecimal useMoney;

	/**
	 * 奖励
	 */
	private BigDecimal awards;

	/**
	 * 转让价格
	 */
	private BigDecimal accountReal;

	/**
	 * 已经借到的金额
	 */
	private BigDecimal accountYes;

	/**
	 * 锁定期限
	 */
	private Integer fixLockLimit;

	/**
	 * 锁定方式
	 */
	private Integer fixLockStyle;

	/**
	 * 锁定结束日期
	 */
	private Date fixLockEndtime;

	/**
	 * 年利率
	 */
	private BigDecimal fixApr;

	/**
	 * 定期宝名称
	 */
	private String fixBorrowName;

	/**
	 * 定期宝生成编号
	 */
	private String fixContractNo;

	/**
	 * 定向密码
	 */
	private String bidPassword;

	/**
	 * 添加时间
	 */
	private Date addtime;

	/**
	 * 添加人
	 */
	private Integer adduser;

	/**
	 * 最后修改时间
	 */
	private Date lastUpdateTime;

	/**
	 * 最后修改人
	 */
	private Integer lastUpdateUser;

	/**
	 * 定期宝转让成功时间
	 */
	private Date successTime;

	/**
	 * 状态
	 */
	private Integer status;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 发起的平台来源
	 */
	private Integer platform;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFixBorrowId() {
		return fixBorrowId;
	}

	public void setFixBorrowId(Integer fixBorrowId) {
		this.fixBorrowId = fixBorrowId;
	}

	public BigDecimal getAccount() {
		return account;
	}

	public void setAccount(BigDecimal account) {
		this.account = account;
	}

	public BigDecimal getUseMoney() {
		return useMoney;
	}

	public void setUseMoney(BigDecimal useMoney) {
		this.useMoney = useMoney;
	}

	public BigDecimal getAwards() {
		return awards;
	}

	public void setAwards(BigDecimal awards) {
		this.awards = awards;
	}
	
	/**
	 * @return accountRealWan : return the property accountRealWan.
	 */
	public String getAccountRealWan() {
		if(accountReal!=null)
		{
			return String.valueOf((accountReal.add(useMoney)).divide(new BigDecimal(10000)));
		}
		return accountRealWan;
	}

	/**
	 * @param accountRealWan
	 *            : set the property accountRealWan.
	 */
	public void setAccountRealWan(String accountRealWan) {
		this.accountRealWan = accountRealWan;
	}

	public BigDecimal getAccountReal() {
		return accountReal;
	}

	public void setAccountReal(BigDecimal accountReal) {
		this.accountReal = accountReal;
	}

	public BigDecimal getAccountYes() {
		return accountYes;
	}

	public void setAccountYes(BigDecimal accountYes) {
		this.accountYes = accountYes;
	}

	public Integer getFixLockLimit() {
		return fixLockLimit;
	}

	public void setFixLockLimit(Integer fixLockLimit) {
		this.fixLockLimit = fixLockLimit;
	}

	public Integer getFixLockStyle() {
		return fixLockStyle;
	}

	public void setFixLockStyle(Integer fixLockStyle) {
		this.fixLockStyle = fixLockStyle;
	}

	public Date getFixLockEndtime() {
		return fixLockEndtime;
	}

	public void setFixLockEndtime(Date fixLockEndtime) {
		this.fixLockEndtime = fixLockEndtime;
	}

	public BigDecimal getFixApr() {
		return fixApr;
	}

	public void setFixApr(BigDecimal fixApr) {
		this.fixApr = fixApr;
	}

	public String getFixBorrowName() {
		return fixBorrowName;
	}

	public void setFixBorrowName(String fixBorrowName) {
		this.fixBorrowName = fixBorrowName;
	}

	public String getFixContractNo() {
		return fixContractNo;
	}

	public void setFixContractNo(String fixContractNo) {
		this.fixContractNo = fixContractNo;
	}

	public String getBidPassword() {
		return bidPassword;
	}

	public void setBidPassword(String bidPassword) {
		this.bidPassword = bidPassword;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Integer getAdduser() {
		return adduser;
	}

	public void setAdduser(Integer adduser) {
		this.adduser = adduser;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Integer getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(Integer lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	public Date getSuccessTime() {
		return successTime;
	}

	public void setSuccessTime(Date successTime) {
		this.successTime = successTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}
}