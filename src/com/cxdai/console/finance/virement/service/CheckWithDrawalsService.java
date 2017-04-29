package com.cxdai.console.finance.virement.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.finance.virement.entity.CheckWithdrawals;
import com.cxdai.console.finance.virement.entity.WithdrawalsStatus;
import com.cxdai.console.finance.virement.mapper.CheckWithdrawalsMapper;
import com.cxdai.console.finance.virement.vo.CheckWithCnd;
import com.cxdai.console.finance.virement.vo.QueryPageCheckWithCnd;
import com.cxdai.console.finance.virement.vo.UpdateCheckWithCnd;
import com.cxdai.console.statistics.reconciliation.entity.AccountCheckCnd;
import com.cxdai.console.statistics.reconciliation.mapper.AccountCheckMapper;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.StringUtils;

@Service
public class CheckWithDrawalsService {

	@Autowired
	private CheckWithdrawalsMapper checkWithMapper;

	@Autowired
	private AccountCheckMapper accountCheckMapper;

	/**
	 * 分页获取每日提现对账汇总列表
	 * 
	 * @param request
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	public Page queryPageCheckWithdrawals(QueryPageCheckWithCnd request, Integer curPage, Integer pageSize) {
		Page page = new Page(curPage, pageSize);
		if (StringUtils.isNotEmpty(request.getStartDate())) {
			request.setStartDate(request.getStartDate() + " 00:00:00");
		}
		if (StringUtils.isNotEmpty(request.getEndDate())) {
			request.setEndDate(request.getEndDate() + " 23:59:59");
		}
		Integer count = checkWithMapper.queryPageCheckWithCount(request);
		page.setTotalCount(count);
		List<CheckWithdrawals> checkWithList = checkWithMapper.queryPageCheckWithdrawals(request, page);
		page.setResult(checkWithList);
		return page;
	}

	/**
	 * 提现对账操作,通过ID变更对账信息
	 * 
	 * @param requestCnd
	 */
	public int updateCheckWithById(UpdateCheckWithCnd requestCnd) {
		return checkWithMapper.updateCheckWithById(requestCnd);
	}

	/****
	 * 根据ID查询当日提现信息
	 * 
	 * @param id
	 * @return
	 */
	public CheckWithdrawals selectByPrimaryKey(Integer id) {
		return checkWithMapper.selectByPrimaryKey(id);
	}

	/**
	 * 导出提现列表信息
	 * 
	 * @param request
	 * @return
	 */
	public List<CheckWithdrawals> exportCheckWithList(QueryPageCheckWithCnd request) {
		Page page = new Page();
		if (StringUtils.isNotEmpty(request.getStartDate())) {
			request.setStartDate(request.getStartDate() + " 00:00:00");
		}
		if (StringUtils.isNotEmpty(request.getEndDate())) {
			request.setEndDate(request.getEndDate() + " 23:59:59");
		}
		Integer count = checkWithMapper.queryPageCheckWithCount(request);
		page.setPageSize(count);
		return checkWithMapper.queryPageCheckWithdrawals(request, page);
	}

