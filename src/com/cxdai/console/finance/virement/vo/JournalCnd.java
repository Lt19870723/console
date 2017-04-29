package com.cxdai.console.finance.virement.vo;

import com.cxdai.console.common.page.BaseCnd;

@SuppressWarnings("serial")
public class JournalCnd extends BaseCnd {
	/****
	 * 1:按天查询
	 */
	public static final int SEARCHTYPE_DAY = 1;

	/****
	 * 2:按月查询
	 */
	public static final int SEARCHTYPE_MONTH = 2;
	/****
	 * 3:按年查询
	 */
	public static final int SEARCHTYPE_YEAR = 3;
	private Byte status;// 状态
	private String operationDate;// 操作日期
	private Integer adduser;// 添加人id
	private int searchType;// 时间查询类别
	private Byte type;
	private Byte thirdPlatform;// 第三方平台

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(String operationDate) {
		this.operationDate = operationDate;
	}

	public Integer getAdduser() {
		return adduser;
	}

	public void setAdduser(Integer adduser) {
		this.adduser = adduser;
	}

	public int getSearchType() {
		return searchType;
	}

	public void setSearchType(int searchType) {
		this.searchType = searchType;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public Byte getThirdPlatform() {
		return thirdPlatform;
	}

	public void setThirdPlatform(Byte thirdPlatform) {
		this.thirdPlatform = thirdPlatform;
	}

}
