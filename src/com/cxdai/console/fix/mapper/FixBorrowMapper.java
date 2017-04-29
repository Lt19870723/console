package com.cxdai.console.fix.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.fix.entity.FixBorrow;
import com.cxdai.console.fix.vo.FixAccountLogVo;
import com.cxdai.console.fix.vo.FixAccountVo;
import com.cxdai.console.fix.vo.FixBorrowCnd;
import com.cxdai.console.fix.vo.FixBorrowVo;
import com.cxdai.console.fix.vo.FixOperationLogVo;
import com.cxdai.console.fix.vo.FixStaticVo;
import com.cxdai.console.statistics.account.entity.AccountLog;

/**
 * <p>
 * 定期宝数据库访问类 Description:这里写描述<br />
 * </p>
 * @title FixBorrowMapper.java
 * @package com.cxdai.console.fix.mapper
 * @author 陈建国
 * @version 0.1 2015年5月28日
 */

public interface FixBorrowMapper {

	public Integer updateRealAccountByUnlock(@Param("unlockaccount") Integer unlockaccount, @Param("id") Integer id) throws Exception;

	/**
	 * <p>
	 * 查询定期宝列表信息 Description:这里写描述<br />
	 * </p>
	 * @author 陈建国
	 * @version 0.1 2015年5月28日
	 * @param firstFixCnd
	 * @param page
	 * @return
	 * @throws Exception List<FixBorrowVo>
	 */
	public List<FixBorrowVo> queryFixBorrowList(FixBorrowCnd firstFixCnd, Page page) throws Exception;

	/**
	 * <p>
	 * 查询审核定期宝列表信息 Description:这里写描述<br />
	 * </p>
	 * @author 陈建国
	 * @version 0.1 2015年5月28日
	 * @param firstFixCnd
	 * @param page
	 * @return
	 * @throws Exception List<FixBorrowVo>
	 */
	public List<FixBorrowVo> queryAppFixBorrowList(FixBorrowCnd firstFixCnd, Page page) throws Exception;
	
	/**
	 * 查询审核定期宝列表数量
	 * @author WangQianJin
	 * @title @param firstFixCnd
	 * @title @return
	 * @title @throws Exception
	 * @date 2016年2月26日
	 */
	public Integer queryAppFixBorrowCount(FixBorrowCnd firstFixCnd) throws Exception;

	/**
	 * <p>
	 * 查询定期宝列表信息 Description:这里写描述<br />
	 * </p>
	 * @author 陈建国
	 * @version 0.1 2015年5月28日
	 * @param firstFixCnd
	 * @return
	 * @throws Exception List<FixBorrowVo>
	 */
	public List<FixBorrowVo> queryFixBorrowList(FixBorrowCnd firstFixCnd) throws Exception;

	/**
	 * <p>
	 * 查询定期宝条数 Description:这里写描述<br />
	 * </p>
	 * @author 陈建国
	 * @version 0.1 2015年5月28日
	 * @param firstFixCnd
	 * @return
	 * @throws Exception Integer
	 */
	public Integer queryFixBorrowCount(FixBorrowCnd firstFixCnd) throws Exception;

	/**
	 * <p>
	 * 增加定期宝记录 Description:这里写描述<br />
	 * </p>
	 * @author 陈建国
	 * @version 0.1 2015年5月28日
	 * @param firstFix
	 * @return
	 * @throws Exception Integer
	 */
	public Integer insertFixBorrowWidthCondition(FixBorrowVo firstFix) throws Exception;

	/**
	 * <p>
	 * 查询最大的合同数 Description:这里写描述<br />
	 * </p>
	 * @author 陈建国
	 * @version 0.1 2015年5月28日
	 * @param contractNo
	 * @return
	 * @throws Exception String
	 */
	public String queryMaxContractNo(@Param(value = "contractNo") String contractNo) throws Exception;

	/**
	 * <p>
	 * 取最新的定期宝信息 Description:这里写描述<br />
	 * </p>
	 * @author 陈建国
	 * @version 0.1 2015年5月28日
	 * @return FixBorrowVo
	 */
	public FixBorrowVo getLatest();

