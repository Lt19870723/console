package com.cxdai.console.borrow.emerg.vo;

import java.io.Serializable;

/**
 * <p>
 * Description:上传资料<br />
 * </p>
 * 
 * @title AccountUploadDocVo.java
 * @package com.cxdai.portal.account.vo
 * @author justin.xu
 * @version 0.1 2014年5月14日
 */
public class AccountUploadDocCnd implements Serializable {

	private static final long serialVersionUID = -7479326477862062111L;
	private Integer id;
	/** 上传人ID */
	private Integer userId;
	/** 借款标id */
	private Integer borrowId;
	/**
	 * 类型 (1:身份证 2:企业营业执照 3:组织结构代码 4:其他 5:房产登记簿 6:房产登记收据 7:户口本 8:房产证 9:汇款凭证
	 * 10:借条 11:收条 12:房地产登记证明)
	 */
	private Integer style;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}

	public Integer getStyle() {
		return style;
	}

	public void setStyle(Integer style) {
		this.style = style;
	}

}
