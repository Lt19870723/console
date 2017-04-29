
package com.cxdai.console.common.custody.xml;


import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**   
 * <p>
 * Description:项目投资登记报文<br />
 * </p>
 * @title PTRReq.java
 * @package com.cxdai.common.custody.xml 
 * @author tanghaitao
 * @version 0.1 2016年5月25日
 */

public class PTRReq extends BasePIReq{

	@XStreamAsAttribute  
    String id = "PTRReq"; 
	
	private String ProjectId;//项目ID
	
	private String ProjectName;//项目名称
	
	private String ProjectStatus;//项目状态
	
	private String RepayName="";//还款账户户名
	
	private String RepayAcct="";//还款账户账号
	
	private Integer Count;//明细笔数
	
	private Integer Amout;//总金额
	
	private RecordList List;
	
	private String extension="";//消息扩展

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjectId() {
		return ProjectId;
	}

	public void setProjectId(String projectId) {
		ProjectId = projectId;
	}

	public String getProjectName() {
		return ProjectName;
	}

	public void setProjectName(String projectName) {
		ProjectName = projectName;
	}

	public String getProjectStatus() {
		return ProjectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		ProjectStatus = projectStatus;
	}

	public String getRepayName() {
		return RepayName;
	}

	public void setRepayName(String repayName) {
		RepayName = repayName;
	}

	public String getRepayAcct() {
		return RepayAcct;
	}

	public void setRepayAcct(String repayAcct) {
		RepayAcct = repayAcct;
	}


	public Integer getCount() {
		return Count;
	}

	public void setCount(Integer count) {
		Count = count;
	}

	public Integer getAmout() {
		return Amout;
	}

	public void setAmout(Integer amout) {
		Amout = amout;
	}

	

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public RecordList getList() {
		return List;
	}

	public void setList(RecordList list) {
		List = list;
	}
	
	
	
	
}
