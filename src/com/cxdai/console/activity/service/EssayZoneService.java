package com.cxdai.console.activity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.activity.mapper.EssayZoneMapper;
import com.cxdai.console.activity.vo.BbsItems;
import com.cxdai.console.common.page.Page;

@Service
@Transactional(rollbackFor = Throwable.class)
public class EssayZoneService {

	@Autowired
	private EssayZoneMapper essayZoneMapper;
	
	public Page findBbsItemsPages(BbsItems bbsItems, int curPage, int pageSize) throws Exception {
		Page page = new Page(curPage, pageSize);
		int totalcount = essayZoneMapper.queryBbsCount(bbsItems);
		page.setTotalCount(totalcount);
		List<BbsItems> list = essayZoneMapper.queryBbsList(bbsItems, page);
		page.setResult(list);
		return page;
	}

	public List<BbsItems> queryIntegral(Integer itemId) {
		List<BbsItems> list = essayZoneMapper.queryIntegral(itemId);
		return list;
	}
	

	
}
