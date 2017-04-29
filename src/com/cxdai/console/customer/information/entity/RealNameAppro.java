package com.cxdai.console.customer.information.entity;
import java.io.Serializable;

/**
 * <p>
 * Description:实名认证<br />
 * </p>
 * 
 * @title RealNameAppro.java
 * @package com.cxdai.base.entity
 * @author justin.xu
 * @version 0.1 2014年4月23日
 */
public class RealNameAppro implements Serializable {
	private static final long serialVersionUID = -368068524132797849L;
	/** ID */
	private Integer id;
	/** 用户ID */
	private Integer userId;
	/** 用户真实姓名 */
	private String realName;
	/** 身份证号码 */
	private String idCardNo;
	/** 身份证扫描1（正面） */
	private String pic1;
	/** 身份证扫描2（反面） */
	private String pic2;
	/** 是否通过实名认证 【-1：审核不通过，0：等待审核，1：审核通过】 */
	private Integer isPassed;
	/** 审核用户 */
	private String approveUser;
	/** 审核时间 */
	private String approveTime;
	/** 申请时间 */
	private String appTime;
	/** 申请IP */
	private String appIp;
	/** 审核备注 */
	private String reason;
	/** 籍贯 */
	private String nativePlace;
	/** 民族 */
	private String nation;
	/** 证件类型 */
	private String cardType;
	/** 生日 */
	private String birthDay;
	/** 性别 */
	private String sex;
	/** 版本号 */
	private Integer version;
	/** 备注 */
	private String remark;
	/** 平台来源(1：网页 2、微信) */
	private Integer platform;

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

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getPic1() {
		return pic1;
	}

	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}

	public String getPic2() {
		return pic2;
	}

	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}

	public Integer getIsPassed() {
		return isPassed;
	}

	public void setIsPassed(Integer isPassed) {
		this.isPassed = isPassed;
	}

	public String getApproveUser() {
		return approveUser;
	}

	public void setApproveUser(String approveUser) {
		this.approveUser = approveUser;
	}

	public String getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(String approveTime) {
		this.approveTime = approveTime;
	}

	public String getAppTime() {
		return appTime;
	}

	public void setAppTime(String appTime) {
		this.appTime = appTime;
	}

	public String getAppIp() {
		return appIp;
	}

	public void setAppIp(String appIp) {
		this.appIp = appIp;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}
}

