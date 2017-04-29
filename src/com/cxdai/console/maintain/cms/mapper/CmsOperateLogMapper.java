package com.cxdai.console.maintain.cms.mapper;

import com.cxdai.console.maintain.cms.entity.CmsOperateLog;


public interface CmsOperateLogMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(CmsOperateLog record);

    int insertSelective(CmsOperateLog record);

    CmsOperateLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CmsOperateLog record);

    int updateByPrimaryKey(CmsOperateLog record);
}