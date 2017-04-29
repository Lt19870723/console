package com.cxdai.console.customer.information.mapper;

import java.util.List;
import java.util.Map;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.information.vo.BankApproCnd;
import com.cxdai.console.customer.information.vo.BankApproVo;

/**
 * @author WangQianJin
 * @title 银行卡审核信息
 * @date 2016年6月7日
 */
public interface BankApproMapper {

	public Integer queryBankApproCount(BankApproCnd bankApproCnd) throws Exception;
	
	public List<BankApproVo> queryBankApproList(BankApproCnd bankApproCnd, Page page) throws Exception;
	
	public BankApproVo queryById(Integer id) throws Exception;
	
	public int updateBankInfo(BankApproVo bankAppro) throws Exception;
	
	public int updateMemberById(Map map) throws Exception;
	
}
