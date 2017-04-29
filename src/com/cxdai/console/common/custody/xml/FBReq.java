
package com.cxdai.console.common.custody.xml;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**   
 * <p>
 * Description:资金冻结报文<br />
 * </p>
 * @title FBReq.java
 * @package com.cxdai.common.custody.xml 
 * @author tanghaitao
 * @version 0.1 2016年5月23日
 */

public class FBReq extends BasePIReq{

	@XStreamAsAttribute  
    private String id = "FBReq"; 
	
	private String ProjectId;//项目ID
	
	private String ProjectName;//项目名称
	
	private String Realname;//投资人姓名
	
	private String certType;//投资人证件类型
	
	private String certNo;//投资人证件号码
	
	private String bindSerialNo;//绑定协议号
	
	private String P2PserialNo;//平台投资流水号
	
	private Integer InvestmentAmount;//冻结金额
	
	private String currency;//冻结币种
	
	private String MobileCode;//短信验证码
	
	private String CommendCode="";//推荐码
	
	private String QuickFlag="";//快捷投资
	
	private String extension="";//消息扩展

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjectId() {
		return ProjectId;
	}

	public void setProjectId(String projectId) {
		ProjectId = projectId;
	}

	public String getProjectName() {
		return ProjectName;
	}

	public void setProjectName(String projectName) {
		ProjectName = projectName;
	}

	public String getRealname() {
		return Realname;
	}

	public void setRealname(String realname) {
		Realname = realname;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getBindSerialNo() {
		return bindSerialNo;
	}

	public void setBindSerialNo(String bindSerialNo) {
		this.bindSerialNo = bindSerialNo;
	}

	public String getP2PserialNo() {
		return P2PserialNo;
	}

	public void setP2PserialNo(String p2PserialNo) {
		P2PserialNo = p2PserialNo;
	}

	public Integer getInvestmentAmount() {
		return InvestmentAmount;
	}

	public void setInvestmentAmount(Integer investmentAmount) {
		InvestmentAmount = investmentAmount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getMobileCode() {
		return MobileCode;
	}

	public void setMobileCode(String mobileCode) {
		MobileCode = mobileCode;
	}

	public String getCommendCode() {
		return CommendCode;
	}

	public void setCommendCode(String commendCode) {
		CommendCode = commendCode;
	}

	public String getQuickFlag() {
		return QuickFlag;
	}

	public void setQuickFlag(String quickFlag) {
		QuickFlag = quickFlag;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	
	
	
	
}
