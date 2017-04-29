package com.cxdai.console.borrow.approve.vo;

import java.io.Serializable;

import com.cxdai.console.base.entity.BorrowApproved;

 
/**
 * <p>
 * Description:借款标审核<br />
 * </p>
 * 
 * @title BorrowApprovedVo.java
 * @package com.cxdai.console.borrow.vo
 * @author justin.xu
 * @version 0.1 2014年8月30日
 */
public class BorrowApprovedVo extends BorrowApproved implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 是否通过 0：通过 1：不通过 */
	private Integer flag;
	/** 审核备注 */
	private String remark;

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
