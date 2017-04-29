package com.cxdai.console.finance.virement.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cxdai.console.finance.virement.entity.AccountBalance;
import com.cxdai.console.finance.virement.entity.IsInvalid;
import com.cxdai.console.finance.virement.mapper.AccountBalanceMapper;
import com.cxdai.console.finance.virement.vo.AccountBalanceResponseCnd;
import com.cxdai.console.util.StringUtils;

@Service
public class AccountBalanceService {

	@Autowired
	private AccountBalanceMapper checkRechargeMapper;
	
	/**
	 * 
	 * <p>
	 * Description:账户余额信息查询<br />
	 * </p>
	 * @author xiaofei.li
	 * @version 0.1 2016-4-23
	 * @param date
	 * @return
	 * List<AccountBalanceResponseCnd>
	 */
	public List<AccountBalanceResponseCnd> queryAccountInfo(String date,String bankType){
		date = date+":00";
		if(!StringUtils.isNotEmpty(date)){
			Calendar cal =   Calendar.getInstance();
			cal.add(Calendar.DATE,   -1);
			date = new SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime())+" 17:00:00";
		}
		return checkRechargeMapper.queryAccountInfo(date,bankType);
	}
	
	public List<AccountBalanceResponseCnd> queryAccountInfoOther(String date){
		date = date+":00";
		if(!StringUtils.isNotEmpty(date)){
			Calendar cal =   Calendar.getInstance();
			cal.add(Calendar.DATE,   -1);
			date = new SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime())+" 17:00:00";
		}
		return checkRechargeMapper.queryAccountInfoOther(date);
	}
	
	/**
	 * 
	 * <p>
	 * Description:保存银行卡余额信息<br />
	 * </p>
	 * @author xiaofei.li
	 * @version 0.1 2016-4-25
	 * @param record
	 * @return
	 * int
	 */
	public int saveBalance(AccountBalance record){
		if(record.getId() != null){
			return checkRechargeMapper.updateByPrimaryKeySelective(record);
		}else{
			record.setStatus(IsInvalid.TAKEEFFECT.getCode());
			return checkRechargeMapper.insert(record);
		}
	}
	
	/**
	 * 
	 * <p>
	 * Description:主键获取数据信息<br />
	 * </p>
	 * @author xiaofei.li
	 * @version 0.1 2016-4-26
	 * @param id
	 * @return
	 * AccountBalance
	 */
	public AccountBalance selectByPrimaryKey(Integer id){
		return checkRechargeMapper.selectByPrimaryKey(id);
	}

	
	public int deleteBalance(AccountBalance record){
		record.setStatus(IsInvalid.INVALID.getCode());
		return checkRechargeMapper.updateByPrimaryKeySelective(record);
	}
}
