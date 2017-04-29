package com.cxdai.console.stock.entity;

import java.math.BigDecimal;
import java.util.Date;

/****
 * 股东花名册实体类
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ShareholderRoster.java
 * @package com.cxdai.console.stock.entity 
 * @author xinwugang
 * @version 0.1 2016-5-9
 */
public class ShareholderRoster {
	//主键id
    private Integer id;
    //用户id
    private Integer userId;
    //用户名
    private String userName;
    //真实姓名
    private String userRealName;
    //身份证号
    private String idCard;
    //股权代码
    private String stockCode;
    //股权名称
    private String stockName;
    //可操作股权数量
    private Integer stockTotal;
    //持股比例
    private BigDecimal shareholdingRatio;
    //状态
    private Integer status;
    //版本号
    private Integer version;
    //备注
    private String remark;
    //添加人id
    private Integer adduser;
    //操作人真实姓名
    private String optUserRealName;
    //添加时间
    private Date addtime;
    //添加ip
    private String addip;
    
    private String mobilenum;
    

    public String getMobilenum() {
		return mobilenum;
	}

	public void setMobilenum(String mobilenum) {
		this.mobilenum = mobilenum;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Integer getStockTotal() {
        return stockTotal;
    }

    public void setStockTotal(Integer stockTotal) {
        this.stockTotal = stockTotal;
    }

    public BigDecimal getShareholdingRatio() {
        return shareholdingRatio;
    }

    public void setShareholdingRatio(BigDecimal shareholdingRatio) {
        this.shareholdingRatio = shareholdingRatio;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getAdduser() {
        return adduser;
    }

    public void setAdduser(Integer adduser) {
        this.adduser = adduser;
    }

    public String getOptUserRealName() {
        return optUserRealName;
    }

    public void setOptUserRealName(String optUserRealName) {
        this.optUserRealName = optUserRealName;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getAddip() {
        return addip;
    }

    public void setAddip(String addip) {
        this.addip = addip;
    }
}