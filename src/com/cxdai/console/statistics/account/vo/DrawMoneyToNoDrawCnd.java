package com.cxdai.console.statistics.account.vo;

import java.io.Serializable;

/**
 * <p>
 * Description:可提金额大于净值额度时，将可提转入受限参数<br />
 * </p>
 * 
 * @title DrawMoneyToNoDrawCnd.java
 * @package com.cxdai.console.account.vo
 * @author justin.xu
 * @version 0.1 2014年10月25日
 */
public class DrawMoneyToNoDrawCnd implements Serializable {
	private static final long serialVersionUID = 8983061145517554053L;
	/** 用户id */
	private Integer userid;
	/** 借款标id */
	private Integer borrowid;
	/** 借款标名字 */
	private String borrow_name;
	/** 新增ip */
	private String addip;
	/**
	 * 净值额度表类型 t_netvalue_log 0：借款入账，1：网站奖励,2:还款扣除，3：还款入帐 4：垫付入帐 5：垫付后还款扣除
	 * 6：垫付还款后非VIP收取利息 7:提前还款扣除 8：提前还款入帐 9：收取罚息:10:直通车解锁
	 */
	private Integer netmoneytype;
	/** 资金明细类型 */
	private String accountlogType;
	/** 资金明细备注 */
	private String accountlogRemark;

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getBorrowid() {
		return borrowid;
	}

	public void setBorrowid(Integer borrowid) {
		this.borrowid = borrowid;
	}

	public String getBorrow_name() {
		return borrow_name;
	}

	public void setBorrow_name(String borrow_name) {
		this.borrow_name = borrow_name;
	}

	public String getAddip() {
		return addip;
	}

	public void setAddip(String addip) {
		this.addip = addip;
	}

	public Integer getNetmoneytype() {
		return netmoneytype;
	}

	public void setNetmoneytype(Integer netmoneytype) {
		this.netmoneytype = netmoneytype;
	}

	public String getAccountlogType() {
		return accountlogType;
	}

	public void setAccountlogType(String accountlogType) {
		this.accountlogType = accountlogType;
	}

	public String getAccountlogRemark() {
		return accountlogRemark;
	}

	public void setAccountlogRemark(String accountlogRemark) {
		this.accountlogRemark = accountlogRemark;
	}

}
