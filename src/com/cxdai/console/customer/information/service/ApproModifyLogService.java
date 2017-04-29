package com.cxdai.console.customer.information.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.customer.information.entity.ApproModifyLog;
import com.cxdai.console.customer.information.mapper.ApproModifyLogMapper;



@Service
@Transactional(rollbackFor = Throwable.class)
public class ApproModifyLogService {
	public Logger logger = Logger.getLogger(ApproModifyLogService.class);


	@Autowired
	private ApproModifyLogMapper approModifyLogMapper;

	@Transactional(rollbackFor = Throwable.class)
	public Integer saveApproModifyLog(ApproModifyLog approModifyLog) throws Exception {
		return approModifyLogMapper.insertEntity(approModifyLog);
	}

}
