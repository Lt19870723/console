package com.cxdai.console.maintain.registersource.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.maintain.registersource.entity.SourceCnd;
import com.cxdai.console.maintain.registersource.entity.SourceListVo;
import com.cxdai.console.maintain.registersource.entity.SourceVo;
import com.cxdai.console.maintain.registersource.mapper.SourceMapper;
import com.cxdai.console.system.entity.Configuration;

/**
 * source相关服务
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title OperationNormalServiceImpl.java
 * @package com.cxdai.console.source.service.impl
 * @author ZHUCHEN
 * @version 0.1 2015年1月17日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class SourceService{

	@Autowired
	private SourceMapper sourceMapper;

	
	public List<SourceListVo> searchAll(SourceCnd sourceCnd) {
		return sourceMapper.searchAll(sourceCnd);
	}

	
	public String saveSource(SourceVo sourceVo) throws Exception {
		Integer i = sourceMapper.queryRockyConfiguration(sourceVo.getSourceNo());
		if (i == null || i == 0) {
			return "该渠道不存在";
		}
		if (sourceMapper.saveSource(sourceVo) == 1) {
			return "保存成功";
		} else {
			throw new Exception("保存失败");
		}
	}

	
	public List<Configuration> querySourceList() {
		return sourceMapper.querySourceList();
	}

	
	public SourceVo searchOne(String sourceNo) {
		return sourceMapper.searchOne(sourceNo);
	}
}
