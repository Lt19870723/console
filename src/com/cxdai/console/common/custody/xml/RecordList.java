/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title RecordList.java
 * @package com.cxdai.common.custody.xml 
 * @author tanghaitao
 * @version 0.1 2016年5月26日
 */
package com.cxdai.console.common.custody.xml;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title RecordList.java
 * @package com.cxdai.common.custody.xml 
 * @author tanghaitao
 * @version 0.1 2016年5月26日
 */

public class RecordList {
	@XStreamImplicit(itemFieldName="Record")
	public List<Record> recordList ;//投资明细

	public List<Record> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<Record> recordList) {
		this.recordList = recordList;
	}
	
	
	
}
