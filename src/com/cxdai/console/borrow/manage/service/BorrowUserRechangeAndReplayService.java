package com.cxdai.console.borrow.manage.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.account.recharge.entity.Account;
import com.cxdai.console.account.recharge.entity.RechargeRecord;
import com.cxdai.console.account.recharge.mapper.BaseAccountMapper;
import com.cxdai.console.account.recharge.mapper.BaseRechargeRecordMapper;
import com.cxdai.console.account.recharge.vo.RechargeRecordVo;
import com.cxdai.console.borrow.manage.vo.BorrowUserRechangeVo;
import com.cxdai.console.common.Constants;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.statistics.account.service.AccountLogService;
import com.cxdai.console.statistics.account.service.AccountService;
import com.cxdai.console.statistics.account.vo.AccountVo;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.ShiroUtils;
import com.cxdai.console.util.exception.AppException;

 

@Service
@Transactional(rollbackFor = Throwable.class)
public class BorrowUserRechangeAndReplayService {
	@Autowired
	private BaseAccountMapper baseAccountMapper;

	@Autowired
	private AccountLogService accountLogService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private BaseRechargeRecordMapper baseRechargeRecordMapper;

	 
	public String savepay(Integer memberId, BorrowUserRechangeVo borrowUserRechangeVo, String ip, ShiroUser shiroUser,String accountType) {
		String result = "success";
		if (null == borrowUserRechangeVo.getReChangeMoney() || borrowUserRechangeVo.getReChangeMoney().compareTo(new BigDecimal("0")) != 1) {
			return "充值金额必须大于0元！";
		}
		try {

			if(accountType != null && accountType.equals("1")){

				packageCustodyRechargeRecord(memberId, borrowUserRechangeVo, ip, shiroUser);
				RechargeRecordVo rechargeRecordVo = new RechargeRecordVo();
				rechargeRecordVo.setUserId(memberId);
				rechargeRecordVo.setMoney(borrowUserRechangeVo.getReChangeMoney());
				// 更新存管帐号的可用余额和总金额
				AccountVo accountVo = refreshCustodyAccountMoney(rechargeRecordVo);
				String remarkbuilder = "后台人工存管充值，资金已到账。操作人：" + ShiroUtils.currentUser().getUserName();

				accountLogService.saveEAccountLogByParams(accountVo, accountVo.getUserId(), rechargeRecordVo.getMoney(),
						remarkbuilder, ip,"cg_offline_recharge", null, null, null);
				return result;

			}else{
			   // 保存平台账号充值记录
				packageRechargeRecord(memberId, borrowUserRechangeVo, ip, shiroUser);
				RechargeRecordVo rechargeRecordVo = new RechargeRecordVo();
				rechargeRecordVo.setUserId(memberId);
				rechargeRecordVo.setMoney(borrowUserRechangeVo.getReChangeMoney());
				// 更新用户帐号的可用余额和总金额
				AccountVo accountVo = refreshAccountMoney(rechargeRecordVo);
				// 插入资金记录
				String remarkbuilder = "后台人工充值，资金已到账。操作人：" + ShiroUtils.currentUser().getUserName();

				accountLogService.saveAccountLogByParams(accountVo, accountVo.getUserId(), rechargeRecordVo.getMoney(), remarkbuilder, ip,
						"offline_recharge", null, null, null);
				return result;
			}

		} catch (Exception e) {
             e.printStackTrace();
		}

		return result;

	}

	/**
	 * <p>
	 * Description:更新用户帐号的可用余额和总金额<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年5月23日
	 * @param rechargeRecordVo
	 * @return
	 * @throws Exception
	 *             Account
	 */
	private AccountVo refreshAccountMoney(RechargeRecordVo rechargeRecordVo) throws Exception {
	 
		AccountVo accountVo = accountService.queryAccountByUserIdForUpdate(rechargeRecordVo.getUserId());

		accountVo.setUseMoney(accountVo.getUseMoney().add(rechargeRecordVo.getMoney()));
		accountVo.setNoDrawMoney(accountVo.getNoDrawMoney().add(rechargeRecordVo.getMoney()));
		accountVo.setTotal(accountVo.getTotal().add(rechargeRecordVo.getMoney()));
		Account account = new Account();
		BeanUtils.copyProperties(accountVo, account);
		Integer updateCount = baseAccountMapper.updateEntity(account);
		if (null == updateCount || updateCount == 0) {
			throw new AppException("帐号数据已变更,请刷新页面或稍后重试！");
		}
		return accountVo;
	}

