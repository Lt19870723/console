package com.cxdai.console.customer.information.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.cxdai.console.util.MoneyUtil;

/**
 * <p>
 * Description:账户类<br />
 * </p>
 * 
 * @title AccountVo.java
 * @package com.cxdai.console.account.vo
 * @author justin.xu
 * @version 0.1 2014年4月25日
 */
public class UnusualAccountVo implements Serializable {

	private static final long serialVersionUID = -3531851612222097637L;
	private Integer id;
	private Integer userId;
	/** 账户总额 */
	private BigDecimal total;
	/** 可用餘額 */
	private BigDecimal useMoney;
	/** 凍結金額 */
	private BigDecimal noUseMoney;
	/** 待收總額 */
	private BigDecimal collection;
	/** 贷款額度 */
	private BigDecimal netvalue;
	/** 可提现金额 */
	private BigDecimal drawMoney;
	/** 不可提现金额 */
	private BigDecimal noDrawMoney;
	/** 优先计划总可用金额 */
	private BigDecimal firstBorrowUseMoney;
	/** 版本号. **/
	private Integer version;

	// 临时属性
	private String totalStr;
	private String useMoneyStr;
	private String noUseMoneyStr;
	private String collectionStr;
	private String netvalueStr;
	private String drawMoneyStr;
	private String noDrawMoneyStr;
	private String firstBorrowUseMoneyStr;
	private String username;

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

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
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

	public BigDecimal getNetvalue() {
		return netvalue;
	}

	public void setNetvalue(BigDecimal netvalue) {
		this.netvalue = netvalue;
	}

	public BigDecimal getFirstBorrowUseMoney() {
		return firstBorrowUseMoney;
	}

	public void setFirstBorrowUseMoney(BigDecimal firstBorrowUseMoney) {
		this.firstBorrowUseMoney = firstBorrowUseMoney;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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

	public String getTotalStr() {
		if (total != null) {
			return MoneyUtil.fmtMoneyByDot(total);
		}
		return totalStr;
	}

	public void setTotalStr(String totalStr) {
		this.totalStr = totalStr;
	}

	public String getUseMoneyStr() {
		if (useMoney != null) {
			return MoneyUtil.fmtMoneyByDot(useMoney);
		}
		return useMoneyStr;
	}

	public void setUseMoneyStr(String useMoneyStr) {
		this.useMoneyStr = useMoneyStr;
	}

	public String getNoUseMoneyStr() {
		if (noUseMoney != null) {
			return MoneyUtil.fmtMoneyByDot(noUseMoney);
		}
		return noUseMoneyStr;
	}

	public void setNoUseMoneyStr(String noUseMoneyStr) {
		this.noUseMoneyStr = noUseMoneyStr;
	}

	public String getCollectionStr() {
		if (collection != null) {
			return MoneyUtil.fmtMoneyByDot(collection);
		}
		return collectionStr;
	}

	public void setCollectionStr(String collectionStr) {
		this.collectionStr = collectionStr;
	}

	public String getNetvalueStr() {
		if (netvalue != null) {
			return MoneyUtil.fmtMoneyByDot(netvalue);
		}
		return netvalueStr;
	}

	public void setNetvalueStr(String netvalueStr) {
		this.netvalueStr = netvalueStr;
	}

	public String getDrawMoneyStr() {
		if (drawMoney != null) {
			return MoneyUtil.fmtMoneyByDot(drawMoney);
		}
		return drawMoneyStr;
	}

	public void setDrawMoneyStr(String drawMoneyStr) {
		this.drawMoneyStr = drawMoneyStr;
	}

	public String getNoDrawMoneyStr() {
		if (noDrawMoney != null) {
			return MoneyUtil.fmtMoneyByDot(noDrawMoney);
		}
		return noDrawMoneyStr;
	}

	public void setNoDrawMoneyStr(String noDrawMoneyStr) {
		this.noDrawMoneyStr = noDrawMoneyStr;
	}

	public String getFirstBorrowUseMoneyStr() {
		if (firstBorrowUseMoney != null) {
			return MoneyUtil.fmtMoneyByDot(firstBorrowUseMoney);
		}
		return firstBorrowUseMoneyStr;
	}

	public void setFirstBorrowUseMoneyStr(String firstBorrowUseMoneyStr) {
		this.firstBorrowUseMoneyStr = firstBorrowUseMoneyStr;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
