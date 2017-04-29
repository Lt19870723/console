package com.cxdai.console.finance.virement.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.finance.virement.vo.WithdrawalAnalysisCnd;
import com.cxdai.console.finance.virement.vo.WithdrawalAnalysisVo;

public interface WithdrawalAnalysisMapper {

	Integer queryListCountByCnd(WithdrawalAnalysisCnd rechargeAnalysisCnd);

	List<WithdrawalAnalysisVo> queryListByCnd(WithdrawalAnalysisCnd rechargeAnalysisCnd, Page page);

	/****
	 * 
	 * <p>
	 * Description:根据用户ID获取用户的资产总额<br />
	 * </p>
	 * 
	 * @author Kimmyzhao
	 * @version 0.1 2016年4月17日
	 * @param userId
	 * @return BigDecimal
	 */
	public BigDecimal getTotalAssetByUserId(@Param("userId") Integer userId);

	List<WithdrawalAnalysisVo> queryWithdrawalAnalysisList(WithdrawalAnalysisCnd withdrawalAnalysisCnd);

	/***
	 * 
	 * <p>
	 * Description:根据条件统计总的提现金额<br />
	 * </p>
	 * 
	 * @author Kimmyzhao
	 * @version 0.1 2016年4月22日
	 * @param withdrawalAnalysisCnd
	 * @return BigDecimal
	 */
	BigDecimal getTotalWithdrawalAccount(WithdrawalAnalysisCnd withdrawalAnalysisCnd);

	/***
	 * 
	 * <p>
	 * Description:根据条件统计正常提现的提现金额<br />
	 * </p>
	 * 
	 * @author Kimmyzhao
	 * @version 0.1 2016年4月22日
	 * @param withdrawalAnalysisCnd
	 * @return BigDecimal
	 */
	BigDecimal getTotalNomalWithdrawal(WithdrawalAnalysisCnd withdrawalAnalysisCnd);

	/***
	 * 
	 * <p>
	 * Description:根据条件统计撤资的提现金额<br />
	 * </p>
	 * 
	 * @author Kimmyzhao
	 * @version 0.1 2016年4月22日
	 * @param withdrawalAnalysisCnd
	 * @return BigDecimal
	 */
	BigDecimal getTotalDisinvestment(WithdrawalAnalysisCnd withdrawalAnalysisCnd);

	/***
	 * 
	 * <p>
	 * Description:根据条件统计撤资倾向的提现金额<br />
	 * </p>
	 * 
	 * @author Kimmyzhao
	 * @version 0.1 2016年4月22日
	 * @param withdrawalAnalysisCnd
	 * @return BigDecimal
	 */
	BigDecimal getTotalDivestmentTendency(WithdrawalAnalysisCnd withdrawalAnalysisCnd);
}
