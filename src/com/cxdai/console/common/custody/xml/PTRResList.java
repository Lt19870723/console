
package com.cxdai.console.common.custody.xml;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title PTRResList.java
 * @package com.cxdai.common.custody.xml 
 * @author tanghaitao
 * @version 0.1 2016年5月25日
 */

public class PTRResList{
	
	private String P2PserialNo;//P2P平台流水号
	
	private String InvestmentStatus;//投资状态
	
	private String InvestmentSerialNo;//银行投资流水号
	
	private String instSettleDate;//机构清算日期

	public String getP2PserialNo() {
		return P2PserialNo;
	}

	public void setP2PserialNo(String p2PserialNo) {
		P2PserialNo = p2PserialNo;
	}

	public String getInvestmentStatus() {
		return InvestmentStatus;
	}

	public void setInvestmentStatus(String investmentStatus) {
		InvestmentStatus = investmentStatus;
	}

	public String getInvestmentSerialNo() {
		return InvestmentSerialNo;
	}

	public void setInvestmentSerialNo(String investmentSerialNo) {
		InvestmentSerialNo = investmentSerialNo;
	}

	public String getInstSettleDate() {
		return instSettleDate;
	}

	public void setInstSettleDate(String instSettleDate) {
		this.instSettleDate = instSettleDate;
	}
	
	
}
