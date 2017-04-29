package com.cxdai.console.common.custody;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("Message") 
public class Message {
	
	@XStreamAsAttribute  
    String id = ""; 
	
	public CustodyBiz Mode;
	
	

	/**
	 * @return id : return the property id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id : set the property id.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return mode : return the property mode.
	 */
	public CustodyBiz getMode() {
		return Mode;
	}

	/**
	 * @param mode : set the property mode.
	 */
	public void setMode(CustodyBiz mode) {
		Mode = mode;
	}
}
