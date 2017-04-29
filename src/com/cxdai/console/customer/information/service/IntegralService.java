package com.cxdai.console.customer.information.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.Constants;
import com.cxdai.console.common.Dictionary;
import com.cxdai.console.customer.information.entity.Integral;
import com.cxdai.console.customer.information.mapper.IntegralMapper;
import com.cxdai.console.customer.information.vo.IntegralCnd;
import com.cxdai.console.customer.information.vo.IntegralVo;
import com.cxdai.console.system.entity.Configuration;

/**
 * <p>
 * Description:积分等级接口的实现类<br />
 * </p>
 * 
 * @title IntegralServiceImpl.java
 * @package com.cxdai.console.member.service.impl
 * @author justin.xu
 * @version 0.1 2014年5月6日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class IntegralService {


	@Autowired
	private IntegralMapper integralMapper;

	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public IntegralVo queryIntegralByUserId(Integer memberId) throws Exception {
		IntegralCnd integralCnd = new IntegralCnd();
		integralCnd.setUserId(memberId);
		List<IntegralVo> list = integralMapper.queryIntegralList(integralCnd);
		if (null != list && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}


	public void updateCreditLevel(IntegralVo integralVo) throws Exception {
		Collection<Configuration> investList = Dictionary.getValues(Constants.CONFIGURATION_TYPE_INVEST_LEVEL); // 投资等级
		Collection<Configuration> creditList = Dictionary.getValues(Constants.CONFIGURATION_TYPE_CREDIT_LEVEL);// 信用等级
		// 设置投资等级
		for (Configuration con : investList) {
			long begin = Long.parseLong(con.getValue().split("-")[0]);
			long end = Long.parseLong(con.getValue().split("-")[1]);
			if (integralVo.getInvestIntegral() < end && integralVo.getInvestIntegral() > begin) {
				integralVo.setInvestLevel(con.getName());
			}
		}
		// 设置信用等级
		for (Configuration con : creditList) {
			long begin = Long.parseLong(con.getValue().split("-")[0]);
			long end = Long.parseLong(con.getValue().split("-")[1]);
			if (integralVo.getCreditIntegral() < end && integralVo.getCreditIntegral() > begin) {
				integralVo.setCreditLevel(con.getName());
			}
		}
		Integral integral = new Integral();
		BeanUtils.copyProperties(integralVo, integral);
		integralMapper.updateEntity(integral);
	}

}