	/**
	 * <p>
	 * 取最新的定期宝信息 Description:这里写描述<br />
	 * </p>
	 * @author 陈建国
	 * @version 0.1 2015年5月28日
	 * @return FixBorrowVo
	 */
	public FixBorrowVo getNewLatestById(int id);

	/**
	 * 根据ID获取记录
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 陈建国
	 * @version 0.1 2015年5月20日
	 * @param id
	 * @return FixBorrowVo
	 */
	public FixBorrowVo getFixBorrowByCnd(int id);

	/**
	 * 撤销更新tender记录为2
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 陈建国
	 * @version 0.1 2015年5月20日 void
	 */
	public int updateFixTenderCancelById(Integer id);

	/**
	 * 撤销更新账户信息
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 陈建国
	 * @version 0.1 2015年5月20日
	 * @param id void
	 */
	public int updateRockyAccountCancelById(Integer id);

	/**
	 * 更新定期宝信息
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 陈建国
	 * @version 0.1 2015年5月20日
	 * @param firstFix
	 * @return int
	 */
	public int updatefixBorrowMapper(FixBorrowVo firstFix);

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 陈建国
	 * @version 0.1 2015年5月20日
	 * @param fixBorrowVo
	 * @return
	 * @throws Exception String
	 */
	public int insertFixOperationLog(FixOperationLogVo fixOperationLogVo) throws Exception;

	/**
	 * 插入资金明细
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 陈建国
	 * @version 0.1 2015年6月8日
	 * @param fixOperationLogVo
	 * @return
	 * @throws Exception int
	 */
	public int insertAccountLog(AccountLog accountLog) throws Exception;

	/**
	 * <p>
	 * 新增账户表记录 有关定期宝 Description:这里写描述<br />
	 * </p>
	 * @author 陈建国
	 * @version 0.1 2015年5月28日
	 * @param fixAccountVo
	 * @return
	 * @throws Exception int
	 */
	public int insertFixAccount(FixAccountVo fixAccountVo) throws Exception;

	/**
	 * <p>
	 * 新增账户日志表记录 Description:这里写描述<br />
	 * </p>
	 * @author 陈建国
	 * @version 0.1 2015年5月28日
	 * @param fixAccountVo
	 * @return
	 * @throws Exception int
	 */

	public int insertFixAccountLog(FixAccountLogVo fixAccountLogVo) throws Exception;

	/**
	 * <p>
	 * 查询最新的审核信息 Description:这里写描述<br />
	 * </p>
	 * @author 陈建国
	 * @version 0.1 2015年5月28日
	 * @return FixBorrowVo
	 */
	public List<FixOperationLogVo> queryfixBorrowApprList(Integer id) throws Exception;

	/**
	 * <p>
	 * 查询最新的定期宝信息 Description:这里写描述<br />
	 * </p>
	 * @author 陈建国
	 * @version 0.1 2015年5月28日
	 * @return FixBorrowVo
	 */
	public FixStaticVo queryStaticBorrow() throws Exception;

	/**
	 * 查询即将到期的定期宝列表
	 * @param fixBorrowCnd
	 * @param page
	 * @return
	 */
	public List<FixBorrowVo> queryWillExpireFixBorrowList(FixBorrowCnd fixBorrowCnd, Page page) throws Exception;

	/**
	 * 查询即将到期的定期记录数
	 * @param fixBorrowCnd
	 * @return
	 */
	public Integer queryWillExpireFixBorrowCounts(FixBorrowCnd fixBorrowCnd) throws Exception;

	public FixStaticVo sumWillExpireFixBorrow(FixBorrowCnd fixBorrowCnd) throws Exception;

	/**
	 * 定期宝调用存储过程
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 陈建国
	 * @version 0.1 2015年5月29日
	 * @param map void
	 */
	public void fixTender(Map map) throws Exception;

	/**
	 * 取符合投标的定期宝列表
	 */
	public List<FixBorrowVo> getHandleFixBorrowListTender(FixBorrowCnd fixBorrowCnd, Page page) throws Exception;

