package com.cxdai.console.base.mapper;

import com.cxdai.console.base.entity.MobileAppro;

/**
 * <p>
 * Description:手机验证数据访问类<br />
 * </p>
 * 
 * @title BaseMobileApproMapper.java
 * @package com.cxdai.base.mapper
 * @author justin.xu
 * @version 0.1 2014年4月25日
 */
public interface BaseMobileApproMapper {
	/**
	 * <p>
	 * Description:插入记录到,返回新增的主键ID<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param mobileAppro
	 * @return
	 * @throws Exception
	 *             int
	 */
	public int insertEntity(MobileAppro mobileAppro) throws Exception;

	/**
	 * <p>
	 * Description:根据id查询<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param id
	 * @return
	 * @throws Exception
	 *             VIPAppro
	 */
	public MobileAppro queryById(Integer id) throws Exception;
	
	/**
	 * 根据userId查询
	 * <p>
	 * Description:<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2014年8月26日
	 * @param userId
	 * @return
	 * @throws Exception
	 * MobileAppro
	 */
	public MobileAppro queryByUserId(Integer userId) throws Exception;

	/**
	 * <p>
	 * Description:更新对象<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param mobileAppro
	 * @return
	 * @throws Exception
	 *             int
	 */
	public int updateEntity(MobileAppro mobileAppro) throws Exception;

}
