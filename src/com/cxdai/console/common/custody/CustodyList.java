package com.cxdai.console.common.custody;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class CustodyList {
	
	@XStreamImplicit(itemFieldName="Record")
	public List<Record> recordList ;

	/**
	 * @return recordList : return the property recordList.
	 */
	public List<Record> getRecordList() {
		return recordList;
	}

	/**
	 * @param recordList : set the property recordList.
	 */
	public void setRecordList(List<Record> recordList) {
		this.recordList = recordList;
	}


}
