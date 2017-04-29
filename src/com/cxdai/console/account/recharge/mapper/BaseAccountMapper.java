package com.cxdai.console.account.recharge.mapper;

import com.cxdai.console.account.recharge.entity.Account;

/**
 * <p>
 * Description:账户数据访问类<br />
 * </p>
 * 
 * @title AccountMapper.java
 * @package com.base.mapper
 * @author justin.xu
 * @version 0.1 2014年4月16日
 */
public interface BaseAccountMapper {
	/**
	 * <p>
	 * Description:插入记录到帐号表,返回新增的主键ID<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param account
	 * @return
	 * @throws Exception int
	 */
	public int insertEntity(Account account) throws Exception;

	/**
	 * <p>
	 * Description:根据id查询<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param id
	 * @return
	 * @throws Exception Account
	 */
	public Account queryById(Integer id) throws Exception;

	/**
	 * <p>
	 * Description:更新对象<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param account
	 * @return
	 * @throws Exception int
	 */
	public int updateEntity(Account account) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据ID查询（用于更新账户记录时使用）<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年7月2日
	 * @param id
	 * @return
	 * @throws Exception Account
	 */
	public Account queryByUserIdForUpdate(Integer userId) throws Exception;
	
	public int updateAccount(com.cxdai.console.statistics.account.entity.Account account) throws Exception;
}
