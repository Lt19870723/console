/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title AQReq.java
 * @package com.cxdai.common.custody.xml 
 * @author tanghaitao
 * @version 0.1 2016年5月19日
 */
package com.cxdai.console.common.custody.xml;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**   
 * <p>
 * Description:余额查询报文<br />
 * </p>
 * @title AQReq.java
 * @package com.cxdai.common.custody.xml 
 * @author tanghaitao
 * @version 0.1 2016年5月19日
 */

public class AQReq extends BasePIReq{

	@XStreamAsAttribute  
    String id = "AQReq"; 
	
	private String bindSerialNo;//绑定协议号
	
	private String AccNo="";//存管账号
	
	private String extension="";//消息扩展

	public String getBindSerialNo() {
		return bindSerialNo;
	}

	public void setBindSerialNo(String bindSerialNo) {
		this.bindSerialNo = bindSerialNo;
	}

	public String getAccNo() {
		return AccNo;
	}

	public void setAccNo(String accNo) {
		AccNo = accNo;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	
}
