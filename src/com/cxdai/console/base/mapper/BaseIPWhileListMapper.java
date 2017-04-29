package com.cxdai.console.base.mapper;

import com.cxdai.console.base.entity.IPWhileList;


/**
 * 
 * <p>
 * Description:IP白名单数据访问类<br />
 * </p>
 * @title BaseIPWhileListMapper.java
 * @package com.cxdai.base.mapper 
 * @author yangshijin
 * @version 0.1 2014年7月2日
 */
public interface BaseIPWhileListMapper {
	/**
	 * 
	 * <p>
	 * Description:插入记录到帐号表,返回新增的主键ID<br />
	 * </p>
	 * @author yangshijin
	 * @version 0.1 2014年7月2日
	 * @param iPWhileList
	 * @return
	 * @throws Exception
	 * int
	 */
	public int insertEntity(IPWhileList iPWhileList) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据id查询<br />
	 * </p>
	 * @author yangshijin
	 * @version 0.1 2014年7月2日
	 * @param id
	 * @return
	 * @throws Exception
	 * IPWhileList
	 */
	public IPWhileList queryById(Integer id) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:更新对象<br />
	 * </p>
	 * @author yangshijin
	 * @version 0.1 2014年7月2日
	 * @param iPWhileList
	 * @return
	 * @throws Exception
	 * int
	 */
	public int updateEntity(IPWhileList iPWhileList) throws Exception;

}
