package com.cxdai.console.account.risk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.account.risk.entity.RiskMargin;
import com.cxdai.console.account.risk.entity.RiskMarginLog;
import com.cxdai.console.account.risk.mapper.BaseRiskMarginLogMapper;
import com.cxdai.console.account.risk.mapper.BaseRiskMarginMapper;
import com.cxdai.console.system.vo.UserVo;
import com.cxdai.console.util.DateUtils;

/**
 * 
 * <p>
 * Description:风险备用金业务实现类<br />
 * </p>
 * @title RiskMarginServiceImpl.java
 * @package com.cxdai.console.account.service.impl 
 * @author yangshijin
 * @version 0.1 2014年7月3日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class RiskMarginService{

	@Autowired
	private BaseRiskMarginMapper baseRiskMarginMapper;
	@Autowired
	private BaseRiskMarginLogMapper baseRiskMarginLogMapper;

	public RiskMargin queryRiskMargin() throws Exception {
		return baseRiskMarginMapper.queryRiskMargin();
	}

	public String updateRiskMargin(RiskMargin riskMargin, UserVo userVo, String ip) throws Exception {
		if(riskMargin != null && riskMargin.getId() != null){
			RiskMargin riskMargin_old = baseRiskMarginMapper.queryById(riskMargin.getId());
			if(baseRiskMarginMapper.updateEntity(riskMargin) >0){
				//保存修改日志
				RiskMarginLog riskMarginLog = new RiskMarginLog();
				riskMarginLog.setAccount(riskMargin_old.getAccount());
				riskMarginLog.setStaffId(userVo.getId());
				riskMarginLog.setAddTime(DateUtils.getCurrentTimeStamp());
				riskMarginLog.setAddIp(ip);
				baseRiskMarginLogMapper.insertEntity(riskMarginLog);
				return "修改成功";
			}else {
				return "修改失败";
			}
		}
		return "该记录不存在";
	}

}
