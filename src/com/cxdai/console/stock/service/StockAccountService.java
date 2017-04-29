package com.cxdai.console.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cxdai.console.stock.entity.StockAccount;
import com.cxdai.console.stock.mapper.StockAccountMapper;
import com.cxdai.console.stock.vo.StockAccountRequest;
import com.cxdai.console.util.StringUtils;

@Service
public class StockAccountService {
	@Autowired
	private StockAccountMapper stockAccountMapper;
	
	public StockAccount selectAccountForUpdate(Integer userId, String forUpdate){
		StockAccountRequest request = new StockAccountRequest();
		request.setUserId(userId);
		if(StringUtils.isNotEmpty(forUpdate)){
			request.setIsForUpdate(1);
		}
		return stockAccountMapper.selectByPrimaryCnd(request);
	}
}
