package com.cxdai.console.maintain.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.maintain.cms.entity.CmsChannel;
import com.cxdai.console.maintain.cms.entity.CmsChannelCnd;

public interface CmsChannelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CmsChannel record);

    int insertSelective(CmsChannel record);

    CmsChannel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CmsChannel record);

    int updateByPrimaryKey(CmsChannel record);

	List<CmsChannel> queryCmsChannelList();

	List<CmsChannel> queryCmsChannelListForPage(CmsChannelCnd cmsChannelCnd, Page page);

	Integer getCountCmsChannelListForPage(CmsChannelCnd cmsChannelCnd);

	List<CmsChannel> queryCmsParentChannelList();

	CmsChannel getCmsChannelByName(@Param("name") String name, @Param("id") Integer id);

	int getCountByOrder(@Param("order") Integer order, @Param("id") Integer id);

	int getMaxOrder();

	int getCountByUrlcode(@Param("urlCode") String urlCode, @Param("id") Integer id);

	void updateHiddenById(@Param("id") Integer cmsChannelId, @Param("isHidden") Integer isHidden);

}