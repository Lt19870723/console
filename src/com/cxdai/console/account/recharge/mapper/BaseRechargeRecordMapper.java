package com.cxdai.console.account.recharge.mapper;

import com.cxdai.console.account.recharge.entity.RechargeRecord;

/**
 * <p>
 * Description:充值记录数据访问类<br />
 * </p>
 * 
 * @title RechargeRecordMapper.java
 * @package com.cxdai.base.mapper
 * @author justin.xu
 * @version 0.1 2014年5月22日
 */
public interface BaseRechargeRecordMapper {
	/**
	 * <p>
	 * Description:插入记录,返回新增的主键ID<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param account
	 * @return
	 * @throws Exception int
	 */
	public int insertEntity(RechargeRecord rechargeRecord) throws Exception;

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
	public RechargeRecord queryById(Integer id) throws Exception;

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
	public int updateEntity(RechargeRecord rechargeRecord) throws Exception;

}
