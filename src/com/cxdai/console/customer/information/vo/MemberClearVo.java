package com.cxdai.console.customer.information.vo;

import java.io.Serializable;

/**
 * <p>
 * Description:会员类Vo<br />
 * </p>
 * 
 * @title MemberVo.java
 * @package com.cxdai.member.vo
 * @author justin.xu
 * @version 0.1 2014年4月11日
 */
public class MemberClearVo implements Serializable {

	private static final long serialVersionUID = -5890980057423538506L;
	/**
	 * 主键ID
	 */
	private Integer ID;

	/**
	 * 用户ID
	 */
	private Integer MEMBERID;

	/**
	 * 添加人
	 */
	private String ADDPEOPLE;
	// 添加时间
	private String ADD_TIME;
	// 添加IP
	private String ADDIP;
	// 添加的mac地址
	private String MAC;
	// 备注
	private String REMARK;

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public Integer getMEMBERID() {
		return MEMBERID;
	}

	public void setMEMBERID(Integer mEMBERID) {
		MEMBERID = mEMBERID;
	}

	public String getADDPEOPLE() {
		return ADDPEOPLE;
	}

	public void setADDPEOPLE(String aDDPEOPLE) {
		ADDPEOPLE = aDDPEOPLE;
	}

	public String getADD_TIME() {
		return ADD_TIME;
	}

	public void setADD_TIME(String aDD_TIME) {
		ADD_TIME = aDD_TIME;
	}

	public String getADDIP() {
		return ADDIP;
	}

	public void setADDIP(String aDDIP) {
		ADDIP = aDDIP;
	}

	public String getMAC() {
		return MAC;
	}

	public void setMAC(String mAC) {
		MAC = mAC;
	}

	public String getREMARK() {
		return REMARK;
	}

	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}

}