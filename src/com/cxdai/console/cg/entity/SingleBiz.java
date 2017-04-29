package com.cxdai.console.cg.entity;

import com.cxdai.console.common.custody.CustodyBiz;
import com.cxdai.console.common.custody.CustodyList;
import com.cxdai.console.util.DateUtils;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.util.Date;

public class SingleBiz extends CustodyBiz {
	
	@XStreamAsAttribute  
    String id = "TSQReq";
	
	public String version;
	public String instId;
	public String certId;
	public String date= DateUtils.format(new Date(), DateUtils.YMDH_M_S);;
	public String orderNo;
	public String type;
	public String extension;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	
}
