package com.cxdai.console.sycee.vo;

import java.io.Serializable;

import com.cxdai.console.common.page.BaseCnd;

/**
 * <p>
 * Description:元宝商场条件查询<br />
 * </p>
 * @title SyceeGoods.java
 * @package com.cxdai.console.sycee.entity
 * @author yubin
 * @version 0.1 2015年10月22日
 */
public class SyceeGoodCnd extends BaseCnd implements Serializable {

	private static final long serialVersionUID = -1826435038154466525L;

	private String name;// 商品名称
 
	private Integer showFlag;// 商品上下架：1上架，2下架

	private String approveStatus;// 0未审核；1审核通过；2审核不通过

	private Integer firstClass;

	public Integer getFirstClass() {
		return firstClass;
	}

	public void setFirstClass(Integer firstClass) {
		this.firstClass = firstClass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getShowFlag() {
		return showFlag;
	}

	public void setShowFlag(Integer showFlag) {
		this.showFlag = showFlag;
	}

	public String getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}

}
