package com.cxdai.console.sycee.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.sycee.entity.SyceeAddress;
import com.cxdai.console.sycee.entity.SyceeExchange;
import com.cxdai.console.sycee.mapper.SyceeAddressMapper;
import com.cxdai.console.sycee.mapper.SyceeExchangeMapper;
import com.cxdai.console.sycee.vo.SyceeExchangeCnd;
import com.cxdai.console.sycee.vo.SyceeExchangeVo;
import com.cxdai.console.util.ShiroUtils;
/**
 * 
 * <p>
 * Description:商品兑换处理业务<br />
 * </p>
 * @title SyceeExchangeService.java
 * @package com.cxdai.console. sycee.service 
 * @author yubin
 * @version 0.1 2015年10月30日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class SyceeExchangeService {

	@Autowired
	SyceeExchangeMapper syceeExchangeMapper;
	
	@Autowired
	SyceeAddressMapper syceeAddressMapper;
    /**
     * 
     * <p>
     * Description:分页查询<br />
     * </p>
     * @author yubin
     * @version 0.1 2015年10月30日
     * @param cnd
     * @param page
     * @return
     * @throws Exception
     * Page
     */
	public Page pageQuery(SyceeExchangeCnd cnd, Page page) throws Exception {
		int totalCount = syceeExchangeMapper.countSyceeExchange(cnd);
		page.setTotalCount(totalCount);
		List<SyceeExchange> list = syceeExchangeMapper.querySyceeExchange(cnd,page);
		page.setResult(list);
		return page;
	}
    /**
     * 
     * <p>
     * Description:导出记录<br />
     * </p>
     * @author yubin
     * @version 0.1 2015年10月30日
     * @param cnd
     * @return
     * @throws Exception
     * List<SyceeExchange>
     */
	public List<SyceeExchange> findAll(SyceeExchangeCnd cnd) throws Exception {
		return syceeExchangeMapper.querySyceeExchange(cnd);
	}
	
	public SyceeExchange selectByPrimaryKey(Integer id)throws Exception{
		return syceeExchangeMapper.selectByPrimaryKey(id);
	}
	public SyceeExchangeVo getSyceeExchangeById(Integer id)throws Exception{
		return syceeExchangeMapper.getSyceeExchangeById(id);
	}
	public int updateByPrimaryKeySelective(SyceeExchange syc)throws Exception{
		 ShiroUser shiroUser=ShiroUtils.currentUser();
		 syc.setDealTime(new Date());
		 syc.setDealStatus(1);
		 syc.setDealUsername(shiroUser.getUserName());
		 syc.setDealUser(shiroUser.getUserId());
		return syceeExchangeMapper.updateByPrimaryKeySelective(syc);
	}
	
	public SyceeAddress getAddresssById(int user_id) {
		return syceeAddressMapper.getById(user_id);
	}
}
