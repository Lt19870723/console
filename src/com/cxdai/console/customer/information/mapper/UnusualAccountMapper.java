package com.cxdai.console.customer.information.mapper;

import java.util.List;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.information.vo.UnusualAccountCnd;
import com.cxdai.console.customer.information.vo.UnusualAccountVo;

public interface UnusualAccountMapper {

	/**
	 * <p>
	 * Description:查询账户资金异常记录<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月23日
	 * @param accountCnd
	 * @return
	 * @throws Exception List<AccountVo>
	 */
	public List<UnusualAccountVo> queryAccountUnusualForPage(UnusualAccountCnd unusualAccountCnd, Page page) throws Exception;

	/**
	 * <p>
	 * Description:查询账户资金异常记录数量<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月23日
	 * @param UnusualAccountCnd accountCnd
	 * @return
	 * @throws Exception Integer
	 */
	public Integer queryAccountUnusualForCounts(UnusualAccountCnd unusualAccountCnd) throws Exception;
}
