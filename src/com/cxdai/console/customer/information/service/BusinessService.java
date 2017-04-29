package com.cxdai.console.customer.information.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.information.mapper.BorrowBusinessMapper;
import com.cxdai.console.customer.information.mapper.BusinessMapper;
import com.cxdai.console.customer.information.vo.BorrowBusinessVo;
import com.cxdai.console.customer.information.vo.BusinessCnd;
import com.cxdai.console.customer.information.vo.BusinessVo;
import com.cxdai.console.util.HttpTookit;
import com.cxdai.console.util.ShiroUtils;

/**
 * @author WangQianJin
 * @title 业务员Service
 * @date 2015年8月17日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class BusinessService {

	@Autowired
	private BusinessMapper businessMapper;
	@Autowired
	private BorrowBusinessMapper borrowBusinessMapper;
	
	/**
	 * 添加业务员
	 * @author WangQianJin
	 * @title @param business
	 * @title @throws Exception
	 * @date 2015年8月17日
	 */
	public void addBusiness(BusinessVo business, HttpServletRequest request) throws Exception{
		business.setAddip(HttpTookit.getRealIpAddr(request));
		business.setAdduser(ShiroUtils.currentUser().getUserId());
		businessMapper.addBusiness(business);
	}
	
	/**
	 * 修改业务员
	 * @author WangQianJin
	 * @title @param business
	 * @title @throws Exception
	 * @date 2015年8月17日
	 */
	public void updateBusiness(BusinessVo business, HttpServletRequest request) throws Exception{
		business.setAddip(HttpTookit.getRealIpAddr(request));
		business.setAdduser(ShiroUtils.currentUser().getUserId());
		businessMapper.updateBusiness(business);
	}
	
	/**
	 * 根据ID获取业务员
	 * @author WangQianJin
	 * @title @param id
	 * @title @return
	 * @title @throws Exception
	 * @date 2015年8月17日
	 */
	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public BusinessVo getBusinessById(Integer id) throws Exception{
		return businessMapper.getBusinessById(id);
	}
	
	/**
	 * 根据查询条件分页获取业务员
	 * @author WangQianJin
	 * @title @param businessCnd
	 * @title @return
	 * @title @throws Exception
	 * @date 2015年8月17日
	 */
	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public Page selectPageBusinessByCnd(BusinessCnd businessCnd, Integer curPage, Integer pageSize) throws Exception{
		Page page = new Page(curPage, pageSize);
		int totalCount = businessMapper.getBusinessCountByCnd(businessCnd);
		page.setTotalCount(totalCount);
		List<BusinessVo> list = businessMapper.getBusinessListByCnd(businessCnd, page);
		page.setResult(list);
		return page;
	}	
	
	/**
	 * 根据查询条件获取业务员
	 * @author WangQianJin
	 * @title @param businessCnd
	 * @title @return
	 * @title @throws Exception
	 * @date 2015年8月17日
	 */
	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public List<BusinessVo> searchBusinessListByCnd(BusinessCnd businessCnd) throws Exception{
		return businessMapper.searchBusinessListByCnd(businessCnd);
	}
	
	/**
	 * 根据借款标ID查询借款标与业务员关系
	 * @author WangQianJin
	 * @title @param borrowId
	 * @title @return
	 * @title @throws Exception
	 * @date 2015年8月17日
	 */
	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public BorrowBusinessVo selectBorBusByBorrowId(Integer borrowId) throws Exception{
		return borrowBusinessMapper.selectBorBusByBorrowId(borrowId);
	}
	
	/**
	 * 根据用户ID获取业务员个数
	 * @author WangQianJin
	 * @throws Exception 
	 * @title @param id
	 * @title @return
	 * @title @throws Exception
	 * @date 2015年8月18日
	 */
	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public int getBusinessCountByUserId(Integer userId) throws Exception{
		return businessMapper.getBusinessCountByUserId(userId);
	}
}
