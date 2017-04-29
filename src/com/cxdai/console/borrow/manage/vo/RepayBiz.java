package com.cxdai.console.borrow.manage.vo;

import com.cxdai.console.common.custody.CustodyBiz;
import com.cxdai.console.common.custody.CustodyList;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

public class RepayBiz extends CustodyBiz {
	
	@XStreamAsAttribute  
    String id = "FRReq"; 
	
	public String version;
	public String instId;
	public String certId;
	public String date;
	public String ProjectId;
	public String ProjectName;
	public String Realname;
	public Integer Count;
	public String Complete;
	public Integer WitnessFee;
	public String Flag;

	public CustodyList List;
	
	public String extension;

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

	public String getDate() {
		return date;
	}

	public String getProjectId() {
		return ProjectId;
	}

	public String getProjectName() {
		return ProjectName;
	}

	public String getRealname() {
		return Realname;
	}

	public Integer getCount() {
		return Count;
	}

	public String getComplete() {
		return Complete;
	}

	public Integer getWitnessFee() {
		return WitnessFee;
	}

	public String getFlag() {
		return Flag;
	}

	public CustodyList getList() {
		return List;
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

	public void setDate(String date) {
		this.date = date;
	}

	public void setProjectId(String projectId) {
		ProjectId = projectId;
	}

	public void setProjectName(String projectName) {
		ProjectName = projectName;
	}

	public void setRealname(String realname) {
		Realname = realname;
	}

	public void setCount(Integer count) {
		Count = count;
	}

	public void setComplete(String complete) {
		Complete = complete;
	}

	public void setWitnessFee(Integer witnessFee) {
		WitnessFee = witnessFee;
	}

	public void setFlag(String flag) {
		Flag = flag;
	}

	public void setList(CustodyList list) {
		List = list;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	
}
