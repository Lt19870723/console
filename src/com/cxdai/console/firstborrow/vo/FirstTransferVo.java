package com.cxdai.console.firstborrow.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.cxdai.console.base.entity.FirstTransfer;
import com.cxdai.console.util.DateUtils;

/**
 * 
 * <p>
 * Description:直通车转让列表<br />
 * </p>
 * 
 * @title FirstTransferVo.java
 * @package com.cxdai.portal.first.vo
 * @author 朱泳霖
 * @version 0.1 2015年3月16日
 */
public class FirstTransferVo extends FirstTransfer implements Serializable {

	private static final long serialVersionUID = -191815169850848319L;

	/**
	 * 状态
	 */
	private String statustr;

	/**
	 * 用户名
	 */
	private String userName;
	
	/**
	 * 开通金额
	 */
	private BigDecimal tenderRealAccount;
	
	/**
	 * 添加时间
	 */
	private String addTimeStr;
	
	/**
	 * 转让成功时间
	 */
	private String successTimeStr;
	
	/**
	 * 转让撤销时间
	 */
	private String cancleTimeStr;

	public String getStatustr() {
		return statustr;
	}

	public void setStatustr(String statustr) {
		this.statustr = statustr;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public BigDecimal getTenderRealAccount() {
		return tenderRealAccount;
	}

	public void setTenderRealAccount(BigDecimal tenderRealAccount) {
		this.tenderRealAccount = tenderRealAccount;
	}
	
	public String getAddTimeStr() {
		if (super.getAddtime() != null) {
			return DateUtils.format(super.getAddtime(), DateUtils.YMD_HMS);
		}
		return addTimeStr;
	}

	public void setAddTimeStr(String addTimeStr) {
		this.addTimeStr = addTimeStr;
	}

	public String getSuccessTimeStr() {
		if (super.getSuccessTime() != null) {
			return DateUtils.format(super.getSuccessTime(), DateUtils.YMD_HMS);
		}
		return successTimeStr;
	}

	public void setSuccessTimeStr(String successTimeStr) {
		this.successTimeStr = successTimeStr;
	}

	public String getCancleTimeStr() {
		if (super.getLastUpdateTime() != null) {
			return DateUtils.format(super.getLastUpdateTime(), DateUtils.YMD_HMS);
		}
		return cancleTimeStr;
	}

	public void setCancleTimeStr(String cancleTimeStr) {
		this.cancleTimeStr = cancleTimeStr;
	}

}
