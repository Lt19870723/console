package com.cxdai.console.maintain.cms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.maintain.cms.entity.CmsOperateLog;
import com.cxdai.console.maintain.cms.mapper.CmsOperateLogMapper;

@Service
@Transactional(rollbackFor = Throwable.class)
public class CmsOperateLogService{

	@Autowired
	private  CmsOperateLogMapper cmsOperateLogMapper;

	public void save(CmsOperateLog cmsOperateLog) {
		cmsOperateLogMapper.insert(cmsOperateLog);
	}

}
