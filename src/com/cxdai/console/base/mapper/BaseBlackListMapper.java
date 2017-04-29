package com.cxdai.console.base.mapper;

import com.cxdai.console.base.entity.BlackList;

/**
 * 
 * <p>
 * Description:黑名单类数据访问类<br />
 * </p>
 * 
 * @title BaseBlackListMapper.java
 * @package com.cxdai.base.mapper
 * @author yangshijin
 * @version 0.1 2015年1月25日
 */
public interface BaseBlackListMapper {

	/**
	 * 
	 * <p>
	 * Description:新增对象<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2015年1月25日
	 * @param blackList
	 * @return
	 * @throws Exception int
	 */
	public int insertEntity(BlackList blackList) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据id查询对象<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2015年1月25日
	 * @param id
	 * @return
	 * @throws Exception BlackList
	 */
	public BlackList queryById(Integer id) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:更新对象<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2015年1月25日
	 * @param blackList
	 * @return
	 * @throws Exception int
	 */
	public int updateEntity(BlackList blackList) throws Exception;

}
