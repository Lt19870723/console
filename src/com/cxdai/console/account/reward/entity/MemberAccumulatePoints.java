package com.cxdai.console.account.reward.entity;

import java.io.Serializable;
import java.util.Date;

import com.cxdai.console.common.Dictionary;

/**
 * <p>
 * Description:用户积分记录<br />
 * </p>
 * @title MemberAccumulatePoints.java
 * @package com.cxdai.base.entity
 * @author justin.xu
 * @version 0.1 2014年4月17日
 */
public class MemberAccumulatePoints implements Serializable {

	private static final long serialVersionUID = -4669558720498967247L;

	private Integer id;
	/** 会员Id */
	private Integer memberId;
	/** 积分 */
	private Integer accumulatePoints;
	/** 可用于兑换工资的积分 */
	private Integer gainAccumulatePoints;
	/** 获取的日期 */
	private Date gainDate;
	/** 积分类型(0登陆 1发帖 2 回复 3领工资 4精华帖 5收藏 6顶帖 7邀请 8加好友 9考察报告10首充1000积分奖励 11网站奖励) */
	private Integer type;
	/** 积分倍率 */
	private Integer pointSmagnification;
	/** 备注 */
	private String remark;
	/** 目标ID */
	private Integer targetId;
	/** 总荣誉 */
	private Integer honor;
	/** 总元宝 */
	private Integer sycee;
	/** 积分类型名称 */
	private String typeName;
	/** 明细 */
	private String detail;
	/** 用户名 */
	private String username;
	/** 真实姓名 */
	private String realname;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getAccumulatePoints() {
		return accumulatePoints;
	}

	public void setAccumulatePoints(Integer accumulatePoints) {
		this.accumulatePoints = accumulatePoints;
	}

	public Integer getGainAccumulatePoints() {
		return gainAccumulatePoints;
	}

	public void setGainAccumulatePoints(Integer gainAccumulatePoints) {
		this.gainAccumulatePoints = gainAccumulatePoints;
	}

	public Date getGainDate() {
		return gainDate;
	}

	public void setGainDate(Date gainDate) {
		this.gainDate = gainDate;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getPointSmagnification() {
		return pointSmagnification;
	}

	public void setPointSmagnification(Integer pointSmagnification) {
		this.pointSmagnification = pointSmagnification;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getTargetId() {
		return targetId;
	}

	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}

	public Integer getHonor() {
		return honor;
	}

	public void setHonor(Integer honor) {
		this.honor = honor;
	}

	public Integer getSycee() {
		return sycee;
	}

	public void setSycee(Integer sycee) {
		this.sycee = sycee;
	}

	public String getTypeName() {
		if (getType() != null)
			return Dictionary.getValue(1001, getType() + "");
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

}
