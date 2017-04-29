package com.cxdai.console.customer.bankcard.mapper;

import java.util.List;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.bankcard.entity.MemberBankCardInfoCnd;
import com.cxdai.console.customer.bankcard.entity.MemberBankCardInfoVo;


public interface MemberBankCardInfoMapper {


	public List<MemberBankCardInfoVo> queryListForPage(MemberBankCardInfoCnd memberBankCardInfoCnd, Page page) throws Exception;

	public Integer queryListCount(MemberBankCardInfoCnd memberBankCardInfoCnd) throws Exception;
}
