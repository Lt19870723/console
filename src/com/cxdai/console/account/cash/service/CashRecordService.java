package com.cxdai.console.account.cash.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.account.cash.entity.CashRecord;
import com.cxdai.console.account.cash.entity.CashRecordlog;
import com.cxdai.console.account.cash.mapper.CashRecordMapper;
import com.cxdai.console.account.cash.mapper.CollectionMapper;
import com.cxdai.console.account.cash.vo.CashPayVo;
import com.cxdai.console.account.cash.vo.CashRecordCnd;
import com.cxdai.console.account.cash.vo.CashRecordVo;
import com.cxdai.console.account.cash.vo.DrawMoneyToNoDrawCnd;
import com.cxdai.console.account.recharge.entity.Account;
import com.cxdai.console.account.recharge.mapper.BaseAccountMapper;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.curaccount.mapper.CurAccountMapper;
import com.cxdai.console.curaccount.vo.CurAccountVo;
import com.cxdai.console.customer.information.service.MemberService;
import com.cxdai.console.customer.information.vo.MemberVo;
import com.cxdai.console.fix.mapper.FixCollectionrecordMapper;
import com.cxdai.console.statistics.account.entity.UserNetRepayMoneyTotal;
import com.cxdai.console.statistics.account.entity.UserNetValue;
import com.cxdai.console.statistics.account.mapper.AccountNetValueMapper;
import com.cxdai.console.statistics.account.service.AccountLogService;
import com.cxdai.console.statistics.account.service.AccountService;
import com.cxdai.console.statistics.account.vo.AccountVo;
import com.cxdai.console.system.vo.UserVo;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.HttpTookit;
import com.cxdai.console.util.exception.AppException;

