package com.cxdai.console.statistics.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.statistics.account.entity.YunYingData;
import com.cxdai.console.statistics.account.mapper.OperateStatementMapper;
import com.cxdai.console.statistics.account.vo.OperateStatementVo;
import com.cxdai.console.statistics.account.vo.ReportStatementCnd;

@Service
@Transactional(rollbackFor = Throwable.class)
public class OperateStatementService {
	@Autowired
	private OperateStatementMapper operateStatementMapper;
	
	public OperateStatementVo queryOperateStatement(
			ReportStatementCnd reportStatementCnd) {
		return operateStatementMapper.queryOperateStatement(reportStatementCnd);
	}
	public YunYingData querystatisticsyunYingList(
			ReportStatementCnd reportStatementCnd) {
		return operateStatementMapper.querystatisticsyunYingList(reportStatementCnd);
	}
}
