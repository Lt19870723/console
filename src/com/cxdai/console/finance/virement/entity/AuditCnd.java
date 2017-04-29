/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title AuditCnd.java
 * @package com.cxdai.console.finance.virement.entity 
 * @author tanghaitao
 * @version 0.1 2016年3月31日
 */
package com.cxdai.console.finance.virement.entity;

import java.util.Date;

import com.cxdai.console.util.DateUtils;


/**   
 * <p>
 * Description:转账审核<br />
 * </p>
 * @title AuditCnd.java
 * @package com.cxdai.console.finance.virement.entity 
 * @author tanghaitao
 * @version 0.1 2016年3月31日
 */

public class AuditCnd {
	
	private Integer id;
	
	private Date businessTime;//审核时间
	
	private Integer status;//状态(1、草稿中；2、待审核，3、审核通过，4、已删除)
	
	private String remark;//审核意见
	
	private Integer result;//审核结果(0:通过，1：拒绝)
	
	private String operationCode;//编号
	
	private Integer userId;//更新人
	
	private String ip;
	
	private String attachmentName;//证件名
	
	private String attachmentUrl;//证件地址
	
	private Integer attachmentType;//证件形式
	
	
	
	
	
	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public String getAttachmentUrl() {
		return attachmentUrl;
	}

	public void setAttachmentUrl(String attachmentUrl) {
		this.attachmentUrl = attachmentUrl;
	}

	public Integer getAttachmentType() {
		return attachmentType;
	}

	public void setAttachmentType(Integer attachmentType) {
		this.attachmentType = attachmentType;
	}

	public void setBusinessTime(Date businessTime) {
		this.businessTime = businessTime;
	}

	public String getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getBusinessTime() {
		return businessTime;
	}

	public void setBusinessTime(String businessTime) {
		this.businessTime = DateUtils.parse(businessTime, DateUtils.YMD_DASH);
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}
	
	
	
	
	
	
}
