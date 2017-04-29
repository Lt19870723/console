package com.cxdai.console.fix.mapper;

import com.cxdai.console.fix.entity.FixTenderReal;

public interface FixTenderRealMapper {

	/**
	 * <p>
	 * Description:根据定期宝ID，更新定期宝最终认购记录表<br />
	 * </p>
	 * 
	 * @author HuangJun
	 * @version 0.1 2015年6月26日
	 * @param fixTenderReal
	 * @return int
	 */
	int updateFixTenderRealByFixId(FixTenderReal fixTenderReal) throws Exception;

}