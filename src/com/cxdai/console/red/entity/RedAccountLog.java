package com.cxdai.console.red.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.cxdai.console.util.DateUtils;

public class RedAccountLog implements Serializable {

	private static final long serialVersionUID = 6270596230873388780L;
	private Integer id;
	private Integer redId;// 红包账户表ID
	private String redIdstr;// 红包账户表ID
	private BigDecimal money;// 红包金额
	private Integer userId;// 用户ID
	private Integer usebizId;// 目标业务ID，包含定期宝、直通车、手动投标ID
	private Integer bizId;// 定期宝认购明细表ID，直通车认购明细表ID，投标记录ID
	private Integer bizType;// 业务类型 0创建；1定期宝；2直通车；3手动投标；6撤宝；7流宝；8撤标；9流标
	private Integer status;// 状态：1未开启；2未使用；3已冻结；4已使用；5已过期
	private Integer optuser;// 操作人
	private Date opttime;// 操作时间
	private Date freezeTime;// 冻结时间
	private Date useTime;// 使用时间
	private String addip;// 操作IP
	private String remark;// 备注
	private String userName;// 用户名
	private Date addTime;// 获取时间
	private Integer redType;// 红包类型 1910生日红包;1920推荐成功红包；1930贵宾特权红包；1940推荐成功红包；1950元宝兑换红包；1960抽奖红包
	private String fixContractno;
	private String rockyContractno;
	private String usebizNo;// 目标业务编码，包含定期宝、直通车、手动投标
	private String source;// 红包来源
	private String memberSource;// 推广来源
	private BigDecimal account;// 投标金额
	private String bizTypeStr;// 导出用
	private String addTimeStr;//   导出用
	private String useTimeStr;// 导出用
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUsebizId() {
		return usebizId;
	}

	public void setUsebizId(Integer usebizId) {
		this.usebizId = usebizId;
	}

	public Integer getBizId() {
		return bizId;
	}

	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}

	public Integer getBizType() {
		return bizType;
	}

	public void setBizType(Integer bizType) {
		this.bizType = bizType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOptuser() {
		return optuser;
	}

	public void setOptuser(Integer optuser) {
		this.optuser = optuser;
	}

	public Date getOpttime() {
		return opttime;
	}

	public void setOpttime(Date opttime) {
		this.opttime = opttime;
	}

	public Date getFreezeTime() {
		return freezeTime;
	}

	public void setFreezeTime(Date freezeTime) {
		this.freezeTime = freezeTime;
	}

	public Date getUseTime() {
		return useTime;
	}

	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}

	public String getAddip() {
		return addip;
	}

	public void setAddip(String addip) {
		this.addip = addip;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Integer getRedType() {
		return redType;
	}

	public void setRedType(Integer redType) {
		this.redType = redType;
	}

	public String getFixContractno() {
		return fixContractno;
	}

	public void setFixContractno(String fixContractno) {
		this.fixContractno = fixContractno;
	}


	public String getRockyContractno() {
		return rockyContractno;
	}

	public void setRockyContractno(String rockyContractno) {
		this.rockyContractno = rockyContractno;
	}
	public String getUsebizNo() {
		String usebizNo="";
		if(null!=getBizId()){
			if(null!=getBizType() && (getBizType().equals(1)||getBizType()==1)){
				usebizNo=fixContractno;
				return usebizNo;
			}else if(null!=getBizType() &&  (getBizType().equals(3)||getBizType()==3)){
				usebizNo=rockyContractno;
				return usebizNo;
			}else{
				return null;
			}
		}
		return null;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getMemberSource() {
		return memberSource;
	}

	public void setMemberSource(String memberSource) {
		this.memberSource = memberSource;
	}

	public BigDecimal getAccount() {
		return account;
	}

	public void setAccount(BigDecimal account) {
		this.account = account;
	}

	public Integer getRedId() {
		return redId;
	}

	public void setRedId(Integer redId) {
		this.redId = redId;
	}

	public String getRedIdstr() {
		if(null!=redId){
			return redId.toString();
		}else{
			return redIdstr;
		}
	
	}

	public void setRedIdstr(String redIdstr) {
		this.redIdstr = redIdstr;
	}

	public String getBizTypeStr() {
		if(null!=bizType&&bizType==1){
			return "定期宝";
		}else{
			return "手动投标";
		}
	}

	public void setBizTypeStr(String bizTypeStr) {
		this.bizTypeStr = bizTypeStr;
	}

	public String getAddTimeStr() {
		String addTimeStr="";
		try {
			if(null!=this.addTime){
				addTimeStr=DateUtils.format(this.addTime, DateUtils.YMD_DASH);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return addTimeStr;
	}

	public void setAddTimeStr(String addTimeStr) {
		this.addTimeStr = addTimeStr;
	}

	public String getUseTimeStr() {
		String useTimeStr="";
		try {
			if(null!=this.useTime){
				useTimeStr=DateUtils.format(this.useTime, DateUtils.YMD_HMS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return useTimeStr;
	}

	public void setUseTimeStr(String useTimeStr) {
		this.useTimeStr = useTimeStr;
	}
	
}
