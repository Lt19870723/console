package com.cxdai.console.borrow.manage.mapper;

import com.cxdai.console.borrow.manage.vo.BRepaymentRecordVo;
import com.cxdai.console.borrow.manage.vo.BorrowCnd;
import com.cxdai.console.common.page.Page;

import java.math.BigDecimal;
import java.util.List;


/**
 * <p>
 * Description:存管待还记录访问类<br />
 * </p>
 * 
 * @title RepaymentBatchRecordMapper.java
 * @package com.cxdai.console.borrow.mapper
 * @author justin.xu
 * @version 0.1 2014年8月13日
 */
public interface RepaymentBatchRecordMapper {
	/**
	 *
	 * <p>
	 * Description:后台操作还款的借款标记录<br />
	 * </p>
	 *
	 * @author yangshijin
	 * @version 0.1 2014年9月12日
	 * @param page
	 * @return
	 * @throws Exception
	 *             List<BRepaymentRecordVo>
	 */
	List<BRepaymentRecordVo> selectRepayingBorrow(BorrowCnd borrowCnd, Page page) throws Exception;

	/**
	 *
	 * <p>
	 * Description:统计符合条件的还款中的借款标的【待还总额】<br />
	 * </p>
	 *
	 * @author 胡建盼
	 * @version 0.1 2014年12月26日
	 * @param borrowCnd
	 * @return Integer
	 */
	BigDecimal sumWaitRepayMoney(BorrowCnd borrowCnd);


	/**
	 *
	 * <p>
	 * Description:查询还款中的借款标数量<br />
	 * </p>
	 *
	 * @author yangshijin
	 * @version 0.1 2014年9月12日
	 * @return
	 * @throws Exception
	 *             Integer
	 */
	Integer selectRepayingBorrowCount(BorrowCnd borrowCnd) throws Exception;

}
