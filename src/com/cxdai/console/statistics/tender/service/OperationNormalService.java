package com.cxdai.console.statistics.tender.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.Constants;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.statistics.tender.entity.BorrowDataInfoVo;
import com.cxdai.console.statistics.tender.entity.CpaAndCpsVo;
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
import com.cxdai.console.statistics.tender.mapper.ChannelCpaAndCpsMapper;
import com.cxdai.console.statistics.tender.mapper.OperationNormalMapper;
import com.cxdai.console.util.DateUtils;

/**
 * <p>
 * Description:运营数据分析常规业务实现类<br />
 * </p>
 * 
 * @title OperationNormalServiceImpl.java
 * @package com.cxdai.console.opration.service.impl
 * @author justin.xu
 * @version 0.1 2014年12月24日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class OperationNormalService {

	@Autowired
	private OperationNormalMapper operationNormal;
	
	@Autowired
	private ChannelCpaAndCpsMapper channelCpaAndCpsMapper;

	public Map<String, Object> queryNormalData(OperationCnd operationCnd) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (null != operationCnd.getBeginTime()) {
			operationCnd.setBeginTime(DateUtils.convert2StartDate(operationCnd.getBeginTime()));
			operationCnd.setBeginTimeStr(operationCnd.getBeginTime().getTime() / 1000 + "");
		}
		if (null != operationCnd.getEndTime()) {
			operationCnd.setEndTime(DateUtils.convert2EndDate(operationCnd.getEndTime()));
			operationCnd.setEndTimeStr(operationCnd.getEndTime().getTime() / 1000 + "");
		}
		// 查询最新的待收总额
		BigDecimal collectionTotal = operationNormal.queryCollectionTotal();
		resultMap.put("collectionTotal", collectionTotal);
		// 根据条件查询所有满标的所有标种成交总额
		BigDecimal successAccountTotal = operationNormal.queryBorrowSuccessAccountTotal(operationCnd);
		resultMap.put("successAccountTotal", successAccountTotal);
		// 根据条件查询累计投资人数
		Integer investPersonsTotal = operationNormal.queryInvestPersonsTotal(operationCnd);
		resultMap.put("investPersonsTotal", investPersonsTotal);
		// 设置默认值
		BigDecimal manualTenderMoney = new BigDecimal(0);
		BigDecimal autoTenderMoney = new BigDecimal(0);
		BigDecimal firstTenderMoney = new BigDecimal(0);
		// 查询成交的借款标不同投标方式投标金额
		List<BorrowDataInfoVo> tenderMoneyList = operationNormal.querySuccessAccountForTenderType(operationCnd);
		if (null != tenderMoneyList && tenderMoneyList.size() > 0) {
			for (BorrowDataInfoVo borrowDataInfoVo : tenderMoneyList) {
				// 手动投标
				if (borrowDataInfoVo.getTenderType().equals(Constants.TENDER_TYPE_MANUAL)) {
					manualTenderMoney = borrowDataInfoVo.getAccount();
					// 自动投标
				} else if (borrowDataInfoVo.getTenderType().equals(Constants.TENDER_TYPE_AUTO)) {
					autoTenderMoney = borrowDataInfoVo.getAccount();
					// 直通车投标
				} else if (borrowDataInfoVo.getTenderType().equals(Constants.TENDER_TYPE_FIRST)) {
					firstTenderMoney = borrowDataInfoVo.getAccount();
				}
			}
		}
		resultMap.put("manualTenderMoney", manualTenderMoney);
		resultMap.put("autoTenderMoney", autoTenderMoney);
		resultMap.put("firstTenderMoney", firstTenderMoney);
		return resultMap;
	}

	public Map<String, Object> queryNewRegisterData(OperationCnd operationCnd) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (null != operationCnd.getBeginTime()) {
			operationCnd.setBeginTime(DateUtils.convert2StartDate(operationCnd.getBeginTime()));
			operationCnd.setBeginTimeStr(operationCnd.getBeginTime().getTime() / 1000 + "");
		}
		if (null != operationCnd.getEndTime()) {
			operationCnd.setEndTime(DateUtils.convert2EndDate(operationCnd.getEndTime()));
			operationCnd.setEndTimeStr(operationCnd.getEndTime().getTime() / 1000 + "");
		}
		// 新注册用户充值总额(在查询时间段内注册的投资用户必且在查询时间段内充值成功的总额)
		BigDecimal topupTotalNewRegister = operationNormal.queryTopupTotalForNewRegister(operationCnd);
		resultMap.put("topupTotalNewRegister", topupTotalNewRegister);
		// 新注册用户提现总额(在查询时间段内注册的投资用户必且在查询时间段内提现成功的总额)
		BigDecimal withDrawTotalNewRegister = operationNormal.queryWithDrawTotalForNewRegister(operationCnd);
		resultMap.put("withDrawTotalNewRegister", withDrawTotalNewRegister);
		// 新用户投资总额(在查询时间段内注册的投资用户必且在查询时间段内满标的投标总额)
		BigDecimal tenderTotalNewRegister = operationNormal.queryTenderTotalForNewRegister(operationCnd);
		resultMap.put("tenderTotalNewRegister", tenderTotalNewRegister);
		// 流失投资者人数：（之前有充值并投标超过100【包含100元】，现资产总额小于100）在某个时间范围内的总计
		Integer losePersonsTotal = operationNormal.queryLosePersonsTotal(operationCnd);
		resultMap.put("losePersonsTotal", losePersonsTotal);
		return resultMap;
	}

	public Map<String, Object> queryRecommendData(OperationCnd operationCnd) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (null != operationCnd.getBeginTime()) {
			operationCnd.setBeginTime(DateUtils.convert2StartDate(operationCnd.getBeginTime()));
			operationCnd.setBeginTimeStr(operationCnd.getBeginTime().getTime() / 1000 + "");
		}
		if (null != operationCnd.getEndTime()) {
			operationCnd.setEndTime(DateUtils.convert2EndDate(operationCnd.getEndTime()));
			operationCnd.setEndTimeStr(operationCnd.getEndTime().getTime() / 1000 + "");
		}
		// 推荐人数量与被推荐人数量
		RecommendInfoVo recommendInfoVo = operationNormal.queryRecommendInfo(operationCnd);
		resultMap.put("recommendInfoVo", recommendInfoVo);
		// 被推荐人充值额
		BigDecimal topupTotalRecommended = operationNormal.queryTopupTotalForRecommended(operationCnd);
		resultMap.put("topupTotalRecommended", topupTotalRecommended);
		// 被推荐人投资额
		BigDecimal tenderTotalRecommended = operationNormal.queryTenderTotalForRecommended(operationCnd);
		resultMap.put("tenderTotalRecommended", tenderTotalRecommended);
		// 流失被推荐投资者人数：（之前有充值并投标超过100【包含100元】，现资产总额小于100） 在某个时间范围内的总计
		Integer losePersonsRecommended = operationNormal.queryLosePersonsForRecommended(operationCnd);
		resultMap.put("losePersonsRecommended", losePersonsRecommended);
		return resultMap;
	}

	public NetValueBorrowCountVo queryNetValueBorrowCount(OperationCnd operationCnd) throws Exception {
		NetValueBorrowCountVo vo = operationNormal.queryOKNetValueBorrowCount(operationCnd);
		if (vo == null) {
			vo = new NetValueBorrowCountVo();
		}
		vo.setPublishCount(operationNormal.queryPushNetValueCount(operationCnd));
		return vo;
	}

	public List<RecommendCountVo> queryRecommendCountList(OperationCnd operationCnd) {
		return operationNormal.queryRecommendCountList(operationCnd);
	}

	public FirstInfoCountVo queryfirstInfoCount(OperationCnd operationCnd) {
		return operationNormal.queryfirstInfoCount(operationCnd);
	}

	public List<MoreInvestCountVo> querymoreInvestCount(OperationCnd operationCnd) {
		return operationNormal.querymoreInvestCount(operationCnd);
	}

	public NewInvestCountVo queryNewInvestCount(OperationCnd operationCnd) {
		return operationNormal.queryNewInvestCount(operationCnd);
	}
	
	public List<MonthlyInvestVo> queryMonthlyInvest(OperationCnd operationCnd, Page page) throws Exception {
		page.setTotalCount(operationNormal.queryMonthlyInvestCount(operationCnd));
		if (page.getTotalPage() < page.getPageNo()) {
			page.setPageNo(page.getTotalPage());
		}
		operationCnd.set_limit(page.getOffset());
		operationCnd.set_offset(page.getLimit());
		return operationNormal.queryMonthlyInvest(operationCnd);
	}
	
	/**
	 * 注册表单明细统计
	 * @author WangQianJin
	 * @title @param operationCnd
	 * @title @return
	 * @date 2016年1月14日
	 */
	public List<RegisterFormDetailVo> queryRegisterFormDetailListAll(OperationCnd operationCnd) {
		return operationNormal.queryRegisterFormDetailListAll(operationCnd);
	}
	
	/**
	 * 分页查询注册表单明细
	 * @author WangQianJin
	 * @title @param operationCnd
	 * @title @param pageNo
	 * @title @param pageSize
	 * @title @return
	 * @date 2016年1月14日
	 */
	public Page queryRegisterFormDetailPage(OperationCnd operationCnd, int pageNo, int pageSize) {
		Page page = new Page(pageNo, pageSize);

		page.setTotalCount(operationNormal.queryRegisterFormDetailListCount(operationCnd));
		page.setResult(operationNormal.queryRegisterFormDetailList(operationCnd,page));

		return page;
	}
	
	/**
	 * 分页查询注册表单明细
	 * @author WangQianJin
	 * @title @param operationCnd
	 * @title @param pageNo
	 * @title @param pageSize
	 * @title @return
	 * @date 2016年3月21日
	 */
	public Page queryCpaAndCpsPage(OperationCnd operationCnd, int pageNo, int pageSize) {
		Page page = new Page(pageNo, pageSize);
		
		if("17".equals(operationCnd.getSource())){
			//投之家
			if("cpa".equals(operationCnd.getSourceType())){
				page.setTotalCount(channelCpaAndCpsMapper.queryTzjCpaCount(operationCnd));
				page.setResult(channelCpaAndCpsMapper.queryTzjCpaList(operationCnd, page));
			}else if("cps".equals(operationCnd.getSourceType())){
				page.setTotalCount(channelCpaAndCpsMapper.queryTzjCpsCount(operationCnd));
				page.setResult(channelCpaAndCpsMapper.queryTzjCpsList(operationCnd, page));
			}			
		}else if("13".equals(operationCnd.getSource())){
			//网贷天眼
			if("cpa".equals(operationCnd.getSourceType())){
				page.setTotalCount(channelCpaAndCpsMapper.queryWdtyCpaCount(operationCnd));
				page.setResult(channelCpaAndCpsMapper.queryWdtyCpaList(operationCnd, page));
			}else if("cps".equals(operationCnd.getSourceType())){
				page.setTotalCount(channelCpaAndCpsMapper.queryWdtyCpsCount(operationCnd));
				page.setResult(channelCpaAndCpsMapper.queryWdtyCpsList(operationCnd, page));
			}
		}else if("60".equals(operationCnd.getSource())){
			//多麦网
			page.setTotalCount(channelCpaAndCpsMapper.queryDmwCount(operationCnd));
			page.setResult(channelCpaAndCpsMapper.queryDmwList(operationCnd, page));
		}else if("37".equals(operationCnd.getSource())){
			//富爸爸
			page.setTotalCount(channelCpaAndCpsMapper.queryFbbCount(operationCnd));
			page.setResult(channelCpaAndCpsMapper.queryFbbList(operationCnd, page));
		}else if("38".equals(operationCnd.getSource())){
			//领克特
			page.setTotalCount(channelCpaAndCpsMapper.queryLktCount(operationCnd));
			page.setResult(channelCpaAndCpsMapper.queryLktList(operationCnd, page));
		}
		
		return page;
	}
	
	/**
	 * CPA与CPS统计
	 * @author WangQianJin
	 * @title @param operationCnd
	 * @title @return
	 * @date 2016年3月21日
	 */
	public List<CpaAndCpsVo> queryCpaAndCpsListAll(OperationCnd operationCnd) {
		
		List<CpaAndCpsVo> list=new ArrayList<CpaAndCpsVo>();
		
		if("17".equals(operationCnd.getSource())){
			//投之家
			if("cpa".equals(operationCnd.getSourceType())){
				list=channelCpaAndCpsMapper.queryTzjCpaList(operationCnd);				
			}else if("cps".equals(operationCnd.getSourceType())){
				list=channelCpaAndCpsMapper.queryTzjCpsList(operationCnd);				
			}			
		}else if("13".equals(operationCnd.getSource())){
			//网贷天眼
			if("cpa".equals(operationCnd.getSourceType())){
				list=channelCpaAndCpsMapper.queryWdtyCpaList(operationCnd);				
			}else if("cps".equals(operationCnd.getSourceType())){
				list=channelCpaAndCpsMapper.queryWdtyCpsList(operationCnd);				
			}
		}else if("60".equals(operationCnd.getSource())){
			//多麦网
			list=channelCpaAndCpsMapper.queryDmwList(operationCnd);
		}else if("37".equals(operationCnd.getSource())){
			//富爸爸
			list=channelCpaAndCpsMapper.queryFbbList(operationCnd);
		}else if("38".equals(operationCnd.getSource())){
			//领克特
			list=channelCpaAndCpsMapper.queryLktList(operationCnd);
		}
		
		return list;
	}
	public Page queryMemberTenderRecordList(OperationCnd operationCnd, int pageSize, int curPage) throws Exception {
		Page page = new Page(curPage, pageSize);
		int totalCount = operationNormal.queryMemberTenderRecordCount(operationCnd);
		page.setTotalCount(totalCount);
		List<MemberTenderRecordVo> list =operationNormal.queryMemberTenderRecordList(operationCnd,page);
		page.setResult(list);
		return page;
	}
}
