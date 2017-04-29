package com.cxdai.console.account.reward.service;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.account.recharge.entity.Account;
import com.cxdai.console.account.recharge.mapper.BaseAccountMapper;
import com.cxdai.console.account.reward.entity.NetvalueLog;
import com.cxdai.console.account.reward.mapper.NetvalueLogMapper;
import com.cxdai.console.statistics.account.entity.UserNetValue;
import com.cxdai.console.statistics.account.mapper.AccountNetValueMapper;
import com.cxdai.console.statistics.account.service.AccountLogService;
import com.cxdai.console.statistics.account.service.AccountService;
import com.cxdai.console.statistics.account.vo.AccountVo;
import com.cxdai.console.util.HttpTookit;

/**
 * 
 * <p>
 * Description:网站奖励业务类<br />
 * </p>
 * @title WebAwardService.java
 * @package com.cxdai.console.account.service
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class WebAwardService {

	@Autowired
	private BaseAccountMapper baseAccountMapper;
	@Autowired
	private AccountService accountService;
	@Autowired
	private AccountLogService accountLogService;
	@Autowired
	private AccountNetValueMapper accountNetValueMapper;
	@Autowired
	private NetvalueLogMapper netvalueLogMapper;
	/**
	 * 
	 * <p>
	 * Description:网站奖励<br />
	 * </p>
	 * @param userId
	 * @param money
	 * @return
	 * @throws Exception String
	 */
	public String webaward(int userId, BigDecimal money, String remark,HttpServletRequest request) throws Exception{
		Account account = baseAccountMapper.queryByUserIdForUpdate(userId);
		String msg = "";
		if (money.compareTo(new BigDecimal(0)) == -1) {
			msg = "网站扣除";
		} else {
			msg = "网站奖励";
		}
		if (account != null) {
			if (money.compareTo(new BigDecimal(0)) == -1) {
				account.setUseMoney(account.getUseMoney().add(money));
				account.setTotal(account.getTotal().add(money));
				BigDecimal remain = account.getNoDrawMoney().add(money);
				if (remain.compareTo(new BigDecimal(0)) == -1) {
					account.setNoDrawMoney(new BigDecimal(0));
					BigDecimal remain2 = account.getDrawMoney().add(remain);
					if (remain2.compareTo(new BigDecimal(0)) == -1) {
						account.setDrawMoney(new BigDecimal(0));
						account.setNoDrawMoney(account.getNoDrawMoney().add(remain2));
					} else {
						account.setDrawMoney(account.getDrawMoney().add(remain));
					}
				} else {
					account.setNoDrawMoney(account.getNoDrawMoney().add(money));
				}
				BigDecimal money2 = new BigDecimal(0).subtract(money);
				if (remark == null || remark.equals("")) {
					remark = "网站扣除，资产总额和可用余额减少" + money2 + "元。";
				}
			} else {
				if (remark == null || remark.equals("")) {
					remark = "网站奖励成功，资金已入账";
				}
				account.setUseMoney(account.getUseMoney().add(money));
				account.setTotal(account.getTotal().add(money));
				account.setNoDrawMoney(account.getNoDrawMoney().add(money));
			}
			baseAccountMapper.updateEntity(account);
			AccountVo accountVo = accountService.queryAccountByUserId(userId);
			accountLogService.saveAccountLogByParams(accountVo, userId, money, remark, HttpTookit.getRealIpAddr(request), "web_award", null, null, null);
			// 计算净值额度
			UserNetValue userNetValue = new UserNetValue();
			userNetValue.setUserid(userId);
			accountNetValueMapper.callGetUserNetMoneyLimit(userNetValue);
			if (userNetValue != null && userNetValue.getNetMoneyLimit() != null) {
				if (accountVo.getDrawMoney().compareTo(userNetValue.getNetMoneyLimit()) == 1) {
					BigDecimal remain2 = accountVo.getDrawMoney().subtract(userNetValue.getNetMoneyLimit());
					accountVo.setDrawMoney(userNetValue.getNetMoneyLimit());
					accountVo.setNoDrawMoney(accountVo.getNoDrawMoney().add(remain2));
					account = new Account();
					BeanUtils.copyProperties(accountVo, account);
					baseAccountMapper.updateEntity(account);
					accountLogService.saveAccountLogByParams(accountVo, userId, remain2, msg + "之后，可提金额大于净值额度，可提金额转入受限金额。", HttpTookit.getRealIpAddr(request), "net_draw_to_nodraw", null, null, null);
				}
				NetvalueLog netvalueLog = new NetvalueLog();
				netvalueLog.setUserId(userId);
				netvalueLog.setNetMoneyLimit(userNetValue.getNetMoneyLimit());
				netvalueLog.setNetWaitTopayCount(userNetValue.getNetWaitToPayCount());
				netvalueLog.setWaitReceiveCapital(userNetValue.getWaitReceiveCapital());
				netvalueLog.setTenderLockAccountTotal(userNetValue.getTenderLockAccountTotal());
				netvalueLog.setRepaymentAccountTotal(userNetValue.getRepaymentAccountTotal());
				netvalueLog.setFirstFreezeAccount(userNetValue.getFirstFreezeAccount());
				netvalueLog.setUseMoney(accountVo.getUseMoney());
				netvalueLog.setFirstBorrowUseMoney(accountVo.getFirstBorrowUseMoney());
				netvalueLog.setTakeCashFreezeAccount(userNetValue.getTakeCashFreezeAccount());
				netvalueLog.setRemark(msg + "之后，可提金额大于净值额度，可提金额转入受限金额。");
				netvalueLog.setType(1); // 1：网站奖励
				netvalueLog.setAddip(HttpTookit.getRealIpAddr(request));
				netvalueLogMapper.insertEntity(netvalueLog);
			}
			return msg + "成功";
		} else {
			return msg + "失败";
		}
	}
}
