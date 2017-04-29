package com.cxdai.console.finance.virement.vo;

import com.cxdai.console.common.page.BaseCnd;

/***
 * 
 * <p>
 * Description:充值数据查询条件类<br />
 * </p>
 * 
 * @title RechargeAnalysisCnd.java
 * @package com.cxdai.console.finance.virement.vo
 * @author Administrator
 * @version 0.1 2016年4月17日
 */

public class WithdrawalAnalysisCnd extends BaseCnd {
	/**
	 * @fields serialVersionUID
	 */

	private static final long serialVersionUID = 6113999459657894292L;
	private String realname;// 用户姓名
	private String beginTime;// 查询时间
	private String endTime;

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
