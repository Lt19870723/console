package com.cxdai.console.base.mapper;

import com.cxdai.console.base.entity.BorrowApproved;

 

/**
 * <p>
 * Description:借款标审核数据访问类<br />
 * </p>
 * 
 * @title BaseBorrowApprovedMapper.java
 * @package com.cxdai.base.mapper
 * @author justin.xu
 * @version 0.1 2014年8月30日
 */
public interface BaseBorrowApprovedMapper {
	/**
	 * <p>
	 * Description:插入记录,返回新增的主键ID<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param borrowApproved
	 * @return
	 * @throws Exception
	 *             int
	 */
	public int insertEntity(BorrowApproved borrowApproved) throws Exception;

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
	 *             Account
	 */
	public BorrowApproved queryById(Integer id) throws Exception;

	/**
	 * <p>
	 * Description:更新对象<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param borrowApproved
	 * @return
	 * @throws Exception
	 *             int
	 */
	public int updateEntity(BorrowApproved borrowApproved) throws Exception;

	public Integer updateBorrowApprovedRecheck(BorrowApproved borrowApproved);
	
}
