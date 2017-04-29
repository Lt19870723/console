package com.cxdai.console.fix.vo;

import com.cxdai.console.common.page.BaseCnd;

/**
 * 
 * <p>
 * Description:定期宝标转让查询条件<br />
 * </p>
 * 
 * @title FixBorrowCnd.java
 * @package com.cxdai.face.fix.vo
 * @author 朱泳霖
 * @version 0.1 2015年6月29日
 */
public class FixTenderTransferCnd extends BaseCnd {

	private static final long serialVersionUID = -747561170614279281L;

	/**
	 * 定期宝转让ID
	 */
	private Integer fixBorrowTransferId;

	/**
	 * 状态
	 */
	private Integer status;

	public Integer getFixBorrowTransferId() {
		return fixBorrowTransferId;
	}

	public void setFixBorrowTransferId(Integer fixBorrowTransferId) {
		this.fixBorrowTransferId = fixBorrowTransferId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
