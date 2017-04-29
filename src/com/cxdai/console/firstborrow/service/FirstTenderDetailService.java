package com.cxdai.console.firstborrow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.firstborrow.mapper.FirstTenderDetailMapper;
import com.cxdai.console.firstborrow.vo.FirstTenderDetailCnd;
import com.cxdai.console.firstborrow.vo.FirstTenderDetailVo;
import com.cxdai.console.statistics.account.vo.AccountVo;

/**
 * <p>
 * Description:优先投标计划开通明细业务实现类<br />
 * </p>
 * 
 * @title FirstTenderDetailServiceImpl.java
 * @package com.cxdai.console.first.service.impl
 * @author justin.xu
 * @version 0.1 2014年7月22日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class FirstTenderDetailService{

	@Autowired
	private FirstTenderDetailMapper firstTenderDetailMapper;

	
	public Integer updateStatusByFirstBorrowId(Integer firstBorrowId, Integer status) throws Exception {
		return firstTenderDetailMapper.updateStatusByFirstBorrowId(firstBorrowId, status);
	}

	
	public Integer updateRealIdByFirstBorrowId(Integer firstBorrowId) throws Exception {
		return firstTenderDetailMapper.updateRealIdByFirstBorrowId(firstBorrowId);
	}

	
	public List<FirstTenderDetailVo> queryListByCnd(FirstTenderDetailCnd firstTenderDetailCnd) throws Exception {
		return firstTenderDetailMapper.queryFirstTenderDetailList(firstTenderDetailCnd);
	}

	
	public List<AccountVo> queryAccountListForUpdateByFirstId(Integer firstBorrowId) {
		return firstTenderDetailMapper.queryAccountListForUpdateByFirstId(firstBorrowId);
	}
}
