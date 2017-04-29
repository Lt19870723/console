package com.cxdai.console.customer.bankcard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cxdai.console.customer.bankcard.entity.Bank;
import com.cxdai.console.customer.bankcard.vo.BankInfoVo;
import com.cxdai.console.customer.bankcard.vo.UserBindBankCnd;



public interface UserBindBankMapper {

	int countCardByCardNum(String cardNum);

	void insertEntity(BankInfoVo bankInfoVo);

	void deleteBankinfoById(Integer id);
	
	int queryCountPageBanksByCnd(UserBindBankCnd bindBankCnd);

	List<Bank> queryProvinceList();

	List<Bank> queryCityList(String province);

	List<Bank> queryDistrictList(String city);

	List<Bank> queryBankList(String district);

	List<Bank> queryBranchListLike(String district, String bankCode, String branchKey);

	List<BankInfoVo> queryBanksByCnd(UserBindBankCnd bindBankCnd);

	Integer queryCardCountByUserId(Integer userId);

	List<Bank> queryBankInfosByCnapsCode(Long cnapsCode);

	/**
	 * 
	 * <p>
	 * Description:通过userId删除指定用户所有的银行卡信息<br />
	 * </p>
	 * @author 胡建盼
	 * @version 0.1 2014年12月5日
	 * @return
	 * @throws Exception
	 * Integer
	 */
	public Integer deleteBankinfoByUserId(@Param("userId")Integer userId) throws Exception;
}
