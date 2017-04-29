package com.cxdai.console.fix.mapper;

import com.cxdai.console.fix.entity.FixOperationLog;

/**
 * <p>
 * Description:定期宝操作日志数据库接口类<br />
 * </p>
 * 
 * @title FixOperationLogMapper.java
 * @package com.cxdai.console.fix.mapper
 * @author HuangJun
 * @version 0.1 2015年6月29日
 */
public interface FixOperationLogMapper {
	/**
	 * 添加定期宝操作日志
	 * 
	 * @param fixOperationLog
	 */
	public void insertFixOperationLog(FixOperationLog fixOperationLog);
}
