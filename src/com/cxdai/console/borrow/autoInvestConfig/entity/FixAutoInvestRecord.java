package com.cxdai.console.borrow.autoInvestConfig.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 * Description:自动投宝记录<br />
 * </p>
 * 
 * @title FixAutoInvestEntity.java
 * @package com.cxdai.console.borrow.autoInvestConfig.entity
 * @author liutao
 * @date 2015年11月20日
 */
public class FixAutoInvestRecord implements Serializable {

	private static final long serialVersionUID = -7120622884570013103L;
	private int id;// 主键ID
	private int userId;// 用户ID，rocky_member的id
	private String userName;// 用户名
	private String status;// 状态【0：未启用 1：启用 -1：删除】
	private String autoTenderType;// 自动投宝方式【1：按金额投宝，2：按账户余额】
	private int tenderMoney;// 按金额投宝-投宝金额，最低100且为100的整数倍
	private String fixLimit;// 定期宝期限【1：1月宝，3：3月宝，6：6月宝】[1][3][6]
	private String isUseCur;// 是否使用活期宝【0：不使用，1：使用】
	private String uptime;// 排队时间,18位（毫秒13位+序号5位），设置唯一约束
	private String platForm;// 平台来源【1：网页 2：微信 3：安卓端 4：IOS端】
	private String remark;// 备注
	private Date addTime;// 添加时间,now()
	private String addip;// 添加IP
	private int rownum;// 排队号
	private int autoInvestId;// t_fix_auto_invest的id
	private int autoTenderMoney;// 自动投宝金额，实际投出的
	private int fixId;// 定期宝ID
	private String fixNo;// 定期宝编号
	private String fixType;// 定期宝期限【1：1月宝，3：3月宝，6：6月宝】
	private String recordType;// 记录类型【1：设置 2：修改 3：停用 4：启用 5：删除 6：投宝 7：满宝 8：重新排队】
	private BigDecimal useMoney;// 自动投宝时用户的可用余额，rocky_account的use_money
	private BigDecimal curMoney;// 自动投宝时用户的活期宝总额，is_use_cur=1才记，t_cur_account的total
	private String name;// 定期宝名称
	private BigDecimal apr;// 年利率
	private BigDecimal lowestAccount;// 最低投宝金额
	private int limitMoney;// 用户可投额度，按照用户的设置的规则计算
	private String preUptime;//record_type=6时记录，投宝时的uptime
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

	public int getRownum() {
		return rownum;
	}

	public void setRownum(int rownum) {
		this.rownum = rownum;
	}

	public int getAutoInvestId() {
		return autoInvestId;
	}

	public void setAutoInvestId(int autoInvestId) {
		this.autoInvestId = autoInvestId;
	}

	public int getAutoTenderMoney() {
		return autoTenderMoney;
	}

	public void setAutoTenderMoney(int autoTenderMoney) {
		this.autoTenderMoney = autoTenderMoney;
	}

	public int getFixId() {
		return fixId;
	}

	public void setFixId(int fixId) {
		this.fixId = fixId;
	}

	public String getFixNo() {
		return fixNo;
	}

	public void setFixNo(String fixNo) {
		this.fixNo = fixNo;
	}

	public String getFixType() {
		return fixType;
	}

	public void setFixType(String fixType) {
		this.fixType = fixType;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
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
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getApr() {
		return apr;
	}

	public void setApr(BigDecimal apr) {
		this.apr = apr;
	}
	public BigDecimal getLowestAccount() {
		return lowestAccount;
	}

	public void setLowestAccount(BigDecimal lowestAccount) {
		this.lowestAccount = lowestAccount;
	}

	public int getLimitMoney() {
		return limitMoney;
	}

	public void setLimitMoney(int limitMoney) {
		this.limitMoney = limitMoney;
	}

	public String getPreUptime() {
		return preUptime;
	}

	public void setPreUptime(String preUptime) {
		this.preUptime = preUptime;
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