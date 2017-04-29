package com.cxdai.console.firstborrow.mapper;

import com.cxdai.console.base.entity.FirstTransferLog;

/**
 * <p>
 * Description:直通车转让事件日志数据库访问类述<br />
 * </p>
 * 
 * @title FirstTransferLogMapper.java
 * @package com.cxdai.portal.first.mapper
 * @author justin.xu
 * @version 0.1 2015年3月16日
 */
public interface FirstTransferLogMapper {
	/**
	 * <p>
	 * Description:保存直通车转让事件日志<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2015年3月16日
	 * @param firstTransferLog
	 * @return
	 * @throws Exception Integer
	 */
	public Integer saveFirstTransferLog(FirstTransferLog firstTransferLog) throws Exception;

}