/**
 * <p>
 * Description:提现记录业务实现类<br />
 * </p>
 * 
 * @title CashRecordServiceImpl.java
 * @package com.cxdai.console.account.service.impl
 * @author justin.xu
 * @version 0.1 2014年6月26日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class CashRecordService {

	@Autowired
	private CashRecordMapper cashRecordMapper;
	@Autowired
	private CollectionMapper collectionMapper;
	@Autowired
	private AccountService accountService;
	@Autowired
	private BorrowReportService borrowReportService;
	@Autowired
	private AccountNetValueMapper accountNetValueMapper;
	@Autowired
	private BaseAccountMapper baseAccountMapper;
	@Autowired
	private AccountLogService accountLogService;
	@Autowired
	private CashRecordlogService cashRecordlogService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private CurAccountMapper curMapper;
	@Autowired
	private FixCollectionrecordMapper fixCollectionMapper;

	public Page queryPageListByCnd(CashRecordCnd cashRecordCnd, Integer curPage, Integer pageSize) throws Exception {

		Page page = new Page(curPage, pageSize);
		if (cashRecordCnd.getCashColumn() != null) {
			if (cashRecordCnd.getCashColumn() == 1) { // 申请提现
				cashRecordCnd.setStatus(String.valueOf(Constants.CASH_RECORD_STATUS_APPLYING));
				cashRecordCnd.setOrderSql(" order by c.ADDTIME asc");
			} else if (cashRecordCnd.getCashColumn() == 2) { // 提现处理
				cashRecordCnd.setOrderSql(" order by c.id asc");
			} else if (cashRecordCnd.getCashColumn() == 3) { // 提现查询
				cashRecordCnd.setOrderSql(" order by FIELD(c.STATUS,0,1,2,3,-1),c.VERIFY_TIME2 desc,c.ADDTIME desc");
			} else if (cashRecordCnd.getCashColumn() == 4) { // 提现打款成功
				cashRecordCnd.setOrderSql(" order by c.ADDTIME desc");
			}
		}
		if (cashRecordCnd.getCashColumn() != null && cashRecordCnd.getCashColumn() == 2) { // 提现处理
			// 转换成秒数
			if (null != cashRecordCnd.getBeginTime()) {
				cashRecordCnd.setBeginTimeStr(DateUtils.dateTime2TimeStamp(DateUtils.format(cashRecordCnd.getBeginTime(), DateUtils.YMD_HMS)));
			} else {
				cashRecordCnd.setBeginTimeStr(null);
			}
			if (null != cashRecordCnd.getEndTime()) {
				cashRecordCnd.setEndTimeStr(DateUtils.dateTime2TimeStamp(DateUtils.format(cashRecordCnd.getEndTime(), DateUtils.YMD_HMS)));
			} else {
				cashRecordCnd.setEndTimeStr(null);
			}
		} else {
			// 转换成秒数
			if (null != cashRecordCnd.getBeginTime()) {
				cashRecordCnd.setBeginTimeStr(DateUtils.convert2StartDate(cashRecordCnd.getBeginTime()).getTime() / 1000 + "");
			} else {
				cashRecordCnd.setBeginTimeStr(null);
			}
			if (null != cashRecordCnd.getEndTime()) {
				cashRecordCnd.setEndTimeStr(DateUtils.convert2EndDate(cashRecordCnd.getEndTime()).getTime() / 1000 + "");
			} else {
				cashRecordCnd.setEndTimeStr(null);
			}
			if (null != cashRecordCnd.getBeginTime2()) {
				cashRecordCnd.setBeginTime2Str(DateUtils.convert2StartDate(cashRecordCnd.getBeginTime2()).getTime() / 1000 + "");
			} else {
				cashRecordCnd.setBeginTime2Str(null);
			}
			if (null != cashRecordCnd.getEndTime2()) {
				cashRecordCnd.setEndTime2Str(DateUtils.convert2EndDate(cashRecordCnd.getEndTime2()).getTime() / 1000 + "");
			} else {
				cashRecordCnd.setEndTime2Str(null);
			}
			if (null != cashRecordCnd.getVerifyTimeBeginDate()) {
				cashRecordCnd.setVerifyTimeBeginStr(DateUtils.convert2StartDate(cashRecordCnd.getVerifyTimeBeginDate()).getTime() / 1000 + "");
			} else {
				cashRecordCnd.setVerifyTimeBeginStr(null);
			}
			if (null != cashRecordCnd.getVerifyTimeEndDate()) {
				cashRecordCnd.setVerifyTimeEndStr(DateUtils.convert2EndDate(cashRecordCnd.getVerifyTimeEndDate()).getTime() / 1000 + "");
			} else {
				cashRecordCnd.setVerifyTimeEndStr(null);
			}
		}
        if (cashRecordCnd.getIsCustody() != null && cashRecordCnd.getIsCustody() == -1) {
            cashRecordCnd.setIsCustody(null);
        }
		int totalCount = cashRecordMapper.queryCashRecordCount(cashRecordCnd);
		page.setTotalCount(totalCount);
		List<CashRecordVo> list = cashRecordMapper.queryCashRecordList(cashRecordCnd, page);
		page.setResult(list);
		return page;

	}

	public Map<String, BigDecimal> queryCashRecorData(CashRecordCnd cashRecordCnd) throws Exception {
        if (cashRecordCnd.getIsCustody() != null && cashRecordCnd.getIsCustody() == -1) {
            cashRecordCnd.setIsCustody(null);
        }
		if (cashRecordCnd.getCashColumn() != null) {
			if (cashRecordCnd.getCashColumn() == 1) { // 申请提现
				cashRecordCnd.setStatus(String.valueOf(Constants.CASH_RECORD_STATUS_APPLYING));
				cashRecordCnd.setOrderSql(" order by c.ADDTIME asc");
			} else if (cashRecordCnd.getCashColumn() == 2) { // 提现处理
				// fateson 2015年1月22日 update start
				// 修改 c.addtime 为 c.id 查询的时候如果时间一样 可能相同sql 查询到的数据不一样，导致导出不一样
				// cashRecordCnd.setOrderSql(" order by c.ADDTIME asc");
				cashRecordCnd.setOrderSql(" order by c.id asc");
				// fateson end
			} else if (cashRecordCnd.getCashColumn() == 3) { // 提现查询
				cashRecordCnd.setOrderSql(" order by FIELD(c.STATUS,0,1,2,3,-1),c.ADDTIME desc");
			} else if (cashRecordCnd.getCashColumn() == 4) { // 提现打款成功
				cashRecordCnd.setOrderSql(" order by c.ADDTIME desc");
			}
		}
		if (cashRecordCnd.getCashColumn() != null && cashRecordCnd.getCashColumn() == 2) { // 提现处理
			// 转换成秒数
			if (null != cashRecordCnd.getBeginTime()) {
				cashRecordCnd.setBeginTimeStr(DateUtils.dateTime2TimeStamp(DateUtils.format(cashRecordCnd.getBeginTime(), DateUtils.YMD_HMS)));
			} else {
				cashRecordCnd.setBeginTimeStr(null);
			}
			if (null != cashRecordCnd.getEndTime()) {
				cashRecordCnd.setEndTimeStr(DateUtils.dateTime2TimeStamp(DateUtils.format(cashRecordCnd.getEndTime(), DateUtils.YMD_HMS)));
			} else {
				cashRecordCnd.setEndTimeStr(null);
			}
		} else {
			// 转换成秒数
			if (null != cashRecordCnd.getBeginTime()) {
				cashRecordCnd.setBeginTimeStr(DateUtils.convert2StartDate(cashRecordCnd.getBeginTime()).getTime() / 1000 + "");
			} else {
				cashRecordCnd.setBeginTimeStr(null);
			}
			if (null != cashRecordCnd.getEndTime()) {
				cashRecordCnd.setEndTimeStr(DateUtils.convert2EndDate(cashRecordCnd.getEndTime()).getTime() / 1000 + "");
			} else {
				cashRecordCnd.setEndTimeStr(null);
			}
			if (null != cashRecordCnd.getBeginTime2()) {
				cashRecordCnd.setBeginTime2Str(DateUtils.convert2StartDate(cashRecordCnd.getBeginTime2()).getTime() / 1000 + "");
			} else {
				cashRecordCnd.setBeginTime2Str(null);
			}
			if (null != cashRecordCnd.getEndTime2()) {
				cashRecordCnd.setEndTime2Str(DateUtils.convert2EndDate(cashRecordCnd.getEndTime2()).getTime() / 1000 + "");
			} else {
				cashRecordCnd.setEndTime2Str(null);
			}
		}
		Map<String, BigDecimal> resultMap = new HashMap<String, BigDecimal>();
		Map<String, Object> map = cashRecordMapper.queryCashRecorData(cashRecordCnd);
		if (map != null && map.get("sum_total") != null) {
			resultMap.put("sumTotal", new BigDecimal(map.get("sum_total").toString()).setScale(2, BigDecimal.ROUND_DOWN));
		} else {
			resultMap.put("sumTotal", new BigDecimal(0));
		}
		if (map != null && map.get("sum_credited") != null) {
			resultMap.put("sumCredited", new BigDecimal(map.get("sum_credited").toString()).setScale(2, BigDecimal.ROUND_DOWN));
		} else {
			resultMap.put("sumCredited", new BigDecimal(0));
		}
		if (map != null && map.get("sum_fee") != null) {
			resultMap.put("sumFee", new BigDecimal(map.get("sum_fee").toString()).setScale(2, BigDecimal.ROUND_DOWN));
		} else {
			resultMap.put("sumFee", new BigDecimal(0));
		}
		if (map != null && map.get("sum_unfee") != null) {
			resultMap.put("sumUnFee", new BigDecimal(map.get("sum_unfee").toString()).setScale(2, BigDecimal.ROUND_DOWN));
		} else {
			resultMap.put("sumUnFee", new BigDecimal(0));
		}

		return resultMap;

	}

	public CashRecordVo queryCashRecordById(CashRecordCnd cashRecordCnd) throws Exception {
		List<CashRecordVo> list = cashRecordMapper.queryCashRecordList(cashRecordCnd);
		if (null != list && list.size() == 1) {
			return list.get(0);
		}
		return null;
	}

	public CashRecordVo queryCashRecordByIdForUpdate(Integer id) throws Exception {
		return cashRecordMapper.queryCashRecordByIdForUpdate(id);
	}

	public List<CashRecordVo> queryCashTwoSuccessListByMemberId(Integer memberId) throws Exception {
		return cashRecordMapper.queryCashTwoSuccessListByMemberId(memberId);
	}

	/**
	 * <p>
	 * Description:验证审核提现记录数据的正确性<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年7月2日
	 * @param cashRecordVo
	 * @param updateCashRecordVo
	 *            void
	 */
	private String validateCashApproData(CashRecordVo cashRecordVo, CashRecordVo updateCashRecordVo) {
		String result = "success";
		if (null == updateCashRecordVo) {
			return "数据已变更，请刷新页面或稍或重试！";
		}
		if (updateCashRecordVo.getStatus() != Constants.CASH_RECORD_STATUS_APPLYING) {
			return "提现记录状态非申请提现，请刷新页面或稍或重试！";
		}
		return result;
	}

	public List<CashRecordVo> exportToExcel(CashRecordCnd cashRecordCnd, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (cashRecordCnd.getIsCustody() != null && cashRecordCnd.getIsCustody() == -1) {
            cashRecordCnd.setIsCustody(null);
        }
        if (cashRecordCnd.getCashColumn() != null) {
			if (cashRecordCnd.getCashColumn() == 1) { // 申请提现
				cashRecordCnd.setStatus(String.valueOf(Constants.CASH_RECORD_STATUS_APPLYING));
				cashRecordCnd.setOrderSql(" order by c.ADDTIME");
			} else if (cashRecordCnd.getCashColumn() == 2) { // 提现处理
				cashRecordCnd.setOrderSql(" order by FIELD(c.STATUS,1,2),c.ADDTIME asc");
			} else if (cashRecordCnd.getCashColumn() == 3) { // 提现查询
				cashRecordCnd.setOrderSql(" order by FIELD(c.STATUS,0,1,2,3,-1),c.VERIFY_TIME2 desc,c.ADDTIME desc");
			}
		}
		// 转换成秒数
		if (null != cashRecordCnd.getBeginTime()) {
			cashRecordCnd.setBeginTimeStr(DateUtils.convert2StartDate(cashRecordCnd.getBeginTime()).getTime() / 1000 + "");
		} else {
			cashRecordCnd.setBeginTimeStr(null);
		}
		if (null != cashRecordCnd.getEndTime()) {
			cashRecordCnd.setEndTimeStr(DateUtils.convert2EndDate(cashRecordCnd.getEndTime()).getTime() / 1000 + "");
		} else {
			cashRecordCnd.setEndTimeStr(null);
		}
		if (null != cashRecordCnd.getBeginTime2()) {
			cashRecordCnd.setBeginTime2Str(DateUtils.convert2StartDate(cashRecordCnd.getBeginTime2()).getTime() / 1000 + "");
		} else {
			cashRecordCnd.setBeginTime2Str(null);
		}
		if (null != cashRecordCnd.getEndTime2()) {
			cashRecordCnd.setEndTime2Str(DateUtils.convert2EndDate(cashRecordCnd.getEndTime2()).getTime() / 1000 + "");
		} else {
			cashRecordCnd.setEndTime2Str(null);
		}
		List<CashRecordVo> list = cashRecordMapper.queryCashRecordList(cashRecordCnd);

		return list;
	}

	@Transactional(rollbackFor = Throwable.class)
	public List<CashPayVo> exportForPayToExcel(String ids) throws Exception {
		List<CashPayVo> list = new ArrayList<CashPayVo>();
		String[] idss = ids.split(",");
		if (idss.length > 0) {
			list = cashRecordMapper.exportForPayToExcel(idss, "0");
		}
		return list;
	}

	public Map<String, BigDecimal> queryDataForCashApproById(Integer id) throws Exception {
		Map<String, BigDecimal> resultMap = new HashMap<String, BigDecimal>();
		CashRecordVo cashRecordVo = cashRecordMapper.queryCashRecordById(id);
		// 资产总额
		BigDecimal total = null;
		// 查询提现冻结总额
		BigDecimal cashFreezeTotal = null;
		// 待收利息
		BigDecimal collectionInterest = null;
		// 净值标还款中的待还总额总计
		BigDecimal repaymentAccountTotal = null;
		// 用户可提取总额
		BigDecimal drawTotal = null;
		if (cashRecordVo != null) {
			AccountVo accountVo = accountService.queryAccountByUserId(cashRecordVo.getUserId());
			if (accountVo != null) {
				total = accountVo.getTotal();

			} else {
				total = BigDecimal.ZERO;
			}

			// 2015.12.18 总额加上活期宝&定期宝
			CurAccountVo cur = curMapper.selectByUserId(cashRecordVo.getUserId());
			if (cur != null) {
				total = total.add(cur.getTotal());
			}
			BigDecimal fixTotal = fixCollectionMapper.getTotalByUserId(cashRecordVo.getUserId());
			total = total.add(fixTotal);

			cashFreezeTotal = cashRecordMapper.queryTotalForFreeze(cashRecordVo.getUserId());
			if (cashFreezeTotal == null) {
				cashFreezeTotal = BigDecimal.ZERO;
			}

			collectionInterest = collectionMapper.queryUnReceiveInterstTotalByMemberId(cashRecordVo.getUserId());
			if (collectionInterest == null) {
				collectionInterest = BigDecimal.ZERO;
			}

			repaymentAccountTotal = borrowReportService.queryRepaymentAccountTotalByMemberIdAndBorrowType(cashRecordVo.getUserId(),
					Constants.BORROW_TYPE_NETVALUE);
			if (repaymentAccountTotal == null) {
				repaymentAccountTotal = BigDecimal.ZERO;
			}
		} else {
			total = BigDecimal.ZERO;
			cashFreezeTotal = BigDecimal.ZERO;
			collectionInterest = BigDecimal.ZERO;
			repaymentAccountTotal = BigDecimal.ZERO;
		}
		drawTotal = total.subtract(repaymentAccountTotal).subtract(collectionInterest).subtract(cashFreezeTotal).add(cashRecordVo.getTotal());
		resultMap.put("total", total);
		resultMap.put("repaymentAccountTotal", repaymentAccountTotal);
		resultMap.put("collectionInterest", collectionInterest);
		resultMap.put("cashFreezeTotal", cashFreezeTotal);
		resultMap.put("drawTotal", drawTotal);
		return resultMap;
	}

	public BigDecimal saveNetMoneyLimit(Integer userId) throws Exception {
		// 取得用户的净值额度
		UserNetValue netValue = new UserNetValue();
		netValue.setUserid(userId);
		accountNetValueMapper.callGetUserNetMoneyLimit(netValue);
		return netValue.getNetMoneyLimit();

	}

	/**
	 * 提现审核（通过）
	 */
	public String saveApprovePass(CashRecordVo cashRecordVo, UserVo userVo, HttpServletRequest request) throws Exception {
		String result = "success";
		// 根据id和版本号查询提现记录
		CashRecordVo updateCashRecordVo = this.queryCashRecordByIdForUpdate(cashRecordVo.getId());
		// 验证审核提现记录的正确性
		result = this.validateCashApproData(cashRecordVo, updateCashRecordVo);
		if (!"success".equals(result)) {
			return result;
		}
		UserNetValue userNetValue = new UserNetValue();
		userNetValue.setUserid(updateCashRecordVo.getUserId());
		accountNetValueMapper.callGetUserNetMoneyLimit(userNetValue);
		if (userNetValue == null || userNetValue.getNetMoneyLimit() == null) {
			return "净值额度为空,请刷新后重试!!";
		} else {
			if (userNetValue.getNetMoneyLimit().compareTo(new BigDecimal(0)) == -1) {
				return "净值额度为负数,无法审核通过!!";
			}
		}
		AccountVo accountVo = accountService.queryAccountByUserIdForUpdate(updateCashRecordVo.getUserId());
		Map<String, BigDecimal> cashMap = queryDataForCashApproById(cashRecordVo.getId());
		BigDecimal userCashTotal = new BigDecimal(0);
		if (cashMap != null && cashMap.get("drawTotal") != null) {
			if (cashRecordVo.getTotal().compareTo(cashMap.get("drawTotal")) == 1) {
				return "提现金额大于可提取总额，无法审核通过！";
			} else {
				userCashTotal = cashMap.get("drawTotal");
			}
		} else {
			return "可提取总额为空，请刷新后重试！";
		}
		UserNetRepayMoneyTotal userNetRepayMoneyTotal = new UserNetRepayMoneyTotal();
		userNetRepayMoneyTotal.setUserid(updateCashRecordVo.getUserId());
		accountNetValueMapper.callGetUserNetRepayMoneyTotal(userNetRepayMoneyTotal);
		if (userNetRepayMoneyTotal.getNetRepayMoneyTotal() != null && userNetRepayMoneyTotal.getManagerFeeTotal() != null) {
			BigDecimal resultValue = userCashTotal.subtract(userNetRepayMoneyTotal.getNetRepayMoneyTotal()).subtract(
					userNetRepayMoneyTotal.getManagerFeeTotal());
			if (cashRecordVo.getTotal().compareTo(resultValue) == 1) {
				return "提现金额不符合，无法审核通过";
			}
		}
		// 更新状态为审核通过
		updateCashRecordVo.setVerifyRemark(cashRecordVo.getVerifyRemark());
		updateCashRecordVo.setVerifyTime(DateUtils.getTime());
		updateCashRecordVo.setVerifyUserid(userVo.getId());
		modifyStatusForApproveCash(Constants.RECHARGE_STATUS_SUCCESS, updateCashRecordVo);
		// 插入审核资金记录
		String remark = "审核成功，财务人员会尽快把钱转入您填写的银行账户中。本次提现手续费为：" + updateCashRecordVo.getFee() + "元";
		accountLogService.saveAccountLogByParams(accountVo, updateCashRecordVo.getUserId(), updateCashRecordVo.getTotal(), remark,
				HttpTookit.getRealIpAddr(request), "cash_success", null, null, null);
		return result;
	}

	/**
	 * 提现审核不通过
	 */
	public String saveApproveReject(CashRecordVo cashRecordVo, UserVo userVo, HttpServletRequest request) throws Exception {
		String result = "success";
		// 根据id查询提现记录并锁定
		CashRecordVo updateCashRecordVo = this.queryCashRecordByIdForUpdate(cashRecordVo.getId());
		// 验证审核提现记录的正确性
		result = this.validateCashApproData(cashRecordVo, updateCashRecordVo);
		if (!"success".equals(result)) {
			return result;
		}
		// 更新状态为审核不通过
		updateCashRecordVo.setVerifyRemark(cashRecordVo.getVerifyRemark());
		updateCashRecordVo.setVerifyTime(DateUtils.getTime());
		updateCashRecordVo.setVerifyUserid(userVo.getId());
		modifyStatusForApproveCash(Constants.CASH_RECORD_STATUS_APPROVE_FAILURE, updateCashRecordVo);
		AccountVo accountVo = accountService.queryAccountByUserIdForUpdate(updateCashRecordVo.getUserId());
		// 解冻用户提现资金
		accountVo = this.refreshAccountMoneyByApproReject(updateCashRecordVo, accountVo);
		// 插入审核资金记录
		String remark = "提现失败，失败原因查看提现记录备注。";
		accountLogService.saveAccountLogByParams(accountVo, updateCashRecordVo.getUserId(), updateCashRecordVo.getTotal(), remark,
				HttpTookit.getRealIpAddr(request), "cash_faild", null, null, null);

		DrawMoneyToNoDrawCnd drawMoneyToNoDrawCnd = new DrawMoneyToNoDrawCnd();
		drawMoneyToNoDrawCnd.setUserid(cashRecordVo.getUserId());
		drawMoneyToNoDrawCnd.setNetmoneytype(BusinessConstants.NET_TYPE_CASH_FAILED);
		drawMoneyToNoDrawCnd.setAddip(HttpTookit.getRealIpAddr(request));
		drawMoneyToNoDrawCnd.setAccountlogType("net_draw_to_nodraw_cash_reject");
		drawMoneyToNoDrawCnd.setAccountlogRemark("提现审核不通过之后,可提金额大于净值额度，可提金额转入受限金额。");
		accountNetValueMapper.dealDrawmoneyToNodraw(drawMoneyToNoDrawCnd);
		return result;
	}

	/**
	 * Description:审核不通过更新用户帐号的可用余额和不可用余额<br />
	 * 
	 * @param rechargeRecordVo
	 * @return Account
	 * @throws Exception
	 */
	private AccountVo refreshAccountMoneyByApproReject(CashRecordVo cashRecordVo, AccountVo accountVo) throws Exception {
		Account account = new Account();
		accountVo.setNoUseMoney(accountVo.getNoUseMoney().subtract(cashRecordVo.getTotal()));
		accountVo.setUseMoney(accountVo.getUseMoney().add(cashRecordVo.getTotal()));
		accountVo.setDrawMoney(accountVo.getDrawMoney().add(cashRecordVo.getTotal()));
		BeanUtils.copyProperties(accountVo, account);
		baseAccountMapper.updateEntity(account);
		return accountVo;
	}

	/**
	 * <p>
	 * Description:更新提现记录状态 <br />
	 * </p>
	 * 
	 * @param status
	 * @param updateCashRecordVo
	 * @param staff
	 * @return
	 * @throws Exception
	 *             Integer
	 */
	private Integer modifyStatusForApproveCash(Integer status, CashRecordVo updateCashRecordVo) throws Exception {
		CashRecord updateCashRecord = new CashRecord();
		BeanUtils.copyProperties(updateCashRecordVo, updateCashRecord);
		updateCashRecord.setStatus(status);
		Integer updateCount = cashRecordMapper.updateEntity(updateCashRecord);
		return updateCount;
	}

	/**
	 * 立即打款
	 */
	public String savePayCash(Integer id, UserVo userVo, HttpServletRequest request) throws Exception {
		if (null == id || id.equals("")) {
			return "参数错误";
		}
		CashRecordVo cashRecordVo = this.queryCashRecordByIdForUpdate(id);
		// 锁定记录
		if (null == cashRecordVo || cashRecordVo.getStatus() != Constants.CASH_RECORD_STATUS_APPROVE_SUCCESS) {
			return "提现记录未找到或状态已变更,请刷新数据或稍后重试！";
		}
		cashRecordVo.setVerifyRemark2("打款成功");
		cashRecordVo.setVerifyTime2(DateUtils.getTime());
		cashRecordVo.setVerifyUserid2(userVo.getId());
		// 立即提现，更新提现记录
		modifyStatusForApproveCash(Constants.CASH_RECORD_STATUS_CASH_FINISH, cashRecordVo);
		AccountVo accountVo = accountService.queryAccountByUserIdForUpdate(cashRecordVo.getUserId());
		// 扣除提现资金
		accountVo = this.refreshAccountMoneyByPayCash(cashRecordVo, accountVo);
		// 插入账户资金记录
		String remark = "打款成功，请注意查收。";
		accountLogService.saveAccountLogByParams(accountVo, cashRecordVo.getUserId(), cashRecordVo.getTotal(), remark, HttpTookit.getRealIpAddr(request),
				"payment_success", null, null, null);
		return "立即打款成功";
	}

	private AccountVo refreshAccountMoneyByPayCash(CashRecordVo cashRecordVo, AccountVo accountVo) throws Exception {
		Account account = new Account();
		accountVo.setNoUseMoney(accountVo.getNoUseMoney().subtract(cashRecordVo.getTotal()));
		accountVo.setTotal(accountVo.getTotal().subtract(cashRecordVo.getTotal()));
		BeanUtils.copyProperties(accountVo, account);
		baseAccountMapper.updateEntity(account);
		return accountVo;
	}

	/**
	 * 取消提现
	 */
	public String cancelCash(Integer id, UserVo userVo, HttpServletRequest request) throws Exception {
		if (id == null || id.equals("")) {
			return "取消提现参数错误";
		}
		CashRecordVo cashRecordVo = this.queryCashRecordByIdForUpdate(id);
		// 锁定记录
		if (null == cashRecordVo || cashRecordVo.getStatus() != Constants.CASH_RECORD_STATUS_APPROVE_SUCCESS) {
			return "提现记录未找到或状态已变更,请刷新数据或稍后重试！";
		}
		cashRecordVo.setVerifyTime(DateUtils.getTime());
		cashRecordVo.setVerifyUserid(userVo.getId());
		cashRecordVo.setVerifyRemark("取消提现");
		// 取消提现
		modifyStatusForApproveCash(Constants.CASH_RECORD_STATUS_CANCEL_CASH, cashRecordVo);
		AccountVo accountVo = accountService.queryAccountByUserIdForUpdate(cashRecordVo.getUserId());
		// 解冻用户提现资金
		accountVo = this.refreshAccountMoneyByApproReject(cashRecordVo, accountVo);
		// 插入账户资金记录
		String remark = "取消提现";
		accountLogService.saveAccountLogByParams(accountVo, cashRecordVo.getUserId(), cashRecordVo.getTotal(), remark, HttpTookit.getRealIpAddr(request),
				"cash_faild", null, null, null);

		// 取消提现：判断用户的可提金额是否大于净值额度，如果大于，转入受限金额
		saveDrawToNoDraw(HttpTookit.getRealIpAddr(request), cashRecordVo.getUserId(), BusinessConstants.NET_TYPE_CASH_CANCEL, "net_draw_to_nodraw_cash_cancel",
				"取消提现之后,可提金额大于净值额度，可提金额转入受限金额。");

		// 记录取消日志
		CashRecordlog cashRecordlog = new CashRecordlog();
		cashRecordlog.setAddip(HttpTookit.getRealIpAddr(request));
		cashRecordlog.setAddtime(new Date());
		cashRecordlog.setAddUserId(userVo.getId());
		cashRecordlog.setCashrecordId(cashRecordVo.getId());
		cashRecordlog.setType(Constants.CASHRECORD_LOG_TYPE_CANCEL);
		cashRecordlog.setPlatform(1);
		cashRecordlogService.saveCashRecordlog(cashRecordlog);
		return "取消提现成功";
	}

	/**
	 * <p>
	 * Description: 取消提现：判断用户的可提金额是否大于净值额度，如果大于，转入受限金额<br />
	 * </p>
	 * 
	 * @param ip
	 * @param cashRecordVo
	 * @throws Exception
	 *             void
	 */
	private void saveDrawToNoDraw(String ip, Integer userId, Integer netmoneytype, String accountlogType, String remark) throws Exception {
		DrawMoneyToNoDrawCnd drawMoneyToNoDrawCnd = new DrawMoneyToNoDrawCnd();
		drawMoneyToNoDrawCnd.setUserid(userId);
		drawMoneyToNoDrawCnd.setNetmoneytype(netmoneytype);
		drawMoneyToNoDrawCnd.setAddip(ip);
		drawMoneyToNoDrawCnd.setAccountlogType(accountlogType);
		drawMoneyToNoDrawCnd.setAccountlogRemark(remark);
		accountNetValueMapper.dealDrawmoneyToNodraw(drawMoneyToNoDrawCnd);
	}

	/**
	 * 民生打款批量导出（excel）
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Throwable.class)
	public List<CashPayVo> exportForPayToExcelToMS(String ids,Integer userId) throws Exception {
		List<CashPayVo> list = new ArrayList<CashPayVo>();
		String[] idss = ids.split(",");
		if (idss.length > 0) {
			list = cashRecordMapper.exportForPayToExcelToMS(idss, "0");
		}
		// 更新待打款的记录
		batchExported(ids, userId);
		return list;
	}

	/**
	 * 民生打款批量导出（txt）
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Throwable.class)
	public List<CashPayVo> exportForPayToTxtToMs(String ids, Integer userId) throws Exception {
		List<CashPayVo> list = new ArrayList<CashPayVo>();
		String[] idss = ids.split(",");
		if (idss.length > 0) {
			list = cashRecordMapper.exportForPayToExcelToMS(idss, "0");
		}
		// 更新待打款的记录
		batchExported(ids, userId);
		return list;
	}

	@Transactional(rollbackFor = Throwable.class)
	public String batchExported(String ids, Integer userId) throws Exception {
		if (ids == null || ids.equals("") || ids.equals("0")) {
			return "请选择要设置的记录。";
		}

		String[] idss = ids.split(",");
		for (int i = 0; i < idss.length; i++) {
			CashRecordVo cashRecordVo = cashRecordMapper.queryCashRecordByIdForUpdate(Integer.parseInt(idss[i]));
			if (cashRecordVo != null) {
				cashRecordVo.setIsExport(1);
				cashRecordVo.setExportUserId(userId);
				CashRecord cashRecord = new CashRecord();
				BeanUtils.copyProperties(cashRecordVo, cashRecord);
				cashRecord.setSelfVersion(cashRecord.getVersion());
				cashRecord.setVersion(cashRecord.getVersion() + 1);
				Integer updateCount = cashRecordMapper.updateEntity(cashRecord);
				if (null == updateCount || updateCount == 0) {
					throw new AppException("数据已变更,请刷新页面或稍后重试！");
				}
			}
		}
		return "批量设置已导出成功！";
	}

	@Transactional(rollbackFor = Throwable.class)
	public List<CashPayVo> exportForPayToExcelToWYZX(String ids, Integer userId) throws Exception {
		List<CashPayVo> list = new ArrayList<CashPayVo>();
		String[] idss = ids.split(",");
		if (idss.length > 0) {
			list = cashRecordMapper.exportForPayToExcelToWYZX(idss, "0");
		}
		// 更新待打款的记录
		batchExported(ids, userId);
		return list;
	}

	@Transactional(rollbackFor = Throwable.class)
	public List<CashPayVo> exportForPayToTxtToWXZX(String ids, Integer userId) throws Exception {
		List<CashPayVo> list = new ArrayList<CashPayVo>();
		String[] idss = ids.split(",");
		if (idss.length > 0) {
			list = cashRecordMapper.exportForPayToExcelToWYZX(idss, "0");
		}
		// 更新待打款的记录
		batchExported(ids, userId);
		return list;
	}

	@Transactional(rollbackFor = Throwable.class)
	public String batchPayCash(String ids, Integer userId) throws Exception {
		if (ids == null || ids.equals("") || ids.equals("0")) {
			return "请选择要设置的记录。";
		}
		String[] idss = ids.split(",");
		String tips = "";
		for (int i = 0; i < idss.length; i++) {
			CashRecordVo cashRecordVo = cashRecordMapper.queryCashRecordByIdForUpdate(Integer.parseInt(idss[i]));
			if (cashRecordVo != null && cashRecordVo.getStatus() == Constants.CASH_RECORD_STATUS_APPROVE_SUCCESS) {
				cashRecordVo.setStatus(2);
				cashRecordVo.setVerifyRemark2("打款成功");
				cashRecordVo.setVerifyTime2(DateUtils.getTime());
				cashRecordVo.setVerifyUserid2(userId);
				CashRecord cashRecord = new CashRecord();
				BeanUtils.copyProperties(cashRecordVo, cashRecord);
				cashRecord.setSelfVersion(cashRecord.getVersion());
				cashRecord.setVersion(cashRecord.getVersion() + 1);
				Integer updateCount = cashRecordMapper.updateEntity(cashRecord);
				if (null == updateCount || updateCount == 0) {
					throw new AppException("数据已变更,请刷新页面或稍后重试！");
				}

				AccountVo accountVo = accountService.queryAccountByUserIdForUpdate(cashRecordVo.getUserId());
				// 扣除提现资金
				accountVo = this.refreshAccountMoneyByPayCash(cashRecordVo, accountVo);
				// 插入账户资金记录
				String remark = "打款成功，请注意查收。";
				accountLogService.saveAccountLogByParams(accountVo, cashRecordVo.getUserId(), cashRecordVo.getTotal(), remark, "127.0.0.1", "payment_success",
						null, null, null);

			} else {
				Integer userIds = cashRecordVo.getUserId();
				MemberVo membervo = memberService.queryMemberById(userIds);
				BigDecimal total = cashRecordVo.getTotal();
				tips += "【用户名" + membervo.getUsername() + "，提现金额为" + total.doubleValue() + "】，";
			}

		}

		if (!"".equals(tips)) {
			throw new Exception("批量打款失败：" + tips + "状态已变更，请刷新页面重试");
		}

		return "批量打款成功！";
	}

	/**
	 * 打款失败
	 * 
	 * @param id
	 * @param remark
	 * @param ip
	 * @return
	 * @throws Exception
	 */
	public String saveFailCash(Integer id, UserVo userVo, String remark, HttpServletRequest request) throws Exception {
		CashRecordVo cashRecordVo = this.queryCashRecordByIdForUpdate(id);
		// 锁定记录
		if (null == cashRecordVo || cashRecordVo.getStatus() != Constants.CASH_RECORD_STATUS_APPROVE_SUCCESS) {
			return "提现记录未找到或状态已变更,请刷新数据或稍后重试！";
		}
		cashRecordVo.setVerifyTime2(DateUtils.getTime());
		cashRecordVo.setVerifyUserid2(userVo.getId());
		cashRecordVo.setVerifyRemark2(remark);
		// 取消提现
		modifyStatusForApproveCash(Constants.CASH_RECORD_STATUS_FAILD_CASH, cashRecordVo);
		AccountVo accountVo = accountService.queryAccountByUserIdForUpdate(cashRecordVo.getUserId());
		// 解冻用户提现资金
		accountVo = this.refreshAccountMoneyByApproReject(cashRecordVo, accountVo);
		// 插入账户资金记录
		accountLogService.saveAccountLogByParams(accountVo, cashRecordVo.getUserId(), cashRecordVo.getTotal(), "打款失败", HttpTookit.getRealIpAddr(request),
				"cash_faild", null, null, null);

		// 取消提现：判断用户的可提金额是否大于净值额度，如果大于，转入受限金额
		saveDrawToNoDraw(HttpTookit.getRealIpAddr(request), cashRecordVo.getUserId(), BusinessConstants.NET_TYPE_CASH_FAILED, "net_draw_to_nodraw_cash_failed",
				"打款失败之后,可提金额大于净值额度，可提金额转入受限金额。");

		// 记录取消日志
		CashRecordlog cashRecordlog = new CashRecordlog();
		cashRecordlog.setAddip(HttpTookit.getRealIpAddr(request));
		cashRecordlog.setAddtime(new Date());
		cashRecordlog.setAddUserId(userVo.getId());
		cashRecordlog.setCashrecordId(cashRecordVo.getId());
		cashRecordlog.setType(Constants.CASHRECORD_LOG_TYPE_FAILED);
		cashRecordlog.setPlatform(1);
		cashRecordlogService.saveCashRecordlog(cashRecordlog);
		return "取消提现成功";
	}

	/**
	 * 作废提现
	 * 
	 * @param id
	 * @param version
	 * @param ip
	 * @return
	 * @throws Exception
	 */
	public String saveInvalidCash(Integer id, UserVo userVo, String ip) throws Exception {
		if (id == null || id.equals("")) {
			return "提现作废参数错误";
		}
		CashRecordVo cashRecordVo = this.queryCashRecordByIdForUpdate(id);
		// 锁定记录
		if (null == cashRecordVo || cashRecordVo.getStatus() != Constants.CASH_RECORD_STATUS_CASH_FINISH) {
			return "提现记录未找到或状态已变更,请刷新数据或稍后重试！";
		}
		cashRecordVo.setVerifyTime3(DateUtils.getTime());
		cashRecordVo.setVerifyUserid3(userVo.getId());
		cashRecordVo.setVerifyRemark3("提现作废");
		// 取消提现
		modifyStatusForApproveCash(Constants.CASH_RECORD_STATUS_INVALID_CASH, cashRecordVo);

		// 总额增加,可用增加,可提增加
		AccountVo accountVo = accountService.queryAccountByUserIdForUpdate(cashRecordVo.getUserId());
		accountVo.setTotal(accountVo.getTotal().add(cashRecordVo.getTotal()));
		accountVo.setUseMoney(accountVo.getUseMoney().add(cashRecordVo.getTotal()));
		accountVo.setDrawMoney(accountVo.getDrawMoney().add(cashRecordVo.getTotal()));
		Account account = new Account();
		BeanUtils.copyProperties(accountVo, account);
		baseAccountMapper.updateEntity(account);

		// 插入账户资金记录
		accountLogService.saveAccountLogByParams(accountVo, cashRecordVo.getUserId(), cashRecordVo.getTotal(), "提现作废", ip, "cash_faild", null, null, null);

		// 取消提现：判断用户的可提金额是否大于净值额度，如果大于，转入受限金额
		saveDrawToNoDraw(ip, cashRecordVo.getUserId(), BusinessConstants.NET_TYPE_CASH_INVALID, "net_draw_to_nodraw_cash_invalid",
				"打款作废之后,可提金额大于净值额度，可提金额转入受限金额。");

		// 记录取消日志
		CashRecordlog cashRecordlog = new CashRecordlog();
		cashRecordlog.setAddip(ip);
		cashRecordlog.setAddtime(new Date());
		cashRecordlog.setAddUserId(userVo.getId());
		cashRecordlog.setCashrecordId(cashRecordVo.getId());
		cashRecordlog.setType(Constants.CASHRECORD_LOG_TYPE_INVALID);
		cashRecordlog.setPlatform(1);
		cashRecordlogService.saveCashRecordlog(cashRecordlog);
		return "提现作废成功";
	}

}
