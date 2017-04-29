package com.cxdai.console.borrow.approve.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cxdai.console.borrow.approve.vo.BorrowAuditHistoryCnd;
import com.cxdai.console.borrow.approve.vo.BorrowAuditHistoryVo;
import com.cxdai.console.common.page.Page;

 

/**
 * 
 * @author hujianpan
 * @desc 借款标审核日志DAO
 *
 */
@Repository
public interface BorrowAuditHistoryMapper {
	/**
	 * 
	 * <p>
	 * Description:查询借款标审核日志<br />
	 * </p>
	 * @author 胡建盼
	 * @version 0.1 2014年12月10日
	 * @param borrowAuditHistoryCnd
	 * @param page
	 * @return
	 * List<BorrowAuditHistoryVo>
	 */
	public List<BorrowAuditHistoryVo> queryBorrowAuditHistoryList(
			BorrowAuditHistoryCnd borrowAuditHistoryCnd, Page page);

	/**
	 * 
	 * <p>
	 * Description:统计符合条件的借款标数量<br />
	 * </p>
	 * @author 胡建盼
	 * @version 0.1 2014年12月10日
	 * @param borrowAuditHistoryCnd
	 * @return
	 * int
	 */
	public int countBorrowAuditHistory(BorrowAuditHistoryCnd borrowAuditHistoryCnd);

}
