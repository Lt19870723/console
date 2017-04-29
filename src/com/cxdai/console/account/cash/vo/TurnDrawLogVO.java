package com.cxdai.console.account.cash.vo;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.cxdai.console.util.HtmlUtils;
import com.cxdai.console.util.MoneyUtil;

/**
 * @author 胡建盼
 * @date 2014-8-6 t_todraw_log 账户转可提历史记录表
 */
public class TurnDrawLogVO {
	private int Id;
	private String userId;
	/** 帳戶總額 */
	private BigDecimal total;
	/** 操作金額 */
	private BigDecimal money;
	/** 可用餘額 */
	private BigDecimal use_money;
	/** 凍結金額 */
	private BigDecimal no_use_money;
	/** 待收總額 */
	private BigDecimal collection;
	/*** 可提现金额 */
	private BigDecimal draw_money;
	/** 不可提现金额 */
	private BigDecimal no_draw_money;
	/** 用户名 */
	private String userName;
	/** 日誌備註 */
	private String remark;
	/** 操作時間 */
	private Date addtime;
	/** 插入IP */
	private String addip;
	/** 交易对象 */
	private int to_user;
	/** 交易对象名称 */
	private String to_username;
	/** 优先计划总可用金额 */
	private BigDecimal first_borrow_use_money;
	private BigDecimal credited;
	private BigDecimal fee;
	private Integer status;

	/** 操作IP */
	private String addIp;
	private String creditedStr;
	private String feeStr;
	private String statusStr;

	public BigDecimal getCredited() {
		return credited;
	}

	public String getUserName() {
		return userName;
	}

	public String getAddIp() {
		return addIp;
	}

	public void setAddIp(String addIp) {
		this.addIp = addIp;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setCredited(BigDecimal credited) {
		this.credited = credited;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreditedStr() {
		return MoneyUtil.fmtMoneyByDot(credited);

	}

	public String getFeeStr() {
		return MoneyUtil.fmtMoneyByDot(fee);
	}

	public String getStatusStr() {
		/** -1：审核失败 0：申请转可提 1：审核通过 -2：取消转可提 */
		if (-1 == status) {
			return "审核失败";
		} else if (0 == status) {
			return "申请转可提";
		} else if (1 == status) {
			return "审核通过";
		} else if (-2 == status) {
			return "取消转可提";
		}

		return "";
	}

	/** 导出Excel需要转换的字段 begin */

	private String addtimeFMT;
	private String totalStr;
	private String moneyStr;
	private String use_moneyStr;
	private String no_use_moneyStr;
	private String collectionStr;
	private String usernameStr;
	private String remarkStr;
	private String first_borrow_use_moneyStr;
	private String draw_moneyStr;
	private String no_draw_moneyStr;

	/** 导出Excel需要转换的字段 end */

	public TurnDrawLogVO() {
		super();
	}

	public BigDecimal getDraw_money() {
		return draw_money;
	}

	public void setDraw_money(BigDecimal draw_money) {
		this.draw_money = draw_money;
	}

	public BigDecimal getNo_draw_money() {
		return no_draw_money;
	}

	public void setNo_draw_money(BigDecimal no_draw_money) {
		this.no_draw_money = no_draw_money;
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

	public BigDecimal getUse_money() {
		return use_money;
	}

	public void setUse_money(BigDecimal useMoney) {
		use_money = useMoney;
	}

	public BigDecimal getNo_use_money() {
		return no_use_money;
	}

	public void setNo_use_money(BigDecimal noUseMoney) {
		no_use_money = noUseMoney;
	}

	public BigDecimal getCollection() {
		return collection;
	}

	public void setCollection(BigDecimal collection) {
		this.collection = collection;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAddip() {
		return addip;
	}

	public void setAddip(String addip) {
		this.addip = addip;
	}

	public int getTo_user() {
		return to_user;
	}

	public void setTo_user(int to_user) {
		this.to_user = to_user;
	}

	public String getTo_username() {
		return to_username;
	}

	public void setTo_username(String to_username) {
		this.to_username = to_username;
	}

	public String getTotalStr() {
		return MoneyUtil.fmtMoneyByDot(total);
	}

	public String getDraw_moneyStr() {
		return MoneyUtil.fmtMoneyByDot(draw_money);
	}

	public String getNo_draw_moneyStr() {
		return MoneyUtil.fmtMoneyByDot(no_draw_money);
	}

	public String getMoneyStr() {
		return MoneyUtil.fmtMoneyByDot(money);
	}

	public String getUse_moneyStr() {
		return MoneyUtil.fmtMoneyByDot(use_money);
	}

	public String getNo_use_moneyStr() {
		return MoneyUtil.fmtMoneyByDot(no_use_money);
	}

	public String getCollectionStr() {
		return MoneyUtil.fmtMoneyByDot(collection);
	}

	public String getUsernameStr() {
		if (null == to_username) {
			return "admin";
		}
		return to_username;
	}

	public String getRemarkStr() {
		return HtmlUtils.getText(remark);
	}

	public void setTotalStr(String totalStr) {
		this.totalStr = totalStr;
	}

	public void setMoneyStr(String moneyStr) {
		this.moneyStr = moneyStr;
	}

	public void setUse_moneyStr(String use_moneyStr) {
		this.use_moneyStr = use_moneyStr;
	}

	public void setNo_use_moneyStr(String no_use_moneyStr) {
		this.no_use_moneyStr = no_use_moneyStr;
	}

	public void setCollectionStr(String collectionStr) {
		this.collectionStr = collectionStr;
	}

	public void setUsernameStr(String usernameStr) {
		this.usernameStr = usernameStr;
	}

	public void setRemarkStr(String remarkStr) {
		this.remarkStr = remarkStr;
	}

	public BigDecimal getFirst_borrow_use_money() {
		return first_borrow_use_money;
	}

	public void setFirst_borrow_use_money(BigDecimal first_borrow_use_money) {
		this.first_borrow_use_money = first_borrow_use_money;
	}

	public String getFirst_borrow_use_moneyStr() {

		return MoneyUtil.fmtMoneyByDot(first_borrow_use_money);
	}

	public void setFirst_borrow_use_moneyStr(String first_borrow_use_moneyStr) {
		this.first_borrow_use_moneyStr = first_borrow_use_moneyStr;
	}

	public String getAddtimeFMT() {
		return new SimpleDateFormat("yyyy-MM-dd").format(addtime);
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
