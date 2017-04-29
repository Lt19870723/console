package com.cxdai.console.system.entity;

import java.io.Serializable;
import java.util.Date;

public class SchedulerErrorLog implements Serializable {
	private static final long serialVersionUID = 1965764052179547399L;

	private Integer id; // 主键
	private String message; // 错误信息
	private String exception; // 异常信息
	private Date logTime; // 记录时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public Date getLogTime() {
		return logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}
}