	/**
	 * 取符合投标的定期宝列表记录
	 */
	public Integer getCountHandleFixBorrowListTender(FixBorrowCnd firstFixCnd) throws Exception;

	/**
	 * 定期宝投标
	 */
	public String getHandleFixTender() throws Exception;

	/**
	 * 取符合投标的定期宝列表
	 */
	public List<FixBorrowVo> queryPageInterestFix(FixBorrowCnd fixBorrowCnd, Page page) throws Exception;

	/**
	 * 取符合投标的定期宝列表记录
	 */
	public Integer queryPageInterestFixCount(FixBorrowCnd firstFixCnd) throws Exception;

	/**
	 * 取符合投标的定期宝列表
	 */
	public List<FixBorrowVo> queryPageInterestUser(FixBorrowCnd fixBorrowCnd, Page page) throws Exception;

	/**
	 * 取符合投标的定期宝列表记录
	 */
	public Integer queryPageInterestUserCount(FixBorrowCnd firstFixCnd) throws Exception;

	/**
	 * 通过合同编号查询宝的详细信息
	 */
	public FixBorrowVo queryAccountByContactNo(FixBorrowCnd firstFixCnd) throws Exception;

	/**
	 * <p>
	 * Description:一键法宝<br />
	 * </p>
	 * @author HuangJun
	 * @version 0.1 2015年6月24日
	 * @param id
	 * @return
	 * @throws Exception int
	 */
	public FixBorrowVo getFixBorrowById(FixBorrowCnd fixBorrowCnd) throws Exception;

	/**
	 * <p>
	 * Description:手动转让count<br />
	 * </p>
	 * @author HuangJun
	 * @version 0.1 2015年6月24日
	 * @param fixBorrowCnd
	 * @return
	 * @throws Exception Integer
	 */
	public Integer getFixBorrowCountByTransferConn(FixBorrowCnd fixBorrowCnd) throws Exception;

	/**
	 * <p>
	 * Description:手动转让 list <br />
	 * </p>
	 * @author HuangJun
	 * @version 0.1 2015年6月24日
	 * @param fixBorrowCnd
	 * @return
	 * @throws Exception FixBorrowVo
	 */
	public List<FixBorrowVo> getFixBorrowByTransferConn(FixBorrowCnd fixBorrowCnd, Page page) throws Exception;

	/**
	 * <p>
	 * Description:定期宝可用金额<br />
	 * </p>
	 * @author HuangJun
	 * @version 0.1 2015年6月25日
	 * @param fixBorrowCnd
	 * @return
	 * @throws Exception BigDecimal
	 */
	public BigDecimal getFixBorrowSumUseMoney(FixBorrowCnd fixBorrowCnd) throws Exception;

	/**
	 * <p>
	 * Description:定期宝可投金额<br />
	 * </p>
	 * @author HuangJun
	 * @version 0.1 2015年6月25日
	 * @param fixBorrowCnd
	 * @return
	 * @throws Exception BigDecimal
	 */
	public BigDecimal getFixBorrowSumUseMoneyYes(FixBorrowCnd fixBorrowCnd) throws Exception;

	/**
	 * <p>
	 * Description:获取所有转让成功的老宝，准备还款给投资人 count<br />
	 * </p>
	 * @author HuangJun
	 * @version 0.1 2015年6月26日
	 * @param fixBorrowCnd
	 * @return
	 * @throws Exception int
	 */
	public int queryFixBorrowCountForRepayment(FixBorrowCnd fixBorrowCnd) throws Exception;

	/**
	 * <p>
	 * Description:获取所有转让成功的老宝，准备还款给投资人<br />
	 * </p>
	 * @author HuangJun
	 * @version 0.1 2015年6月26日
	 * @param fixBorrowCnd
	 * @return
	 * @throws Exception List<FixBorrowVo>
	 */
	public List<FixBorrowVo> queryFixBorrowListForRepayment(FixBorrowCnd fixBorrowCnd, Page page) throws Exception;

