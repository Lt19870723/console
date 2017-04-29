package com.cxdai.console.lottery.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.lottery.constants.LotteryConstant;
import com.cxdai.console.lottery.entity.BaseGood;
import com.cxdai.console.lottery.mapper.GoodMapper;
import com.cxdai.console.lottery.vo.Good;
import com.cxdai.console.lottery.vo.GoodCnd;

@Service
@Transactional(rollbackFor = Throwable.class)
public class   GoodService {

	@Autowired
	GoodMapper goodMapper;

	@Autowired
	SourceTypeChanceService sourceTypeChanceService;

	
	public void save(BaseGood good) {

		if (good.getParentId() == null) {
			int count = goodMapper.getCountTurntablePosition(good.getTurntablePosition(), good.getId());
			if (count != 0) {
				throw new RuntimeException("转盘位置已存在");
			}

			// 判断父概率总和是否大于100
			BigDecimal sumChance = goodMapper.getSumChance(good.getId());
			BigDecimal change = good.getChance();
			 
			if (change.add(sumChance).compareTo(new BigDecimal(100)) == 1) {
				throw new RuntimeException("概率超过100%");
			}
		}

		if (good.getId() != null) {
			// 判断修改的奖品 的 概率 关联的 指定活动下的类型是否 包含大于的父类概率的
			int count = sourceTypeChanceService.existsGlParentChanceByGood(good);
			if (count > 0) {
				throw new RuntimeException("关联活动中有概率大于该奖品概率，请先修改关联活动的概率");
			}
		}

		GoodCnd goodCnd = new GoodCnd();
		if (good.getAwardType() == 4 && good.getParentId() == null) {
			// 说明保存的是父类 抽奖机会 判断抽奖机会是否已经被保存
			String navtName = good.getName();
			if (good.getId() != null) {
				// 删除
				goodMapper.deleteChancesByParentId(good.getId());
				// 新增操作
				addChildChance(good, navtName);
				// 修改good其他属性
				good.setName(LotteryConstant.CHANCENAME);
				goodMapper.updateByPrimaryKey(good);
				return;
			}


			good.setName(LotteryConstant.CHANCENAME);
			goodCnd.setName(LotteryConstant.CHANCENAME);
			goodCnd.setType(good.getAwardType());
			Good goods = goodMapper.getGoodsByCnd(goodCnd);
			if (goods != null) {
				good.setId(goods.getId());
			} else {
				goodMapper.insert(good);
			}

			addChildChance(good, navtName);

		} else {
			// 其他类型

			if (good.getAwardType() == 3) {
				// 谢谢参与
				goodCnd.setName(LotteryConstant.THINKJOIN);
				good.setName(LotteryConstant.THINKJOIN);
			}else{
				goodCnd.setName(good.getName());
			}

			goodCnd.setType(good.getAwardType());
			goodCnd.setId(good.getId());
			Good goods = goodMapper.getGoodsByCnd(goodCnd);
			if (goods != null) {
				throw new RuntimeException("该类型中已存在相同类型的【" + good.getName() + "】");
			}

			if (good.getId() != null) {
				goodMapper.updateByPrimaryKey(good);
			} else {
				goodMapper.insert(good);
			}
		}

	}

	private void addChildChance(BaseGood good, String navtName) {
		int parentId = good.getId();
		GoodCnd goodCnd = new GoodCnd();
		String chirldChanceStr = ((Good) good).getChirldChanceStr();
		String[] chirldChance = chirldChanceStr.split(LotteryConstant.FENHAO);
		String[] names = navtName.split(LotteryConstant.FENHAO);
		for (int i = 0; i < names.length; i++) {
			Good chirldgood = new Good();
			chirldgood.setParentId(parentId);
			chirldgood.setName(names[i]);
			chirldgood.setChance(BigDecimal.valueOf(Double.parseDouble(chirldChance[i])));
			chirldgood.setAwardType(good.getAwardType());

			goodCnd.setParentId(parentId);
			goodCnd.setName(names[i]);
			goodCnd.setType(good.getAwardType());
			Good existsGood = goodMapper.getGoodsByCnd(goodCnd);
			if (existsGood == null) {
				chirldgood.setAwardNum(Integer.parseInt(chirldgood.getName()));
				chirldgood.setName(chirldgood.getName().concat("次抽奖机会"));
				goodMapper.insert(chirldgood);
			}

		}
	}

	
	public Page queryPageGoodList(int pageNo, int pageSize) {
		Page page = new Page(pageNo, pageSize);
		page.setResult(goodMapper.queryGoodList(page));
		page.setTotalCount(goodMapper.queryCountGoodList());
		return page;
	}

	
	public void deleteGood(Integer id) {
		goodMapper.deleteByPrimaryKey(id);
	}

	
	public Good getGoodById(Integer goodId) {
		return goodMapper.getGoodById(goodId);
	}


	
	public BigDecimal getSumChance(Integer goodId) {
		return goodMapper.getSumChance(goodId);
	}

	
	public BigDecimal getGoodChangeById(Integer goodId) {
		return goodMapper.getGoodChangeById(goodId);
	}

	
	public List<Good> queryGoods() {
		return goodMapper.queryGoods();
	}

}
