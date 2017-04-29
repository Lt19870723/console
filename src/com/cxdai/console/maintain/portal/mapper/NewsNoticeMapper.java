package com.cxdai.console.maintain.portal.mapper;

import java.util.List;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.maintain.portal.entity.NewsNoticeCnd;
import com.cxdai.console.maintain.portal.entity.NewsNoticeVo;

/**
 * 
 * <p>
 * Description:新闻公告类数据访问类<br />
 * </p>
 * 
 * @title NewsNoticeMapper.java
 * @package com.cxdai.portal.news_notice.mapper
 * @author yangshijin
 * @version 0.1 2014年4月23日
 */
public interface NewsNoticeMapper {
	/**
	 * 
	 * <p>
	 * Description:根据条件查询新闻公告记录集合<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年4月23日
	 * @param newsNoticeCnd
	 * @return
	 * @throws Exception
	 *             List<NewsNoticeVo>
	 */
	public List<NewsNoticeVo> queryNewsNoticeList(NewsNoticeCnd newsNoticeCnd)
			throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据条件查询新闻公告记录集合数量<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年4月23日
	 * @param newsNoticeCnd
	 * @return
	 * @throws Exception
	 *             Integer
	 */
	public Integer queryNewsNoticeCount(NewsNoticeCnd newsNoticeCnd)
			throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据条件查询新闻公告记录集合（分页）<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年4月23日
	 * @param newsNoticeCnd
	 * @return
	 * @throws Exception
	 *             List<NewsNoticeVo>
	 */
	public List<NewsNoticeVo> queryNewsNoticeListForPage(
			NewsNoticeCnd newsNoticeCnd, Page page) throws Exception;
}
