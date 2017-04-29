package com.cxdai.console.transfer.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.transfer.vo.SubscribeVo;
import com.cxdai.console.transfer.vo.TransferCnd;
import com.cxdai.console.transfer.vo.TransferListCnd;
import com.cxdai.console.transfer.vo.TransferVo;

public interface TransferMapper {

	List<TransferVo> queryPageListByCnd(@Param("transferCnd") TransferCnd transferCnd, Page page);

	int queryCountPageListByCnd(@Param("transferCnd") TransferCnd transferCnd);

	void updateStute(@Param("transfer") TransferVo transfer);

	List<TransferVo> queryPageAllTransferListByCnd(@Param("transferListCnd") TransferListCnd transferListCnd, Page page);

	int queryCountPageAllTransferListByCnd(@Param("transferListCnd") TransferListCnd transferListCnd);

	void updateStuteForCancel(@Param("bTransfer") TransferVo transfer);

	List<TransferVo> queryPageTransferCheckListByCnd(@Param("transferCnd") TransferCnd transferCnd, Page page);

	int queryCountPageTransferCheckListByCnd(@Param("transferCnd") TransferCnd transferCnd);

	TransferVo selectByIdForUpdate(Integer transferId);

	BigDecimal querySubscribeTotalByTransferId(Integer transferId);

	void saveApproveTransferRecheck(Map<String, Object> params);

	List<Integer> queryCancelTransfers(Integer borrowId);

	void transferCancelByRepay(Map<String, Object> mapTrans);

	List<SubscribeVo> querySubscribeMemberListByTransferId(@Param("transferId") Integer transferId);

}
