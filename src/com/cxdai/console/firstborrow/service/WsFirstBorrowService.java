package com.cxdai.console.firstborrow.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.util.WebServiceMD5;

/**
 * <p>
 * Description:直通车信息业务类实现WebService<br />
 * </p>
 * 
 * @title WsBorrowReportServiceImpl.java
 * @package com.cxdai.portal.webservice.borrowreport.service.impl
 * @author justin.xu
 * @version 0.1 2014年6月26 日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class WsFirstBorrowService{

	@Autowired
	private FirstBorrowService firstBorrowService;
	@Autowired
	private FirstTenderRealService firstTenderRealService;

	
	public String saveTenderFirstManualApprove(Integer firstBorrowId, String ip, String serviceKey) throws Exception {
		// 验证数据是否正确
		Map<String, Object> validateKeyMap = new HashMap<>();
		validateKeyMap.put("firstBorrowId", firstBorrowId);
		validateKeyMap.put("ip", ip);
		if (!WebServiceMD5.validateServiceKey(validateKeyMap, serviceKey)) {
			return "数据验签出错";
		}
		return firstBorrowService.saveTenderFirstAutoApprove(firstBorrowId, ip);
	}

	
	public String saveUnlockManualApprove(Integer firstTenderRealId, Integer memberId, String ip, Integer approveUserId, String approveRemark, String serviceKey) throws Exception {
		// 验证数据是否正确
		Map<String, Object> validateKeyMap = new HashMap<>();
		validateKeyMap.put("firstTenderRealId", firstTenderRealId);
		validateKeyMap.put("memberId", memberId);
		validateKeyMap.put("ip", ip);
		validateKeyMap.put("approveUserId", approveUserId);
		validateKeyMap.put("approveRemark", approveRemark);
		if (!WebServiceMD5.validateServiceKey(validateKeyMap, serviceKey)) {
			return "数据验签出错";
		}
		return firstTenderRealService.saveUnLockApprove(firstTenderRealId, memberId, ip, approveUserId, approveRemark);
	}

	
	public String saveUnLockManual(Integer firstTenderRealId, Integer memberId, String serviceKey) throws Exception {
		// 验证数据是否正确
		Map<String, Object> validateKeyMap = new HashMap<>();
		validateKeyMap.put("firstTenderRealId", firstTenderRealId);
		validateKeyMap.put("memberId", memberId);
		if (!WebServiceMD5.validateServiceKey(validateKeyMap, serviceKey)) {
			return "数据验签出错";
		}
		// 执行申请解锁
		return firstTenderRealService.saveUnLock(firstTenderRealId, memberId);
	}
}
