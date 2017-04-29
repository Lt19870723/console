package com.cxdai.console.common.custody;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Finance")
public class Finance {
	
	public Message Message;

	/**
	 * @return message : return the property message.
	 */
	public Message getMessage() {
		return Message;
	}

	/**
	 * @param message : set the property message.
	 */
	public void setMessage(Message message) {
		Message = message;
	}


}
