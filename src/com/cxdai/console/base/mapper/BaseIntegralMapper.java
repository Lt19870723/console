package com.cxdai.console.base.mapper;

import com.cxdai.console.customer.information.entity.Integral;

/**
 * 
 * <p>
 * Description:积分等级数据访问类<br />
 * </p>
 * 
 * @title IntegralMapper.java
 * @package com.base.mapper
 * @author justin.xu
 * @version 0.1 2014年4月16日
 */
public interface BaseIntegralMapper {
	/**
	 * <p>
	 * Description:插入记录,返回新增的主键ID<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param integral
	 * @return
	 * @throws Exception
	 *             int
	 */
	public int insertEntity(Integral integral) throws Exception;

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
	 *             Integral
	 */
	public Integral queryById(Integer id) throws Exception;

	/**
	 * <p>
	 * Description:更新对象<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param integral
	 * @return
	 * @throws Exception
	 *             int
	 */
	public int updateEntity(Integral integral) throws Exception;

}
