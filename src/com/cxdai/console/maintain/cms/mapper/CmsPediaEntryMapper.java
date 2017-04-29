package com.cxdai.console.maintain.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.maintain.cms.entity.CmsPediaEntry;
import com.cxdai.console.maintain.cms.entity.CmsPediaEntryCnd;

public interface CmsPediaEntryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CmsPediaEntry record);

    int insertSelective(CmsPediaEntry record);

    CmsPediaEntry selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CmsPediaEntry record);

    int updateByPrimaryKeyWithBLOBs(CmsPediaEntry record);

    int updateByPrimaryKey(CmsPediaEntry record);

	List<CmsPediaEntry> queryCmsPediaEntryListForPage(CmsPediaEntryCnd cmsPediaEntryCnd, Page page);

	int getCountCmsPediaEntryListForPage(CmsPediaEntryCnd cmsPediaEntryCnd);

	void deleteByIds(@Param("cmsPediaEntryIds") String[] cmsPediaEntryIds);

	CmsPediaEntry getPediaEntryByName(@Param("name") String name, @Param("id") Integer id);
}