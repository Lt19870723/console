package com.cxdai.console.account.reward.mapper;

import com.cxdai.console.account.reward.entity.NetvalueLog;

public interface NetvalueLogMapper {

	/**
	 * 
	 * <p>
	 * Description:插入记录,返回新增的主键ID<br />
	 * </p>
	 * @param netvalueLog
	 * @return
	 * @throws Exception int
	 */
	public int insertEntity(NetvalueLog netvalueLog) throws Exception;
}
