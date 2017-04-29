package com.cxdai.console.firstborrow.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.base.entity.FirstTenderReal;
import com.cxdai.console.base.entity.FirstTransfer;
import com.cxdai.console.base.entity.FirstTransferApproved;
import com.cxdai.console.base.entity.FirstTransferLog;
import com.cxdai.console.base.mapper.BaseFirstTenderRealMapper;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.firstborrow.mapper.FirstTransferApprovedMapper;
import com.cxdai.console.firstborrow.mapper.FirstTransferLogMapper;
import com.cxdai.console.firstborrow.mapper.FirstTransferMapper;
import com.cxdai.console.firstborrow.vo.FirstTransferCnd;
import com.cxdai.console.firstborrow.vo.FirstTransferVo;
import com.cxdai.console.security.ShiroUser;


/**
 * <p>
 * Description:直通车转让Service实现类<br />
 * </p>
 * @title FirstTransferServiceImpl.java
 * @package com.cxdai.console.first.service.impl 
 * @author 朱泳霖
 * @version 0.1 2015年4月9日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class FirstTransferService{

	@Autowired
	private FirstTransferMapper firstTransferMapper;
	
	@Autowired
	private FirstTransferLogMapper firstTransferLogMapper;
	
	@Autowired
	private BaseFirstTenderRealMapper baseFirstTenderRealMapper;
	
	@Autowired
	private FirstTransferApprovedMapper firstTransferApprovedMapper;
	
	
	public Page queryPageListByCnd(FirstTransferCnd firstTransferCnd, int pageNo, int pageSize) {
		Page page = new Page(pageNo, pageSize);
		page.setResult(firstTransferMapper.queryPageListByCnd(firstTransferCnd, page));
		page.setTotalCount(firstTransferMapper.queryCountPageListByCnd(firstTransferCnd));
		return page;
	}

	
	public Page queryFirstTransferListByCnd(FirstTransferCnd firstTransferCnd, int pageNo, int pageSize) {
		Page page = new Page(pageNo, pageSize);
		page.setResult(firstTransferMapper.queryFirstTransferListByCnd(firstTransferCnd, page));
		page.setTotalCount(firstTransferMapper.queryCountFirstTransferListByCnd(firstTransferCnd));
		return page;
	}
	
	public String saveCancelFirstTransfer(FirstTransferVo transfer,ShiroUser user,String ip  ) throws Exception {
		String result = BusinessConstants.SUCCESS;
		FirstTransferCnd firstTransferCnd = new FirstTransferCnd();
		firstTransferCnd.setLockedRecordYn(BusinessConstants.LOCKED_RECORD_YES);
		firstTransferCnd.setId(transfer.getId());
		FirstTransferVo firstTransferVo = firstTransferMapper.queryTransferById(firstTransferCnd);
		if (null == firstTransferVo) {
			return "未找到直通车转让数据!";
		}
		if (!(firstTransferVo.getStatus() == 2 || firstTransferVo.getStatus() == 3)) {
			return "直通车数据非转让中,请确认!";
		}
		// 更新转让的状态为被撤销
		FirstTransfer firstTransfer = new FirstTransfer();
		firstTransfer.setId(firstTransferVo.getId());
		firstTransfer.setStatus(6);
		firstTransfer.setRemark(transfer.getRemark());
		firstTransfer.setLastUpdateName(user.getUserName());
		firstTransfer.setLastUpdateTime(new Date());
		firstTransferMapper.updateFirstTransferStatus(firstTransfer);
		// 记录撤消日志
		FirstTransferLog firstTransferLog = new FirstTransferLog(firstTransferVo.getId(), firstTransferVo.getUserId(), new Date(), ip, null,
				user.getUserName(), "取消直通车转让", 2, 2, firstTransferVo.getPlatform());
		firstTransferLogMapper.saveFirstTransferLog(firstTransferLog);
		// 更新最终认购记录的状态为未失效
		FirstTenderReal firstTenderReal = new FirstTenderReal();
		firstTenderReal.setId(firstTransferVo.getFirstTenderRealId());
		firstTenderReal.setStatus(0);
		baseFirstTenderRealMapper.updateEntity(firstTenderReal);
		return result;
	}

	
	public void updateTransferApproved(FirstTransferApproved firstTransferApproved)throws Exception {
		firstTransferApprovedMapper.updateTransferApproved(firstTransferApproved);
	}
	
}
