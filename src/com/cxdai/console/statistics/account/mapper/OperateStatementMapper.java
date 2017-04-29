package com.cxdai.console.statistics.account.mapper;

import com.cxdai.console.statistics.account.entity.YunYingData;
import com.cxdai.console.statistics.account.vo.OperateStatementVo;
import com.cxdai.console.statistics.account.vo.ReportStatementCnd;

public interface OperateStatementMapper {
	public OperateStatementVo queryOperateStatement(ReportStatementCnd reportStatementCnd);
	public YunYingData querystatisticsyunYingList(ReportStatementCnd reportStatementCnd);
}
