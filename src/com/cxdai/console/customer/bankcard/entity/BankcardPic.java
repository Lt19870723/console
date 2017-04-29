package com.cxdai.console.customer.bankcard.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 银行卡更换上传资料表
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title BankcardPic.java
 * @package com.cxdai.console.bank.vo
 * @author huangpin
 * @version 0.1 2015年3月27日
 */
public class BankcardPic implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id; // 自增ID
	private String picType;// 图片类型：身份证明，挂失证明
	private String picUrl;// 图片路径
	private Integer bcId;// 银行卡更换表ID
	private Integer userId;// 用户ID
	private Date addTime;// 添加时间
	private String addIp;// 添加IP

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPicType() {
		return picType;
	}

	public void setPicType(String picType) {
		this.picType = picType;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public Integer getBcId() {
		return bcId;
	}

	public void setBcId(Integer bcId) {
		this.bcId = bcId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getAddIp() {
		return addIp;
	}

	public void setAddIp(String addIp) {
		this.addIp = addIp;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
