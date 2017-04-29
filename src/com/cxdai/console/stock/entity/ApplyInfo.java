package com.cxdai.console.stock.entity;

import java.math.BigDecimal;
import java.util.Date;
/****
 * 
 * <p>
 * Description:审核表实体类<br />
 * </p>
 * @title ApplyInfo.java
 * @package com.cxdai.console.stock.entity 
 * @author xinwugang
 * @version 0.1 2016-5-9
 */
public class ApplyInfo {
	//主键id
    private Integer id;
    //用户id
    private Integer userId;
    //用户名称
    private String userName;
    //用户真实姓名
    private String userRealName;
    //身份证号
    private String idCard;
    //手机号
    private String mobile;
    //性别
    private Integer sex;
    //状态（1、待审核；2、审核通过；3、审核不通过；-1、已作废）
    private Integer status;
    //类型（1、股权系统登录；2、退出股东；）
    private Integer type;
    //待收总额
    private BigDecimal collection;
    //持股总数量
    private Integer stockTotal;
    //是否同意协议（1、是；0、默认0）
    private Integer isProtocol;
    //备注
    private String remark;
    //添加人od
    private Integer adduser;
    //添加时间
    private Date addtime;
    //添加ip
    private String addip;
    //最后修改人id
    private Integer updateuser;
    //最后修改时间
    private Date updatetime;
    //最后修改人ip
    private String updateip;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getCollection() {
        return collection;
    }

    public void setCollection(BigDecimal collection) {
        this.collection = collection;
    }

    public Integer getIsProtocol() {
        return isProtocol;
    }

    public void setIsProtocol(Integer isProtocol) {
        this.isProtocol = isProtocol;
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

    public Integer getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(Integer updateuser) {
        this.updateuser = updateuser;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getUpdateip() {
        return updateip;
    }

    public void setUpdateip(String updateip) {
        this.updateip = updateip;
    }

	public Integer getStockTotal() {
		return stockTotal;
	}

	public void setStockTotal(Integer stockTotal) {
		this.stockTotal = stockTotal;
	}
    
}