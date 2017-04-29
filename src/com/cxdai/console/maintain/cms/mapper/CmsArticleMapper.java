package com.cxdai.console.maintain.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.maintain.cms.entity.CmsArticle;
import com.cxdai.console.maintain.cms.entity.CmsArticleCnd;

public interface CmsArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CmsArticle record);

    int insertSelective(CmsArticle record);

    CmsArticle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CmsArticle record);

    int updateByPrimaryKeyWithBLOBs(CmsArticle record);

    int updateByPrimaryKey(CmsArticle record);

	void deleteByIds(@Param("cmsArticleIds") String[] cmsArticleIds);

	List<CmsArticle> queryCmsArticleListByCnd(CmsArticleCnd cmsArticleCnd, Page page);

	int getCountCmsArticleListByCnd(CmsArticleCnd cmsArticleCnd);

	void updateTopById(@Param("id") Integer cmsArticleId, @Param("status") int status);

}