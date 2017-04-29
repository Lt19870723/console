package com.cxdai.console.customer.bankcard.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;

import com.cxdai.console.security.ShiroUser;

/**
 * <p>
 * Description:银行卡信息日志表<br />
 * </p>
 * 
 * @title BankinfoLog.java
 * @package com.cxdai.base.entity
 * @author chenpeng
 * @version 0.1 2014年11月19日
 */
public class BankinfoLog implements Serializable {
	private static final long serialVersionUID = -7600234119551306240L;

	private Integer id;
	/** 用户ID */
	private Integer userId;
	/** 用户名称 */
	private String userName;
	/** 真实姓名 */
	private String realName;
	/** 联行号 */
	private String cnapsCode;
	/** 銀行帳戶號 */
	private String cardNum;
	/** 身份证号码 */
	private String idCardNo;
	/** 开户行名称 */
	private String bankName;
	/** 操作人 */
	private String jobNum;
	/** 操作类型 */
	private Integer type;
	/** 状态 */
	private Integer status;
	/** 添加人 */
	private Integer addBy;
	/** 添加时间 */
	private Date addTime;
	/** 备注 */
	private String remark;
	/** 四要素验证状态*/
	private String bankVerify;
	/** 银行四要素验证是否成功*/
	private Integer bankVerif;	
	public Integer getBankVerif() {
		return bankVerif;
	}
	public void setBankVerif(Integer bankVerif) {
		this.bankVerif = bankVerif;
	}
	public String getBankVerify() {
		if(StringUtils.isNotEmpty(bankVerify)&&bankVerify.equals("1")){
			return "通过";
		}else{
			return "不通过";
		}
	}
	public void setBankVerify(String bankVerify) {
		this.bankVerify = bankVerify;
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

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAddBy() {
		return addBy;
	}

	public void setAddBy(Integer addBy) {
		this.addBy = addBy;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getCnapsCode() {
		return cnapsCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public void setCnapsCode(String cnapsCode) {
		this.cnapsCode = cnapsCode;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getJobNum() {
		return jobNum;
	}

	public void setJobNum(String jobNum) {
		this.jobNum = jobNum;
	}

	/**
	 * 
	 * <p>
	 * Description:构造方法<br />
	 * </p>
	 * 
	 * @author 胡建盼
	 * @version 0.1 2014年12月10日
	 * @param userId
	 * @param bankinfoLogAdd
	 * @param remark
	 * @return BankinfoLog
	 */
	public static BankinfoLog builder(Integer userId, int bankinfoLogAdd, String remark) {
		ShiroUser user = (ShiroUser) (SecurityUtils.getSubject()).getPrincipal();
		BankinfoLog bankinfoLog = new BankinfoLog();
		bankinfoLog.setUserId(userId);
		bankinfoLog.setType(bankinfoLogAdd);
		bankinfoLog.setAddBy(user.getUserId());
		bankinfoLog.setRemark(remark);
		return bankinfoLog;
	}

}
