package com.cxdai.console.lottery.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cxdai.console.base.mapper.BaseSourceTypeChanceMapper;
import com.cxdai.console.lottery.entity.BaseGood;
import com.cxdai.console.lottery.vo.SourceTypeChance;

public interface SourceTypeChanceMapper extends BaseSourceTypeChanceMapper {

	List<SourceTypeChance> querySourceTypeChanceByGoodId(Integer goodId);


	SourceTypeChance getSourceTypeChance(@Param("lotteryChanceRuleInfoId") Integer lotteryChanceRuleInfoId, @Param("lotteryGoodsId") Integer lotteryGoodsId);

	int queryCountGlParentChanceByGood(BaseGood good);

}