package com.cxdai.console.customer.information.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author WangQianJin
 * @title 实名认证VO
 * @date 2016年6月7日
 */
public class BankApproVo implements Serializable {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = -8268171996134450904L;
	
	private Integer id;
	/** 用户ID */
	private Integer userId;
	/** 銀行名稱 */
	private String bankName;
	/** 銀行帳戶號 */
	private String cardNum;
	/** 所屬分行 */
	private String branch;
	/** 真实姓名 */
	private String realName;
	/** 身份证号码 */
	private String idCardNo;
	/** 联行号 */
	private Long cnapsCode;
	private Date addtime;
	private Date frozenTime;
	/** 验证状态【0：未验证 1：验证通过】 */
	private Integer verifyStatus;
	/** 支付时的签约协议号 */
	private String noAgree;
	/** 银行四要素验证是否成功*/
	private Integer bankVerif;
   
	/** 状态【0：正常，-1：已删除，1：审核中，2：已冻结】 */
	private Integer status;
	//银行编码
	private String bankCode;
	//审核备注
	private String remark;
	//用户名
	private String username;	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getAddtime() {
		return addtime;
	}
	
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	
	public Date getFrozenTime() {
		return frozenTime;
	}
	
	public void setFrozenTime(Date frozenTime) {
		this.frozenTime = frozenTime;
	}

	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getBankVerif() {
		return bankVerif;
	}

	public void setBankVerif(Integer bankVerif) {
		this.bankVerif = bankVerif;
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

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public Long getCnapsCode() {
		return cnapsCode;
	}

	public void setCnapsCode(Long cnapsCode) {
		this.cnapsCode = cnapsCode;
	}

	public Integer getVerifyStatus() {
		return verifyStatus;
	}

	public void setVerifyStatus(Integer verifyStatus) {
		this.verifyStatus = verifyStatus;
	}

	public String getNoAgree() {
		return noAgree;
	}

	public void setNoAgree(String noAgree) {
		this.noAgree = noAgree;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

}
