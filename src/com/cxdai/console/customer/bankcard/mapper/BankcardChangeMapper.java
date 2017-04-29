package com.cxdai.console.customer.bankcard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.bankcard.entity.BankcardChange;
import com.cxdai.console.customer.bankcard.entity.BankcardChangeCnd;

public interface BankcardChangeMapper {

	public int pageQueryCount(BankcardChangeCnd changeCnd) throws Exception;

	public List<BankcardChange> pageQuery(BankcardChangeCnd changeCnd, Page page) throws Exception;

	public BankcardChange getById(int id) throws Exception;

	public void updateBankcardChange(BankcardChange change) throws Exception;

	public void updateBankinfo(BankcardChange change) throws Exception;

	public void updateBankinfoStatus(@Param(value = "status") Integer status, @Param(value = "userId") Integer userId) throws Exception;

	public String getUserCurrentCardNoAgree(int userId) throws Exception;

	public void updateRecheckBankcardChange(BankcardChange change) throws Exception;
}
