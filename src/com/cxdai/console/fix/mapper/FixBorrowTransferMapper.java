package com.cxdai.console.fix.mapper;

import java.util.List;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.fix.entity.FixBorrowTransfer;
import com.cxdai.console.fix.vo.FixBorrowTransferCnd;
import com.cxdai.console.fix.vo.FixBorrowTransferVo;

public interface FixBorrowTransferMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(FixBorrowTransfer record);

	int insertSelective(FixBorrowTransfer record);

	FixBorrowTransfer selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(FixBorrowTransfer record);

	int updateByPrimaryKey(FixBorrowTransfer record);

	/**
	 * <p>
	 * Description:手动认购count <br />
	 * </p>
	 * @author HuangJun
	 * @version 0.1 2015年6月25日
	 * @param fixBorrowTransferCnd
	 * @return
	 * @throws Exception
	 * Integer
	 */
	public Integer getFixBorrowCountBySubsConn(FixBorrowTransferCnd fixBorrowTransferCnd) throws Exception;

	/**
	 * <p>
	 * Description:手动认购 list<br />
	 * </p>
	 * @author HuangJun
	 * @version 0.1 2015年6月25日
	 * @param fixBorrowTransferCnd
	 * @param page
	 * @return
	 * @throws Exception
	 * List<FixBorrowTransferVo>
	 */
	public List<FixBorrowTransferVo> getFixBorrowBySubsConn(FixBorrowTransferCnd fixBorrowTransferCnd, Page page) throws Exception;
	
	/**
	 * 
	 * <p>
	 * Description:查询转让中定期宝转让信息<br />
	 * </p>
	 * 
	 * @author 朱泳霖
	 * @version 0.1 2015年6月28日
	 * @return List<FixBorrowTransferVo>
	 */
	public List<FixBorrowTransferVo> queryTransferingFixBorrowList(FixBorrowTransferCnd FixBorrowTransferCnd);

	/**
	 * 
	 * <p>
	 * Description:根据ID查询定期宝转让信息<br />
	 * </p>
	 * 
	 * @author 朱泳霖
	 * @version 0.1 2015年6月30日
	 * @param id
	 * @return FixBorrowTransferVo
	 */
	public FixBorrowTransferVo queryFixBorrowTransferById(Integer id);
	
	/**
	 * 根据借款标ID查询定期宝转让信息
	 * @author WangQianJin
	 * @title @param borrowId
	 * @title @return
	 * @date 2015年9月15日
	 */
	public List<FixBorrowTransferVo> queryFixBorrowTransferByBorrowId(Integer borrowId);

}