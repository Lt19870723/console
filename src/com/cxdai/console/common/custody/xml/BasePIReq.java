
package com.cxdai.console.common.custody.xml;

import com.cxdai.console.util.PropertiesUtil;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title BasePIReq.java
 * @package com.cxdai.console.common.custody.xml 
 * @author tanghaitao
 * @version 0.1 2016年5月17日
 */

public class BasePIReq {
	
	private String version=PropertiesUtil.getValue("custody_version");//目前版本号
	
	private String instId=PropertiesUtil.getValue("custody_instId");//P2P平台在银行的唯一标识
	
	private String certId=PropertiesUtil.getValue("custody_certId");//对报文进行签名的数字证书标识
	
	private String date="";//交易日期和时间YYYYMMDD HH:MM:SS
	
	

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getInstId() {
		return instId;
	}

	public void setInstId(String instId) {
		this.instId = instId;
	}

	public String getCertId() {
		return certId;
	}

	public void setCertId(String certId) {
		this.certId = certId;
	}
	
	
}
