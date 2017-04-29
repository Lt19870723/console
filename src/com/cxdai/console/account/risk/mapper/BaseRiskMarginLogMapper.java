package com.cxdai.console.account.risk.mapper;

import com.cxdai.console.account.risk.entity.RiskMarginLog;

/**
 * 
 * <p>
 * Description:风险备用金日志数据访问类<br />
 * </p>
 * @title BaseRishMarginLogMapper.java
 * @package com.cxdai.base.mapper 
 * @author yangshijin
 * @version 0.1 2014年7月3日
 */
public interface BaseRiskMarginLogMapper {
	/**
	 * 
	 * <p>
	 * Description:插入记录到风险备用金日志表,返回新增的主键ID<br />
	 * </p>
	 * @author yangshijin
	 * @version 0.1 2014年4月23日
	 * @param newsNotice
	 * @return
	 * @throws Exception
	 * int
	 */
	public int insertEntity(RiskMarginLog riskMarginLog) throws Exception;

}
