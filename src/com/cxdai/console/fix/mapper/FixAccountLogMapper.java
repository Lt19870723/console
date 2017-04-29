package com.cxdai.console.fix.mapper;

import com.cxdai.console.fix.vo.FixAccountLogVo;


/**   
 * <p>定期宝数据库访问类
 * Description:这里写描述<br />
 * </p>
 * @title FixBorrowMapper.java
 * @package com.cxdai.console.fix.mapper 
 * @author 陈建国
 * @version 0.1 2015年5月28日
 */
 

public interface FixAccountLogMapper {
	
 
	/**
	 * <p> 定期宝日志表
	 * Description:这里写描述<br />
	 * </p>
	 * @author 陈建国
	 * @version 0.1 2015年5月28日
	 * @param fixAccountLogVo
	 * @return
	 * @throws Exception
	 * int
	 */
	public int insertFixAccountLog(FixAccountLogVo fixAccountLogVo) throws Exception;
	
	 
}
