package com.cxdai.console.sycee.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.sycee.entity.SyceeGoods;
import com.cxdai.console.sycee.entity.SyceeGoodsDiscount;
import com.cxdai.console.sycee.mapper.SyceeGoodsMapper;
import com.cxdai.console.sycee.vo.SyceeGoodCnd;
import com.cxdai.console.system.mapper.ConfigurationMapper;
import com.cxdai.console.util.ShiroUtils;

@Service
@Transactional(rollbackFor = Throwable.class)
public class SyceeGoodService {

	@Autowired
	SyceeGoodsMapper syceeGoodsMapper;

	@Autowired
	ConfigurationMapper configurationMapper;

	/**
	 * 用户元宝明细分页查询
	 * <p>
	 * Description: 这里写描述<br />
	 * </p>
	 * 
	 * @author huangpin
	 * @version 0.1 2015年10月19日
	 * @param cnd
	 * @param page
	 * @return
	 * @throws Exception
	 *             Page
	 */
	public Page pageQuery(SyceeGoodCnd cnd, Page page) throws Exception {
		int totalCount = syceeGoodsMapper.countSyceeGoodList(cnd);
		page.setTotalCount(totalCount);
		List<SyceeGoods> list = syceeGoodsMapper.selectSyceeGoodList(cnd, page);
		page.setResult(list);
		return page;
	}

	/**
	 * <p>
	 * Description:添加商品<br />
	 * </p>
	 * 
	 * @author yubin
	 * @version 0.1 2015年10月23日
	 * @param good
	 * @return
	 * @throws Exception
	 *             int
	 */
	public int insertSelective(SyceeGoods good) throws Exception {
		ShiroUser user = ShiroUtils.currentUser();
		good.setAdduser(user.getUserId());
		good.setAddusername(user.getUserName());
		good.setAddtime(new Date());
		return syceeGoodsMapper.insertSelective(good);
	}

	/**
	 * <p>
	 * Description:查找商品<br />
	 * </p>
	 * 
	 * @author yubin
	 * @version 0.1 2015年10月23日
	 * @param id
	 * @return
	 * @throws Exception
	 *             SyceeGoods
	 */
	public SyceeGoods selectByPrimaryKey(Integer id) throws Exception {
		return syceeGoodsMapper.selectByPrimaryKey(id);
	}

	/**
	 * <p>
	 * Description:更新商品<br />
	 * </p>
	 * 
	 * @author yubin
	 * @version 0.1 2015年10月23日
	 * @param syceeGoods
	 * @return
	 * @throws Exception
	 *             int
	 */
	public int updateByPrimaryKeySelective(SyceeGoods syceeGoods) throws Exception {
		return syceeGoodsMapper.updateByPrimaryKeySelective(syceeGoods);
	}

	public SyceeGoodsDiscount getDiscount() {
		return syceeGoodsMapper.getDiscount();
	}

	public int setDiscountDate(String beginDate, String endDate) throws Exception {
		return configurationMapper.updateByType(beginDate, endDate, 1398, 1);
	}
}
