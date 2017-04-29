/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title BorrowReCheckCnd.java
 * @package com.cxdai.console.borrow.check.vo 
 * @author tanghaitao
 * @version 0.1 2016年6月7日
 */
package com.cxdai.console.borrow.check.vo;

import java.io.Serializable;

import com.cxdai.console.security.ShiroUser;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title BorrowReCheckCnd.java
 * @package com.cxdai.console.borrow.check.vo 
 * @author tanghaitao
 * @version 0.1 2016年6月7日
 */

public class BorrowReCheckCnd implements Serializable{

	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = 6632831020313837177L;

	private Integer borrowId;
	
	private Integer userId;
	private Integer platform;

	public Integer getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}

	
	
	
}
