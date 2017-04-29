package com.cxdai.console.stock.vo;

import java.util.Date;

import com.cxdai.console.common.page.BaseCnd;

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
public class ShareholderCnd extends BaseCnd{
    //用户名
    private String userName;
    //真实姓名
    private String userRealName;
    //身份证号
    private String idCard;
    //股权代码
    private String stockCode;
    //开始日期
	 private Date beginTime;
	 //结束日期
	 private Date endinTime;
    //状态
    private int status;
    
    
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndinTime() {
		return endinTime;
	}
	public void setEndinTime(Date endinTime) {
		this.endinTime = endinTime;
	}
	
   
}