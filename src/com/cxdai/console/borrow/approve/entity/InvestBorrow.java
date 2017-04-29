/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title InvestBorrow.java
 * @package com.cxdai.portal.borrow.vo 
 * @author tanghaitao
 * @version 0.1 2016年5月25日
 */
package com.cxdai.console.borrow.approve.entity;

import java.math.BigDecimal;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title InvestBorrow.java
 * @package com.cxdai.portal.borrow.vo 
 * @author tanghaitao
 * @version 0.1 2016年5月25日
 */

public class InvestBorrow {

	private Integer investBorrowId;//投标记录ID
	
	private String realname;//投资人姓名
	
	private String certType;//投资人证件类型
	
	private String certNo;//投资人证件号码
	
	private String bindSerialNo;//绑定协议号
	
	private String payNum;//资金冻结流水
	
	private BigDecimal investmentAmount;//投资金额
	
	private String p2pSerialNo;//平台投资流水号
	
	private BigDecimal interestCapital;//投资利息
	
	

	public BigDecimal getInterestCapital() {
		return interestCapital;
	}

	public void setInterestCapital(BigDecimal interestCapital) {
		this.interestCapital = interestCapital;
	}

	public String getP2pSerialNo() {
		return p2pSerialNo;
	}

	public void setP2pSerialNo(String p2pSerialNo) {
		this.p2pSerialNo = p2pSerialNo;
	}

	public Integer getInvestBorrowId() {
		return investBorrowId;
	}

	public void setInvestBorrowId(Integer investBorrowId) {
		this.investBorrowId = investBorrowId;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
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
		return payNum;
	}

	public void setPayNum(String payNum) {
		this.payNum = payNum;
	}

	public BigDecimal getInvestmentAmount() {
		return investmentAmount;
	}

	public void setInvestmentAmount(BigDecimal investmentAmount) {
		this.investmentAmount = investmentAmount;
	}
	
	
	
	
	
	
	
	
}
