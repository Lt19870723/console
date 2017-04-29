
package com.cxdai.console.borrow.check.vo;

import java.io.Serializable;
import java.util.Date;

import com.cxdai.console.common.page.BaseCnd;
import com.cxdai.console.util.DateUtils;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title AccountErrorCnd.java
 * @package com.cxdai.console.borrow.check.vo 
 * @author tanghaitao
 * @version 0.1 2016年6月4日
 */

public class AccountErrorCnd extends BaseCnd implements Serializable{

	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = 6701265674297421902L;

	
	private String userName;
	
	private String status;
	
	private Date beginDate;
	
	private Date endDate;
	
	private String scene;
	
	

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = DateUtils.parse(beginDate, DateUtils.YMD_DASH);
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = DateUtils.parse(endDate, DateUtils.YMD_DASH);
	}

	public String getScene() {
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene;
	}

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
	
	
}