	/**
	 * <p>
	 * Description:根据定期宝id查询，锁表<br />
	 * </p>
	 * @author HuangJun
	 * @version 0.1 2015年6月26日
	 * @param fixBorrow
	 * @return
	 * @throws Exception FixBorrow
	 */
	public FixBorrowVo queryFixBorrowByIdForUpdate(FixBorrow fixBorrow) throws Exception;

	/**
	 * <p>
	 * Description:更新：定期宝信息表t_fix_borrow.STATUS = 7(还款结束)<br />
	 * </p>
	 * @author HuangJun
	 * @version 0.1 2015年6月26日
	 * @param fixBorrow
	 * @return
	 * @throws Exception int
	 */
	int updateFixBorrowById(FixBorrow fixBorrow) throws Exception;

	/**
	 * <p>
	 * Description:判断: 还款日期(含逾期还款的情况) t_fix_borrow.LOCK_ENDTIME<= now()<br />
	 * </p>
	 * @author HuangJun
	 * @version 0.1 2015年7月6日
	 * @param fixBorrow
	 * @return
	 * @throws Exception int
	 */
	public int queryFixBorrowCountById(FixBorrow fixBorrow) throws Exception;

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 朱泳霖
	 * @version 0.1 2015年7月2日
	 * @return List<FixBorrowVo>
	 */
	public FixBorrowVo queryMatureFixBorrowById(Integer id);

	/**
	 * <p>
	 * Description:根据定期宝id修改定期宝状态为8(转让中)<br />
	 * </p>
	 * @author 朱泳霖
	 * @version 0.1 2015年6月28日
	 * @param fixBorrow void
	 */
	public void updateFixBorrowStatusById(FixBorrow fixBorrow);

	/**
	 * <p>
	 * Description:查询认购定期宝信息<br />
	 * </p>
	 * @author 朱泳霖
	 * @version 0.1 2015年6月29日
	 * @param fixBorrowCnd
	 * @return List<FixBorrowVo>
	 */
	public List<FixBorrowVo> querySubscribeFixBorrowList(FixBorrowCnd fixBorrowCnd);
	
	/**
	 * 根据ID查询定期宝锁定
	 * @author WangQianJin
	 * @title @param id
	 * @title @return
	 * @date 2015年12月3日
	 */
	public FixBorrowVo searchFixBorrowByIdForUpdate(Integer id);
	
	/**
	 * 修改限制自动投标
	 * @param fixId
	 * @param tenderBidFlag
	 * @param tenderBidDate
	 * @return
	 * @throws Exception
	 */
	@Update("update t_fix_borrow set tender_bid_flag=#{tenderBidFlag},tender_bid_date=#{tenderBidDate} where id=#{fixId}")
	@ResultType(Integer.class)
	int updateTenderBid(@Param("fixId")int fixId,@Param("tenderBidFlag")int tenderBidFlag,@Param("tenderBidDate")String tenderBidDate) throws Exception;

	/**
	 * 查询定期宝的可用余额
	 * @param fixId
	 * @return
	 */
	@Select("SELECT IFNULL(SUM(USE_MONEY),0) from t_fix_account where FIX_BORROW_ID=#{fixId} LIMIT 1")
	@ResultType(Double.class)
	Double getFixUserMoney(@Param("fixId")int fixId);
	
	/**
	 * 根据查询条件获取定期宝统计分析
	 * @author WangQianJin
	 * @title @param fixBorrowCnd
	 * @title @return
	 * @title @throws Exception
	 * @date 2016年3月15日
	 */
	public FixStaticVo queryFixStaticAnalysis(FixBorrowCnd fixBorrowCnd) throws Exception;
	/**
	 * 根据查询条件获取定期宝统计分析
	 * @author WangQianJin
	 * @title @param fixBorrowCnd
	 * @title @return
	 * @title @throws Exception
	 * @date 2016年3月15日
	 */
	public FixStaticVo queryNewFixStaticAnalysis(FixBorrowCnd fixBorrowCnd) throws Exception;
	
}
