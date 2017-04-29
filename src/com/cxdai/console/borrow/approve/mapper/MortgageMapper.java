package com.cxdai.console.borrow.approve.mapper;

import com.cxdai.console.base.mapper.BaseMortgageMapper;
import com.cxdai.console.borrow.emerg.vo.MortgageVo;

public interface MortgageMapper extends BaseMortgageMapper {
	/**
	 * <p>
	 * Description:根据借款标id查询抵押信息<br />
	 * </p>
	 * 
	 * @author chenlu
	 * @version 0.1 2014年8月26日
	 * @param map
	 * @param p
	 * @return
	 * @throws Exception
	 *             Page
	 */
	public MortgageVo getMortgageByBorrowId(Integer borrowId) throws Exception;
}
