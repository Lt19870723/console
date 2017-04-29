package com.cxdai.console.customer.information.mapper;

import com.cxdai.console.customer.information.entity.MemberAuditLog;

/**
 * <p>
 * Description:用户审核记录日志数据访问类<br />
 * </p>
 * 
 * @title MemberAuditLogMapper.java
 * @package com.cxdai.console.member.mapper
 * @author hujianpan
 * @version 0.1 2014年11月14日
 */
public interface MemberAuditLogMapper {

	/**
	 * <p>
	 * Description:插入记录<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2014年11月14日
	 * @param MemberAuditLog
	 * @throws Exception 
	 */
	public void insertEntity(MemberAuditLog memberAuditLog) throws Exception;

}
