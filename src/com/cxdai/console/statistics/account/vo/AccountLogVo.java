package com.cxdai.console.statistics.account.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.MoneyUtil;



/**
 * <p>
 * Description:资金操作日志Vo<br />
 * </p>
 * 
 * @title AccountLogVo.java
 * @package com.cxdai.console.account.vo
 * @author justin.xu
 * @version 0.1 2014年4月25日
 */
public class AccountLogVo implements Serializable {
	private static final long serialVersionUID = 8983061145517554053L;
	private Integer id;
	/** 會員ID */
	private Integer userId;
	/** 日誌類型 */
	private String type;
	/** 帳戶總額 */
	private BigDecimal total;
	/** 操作金額 */
	private BigDecimal money;
	/** 可用餘額 */
	private BigDecimal useMoney;
	/** 凍結金額 */
	private BigDecimal noUseMoney;
	/** 待收總額 */
	private BigDecimal collection;
	/** 交易對方 */
	private Integer toUser;
	/** 日誌備註 */
	private String remark;
	/** 操作時間 */
	private String addtime;
	/** 插入IP */
	private String addip;
	/** 优先计划总可用金额 */
	private BigDecimal firstBorrowUseMoney;
	/** 可提现金额 */
	private BigDecimal drawMoney;
	/** 不可提现金额 */
	private BigDecimal noDrawMoney;
	/** 注册时间 */
	private String registTime;

	/** 存管可用金额*/
	private BigDecimal eUseMoney;
	/**存管冻结金额*/
	private BigDecimal eFreezeMoney;
	/** 存管待收金额*/
	private BigDecimal eCollection;
	private BigDecimal accountTotal;
	
	private Integer isCustody;//是否开立存管账户（0：未开立；1：已开立）
	//临时属性
	private String username;
	private String typeStr;
	private String totalStr;
	private String useMoneyStr;
	private String noUseMoneyStr;
	private String collectionStr;
	private String drawMoneyStr;
	private String noDrawMoneyStr;
	private String firstBorrowUseMoneyStr;
	private String moneyStr;
	private String addtimeStr;
	private String eUseMoneyStr;
	private String eFreezeMoneyStr;
	private String eCollectionStr;
	private String accountTotalStr;
	/** 0:借款标ID, 1:直通车id, 2:待还记录id null:其他资金操作日志,例如：网站奖励成功，资金已入账 */
	private Integer idType;

	/** 借款标ID */
	private Integer borrowId;
	/** 借款标NAME */
	private String borrowName;

	public Integer getIdType() {
		return idType;
	}

	public void setIdType(Integer idType) {
		this.idType = idType;
	}

