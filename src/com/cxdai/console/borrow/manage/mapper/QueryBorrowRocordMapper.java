package com.cxdai.console.borrow.manage.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cxdai.console.borrow.approve.vo.BorrowVo;
import com.cxdai.console.borrow.manage.vo.BorrowCnd;
import com.cxdai.console.common.page.Page;

public interface QueryBorrowRocordMapper {
	
	public List<BorrowVo> queryBorrowRecordList(
			BorrowCnd repaymentOverBorrowCnd, Page page) throws Exception;
	public List<BorrowVo> queryBorrowRecordList(
			BorrowCnd repaymentOverBorrowCnd) throws Exception;

	public int queryBorrowRecordCount(BorrowCnd repaymentBorrowCnd);
	public BorrowVo queryBorrowTotal(
			BorrowCnd repaymentOverBorrowCnd) throws Exception;
	
}
