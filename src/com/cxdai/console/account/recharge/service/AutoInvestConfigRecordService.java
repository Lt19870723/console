package com.cxdai.console.account.recharge.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.account.recharge.mapper.AutoInvestConfigRecordMapper;
import com.cxdai.console.account.recharge.vo.AutoInvestConfigRecordCnd;
import com.cxdai.console.account.recharge.vo.AutoInvestConfigRecordVo;
import com.cxdai.console.account.recharge.vo.AutoInvestConfigVo;
import com.cxdai.console.base.entity.AutoInvestConfig;
import com.cxdai.console.base.entity.AutoInvestConfigRecord;
import com.cxdai.console.common.page.Page;

/**
 * 
 * <p>
 * Description:自动投标规则日志业务接口<br />
 * </p>
 * 
 * @title AutoInvestConfigRecordService.java
 * @package com.cxdai.console.account.service
 * @author yangshijin
 * @version 0.1 2014年11月28日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class AutoInvestConfigRecordService {
	@Autowired
	private AutoInvestConfigRecordMapper autoInvestConfigRecordMapper;
	/**
	 * 
	 * <p>
	 * Description:自动投标规则日志记录列表分页<br />
	 * </p>s
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年11月28日
	 * @param autoInvestConfigRecordCnd
	 * @param curPage
	 * @param pageSize
	 * @return
	 * @throws Exception Page
	 */
	public Page queryListForPage(AutoInvestConfigRecordCnd autoInvestConfigRecordCnd, int curPage, int pageSize) throws Exception{
		Page page = new Page(curPage, pageSize);
		Integer totalCount = autoInvestConfigRecordMapper.queryAutoInvestConfigRecordCount(autoInvestConfigRecordCnd);
		page.setTotalCount(totalCount);
		List<AutoInvestConfigRecordVo> list = autoInvestConfigRecordMapper.queryAutoInvestConfigRecordListForPage(autoInvestConfigRecordCnd, page);
		page.setResult(list);
		return page;
	}

	/**
	 * 
	 * <p>
	 * Description:根据id查询自动投标规则记录 <br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年11月28日
	 * @param id
	 * @return
	 * @throws Exception AutoInvestConfigRecordVo
	 */
	public AutoInvestConfigRecordVo queryById(int id) throws Exception{
		return autoInvestConfigRecordMapper.queryById(id);
	}

	/**
	 * 
	 * <p>
	 * Description:初始化自动投标规则日志信息.<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年5月20日
	 * @param autoInvestConfig
	 * @return AutoInvestConfigRecord
	 */
	public AutoInvestConfigRecord setAutoInvestConfigRecord(AutoInvestConfigVo autoInvestConfigVo){

		
		
		
		AutoInvestConfigRecord autoInvestConfigRecord = new AutoInvestConfigRecord();
		if (autoInvestConfigVo.getId() != null) {
			autoInvestConfigRecord.setAuto_tender_id(autoInvestConfigVo.getId());
		}
		autoInvestConfigRecord.setUser_id(autoInvestConfigVo.getUser_id());
		autoInvestConfigRecord.setStatus(autoInvestConfigVo.getStatus());
		autoInvestConfigRecord.setTender_type(autoInvestConfigVo.getTender_type());
		autoInvestConfigRecord.setBorrow_type(autoInvestConfigVo.getBorrow_type());
		autoInvestConfigRecord.setTimelimit_status(autoInvestConfigVo.getTimelimit_status());
		autoInvestConfigRecord.setMin_time_limit(autoInvestConfigVo.getMin_time_limit());
		autoInvestConfigRecord.setMax_time_limit(autoInvestConfigVo.getMax_time_limit());
		autoInvestConfigRecord.setMin_day_limit(autoInvestConfigVo.getMin_day_limit());
		autoInvestConfigRecord.setMax_day_limit(autoInvestConfigVo.getMax_day_limit());
		autoInvestConfigRecord.setMin_apr(autoInvestConfigVo.getMin_apr());
		autoInvestConfigRecord.setMax_apr(autoInvestConfigVo.getMax_apr());
		autoInvestConfigRecord.setMin_tender_account(autoInvestConfigVo.getMin_tender_account());
		autoInvestConfigRecord.setTender_account_auto(autoInvestConfigVo.getTender_account_auto());
		autoInvestConfigRecord.setTender_scale(autoInvestConfigVo.getTender_scale());
		autoInvestConfigRecord.setAward_flag(autoInvestConfigVo.getAward_flag());
		autoInvestConfigRecord.setBalance_not_enough(autoInvestConfigVo.getBalance_not_enough());
		autoInvestConfigRecord.setSettime(autoInvestConfigVo.getSettime());
		autoInvestConfigRecord.setSetip(autoInvestConfigVo.getSetip());
		autoInvestConfigRecord.setBorrow_type1_status(autoInvestConfigVo.getBorrow_type1_status());
		autoInvestConfigRecord.setBorrow_type2_status(autoInvestConfigVo.getBorrow_type2_status());
		autoInvestConfigRecord.setBorrow_type3_status(autoInvestConfigVo.getBorrow_type3_status());
		autoInvestConfigRecord.setBorrow_type4_status(autoInvestConfigVo.getBorrow_type4_status());
		autoInvestConfigRecord.setUptime(autoInvestConfigVo.getUptime());
		autoInvestConfigRecord.setRemark(autoInvestConfigVo.getRemark());
		autoInvestConfigRecord.setBorrow_type5_status(autoInvestConfigVo.getBorrow_type5_status());
		autoInvestConfigRecord.setAutoType(autoInvestConfigVo.getAutoType());
		autoInvestConfigRecord.setVipLevel(autoInvestConfigVo.getVipLevel());
		return autoInvestConfigRecord;
	}

	/**
	 * 
	 * <p>
	 * Description:初始化自动投标规则日志信息.<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年5月20日
	 * @param autoInvestConfig
	 * @return AutoInvestConfigRecord
	 */
	public AutoInvestConfigRecord setAutoInvestConfigRecord(AutoInvestConfig autoInvestConfig){
		AutoInvestConfigRecord autoInvestConfigRecord = new AutoInvestConfigRecord();
		if (autoInvestConfig.getId() != null) {
			autoInvestConfigRecord.setAuto_tender_id(autoInvestConfig.getId());
		}
		autoInvestConfigRecord.setUser_id(autoInvestConfig.getUser_id());
		autoInvestConfigRecord.setStatus(autoInvestConfig.getStatus());
		autoInvestConfigRecord.setTender_type(autoInvestConfig.getTender_type());
		autoInvestConfigRecord.setBorrow_type(autoInvestConfig.getBorrow_type());
		autoInvestConfigRecord.setTimelimit_status(autoInvestConfig.getTimelimit_status());
		autoInvestConfigRecord.setMin_time_limit(autoInvestConfig.getMin_time_limit());
		autoInvestConfigRecord.setMax_time_limit(autoInvestConfig.getMax_time_limit());
		autoInvestConfigRecord.setMin_day_limit(autoInvestConfig.getMin_day_limit());
		autoInvestConfigRecord.setMax_day_limit(autoInvestConfig.getMax_day_limit());
		autoInvestConfigRecord.setMin_apr(autoInvestConfig.getMin_apr());
		autoInvestConfigRecord.setMax_apr(autoInvestConfig.getMax_apr());
		autoInvestConfigRecord.setMin_tender_account(autoInvestConfig.getMin_tender_account());
		autoInvestConfigRecord.setTender_account_auto(autoInvestConfig.getTender_account_auto());
		autoInvestConfigRecord.setTender_scale(autoInvestConfig.getTender_scale());
		autoInvestConfigRecord.setAward_flag(autoInvestConfig.getAward_flag());
		autoInvestConfigRecord.setBalance_not_enough(autoInvestConfig.getBalance_not_enough());
		autoInvestConfigRecord.setSettime(autoInvestConfig.getSettime());
		autoInvestConfigRecord.setSetip(autoInvestConfig.getSetip());
		autoInvestConfigRecord.setBorrow_type1_status(autoInvestConfig.getBorrow_type1_status());
		autoInvestConfigRecord.setBorrow_type2_status(autoInvestConfig.getBorrow_type2_status());
		autoInvestConfigRecord.setBorrow_type3_status(autoInvestConfig.getBorrow_type3_status());
		autoInvestConfigRecord.setBorrow_type4_status(autoInvestConfig.getBorrow_type4_status());
		autoInvestConfigRecord.setUptime(autoInvestConfig.getUptime());
		autoInvestConfigRecord.setRemark(autoInvestConfig.getRemark());
		autoInvestConfigRecord.setBorrow_type5_status(autoInvestConfig.getBorrow_type5_status());
		autoInvestConfigRecord.setAutoType(autoInvestConfig.getAutoType());
		autoInvestConfigRecord.setVipLevel(autoInvestConfig.getVipLevel());
		return autoInvestConfigRecord;
		
	}
}
