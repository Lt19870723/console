package com.cxdai.console.borrow.autoInvestConfig.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;




/**
 * <p>
 * Description:自动投宝规则<br />
 * </p>
 * 
 * @title FixAutoInvestEntity.java
 * @package com.cxdai.console.borrow.autoInvestConfig.entity
 * @author liutao
 * @date  2015年11月20日
 */
public class FixAutoInvest  implements Serializable {

	private static final long serialVersionUID = -7120622884570013103L;
	private int id;//主键ID
	private int userId;//用户ID，rocky_member的id
	private String userName;//用户名
	private String status;//状态【0：未启用 1：启用 -1：删除】
	private String autoTenderType;//自动投宝方式【1：按金额投宝，2：按账户余额】
	private int tenderMoney;//按金额投宝-投宝金额，最低100且为100的整数倍
	private String fixLimit;//定期宝期限【1：1月宝，3：3月宝，6：6月宝】[1][3][6]
	private String isUseCur;//是否使用活期宝【0：不使用，1：使用】
	private String uptime;//排队时间,18位（毫秒13位+序号5位），设置唯一约束
	private String platForm;//平台来源【1：网页 2：微信 3：安卓端 4：IOS端】
	private String remark;//备注
	private Date addTime;//添加时间,now()
	private String addip;//添加IP
	private int  rownum;//排队号
	private BigDecimal apr;//年利率
	private  int limitMoney;//用户可投额度，按照用户的设置的规则计算
	private BigDecimal useMoney;// 自动投宝时用户的可用余额，rocky_account的use_money
	private BigDecimal curMoney;// 自动投宝时用户的活期宝总额，is_use_cur=1才记，t_cur_account的total
	private String fixLimitTemp;//页面显示用
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAutoTenderType() {
		return autoTenderType;
	}
	public void setAutoTenderType(String autoTenderType) {
		this.autoTenderType = autoTenderType;
	}
	public int getTenderMoney() {
		return tenderMoney;
	}
	public void setTenderMoney(int tenderMoney) {
		this.tenderMoney = tenderMoney;
	}
	public String getFixLimit() {
		return fixLimit;
	}
	public void setFixLimit(String fixLimit) {
		this.fixLimit = fixLimit;
	}
	public String getIsUseCur() {
		return isUseCur;
	}
	public void setIsUseCur(String isUseCur) {
		this.isUseCur = isUseCur;
	}
	public String getUptime() {
		return uptime;
	}
	public void setUptime(String uptime) {
		this.uptime = uptime;
	}
	public String getPlatForm() {
		return platForm;
	}
	public void setPlatForm(String platForm) {
		this.platForm = platForm;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public String getAddip() {
		return addip;
	}
	public void setAddip(String addip) {
		this.addip = addip;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getRownum() {
		return rownum;
	}
	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
	public BigDecimal getApr() {
		return apr;
	}
	public void setApr(BigDecimal apr) {
		this.apr = apr;
	}
	public int getLimitMoney() {
		int limitMoney=0;
		if(null!=this.autoTenderType&&this.autoTenderType.equals("1")&&this.tenderMoney>0){
			limitMoney=this.tenderMoney;
		}
		if(null!=this.autoTenderType&&this.autoTenderType.equals("2")){
			if(null!=this.isUseCur&&this.isUseCur.equals("1")){
				limitMoney=this.useMoney.add(this.curMoney).intValue();
				limitMoney=limitMoney-limitMoney%100;
			}else if(null!=this.isUseCur&&this.isUseCur.equals("0")){
				limitMoney=this.useMoney.intValue()-this.useMoney.intValue()%100;
			}
		}
		return limitMoney;
	}
	public void setLimitMoney(int limitMoney) {
		this.limitMoney = limitMoney;
	}
	public BigDecimal getUseMoney() {
		return useMoney;
	}
	public void setUseMoney(BigDecimal useMoney) {
		this.useMoney = useMoney;
	}
	public BigDecimal getCurMoney() {
		return curMoney;
	}
	public void setCurMoney(BigDecimal curMoney) {
		this.curMoney = curMoney;
	}
	public String getFixLimitTemp() {
		if(null!=this.fixLimit&&StringUtils.isNotEmpty(this.fixLimit)){
			return this.fixLimit.replaceAll("\\[", "").replaceAll("\\]", "月宝 ");
		}
		return fixLimitTemp;
	}
	public void setFixLimitTemp(String fixLimitTemp) {
		this.fixLimitTemp = fixLimitTemp;
	}
}