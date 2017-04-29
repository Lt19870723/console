package com.cxdai.console.finance.virement.vo;

import com.cxdai.console.common.page.BaseCnd;

/***
 * 内部转账条件查询实体类
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class InterTransferCnd extends BaseCnd {
	private String operator;// 操作员
	private Integer status;// 状态
	private String operationCode;//编号
	private Integer adduser;// 添加人id
	
	

	public Integer getAdduser() {
		return adduser;
	}

	public void setAdduser(Integer adduser) {
		this.adduser = adduser;
	}

	public String getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
