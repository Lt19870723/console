package com.cxdai.console.customer.bankcard.mapper;

import java.util.List;

import com.cxdai.console.customer.bankcard.entity.BankcardClick;
import com.cxdai.console.customer.bankcard.entity.BankcardClickCnd;

public interface BankcardClickMapper {

	public List<BankcardClick> getClicksByUserId(BankcardClickCnd clickCnd) throws Exception;

}
