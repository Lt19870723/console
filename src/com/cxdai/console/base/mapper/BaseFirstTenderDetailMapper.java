package com.cxdai.console.base.mapper;

import com.cxdai.console.base.entity.FirstTenderDetail;

/**
 * <p>
 * Description:优先投标计划认购明细数据访问类<br />
 * </p>
 * 
 * @title FirstTenderDetailMapper.java
 * @package com.cxdai.base.mapper
 * @author justin.xu
 * @version 0.1 2014年7月11日
 */
public interface BaseFirstTenderDetailMapper {
	/**
	 * <p>
	 * Description:插入记录,返回新增的主键ID<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param firstTenderDetail
	 * @return
	 * @throws Exception
	 *             int
	 */
	public Integer insertEntity(FirstTenderDetail firstTenderDetail)
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
	 *             FirstTenderDetail
	 */
	public FirstTenderDetail queryById(Integer id) throws Exception;

	/**
	 * <p>
	 * Description:更新对象<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param firstTenderDetail
	 * @return
	 * @throws Exception
	 *             int
	 */
	public Integer updateEntity(FirstTenderDetail firstTenderDetail)
			throws Exception;

}
