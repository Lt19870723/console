package com.cxdai.console.maintain.portal.entity;

import java.util.Date;

public class SlideshowOperateLog {
	private Integer id;

    private Integer sourceId;

	private int sourceType;

	private int operateType;

    private Integer operateBy;

    private Date operateTime;

    private String optionIp;

    private String optionMac;

    private String remark;

	public SlideshowOperateLog(Integer sourceId, int sourceType, int operateType, Integer operateBy, Date operateTime, String optionIp, String optionMac, String remark) {
		super();
		this.sourceId = sourceId;
		this.sourceType = sourceType;
		this.operateType = operateType;
		this.operateBy = operateBy;
		this.operateTime = operateTime;
		this.optionIp = optionIp;
		this.optionMac = optionMac;
		this.remark = remark;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	public int getSourceType() {
		return sourceType;
	}

	public void setSourceType(int sourceType) {
		this.sourceType = sourceType;
	}

	public int getOperateType() {
		return operateType;
	}

	public void setOperateType(int operateType) {
		this.operateType = operateType;
	}

	public Integer getOperateBy() {
		return operateBy;
	}

	public void setOperateBy(Integer operateBy) {
		this.operateBy = operateBy;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getOptionIp() {
		return optionIp;
	}

	public void setOptionIp(String optionIp) {
		this.optionIp = optionIp;
	}

	public String getOptionMac() {
		return optionMac;
	}

	public void setOptionMac(String optionMac) {
		this.optionMac = optionMac;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
    
    
}
