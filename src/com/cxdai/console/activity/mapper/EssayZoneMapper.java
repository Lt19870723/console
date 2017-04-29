package com.cxdai.console.activity.mapper;

import java.util.List;

import com.cxdai.console.activity.vo.BbsItems;
import com.cxdai.console.common.page.Page;

/**
 * 
 * <p>
 * Description:三周年征文后台管理<br />
 * </p>
 * @title EssayZoneMapper.java
 * @package com.cxdai.console.activity.mapper 
 * @author likang
 * @version 0.1 2016年6月28日
 */
public interface EssayZoneMapper {

	//查询总帖子数
	int queryBbsCount(BbsItems bbsItems);

	//详细列表
	List<BbsItems> queryBbsList(BbsItems bbsItems, Page page);

	//查询积分详情
	List<BbsItems> queryIntegral(Integer itemId);

}
