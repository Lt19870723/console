package com.cxdai.console.maintain.cms.mapper;

import org.apache.ibatis.annotations.Param;

import com.cxdai.console.maintain.cms.entity.CmsArticleTag;
import com.cxdai.console.maintain.cms.entity.CmsArticleTagKey;

public interface CmsArticleTagMapper {
    int deleteByPrimaryKey(CmsArticleTagKey key);

    int insert(CmsArticleTag record);

    int insertSelective(CmsArticleTag record);

    CmsArticleTag selectByPrimaryKey(CmsArticleTagKey key);

    int updateByPrimaryKeySelective(CmsArticleTag record);

    int updateByPrimaryKey(CmsArticleTag record);

	void deleteArticleTag(@Param("articleId") Integer id, @Param("tags") String[] tagsSpl);

	void deleteArticleTagByTags(@Param("tagIds") String[] tagIds);

	void deleteArticleTagByArticleId(@Param("articleId") Integer id);

}