package com.cxdai.console.base.mapper;

import com.cxdai.console.customer.information.entity.RealNameAppro;

/**
 * <p>
 * Description:实名认证数据访问类<br />
 * </p>
 * 
 * @title BaseRealNameApproMapper.java
 * @package com.cxdai.base.mapper
 * @author justin.xu
 * @version 0.1 2014年4月23日
 */
public interface BaseRealNameApproMapper {
	/**
	 * <p>
	 * Description:插入记录,返回新增的主键ID<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param realNameAppro
	 * @return
	 * @throws Exception
	 *             int
	 */
	public int insertEntity(RealNameAppro realNameAppro) throws Exception;

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
	 *             RealNameAppro
	 */
	public RealNameAppro queryById(Integer id) throws Exception;
	
	/**
	 * 
	 * <p>
	 * Description:根据user_id查询<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2014年8月18日
	 * @param id
	 * @return
	 * @throws Exception
	 * RealNameAppro
	 */
	public RealNameAppro queryByUserId(Integer id) throws Exception;

	/**
	 * <p>
	 * Description:更新对象<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param realNameAppro
	 * @return
	 * @throws Exception
	 *             int
	 */
	public int updateEntity(RealNameAppro realNameAppro) throws Exception;

}
