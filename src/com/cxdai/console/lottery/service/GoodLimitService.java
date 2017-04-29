package com.cxdai.console.lottery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.lottery.mapper.GoodsLimitMapper;
import com.cxdai.console.lottery.vo.GoodsLimit;

@Service
@Transactional(rollbackFor = Throwable.class)
public class   GoodLimitService {

	@Autowired
	GoodsLimitMapper goodLimitMapper;

	
	public Page queryPageGoodLimitList(int pageNo, int pageSize) {
		Page  page= new  Page(pageNo, pageSize);
		page.setResult(goodLimitMapper.queryGoodLimitList(page));
		page.setTotalCount(goodLimitMapper.queryCountGoodLimitList());
		return page;
	}

	
	public GoodsLimit getGoodsLimitById(Integer goodsLimitId) {

		return goodLimitMapper.getGoodsLimitById(goodsLimitId);
	}

	
	public void saveGoodLimit(GoodsLimit goodsLimit) {
		int count = 0;
		System.out.println(goodsLimit.getStartTime());
		System.out.println(goodsLimit.getEndTime());
		if (goodsLimit.getId() != null) {
			count = goodLimitMapper.existsGoodsLimitByGoodId(goodsLimit.getLotteryGoodsId(), goodsLimit.getId(), goodsLimit.getStartTime(), goodsLimit.getEndTime());
			if (count == 0) {
				goodLimitMapper.updateByPrimaryKeySelective(goodsLimit);
			}

		} else {
			count = goodLimitMapper.existsGoodsLimitByGoodId(goodsLimit.getLotteryGoodsId(), null, goodsLimit.getStartTime(), goodsLimit.getEndTime());
			if (count == 0) {
				goodLimitMapper.insert(goodsLimit);
			}
		}

		if (count > 0) {
			throw new RuntimeException("该时间段内已经对该物品做过设置");
		}

	}

	
	public void deleteGoodLimitById(Integer goodsLimitId) {
		goodLimitMapper.deleteByPrimaryKey(goodsLimitId);
	}

}
