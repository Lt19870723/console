package com.cxdai.console.sycee.mapper;

import java.util.List;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.sycee.entity.SyceeExchange;
import com.cxdai.console.sycee.vo.SyceeExchangeCnd;
import com.cxdai.console.sycee.vo.SyceeExchangeVo;

public interface SyceeExchangeMapper {
	int countSyceeExchange(SyceeExchangeCnd cnd);

	List<SyceeExchange> querySyceeExchange(SyceeExchangeCnd cnd,Page page);
	
	List<SyceeExchange> querySyceeExchange(SyceeExchangeCnd cnd);
	
	SyceeExchange selectByPrimaryKey(Integer id);
	
	int updateByPrimaryKeySelective(SyceeExchange  record);
	
	SyceeExchangeVo getSyceeExchangeById(Integer id);
}
