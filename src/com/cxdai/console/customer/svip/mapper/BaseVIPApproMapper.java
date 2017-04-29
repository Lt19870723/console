package com.cxdai.console.customer.svip.mapper;

import com.cxdai.console.customer.svip.entity.VIPAppro;


/**
 * <p>
 * Description:VIP验证数据访问类<br />
 * </p>
 * 
 * @title BaseVIPApproMapper.java
 * @package com.cxdai.base.mapper
 * @author justin.xu
 * @version 0.1 2014年4月25日
 */
public interface BaseVIPApproMapper {
	/**
	 * <p>
	 * Description:插入记录到,返回新增的主键ID<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param vipAppro
	 * @return
	 * @throws Exception int
	 */
	public int insertEntity(VIPAppro vipAppro) throws Exception;

	/**
	 * <p>
	 * Description:根据id查询<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param id
	 * @return
	 * @throws Exception VIPAppro
	 */
	public VIPAppro queryById(Integer id) throws Exception;

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
	public int updateEntity(VIPAppro vipAppro) throws Exception;

	/**
	 * <p>
	 * Description:根据userId查询<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param id
	 * @return
	 * @throws Exception VIPAppro
	 */
	public VIPAppro queryByUserId(Integer userId) throws Exception;
	
	public Integer findPassed(Integer userId);

}
