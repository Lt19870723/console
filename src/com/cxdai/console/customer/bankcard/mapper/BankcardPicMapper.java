package com.cxdai.console.customer.bankcard.mapper;

import java.util.List;

import com.cxdai.console.customer.bankcard.entity.BankcardPic;

public interface BankcardPicMapper {

	public List<BankcardPic> getPicsByBcId(int bcId) throws Exception;

}
