
package com.cxdai.console.common.custody.xml;

import java.util.List;

/**   
 * <p>
 * Description:项目投资登记响应报文<br />
 * </p>
 * @title PTRRes.java
 * @package com.cxdai.common.custody.xml 
 * @author tanghaitao
 * @version 0.1 2016年5月25日
 */

public class PTRRes {

	private String version;//版本号
	
	private String instId;//机构标识
	
	private String certId;//数字证书标识
	
	private Integer succount;//投资成功笔数
	
	private Integer sucsum;//投资成功金额
	
	private Integer failcount;//投资失败笔数
	
	private Integer failsum;//投资失败金额
	
	
	private List<PTRResList> pTRResList;
	
	private String extension;//消息扩展
	
	
	
	
	
	

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

	public Integer getSuccount() {
		return succount;
	}

	public void setSuccount(Integer succount) {
		this.succount = succount;
	}

	public Integer getSucsum() {
		return sucsum;
	}

	public void setSucsum(Integer sucsum) {
		this.sucsum = sucsum;
	}

	public Integer getFailcount() {
		return failcount;
	}

	public void setFailcount(Integer failcount) {
		this.failcount = failcount;
	}

	public Integer getFailsum() {
		return failsum;
	}

	public void setFailsum(Integer failsum) {
		this.failsum = failsum;
	}

	
	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public List<PTRResList> getpTRResList() {
		return pTRResList;
	}

	public void setpTRResList(List<PTRResList> pTRResList) {
		this.pTRResList = pTRResList;
	}

	
	
	
}
