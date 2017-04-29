package com.cxdai.console.firstborrow.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.account.recharge.entity.Account;
import com.cxdai.console.account.recharge.mapper.BaseAccountMapper;
import com.cxdai.console.base.entity.FirstTenderReal;
import com.cxdai.console.base.entity.FirstUnlockAppr;
import com.cxdai.console.base.mapper.BaseFirstTenderRealMapper;
import com.cxdai.console.base.mapper.BaseFirstUnlockApprMapper;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.firstborrow.mapper.FirstTenderRealMapper;
import com.cxdai.console.firstborrow.vo.FirstTenderRealCnd;
import com.cxdai.console.firstborrow.vo.FirstTenderRealVo;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.statistics.account.mapper.AccountNetValueMapper;
import com.cxdai.console.statistics.account.service.AccountLogService;
import com.cxdai.console.statistics.account.service.AccountService;
import com.cxdai.console.statistics.account.vo.AccountVo;
import com.cxdai.console.statistics.account.vo.DrawMoneyToNoDrawCnd;
import com.cxdai.console.util.WebServiceMD5;
import com.cxdai.console.util.exception.AppException;

/**
 * <p>
 * Description:优先投标计划最终认购业务实现类<br />
 * </p>
 * 
 * @title FirstTenderRealServiceImpl.java
 * @package com.cxdai.console.first.service.impl
 * @author justin.xu
 * @version 0.1 2014年10月28日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class FirstTenderRealService{

	@Autowired
	private BaseFirstTenderRealMapper baseFirstTenderRealMapper;
	@Autowired
	private BaseFirstUnlockApprMapper baseFirstUnlockApprMapper;
	@Autowired
	private FirstTenderRealMapper firstTenderRealMapper;
	@Autowired
	private AccountService accountService;
	@Autowired
	private BaseAccountMapper baseAccountMapper;
	@Autowired
	private AccountLogService accountLogService;
	@Autowired
	private AccountNetValueMapper accountNetValueMapper;
	@Autowired
	private FirstBorrowService firstBorrowService;
//	@Autowired
//	private BlackListSevice blackListSevice;

	
	public String saveUnLock(Integer firstTenderRealId, Integer memberId) throws Exception {
		String result = "success";
		if (null == firstTenderRealId) {
			return "参数错误";
		}
		FirstTenderRealCnd firstTenderRealCnd = new FirstTenderRealCnd();
		firstTenderRealCnd.setId(String.valueOf(firstTenderRealId));
		firstTenderRealCnd.setUserId(String.valueOf(memberId));
		firstTenderRealCnd.setLockedRecordYn(BusinessConstants.LOCKED_RECORD_YES);
		List<FirstTenderRealVo> realList = firstTenderRealMapper.queryFirstTenderRealList(firstTenderRealCnd);
		if (null == realList || realList.size() != 1) {
			return "未找到此直通车数据!";
		}
		FirstTenderRealVo firstTenderRealVo = realList.get(0);
		if (firstTenderRealVo.getStatus() == 2) {
			return "该直通车解锁中，请确认数据！";
		}
		if (firstTenderRealVo.getStatus() == 1) {
			return "该直通车已解锁，请勿重复操作！";
		}
		// 如果不存在直通车下车黑名单记录，则需判断是否过最低锁定期
//		if (!blackListSevice.judgeBlackByUserIdAndType(memberId, BusinessConstants.BLACK_TYPE_FIRST_DEBUS)) {
//			if (null == firstTenderRealVo.getUnLockYn() || !"Y".equals(firstTenderRealVo.getUnLockYn())) {
//				return "未过最低锁定期，请确认数据！";
//			}
//		}
		// 更新此期数此用户的状态为 解锁中
		firstTenderRealVo.setStatus(Constants.TENDER_REAL_STATUS_UNLOCKING);
		FirstTenderReal firstTenderReal = new FirstTenderReal();
		BeanUtils.copyProperties(firstTenderRealVo, firstTenderReal);
		baseFirstTenderRealMapper.updateEntity(firstTenderReal);
		return result;
	}

	
	public Page queryPageListByCnd(FirstTenderRealCnd firstTenderRealCnd, Integer curPage, Integer pageSize) throws Exception {
		Page page = new Page(curPage, pageSize);
		int totalCount = firstTenderRealMapper.queryFirstTenderRealCount(firstTenderRealCnd);
		page.setTotalCount(totalCount);
		List<FirstTenderRealVo> list = firstTenderRealMapper.queryFirstTenderRealList(firstTenderRealCnd, page);
		page.setResult(list);
		return page;
	}

	
	public String saveApprovedPass(String firstTenderRealId, String remark, Integer userId, ShiroUser user, String ip) throws Exception {
		String result = "success";
		FirstTenderRealCnd firstTenderRealCnd = new FirstTenderRealCnd();
		firstTenderRealCnd.setId(firstTenderRealId);
		List<FirstTenderRealVo> list = firstTenderRealMapper.queryFirstTenderRealList(firstTenderRealCnd);
		if (null == list || list.size() != 1) {
			return "未找到此直通车认购数据";
		}
		FirstTenderRealVo firstTenderRealVo = list.get(0);
		if (firstTenderRealVo.getStatus() != Constants.TENDER_REAL_STATUS_UNLOCKING || !firstTenderRealVo.getUserId().equals(userId)) {
			return "认购记录非锁定中,请刷新页面或稍后重试";
		}
		// 调用webService执行审核
		// 验证数据是否正确
		Map<String, Object> validateKeyMap = new HashMap<>();
		validateKeyMap.put("firstTenderRealId", firstTenderRealVo.getId());
		validateKeyMap.put("memberId", userId);
		validateKeyMap.put("ip", ip);
		validateKeyMap.put("approveUserId", user.getUserId());
		validateKeyMap.put("approveRemark", remark);
		saveUnlockManualApprove(firstTenderRealVo.getId(), userId, ip, user.getUserId(), remark, WebServiceMD5.encodeParams(validateKeyMap));
		return result;
	}

	public String saveUnlockManualApprove(Integer firstTenderRealId, Integer memberId, String ip, Integer approveUserId, String approveRemark, String serviceKey) throws Exception {
		// 验证数据是否正确
		Map<String, Object> validateKeyMap = new HashMap<>();
		validateKeyMap.put("firstTenderRealId", firstTenderRealId);
		validateKeyMap.put("memberId", memberId);
		validateKeyMap.put("ip", ip);
		validateKeyMap.put("approveUserId", approveUserId);
		validateKeyMap.put("approveRemark", approveRemark);
		if (!WebServiceMD5.validateServiceKey(validateKeyMap, serviceKey)) {
			return "数据验签出错";
		}
		return this.saveUnLockApprove(firstTenderRealId, memberId, ip, approveUserId, approveRemark);
	}

	
	public String saveUnLockApprove(Integer firstTenderRealId, Integer memberId, String ip, Integer approveUserId, String approveRemark) throws Exception {
		String result = "success";
		if (null == firstTenderRealId) {
			return "参数错误";
		}
		FirstTenderRealCnd firstTenderRealCnd = new FirstTenderRealCnd();
		firstTenderRealCnd.setId(String.valueOf(firstTenderRealId));
		firstTenderRealCnd.setUserId(String.valueOf(memberId));
		// 锁定记录
		firstTenderRealCnd.setLockedRecordYn(BusinessConstants.LOCKED_RECORD_YES);
		List<FirstTenderRealVo> realList = firstTenderRealMapper.queryFirstTenderRealList(firstTenderRealCnd);
		if (null == realList || realList.size() != 1) {
			return "未找到此直通车数据!";
		}
		FirstTenderRealVo firstTenderRealVo = realList.get(0);
		if (firstTenderRealVo.getStatus() != Constants.TENDER_REAL_STATUS_UNLOCKING) {
			return "认购记录非锁定中,请刷新页面或稍后重试";
		}
		// 直通车可用余额
		BigDecimal realUseMoney = firstTenderRealVo.getUseMoney();
		// 更新此期数此用户的最终开通可用余额 = 0，状态为已失效
		updateFirstRealWhenUnLockApprove(firstTenderRealVo);
		if (realUseMoney.compareTo(new BigDecimal("0")) > 0) {
			// 更新用户帐号,将直通车可用余额返回到帐号
			AccountVo accountVo = updateAccountWhenUnLockApprove(firstTenderRealVo, realUseMoney);
			// 插入一条用户解锁资金明细记录
			packageAccountlogWhenUnLockApprove(ip, firstTenderRealVo, realUseMoney, accountVo);
		}
		// 判断用户的可提金额是否大于净值额度，如果大于，转入受限金额
		DrawMoneyToNoDrawCnd drawMoneyToNoDrawCnd = new DrawMoneyToNoDrawCnd();
		drawMoneyToNoDrawCnd.setUserid(memberId);
		drawMoneyToNoDrawCnd.setNetmoneytype(BusinessConstants.NET_TYPE_FIRST_UNLOCK_SUCCESS);
		drawMoneyToNoDrawCnd.setAddip(ip);
		drawMoneyToNoDrawCnd.setAccountlogType("net_draw_to_nodraw_first_unlock_success");
		drawMoneyToNoDrawCnd.setAccountlogRemark("直通车解锁之后,可提金额大于净值额度，可提金额转入受限金额。");
		accountNetValueMapper.dealDrawmoneyToNodraw(drawMoneyToNoDrawCnd);

		// 更新待收中的直通车状态 为已失效
		firstTenderRealMapper.updateCollectionFirstToInvalid(firstTenderRealId, memberId);
		// 根据解锁变动情况更新 实际直通车总额字段
		firstBorrowService.updateRealAccountByUnlock(firstTenderRealVo.getAccount(), firstTenderRealVo.getFirstBorrowId());
		// 如果实际金额为0，更新直通车记录的状态为失效
		// firstBorrowService.updateFirstBorrowStatusByUnlock(firstBorrowId);
		// 向直通车解锁审核表中插入一条记录
		insertFirstUnlockAppr(firstTenderRealVo, approveUserId, approveRemark);
		return result;
	}

	/**
	 * <p>
	 * Description:向直通车解锁审核表中插入一条记录<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年7月28日
	 * @param firstTenderRealVo
	 * @throws Exception void
	 */
	private void insertFirstUnlockAppr(FirstTenderRealVo firstTenderRealVo, Integer approveUserId, String approveRemark) throws Exception {
		FirstUnlockAppr firstUnlockAppr = new FirstUnlockAppr();
		firstUnlockAppr.setApprTime(new Date());
		if (null != approveUserId) {
			firstUnlockAppr.setUserId(approveUserId);
		}
		if (null != approveRemark && !"".equals(approveRemark.trim())) {
			firstUnlockAppr.setRemark(approveRemark);
		} else {
			firstUnlockAppr.setRemark("解锁审核，系统自动通过!");
		}
		firstUnlockAppr.setStatus(Constants.APPROVE_PASS);
		firstUnlockAppr.setFirstTenderRealId(firstTenderRealVo.getId());
		baseFirstUnlockApprMapper.insertEntity(firstUnlockAppr);
	}

	/**
	 * <p>
	 * Description:解锁审核时：插入一条用户解锁资金明细记录<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年7月25日
	 * @param ip
	 * @param firstTenderRealVo
	 * @param realUseMoney
	 * @param accountVo
	 * @throws Exception void
	 */
	private void packageAccountlogWhenUnLockApprove(String ip, FirstTenderRealVo firstTenderRealVo, BigDecimal realUseMoney, AccountVo accountVo) throws Exception {
		StringBuffer firstLogRemark = new StringBuffer();
		firstLogRemark.append("投标直通车解锁成功，可用增加,直通车余额减少成功。");
		accountLogService.saveAccountLogByParams(accountVo, firstTenderRealVo.getUserId(), realUseMoney, firstLogRemark.toString(), ip, "first_unlock_success", Constants.ACCOUNT_LOG_ID_TYPE_FIRST,
				firstTenderRealVo.getFirstBorrowId(), firstTenderRealVo.getFirstBorrowName());
	}

	/**
	 * <p>
	 * Description:解锁审核时：更新用户帐号,将直通车可用余额返回到帐号<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年7月25日
	 * @param firstTenderRealVo
	 * @param realUseMoney
	 * @throws Exception void
	 */
	private AccountVo updateAccountWhenUnLockApprove(FirstTenderRealVo firstTenderRealVo, BigDecimal realUseMoney) throws Exception {
		AccountVo accountVo = accountService.queryAccountByUserIdForUpdate(firstTenderRealVo.getUserId());
		accountVo.setUseMoney(accountVo.getUseMoney().add(realUseMoney));
		accountVo.setDrawMoney(accountVo.getDrawMoney().add(realUseMoney));
		accountVo.setFirstBorrowUseMoney(accountVo.getFirstBorrowUseMoney().subtract(realUseMoney));
		Account account = new Account();
		BeanUtils.copyProperties(accountVo, account);
		baseAccountMapper.updateEntity(account);
		return accountVo;
	}

	/**
	 * <p>
	 * Description:解锁审核时：更新此期数此用户的最终开通可用余额 = 0，状态为已失效<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年7月25日
	 * @param firstTenderRealVo
	 * @throws Exception
	 * @throws AppException void
	 */
	private void updateFirstRealWhenUnLockApprove(FirstTenderRealVo firstTenderRealVo) throws Exception {
		firstTenderRealVo.setUseMoney(BigDecimal.ZERO);
		firstTenderRealVo.setStatus(Constants.TENDER_REAL_STATUS_INVALID);
		FirstTenderReal firstTenderReal = new FirstTenderReal();
		BeanUtils.copyProperties(firstTenderRealVo, firstTenderReal);
		baseFirstTenderRealMapper.updateEntity(firstTenderReal);
	}

	
	public String saveApprovedReject(String firstTenderRealId, String remark, Integer userId, ShiroUser user) throws Exception {
		String result = "success";
		FirstTenderRealCnd firstTenderRealCnd = new FirstTenderRealCnd();
		firstTenderRealCnd.setId(firstTenderRealId);
		// 锁定记录
		firstTenderRealCnd.setLockedRecordYn(BusinessConstants.LOCKED_RECORD_YES);
		List<FirstTenderRealVo> list = firstTenderRealMapper.queryFirstTenderRealList(firstTenderRealCnd);
		if (null == list || list.size() != 1) {
			return "未找到此直通车认购数据";
		}
		FirstTenderRealVo firstTenderRealVo = list.get(0);
		if (firstTenderRealVo.getStatus() != Constants.TENDER_REAL_STATUS_UNLOCKING || !firstTenderRealVo.getUserId().equals(userId)) {
			return "认购记录非锁定中,请刷新页面或稍后重试";
		}
		// 更新直通车认购记录为正常状态
		FirstTenderReal firstTenderReal = new FirstTenderReal();
		BeanUtils.copyProperties(firstTenderRealVo, firstTenderReal);
		firstTenderReal.setStatus(Constants.TENDER_REAL_STATUS_AVAILABLE);
		baseFirstTenderRealMapper.updateEntity(firstTenderReal);
		// 插入一条审核记录
		packageFirstUnlockByReject(remark, firstTenderRealVo, user);
		return result;
	}

	
	public String saveUnlockManual(Integer firstTenderRealId, Integer memberId) throws Exception {
		// 申请解锁
		Map<String, Object> validateKeyMap = new HashMap<>();
		validateKeyMap.put("firstTenderRealId", firstTenderRealId);
		validateKeyMap.put("memberId", memberId);
		return saveUnLockManual(firstTenderRealId, memberId, WebServiceMD5.encodeParams(validateKeyMap));
	}

	public String saveUnLockManual(Integer firstTenderRealId, Integer memberId, String serviceKey) throws Exception {
		// 验证数据是否正确
		Map<String, Object> validateKeyMap = new HashMap<>();
		validateKeyMap.put("firstTenderRealId", firstTenderRealId);
		validateKeyMap.put("memberId", memberId);
		if (!WebServiceMD5.validateServiceKey(validateKeyMap, serviceKey)) {
			return "数据验签出错";
		}
		// 执行申请解锁
		return this.saveUnLock(firstTenderRealId, memberId);
	}

	/**
	 * <p>
	 * Description:插入一条审核拒绝记录<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年10月29日
	 * @param remark
	 * @param firstTenderRealVo
	 * @throws Exception void
	 */
	private void packageFirstUnlockByReject(String remark, FirstTenderRealVo firstTenderRealVo, ShiroUser user) throws Exception {
		FirstUnlockAppr firstUnlockAppr = new FirstUnlockAppr();
		firstUnlockAppr.setUserId(user.getUserId());
		firstUnlockAppr.setApprTime(new Date());
		firstUnlockAppr.setStatus(Constants.APPROVE_REJECT);
		firstUnlockAppr.setRemark(remark);
		firstUnlockAppr.setFirstTenderRealId(firstTenderRealVo.getId());
		baseFirstUnlockApprMapper.insertEntity(firstUnlockAppr);
	}

	
	public BigDecimal queryFirstTenderRealAccount(FirstTenderRealCnd firstTenderRealCnd) {
		return firstTenderRealMapper.queryFirstTenderRealAccount(firstTenderRealCnd);
	}

	
	public List<FirstTenderRealVo> findFirstTenderRealVoToExcel(FirstTenderRealCnd firstTenderRealCnd) throws Exception {
		return firstTenderRealMapper.findFirstTenderRealVoToExcel(firstTenderRealCnd);
	}

	
	public BigDecimal queryFirstUseMoneyTotal() {
		return firstTenderRealMapper.queryFirstUseMoneyTotal();
	}

}
