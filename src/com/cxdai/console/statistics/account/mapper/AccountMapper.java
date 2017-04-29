package com.cxdai.console.statistics.account.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Update;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.fix.vo.FixTenderDetailVo;
import com.cxdai.console.statistics.account.entity.Account;
import com.cxdai.console.statistics.account.vo.AccountCnd;
import com.cxdai.console.statistics.account.vo.AccountVo;

/**
 * <p>
 * Description:帐号数据访问类<br />
 * </p>
 * @title AccountMapper.java
 * @package com.cxdai.console.account.mapper
 * @author justin.xu
 * @version 0.1 2014年4月25日
 */
public interface AccountMapper {

	/**
	 * <p>
	 * Description:插入记录到帐号表,返回新增的主键ID<br />
	 * </p>
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param account
	 * @return
	 * @throws Exception int
	 */
	public int insertEntity(Account account) throws Exception;

	/**
	 * <p>
	 * Description:根据id查询<br />
	 * </p>
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param id
	 * @return
	 * @throws Exception Account
	 */
	public Account queryById(Integer id) throws Exception;

	/**
	 * <p>
	 * Description:更新对象<br />
	 * </p>
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param account
	 * @return
	 * @throws Exception int
	 */
	public int updateEntity(Account account) throws Exception;

	/**
	 * <p>
	 * Description:根据ID查询（用于更新账户记录时使用）<br />
	 * </p>
	 * @author yangshijin
	 * @version 0.1 2014年7月2日
	 * @param id
	 * @return
	 * @throws Exception Account
	 */
	public Account queryByUserIdForUpdate(Integer userId) throws Exception;

	/**
	 * <p>
	 * Description:根据用户ID查询对象并锁定记录<br />
	 * </p>
	 * @author justin.xu
	 * @version 0.1 2014年4月23日
	 * @param memberId
	 * @return AccountVo
	 */
	public AccountVo queryAccountByUserIdForUpdate(Integer memberId) throws Exception;

	public AccountVo queryAccountByUserId(Integer memberId) throws Exception;

	/**
	 * <p>
	 * Description:根据条件查询对象集合<br />
	 * </p>
	 * @author justin.xu
	 * @version 0.1 2014年4月23日
	 * @param accountCnd
	 * @return
	 * @throws Exception List<AccountVo>
	 */
	public List<AccountVo> queryAccountList(AccountCnd accountCnd) throws Exception;

	/**
	 * <p>
	 * Description:根据条件查询对象集合（分页）<br />
	 * </p>
	 * @author justin.xu
	 * @version 0.1 2014年4月23日
	 * @param accountCnd
	 * @return
	 * @throws Exception List<AccountVo>
	 */
	public List<AccountVo> queryAccountList(AccountCnd accountCnd, Page page) throws Exception;

	/**
	 * <p>
	 * Description:根据条件查询对象集合数量<br />
	 * </p>
	 * @author justin.xu
	 * @version 0.1 2014年4月23日
	 * @param UnusualAccountCnd accountCnd
	 * @return
	 * @throws Exception Integer
	 */
	public Integer queryAccountCount(AccountCnd accountCnd) throws Exception;

	/**
	 * <p>
	 * Description:统计平台数据（资产总额、可用余额、可提现金额、不可提现金额、冻结总额、待收金额、投标直通车余额）<br />
	 * </p>
	 * @author yangshijin
	 * @version 0.1 2014年7月7日
	 * @return
	 * @throws Exception Map<String,Object>
	 */
	public Map<String, Object> platformAccount() throws Exception;

	/**
	 * <p>
	 * Description: 统计平台数据（可用余额 、可用资金统计，其中账户总额小于50元的不统计)<br />
	 * </p>
	 * @author justin.xu
	 * @version 0.1 2014年7月7日
	 * @return BigDecimal
	 * @throws Exception
	 */
	public BigDecimal platformUserMoneyTotal() throws Exception;

	/**
	 * <p>
	 * Description:查询账户资金异常记录<br />
	 * </p>
	 * @author justin.xu
	 * @version 0.1 2014年4月23日
	 * @param accountCnd
	 * @return
	 * @throws Exception List<AccountVo>
	 */
	public List<AccountVo> queryAccountUnusualForPage(AccountCnd accountCnd, Page page) throws Exception;

	/**
	 * <p>
	 * Description:查询账户资金异常记录数量<br />
	 * </p>
	 * @author justin.xu
	 * @version 0.1 2014年4月23日
	 * @param UnusualAccountCnd accountCnd
	 * @return
	 * @throws Exception Integer
	 */
	public Integer queryAccountUnusualForCounts(AccountCnd accountCnd) throws Exception;

	/**
	 * 更新用户账户
	 * @author WangQianJin
	 * @title @param fixTenderDetailVo
	 * @title @throws Exception
	 * @date 2015年9月8日
	 */
	public void updateAccountByUserId(FixTenderDetailVo fixTenderDetailVo) throws Exception;

	@Update("update rocky_account set total = total + #{redMoney},use_money = use_money + #{redMoney},NO_DRAW_MONEY=NO_DRAW_MONEY + #{redMoney} where user_id = #{userId}")
	@ResultType(Integer.class)
	int updateAccountForRed(@Param("redMoney") BigDecimal redMoney, @Param("userId") int userId);
	
	
	public AccountVo findAccountByUserIdForUpdate(Integer userId);
	
	
	/**
	 * 
	 * <p>
	 * Description:更新存管金额<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年5月24日
	 * @param accountVo
	 * @return
	 * int
	 */
	public Integer updateCGAccount(AccountVo accountVo);
}
