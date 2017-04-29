package com.cxdai.console.stock.vo;

import java.util.Date;

import com.cxdai.console.common.page.BaseCnd;
/****
 * 
 * <p>
 * Description:审核表实体类扩展类<br />
 * </p>
 * @title ApplyInfo.java
 * @package com.cxdai.console.stock.entity 
 * @author xinwugang
 * @version 0.1 2016-5-9
 */
public class ApplyInfoCnd extends BaseCnd{
	 //开始日期
	 private Date beginTime;
	 //结束日期
	 private Date endinTime;
	 //状态
	 private int status;
	 //类型
	 private int type;
	 
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndinTime() {
		return endinTime;
	}
	public void setEndinTime(Date endinTime) {
		this.endinTime = endinTime;
	}

  
}