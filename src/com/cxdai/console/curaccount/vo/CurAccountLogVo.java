package com.cxdai.console.curaccount.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.cxdai.console.curaccount.entity.CurAccountlog;
import com.cxdai.console.util.DateUtils;

public class CurAccountLogVo extends CurAccountlog implements Serializable {
	
	private static final long serialVersionUID = -6903226903535940857L;

	//用户名
	private String userName;
	
	// 类型：
	private String type_z;
	
	//操作日期（转入转出日期）
	private String addtimeStr;
	
	//转入累计
	private BigDecimal sumMoneyIn;

	
	//转出累计
	private BigDecimal sumMoneyOut;
	
	
	/**
	 * @return type_z : return the property type_z.
	 */
	public String getType_z() {
		return type_z;
	}

	/**
	 * @param type_z
	 *            : set the property type_z.
	 */
	public void setType_z(String type_z) {
		this.type_z = type_z;
	}

	/**
	 * @return userName : return the property userName.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName : set the property userName.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return addtimeStr : return the property addtimeStr.
	 */
	public String getAddtimeStr() {
		if(super.getAddtime()!=null)
		{  // 处理前台日期格式化 (2015-5-15 10:12:55)
			return DateUtils.format(super.getAddtime(), DateUtils.YMD_HMS);
		}
		return addtimeStr;
	}

	/**
	 * @param addtimeStr : set the property addtimeStr.
	 */
	public void setAddtimeStr(String addtimeStr) {	
		this.addtimeStr = addtimeStr;
	}

	/**
	 * @return sumMoneyIn : return the property sumMoneyIn.
	 */
	public BigDecimal getSumMoneyIn() {
		return sumMoneyIn;
	}

	/**
	 * @param sumMoneyIn : set the property sumMoneyIn.
	 */
	public void setSumMoneyIn(BigDecimal sumMoneyIn) {
		this.sumMoneyIn = sumMoneyIn;
	}

	/**
	 * @return sumMoneyOut : return the property sumMoneyOut.
	 */
	public BigDecimal getSumMoneyOut() {
		return sumMoneyOut;
	}

	/**
	 * @param sumMoneyOut : set the property sumMoneyOut.
	 */
	public void setSumMoneyOut(BigDecimal sumMoneyOut) {
		this.sumMoneyOut = sumMoneyOut;
	}

	
	
	
	
	
	
}