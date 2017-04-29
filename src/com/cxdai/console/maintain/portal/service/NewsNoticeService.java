package com.cxdai.console.maintain.portal.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.maintain.portal.entity.NewsNotice;
import com.cxdai.console.maintain.portal.entity.NewsNoticeCnd;
import com.cxdai.console.maintain.portal.entity.NewsNoticeVo;
import com.cxdai.console.maintain.portal.mapper.BaseNewsNoticeMapper;
import com.cxdai.console.maintain.portal.mapper.NewsNoticeMapper;

/**
 * 
 * <p>
 * Description:新闻公告接口的实现类<br />
 * </p>
 * 
 * @title NewsNoticeServiceImpl.java
 * @package com.cxdai.news_notice.service.impl
 * @author yangshijin
 * @version 0.1 2014年4月24日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class NewsNoticeService {
	public Logger logger = Logger.getLogger(NewsNoticeService.class);

	@Autowired
	private NewsNoticeMapper newsNoticeMapper;
	@Autowired
	private BaseNewsNoticeMapper baseNewsNoticeMapper;

	
	public NewsNotice queryById(int id) throws Exception {
		return baseNewsNoticeMapper.queryById(id);
	}

	
	public int insertNewsNotice(NewsNotice newsNotice) throws Exception {
		return baseNewsNoticeMapper.insertEntity(newsNotice);
	}

	
	public int updateNewsNotice(NewsNotice newsNotice) throws Exception {
		return baseNewsNoticeMapper.updateEntity(newsNotice);
	}

	
	public List<NewsNoticeVo> queryNewsNoticeList(NewsNoticeCnd newsNoticeCnd)
			throws Exception {
		return newsNoticeMapper.queryNewsNoticeList(newsNoticeCnd);
	}

	
	public Integer queryNewsNoticeCount(NewsNoticeCnd newsNoticeCnd)
			throws Exception {
		return newsNoticeMapper.queryNewsNoticeCount(newsNoticeCnd);
	}

	
	public Page queryListByNewsNoticeCnd(NewsNoticeCnd newsNoticeCnd,
			int curPage, int pageSize) throws Exception {
		Page page = new Page(curPage, pageSize);
		int totalCount = queryNewsNoticeCount(newsNoticeCnd);
		page.setTotalCount(totalCount);
		List<NewsNoticeVo> list = newsNoticeMapper.queryNewsNoticeListForPage(
				newsNoticeCnd, page);
		page.setResult(list);
		return page;
	}
}
