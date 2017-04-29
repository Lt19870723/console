package com.cxdai.console.fix.vo;

import java.util.Date;

import com.cxdai.console.fix.entity.FixTenderUser;
import com.cxdai.console.util.DateUtils;

/**
 * <p>
 * Description:定期宝投标记录类<br />
 * </p>
 * 
 * @title FixTenderUserVo.java
 * @package com.cxdai.portal.fix1.vo
 * @author caodefeng
 * @version 0.1 2015年5月14日
 */
public class FixTenderUserVo extends FixTenderUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8781717275965141077L;
	
	/**
	 * 定期宝编号
	 */
	private String fixContractNo;
	
	/**
	 * 借款标所属原定期宝编号
	 */
	private String parentContractNo;
	
	/**
	 * 锁定结束日期(还款日期)
	 */
	private Date  lockEndtime;
	
	/**
	 * 页面输出锁定结束日期(还款日期)
	 */
	private String lockEndtimeStr;
	
	/**
	 * 页面输出投标时间
	 */
	private String addtimeStr;
	

	public String getFixContractNo() {
		return fixContractNo;
	}

	public void setFixContractNo(String fixContractNo) {
		this.fixContractNo = fixContractNo;
	}

	public String getParentContractNo() {
		return parentContractNo;
	}

	public void setParentContractNo(String parentContractNo) {
		this.parentContractNo = parentContractNo;
	}

	public Date getLockEndtime() {
		return lockEndtime;
	}

	public void setLockEndtime(Date lockEndtime) {
		this.lockEndtime = lockEndtime;
	}

	public String getLockEndtimeStr() {
		if(null!=getLockEndtime()){
			lockEndtimeStr = DateUtils.format(getLockEndtime(), DateUtils.YMD_DASH);
		}
		return lockEndtimeStr;
	}

	public void setLockEndtimeStr(String lockEndtimeStr) {
		this.lockEndtimeStr = lockEndtimeStr;
	}

	public String getAddtimeStr() {
		if(null!=getAddtime()){
			addtimeStr = DateUtils.format(getAddtime(),DateUtils.YMD_HMS);
		}
		return addtimeStr;
	}

	public void setAddtimeStr(String addtimeStr) {
		this.addtimeStr = addtimeStr;
	}
	
	

}
