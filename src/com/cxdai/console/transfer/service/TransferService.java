package com.cxdai.console.transfer.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.Constants;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.transfer.mapper.TransferApprovedMapper;
import com.cxdai.console.transfer.mapper.TransferMapper;
import com.cxdai.console.transfer.vo.SubscribeVo;
import com.cxdai.console.transfer.vo.TransferApprovedVo;
import com.cxdai.console.transfer.vo.TransferCnd;
import com.cxdai.console.transfer.vo.TransferListCnd;
import com.cxdai.console.transfer.vo.TransferVo;

@Service
@Transactional(rollbackFor = Throwable.class)
public class TransferService{

	@Autowired
	TransferMapper transferMapper;

	@Autowired
	TransferApprovedMapper transferApprovedMapper;

	
	public Page queryPageListByCnd(TransferCnd transferCnd, int pageNo, int pageSize) {
		Page page = new Page(pageNo, pageSize);
		page.setResult(transferMapper.queryPageListByCnd(transferCnd, page));
		page.setTotalCount(transferMapper.queryCountPageListByCnd(transferCnd));
		return page;
	}


	
	public void updateStute(TransferApprovedVo transferApprovedVo, TransferVo transfer) {
		transferMapper.updateStute(transfer);
		transferApprovedMapper.updateStute(transferApprovedVo);
	}

	
	public Page queryPageTransferListByCnd(TransferListCnd transferCnd, int pageNo, int pageSize) {
		Page page = new Page(pageNo, pageSize);
		page.setResult(transferMapper.queryPageAllTransferListByCnd(transferCnd, page));
		page.setTotalCount(transferMapper.queryCountPageAllTransferListByCnd(transferCnd));
		return page;
	}


	
	public void updateStuteForCancelTransfer(TransferVo transfer) {
		transferMapper.updateStuteForCancel(transfer);
	}

	
	public Page queryPageTransferCheckListByCnd(TransferCnd transferCnd, int pageNo, int pageSize) {
		Page page = new Page(pageNo, pageSize);
		page.setResult(transferMapper.queryPageTransferCheckListByCnd(transferCnd, page));
		page.setTotalCount(transferMapper.queryCountPageTransferCheckListByCnd(transferCnd));
		return page;
	}

	
	public String saveApproveTransferRecheck(Integer transferId, Integer checkUserId, String checkRemark, String addIp, Integer platForm) throws Exception {

		TransferVo transferVo = transferMapper.selectByIdForUpdate(transferId);
		if (!transferVo.getStatus().equals(Constants.TRANSFER_FULL_RECHECK) || !transferVo.getAccountReal().equals(transferVo.getAccountYes())) {
			return "债权转让状态不是满标复审中";
		}

		// 查询已投标总额 (logic for leader)
		BigDecimal total = transferMapper.querySubscribeTotalByTransferId(transferId);
		if (null == total || !total.equals(transferVo.getAccountReal())) {
			return "债权转让金额与认购金额不相等";
		}

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("transferId", transferId);
		params.put("addIp", addIp);
		params.put("checkUserId", checkUserId);
		params.put("checkRemark", checkRemark);
		params.put("platForm", platForm);
		transferMapper.saveApproveTransferRecheck(params);
		String msg = params.get("msg").toString();
		if (!"00001".equals(msg)) {
			throw new RuntimeException("债权转让复审失败");
		}

		return "success";
	}

	
	public List<SubscribeVo> querySubscribeMemberByTransferId(Integer transferId) {
		return transferMapper.querySubscribeMemberListByTransferId(transferId);
	}

}
