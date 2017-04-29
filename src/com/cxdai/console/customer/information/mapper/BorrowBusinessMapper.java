package com.cxdai.console.customer.information.mapper;

import com.cxdai.console.customer.information.entity.BorrowBusiness;
import com.cxdai.console.customer.information.vo.BorrowBusinessVo;

/**
 * @author WangQianJin
 * @title 借款标与业务员Mapper接口
 * @date 2015年8月14日
 */
public interface BorrowBusinessMapper {

	/**
	 * 增加借款标与业务员关系
	 * @author WangQianJin
	 * @title @param borrowBusiness
	 * @title @return
	 * @title @throws Exception
	 * @date 2015年8月14日
	 */
	public Integer insertBorrowBusiness(BorrowBusiness borrowBusiness) throws Exception;

	/**
	 * 修改借款标与业务员关系
	 * @author WangQianJin
	 * @title @param borrowBusiness
	 * @title @return
	 * @title @throws Exception
	 * @date 2015年8月14日
	 */
	public Integer updateBorrowBusiness(BorrowBusiness borrowBusiness) throws Exception;
	
	/**
	 * 根据ID查询借款标与业务员关系
	 * @author WangQianJin
	 * @title @param id
	 * @title @return
	 * @title @throws Exception
	 * @date 2015年8月14日
	 */
	public BorrowBusinessVo selectBorrowBusinessById(Integer id) throws Exception;
	
	/**
	 * 根据借款标ID查询锁定借款标与业务员关系
	 * @author WangQianJin
	 * @title @param borrowId
	 * @title @return
	 * @title @throws Exception
	 * @date 2015年8月14日
	 */
	public BorrowBusinessVo selectBorBusByBorrowIdForUpdate(Integer borrowId) throws Exception;
	
	/**
	 * 根据借款标ID查询借款标与业务员关系
	 * @author WangQianJin
	 * @title @param borrowId
	 * @title @return
	 * @title @throws Exception
	 * @date 2015年8月17日
	 */
	public BorrowBusinessVo selectBorBusByBorrowId(Integer borrowId) throws Exception;
}
