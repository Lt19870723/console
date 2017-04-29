package com.cxdai.console.lottery.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cxdai.console.base.mapper.BaseGoodMapper;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.lottery.vo.Good;
import com.cxdai.console.lottery.vo.GoodCnd;

public interface GoodMapper extends BaseGoodMapper {

	List<Good> queryGoodList(Page page);

	int queryCountGoodList();

	Good getGoodsByCnd(GoodCnd goodCnd);

	BigDecimal getSumChance(@Param("id") Integer id);

	int getCountTurntablePosition(@Param("turntablePosition") Integer turntablePosition, @Param("id") Integer Id);

	Good getGoodById(Integer goodId);

	void deleteChancesByParentId(@Param("id") Integer id);

	BigDecimal getGoodChangeById(@Param("id") Integer goodId);

	List<Good> queryGoods();

}