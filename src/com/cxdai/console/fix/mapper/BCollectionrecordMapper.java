package com.cxdai.console.fix.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.cxdai.console.borrow.approve.vo.BorrowVo;
import com.cxdai.console.borrow.check.vo.BorrowCheckVo;
import com.cxdai.console.borrow.manage.vo.EFundRepayMentCnd;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.fix.vo.BCollectionAmountVo;
import com.cxdai.console.fix.vo.BCollectionEfendRecord;
import com.cxdai.console.fix.vo.BCollectionRecordCnd;
import com.cxdai.console.fix.vo.BCollectionRecordVo;
import org.apache.ibatis.annotations.Param;

public interface BCollectionrecordMapper {
	/**
	 * 
	 * <p>
	 * Description:根据定期宝ID查询投标记录表中待收本金总和<br />
	 * </p>
	 * 
	 * @author 朱泳霖
	 * @version 0.1 2015年6月26日
	 * @param fixBorrowId
	 * @return Integer
	 */
	public BigDecimal queryCollectionrecordSum(Integer fixBorrowId);

	/**
	 * 
	 * <p>
	 * Description:根据投标ID查询待收记录<br />
	 * </p>
	 * 
	 * @author 朱泳霖
	 * @version 0.1 2015年7月1日
	 * @param bCollectionRecordCnd
	 * @return List<BCollectionRecordVo>
	 */
	public List<BCollectionRecordVo> queryCollectionrecordListByTenderId(BCollectionRecordCnd bCollectionRecordCnd);

	/**
	 * <p>
	 * Description:添加待收记录<br />
	 * </p>
	 * 
	 * @author 朱泳霖
	 * @version 0.1 2015年7月1日
	 * @param bCollectionRecordVo
	 *            void
	 */
	public void insert(BCollectionRecordVo bCollectionRecordVo);

	/**
	 * 
	 * <p>
	 * Description:根据投标ID和期数查询预还总和，本金总和 <br />
	 * </p>
	 * 
	 * @author 朱泳霖
	 * @version 0.1 2015年7月2日
	 * @param bCollectionRecordCnd
	 * @return BCollectionRecordVo
	 */
	public BCollectionRecordVo queryRepayAccountAndCapitalSum(BCollectionRecordCnd bCollectionRecordCnd);

	/**
	 * 
	 * <p>
	 * Description:修改定期宝状态<br />
	 * </p>
	 * 
	 * @author 朱泳霖
	 * @version 0.1 2015年7月2日
	 * @param tenderId
	 *            void
	 */
	public void updateStatusById(Integer tenderId);

	/**
	 * 
	 * <p>
	 * Description:根据定期宝ID查询投标记录表中待收总和<br />
	 * </p>
	 * 
	 * @author 朱泳霖
	 * @version 0.1 2015年7月14日
	 * @param collectionRecordCnd
	 * @return BigDecimal
	 */
	public BigDecimal queryRepayAccountSum(BCollectionRecordCnd collectionRecordCnd);
	
	/**
	 * 根据借款标ID和期数查询尚未还款的待收记录
	 * @param bCollectionRecordCnd
	 * @return
	 */
	public  List<BCollectionRecordVo> queryCollectionForRepay(BCollectionRecordCnd bCollectionRecordCnd);
	
	
	/**
	 * 
	 * <p>
	 * Description:修改待收平台流水号<br />
	 * </p>
	 */
	public void updateBizNoByID(BCollectionRecordCnd bCollectionRecordCnd);
	
	/**
	 * 更新银行交易流水号,状态更新为4银行还款中
	 * @param efundCnd
	 */
	public void updateByResponse(EFundRepayMentCnd efundCnd);


	public List<BCollectionRecordVo> queryCollectionList(@Param("borrowId") Integer borrowId,@Param("repaymentId") Integer repaymentId);

	/**
	 * 本息还款对账后更新
	 * @param map
     */
	public void updateAfterRecon(Map map);
	public void updateStatusAfterRecon(Map map);
	
	public BigDecimal findSumRepyAccount(BCollectionRecordVo bCollectionRecordVo);
	
	public BorrowCheckVo findCollectionAccount(@Param("borrowId") Integer borrowId,@Param("order") Integer order);
	
	public void insertCollectionrecord(BCollectionRecordVo bCollectionRecordVo);


	/**
	 * 查询本息还款总待收金额
	 * @param borrowId
	 * @param repaymentId
     * @return
     */
	public BigDecimal queryCollectionCount(@Param("borrowId") Integer borrowId,@Param("repaymentId") Integer repaymentId);




	public List<BCollectionEfendRecord> queryCollectionByRepayMentId(@Param("repaymentId") Integer repaymentId, Page page);

	public Integer queryCollectionCountByRepayMentId(@Param("repaymentId") Integer repaymentId);

	/**
	 * 查询应收总额
	 * @param borrowId
	 * @return
     */
	public BigDecimal queryCollectionTotalByBorrowId(@Param("borrowId") Integer borrowId);



	/**
	 * 查询某期尚未还款的待收记录
	 * @param borrowId 借款标ID
	 * @param order 期数
	 * @return
	 */
	public  List<BCollectionRecordVo> queryCurrentCollectionRecord(@Param("borrowId") Integer borrowId,
																   @Param("order") Integer order);


	/**
	 *  投资者某笔投标对应的当期之后应收利息总额
	 * @return
	 */
	public  BigDecimal queryAmountAfterCurrent(Map map);

	/**
	 *  投资者累计待收总额，累计待收本金总额，累计待收利息总额
	 * @return
	 */
	public BCollectionAmountVo queryAmountAll(Map map);

	public void updateCollectionByMap(Map map);
	public void updateCollectionById(Map map);
	public void updateCollectionAfterorderByMap(Map map);
	public BCollectionRecordVo selectByPrimaryKey(Integer id);


	public List<BCollectionRecordVo> queryCollectionListByBizNo(@Param("noList") List noList);
}
