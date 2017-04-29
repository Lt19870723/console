package com.cxdai.console.borrow.manage.mapper;

import com.cxdai.console.borrow.manage.vo.*;
import com.cxdai.console.common.page.Page;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;



public interface CGRepayMentMapper {

	/**
	 * 本息还款更新投标记录
	 * @param map
	 * @throws Exception
	 */
	public  void updateTenderrecord(Map map) throws Exception;

	/**
	 * 还款完成更新投标记录状态
	 * @param map
	 * @throws Exception
	 */
	public  void updateTenderrecordStatus(Map map) throws Exception;


	/**
	 * 还款完成更新借款标状态
	 * @param map
	 * @throws Exception
	 */
	public  void updateBorrowStatus(Map map) throws Exception;

	/**
	 * 本息还款更新投标记录状态
	 * @throws Exception
	 */
	public  void updateTenderrecordStatusById(Map map) throws Exception;

	/**
	 * 累计待还总额
	 * @param borrowId
	 * @return
	 * @throws Exception
     */
	public  BigDecimal queryRepayTotalByBorrowId(@Param("borrowId") Integer borrowId) throws Exception;

	/**
	 * 更新某期待还记录
	 * @throws Exception
	 */
	public  void updatePrevRepaymentByMap(Map map) throws Exception;
	/**
	 * 更新当期待还记录
	 * @throws Exception
	 */
	public  void updateCurrentRepaymentByMap(Map map) throws Exception;
	/**
	 * 更新当期以后待还记录
	 * @throws Exception
	 */
	public  void updateAfterRepaymentByMap(Map map) throws Exception;


	/**
	 * 更新平台垫付待收记录
	 * @throws Exception
	 */
	public  void updateCollectionForWebpay(Map map) throws Exception;

	/**
	 * 更新平台正常还款待收记录
	 * @throws Exception
	 */
	public  void updateCollectionForNormalRepay(Map map) throws Exception;

	/**
	 * 更新提前正常还款待收记录
	 * @throws Exception
	 */
	public  void updateCollectionForEarlyRepay(Map map) throws Exception;


	/**
	 * 更新平台垫付待还记录
	 * @throws Exception
	 */
	public  void updateRepayMentForWebpay(Map map) throws Exception;


	/**
	 * 更新待收记录状态
	 * @throws Exception
	 */
	public  void updateCollectionStatusByID(@Param("status") Integer status,
										  @Param("collectionId") Integer collectionId) throws Exception;


	/**
	 * 更新待还金额
	 * @throws Exception
	 */
	public  void updateRepayMentAccount(Map map) throws Exception;

}
