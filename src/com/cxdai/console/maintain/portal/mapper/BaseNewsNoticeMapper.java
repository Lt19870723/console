package com.cxdai.console.maintain.portal.mapper;

import com.cxdai.console.maintain.portal.entity.NewsNotice;


/**
 * 
 * <p>
 * Description:新闻公告类数据访问类<br />
 * </p>
 * @title BaseNewsNoticeMapper.java
 * @package com.cxdai.base.mapper 
 * @author yangshijin
 * @version 0.1 2014年4月23日
 */
public interface BaseNewsNoticeMapper {
	/**
	 * 
	 * <p>
	 * Description:插入记录到新闻公告表,返回新增的主键ID<br />
	 * </p>
	 * @author yangshijin
	 * @version 0.1 2014年4月23日
	 * @param newsNotice
	 * @return
	 * @throws Exception
	 * int
	 */
	public int insertEntity(NewsNotice newsNotice) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据id查询新闻公告类<br />
	 * </p>
	 * @author yangshijin
	 * @version 0.1 2014年4月23日
	 * @param id
	 * @return
	 * @throws Exception
	 * Member
	 */
	public NewsNotice queryById(Integer id) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:更新新闻公告类<br />
	 * </p>
	 * @author yangshijin
	 * @version 0.1 2014年4月23日
	 * @param newsNotice
	 * @return
	 * @throws Exception
	 * int
	 */
	public int updateEntity(NewsNotice newsNotice) throws Exception;

}