	/**
	 * 提现 实际打款总额 与 实际网银打款 数据校对
	 * 
	 * @param requestCnd
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> checkWithdrawals(CheckWithCnd requestCnd) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", false);
		CheckWithdrawals che = checkWithMapper.selectByPrimaryKey(requestCnd.getId());
		// 实际网银打款 = 网银划出金额 + 手续费
		BigDecimal sum = requestCnd.getTotalExpenditure().add(requestCnd.getCounterFee());
		if (che != null) {
			// 实际打款总额 = 实际网银打款
			BigDecimal returnCompare = che.getActualCashMoney().subtract(sum);
			if (returnCompare.compareTo(BigDecimal.ZERO) == 0) {
				map.put("result", true);
			} else {
				map.put("difference", returnCompare);
			}
		}
		return map;
	}

	/****
	 * 当净提现总额、提现支出总额、实际打款总额三者不相等时，手动更新该天数据
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	public int updateWithdrawal(Integer id, Integer userId, String ipAddr) throws Exception {
		CheckWithdrawals che = checkWithMapper.selectByPrimaryKeyForUpdate(id);
		if (che != null && che.getBillDate() != null) {
			Date searchDate = che.getBillDate();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			format.format(searchDate);
			AccountCheckCnd accountCheckCnd = new AccountCheckCnd();
			accountCheckCnd.setBeginTime(DateUtils.convert2StartDate(searchDate));
			accountCheckCnd.setBeginTimeStr(accountCheckCnd.getBeginTime().getTime() / 1000 + "");
			accountCheckCnd.setEndTime(DateUtils.convert2EndDate(searchDate));
			accountCheckCnd.setEndTimeStr(accountCheckCnd.getEndTime().getTime() / 1000 + "");

			// 根据查询条件统计网站的提现记录总额
			BigDecimal paymentSuccessMoney = accountCheckMapper.queryPaymentSuccessMoneyByCnd(accountCheckCnd);
			paymentSuccessMoney = paymentSuccessMoney == null ? BigDecimal.ZERO : paymentSuccessMoney;

			// 根据查询条件统计网站的提现作废总额
			BigDecimal paymentFailedMoney = accountCheckMapper.queryPaymentFailedMoneyByCnd(accountCheckCnd);
			paymentFailedMoney = paymentFailedMoney == null ? BigDecimal.ZERO : paymentFailedMoney;
			// 根据条件统计网站的实现提现作废总额
			BigDecimal actualFailedMoney = accountCheckMapper.queryPaymentActualFailedMoneyByCnd(accountCheckCnd);
			actualFailedMoney = actualFailedMoney == null ? BigDecimal.ZERO : actualFailedMoney;
			// 净提现总额 = 提现记录总额-提现作废总额+实现提现作废总额
			BigDecimal paymentRealSuccessMoney = paymentSuccessMoney.subtract(paymentFailedMoney)
					.add(actualFailedMoney);
			// 根据查询条件统计网站提现支出总额（包括作废金额）
			BigDecimal takeCashMoney = accountCheckMapper.queryTakeCashMoneyByCnd(accountCheckCnd);
			takeCashMoney = takeCashMoney == null ? BigDecimal.ZERO : takeCashMoney;
			// 根据查询条件统计公司实际打款总额
			BigDecimal actualCashMoney = accountCheckMapper.queryActualCashMoneyByCnd(accountCheckCnd);
			actualCashMoney = actualCashMoney == null ? BigDecimal.ZERO : actualCashMoney;

			CheckWithdrawals checkWithdrawals = new CheckWithdrawals();
			// 比较：净提现总额、提现支出总额、实际打款总额三者是否一致
			if (paymentRealSuccessMoney.compareTo(takeCashMoney) == 0
					&& paymentRealSuccessMoney.compareTo(actualCashMoney) == 0) {
				// 设置状态为未对账
				checkWithdrawals.setIsSuccess(WithdrawalsStatus.ORDER.getCode());
			} else {
				// 设置状态为 信息不匹配
				checkWithdrawals.setIsSuccess(WithdrawalsStatus.APPLY.getCode());
			}
			checkWithdrawals.setId(id);
			checkWithdrawals.setPresentSuccessMoney(paymentRealSuccessMoney);
			checkWithdrawals.setTakeCashMoney(takeCashMoney);
			checkWithdrawals.setActualCashMoney(actualCashMoney);
			checkWithdrawals.setUpdateIp(ipAddr);
			checkWithdrawals.setUpdateUser(userId);
			return checkWithMapper.updateWithdrawal(checkWithdrawals);
		}
		return 0;
	}

	/**
	 * 根据日期手工对账
	 * 
	 * @param billDate
	 * @return
	 * @throws Exception
	 */
	public boolean updateCheckWithdrawals(CheckWithdrawals requestCnd) throws Exception {
		requestCnd = this.queryTakeCashCheckByCnd(requestCnd);
		int flag = checkWithMapper.updateByPrimaryKey(requestCnd);
		if (flag == 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 汇总匹配每日提现对账数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public CheckWithdrawals queryTakeCashCheckByCnd(CheckWithdrawals withdrawals) throws Exception {
		AccountCheckCnd accountCheckCnd = this.createTime(withdrawals.getBillDate());
		// 根据查询条件统计网站的提现记录总额
		BigDecimal paymentSuccessMoney = this.queryPaymentSuccessMoneyByCnd(accountCheckCnd);
		// 根据查询条件统计网站的提现作废总额
		BigDecimal paymentFailedMoney = this.queryPaymentFailedMoneyByCnd(accountCheckCnd);
		// 根据条件统计网站的实现提现作废总额
		BigDecimal actualFailedMoney = this.queryPaymentActualFailedMoneyByCnd(accountCheckCnd);
		// 净提现总额 = 提现记录总额-提现作废总额+实现提现作废总额
		BigDecimal paymentRealSuccessMoney = paymentSuccessMoney.subtract(paymentFailedMoney).add(actualFailedMoney);
		withdrawals.setPresentSuccessMoney(paymentRealSuccessMoney);

		// 根据查询条件统计网站提现支出总额（包括作废金额）
		BigDecimal takeCashMoney = this.queryTakeCashMoneyByCnd(accountCheckCnd);
		withdrawals.setTakeCashMoney(takeCashMoney);
		// 根据查询条件统计公司实际打款总额
		BigDecimal actualCashMoney = this.queryActualCashMoneyByCnd(accountCheckCnd);
		withdrawals.setActualCashMoney(actualCashMoney);
		// 比较：净提现总额、提现支出总额、实际打款总额三者是否一致
		if (paymentRealSuccessMoney.compareTo(takeCashMoney) == 0
				&& paymentRealSuccessMoney.compareTo(actualCashMoney) == 0) {
			// 设置状态为未对账
			withdrawals.setIsSuccess(WithdrawalsStatus.ORDER.getCode());
		} else {
			// 设置状态为 信息不匹配
			withdrawals.setIsSuccess(WithdrawalsStatus.APPLY.getCode());
		}
		return withdrawals;
	}

	/**
	 * <p>
	 * Description:根据查询条件统计网站的提现记录总额<br />
	 * </p>
	 * 
	 * @author xiaofei.li
	 * @version 0.1 2016年4月12日16:42:07
	 * @param accountCheckCnd
	 * @return
	 * @throws Exception
	 *             BigDecimal
	 */
	private BigDecimal queryPaymentSuccessMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception {
		BigDecimal result = accountCheckMapper.queryPaymentSuccessMoneyByCnd(accountCheckCnd);
		if (null != result) {
			return result;
		}
		return BigDecimal.ZERO;
	}

	/**
	 * <p>
	 * Description:根据查询条件统计网站的提现作废总额<br />
	 * </p>
	 * 
	 * @author xiaofei.li
	 * @version 0.1 2016年4月12日16:42:07
	 * @param accountCheckCnd
	 * @return
	 * @throws Exception
	 *             BigDecimal
	 */
	private BigDecimal queryPaymentFailedMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception {
		BigDecimal result = accountCheckMapper.queryPaymentFailedMoneyByCnd(accountCheckCnd);
		if (null != result) {
			return result;
		}
		return BigDecimal.ZERO;
	}

	/**
	 * <p>
	 * Description:根据查询条件统计网站的实际提现作废总额<br />
	 * </p>
	 * 
	 * @author xiaofei.li
	 * @version 0.1 2016年4月12日16:42:07
	 * @param accountCheckCnd
	 * @return
	 * @throws Exception
	 *             BigDecimal
	 */
	private BigDecimal queryPaymentActualFailedMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception {
		BigDecimal result = accountCheckMapper.queryPaymentActualFailedMoneyByCnd(accountCheckCnd);
		if (null != result) {
			return result;
		}
		return BigDecimal.ZERO;
	}

	/**
	 * <p>
	 * Description:根据查询条件统计网站提现支出总额<br />
	 * </p>
	 * 
	 * @author xiaofei.li
	 * @version 0.1 2016年4月12日16:42:07
	 * @param accountCheckCnd
	 * @return
	 * @throws Exception
	 *             BigDecimal
	 */
	private BigDecimal queryTakeCashMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception {
		BigDecimal result = accountCheckMapper.queryTakeCashMoneyByCnd(accountCheckCnd);
		if (null != result) {
			return result;
		}
		return BigDecimal.ZERO;
	}

	/**
	 * <p>
	 * Description:根据查询条件统计公司实际打款总额（包括作废金额）<br />
	 * </p>
	 * 
	 * @author xiaofei.li
	 * @version 0.1 2016年4月12日16:42:07
	 * @param accountCheckCnd
	 * @return
	 * @throws Exception
	 *             BigDecimal
	 */
	private BigDecimal queryActualCashMoneyByCnd(AccountCheckCnd accountCheckCnd) throws Exception {
		BigDecimal result = accountCheckMapper.queryActualCashMoneyByCnd(accountCheckCnd);
		if (null != result) {
			return result;
		}
		return BigDecimal.ZERO;
	}

	private AccountCheckCnd createTime(Date billDate) {
		AccountCheckCnd accountCheckCnd = new AccountCheckCnd();
		accountCheckCnd.setBeginTime(DateUtils.convert2StartDate(billDate));
		accountCheckCnd.setBeginTimeStr(accountCheckCnd.getBeginTime().getTime() / 1000 + "");
		accountCheckCnd.setEndTime(DateUtils.convert2EndDate(billDate));
		accountCheckCnd.setEndTimeStr(accountCheckCnd.getEndTime().getTime() / 1000 + "");
		return accountCheckCnd;
	}

}
