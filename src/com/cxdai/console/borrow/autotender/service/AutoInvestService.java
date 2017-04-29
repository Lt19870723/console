package com.cxdai.console.borrow.autotender.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.account.recharge.mapper.AutoInvestConfigMapper;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.util.exception.AppException;
/**
 * 
 * <p>
 * Description:自动投标业务处理方法<br />
 * </p>
 * @title AutoInvestService.java
 * @package com.cxdai.console.borrow.autotender.service 
 * @author Administrator
 * @version 0.1 2015年6月25日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class AutoInvestService {
	public Logger logger = Logger.getLogger(AutoInvestService.class);

	@Autowired
	private AutoInvestConfigMapper autoInvestConfigMapper;

	public String saveAutoTenderBorrow(int borrow_id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("borrowid", borrow_id);
		map.put("uptime", 1);
		autoInvestConfigMapper.autoTender(map);
		String msg = map.get("msg").toString();
		if (!"0001".equals(msg)) {
			throw new AppException("自动投标出错");
		}
		return BusinessConstants.SUCCESS;
	}
}
