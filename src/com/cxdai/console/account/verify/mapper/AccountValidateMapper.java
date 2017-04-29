package com.cxdai.console.account.verify.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.statistics.account.vo.AccountValidateCnd;
import com.cxdai.console.statistics.account.vo.AccountVo;

/**
 * 
 * <p>
 * Description:资金验证<br />
 * </p>
 */
public interface AccountValidateMapper {

	/**
	 * 
	 * <p>
	 * Description:查询列表-验证账户总额<br />
	 * </p>
	 */
	List<AccountVo> queryPageAccountValidateListByCnd(@Param("accountValidateCnd") AccountValidateCnd accountValidateCnd, Page page);
	int queryCountAccountValidateListByCnd(@Param("accountValidateCnd") AccountValidateCnd accountValidateCnd);

	/**
	 * 
	 * <p>
	 * Description:待收表应收总额-列表<br />
	 * </p>
	 */
	List<?> queryPageCollectionValidateListByCnd(@Param("accountValidateCnd") AccountValidateCnd accountValidateCnd, Page page);
	int queryCountCollectionValidateListByCnd(@Param("accountValidateCnd") AccountValidateCnd accountValidateCnd);

	/**
	 * 
	 * <p>
	 * Description:待还表应还总额-列表<br />
	 * </p>
	 */
	List<?> queryPageRepaymentValidateListByCnd(@Param("accountValidateCnd") AccountValidateCnd accountValidateCnd, Page page);
	int queryCountRepaymentValidateListByCnd(@Param("accountValidateCnd") AccountValidateCnd accountValidateCnd);
	
	/**
	 * 
	 * <p>
	 * Description:查询列表-投标表应收总额<br />
	 * </p>
	 */
	List<?> queryPageTenderRepaymentAccountListByCnd(@Param("accountValidateCnd") AccountValidateCnd accountValidateCnd, Page page);
	int queryCountTenderRepaymentAccountListByCnd(@Param("accountValidateCnd") AccountValidateCnd accountValidateCnd);
	
	/**
	 * 
	 * <p>
	 * Description:查询列表-待收表垫付金额<br />
	 * </p>
	 */
	List<?> queryPageCollectionAdvanceYAListByCnd(@Param("accountValidateCnd") AccountValidateCnd accountValidateCnd, Page page);
	int queryCountCollectionAdvanceYAListByCnd(@Param("accountValidateCnd") AccountValidateCnd accountValidateCnd);
	
	/**
	 * 
	 * <p>
	 * Description:账户和账户日志-列表<br />
	 * </p>
	 */
	List<?> queryPageAccountAndLogListByCnd(@Param("accountValidateCnd") AccountValidateCnd accountValidateCnd, Page page);
	int queryCountAccountAndLogListByCnd(@Param("accountValidateCnd") AccountValidateCnd accountValidateCnd);
	
	/**
	 * 
	 * <p>
	 * Description:待收表和投标表的应收总额-列表<br />
	 * </p>
	 */
	List<?> queryPageTenderCollectionRepayAListByCnd(@Param("accountValidateCnd") AccountValidateCnd accountValidateCnd, Page page);
	int queryCountTenderCollectionRepayAListByCnd(@Param("accountValidateCnd") AccountValidateCnd accountValidateCnd);
	
	/**
	 * 
	 * <p>
	 * Description:待收、待还表中本金之和-列表<br />
	 * </p>
	 */
	List<?> queryPageCRSumCapitalToOtherListByCnd(@Param("accountValidateCnd") AccountValidateCnd accountValidateCnd, Page page);
	int queryCountCRSumCapitalToOtherListByCnd(@Param("accountValidateCnd") AccountValidateCnd accountValidateCnd);
	
	/**
	 * 
	 * <p>
	 * Description:待收、待还表中每期本金之和-列表<br />
	 * </p>
	 */
	List<?> queryPageCRSumCapitalListByCnd(@Param("accountValidateCnd") AccountValidateCnd accountValidateCnd, Page page);
	int queryCountCRSumCapitalListByCnd(@Param("accountValidateCnd") AccountValidateCnd accountValidateCnd);
	
	/**
	 * 
	 * <p>
	 * Description:待收表的每投标记录-列表<br />
	 * </p>
	 */
	List<?> queryPageCSumCapitalToTenderListByCnd(@Param("accountValidateCnd") AccountValidateCnd accountValidateCnd, Page page);
	int queryCountCSumCapitalToTenderListByCnd(@Param("accountValidateCnd") AccountValidateCnd accountValidateCnd);

}
