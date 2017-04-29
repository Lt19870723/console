package com.cxdai.console.customer.information.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.Constants;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.customer.information.entity.OnlineCounter;
import com.cxdai.console.customer.information.mapper.OnlineCounterMapper;
import com.cxdai.console.customer.information.vo.LoginCnd;
import com.cxdai.console.customer.information.vo.LoginRecordCnd;
import com.cxdai.console.customer.information.vo.OnlineCounterVo;
import com.cxdai.console.util.BaiDuIp;
import com.cxdai.console.util.BaiDuIp.IpAddr;

/**
 * <p>
 * Description:登录业务实现类<br />
 * </p>
 * 
 * @title LoginServiceImpl.java
 * @package com.cxdai.console.member.service.impl
 * @author justin.xu
 * @version 0.1 2014年11月14日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class LoginService {

	@Autowired
	private OnlineCounterMapper onlineCounterMapper;

	public String saveLogin(LoginCnd loginCnd) throws Exception {
		// 录登录信息
		OnlineCounter onlineCounter = new OnlineCounter();
		IpAddr ipAddr = BaiDuIp.queryAddrByIp(loginCnd.getIp()); // 调用百度api获得位置
		onlineCounter.setUserId(loginCnd.getUserId());
		onlineCounter.setUsername(loginCnd.getUserName());
		onlineCounter.setSessionid(loginCnd.getSessionId());
		onlineCounter.setAddip(loginCnd.getIp());
		onlineCounter.setSystem(Constants.ONLINE_COUNTER_SYSTEM_CONSOLE);
		onlineCounter.setArea(ipAddr.getArea());
		onlineCounter.setProvince(ipAddr.getProvince());
		onlineCounter.setCity(ipAddr.getCity());
		onlineCounter.setPlatform(loginCnd.getPlatform());
		onlineCounterMapper.insert(onlineCounter);

		return BusinessConstants.SUCCESS;
	}

	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public Page queryMemberLoginRecordWithPage(LoginRecordCnd loginRecordCnd, int pageSize, int pageNo) {

		Page page = new Page(pageNo, pageSize);
		int totalCount = onlineCounterMapper.countMemberLoginRecord(loginRecordCnd);
		page.setTotalCount(totalCount);
		List<OnlineCounterVo> list = onlineCounterMapper.queryMemberLoginRecordWithPage(loginRecordCnd, page);
		page.setResult(list);
		return page;
	}

}
