package com.cxdai.console.maintain.cms.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.maintain.cms.entity.CmsArticle;
import com.cxdai.console.maintain.cms.entity.CmsArticleCnd;
import com.cxdai.console.maintain.cms.entity.CmsArticleTag;
import com.cxdai.console.maintain.cms.entity.CmsTag;
import com.cxdai.console.maintain.cms.mapper.CmsArticleMapper;
import com.cxdai.console.maintain.cms.mapper.CmsArticleTagMapper;

@Service
@Transactional(rollbackFor = Throwable.class)
public class CmsArticleService {

	@Autowired
	CmsArticleMapper cmsArticleMapper;

	@Autowired
	CmsTagService cmsTagService;

	@Autowired
	CmsArticleTagMapper articleTagMapper;

	public void saveCmsArticle(CmsArticle cmsArticle) {
		cmsArticleMapper.insert(cmsArticle);
		if (cmsArticle.getId() != null) {
			String tags = cmsArticle.getTags();
			if (!StringUtils.isEmpty(tags)) {
				if (tags.endsWith(",")) {
					tags = StringUtils.removeEnd(tags, ",");
				}
				String[] tagsSpl = StringUtils.splitByWholeSeparator(tags, ",");
				for (int i = 0; i < tagsSpl.length; i++) {
					String tag = tagsSpl[i];
					if (!StringUtils.isEmpty(tag)) {
						CmsTag cmsTag = new CmsTag(tag);
						// 保存标签
						cmsTagService.save(cmsTag);
						// 保存标签和文章关系
						CmsArticleTag cmsArticleTag = new CmsArticleTag(cmsArticle.getId(), cmsTag.getId(), cmsArticle.getChannelId());
						articleTagMapper.insert(cmsArticleTag);
					}

				}

			}

		}

	}

	public void deleteCmsArticleByIds(String[] cmsArticleIds) {
		cmsArticleMapper.deleteByIds(cmsArticleIds);
	}

	public void updateCmsArticle(CmsArticle cmsArticle) {
		cmsArticleMapper.updateByPrimaryKeyWithBLOBs(cmsArticle);

		String tags = cmsArticle.getTags();
		if (!StringUtils.isEmpty(tags)) {
			if (tags.endsWith(",")) {
				tags = StringUtils.removeEnd(tags, ",");
			}
			String[] tagsSpl = StringUtils.splitByWholeSeparator(tags, ",");
			// 先删除该文章所有关系
			articleTagMapper.deleteArticleTagByArticleId(cmsArticle.getId());
			for (int i = 0; i < tagsSpl.length; i++) {
				String tag = tagsSpl[i];
				if (!StringUtils.isEmpty(tag)) {
					CmsTag cmsTag = new CmsTag(tag);
					// 保存标签
					cmsTagService.save(cmsTag);
					// 保存标签和文章关系
					CmsArticleTag cmsArticleTag = new CmsArticleTag(cmsArticle.getId(), cmsTag.getId(), cmsArticle.getChannelId());
					articleTagMapper.insert(cmsArticleTag);
				}
			}
		}

	}

	public Page searchCmsArticlePageByCnd(CmsArticleCnd cmsArticleCnd, int pageNo, int pageSize) {
		Page page = new Page(pageNo, pageSize);
		page.setResult(cmsArticleMapper.queryCmsArticleListByCnd(cmsArticleCnd, page));
		page.setTotalCount(cmsArticleMapper.getCountCmsArticleListByCnd(cmsArticleCnd));
		return page;
	}

	public CmsArticle getCmsArticleById(Integer cmsArticleId) {
		return cmsArticleMapper.selectByPrimaryKey(cmsArticleId);
	}

	public void updateTopById(Integer cmsArticleId, int status) {
		cmsArticleMapper.updateTopById(cmsArticleId, status);
	}

}
