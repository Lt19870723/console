/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title Record.java
 * @package com.cxdai.common.custody.xml 
 * @author tanghaitao
 * @version 0.1 2016年5月25日
 */
package com.cxdai.console.common.custody.xml;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title Record.java
 * @package com.cxdai.common.custody.xml 
 * @author tanghaitao
 * @version 0.1 2016年5月25日
 */

public class Record {

	private String P2PserialNo;//P2P平台流水号
	
	private String Realname;//投资人姓名
	
	private String certType;//投资人证件类型
	
	private String certNo;//投资人证件号码
	
	private String bindSerialNo;//绑定协议号
	
	private String PayNum;//资金冻结流水
	
	private Integer InvestmentAmount;//投资金额
	
	private Integer InterestCapital;//投资利息
	
	private String currency="156";//币种
	
	private Integer Type=0;//回款类型
	
	private String AccountBankNumber="";//回款账户账号
	
	private String AccountName="";//回款账户户名
	
	private String BankBranchNo="";//开户行支行联行号

	public String getP2PserialNo() {
		return P2PserialNo;
	}

	public void setP2PserialNo(String p2PserialNo) {
		P2PserialNo = p2PserialNo;
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

	public String getPayNum() {
		return PayNum;
	}

	public void setPayNum(String payNum) {
		PayNum = payNum;
	}

	public Integer getInvestmentAmount() {
		return InvestmentAmount;
	}

	public void setInvestmentAmount(Integer investmentAmount) {
		InvestmentAmount = investmentAmount;
	}

	public Integer getInterestCapital() {
		return InterestCapital;
	}

	public void setInterestCapital(Integer interestCapital) {
		InterestCapital = interestCapital;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Integer getType() {
		return Type;
	}

	public void setType(Integer type) {
		Type = type;
	}

	public String getAccountBankNumber() {
		return AccountBankNumber;
	}

	public void setAccountBankNumber(String accountBankNumber) {
		AccountBankNumber = accountBankNumber;
	}

	public String getAccountName() {
		return AccountName;
	}

	public void setAccountName(String accountName) {
		AccountName = accountName;
	}

	public String getBankBranchNo() {
		return BankBranchNo;
	}

	public void setBankBranchNo(String bankBranchNo) {
		BankBranchNo = bankBranchNo;
	}
	
	
	
	
	
}
