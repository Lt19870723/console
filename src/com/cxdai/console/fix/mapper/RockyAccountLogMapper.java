package com.cxdai.console.fix.mapper;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;

import com.cxdai.console.fix.entity.RockyAccountLog;
import com.cxdai.console.statistics.account.vo.ReportStatementCnd;


public interface RockyAccountLogMapper {

	/**
	 * <p>
	 * Description:新增:普通账户日志rocky_accountlog<br />
	 * </p>
	 * 
	 * @author HuangJun
	 * @version 0.1 2015年6月26日
	 * @param rockyAccountLog
	 * @return int
	 */
	int insertRockyAccountLog(RockyAccountLog rockyAccountLog);
	
	/**
	 * Description:根据时间段，展示推荐投资用户现金奖励金额
	 * @author wushaoling
	 * @param reportStatementCnd 
	 * @return 
	 */
	public BigDecimal querySumRockyAccountLog(ReportStatementCnd reportStatementCnd);
	
	/**
	 * Description:根据时间段，展示论坛活动现金支出
	 * @author jingbinbin
	 * @param reportStatementCnd 
	 * @return 
	 */
	public BigDecimal queryAwardAccountLog(ReportStatementCnd reportStatementCnd);

}