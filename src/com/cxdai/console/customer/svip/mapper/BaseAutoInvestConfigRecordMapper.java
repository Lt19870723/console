package com.cxdai.console.customer.svip.mapper;

import com.cxdai.console.base.entity.AutoInvestConfigRecord;

/**
 * 
 * <p>
 * Description:自动投标规则日志类数据访问类<br />
 * </p>
 * @title BaseAutoInvestConfigRecordMapper.java
 * @package com.cxdai.base.mapper 
 * @author yangshijin
 * @version 0.1 2014年5月19日
 */
public interface BaseAutoInvestConfigRecordMapper {
	/**
	 * 
	 * <p>
	 * Description:插入记录到自动投标规则日志表,返回新增的主键ID<br />
	 * </p>
	 * @author yangshijin
	 * @version 0.1 2014年5月19日
	 * @param autoInvestConfigRecord
	 * @return
	 * @throws Exception
	 * int
	 */
	public int insertEntity(AutoInvestConfigRecord autoInvestConfigRecord) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据id查询自动投标规则日志类<br />
	 * </p>
	 * @author yangshijin
	 * @version 0.1 2014年5月19日
	 * @param id
	 * @return
	 * @throws Exception
	 * AutoInvestConfig
	 */
	public AutoInvestConfigRecord queryById(Integer id) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:更新自动投标规则日志类<br />
	 * </p>
	 * @author yangshijin
	 * @version 0.1 2014年5月19日
	 * @param autoInvestConfigRecord
	 * @return
	 * @throws Exception
	 * int
	 */
	public int updateEntity(AutoInvestConfigRecord autoInvestConfigRecord) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据投标记录ID查询日志信息.<br />
	 * </p>
	 * @author yangshijin
	 * @version 0.1 2014年5月23日
	 * @param tender_record_id
	 * @return
	 * @throws Exception
	 * AutoInvestConfigRecord
	 */
	public AutoInvestConfigRecord queryByTender_record_id(Integer tender_record_id) throws Exception;
}
