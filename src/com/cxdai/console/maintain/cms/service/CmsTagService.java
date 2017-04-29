package com.cxdai.console.maintain.cms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.maintain.cms.entity.CmsTag;
import com.cxdai.console.maintain.cms.mapper.CmsArticleTagMapper;
import com.cxdai.console.maintain.cms.mapper.CmsTagMapper;

@Service
@Transactional(rollbackFor = Throwable.class)
public class CmsTagService{

	@Autowired
	CmsTagMapper cmsTagMapper;
	@Autowired
	CmsArticleTagMapper cmsArticleTagMapper;

	
	public Page searchCmsTagPage(int pageNo, int pageSize) {
		Page page = new Page(pageNo, pageSize);
		page.setResult(cmsTagMapper.queryCmsTagListForPage(page));
		page.setTotalCount(cmsTagMapper.getCountCmsTagListForPage());
		return page;
	}


	
	public void save(CmsTag cmsTag) {
		CmsTag cmsTagTemp = cmsTagMapper.getTagByName(cmsTag.getName());
		if (cmsTagTemp != null) {
			cmsTag.setId(cmsTagTemp.getId());
		} else {
			cmsTagMapper.insert(cmsTag);
		}
	}


	
	public void deleleteCmsTags(String[] cmsTagIds) {
		cmsTagMapper.deleleteCmsTags(cmsTagIds);
		// 删除关系
		cmsArticleTagMapper.deleteArticleTagByTags(cmsTagIds);
	}

}
