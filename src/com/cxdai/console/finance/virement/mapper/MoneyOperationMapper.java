package com.cxdai.console.finance.virement.mapper;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.finance.virement.entity.AuditCnd;
import com.cxdai.console.finance.virement.entity.MoneyOperation;
import com.cxdai.console.finance.virement.vo.InterTransferCnd;
import com.cxdai.console.finance.virement.vo.MoneyOperationVo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface MoneyOperationMapper {
	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table cw_money_operation
	 *
	 * @mbggenerated
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table cw_money_operation
	 *
	 * @mbggenerated
	 */
	int insert(MoneyOperation record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table cw_money_operation
	 *
	 * @mbggenerated
	 */
	MoneyOperation selectByPrimaryKey(Integer id);

	MoneyOperation selectByPrimaryKeyForUpdate(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table cw_money_operation
	 *
	 * @mbggenerated
	 */
	List<MoneyOperation> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table cw_money_operation
	 *
	 * @mbggenerated
	 */
	int updateByPrimaryKey(MoneyOperation record);

	/***
	 * 根据条件查询内部转账总记录数
	 * 
	 * @param interTransferCnd
	 * @return
	 */
	Integer queryListCountByCnd(InterTransferCnd interTransferCnd);

	/***
	 * 根据条件查询内部转账记录
	 * 
	 * @param interTransferCnd
	 * @param page
	 * @return
	 */
	List<MoneyOperationVo> queryListByCnd(InterTransferCnd interTransferCnd, Page page);

	MoneyOperationVo findMoneyOperationDetail(@Param("id") Integer id);

	/***
	 * 内部转账查询统计
	 * 
	 * @param interTransferCnd
	 * @return
	 */
	Integer searchListCountByCnd(InterTransferCnd interTransferCnd);

	/***
	 * 内部转账查询
	 * 
	 * @param interTransferCnd
	 * @param page
	 * @return
	 */
	List<MoneyOperationVo> searchListByCnd(InterTransferCnd interTransferCnd, Page page);

	int updateMoneyOperation(AuditCnd auditCnd);

	MoneyOperationVo findMoneyOperation(@Param("id") Integer id);

	Map<String, Object> findMoneyOperationMap(@Param("id") Integer id);

}