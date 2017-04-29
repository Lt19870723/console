package com.cxdai.console.customer.svip.mapper;

import com.cxdai.console.base.entity.AutoInvestConfig;

/**
 * 
 * <p>
 * Description:自动投标规则类数据访问类<br />
 * </p>
 * @title BaseAutoInvestConfigMapper.java
 * @package com.cxdai.base.mapper 
 * @author yangshijin
 * @version 0.1 2014年5月19日
 */
public interface BaseAutoInvestConfigMapper {
	/**
	 * 
	 * <p>
	 * Description:插入记录到自动投标规则表,返回新增的主键ID<br />
	 * </p>
	 * @author yangshijin
	 * @version 0.1 2014年5月19日
	 * @param autoInvestConfig
	 * @return
	 * @throws Exception
	 * int
	 */
	public int insertEntity(AutoInvestConfig autoInvestConfig) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据id查询自动投标规则类<br />
	 * </p>
	 * @author yangshijin
	 * @version 0.1 2014年5月19日
	 * @param id
	 * @return
	 * @throws Exception
	 * AutoInvestConfig
	 */
	public AutoInvestConfig queryById(Integer id) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:更新自动投标规则类<br />
	 * </p>
	 * @author yangshijin
	 * @version 0.1 2014年5月19日
	 * @param autoInvestConfig
	 * @return
	 * @throws Exception
	 * int
	 */
	public int updateEntity(AutoInvestConfig autoInvestConfig) throws Exception;

	public int updateUptime(AutoInvestConfig autoInvestConfig);
}
