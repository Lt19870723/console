package com.cxdai.console.fix.mapper;

import com.cxdai.console.fix.entity.RockyAccount;


public interface RockyAccountMapper {

	/**
	 * <p>
	 * Description:根据userId查询账户表rocky_account并锁定<br />
	 * </p>
	 * 
	 * @author HuangJun
	 * @version 0.1 2015年6月25日
	 * @return
	 * @throws Exception
	 *             RockyAccount
	 */
	public RockyAccount queryRockyAccountByUserIdForUpdate(RockyAccount rockyAccount) throws Exception;

	/**
	 * <p>
	 * Description:根据userId更新账户表 rocky_account 的金额<br />
	 * </p>
	 * 
	 * @author HuangJun
	 * @version 0.1 2015年6月25日
	 * @param rockyAccount
	 * @return
	 * @throws Exception
	 *             int
	 */
	public int updateRockyAccountByUserId(RockyAccount rockyAccount) throws Exception;

}