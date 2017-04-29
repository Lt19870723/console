package com.cxdai.console.customer.bankcard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cxdai.console.customer.bankcard.entity.BankInfoCnd;
import com.cxdai.console.customer.bankcard.vo.BankInfoVo;

/**
 * <p>
 * Description:银行信息数据访问类<br />
 * </p>
 * 
 * @title BankInfoMapper.java
 * @package com.cxdai.console.lianlian.mapper
 * @author justin.xu
 * @version 0.1 2015年4月8日
 */
public interface BankInfoMapper {
	/**
	 * <p>
	 * Description:根据条件查询对象集合<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月23日
	 * @param bankInfoCnd
	 * @return
	 * @throws Exception List<BankInfoVo>
	 */
	public List<BankInfoVo> queryBankInfoList(BankInfoCnd bankInfoCnd);

	/**
	 * <p>
	 * Description:根据用户更新支付时的签约协议号<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2015年3月28日
	 * @param userId
	 * @return
	 * @throws Exception Integer
	 */
	public Integer updateNoAgreeByUserId(@Param("userId") Integer userId, @Param("noAgree") String noAgree) throws Exception;
}
