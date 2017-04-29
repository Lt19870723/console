package com.cxdai.console.system.mapper;

import java.util.List;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.system.entity.SchedulerErrorLog;
import com.cxdai.console.system.vo.SchedulerErrorLogCnd;

public interface SchedulerMapper {
	
	public int countErrorLogList(SchedulerErrorLogCnd schedulerErrorLogCnd);
	public List<SchedulerErrorLog> selectErrorLogList(SchedulerErrorLogCnd schedulerErrorLogCnd, Page page);
}