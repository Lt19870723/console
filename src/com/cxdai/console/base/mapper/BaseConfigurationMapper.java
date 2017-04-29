package com.cxdai.console.base.mapper;

import java.util.List;

import com.cxdai.console.system.entity.Configuration;


/**
 * 
 * <p>
 * Description:字典项配置数据访问类<br />
 * </p>
 * @title BaseConfigurationMapper.java
 * @package com.cxdai.base.mapper 
 * @author yangshijin
 * @version 0.1 2014年7月7日
 */
public interface BaseConfigurationMapper {

	/**
	 * 
	 * <p>
	 * Description:根据类型查询字典配置<br />
	 * </p>
	 * @author yangshijin
	 * @version 0.1 2014年7月7日
	 * @param type
	 * @return
	 * @throws Exception
	 * List<Configuration>
	 */
	public List<Configuration> queryConfigurationListByType(int type) throws Exception;
}
