package com.cxdai.console.maintain.cms.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.maintain.cms.entity.CmsChannel;
import com.cxdai.console.maintain.cms.entity.CmsChannelCnd;
import com.cxdai.console.maintain.cms.mapper.CmsChannelMapper;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@Service
@Transactional(rollbackFor = Throwable.class)
public class CmsChannelService {

	Logger logger = LoggerFactory.getLogger(CmsChannelService.class);

	@Autowired
	CmsChannelMapper cmsChannelMapper;

	
	public List<CmsChannel> queryCmsChannelList() {
		return cmsChannelMapper.queryCmsChannelList();
	}

	
	public Page queryCmsChannelListForPage(CmsChannelCnd cmsChannelCnd, int pageNo, int pageSize) {
		Page page = new Page(pageNo, pageSize);
		page.setResult(cmsChannelMapper.queryCmsChannelListForPage(cmsChannelCnd, page));
		page.setTotalCount(cmsChannelMapper.getCountCmsChannelListForPage(cmsChannelCnd));
		return page;
	}

	
	public void deleteCmsChannelById(Integer cmsChannelId) {
		cmsChannelMapper.deleteByPrimaryKey(cmsChannelId);
	}

	
	public void updateCmsChannel(CmsChannel cmsChannel) throws MySQLIntegrityConstraintViolationException {

			// 验证用户名
			CmsChannel cmsChannelTemp = cmsChannelMapper.getCmsChannelByName(cmsChannel.getName(), cmsChannel.getId());
			if (cmsChannelTemp != null) {
				throw new RuntimeException("栏目已经存在");
			}

			// 验证排序
			int count = cmsChannelMapper.getCountByOrder(cmsChannel.getOrder(), cmsChannel.getId());
			if (count > 0) {
				throw new RuntimeException("排序已经存在");
			}

			int result = cmsChannelMapper.getCountByUrlcode(cmsChannel.getUrlCode(), cmsChannel.getId());
			if (result > 0) {
				throw new RuntimeException("urlcode已经存在");
			}
		try {
			cmsChannelMapper.updateByPrimaryKey(cmsChannel);
		} catch (Exception e) {
			logger.error("updateCmsChannel", e);
			throw new RuntimeException("保存失败....");
		}

	}

	
	public void saveCmsChannel(CmsChannel cmsChannel) throws MySQLIntegrityConstraintViolationException {
		// 验证用户名
		CmsChannel cmsChannelTemp = cmsChannelMapper.getCmsChannelByName(cmsChannel.getName(), null);
		if (cmsChannelTemp != null) {
			throw new RuntimeException("栏目已经存在");
		}



		int result = cmsChannelMapper.getCountByUrlcode(cmsChannel.getUrlCode(), null);
		if (result > 0) {
			throw new RuntimeException("urlcode已经存在");
		}

		int maxOrder = cmsChannelMapper.getMaxOrder();
		cmsChannel.setOrder(maxOrder);
		try {
			cmsChannelMapper.insert(cmsChannel);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("saveCmsChannel", e);
			throw new RuntimeException("保存失败....");
			// throw new
			// MySQLIntegrityConstraintViolationException("urlcode已经存在");
		}

	}

	
	public CmsChannel getCmsChannelById(Integer cmsChannelId) {
		return cmsChannelMapper.selectByPrimaryKey(cmsChannelId);
	}

	
	public List<CmsChannel> queryCmsParentChannelList() {
		return cmsChannelMapper.queryCmsParentChannelList();
	}

	
	public void updateHiddenById(Integer cmsChannelId, Integer isHidden) {
		cmsChannelMapper.updateHiddenById(cmsChannelId, isHidden);
	}

}
