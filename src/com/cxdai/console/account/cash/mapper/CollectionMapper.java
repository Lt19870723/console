package com.cxdai.console.account.cash.mapper;

import java.math.BigDecimal;

/**
 * 
 * <p>
 * Description:待还记录数据访问接口<br />
 * </p>
 * 
 * @title CollectionMapper.java
 * @package com.cxdai.console.borrow.mapper
 * @author yangshijin
 * @version 0.1 2014年10月27日
 */
public interface CollectionMapper {

	/**
	 * 
	 * <p>
	 * Description:查询某个用户ID下待收利息的总计<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年10月27日
	 * @param memberId
	 * @return
	 * @throws Exception BigDecimal
	 */
	public BigDecimal queryUnReceiveInterstTotalByMemberId(Integer memberId) throws Exception;
}
