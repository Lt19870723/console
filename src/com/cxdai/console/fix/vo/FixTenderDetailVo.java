package com.cxdai.console.fix.vo;

import java.math.BigDecimal;

import com.cxdai.console.fix.entity.FixTenderDetail;
import com.cxdai.console.util.DateUtils;

/**
 * <p>
 * Description:定期宝认购明细类<br />
 * </p>
 * 
 * @title FixTenderDetailVo.java
 * @package com.cxdai.portal.fix1.vo
 * @author caodefeng
 * @version 0.1 2015年5月14日
 */
public class FixTenderDetailVo extends FixTenderDetail {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7393386115454776951L;
	
	/**
	 * 用户认购总额
	 */
	private BigDecimal sumAccount;
	
	
	/**
	 * 用户总利息
	 */
	private BigDecimal sumInterest;
	
	/**
	 * 用户名称
	 */
	private String userName;
	
	/**
	 * 定期宝编号
	 */
	private String contractNo;
	
	/**
	 * 页面输出添加时间str
	 */
	private String addtimeStr;
	
	/**
	 * 页面输出状态str
	 */
	private String statusStr;
	
	/**
	 * 计划金额
	 */
	private BigDecimal planAccount;
	
	/**
	 * 认购金额中包含的可提现总额
	 */
	private BigDecimal sumDrawMoney;
	/**
	 * 认购方式【0：手动投宝，1：自动投宝】，设默认值为0
	 */
	private int tenderType;
	/**
	 * 自动投宝方式【1：按金额投宝，2：按账户余额】
	 */
	private String autoTenderType;
	/**
	 * 自动投宝记录表的ID，t_fix_auto_invest_record.id
	 */
	private int autoInvestRecordId;
	/**
	 * 排队号
	 */
	private int rownum;
	/**
	 * 认购金额中包含的受限总额
	 */
	private BigDecimal sumNoDrawMoney;

	public BigDecimal getSumDrawMoney() {
		return sumDrawMoney;
	}

	public void setSumDrawMoney(BigDecimal sumDrawMoney) {
		this.sumDrawMoney = sumDrawMoney;
	}

	public BigDecimal getSumNoDrawMoney() {
		return sumNoDrawMoney;
	}

	public void setSumNoDrawMoney(BigDecimal sumNoDrawMoney) {
		this.sumNoDrawMoney = sumNoDrawMoney;
	}

	public BigDecimal getSumAccount() {
		return sumAccount;
	}

	public void setSumAccount(BigDecimal sumAccount) {
		this.sumAccount = sumAccount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getAddtimeStr() {
		if(null!=getAddtime()){
			addtimeStr = DateUtils.format(getAddtime(), DateUtils.YMD_HMS);
		}
		return addtimeStr;
	}

	public void setAddtimeStr(String addtimeStr) {
		this.addtimeStr = addtimeStr;
	}

	public String getStatusStr() {
		if(null!=getStatus()){
			switch(getStatus()){
				case 0:
					statusStr =	"加入中";
					break;
				case 1:
					statusStr =	"收益中";
					break;
				case 2:
					statusStr =	"已撤销";
					break;	
				case 3:
					statusStr =	"已退出";
					break;
				
			}
		}
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public BigDecimal getPlanAccount() {
		return planAccount;
	}

	public void setPlanAccount(BigDecimal planAccount) {
		this.planAccount = planAccount;
	}

	public BigDecimal getSumInterest() {
		return sumInterest;
	}

	public void setSumInterest(BigDecimal sumInterest) {
		this.sumInterest = sumInterest;
	}

	public int getTenderType() {
		return tenderType;
	}

	public void setTenderType(int tenderType) {
		this.tenderType = tenderType;
	}

	public String getAutoTenderType() {
		return autoTenderType;
	}

	public void setAutoTenderType(String autoTenderType) {
		this.autoTenderType = autoTenderType;
	}

	public int getAutoInvestRecordId() {
		return autoInvestRecordId;
	}

	public void setAutoInvestRecordId(int autoInvestRecordId) {
		this.autoInvestRecordId = autoInvestRecordId;
	}

	public int getRownum() {
		return rownum;
	}

	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
}
