package com.cxdai.console.system.vo;

import java.util.Date;

import com.cxdai.console.common.page.BaseCnd;

public class SchedulerErrorLogCnd extends BaseCnd {
	private static final long serialVersionUID = -6724246491667123224L;

	private String jobName;
	private Date startTime;
	private Date endTime;
	
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
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
}
