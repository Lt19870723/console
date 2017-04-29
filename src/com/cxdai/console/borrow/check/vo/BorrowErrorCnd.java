/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title BorrowErrorCnd.java
 * @package com.cxdai.console.borrow.check.vo 
 * @author tanghaitao
 * @version 0.1 2016年6月3日
 */
package com.cxdai.console.borrow.check.vo;

import java.io.Serializable;

import com.cxdai.console.common.page.BaseCnd;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title BorrowErrorCnd.java
 * @package com.cxdai.console.borrow.check.vo 
 * @author tanghaitao
 * @version 0.1 2016年6月3日
 */

public class BorrowErrorCnd extends BaseCnd implements Serializable{

	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = -458492140635097519L;

	
	private String status;//项目状态  0流标  1满标
	
	private String disposeStatus;//处理结果 0:未处理，1已处理
	
	private String userName;
	
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDisposeStatus() {
		return disposeStatus;
	}

	public void setDisposeStatus(String disposeStatus) {
		this.disposeStatus = disposeStatus;
	}
	
	
	
	
}
