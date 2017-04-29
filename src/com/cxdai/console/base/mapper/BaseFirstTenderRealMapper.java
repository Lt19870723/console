package com.cxdai.console.base.mapper;

import org.springframework.stereotype.Repository;

import com.cxdai.console.base.entity.FirstTenderReal;

/**
 * <p>
 * Description:优先投标计划最终认购记录数据访问类<br />
 * </p>
 * 
 * @title BaseFirstTenderRealMapper.java
 * @package com.cxdai.base.mapper
 * @author justin.xu
 * @version 0.1 2014年7月17日
 */
public interface BaseFirstTenderRealMapper {
	/**
	 * <p>
	 * Description:插入记录,返回新增的主键ID<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param firstTenderReal
	 * @return
	 * @throws Exception
	 *             int
	 */
	public Integer insertEntity(FirstTenderReal firstTenderReal)
			throws Exception;

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
	 *             FirstTenderReal
	 */
	public FirstTenderReal queryById(Integer id) throws Exception;

	/**
	 * <p>
	 * Description:更新对象<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param firstTenderReal
	 * @return
	 * @throws Exception
	 *             int
	 */
	public Integer updateEntity(FirstTenderReal firstTenderReal)
			throws Exception;

}
