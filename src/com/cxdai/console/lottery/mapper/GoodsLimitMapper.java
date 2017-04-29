package com.cxdai.console.lottery.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cxdai.console.base.mapper.BaseGoodLimitMapper;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.lottery.vo.GoodsLimit;

public interface GoodsLimitMapper extends BaseGoodLimitMapper {

	List<GoodsLimit> queryGoodLimitList(Page page);

	Integer queryCountGoodLimitList();

	GoodsLimit getGoodsLimitById(Integer goodsLimitId);

	int existsGoodsLimitByGoodId(@Param("goodsId") Integer lotteryGoodsId, @Param("id") Integer id, @Param("startTime") Date start, @Param("endTime") Date end);

}