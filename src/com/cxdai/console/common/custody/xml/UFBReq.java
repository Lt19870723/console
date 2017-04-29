/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title UFBReq.java
 * @package com.cxdai.common.custody.xml 
 * @author tanghaitao
 * @version 0.1 2016年5月26日
 */
package com.cxdai.console.common.custody.xml;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**   
 * <p>
 * Description:投资冻结解冻报文<br />
 * </p>
 * @title UFBReq.java
 * @package com.cxdai.common.custody.xml 
 * @author tanghaitao
 * @version 0.1 2016年5月26日
 */

public class UFBReq extends BasePIReq{

	@XStreamAsAttribute  
    String id = "UFBReq"; 
	
	private String ProjectId;//项目ID
	
	private String bindSerialNo;//绑定协议号
	
	private String oriSerialNo;//原投资冻结流水号
	
	private Integer InvestmentAmount;//冻结金额
	
	private String currency="156";//冻结币种
	
	private String extension;//消息扩展

	public String getProjectId() {
		return ProjectId;
	}

	public void setProjectId(String projectId) {
		ProjectId = projectId;
	}

	public String getBindSerialNo() {
		return bindSerialNo;
	}

	public void setBindSerialNo(String bindSerialNo) {
		this.bindSerialNo = bindSerialNo;
	}

	public String getOriSerialNo() {
		return oriSerialNo;
	}

	public void setOriSerialNo(String oriSerialNo) {
		this.oriSerialNo = oriSerialNo;
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

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	
	
	
}
