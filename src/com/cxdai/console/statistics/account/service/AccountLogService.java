package com.cxdai.console.statistics.account.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.base.mapper.BaseConfigurationMapper;
import com.cxdai.console.borrow.manage.mapper.BRepaymentRecordMapper;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.information.mapper.MemberMapper;
import com.cxdai.console.statistics.account.entity.AccountLog;
import com.cxdai.console.statistics.account.entity.UserNetValue;
import com.cxdai.console.statistics.account.mapper.AccountLogMapper;
import com.cxdai.console.statistics.account.mapper.AccountNetValueMapper;
import com.cxdai.console.statistics.account.vo.AccountLogCnd;
import com.cxdai.console.statistics.account.vo.AccountLogVo;
import com.cxdai.console.statistics.account.vo.AccountVo;
import com.cxdai.console.system.entity.Configuration;
import com.cxdai.console.system.mapper.ConfigurationMapper;
import com.cxdai.console.util.DateUtils;

/**
 * <p>
 * Description:资金操作日志业务实现类<br />
 * </p>
 * @title AccountLogServiceImpl.java
 * @package com.cxdai.console.account.service.impl
 * @author justin.xu
 * @version 0.1 2014年4月25日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class AccountLogService {

	@Autowired
	private AccountLogMapper accountLogMapper;
	@Autowired
	private ConfigurationMapper configurationMapper;
	@Autowired
	private BaseConfigurationMapper baseConfigurationMapper;
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private AccountNetValueMapper accountNetValueMapper;
	@Autowired
	private BRepaymentRecordMapper bRepaymentRecordMapper;

	public void insertAccountLog(AccountLog accountLog) throws Exception {
		accountLog.setAddtime(DateUtils.getTime());
		accountLogMapper.insertEntity(accountLog);
	}

	public Integer queryAccountLogCount(AccountLogCnd accountLogCnd) throws Exception {
		return accountLogMapper.queryAccountLogCount(accountLogCnd);
	}

	public void saveAccountLogByParams(AccountVo accountVo, Integer userId, BigDecimal money, String remark, String addIp, String type, Integer idType, Integer borrowId, String borrowName)
			throws Exception {
		AccountLog accountLog = new AccountLog();
		accountLog.setAddip(addIp);
		accountLog.setAddtime(DateUtils.getTime());
		accountLog.setCollection(accountVo.getCollection());
		accountLog.setUseMoney(accountVo.getUseMoney());
		accountLog.setNoUseMoney(accountVo.getNoUseMoney());
		accountLog.setTotal(accountVo.getTotal());
		accountLog.setFirstBorrowUseMoney(accountVo.getFirstBorrowUseMoney());
		accountLog.setDrawMoney(accountVo.getDrawMoney());
		accountLog.setNoDrawMoney(accountVo.getNoDrawMoney());
		accountLog.setMoney(money);// 交易金额
		accountLog.setRemark(remark);
		accountLog.setToUser(userId);// 设置为本人
		accountLog.setUserId(accountVo.getUserId());
		accountLog.setType(type);
		if (null != idType) {
			accountLog.setIdType(idType);
		}
		if (null != borrowId) {
			accountLog.setBorrowId(borrowId);
		}
		if (null != borrowName) {
			accountLog.setBorrowName(borrowName);
		}
		this.insertAccountLog(accountLog);
	}

	/**
	 * 保存存管日志
	 * @param accountVo
	 * @param userId
	 * @param money
	 * @param remark
	 * @param addIp
	 * @param type
	 * @param idType
	 * @param borrowId
	 * @param borrowName
     * @throws Exception
     */
	public void saveEAccountLogByParams(AccountVo accountVo, Integer userId, BigDecimal money, String remark, String addIp, String type, Integer idType, Integer borrowId, String borrowName)
			throws Exception {
		AccountLog accountLog = new AccountLog();
		accountLog.setAddip(addIp);
		accountLog.setAddtime(DateUtils.getTime());
		accountLog.setCollection(accountVo.getCollection());
		accountLog.setUseMoney(accountVo.getUseMoney());
		accountLog.setNoUseMoney(accountVo.getNoUseMoney());
		accountLog.setTotal(accountVo.getTotal());
		accountLog.setFirstBorrowUseMoney(accountVo.getFirstBorrowUseMoney());
		accountLog.setDrawMoney(accountVo.getDrawMoney());
		accountLog.setNoDrawMoney(accountVo.getNoDrawMoney());
		accountLog.setMoney(money);// 交易金额
		accountLog.setRemark(remark);
		accountLog.setToUser(userId);// 设置为本人
		accountLog.setUserId(accountVo.getUserId());
		accountLog.setType(type);

		accountLog.seteCollection(accountVo.geteCollection());
		accountLog.seteFreezeMoney(accountVo.geteFreezeMoney());
		accountLog.seteUseMoney(accountVo.geteUseMoney());
		accountLog.setIsCustody(1);//存管日志
		if (null != idType) {
			accountLog.setIdType(idType);
		}
		if (null != borrowId) {
			accountLog.setBorrowId(borrowId);
		}
		if (null != borrowName) {
			accountLog.setBorrowName(borrowName);
		}
		this.insertAccountLog(accountLog);
	}


	public Page queryAccountLogForPage(AccountLogCnd accountLogCnd, int pageSize, int curPage) throws Exception {
		Page page = new Page(curPage, pageSize);
		 
		int totalCount = accountLogMapper.queryAccountLogForCounts(accountLogCnd);
		page.setTotalCount(totalCount);
		List<AccountLogVo> list = accountLogMapper.queryAccountLogForPage(accountLogCnd, page);
		page.setResult(list);
		return page;
	}
	public List<AccountLogVo>  queryAccountLogVo(AccountLogCnd accountLogCnd) throws Exception { 
		 
		List<AccountLogVo> list = accountLogMapper.queryAccountLogForPage(accountLogCnd);
		 
		return list;
	}

	public List<Configuration> queryConfigurationListByType(int type) throws Exception {
		return baseConfigurationMapper.queryConfigurationListByType(type);
	}

	@SuppressWarnings("null")
	public Map<String, BigDecimal> queryAccoutLogByRecharge(AccountLogCnd accountLogCnd) throws Exception {
		if (null != accountLogCnd.getBeginTime()) {
			accountLogCnd.setBeginTime(DateUtils.convert2StartDate(accountLogCnd.getBeginTime()));
			accountLogCnd.setBeginTimeStr(accountLogCnd.getBeginTime().getTime() / 1000 + "");
		}
		if (null != accountLogCnd.getEndTime()) {
			accountLogCnd.setEndTime(DateUtils.convert2EndDate(accountLogCnd.getEndTime()));
			accountLogCnd.setEndTimeStr(accountLogCnd.getEndTime().getTime() / 1000 + "");
		}
		Map<String, BigDecimal> resultMap = new HashMap<String, BigDecimal>();
		Map<String, Object> map = accountLogMapper.queryAccoutLogByRecharge(accountLogCnd);

		if (map != null && map.get("onlinePaymentRechargeTotal") != null) {
			//网银在线充值总额
			resultMap.put("onlinePaymentRechargeTotal", new BigDecimal(map.get("onlinePaymentRechargeTotal").toString()));
		} else {
			resultMap.put("onlinePaymentRechargeTotal", new BigDecimal(0));
		}
		if (map != null && map.get("sinaPayRechargeTotal") != null) {
			//新浪支付充值总额
			resultMap.put("sinaPayRechargeTotal", new BigDecimal(map.get("sinaPayRechargeTotal").toString()));
		} else {
			resultMap.put("sinaPayRechargeTotal", new BigDecimal(0));
		}
		if (map != null && map.get("shengFutongRechargeTotal") != null) {
			//盛付通充值总额
			resultMap.put("shengFutongRechargeTotal", new BigDecimal(map.get("shengFutongRechargeTotal").toString()));
		} else {
			resultMap.put("shengFutongRechargeTotal", new BigDecimal(0));
		}
		if (map != null && map.get("guoFuBaoRechargeTotal") != null) {
			//国付宝充值总额
			resultMap.put("guoFuBaoRechargeTotal", new BigDecimal(map.get("guoFuBaoRechargeTotal").toString()));
		} else {
			resultMap.put("guoFuBaoRechargeTotal", new BigDecimal(0));
		}
//		if (map != null && map.get("lianlianRechargeTotal") != null) {
//			resultMap.put("lianlianRechargeTotal", new BigDecimal(map.get("lianlianRechargeTotal").toString()));
//		} else {
//			resultMap.put("lianlianRechargeTotal", new BigDecimal(0));
//		}
		if (map != null && map.get("fuiouRechargeTotal") != null) {
			//富友支付充值金额
			resultMap.put("fuiouRechargeTotal", new BigDecimal(map.get("fuiouRechargeTotal").toString()));
		} else {
			resultMap.put("fuiouRechargeTotal", new BigDecimal(0));
		}
		if (map != null && map.get("alipayRechargeTotal") != null) {
			//支付宝充值总额
			resultMap.put("alipayRechargeTotal", new BigDecimal(map.get("alipayRechargeTotal").toString()));
		} else {
			resultMap.put("alipayRechargeTotal", new BigDecimal(0));
		}
        if (map != null && map.get("czbankRechargeTotal") != null) {
			//浙商存管充值总额
			resultMap.put("czbankRechargeTotal", new BigDecimal(map.get("czbankRechargeTotal").toString()));
		} else {
			resultMap.put("czbankRechargeTotal", new BigDecimal(0));
		}
        
        //查询连连支付的相关充值金额（ios ，wx ，pc）
		Map<String, BigDecimal> lianLianMap = accountLogMapper.queryAccoutLogByRechargeForLianLian(accountLogCnd);
		if (lianLianMap != null) {
			BigDecimal lianlianRechargeTotal=BigDecimal.ZERO;			
			if(lianLianMap.get("lianlianRechargeForPCTotal")!=null){
				BigDecimal lianlianRechargeForPCTotal = new BigDecimal(lianLianMap.get("lianlianRechargeForPCTotal").toString());
				lianlianRechargeTotal=lianlianRechargeTotal.add(lianlianRechargeForPCTotal);
			}
			if(lianLianMap.get("lianlianRechargeForWXTotal")!=null){
				BigDecimal lianlianRechargeForWXTotal = new BigDecimal(lianLianMap.get("lianlianRechargeForWXTotal").toString());
				lianlianRechargeTotal=lianlianRechargeTotal.add(lianlianRechargeForWXTotal);
			}
			if(lianLianMap.get("lianlianRechargeForAndroidTotal")!=null){
				BigDecimal lianlianRechargeForAndroidTotal = new BigDecimal(lianLianMap.get("lianlianRechargeForAndroidTotal").toString());
				lianlianRechargeTotal=lianlianRechargeTotal.add(lianlianRechargeForAndroidTotal);
			}
			if(lianLianMap.get("lianlianRechargeForIOSTotal")!=null){
				BigDecimal lianlianRechargeForIOSTotal = new BigDecimal(lianLianMap.get("lianlianRechargeForIOSTotal").toString());
				lianlianRechargeTotal=lianlianRechargeTotal.add(lianlianRechargeForIOSTotal);
			}
			resultMap.put("lianlianRechargeTotal", lianlianRechargeTotal);
			resultMap.putAll(lianLianMap);
		}else{
			BigDecimal lianlianRechargeForPCTotal = BigDecimal.ZERO;	
			BigDecimal lianlianRechargeForWXTotal = BigDecimal.ZERO;
			BigDecimal lianlianRechargeForAndroidTotal = BigDecimal.ZERO;
			BigDecimal lianlianRechargeForIOSTotal = BigDecimal.ZERO;
			BigDecimal lianlianRechargeTotal = BigDecimal.ZERO;	
			lianlianRechargeTotal=lianlianRechargeTotal.add(lianlianRechargeForPCTotal);
			lianlianRechargeTotal=lianlianRechargeTotal.add(lianlianRechargeForWXTotal);
			lianlianRechargeTotal=lianlianRechargeTotal.add(lianlianRechargeForAndroidTotal);
			lianlianRechargeTotal=lianlianRechargeTotal.add(lianlianRechargeForIOSTotal);
			lianlianRechargeTotal=lianlianRechargeTotal.add(lianlianRechargeTotal);
			resultMap.put("lianlianRechargeTotal", lianlianRechargeTotal);
			resultMap.put("lianlianRechargeForPCTotal", lianlianRechargeForPCTotal);
			resultMap.put("lianlianRechargeForWXTotal", lianlianRechargeForWXTotal);
			resultMap.put("lianlianRechargeForAndroidTotal", lianlianRechargeForAndroidTotal);
			resultMap.put("lianlianRechargeForIOSTotal", lianlianRechargeForIOSTotal);
		}
		//查询网银支付的相关充值金额（ios ，wx ，pc）
		Map<String, BigDecimal> wyMap = accountLogMapper.queryAccoutLogByRechargeForWy(accountLogCnd);
		if (wyMap != null) {
			BigDecimal wyRechargeTotal=BigDecimal.ZERO;			
			if(wyMap.get("wyRechargeForPCTotal")!=null){
				BigDecimal wyRechargeForPCTotal = new BigDecimal(wyMap.get("wyRechargeForPCTotal").toString());
				wyRechargeTotal=wyRechargeTotal.add(wyRechargeForPCTotal);
			}
			if(wyMap.get("wyRechargeForWXTotal")!=null){
				BigDecimal wyRechargeForWXTotal = new BigDecimal(wyMap.get("wyRechargeForWXTotal").toString());
				wyRechargeTotal=wyRechargeTotal.add(wyRechargeForWXTotal);
			}
			if(wyMap.get("wyRechargeForAndroidTotal")!=null){
				BigDecimal wyRechargeForAndroidTotal = new BigDecimal(wyMap.get("wyRechargeForAndroidTotal").toString());
				wyRechargeTotal=wyRechargeTotal.add(wyRechargeForAndroidTotal);
			}
			if(wyMap.get("wyRechargeForIOSTotal")!=null){
				BigDecimal wyRechargeForIOSTotal = new BigDecimal(wyMap.get("wyRechargeForIOSTotal").toString());
				wyRechargeTotal=wyRechargeTotal.add(wyRechargeForIOSTotal);
			}
			resultMap.put("wyRechargeTotal", wyRechargeTotal);
			resultMap.putAll(wyMap);
		}else{
			BigDecimal wyRechargeForPCTotal = BigDecimal.ZERO;	
			BigDecimal wyRechargeForWXTotal = BigDecimal.ZERO;
			BigDecimal wyRechargeForAndroidTotal = BigDecimal.ZERO;
			BigDecimal wyRechargeForIOSTotal = BigDecimal.ZERO;
			BigDecimal wyRechargeTotal = BigDecimal.ZERO;	
			wyRechargeTotal=wyRechargeTotal.add(wyRechargeForPCTotal);
			wyRechargeTotal=wyRechargeTotal.add(wyRechargeForWXTotal);
			wyRechargeTotal=wyRechargeTotal.add(wyRechargeForAndroidTotal);
			wyRechargeTotal=wyRechargeTotal.add(wyRechargeForIOSTotal);
			wyRechargeTotal=wyRechargeTotal.add(wyRechargeTotal);
			resultMap.put("wyRechargeTotal", wyRechargeTotal);
			resultMap.put("wyRechargeForPCTotal", wyRechargeForPCTotal);
			resultMap.put("wyRechargeForWXTotal", wyRechargeForWXTotal);
			resultMap.put("wyRechargeForAndroidTotal", wyRechargeForAndroidTotal);
			resultMap.put("wyRechargeForIOSTotal", wyRechargeForIOSTotal);
		}

		return resultMap;
	}

	@Transactional(rollbackFor = Throwable.class)
	public Map<String, BigDecimal> queryAccoutLogByUserName(String userName) throws Exception {
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		Integer userId = memberMapper.getMemberIdByUserName(userName);
		if (userId != null) {
			// 查询净值额度
			UserNetValue userNetValue = new UserNetValue();
			userNetValue.setUserid(userId);
			accountNetValueMapper.callGetUserNetMoneyLimit(userNetValue);
			map.put("netMoneyLimit", userNetValue.getNetMoneyLimit()); // 净值额度
			// 待还总额
			BigDecimal noRepayTotal = bRepaymentRecordMapper.queryNoRepayTotal(userId);
			if (noRepayTotal == null) {
				map.put("noRepayTotal", new BigDecimal(0));
			} else {
				map.put("noRepayTotal", noRepayTotal);
			}

		}
		return map;
	}
}
