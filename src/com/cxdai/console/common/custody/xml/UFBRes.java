/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title UFBRes.java
 * @package com.cxdai.common.custody.xml 
 * @author tanghaitao
 * @version 0.1 2016年6月7日
 */
package com.cxdai.console.common.custody.xml;

import com.cxdai.console.common.custody.BaseReq;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title UFBRes.java
 * @package com.cxdai.common.custody.xml 
 * @author tanghaitao
 * @version 0.1 2016年6月7日
 */

public class UFBRes extends BaseReq{

	private String serialNo;//银行解冻流水号
	
	private String unfreezeStatus;//解冻状态20：成功30：失败
	
	private String extension;//消息扩展

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getUnfreezeStatus() {
		return unfreezeStatus;
	}

	public void setUnfreezeStatus(String unfreezeStatus) {
		this.unfreezeStatus = unfreezeStatus;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	
	
	
	
}
