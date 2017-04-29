package com.cxdai.console.customer.information.mapper;

import java.util.List;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.information.vo.BusinessCnd;
import com.cxdai.console.customer.information.vo.BusinessVo;

/**
 * @author WangQianJin
 * @title 业务员Mapper
 * @date 2015年8月17日
 */
public interface BusinessMapper {

	/**
	 * 添加业务员
	 * @author WangQianJin
	 * @title @param business
	 * @title @throws Exception
	 * @date 2015年8月17日
	 */
	public void addBusiness(BusinessVo business) throws Exception;
	
	/**
	 * 修改业务员
	 * @author WangQianJin
	 * @title @param business
	 * @title @throws Exception
	 * @date 2015年8月17日
	 */
	public void updateBusiness(BusinessVo business) throws Exception;
	
	/**
	 * 根据ID获取业务员
	 * @author WangQianJin
	 * @title @param id
	 * @title @return
	 * @title @throws Exception
	 * @date 2015年8月17日
	 */
	public BusinessVo getBusinessById(Integer id) throws Exception;
	
	/**
	 * 根据查询条件获取业务员个数
	 * @author WangQianJin
	 * @title @param businessCnd
	 * @title @return
	 * @title @throws Exception
	 * @date 2015年8月17日
	 */
	public Integer getBusinessCountByCnd(BusinessCnd businessCnd) throws Exception;
	
	/**
	 * 根据查询条件分页获取业务员列表
	 * @author WangQianJin
	 * @title @param businessCnd,page
	 * @title @return
	 * @title @throws Exception
	 * @date 2015年8月17日
	 */
	public List<BusinessVo> getBusinessListByCnd(BusinessCnd businessCnd, Page page) throws Exception;
	
	/**
	 * 根据查询条件获取业务员列表
	 * @author WangQianJin
	 * @title @param businessCnd
	 * @title @return
	 * @title @throws Exception
	 * @date 2015年8月17日
	 */
	public List<BusinessVo> searchBusinessListByCnd(BusinessCnd businessCnd) throws Exception;
	
	/**
	 * 根据用户ID获取业务员个数
	 * @author WangQianJin
	 * @title @param id
	 * @title @return
	 * @title @throws Exception
	 * @date 2015年8月18日
	 */
	public Integer getBusinessCountByUserId(Integer userId) throws Exception;
}
