package com.cxdai.console.base.mapper;

import org.springframework.stereotype.Repository;

import com.cxdai.console.base.entity.FirstUnlockAppr;

/**
 * <p>
 * Description:直通车解锁审核数据访问类<br />
 * </p>
 * 
 * @title BaseFirstUnlockApprMapper.java
 * @package com.cxdai.base.mapper
 * @author justin.xu
 * @version 0.1 2014年7月28日
 */
public interface BaseFirstUnlockApprMapper {
	/**
	 * <p>
	 * Description:插入记录,返回新增的主键ID<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param ffirstUnlockAppr
	 * @return
	 * @throws Exception
	 *             int
	 */
	public Integer insertEntity(FirstUnlockAppr firstUnlockAppr)
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
	 *             FirstUnlockAppr
	 */
	public FirstUnlockAppr queryById(Integer id) throws Exception;

}