	/**
	 * 更新存管帐号的可用余额和总金额<
	 * @param rechargeRecordVo
	 * @return
	 * @throws Exception
     */
	private AccountVo refreshCustodyAccountMoney(RechargeRecordVo rechargeRecordVo) throws Exception {

		AccountVo accountVo = accountService.queryAccountByUserIdForUpdate(rechargeRecordVo.getUserId());
		accountVo.seteUseMoney(accountVo.geteUseMoney().add(rechargeRecordVo.getMoney()));
		accountVo.setTotal(accountVo.getTotal().add(rechargeRecordVo.getMoney()));
		Account account = new Account();
		BeanUtils.copyProperties(accountVo, account);
		Integer updateCount = baseAccountMapper.updateEntity(account);
		if (null == updateCount || updateCount == 0) {
			throw new AppException("帐号数据已变更,请刷新页面或稍后重试！");
		}
		return accountVo;
	}

	/**
	 * <p>
	 * Description:保存充值记录<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年6月12日
	 * @throws Exception
	 *             void
	 */
	private void packageRechargeRecord(Integer userId, BorrowUserRechangeVo borrowUserRechangeVo, String ip, ShiroUser shiroUser) throws Exception {
		RechargeRecord rechargeRecord = new RechargeRecord();
		// 订单ID
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd-44461248-HHmmssSSS", Locale.US);
		String merOrderNum = sf.format(new Date()) + "_" + userId;
		rechargeRecord.setTradeNo(merOrderNum);
		rechargeRecord.setUserId(userId);
		rechargeRecord.setType(Constants.RECHARGE_TYPE_OFFLINE);
		rechargeRecord.setStatus(Constants.RECHARGE_STATUS_SUCCESS);
		rechargeRecord.setMoney(borrowUserRechangeVo.getReChangeMoney());
		rechargeRecord.setPayment("支付宝充值账号");
		rechargeRecord.setCardNum("44461248@qq.com");
		rechargeRecord.setBankUsername("王建章");
		rechargeRecord.setFee(new BigDecimal("0"));// 支付没有手续费
		rechargeRecord.setAddip(ip);
		rechargeRecord.setAddtime(DateUtils.getTime());
		rechargeRecord.setVersion(1);
		rechargeRecord.setVerifyUserid(shiroUser.getUserId());
		rechargeRecord.setVerifyTime(DateUtils.getTime());
		rechargeRecord.setVerifyUserid2(shiroUser.getUserId());
		rechargeRecord.setVerifyTime2(DateUtils.getTime());
		baseRechargeRecordMapper.insertEntity(rechargeRecord);
	}


	/**
	 * <p>
	 * Description:保存存管充值记录<br />
	 * </p>
	 */
	private void packageCustodyRechargeRecord(Integer userId, BorrowUserRechangeVo borrowUserRechangeVo, String ip, ShiroUser shiroUser) throws Exception {
		RechargeRecord rechargeRecord = new RechargeRecord();
		// 订单ID
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd-44461248-HHmmssSSS", Locale.US);
		String merOrderNum = sf.format(new Date()) + "_" + userId;
		rechargeRecord.setTradeNo(merOrderNum);
		rechargeRecord.setUserId(userId);
		rechargeRecord.setType(Constants.RECHARGE_TYPE_OFFLINE);
		rechargeRecord.setStatus(Constants.RECHARGE_STATUS_SUCCESS);
		rechargeRecord.setMoney(borrowUserRechangeVo.getReChangeMoney());
		rechargeRecord.setPayment("支付宝充值账号");
		rechargeRecord.setCardNum("44461248@qq.com");
		rechargeRecord.setBankUsername("王建章");
		rechargeRecord.setFee(new BigDecimal("0"));// 支付没有手续费
		rechargeRecord.setAddip(ip);
		rechargeRecord.setAddtime(DateUtils.getTime());
		rechargeRecord.setVersion(1);
		rechargeRecord.setVerifyUserid(shiroUser.getUserId());
		rechargeRecord.setVerifyTime(DateUtils.getTime());
		rechargeRecord.setVerifyUserid2(shiroUser.getUserId());
		rechargeRecord.setVerifyTime2(DateUtils.getTime());

		rechargeRecord.setOnlinetype(8);
		rechargeRecord.setIsCustody(1);
		rechargeRecord.setRemark("存管账户后台充值");
		baseRechargeRecordMapper.insertEntity(rechargeRecord);
	}
}
