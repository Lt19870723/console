package com.cxdai.console.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.system.mapper.SchedulerMapper;
import com.cxdai.console.system.vo.SchedulerErrorLogCnd;

@Service
@Transactional(rollbackFor = Throwable.class)
public class SchedulerService {

	@Autowired
	private SchedulerMapper schedulerMapper;

	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public Page selectErrorLogPage(SchedulerErrorLogCnd schedulerErrorLogCnd, Page page) {
		page.setTotalCount(schedulerMapper.countErrorLogList(schedulerErrorLogCnd));
		if (page.getTotalPage() >= page.getPageNo()) {
			page.setResult(schedulerMapper.selectErrorLogList(schedulerErrorLogCnd, page));
		}
		return page;
	}
}
