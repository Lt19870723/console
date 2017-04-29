package com.cxdai.console.system.mapper;

import java.util.List;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.system.vo.IPWhileListCnd;
import com.cxdai.console.system.vo.IPWhileListVo;

/**
 * 
 * <p>
 * Description:IP白名单数据访问类<br />
 * </p>
 * @title BaseIPWhileListMapper.java
 * @package com.cxdai.base.mapper 
 * @author yangshijin
 * @version 0.1 2014年7月2日
 */
public interface IPWhileListMapper {
	/**
	 * 
	 * <p>
	 * Description:根据条件分页查询集合<br />
	 * </p>
	 * @author yangshijin
	 * @version 0.1 2014年7月2日
	 * @param iPWhileListCnd
	 * @param page
	 * @return
	 * @throws Exception
	 * List<IPWhileListVo>
	 */
	public List<IPWhileListVo> queryListForPage(IPWhileListCnd iPWhileListCnd,
			Page page) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据条件查询对象集合数量<br />
	 * </p>
	 * @author yangshijin
	 * @version 0.1 2014年7月2日
	 * @param iPWhileListCnd
	 * @return
	 * @throws Exception
	 * Integer
	 */
	public Integer queryListCount(IPWhileListCnd iPWhileListCnd)
			throws Exception;
}
