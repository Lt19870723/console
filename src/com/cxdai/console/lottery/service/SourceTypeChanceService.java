package com.cxdai.console.lottery.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.lottery.entity.BaseGood;
import com.cxdai.console.lottery.mapper.SourceTypeChanceMapper;
import com.cxdai.console.lottery.vo.SourceTypeChance;

@Service
@Transactional(rollbackFor = Throwable.class)
public class SourceTypeChanceService {

	@Autowired
	GoodService goodService;

	@Autowired
	SourceTypeChanceMapper sourceTypeChanceMapper;

	
	public void saveSourceTypeChance(SourceTypeChance sourceTypeChance) {

		BigDecimal goodChance = goodService.getGoodChangeById(sourceTypeChance.getLotteryGoodsId());

		BigDecimal chance = sourceTypeChance.getChance();
		String chanceRep = "^[0-9]{1,3}[.]?[0-9]?[0-9]?$";

		if (StringUtils.isEmpty(String.valueOf(chance))) {
			throw new RuntimeException("概率不能为空");
		}

		if (!String.valueOf(chance).matches(chanceRep)) {
			throw new RuntimeException(chance + "--概率不能大于100且保留两位位小数");
		}
		if (chance.compareTo(goodChance) == 1) {
			throw new RuntimeException("活动概率不能超过该奖品概率");
		}

		SourceTypeChance sourceTypeChance2 = sourceTypeChanceMapper.getSourceTypeChance(sourceTypeChance.getLotteryChanceRuleInfoId(),
				sourceTypeChance.getLotteryGoodsId());
		if (sourceTypeChance2 != null) {
			return;
		}
		sourceTypeChanceMapper.insert(sourceTypeChance);

	}

	
	public void deleteSourceTypeChance(Integer id) {
		sourceTypeChanceMapper.deleteByPrimaryKey(id);
	}

	
	public List<SourceTypeChance> querySourceTypeChanceByGoodId(Integer goodId) {
		return sourceTypeChanceMapper.querySourceTypeChanceByGoodId(goodId);
	}

	
	public int existsGlParentChanceByGood(BaseGood good) {
		return sourceTypeChanceMapper.queryCountGlParentChanceByGood(good);
	}

	
	public void updateSourceTypeChanceByEntry(SourceTypeChance sourceTypeChance) {
		sourceTypeChanceMapper.updateByPrimaryKeySelective(sourceTypeChance);
	}

}
