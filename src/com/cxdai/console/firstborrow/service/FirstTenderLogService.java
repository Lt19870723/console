package com.cxdai.console.firstborrow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.firstborrow.mapper.FirstTenderLogMapper;
import com.cxdai.console.firstborrow.service.FirstTenderLogService;
import com.cxdai.console.firstborrow.vo.FirstTenderLogCnd;
import com.cxdai.console.firstborrow.vo.FirstTenderLogVo;

/**
 * 
 * <p>
 * Description:直通车投标日志记录业务处理类<br />
 * </p>
 * 
 * @title FirstTenderLogServiceImpl.java
 * @package com.cxdai.portal.first.service.impl
 * @author yangshijin
 * @version 0.1 2015年3月11日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class FirstTenderLogService{

	@Autowired
	private FirstTenderLogMapper firstTenderLogMapper;

	
	public Page queryPageListByCnd(FirstTenderLogCnd firstTenderLogCnd, Page page) throws Exception {
		Integer totalCount = firstTenderLogMapper.queryListCountByCnd(firstTenderLogCnd);
		page.setTotalCount(totalCount);
		List<FirstTenderLogVo> list = firstTenderLogMapper.queryListByCnd(firstTenderLogCnd, page);
		page.setResult(list);
		return page;
	}

	
	public FirstTenderLogVo queryById(int id) throws Exception {
		return firstTenderLogMapper.queryById(id);
	}

	
	@Transactional(rollbackFor = Throwable.class)
	public String delFirstTenderLog() throws Exception {
		String result = "success";
		try {
			firstTenderLogMapper.delFirstTenderLogByTime();
		} catch (Exception e) {
			result = "清除失败！";
			e.printStackTrace();
		}
		return result;
	}
}
