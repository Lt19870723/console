package com.cxdai.console.red.service;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.account.reward.mapper.BaseMemberAccumulatePointsMapper;
import com.cxdai.console.base.mapper.BaseMemberMapper;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.red.entity.RedAccount;
import com.cxdai.console.red.mapper.RedAccountMapper;
import com.cxdai.console.red.vo.RedRecordCnd;

/**
 * 红包管理-红包记录
 * @author liutao
 * @Date 2015-11-11
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class RedRecordService {
	private final static Logger logger = Logger.getLogger(RedRecordLogService.class);
	@Autowired
	private BaseMemberAccumulatePointsMapper baseMemberAccumulatePointsMapper;
	@Autowired
	private BaseMemberMapper baseMemberMapper;
	@Autowired
	private RedAccountMapper redAccountMapper;
	
	/**
	 * 红包管理-查询红包记录集合
	 * @author liutao
	 * @Date 2015-11-11
	 */
	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public Page queryRedAccountVoList(RedRecordCnd redRecordCnd, int pageSize, int curPage) throws Exception {
		Page page = new Page(curPage, pageSize);
		int totalCount = redAccountMapper.queryRedAccountVoCount(redRecordCnd);
		page.setTotalCount(totalCount);
		List<RedAccount> list = redAccountMapper.queryRedAccountVoList(redRecordCnd, page);
		page.setResult(list);
		return page;
	}
}
