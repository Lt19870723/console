package com.cxdai.console.statistics.tender.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.statistics.tender.entity.BorrowDataInfoVo;
import com.cxdai.console.statistics.tender.entity.FirstInfoCountVo;
import com.cxdai.console.statistics.tender.entity.MemberTenderRecordVo;
import com.cxdai.console.statistics.tender.entity.MonthlyInvestVo;
import com.cxdai.console.statistics.tender.entity.MoreInvestCountVo;
import com.cxdai.console.statistics.tender.entity.NetValueBorrowCountVo;
import com.cxdai.console.statistics.tender.entity.NewInvestCountVo;
import com.cxdai.console.statistics.tender.entity.OperationCnd;
import com.cxdai.console.statistics.tender.entity.RecommendCountVo;
import com.cxdai.console.statistics.tender.entity.RecommendInfoVo;
import com.cxdai.console.statistics.tender.entity.RegisterFormDetailVo;

/**
 * <p>
 * Description:运营数据分析常规访问类<br />
 * </p>
 * 
 * @title OperationNormalMapper.java
 * @package com.cxdai.console.opration.mapper
 * @author justin.xu
 * @version 0.1 2014年12月24日
 */
public interface OperationNormalMapper {

	/**
	 * <p>
	 * Description:查询最新的待收总额<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年12月24日
	 * @return
	 * @throws Exception BigDecimal
	 */
	public BigDecimal queryCollectionTotal() throws Exception;

	/**
	 * <p>
	 * Description:根据条件查询所有满标的所有标种成交总额<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年12月24日
	 * @return
	 * @throws Exception BigDecimal
	 */
	public BigDecimal queryBorrowSuccessAccountTotal(OperationCnd operationCnd) throws Exception;

	/**
	 * <p>
	 * Description:根据条件查询累计投资人数<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年12月24日
	 * @return
	 * @throws Exception Integer
	 */
	public Integer queryInvestPersonsTotal(OperationCnd operationCnd) throws Exception;

	/**
	 * <p>
	 * Description:查询成交的借款标不同投标方式投标金额<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2013年12月25日
	 * @return List<BorrowDataInfoVo>
	 */
	public List<BorrowDataInfoVo> querySuccessAccountForTenderType(OperationCnd operationCnd) throws Exception;

	/**
	 * <p>
	 * Description:新注册用户充值总额(在查询时间段内注册的投资用户必且在查询时间段内充值成功的总额)<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年12月25日
	 * @param operationCnd
	 * @return
	 * @throws Exception BigDecimal
	 */
	public BigDecimal queryTopupTotalForNewRegister(OperationCnd operationCnd) throws Exception;

	/**
	 * <p>
	 * Description:新注册用户提现总额(在查询时间段内注册的投资用户必且在查询时间段内提现成功的总额)<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年12月25日
	 * @param operationCnd
	 * @return
	 * @throws Exception BigDecimal
	 */
	public BigDecimal queryWithDrawTotalForNewRegister(OperationCnd operationCnd) throws Exception;

	/**
	 * <p>
	 * Description:新用户投资总额(在查询时间段内注册的投资用户必且在查询时间段内满标的投标总额)<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年12月25日
	 * @param operationCnd
	 * @return
	 * @throws Exception BigDecimal
	 */
	public BigDecimal queryTenderTotalForNewRegister(OperationCnd operationCnd) throws Exception;

	/**
	 * <p>
	 * Description:流失投资者人数：（之前有充值并投标超过100【包含100元】，现资产总额小于100） 在某个时间范围内的总计<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年12月25日
	 * @param operationCnd
	 * @return
	 * @throws Exception Integer
	 */
	public Integer queryLosePersonsTotal(OperationCnd operationCnd) throws Exception;

	/**
	 * <p>
	 * Description:推荐人数量与被推荐人数量：（所有被推荐人数量）<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年12月25日
	 * @param operationCnd
	 * @return
	 * @throws Exception RecommendInfoVo
	 */
	public RecommendInfoVo queryRecommendInfo(OperationCnd operationCnd) throws Exception;

	/**
	 * <p>
	 * Description:被推荐人充值额<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年12月25日
	 * @param operationCnd
	 * @return
	 * @throws Exception BigDecimal
	 */
	public BigDecimal queryTopupTotalForRecommended(OperationCnd operationCnd) throws Exception;

