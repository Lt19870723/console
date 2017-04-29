package com.cxdai.console.maintain.registersource.mapper;

import java.util.List;

import com.cxdai.console.maintain.registersource.entity.SourceCnd;
import com.cxdai.console.maintain.registersource.entity.SourceListVo;
import com.cxdai.console.maintain.registersource.entity.SourceVo;
import com.cxdai.console.system.entity.Configuration;

/**
 * source相关操作
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title SourceMapper.java
 * @package com.cxdai.console.source.mapper
 * @author ZHUCHEN
 * @version 0.1 2015年1月17日
 */
public interface SourceMapper {

	/**
	 * 查询有效source的详细情况
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author ZHUCHEN
	 * @version 0.1 2015年1月17日
	 * @return List<SourceListVo>
	 */
	List<SourceListVo> searchAll(SourceCnd sourceCnd);

	/**
	 * 新增
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author ZHUCHEN
	 * @version 0.1 2015年1月17日
	 * @param sourceVo
	 * @return int
	 */
	int saveSource(SourceVo sourceVo);

	/**
	 * 查询source下拉框
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author ZHUCHEN
	 * @version 0.1 2015年1月16日
	 * @return List<SelectItem>
	 */
	public List<Configuration> querySourceList();

	/**
	 * 验证source编号是否存在
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author ZHUCHEN
	 * @version 0.1 2015年1月17日
	 * @param sourceNo
	 * @return int
	 */
	Integer queryRockyConfiguration(String sourceNo);

	/**
	 * 查询一个source对象
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author ZHUCHEN
	 * @version 0.1 2015年1月18日
	 * @param sourceNo
	 * @return SourceVo
	 */
	SourceVo searchOne(String sourceNo);
}
