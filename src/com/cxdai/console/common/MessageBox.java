package com.cxdai.console.common;

import java.io.Serializable;

public class MessageBox implements Serializable {
	private static final long serialVersionUID = -2441908337182356434L;

	private String code;
	private String message;

	public MessageBox() {
	}

	public MessageBox(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static final MessageBox build(String code, String message) {
		return new MessageBox(code, message);
	}
}