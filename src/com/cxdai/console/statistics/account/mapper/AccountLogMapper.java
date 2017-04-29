package com.cxdai.console.statistics.account.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.statistics.account.entity.AccountLog;
import com.cxdai.console.statistics.account.vo.AccountLogCnd;
import com.cxdai.console.statistics.account.vo.AccountLogVo;

/**
 * <p>
 * Description:资金记录数据访问类<br />
 * </p>
 * 
 * @title AccountLogMapper.java
 * @package com.cxdai.console.account.mapper
 * @author justin.xu
 * @version 0.1 2014年4月30日
 */
public interface AccountLogMapper {

	/**
	 * <p>
	 * Description:插入记录,返回新增的主键ID<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param accountLog
	 * @return
	 * @throws Exception int
	 */
	public int insertEntity(AccountLog accountLog) throws Exception;

	/**
	 * <p>
	 * Description:根据id查询<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param id
	 * @return
	 * @throws Exception AccountLog
	 */
	public AccountLog queryById(Integer id) throws Exception;

	/**
	 * <p>
	 * Description:更新对象<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param accountLog
	 * @return
	 * @throws Exception int
	 */
	public int updateEntity(AccountLog accountLog) throws Exception;

	/**
	 * <p>
	 * Description:根据条件查询对象集合数量<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月23日
	 * @param AccountLogRequest accountLogCnd
	 * @return
	 * @throws Exception Integer
	 */
	public Integer queryAccountLogCount(AccountLogCnd accountLogCnd) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询账户资金日志记录<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年7月7日
	 * @param accountLogCnd
	 * @param page
	 * @return
	 * @throws Exception List<AccountLogVo>
	 */
	public List<AccountLogVo> queryAccountLogForPage(AccountLogCnd accountLogCnd, Page page) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询账户资金日志记录数量<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年7月7日
	 * @param accountLogCnd
	 * @return
	 * @throws Exception Integer
	 */
	public Integer queryAccountLogForCounts(AccountLogCnd accountLogCnd) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:统计各类渠道的充值金额总计<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年10月8日
	 * @param accountLogCnd
	 * @return
	 * @throws Exception Map<String,Object>
	 */
	public Map<String, Object> queryAccoutLogByRecharge(AccountLogCnd accountLogCnd) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询最新一笔受限为0的id<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2015年1月5日
	 * @param userId
	 * @return
	 * @throws Exception Integer
	 */
	public Integer queryMaxIdForNoDrawByUserId(int userId) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询最新一笔受限为0之后的受限增加金额总计（资金类型：线上充值、现金行权、网站奖励、网站充值、活动奖励发放、线下充值奖励、
	 * 线下充值、积分兑换工资）<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2015年1月5日
	 * @param userId
	 * @param accountLogId
	 * @return
	 * @throws Exception BigDecimal
	 */
	public BigDecimal queryNoDrawAddByUserId(@Param("userId") Integer userId, @Param("accountLogId") Integer accountLogId) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询最新一笔受限为0之后的受限减少金额总计（资金类型：投标成功、还款扣除、债权转让复审通过、 投标直通车认购成功） <br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2015年1月5日
	 * @param userId
	 * @param accountLogId
	 * @return
	 * @throws Exception BigDecimal
	 */
	public BigDecimal queryNoDrawReduceByUserId(@Param("userId") Integer userId, @Param("accountLogId") Integer accountLogId) throws Exception;
	
	/**
	 * 查询连连支付的相关充值金额（ios ，wx ，pc）<br />
	 * @param accountLogCnd
	 * @return
	 */
	public Map<String, BigDecimal> queryAccoutLogByRechargeForLianLian(AccountLogCnd accountLogCnd);
	
	/**
	 * 查询网银支付的相关充值金额（ios ，wx ，pc）<br />
	 * @param accountLogCnd
	 * @return
	 */
	public Map<String, BigDecimal> queryAccoutLogByRechargeForWy(AccountLogCnd accountLogCnd);
	
	public List<AccountLogVo> queryAccountLogForPage(AccountLogCnd accountLogCnd) throws Exception;
	
	public Integer insertAccountLog(AccountLog accountLog);
}
