package com.cxdai.console.fix.vo;

import java.io.Serializable;
import java.util.Date;

import com.cxdai.console.common.Dictionary;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.ShiroUtils;

/**
 * <p>
 * Description:直通车Vo<br />
 * </p>
 * 
 * @title FirstBorrowVo.java
 * @package com.cxdai.console.first.vo
 * @author justin.xu
 * @version 0.1 2014年7月2日
 */
public class FixOperationLogVo implements Serializable {
	private static final long serialVersionUID = -5552272784546139026L;
	/**
	 * 主键id
	 */
	private Integer id;
	/**
	 * 操作人
	 */
	private Integer userId;
	/**
	 * 操作人姓名
	 */
	private String userName;
	/**
	 * 用户类型
	 */
	private Integer userType;
	/**
	 * 定期宝Id
	 */
	private Integer fixBorrowId;
	/**
	 * 定期宝最终记录id
	 */
	private Integer fixTenderRealId;
	/**
	 * 操作类型
	 */
	private Integer operType;
	
	/**
	 * 操作时间：
	 */
	private Date addTime;
	
	/**
	 *操作人IP
	 */
	private String addIp;
	
	/**
	 *备注
	 */
	private String remark;
	
	/**
	 *平台来源(1：网页 2、微信)
	 */
	private Integer platForm;
	
	private String addTimeStr;
	
	public String getAddTimeStr() {
		if (null != addTime) {
			return DateUtils.format(addTime, DateUtils.YMD_HMS);
		}
		return addTimeStr;
	}

	public String getOperTypeStr() {
		return Dictionary.getValue(1501, String.valueOf(operType));
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

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getFixBorrowId() {
		return fixBorrowId;
	}

	public void setFixBorrowId(Integer fixBorrowId) {
		this.fixBorrowId = fixBorrowId;
	}

	public Integer getFixTenderRealId() {
		return fixTenderRealId;
	}

	public void setFixTenderRealId(Integer fixTenderRealId) {
		this.fixTenderRealId = fixTenderRealId;
	}

	public Integer getOperType() {
		return operType;
	}

	public void setOperType(Integer operType) {
		this.operType = operType;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getPlatForm() {
		return platForm;
	}

	public void setPlatForm(Integer platForm) {
		this.platForm = platForm;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
 
}
