package com.cxdai.console.util.exception;

/**
 * <p>
 * Description:系统运行时业务异常<br />
 * </p>
 * 
 * @title AppException.java
 * @package com.util.exception
 * @author justin.xu
 * @version 0.1 2014年5月8日
 */
public class AppException extends Exception {

	private static final long serialVersionUID = -5914165266382469973L;
	protected Throwable myException;
	protected String errType;
	protected int errCode;
	protected String errMsg;
	protected String contextMsg;
	protected String cusMsg;
	protected String Msg;

	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param exception
	 */
	public AppException(Exception exception) {
		super(exception.getMessage());
		errType = "";
		errMsg = "";
		contextMsg = "";
		cusMsg = "";
		Msg = "";
		myException = exception;
		errMsg = exception.getMessage();
	}

	public AppException(String s) {
		super(s);
		errType = "";
		errMsg = "";
		contextMsg = "";
		cusMsg = "";
		Msg = "";
		errMsg = s;
	}

	public AppException(int i, String s, String s1) {
		super(s1);
		errType = "";
		errMsg = "";
		contextMsg = "";
		cusMsg = "";
		Msg = "";
		errCode = i;
		errType = s;
		errMsg = s1;
	}

	public AppException(int i, String s, String s1, Throwable throwable) {
		super(throwable.getMessage());
		errType = "";
		errMsg = "";
		contextMsg = "";
		cusMsg = "";
		Msg = "";
		errCode = i;
		errType = s;
		errMsg = s1;
		myException = throwable;
	}

	public AppException(int i, String s, String s1, String s2, String s3,
			Throwable throwable) {
		super(throwable.getMessage());
		errType = "";
		errMsg = "";
		contextMsg = "";
		cusMsg = "";
		Msg = "";
		errMsg = s1;
		errType = s;
		contextMsg = s2;
		cusMsg = s3;
		myException = throwable;
	}

	public AppException(int i, String s, String s1, String s2, String s3) {
		super(s1);
		errType = "";
		errMsg = "";
		contextMsg = "";
		cusMsg = "";
		Msg = "";
		errMsg = s1;
		errType = s;
		contextMsg = s2;
		cusMsg = s3;
	}

	public void printStackTrace() {
		if (myException != null)
			myException.printStackTrace();
	}

	public String msgFormat() {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append(errType);
		stringbuffer.append(errCode);
		stringbuffer.append(contextMsg);
		stringbuffer.append(errMsg);
		stringbuffer.append(cusMsg);
		return stringbuffer.toString();
	}

	public String getContextMsg() {
		return contextMsg;
	}

	public String getCusMsg() {
		return cusMsg;
	}

	public long getErrCode() {
		return (long) errCode;
	}

	public void setContextMsg(String s) {
		contextMsg = s;
	}

	public void setCusMsg(String s) {
		cusMsg = s;
	}

	public void setErrCode(int i) {
		errCode = i;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String s) {
		errMsg = s;
	}

	public String getErrType() {
		return errType;
	}

	public void setErrType(String s) {
		errType = s;
	}

}