package com.cxdai.console.base.mapper;

import com.cxdai.console.base.entity.FirstBorrowAppr;

/**
 * <p>
 * Description:优先投标计划审核数据访问类<br />
 * </p>
 * 
 * @title BaseFirstBorrowApprMapper.java
 * @package com.cxdai.base.mapper
 * @author justin.xu
 * @version 0.1 2014年7月3日
 */
public interface BaseFirstBorrowApprMapper {
	/**
	 * <p>
	 * Description:插入记录,返回新增的主键ID<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param firstBorrowAppr
	 * @return
	 * @throws Exception
	 *             int
	 */
	public Integer insertEntity(FirstBorrowAppr firstBorrowAppr)
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
	 *             FirstBorrowAppr
	 */
	public FirstBorrowAppr queryById(Integer id) throws Exception;

}
