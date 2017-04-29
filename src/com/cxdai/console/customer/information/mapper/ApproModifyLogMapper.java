package com.cxdai.console.customer.information.mapper;

import com.cxdai.console.customer.information.entity.ApproModifyLog;

/**
 * <p>
 * Description:认证修改记录日志数据访问类<br />
 * </p>
 * 
 * @title ApproModifyLogMapper.java
 * @package com.cxdai.console.member.mapper
 * @author justin.xu
 * @version 0.1 2014年10月18日
 */
public interface ApproModifyLogMapper {

	/**
	 * <p>
	 * Description:插入记录,返回新增的主键ID<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param approModifyLog
	 * @return
	 * @throws Exception int
	 */
	public Integer insertEntity(ApproModifyLog approModifyLog) throws Exception;

}
