package com.cxdai.console.red.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.cxdai.console.common.Dictionary;
import com.cxdai.console.util.DateUtils;

public class RedAccount implements Serializable {

	private static final long serialVersionUID = -8105755811911663717L;
	private Integer id;
	private Integer userId;// 用户ID
	private Integer usebizId;// 目标业务ID，包含定期宝、直通车、手动投标ID
	private Integer usebizType;// 1-认购定期宝，2-认购直通车，3-认购标的
	private Integer redType;// 红包类型 1910生日红包;1920推荐成功红包；1930贵宾特权红包；1940推荐成功红包；1950元宝兑换红包；1960抽奖红包
	private BigDecimal money;// 红包金额
	private Date addTime;// 获取时间
	private Date openTime;// 开启时间   
	private Date endTime;// 到期时间     
	private Date freezeTime;// 冻结时间
	private Date useTime;// 使用时间
	private Integer status;// 状态：1未开启；2未使用；3已冻结；4已使用；5已过期
	private Date lastUpdateTime;// 最后更新时间
	private Integer flag;// 查看红包最新记录的标识 1表示最新记录的位置
	private Integer inviterId;// 贵宾推荐表ID
	private String remark;// 备注
	private String userName;// 用户名
	private String realName;// 真实姓名
	private String source;// 来源 导出用
	private String redstatus;// 状态 导出用
	private String addTimeString;// 获取时间  导出用
	private String openTimeString;// 到期时间  导出用
	private String endTimeString;// 开启时间 导出用
	private String useTimeString;// 使用时间 导出用
	private String mobileNum;//电话号码
	
	
	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
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

	public Integer getUsebizId() {
		return usebizId;
	}

	public void setUsebizId(Integer usebizId) {
		this.usebizId = usebizId;
	}

	public Integer getRedType() {
		return redType;
	}

	public void setRedType(Integer redType) {
		this.redType = redType;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getOpenTime() {
		return openTime;
	}
	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getInviterId() {
		return inviterId;
	}

	public void setInviterId(Integer inviterId) {
		this.inviterId = inviterId;
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

	public String getRealName() {
		if(null==this.realName){
			return "";
		}
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getUsebizType() {
		return usebizType;
	}

	public void setUsebizType(Integer usebizType) {
		this.usebizType = usebizType;
	}

	public String getSource() {
		if(this.redType!=null&&this.redType>0){
			return Dictionary.getValue(1900,this.redType.toString());// 红包类型;

		}
		return source;

	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getRedstatus() {
		String redstatus="";
		if(null!=getStatus()){
			if(null!=getStatus() && (getStatus().equals(1)||getStatus()==1)){
				redstatus="未开启";
				return redstatus;
			}else if(null!=getStatus() && (getStatus().equals(2)||getStatus()==2)){
				redstatus="未使用";
				return redstatus;
			}else if(null!=getStatus() && (getStatus().equals(3)||getStatus()==3)){
				redstatus="已冻结";
				return redstatus;
			}else if(null!=getStatus() && (getStatus().equals(4)||getStatus()==4)){
				redstatus="已使用";
				return redstatus;
			}else if(null!=getStatus() && (getStatus().equals(5)||getStatus()==5)){
				redstatus="已过期";
				return redstatus;
			}else{
				return null;
			   }
	  }else{
		return null;
	   }
	}

	public void setRedstatus(String redstatus) {
		this.redstatus = redstatus;
	}

	public String getAddTimeString() {
		String addTimeString="";
		try {
			if(null!=this.addTime){
				addTimeString=DateUtils.format(this.addTime, DateUtils.YMD_DASH);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return addTimeString;
	}
	public String getOpenTimeString() {
		String openTimeString="";
		try {
			if(null!=this.openTime){
				openTimeString=DateUtils.format(this.openTime, DateUtils.YMD_DASH);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return openTimeString;
	}

	public String getEndTimeString() {
		String endTimeString="";
		try {
			if(null!=this.endTime){
				endTimeString=DateUtils.format(this.endTime, DateUtils.YMD_DASH);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return endTimeString;
	}

	public void setAddTimeString(String addTimeString) {
		this.addTimeString = addTimeString;
	}

	public void setOpenTimeString(String openTimeString) {
		this.openTimeString = openTimeString;
	}

	public void setEndTimeString(String endTimeString) {
		this.endTimeString = endTimeString;
	}
	public String getUseTimeString() {
		String useTimeString="";
		try {
			if(null!=this.useTime){
				useTimeString=DateUtils.format(this.useTime, DateUtils.YMD_SLAHMS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return useTimeString;
	}
}
