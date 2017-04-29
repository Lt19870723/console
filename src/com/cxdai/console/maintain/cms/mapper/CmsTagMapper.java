package com.cxdai.console.maintain.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.maintain.cms.entity.CmsTag;

public interface CmsTagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CmsTag record);

    int insertSelective(CmsTag record);

    CmsTag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CmsTag record);

    int updateByPrimaryKey(CmsTag record);

	List<CmsTag> queryCmsTagListForPage(Page page);

	int getCountCmsTagListForPage();

	CmsTag getTagByName(@Param("name") String name);

	void deleleteCmsTags(@Param("cmsTagIds") String[] cmsTagIds);

}