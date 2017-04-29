package com.cxdai.console.statistics.account.mapper;

import com.cxdai.console.statistics.account.entity.UserLevelRatio;
import com.cxdai.console.statistics.account.entity.UserNetRepayMoneyTotal;
import com.cxdai.console.statistics.account.entity.UserNetValue;



public interface AccountNetValueMapper {

	/**
	 * 
	 * <p>
	 * Description:获取净值额度等信息<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年10月28日
	 * @param netValue
	 * @throws Exception void
	 */
	public void callGetUserNetMoneyLimit(UserNetValue netValue) throws Exception;

	/**
	 * <p>
	 * Description:可提金额大于净值额度时，将可提转入受限<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年10月23日
	 * @param <DrawMoneyToNoDrawCnd>
	 * @param drawMoneyToNoDrawCnd
	 * @throws Exception void
	 */
	public <DrawMoneyToNoDrawCnd> void dealDrawmoneyToNodraw(DrawMoneyToNoDrawCnd drawMoneyToNoDrawCnd) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:获取用户投标中的净值标预还总额和借款管理费<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年10月28日
	 * @param userNetRepayMoneyTotal
	 * @return
	 * @throws Exception UserNetRepayMoneyTotal
	 */
	public UserNetRepayMoneyTotal callGetUserNetRepayMoneyTotal(UserNetRepayMoneyTotal userNetRepayMoneyTotal) throws Exception;
	/**
	 * 
	 * <p>
	 * Description:获得用户会员等级和比率<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年12月18日
	 * @param userLevelRatio
	 * @throws Exception
	 *             void
	 */
	public void callGetUserLevelRatio(UserLevelRatio userLevelRatio) throws Exception;
}
