package com.cxdai.console.maintain.cms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.maintain.cms.entity.CmsPediaEntry;
import com.cxdai.console.maintain.cms.entity.CmsPediaEntryCnd;
import com.cxdai.console.maintain.cms.mapper.CmsPediaEntryMapper;

@Service
@Transactional(rollbackFor = Throwable.class)
public class CmsPediaEntryService{

	@Autowired
	private CmsPediaEntryMapper cmsPediaEntryMapper;

	
	public CmsPediaEntry getCmsPediaEntryById(Integer cmsPediaEntryId) {
		return cmsPediaEntryMapper.selectByPrimaryKey(cmsPediaEntryId);
	}

	
	public void updateCmsPediaEntry(CmsPediaEntry cmsPediaEntry) {
		CmsPediaEntry cmsPediaEntryTemp = cmsPediaEntryMapper.getPediaEntryByName(cmsPediaEntry.getName(), cmsPediaEntry.getId());
		if (cmsPediaEntryTemp != null) {
			throw new RuntimeException("词条名已经存在");
		}
		cmsPediaEntryMapper.updateByPrimaryKeySelective(cmsPediaEntry);
	}

	
	public void saveCmsPediaEntry(CmsPediaEntry cmsPediaEntry) {
		CmsPediaEntry cmsPediaEntryTemp = cmsPediaEntryMapper.getPediaEntryByName(cmsPediaEntry.getName(), null);
		if (cmsPediaEntryTemp != null) {
			throw new RuntimeException("词条名已经存在");
		}
		cmsPediaEntryMapper.insert(cmsPediaEntry);
	}

	
	public void deleteCmsPediaEntryByIds(String[] cmsPediaEntryIds) {
		cmsPediaEntryMapper.deleteByIds(cmsPediaEntryIds);
	}

	
	public Page searchCmsPediaEntryPageByCnd(CmsPediaEntryCnd cmsPediaEntryCnd, int pageNo, int pageSize) {
		Page page = new Page(pageNo, pageSize);
		page.setResult(cmsPediaEntryMapper.queryCmsPediaEntryListForPage(cmsPediaEntryCnd, page));
		page.setTotalCount(cmsPediaEntryMapper.getCountCmsPediaEntryListForPage(cmsPediaEntryCnd));
		return page;
	}


}
