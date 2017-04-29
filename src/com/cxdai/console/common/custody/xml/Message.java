package com.cxdai.console.common.custody.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("Message") 
public class Message {
	
	@XStreamAsAttribute  
	String id; 
	
	public Object Mode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Object getMode() {
		return Mode;
	}

	public void setMode(Object mode) {
		Mode = mode;
	}
	
	
	

}
