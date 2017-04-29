package com.cxdai.console.fix.vo;

import java.io.Serializable;
import java.util.Date;

import com.cxdai.console.common.page.BaseCnd;

/**
 * 定期宝账户查询类
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title FixAccountCnd.java
 * @package com.cxdai.console.fix.vo 
 * @author 陈建国
 * @version 0.1 2015年6月8日
 */
public class FixAccountCnd extends BaseCnd implements Serializable {
	private static final long serialVersionUID = 8387186801624482411L;
	/**
	 * 主键id
	 */
	private Integer id;
	/**
	 * 标题
	 */
	private String name;
	/**
	 * 发布开始时间
	 */
	private String beginTime;
	/**
	 * 发布结束时间
	 */
	private String endTime;
	/**
	 * 状态
	 */
	private String status;
	 
	/**
	 * 排序sql
	 */
	private String orderSql;
 
	/**
	 * 定期宝编号
	 */
	
	private String contractNo;
	/**
	 * 用户名字
	 */
	private String userName;

	
	/**
	 * 发布开始日期
	 */
	private Date beginDate;
	/**
	 * 发布结束日期
	 */
	private Date endDate;
	
	
	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
 

	public String getOrderSql() {
		return orderSql;
	}

	public void setOrderSql(String orderSql) {
		this.orderSql = orderSql;
	}

 

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
