
package com.cxdai.console.common.custody.xml;

/**   
 * <p>
 * Description:资金冻结响应报文<br />
 * </p>
 * @title FBRes.java
 * @package com.cxdai.common.custody.xml 
 * @author tanghaitao
 * @version 0.1 2016年5月23日
 */

public class FBRes extends BasePIReq{

	private String serialNo;//银行冻结流水号
	
	private String BlockStatus;//冻结状态
	
	private String instSettleDate;//清算日期
	
	private String extension;//消息扩展

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getBlockStatus() {
		return BlockStatus;
	}

	public void setBlockStatus(String blockStatus) {
		BlockStatus = blockStatus;
	}

	public String getInstSettleDate() {
		return instSettleDate;
	}

	public void setInstSettleDate(String instSettleDate) {
		this.instSettleDate = instSettleDate;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	
	
}