	/**
	 * <p>
	 * Description:被推荐人投资总额<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年12月25日
	 * @param operationCnd
	 * @return
	 * @throws Exception BigDecimal
	 */
	public BigDecimal queryTenderTotalForRecommended(OperationCnd operationCnd) throws Exception;

	/**
	 * <p>
	 * Description:流失被推荐投资者人数：（之前有充值并投标超过100【包含100元】，现资产总额小于100） 在某个时间范围内的总计<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年12月25日
	 * @param operationCnd
	 * @return
	 * @throws Exception Integer
	 */
	public Integer queryLosePersonsForRecommended(OperationCnd operationCnd) throws Exception;

	/**
	 * 统计净值标发布情况
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author ZHUCHEN
	 * @version 0.1 2015年1月9日
	 * @param operationCnd
	 * @return NetValueBorrowVo
	 */
	public Integer queryPushNetValueCount(OperationCnd operationCnd) throws Exception;

	/**
	 * 统计净值标成交情况
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author ZHUCHEN
	 * @version 0.1 2015年1月9日
	 * @param operationCnd
	 * @return NetValueBorrowVo
	 */
	public NetValueBorrowCountVo queryOKNetValueBorrowCount(OperationCnd operationCnd) throws Exception;

	/**
	 * 推广统计
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author ZHUCHEN
	 * @version 0.1 2015年1月12日
	 * @param operationCnd
	 * @return List<RecommendCountVo>
	 */
	public List<RecommendCountVo> queryRecommendCountList(OperationCnd operationCnd);
	
	/**
	 * 直通车情况统计结果
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author ZHUCHEN
	 * @version 0.1 2015年1月16日
	 * @param operationCnd
	 * @return FirstInfoCountVo
	 */
	public FirstInfoCountVo queryfirstInfoCount(OperationCnd operationCnd);

	/**
	 * 复投人次
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author ZHUCHEN
	 * @version 0.1 2015年1月16日
	 * @param operationCnd
	 * @return MoreInvestCountVo
	 */
	public List<MoreInvestCountVo> querymoreInvestCount(OperationCnd operationCnd);

	/**
	 * 新增投资人数统计
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author ZHUCHEN
	 * @version 0.1 2015年1月17日
	 * @param operationCnd
	 * @return NewInvestCountVo
	 */
	public NewInvestCountVo queryNewInvestCount(OperationCnd operationCnd);

	/**
	 * 统计月投资 总条数
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author ZHUCHEN
	 * @version 0.1 2015年1月18日
	 * @param operationCnd
	 * @return List<MonthlyInvestVo>
	 */
	public Integer queryMonthlyInvestCount(OperationCnd operationCnd);

	/**
	 * 统计月投资量
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author ZHUCHEN
	 * @version 0.1 2015年1月18日
	 * @param operationCnd
	 * @return List<MonthlyInvestVo>
	 */
	public List<MonthlyInvestVo> queryMonthlyInvest(OperationCnd operationCnd);
	
	/**
	 * 注册表单明细统计
	 * @author WangQianJin
	 * @title @param operationCnd
	 * @title @return
	 * @date 2016年1月14日
	 */
	public List<RegisterFormDetailVo> queryRegisterFormDetailListAll(OperationCnd operationCnd);
	
	/**
	 * 注册表单明细统计数量
	 * @author WangQianJin
	 * @title @param operationCnd
	 * @title @return
	 * @date 2016年1月14日
	 */
	public int queryRegisterFormDetailListCount(OperationCnd operationCnd);

	/**
	 * 注册表单明细统计分页
	 * @author WangQianJin
	 * @title @param operationCnd
	 * @title @return
	 * @date 2016年1月14日
	 */
	public List<RegisterFormDetailVo> queryRegisterFormDetailList(OperationCnd operationCnd, Page page);
	/**
	 * 客户投资记录
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author liutao
	 * @version 0.1 2016年4月20日
	 * @param operationCnd
	 * @return List<MemberTenderRecordVo>
	 */
	public List<MemberTenderRecordVo> queryMemberTenderRecordList(OperationCnd operationCnd,Page page);
	public int queryMemberTenderRecordCount(OperationCnd operationCnd);

}
