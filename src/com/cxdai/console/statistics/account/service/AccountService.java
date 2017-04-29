package com.cxdai.console.statistics.account.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.borrow.manage.mapper.BRepaymentRecordMapper;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.fix.mapper.FixBorrowMapper;
import com.cxdai.console.fix.vo.FixStaticVo;
import com.cxdai.console.statistics.account.entity.Account;
import com.cxdai.console.statistics.account.entity.AccountLog;
import com.cxdai.console.statistics.account.mapper.AccountLogMapper;
import com.cxdai.console.statistics.account.mapper.AccountMapper;
import com.cxdai.console.statistics.account.vo.AccountCnd;
import com.cxdai.console.statistics.account.vo.AccountVo;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.MoneyUtil;

/**
 * <p>
 * Description:账号接口的实现类<br />
 * </p>
 * 
 * @title AccountServiceImpl.java
 * @package com.cxdai.account.service.impl
 * @author justin.xu
 * @version 0.1 2014年4月16日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class AccountService {

	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private AccountLogMapper accountLogMapper;

	@Autowired
	private BRepaymentRecordMapper bRepaymentRecordMapper;
	
	@Autowired
	private FixBorrowMapper fixBorrowMapper;


	public AccountVo queryAccountByUserId(Integer memberId) throws Exception {
		AccountCnd accountCnd = new AccountCnd();
		accountCnd.setUserId(memberId);
		List<AccountVo> accountList = accountMapper.queryAccountList(accountCnd);
		if (null != accountList && accountList.size() == 1) {
			return accountList.get(0);
		}
		return null;
	}


	public AccountVo queryAccountByUserIdForUpdate(Integer memberId) throws Exception {
		return accountMapper.queryAccountByUserIdForUpdate(memberId);
	}


	public Map<String, Object> platformAccount() throws Exception {
		Map<String, Object> map = accountMapper.platformAccount();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (map != null) {
			if (map.get("sum_total") != null && !map.get("sum_total").equals("")) {
				resultMap.put("sum_total", MoneyUtil.fmtMoneyByDot(new BigDecimal(map.get("sum_total").toString())));
			}
			if (map.get("sum_draw_money") != null && !map.get("sum_draw_money").equals("")) {
				resultMap.put("sum_draw_money", MoneyUtil.fmtMoneyByDot(new BigDecimal(map.get("sum_draw_money").toString())));
			}
			if (map.get("sum_no_draw_money") != null && !map.get("sum_no_draw_money").equals("")) {
				resultMap.put("sum_no_draw_money", MoneyUtil.fmtMoneyByDot(new BigDecimal(map.get("sum_no_draw_money").toString())));
			}
			if (map.get("sum_no_use_money") != null && !map.get("sum_no_use_money").equals("")) {
				resultMap.put("sum_no_use_money", MoneyUtil.fmtMoneyByDot(new BigDecimal(map.get("sum_no_use_money").toString())));
			}
			if (map.get("sum_collection") != null && !map.get("sum_collection").equals("")) {
				resultMap.put("sum_collection", MoneyUtil.fmtMoneyByDot(new BigDecimal(map.get("sum_collection").toString())));
			}
			if (map.get("sum_first_borrow_use_money") != null && !map.get("sum_first_borrow_use_money").equals("")) {
				resultMap.put("sum_first_borrow_use_money", MoneyUtil.fmtMoneyByDot(new BigDecimal(map.get("sum_first_borrow_use_money").toString())));
			}
		}
		// 可用资金统计，其中账户总额小于50元的不统计
		BigDecimal usermoneyTotal = accountMapper.platformUserMoneyTotal();
		resultMap.put("sum_use_money", usermoneyTotal);
		// 定期宝余额统计
		FixStaticVo fixStaticVo= fixBorrowMapper.queryStaticBorrow();
		resultMap.put("sum_fix_use_money", fixStaticVo.getAccountUserMoney());
		return resultMap;
	}


	public Page queryAccountUnusualForPage(AccountCnd accountCnd, int pageSize, int curPage) throws Exception {
		Page page = new Page(curPage, pageSize);
		int totalCount = accountMapper.queryAccountUnusualForCounts(accountCnd);
		page.setTotalCount(totalCount);
		List<AccountVo> list = accountMapper.queryAccountUnusualForPage(accountCnd, page);
		page.setResult(list);
		return page;
	}


	public Page queryAccountListForPage(AccountCnd accountCnd, int pageSize, int curPage) throws Exception {
		Page page = new Page(curPage, pageSize);
		accountCnd.setUserId(null);
		int totalCount = accountMapper.queryAccountCount(accountCnd);
		page.setTotalCount(totalCount);
		List<AccountVo> list = accountMapper.queryAccountList(accountCnd, page);
		page.setResult(list);
		return page;
	}


	@Transactional(rollbackFor = Throwable.class)
	public Map<String, Object> getIsToDrawInfo(int userId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		// 待还总额
		BigDecimal noRepayTotal = bRepaymentRecordMapper.queryNoRepayTotal(userId);
		if (noRepayTotal == null || noRepayTotal.compareTo(new BigDecimal(0)) == 0) {
			// 查询账户并锁定
			AccountVo accountVo = accountMapper.queryAccountByUserIdForUpdate(userId);
			map.put("noDrawMoney", accountVo.getNoDrawMoney().setScale(2, BigDecimal.ROUND_DOWN));
			// 查询最新一笔受限为0的id
			Integer accountLogId = accountLogMapper.queryMaxIdForNoDrawByUserId(userId);
			if (accountLogId == null) {
				map.put("toDraw", new BigDecimal("0"));
				return map;
			}
			// 查询最新一笔受限为0之后的受限增加金额总计（资金类型：线上充值、现金行权、网站奖励、网站充值、活动奖励发放、线下充值奖励、线下充值、积分兑换工资）
			BigDecimal noDrawAddTotal = accountLogMapper.queryNoDrawAddByUserId(userId, accountLogId);
			if (noDrawAddTotal == null) {
				noDrawAddTotal = new BigDecimal(0);
			}
			if (noDrawAddTotal.compareTo(new BigDecimal(0)) == -1) {
				noDrawAddTotal = new BigDecimal(0);
			}
			// 查询最新一笔受限为0之后的受限减少金额总计（资金类型：投标成功、还款扣除、债权转让复审通过，转让回款成功、投标直通车认购成功）
			BigDecimal noDraReduceTotal = accountLogMapper.queryNoDrawReduceByUserId(userId, accountLogId);
			if (noDraReduceTotal == null) {
				noDraReduceTotal = new BigDecimal(0);
			}
			if (noDraReduceTotal.compareTo(new BigDecimal(0)) == -1) {
				noDraReduceTotal = new BigDecimal(0);
			}
			// 理论受限金额 = 受限增加金额总计 - 受限减少金额总计
			BigDecimal result1 = noDrawAddTotal.subtract(noDraReduceTotal);
			if (result1.compareTo(new BigDecimal(0)) == -1) {
				result1 = new BigDecimal(0);
			}
			// 当前受限金额 大于 理论受限金额
			if (accountVo.getNoDrawMoney().compareTo(result1) == 1) {
				// 可转金额
				BigDecimal toDraw = accountVo.getNoDrawMoney().subtract(result1);
				map.put("toDraw", toDraw.setScale(2, BigDecimal.ROUND_DOWN));
			} else {
				map.put("toDraw", new BigDecimal("0"));
			}
		} else {
			// 查询账户并锁定
			AccountVo accountVo = accountMapper.queryAccountByUserIdForUpdate(userId);
			map.put("noDrawMoney", accountVo.getNoDrawMoney().setScale(2, BigDecimal.ROUND_DOWN));
			map.put("toDraw", new BigDecimal("0"));
		}
		return map;
	}


	@Transactional(rollbackFor = Throwable.class)
	public String accountToDraw(int userId) throws Exception {
		// 待还总额
		BigDecimal noRepayTotal = bRepaymentRecordMapper.queryNoRepayTotal(userId);
		if (noRepayTotal == null || noRepayTotal.compareTo(new BigDecimal(0)) == 0) {
			// 查询账户并锁定
			AccountVo accountVo = accountMapper.queryAccountByUserIdForUpdate(userId);
			// 查询最新一笔受限为0的id
			Integer accountLogId = accountLogMapper.queryMaxIdForNoDrawByUserId(userId);
			if (accountLogId == null) {
				return "当前受限金额无法转可提！";
			}
			// 查询最新一笔受限为0之后的受限增加金额总计（资金类型：线上充值、现金行权、网站奖励、网站充值、活动奖励发放、线下充值奖励、线下充值、积分兑换工资）
			BigDecimal noDrawAddTotal = accountLogMapper.queryNoDrawAddByUserId(userId, accountLogId);
			if (noDrawAddTotal == null) {
				noDrawAddTotal = new BigDecimal(0);
			}
			if (noDrawAddTotal.compareTo(new BigDecimal(0)) == -1) {
				noDrawAddTotal = new BigDecimal(0);
			}
			// 查询最新一笔受限为0之后的受限减少金额总计（资金类型：投标成功、还款扣除、债权转让复审通过，转让回款成功、投标直通车认购成功）
			BigDecimal noDraReduceTotal = accountLogMapper.queryNoDrawReduceByUserId(userId, accountLogId);
			if (noDraReduceTotal == null) {
				noDraReduceTotal = new BigDecimal(0);
			}
			if (noDraReduceTotal.compareTo(new BigDecimal(0)) == -1) {
				noDraReduceTotal = new BigDecimal(0);
			}
			// 理论受限金额 = 受限增加金额总计 - 受限减少金额总计
			BigDecimal result1 = noDrawAddTotal.subtract(noDraReduceTotal);
			if (result1.compareTo(new BigDecimal(0)) == -1) {
				result1 = new BigDecimal(0);
			}
			// 当前受限金额 大于 理论受限金额
			if (accountVo.getNoDrawMoney().compareTo(result1) == 1) {
				// 可转金额
				BigDecimal toDraw = accountVo.getNoDrawMoney().subtract(result1);
				accountVo.setDrawMoney(accountVo.getDrawMoney().add(toDraw));
				accountVo.setNoDrawMoney(accountVo.getNoDrawMoney().subtract(toDraw));
				// 更新账户，将可转金额 转入可提中，受限减少
				Account account = new Account();
				BeanUtils.copyProperties(accountVo, account);
				accountMapper.updateEntity(account);
				// 生成资金日志记录
				AccountLog accountLog = new AccountLog();
				accountLog.setAddip("0.0.0.0");
				accountLog.setAddtime(DateUtils.getTime());
				accountLog.setCollection(account.getCollection());
				accountLog.setUseMoney(account.getUseMoney());
				accountLog.setNoUseMoney(account.getNoUseMoney());
				accountLog.setTotal(account.getTotal());
				accountLog.setFirstBorrowUseMoney(account.getFirstBorrowUseMoney());
				accountLog.setMoney(toDraw);// 交易金额
				accountLog.setRemark("异常受限转可提");
				accountLog.setToUser(-1);//
				accountLog.setUserId(account.getUserId());
				accountLog.setType("unusual_no_draw_money_to_draw_money");
				accountLog.setDrawMoney(account.getDrawMoney());
				accountLog.setNoDrawMoney(account.getNoDrawMoney());

				accountLogMapper.insertEntity(accountLog);
				return "当前受限转可提成功，转可提金额为：" + toDraw.setScale(2, BigDecimal.ROUND_DOWN) + "元！";
			} else {
				return "当前可转提金额为：0元，无法转可提！";
			}
		} else {
			return "该用户还有未还款的借款标，需还清后才能操作该功能！";
		}
	}
}
