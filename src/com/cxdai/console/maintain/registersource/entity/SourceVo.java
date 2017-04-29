/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title NetValueBorrowVo.java
 * @package com.cxdai.console.opration.vo 
 * @author ZHUCHEN
 * @version 0.1 2015年1月9日
 */
package com.cxdai.console.maintain.registersource.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * source传递对象
 * <p>
 * Description:新增，修改，查看详情时使用
 * </p>
 * @title SourceVo.java
 * @package com.cxdai.console.source.vo
 * @author ZHUCHEN
 * @version 0.1 2015年1月17日
 */
public class SourceVo implements Serializable {

	private static final long serialVersionUID = -2015989558711119463L;
	private String sourceNo;
	private String sourceName;
	private Date startTime;
	private String startTimeView;
	private Date endTime;
	private String endTimeView;
	public String getStartTimeView() {
		return startTimeView;
	}

	public void setStartTimeView(String startTimeView) {
		this.startTimeView = startTimeView;
	}

	public String getEndTimeView() {
		return endTimeView;
	}

	public void setEndTimeView(String endTimeView) {
		this.endTimeView = endTimeView;
	}

	private String remark;
	private Integer createUserId;
	private Date createTime;
	private Integer updateUserId;
	private Date updateTime;

	public String getSourceNo() {
		return sourceNo;
	}

	public void setSourceNo(String sourceNo) {
		this.sourceNo = sourceNo;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Integer getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Integer updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

}