	public Integer getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}

	public String getBorrowName() {
		return borrowName;
	}

	public void setBorrowName(String borrowName) {
		this.borrowName = borrowName;
	}

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getUseMoney() {
		return useMoney;
	}

	public void setUseMoney(BigDecimal useMoney) {
		this.useMoney = useMoney;
	}

	public BigDecimal getNoUseMoney() {
		return noUseMoney;
	}

	public void setNoUseMoney(BigDecimal noUseMoney) {
		this.noUseMoney = noUseMoney;
	}

	public BigDecimal getCollection() {
		return collection;
	}

	public void setCollection(BigDecimal collection) {
		this.collection = collection;
	}

	public Integer getToUser() {
		return toUser;
	}

	public void setToUser(Integer toUser) {
		this.toUser = toUser;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getAddip() {
		return addip;
	}

	public void setAddip(String addip) {
		this.addip = addip;
	}

	public BigDecimal getFirstBorrowUseMoney() {
		return firstBorrowUseMoney;
	}

	public void setFirstBorrowUseMoney(BigDecimal firstBorrowUseMoney) {
		this.firstBorrowUseMoney = firstBorrowUseMoney;
	}

	public BigDecimal getDrawMoney() {
		return drawMoney;
	}

	public void setDrawMoney(BigDecimal drawMoney) {
		this.drawMoney = drawMoney;
	}

	public BigDecimal getNoDrawMoney() {
		return noDrawMoney;
	}

	public void setNoDrawMoney(BigDecimal noDrawMoney) {
		this.noDrawMoney = noDrawMoney;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTypeStr() {
		return typeStr;
	}

	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}
	
	public String getTotalStr() {
		if(accountTotal != null){
			return MoneyUtil.fmtMoneyByDot(accountTotal);
		}
		return totalStr;
	}

	public void setTotalStr(String totalStr) {
		this.totalStr = totalStr;
	}

	public String getUseMoneyStr() {
		if(useMoney != null){
			return MoneyUtil.fmtMoneyByDot(useMoney);
		}
		return useMoneyStr;
	}

	public void setUseMoneyStr(String useMoneyStr) {
		this.useMoneyStr = useMoneyStr;
	}

	public String getNoUseMoneyStr() {
		if(noUseMoney != null){
			return MoneyUtil.fmtMoneyByDot(noUseMoney);
		}
		return noUseMoneyStr;
	}

	public void setNoUseMoneyStr(String noUseMoneyStr) {
		this.noUseMoneyStr = noUseMoneyStr;
	}

	public String getCollectionStr() {
		if(collection != null){
			return MoneyUtil.fmtMoneyByDot(collection);
		}
		return collectionStr;
	}

	public void setCollectionStr(String collectionStr) {
		this.collectionStr = collectionStr;
	}

	public String getDrawMoneyStr() {
		if(drawMoney != null){
			return MoneyUtil.fmtMoneyByDot(drawMoney);
		}
		return drawMoneyStr;
	}

	public void setDrawMoneyStr(String drawMoneyStr) {
		this.drawMoneyStr = drawMoneyStr;
	}

	public String getNoDrawMoneyStr() {
		if(noDrawMoney != null){
			return MoneyUtil.fmtMoneyByDot(noDrawMoney);
		}
		return noDrawMoneyStr;
	}

	public void setNoDrawMoneyStr(String noDrawMoneyStr) {
		this.noDrawMoneyStr = noDrawMoneyStr;
	}

	public String getFirstBorrowUseMoneyStr() {
		if(firstBorrowUseMoney != null){
			return MoneyUtil.fmtMoneyByDot(firstBorrowUseMoney);
		}
		return firstBorrowUseMoneyStr;
	}

	public void setFirstBorrowUseMoneyStr(String firstBorrowUseMoneyStr) {
		this.firstBorrowUseMoneyStr = firstBorrowUseMoneyStr;
	}

	public String getMoneyStr() {
		if(money != null){
			return MoneyUtil.fmtMoneyByDot(money);
		}
		return moneyStr;
	}

	public void setMoneyStr(String moneyStr) {
		this.moneyStr = moneyStr;
	}

	public String getAddtimeStr() {
		if(addtime != null && !addtime.equals("")){
			return DateUtils.timeStampToDate(addtime, DateUtils.YMD_HMS);
		}
		return addtimeStr;
	}
	

	public void setAddtimeStr(String addtimeStr) {
		this.addtimeStr = addtimeStr;
	}
	public String getRemarkStr(){
		if(this.idType!=null && this.borrowId!=null && this.idType.intValue()==1){
			if(borrowName.length()>10){
				borrowName=borrowName.substring(0, 10);
			}
			return this.remark+" (直通车："+borrowName+")";
		}else if(this.idType!=null && this.borrowId!=null && this.idType.intValue()==0){
			if(borrowName.length()>10){
				borrowName=borrowName.substring(0, 10);
			}
			return this.remark+" (借款标："+borrowName+")";
		}else{
			return this.remark;
		}
	 
	}

	public String getRegistTime() {
		return registTime;
	}

	public void setRegistTime(String registTime) {
		this.registTime = registTime;
	}

	public BigDecimal geteUseMoney() {
		return eUseMoney;
	}

	public void seteUseMoney(BigDecimal eUseMoney) {
		this.eUseMoney = eUseMoney;
	}

	public BigDecimal geteFreezeMoney() {
		return eFreezeMoney;
	}

	public void seteFreezeMoney(BigDecimal eFreezeMoney) {
		this.eFreezeMoney = eFreezeMoney;
	}

	public BigDecimal geteCollection() {
		return eCollection;
	}

	public void seteCollection(BigDecimal eCollection) {
		this.eCollection = eCollection;
	}

	public Integer getIsCustody() {
		return isCustody;
	}

	public void setIsCustody(Integer isCustody) {
		this.isCustody = isCustody;
	}



	public String geteUseMoneyStr() {

		if(eUseMoney != null){
			return MoneyUtil.fmtMoneyByDot(eUseMoney);
		}
		return eUseMoneyStr;
	}

	public void seteUseMoneyStr(String eUseMoneyStr) {
		this.eUseMoneyStr = eUseMoneyStr;
	}

	public String geteCollectionStr() {
		if(eCollection != null){
			return MoneyUtil.fmtMoneyByDot(eCollection);
		}
		return eCollectionStr;
	}

	public void seteCollectionStr(String eCollectionStr) {
		this.eCollectionStr = eCollectionStr;
	}

	public String geteFreezeMoneyStr() {
		if(eFreezeMoney != null){
			return MoneyUtil.fmtMoneyByDot(eFreezeMoney);
		}
		return eFreezeMoneyStr;
	}

	public void seteFreezeMoneyStr(String eFreezeMoneyStr) {
		this.eFreezeMoneyStr = eFreezeMoneyStr;
	}

	public BigDecimal getAccountTotal() {
		return accountTotal;
	}

	public void setAccountTotal(BigDecimal accountTotal) {
		this.accountTotal = accountTotal;
	}

	public String getAccountTotalStr() {
		if(accountTotal != null){
			return MoneyUtil.fmtMoneyByDot(accountTotal);
		}
		return accountTotalStr;
	}

	public void setAccountTotalStr(String accountTotalStr) {
		this.accountTotalStr = accountTotalStr;
	}
	
}
