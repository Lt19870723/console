package com.cxdai.console.base.mapper;

import com.cxdai.console.base.entity.FirstBorrow;

/**
 * <p>
 * Description:直通车数据访问类<br />
 * </p>
 * 
 * @title BaseFirstBorrowMapper.java
 * @package com.cxdai.base.mapper
 * @author justin.xu
 * @version 0.1 2014年7月2日
 */
public interface BaseFirstBorrowMapper {
	/**
	 * <p>
	 * Description:插入记录,返回新增的主键ID<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param firstBorrow
	 * @return
	 * @throws Exception
	 *             int
	 */
	public int insertEntity(FirstBorrow firstBorrow) throws Exception;

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
	 *             FirstBorrow
	 */
	public FirstBorrow queryById(Integer id) throws Exception;

	/**
	 * <p>
	 * Description:更新对象<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param firstBorrow
	 * @return
	 * @throws Exception
	 *             int
	 */
	public Integer updateEntity(FirstBorrow firstBorrow) throws Exception;

}
