package com.cxdai.console.maintain.xw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cxdai.console.maintain.xw.entity.SearchNews;
import com.cxdai.console.maintain.xw.entity.ShowList;
import com.cxdai.console.maintain.xw.entity.WxArticles;

public interface WxPushMapper {

	/**
	 * 查询图文消息总数目
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author Wang Bo
	 * @version 0.1 2015年1月20日
	 * @param searchNews
	 * @return int
	 */
	int searchCount(SearchNews searchNews);

	/**
	 * 查询图文消息分页
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author Wang Bo
	 * @version 0.1 2015年1月20日
	 * @param searchNews
	 * @return List<ShowList>
	 */
	List<ShowList> searchAll(SearchNews searchNews);
	
	/**
	 * 主键查询图文
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author Wang Bo
	 * @version 0.1 2015年1月20日
	 * @param id
	 * @return WxArticles
	 */
	WxArticles queryById(Integer id);

	/**
	 * 插入图文
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author Wang Bo
	 * @version 0.1 2015年1月20日
	 * @param articles
	 * @return int
	 */
	int insterNews(WxArticles articles);

	/**
	 * 修改图文
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author Wang Bo
	 * @version 0.1 2015年1月20日
	 * @param articles
	 * @return int
	 */
	int updateNews(WxArticles articles);

	/**
	 * 删除图文消息
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author Wang Bo
	 * @version 0.1 2015年1月20日
	 * @param id
	 * @param type
	 * @return int
	 */
	int deleteNews(@Param("id") int id, @Param("type") int type);

	/**
	 * 推送后修改图文消息状态
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author Wang Bo
	 * @version 0.1 2015年1月20日
	 * @param articles
	 *            void
	 */
	void updateNewsStatus(WxArticles articles);

	/**
	 * 通过主要图文查询小图文
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author Wang Bo
	 * @version 0.1 2015年1月29日
	 * @param id
	 * @return List<WxArticles>
	 */
	List<WxArticles> queryNewsByPid(int id);

	/**
	 * 删除小图文
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author Wang Bo
	 * @version 0.1 2015年1月29日
	 * @param ids
	 *            小图文id集合
	 * @return int
	 */
	int deleteNewsByPid(@Param("ids") String ids);

	/**
	 * 通过主键插入相同记录
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author Wang Bo
	 * @version 0.1 2015年1月29日
	 * @param articles
	 * @return int
	 */
	int insertByCopy(WxArticles articles);

	/**
	 * 查询推送中的状态，用于校验同一时间只能推送一个任务
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author Wang Bo
	 * @version 0.1 2015年1月29日
	 * @return int
	 */
	int queryPushIng();
}
