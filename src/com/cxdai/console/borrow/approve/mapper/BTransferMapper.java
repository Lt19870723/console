package com.cxdai.console.borrow.approve.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cxdai.console.borrow.approve.entity.BTransfer;
import com.cxdai.console.borrow.approve.vo.BTransferVo;
import com.cxdai.console.borrow.approve.vo.SearchTransferVo;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.transfer.vo.TransferCnd;

 
public interface BTransferMapper {
	int insert(BTransferVo record);

	BTransferVo selectById(@Param("id") Integer id);

	BTransferVo selectByIdForUpdate(@Param("id") Integer id);

	Integer querytransferRecordCount(SearchTransferVo seach);

	List<BTransferVo> querytransferRecordList(SearchTransferVo seach, Page p);

	/**
	 * <p>
	 * Description:调用手动认购存储过程<br />
	 * </p>
	 * 
	 * @author chenpeng
	 * @version 0.1 2014年10月27日
	 * @param map void
	 */
	void saveManualSubscribe(Map<String, Object> params);

	void saveApproveTransferRecheck(Map<String, Object> params);

	BigDecimal querySubscribeTotalByTransferId(@Param("transferId") Integer transferId);

	List<BTransferVo> queryTransferClaim(@Param("transferCnd") TransferCnd transferCnd, Page page);

	int queryCountTransferClaim(@Param("transferCnd") TransferCnd transferCnd);

	BTransferVo queryTransferByTenderIdAndUserId(@Param("tenderId") Integer tenderId, @Param("userId") Integer userId, @Param("yearDay") int yearDay);

	int getTransferCountBytenderId(@Param("tenderId") Integer tenderId);

	int queryCountMyTransfer(@Param("transferCnd") TransferCnd transferCnd);

	List<BTransferVo> queryMyTransfer(@Param("transferCnd") TransferCnd transferCnd, Page page);

	int getTransferCountByVo(@Param("bTransfer") BTransfer bTransfer);

	void updateStute(@Param("id") Integer id, @Param("status") Integer transferStatuIng);

	void updateStuteForCancel(@Param("bTransfer") BTransfer bTransfer);

	BTransfer getTransferDetailById(Integer id);

	void lockTransferRecordByTransferId(@Param("bTransferId") Integer bTransferId);

	/**
	 * <p>
	 * Description:查询借款标id对应的债权转让id列表<br />
	 * </p>
	 * 
	 * @author chenpeng
	 * @version 0.1 2014年12月22日
	 * @param borrowId
	 * @param map void
	 */
	List<Integer> queryCancelTransfers(Integer borrowId);

	List<BTransferVo> querytransferRecordAllList();

	void saveTransfer(BTransferVo record);

	BTransferVo selectByCollectionId(@Param("collectionId") Integer collectionId);

	/**
	 * <p>
	 * Description:获取正在转让的个数<br />
	 * </p>
	 * 
	 * @author 邹理享
	 * @version 0.1 2015年3月4日
	 * @return int
	 */
	int getCountTransfering();

}