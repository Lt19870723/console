package com.cxdai.console.maintain.portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.maintain.portal.entity.Slideshow;
import com.cxdai.console.maintain.portal.entity.SlideshowCnd;
import com.cxdai.console.maintain.portal.entity.SlideshowOperateLog;
import com.cxdai.console.maintain.portal.mapper.SlideshowMapper;

@Service
@Transactional(rollbackFor = Throwable.class)
public class SlideshowService{
	@Autowired
	SlideshowMapper slideshowMapper;

	public Page queryPageSlideshowListByCnd(SlideshowCnd slideshowCnd, int pageNo, int pageSize) {
		Page page = new Page(pageNo, pageSize);
		page.setResult(slideshowMapper.queryPageAllSlideshowListByCnd(slideshowCnd, page));
		page.setTotalCount(slideshowMapper.queryPageCountSlideshowListByCnd(slideshowCnd));
		return page;
	}

	public void updateByPrimaryKeySelective(Slideshow record) {
		slideshowMapper.updateByPrimaryKeySelective(record);	
	}

	public Slideshow selectByPrimaryKey(int slideshowId) {
		return slideshowMapper.selectByPrimaryKey(slideshowId);
	}

	public int saveSlideshow(Slideshow slideshow) {
		return slideshowMapper.insert(slideshow);
	}

	public Slideshow queryByParam(SlideshowCnd slideshowCndUpdate) {
		return slideshowMapper.queryByParam(slideshowCndUpdate);
	}

	public void save(SlideshowOperateLog slideshowOperateLog) {
		slideshowMapper.insertLog(slideshowOperateLog);	
	}

	public int getMaxOrder(int type1) {
		return slideshowMapper.getMaxOrder(type1);
	}
}
