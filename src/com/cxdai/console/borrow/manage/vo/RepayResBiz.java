package com.cxdai.console.borrow.manage.vo;

import java.math.BigDecimal;

public class RepayResBiz {
	String id = "FRReq"; 
	public String version;
	public String instId;
	public String certId;
	public Integer succount;
	public BigDecimal sucsum;
	public Integer failcount;
	public BigDecimal failsum;
	public String extension;
	public Integer reapaymentId;
	
	public String getId() {
		return id;
	}
	public String getVersion() {
		return version;
	}
	public String getInstId() {
		return instId;
	}
	public String getCertId() {
		return certId;
	}
	public Integer getSuccount() {
		return succount;
	}
	public BigDecimal getSucsum() {
		return sucsum;
	}
	public Integer getFailcount() {
		return failcount;
	}
	public BigDecimal getFailsum() {
		return failsum;
	}
	public String getExtension() {
		return extension;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public void setInstId(String instId) {
		this.instId = instId;
	}
	public void setCertId(String certId) {
		this.certId = certId;
	}
	public void setSuccount(Integer succount) {
		this.succount = succount;
	}
	public void setSucsum(BigDecimal sucsum) {
		this.sucsum = sucsum;
	}
	public void setFailcount(Integer failcount) {
		this.failcount = failcount;
	}
	public void setFailsum(BigDecimal failsum) {
		this.failsum = failsum;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public Integer getReapaymentId() {
		return reapaymentId;
	}
	public void setReapaymentId(Integer reapaymentId) {
		this.reapaymentId = reapaymentId;
	}
	
	
}
