package com.cxdai.console.finance.virement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.finance.virement.entity.BankAccountInfo;
import com.cxdai.console.finance.virement.mapper.BankAccountInfoMapper;

/***
 * 银行卡Service
 * 
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class BankAccountService {

	@Autowired
	private BankAccountInfoMapper bankAccountInfoMapper;


	/****
	 * 查询账户银行卡信息
	 * @return
	 */
	public List<BankAccountInfo> selectAll() {
		
		List<BankAccountInfo> list = bankAccountInfoMapper.selectAll();
		return list;
	}
	/**
	 * 根据id查询银行卡信息
	 * <p>
	 * Description:这里写描述<br />
	 * BankAccountInfo
	 */
   public BankAccountInfo selectById(Integer id) {		
		BankAccountInfo bankinfo = bankAccountInfoMapper.selectByPrimaryKey(id);
		return bankinfo;
	}
	
}
