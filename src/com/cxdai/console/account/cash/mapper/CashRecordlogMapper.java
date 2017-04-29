package com.cxdai.console.account.cash.mapper;

import com.cxdai.console.account.cash.entity.CashRecordlog;

/**
 * <p>
 * Description:提现操作日志数据访问类<br />
 * </p>
 * 
 * @title CashRecordlogMapper.java
 * @package com.cxdai.console.account.mapper
 * @author justin.xu
 * @version 0.1 2014年11月10日
 */
public interface CashRecordlogMapper {
	/**
	 * <p>
	 * Description:插入提现操作日志,返回新增的主键ID<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param cashRecordlog
	 * @return
	 * @throws Exception int
	 */
	public int insertCashRecordlog(CashRecordlog cashRecordlog) throws Exception;
}
