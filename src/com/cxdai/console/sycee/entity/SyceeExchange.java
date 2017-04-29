package com.cxdai.console.sycee.entity;

import java.io.Serializable;
import java.util.Date;

import com.cxdai.console.util.DateUtils;

public class SyceeExchange implements Serializable {

	private static final long serialVersionUID = -5890332628953747763L;
	private Integer id;
	private Integer syceeGoodsId;// 元宝商品ID
	private Integer userId;// 用户ID
	private Integer sycee;// 兑换所用元宝=price*num
	private Integer num;// 兑换数量
	private Integer price;// 单价(元宝)
	private Date exchangeTime;// 兑换时间
	private Integer dealStatus;// 0未处理；1已处理（线上商品及时发放均为已处理）；2暂缓处理
	private Integer dealUser;// 处理人ID
	private String dealUsername;// 处理人姓名
	private Date dealTime;// 处理时间
	private String dealRemark;
	private String remark;
	private String mobilenum;
	private String name;// 商品名称
	private String goodsRemark;// 商品备注
	private String username;
	private String firstClass;
	private String secondClass;
	private String realname;

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getFirstClass() {
		return firstClass;
	}

	public void setFirstClass(String firstClass) {
		this.firstClass = firstClass;
	}

	public String getExchangeTimeStr() {
		if (this.exchangeTime != null) {
			return DateUtils.format(this.exchangeTime, DateUtils.YMD_HMS);
		}
		return "未兑换";
	}

	public String getDealTimeStr() {
		if (this.dealTime != null) {
			return DateUtils.format(this.dealTime, DateUtils.YMD_HMS);
		}
		return "未处理";
	}

	public String getDealStatusStr() {
		if (dealStatus == 0) {
			return "未处理";
		}
		return "已处理";
	}

	public String getMobilenum() {
		return mobilenum;
	}

	public void setMobilenum(String mobilenum) {
		this.mobilenum = mobilenum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSyceeGoodsId() {
		return syceeGoodsId;
	}

	public void setSyceeGoodsId(Integer syceeGoodsId) {
		this.syceeGoodsId = syceeGoodsId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getSycee() {
		return sycee;
	}

	public void setSycee(Integer sycee) {
		this.sycee = sycee;
	}

	public Date getExchangeTime() {
		return exchangeTime;
	}

	public void setExchangeTime(Date exchangeTime) {
		this.exchangeTime = exchangeTime;
	}

	public Integer getDealStatus() {
		return dealStatus;
	}

	public void setDealStatus(Integer dealStatus) {
		this.dealStatus = dealStatus;
	}

	public Integer getDealUser() {
		return dealUser;
	}

	public void setDealUser(Integer dealUser) {
		this.dealUser = dealUser;
	}

	public String getDealUsername() {
		return dealUsername;
	}

	public void setDealUsername(String dealUsername) {
		this.dealUsername = dealUsername;
	}

	public Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

	public String getDealRemark() {
		if (dealRemark == null) {
			return "";
		}
		return dealRemark;
	}

	public void setDealRemark(String dealRemark) {
		this.dealRemark = dealRemark;
	}

	public String getRemark() {
		if (this.remark == null) {
			return "";
		}
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSecondClass() {
		return secondClass;
	}

	public void setSecondClass(String secondClass) {
		this.secondClass = secondClass;
	}

	public String getGoodsRemark() {
		return goodsRemark;
	}

	public void setGoodsRemark(String goodsRemark) {
		this.goodsRemark = goodsRemark;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

}
