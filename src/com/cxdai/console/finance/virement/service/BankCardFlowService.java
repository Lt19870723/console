package com.cxdai.console.finance.virement.service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.finance.virement.entity.AccountFlow;
import com.cxdai.console.finance.virement.entity.FlowType;
import com.cxdai.console.finance.virement.entity.IsInvalid;
import com.cxdai.console.finance.virement.mapper.AccountFlowMapper;
import com.cxdai.console.finance.virement.vo.AccountFlowResponse;
import com.cxdai.console.finance.virement.vo.QueryPageCheckWithCnd;
import com.cxdai.console.system.entity.Configuration;
import com.cxdai.console.system.mapper.ConfigurationMapper;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.StringUtils;

@Service
public class BankCardFlowService {

	@Autowired
	private AccountFlowMapper accountFlowMapper;
	
	@Autowired 
	private ConfigurationMapper configurationMapper;
	
	public Page queryPageList(QueryPageCheckWithCnd request, Integer curPage, Integer pageSize) {
		Page page = new Page(curPage, pageSize);
		if (StringUtils.isNotEmpty(request.getStartDate())) {
			request.setStartDate(request.getStartDate() + " 00:00:00");
		}
		if (StringUtils.isNotEmpty(request.getEndDate())) {
			request.setEndDate(request.getEndDate() + " 23:59:59");
		}
		Integer count = accountFlowMapper.queryPageCount(request);
		page.setTotalCount(count);
		List<AccountFlow> list = accountFlowMapper.queryPageList(request, page);
		for(AccountFlow record : list){
			 int days= DateUtils.dayDiff(record.getEndTime(),new Date());
			 record.setOptType(days);
		}
		page.setResult(list);
		return page;
	}
	
	/**
	 * 
	 * <p>
	 * Description:保存银行卡流水信息<br />
	 * </p>
	 * @author xiaofei.li
	 * @version 0.1 2016-4-26
	 * @param record
	 * @return
	 * int
	 */
	public int saveFlow(AccountFlow record){
		//record.setEndTime(DateUtils.parse(record.getReqEndTime()+":00", DateUtils.YMD_HMS));
		
		if(StringUtils.isNotEmpty(record.getBankCard())){
			String cardNo = record.getBankCard();
			record.setBankCard(cardNo.substring(cardNo.length()-4,cardNo.length())); 
		}
		if(record.getId() != null){
			return accountFlowMapper.updateByPrimaryKeySelective(record);
		}else{
			record.setStatus(IsInvalid.TAKEEFFECT.getCode());
			return accountFlowMapper.insert(record);
		}
		
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:主键获取实体信息<br />
	 * </p>
	 * @author xiaofei.li
	 * @version 0.1 2016-4-27
	 * @param id
	 * @return
	 * AccountFlow
	 */
	public AccountFlow queryById(Integer id){
		return accountFlowMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 
	 * <p>
	 * Description:作废流水信息<br />
	 * </p>
	 * @author xiaofei.li
	 * @version 0.1 2016-4-27
	 * @param record
	 * @return
	 * int
	 */
	public int deleteFlow(AccountFlow record){
		record.setStatus(IsInvalid.INVALID.getCode());
		return accountFlowMapper.updateByPrimaryKeySelective(record);
	}
	/**
	 * 
	 * <p>
	 * Description:保存新增类别数据字典表<br />
	 * </p>
	 * @author xiaofei.li
	 * @version 0.1 2016-4-27
	 * @param record
	 * @return
	 * int
	 * @throws UnsupportedEncodingException 
	 */
	public int saveType(Configuration record) throws UnsupportedEncodingException{
		int id = configurationMapper.queeyMaxId()+1;
		record.setId(id);
		String val = java.net.URLDecoder.decode(record.getValue(), "UTF-8");
		record.setValue(val);
		return configurationMapper.insert(record);
	}
	
	/**
	 * 
	 * <p>
	 * Description:查询某日收入支出汇总情况<br />
	 * </p>
	 * @author xiaofei.li
	 * @version 0.1 2016-4-27
	 * @param date
	 * @return
	 * List<AccountFlowResponse>
	 */
	public List<AccountFlowResponse> export(String date){
		QueryPageCheckWithCnd request = new QueryPageCheckWithCnd();
		request.setStartDate(date+" 00:00:00");
		request.setEndDate(date+"23:59:59");
		request.setIsSuccess(FlowType.ICOME.getCode());
		List<AccountFlowResponse> recordIcList = accountFlowMapper.queryByDate(request);
		request.setIsSuccess(FlowType.EXPURTE.getCode());
		List<AccountFlowResponse> recordExList = accountFlowMapper.queryByDate(request);
		if(recordExList != null){
			recordIcList.addAll(recordExList);
		}
		 //汇总收入支出总额
		 BigDecimal icMoney= BigDecimal.ZERO;
		 BigDecimal exMoney = BigDecimal.ZERO;
		 if(recordIcList.size() > 0){
		 for(AccountFlowResponse  record :recordIcList){
			if( FlowType.ICOME.getCode() == record.getMoneyType()){
				icMoney = icMoney.add(record.getMoney());
			}
			if( FlowType.EXPURTE.getCode() == record.getMoneyType()){
				exMoney = exMoney.add(record.getMoney());
			}
		 }
		 AccountFlowResponse response1= new AccountFlowResponse();
		 response1.setMoneyType(3);
		 response1.setMoney(icMoney);
		 recordIcList.add(response1);
		 AccountFlowResponse response= new AccountFlowResponse();
		 response.setMoneyType(4);
		 response.setMoney(exMoney);
		 recordIcList.add(response);
		 }
		 return recordIcList;
	}
}
