package com.cxdai.console.firstborrow.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.base.entity.FirstTransferApproved;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.firstborrow.mapper.FirstSubscribeMapper;
import com.cxdai.console.firstborrow.vo.FirstSubscribeVo;
import com.cxdai.console.security.ShiroUser;

/**
 * <p>
 * Description:直通车转让认购业务实现类<br />
 * </p>
 * 
 * @title FirstSubscribeServiceImpl.java
 * @package com.cxdai.portal.first.service.impl
 * @author 朱泳霖
 * @version 0.1 2015年3月19日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class FirstSubscribeService{

	Logger logger = LoggerFactory.getLogger(FirstSubscribeService.class);

	@Autowired
	private FirstSubscribeMapper firstSubscribeMapper;

	
	public List<FirstSubscribeVo> querySubscribeListByTransferId(Integer transferId) throws Exception {
		return firstSubscribeMapper.querySubscribeListByTransferId(transferId);
	}

	
	public String saveTransferRecheck(ShiroUser user, FirstTransferApproved firstTransferApproved, String ip) throws Exception {
		// 认购成功标志
		String result = BusinessConstants.SUCCESS;
		List<FirstSubscribeVo> list = firstSubscribeMapper.queryTransferRecheckSubscribe(firstTransferApproved.getFirstTransferId());
		if(list!=null&&list.size()>0){
			// 调用自动复审存储过程
			Map<String, Object> params = new HashMap<String, Object>();
			// 直通车转让Id
			params.put("transferId", firstTransferApproved.getFirstTransferId());
			// 用户ID
			params.put("userId", user.getUserId());
			// 用户名
			params.put("userName", user.getUserName());
			// IP地址
			params.put("addip", ip);
			// 审核备注
			params.put("checkRemark", firstTransferApproved.getVerifyRemark());
			// 平台来源
			params.put("platform", 1);
			firstSubscribeMapper.saveTransferRecheck(params);
			// 存储过程返回参数
			String msg = params.get("msg").toString();
			if ("00001".equals(msg)) {
				throw new Exception("直通车转让存储过程,债权价格不正确,请重新操作");
			} else if ("00002".equals(msg)) {
				throw new Exception("直通车转让存储过程,转让价格和认购价格不相等,请重新操作");
			} else if ("00003".equals(msg)) {
				throw new Exception("直通车转让存储过程,直通车转让状态不正确,请重新操作");
			} else if ("00004".equals(msg)) {
				throw new Exception("直通车转让存储过程,借款标待收总额和预还金额总额不相等,请重新操作");
			}
		}
		return result;
	}
   
}
